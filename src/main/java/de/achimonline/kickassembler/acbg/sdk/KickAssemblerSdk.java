package de.achimonline.kickassembler.acbg.sdk;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.AdditionalDataConfigurable;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.openapi.projectRoots.SdkModel;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.SdkType;
import com.intellij.openapi.util.IconLoader;
import de.achimonline.kickassembler.acbg.jdk.KickAssemblerJdkException;
import de.achimonline.kickassembler.acbg.jdk.KickAssemblerJdk;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettings;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettingsState;
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
        return IconLoader.getIcon("/icons/icon_16x16.png", KickAssemblerSdk.class);
    }

    @Nullable
    @Override
    public String suggestHomePath() {
        return null;
    }

    @Override
    public boolean isValidSdkHome(@NotNull String path) {
        if (!(new File(path).isDirectory())) {
            return false;
        }

        boolean jarFileExists = false;

        try {
            jarFileExists = StringUtils.isNotEmpty(determineJarPathFromSdkHomePath(path));
        } catch (Exception e) {
            log.warn(e);
        }

        return jarFileExists;
    }

    @Override
    public @NotNull String suggestSdkName(String currentSdkName, @NotNull String sdkHome) {
        String versionString = getVersionString(sdkHome);
        return versionString != null ? versionString : currentSdkName + "*";
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
        SdkModificator sdkModificator = sdk.getSdkModificator();
        sdkModificator.setVersionString(getVersionString(sdk));
        sdkModificator.commitChanges();

        return true;
    }

    @Nullable
    @Override
    public String getVersionString(@NotNull Sdk sdk) {
        String homePath = sdk.getHomePath();
        return getVersionString(homePath != null ? homePath : "");
    }

    @Nullable
    @Override
    public String getVersionString(@NotNull String sdkHome) {
        if (StringUtils.isNotEmpty(sdkHome)) {
            if (cachedVersions.containsKey(sdkHome)) {
                return cachedVersions.get(sdkHome);
            }

            try {
                String sdkVersion = determineSdkVersionFromSdkHome(sdkHome);

                cachedVersions.put(sdkHome, sdkVersion);

                return sdkVersion;
            } catch (KickAssemblerJdkException jdke) {
                notifyWarning(message("notification.jre.exception", jdke.getMessage()));
            } catch (KickAssemblerSdkException sdke) {
                notifyWarning(message("notification.sdk.exception", sdke.getMessage()));
            } catch (IOException | InterruptedException e) {
                notifyWarning(e.getMessage());
            }
        }

        return null;
    }

    private String determineSdkVersionFromSdkHome(String sdkHome) throws KickAssemblerJdkException, KickAssemblerSdkException, IOException, InterruptedException {
        KickAssemblerSettings settings = ApplicationManager.getApplication().getService(KickAssemblerSettingsState.class).getSettings();
        String javaExecutable = KickAssemblerJdk.getJavaExecutableFromJdkNameOrPath(settings.getJreNameOrPath());

        String[] cmdArray = new String[]{
                javaExecutable, "-jar", determineJarPathFromSdkHomePath(sdkHome)
        };

        Process process = Runtime.getRuntime().exec(cmdArray);
        process.waitFor();

        String output = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

        Matcher matcher = SDK_VERSION_PATTERN.matcher(output);

        if (matcher.find()) {
            return matcher.group(0).trim();
        } else {
            throw new KickAssemblerSdkException("can't determine version string from SDK jar");
        }
    }

    public static String determineJarPathFromSdkHomePath(@NotNull String sdkHomePath) throws KickAssemblerSdkException {
        String jarPath = sdkHomePath + File.separator + JAR_FILENAME;

        if (new File(jarPath).exists()) {
            return jarPath;
        } else {
            jarPath = sdkHomePath + File.separator + JAR_FILENAME.toLowerCase();

            if (new File(jarPath).exists()) {
                return jarPath;
            }
        }

        throw new KickAssemblerSdkException(String.format("unable to find jar file in SDK path [%s]", sdkHomePath));
    }

    public static java.util.@NotNull List<Sdk> getAllSdks() {
        return ProjectJdkTable
                .getInstance()
                .getSdksOfType(SdkType.findInstance(KickAssemblerSdk.class));
    }

    public static Sdk getSdkFromHomePathOrName(String homePathOrName) throws KickAssemblerSdkException {
        for (Sdk sdk : getAllSdks()) {
            if (sdk.getHomePath() != null && (sdk.getHomePath().equals(homePathOrName) || sdk.getName().equals(homePathOrName))) {
                return sdk;
            }
        }

        throw new KickAssemblerSdkException(String.format("SDK with path or name '[%s]' not registered", homePathOrName));
    }
}
