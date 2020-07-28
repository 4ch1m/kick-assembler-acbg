package de.achimonline.kickassembler.acbg.sdk;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.AdditionalDataConfigurable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.openapi.projectRoots.SdkModel;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.util.IconLoader;
import de.achimonline.kickassembler.acbg.exception.JdkException;
import de.achimonline.kickassembler.acbg.exception.SdkException;
import de.achimonline.kickassembler.acbg.project.KickAssemblerProjectJdkTable;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettings;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.achimonline.kickassembler.acbg.notifications.KickAssemblerNotifications.notifyWarning;
import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

public class KickAssemblerSdk extends KickAssemblerSdkType {

    private static final Logger log = Logger.getInstance(KickAssemblerSdk.class);

    public static final String NAME = "Kick Assembler SDK";

    private static final String JAR_FILENAME = "KickAss.jar";
    private static final Pattern SDK_VERSION_PATTERN = Pattern.compile("(?<=Assembler\\s).*(?=\\sby)");

    private final Map<String, String> cachedVersions = Collections.synchronizedMap(new HashMap<>());

    public KickAssemblerSdk() {
        super(NAME);
    }

    @Override
    public Icon getIcon() {
        return IconLoader.findIcon("/icons/icon_16x16.png");
    }

    @NotNull
    @Override
    public Icon getIconForAddAction() {
        return IconLoader.findIcon("/icons/add_sdk_16x16.png");
    }

    @Nullable
    @Override
    public String suggestHomePath() {
        return null;
    }

    @Override
    public boolean isValidSdkHome(String path) {
        if (!(new File(path).isDirectory())) {
            return false;
        }

        boolean jarFileExists = false;

        try {
            jarFileExists = StringUtils.isNotEmpty(determineJarPathFromSdkHome(path));
        } catch (Exception e) {
            log.warn(e);
        }

        return jarFileExists;
    }

    @Override
    public String suggestSdkName(String currentSdkName, String sdkHome) {
        return getVersionString(sdkHome);
    }

    @Nullable
    @Override
    public AdditionalDataConfigurable createAdditionalDataConfigurable(@NotNull SdkModel sdkModel, @NotNull SdkModificator sdkModificator) {
        return null;
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return message("sdk.name");
    }

    @Override
    public void saveAdditionalData(@NotNull SdkAdditionalData additionalData, @NotNull Element additional) {
    }

    @Override
    public boolean setupSdkPaths(@NotNull Sdk sdk, @NotNull SdkModel sdkModel) {
        SdkModificator modificator = sdk.getSdkModificator();
        modificator.setVersionString(getVersionString(sdk));
        modificator.commitChanges();

        return true;
    }

    @Nullable
    @Override
    public String getVersionString(@NotNull Sdk sdk) {
        return getVersionString(sdk.getHomePath());
    }

    @Nullable
    @Override
    public String getVersionString(String sdkHome) {
        if (StringUtils.isNotEmpty(sdkHome)) {
            if (cachedVersions.containsKey(sdkHome)) {
                return cachedVersions.get(sdkHome);
            }

            try {
                String sdkVersion = determineSdkVersionFromSdkHome(sdkHome);

                if (sdkVersion != null) {
                    cachedVersions.put(sdkHome, sdkVersion);
                }

                return sdkVersion;
            } catch (JdkException jdke) {
                notifyWarning(message("notification.jre.exception", jdke.getMessage()));
            } catch (SdkException sdke) {
                notifyWarning(message("notification.sdk.exception", sdke.getMessage()));
            } catch (IOException|InterruptedException e) {
                notifyWarning(e.getMessage());
            }
        }

        return null;
    }

    private String determineSdkVersionFromSdkHome(String sdkHome) throws JdkException, SdkException, IOException, InterruptedException {
        String javaExecutable = KickAssemblerProjectJdkTable.getJavaExecutableFromJdkNameOrPath(KickAssemblerSettings.storedSettings(ApplicationManager.getApplication()).getJrePathOrName());

        String[] cmdArray = new String[] {
                javaExecutable, "-jar", determineJarPathFromSdkHome(sdkHome)
        };

        Process process = Runtime.getRuntime().exec(cmdArray);
        process.waitFor();

        String output = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

        Matcher matcher = SDK_VERSION_PATTERN.matcher(output);

        if (matcher.find()) {
            return matcher.group(0).trim();
        } else {
            throw new SdkException("can't determine version-string from sdk-jar");
        }
    }

    public static String determineJarPathFromSdkHome(@NotNull String sdkHomePath) throws SdkException {
        String jarPath = sdkHomePath + File.separator + JAR_FILENAME;

        if (new File(jarPath).exists()) {
            return jarPath;
        } else {
            jarPath = sdkHomePath + File.separator + JAR_FILENAME.toLowerCase();

            if (new File(jarPath).exists()) {
                return jarPath;
            }
        }

        throw new SdkException(String.format("unable to find jar-file in sdk-path [path: %s]", sdkHomePath));
    }

    public static String determineHomePathFromSdkNameOrPath(String sdkNameOrPath) throws SdkException {
        if (StringUtils.isNotEmpty(sdkNameOrPath)) {
            for (Sdk sdk : KickAssemblerProjectJdkTable.getAllJdks()) {
                if (sdk.getSdkType() instanceof KickAssemblerSdk) {
                    File sdkHomePathFile = new File(sdk.getName().equals(sdkNameOrPath) ? sdk.getHomePath() : sdkNameOrPath);

                    if (sdkHomePathFile.exists() && sdkHomePathFile.isDirectory()) {
                        return sdkHomePathFile.getPath();
                    }
                }
            }

            throw new SdkException("invalid sdk defined in project");
        } else {
            Sdk mostRecentSdk = KickAssemblerProjectJdkTable.findMostRecentSdkOfType(SdkType.findInstance(KickAssemblerSdk.class));

            if (mostRecentSdk != null) {
                return mostRecentSdk.getHomePath();
            }

            for (Sdk sdk : KickAssemblerProjectJdkTable.getAllJdks()) {
                if (sdk.getSdkType() instanceof KickAssemblerSdk) {
                    return sdk.getHomePath();
                }
            }

            throw new SdkException("no sdk configured in project");
        }
    }

}
