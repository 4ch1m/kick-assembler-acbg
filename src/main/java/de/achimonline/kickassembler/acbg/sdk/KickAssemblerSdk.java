package de.achimonline.kickassembler.acbg.sdk;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.projectRoots.AdditionalDataConfigurable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.openapi.projectRoots.SdkModel;
import com.intellij.openapi.projectRoots.SdkModificator;
import com.intellij.openapi.projectRoots.impl.JavaAwareProjectJdkTableImpl;
import com.intellij.openapi.util.IconLoader;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;
import de.achimonline.kickassembler.acbg.runconfiguration.KickAssemblerCommandLine;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KickAssemblerSdk extends KickAssemblerSdkType {

    private static final Logger log = Logger.getInstance(KickAssemblerSdk.class);

    public static final String NAME = "Kick Assembler SDK";

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
            jarFileExists = StringUtils.isNotEmpty(getJarPath(path));
        } catch (Exception e) {
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
        return KickAssemblerProperties.message("sdk.name");
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

            String sdkVersion = determineSdkVersionFromJar(sdkHome);

            if (sdkVersion != null) {
                cachedVersions.put(sdkHome, sdkVersion);
            }

            return sdkVersion;
        }

        return null;
    }

    private String determineSdkVersionFromJar(String sdkHome) {
        Sdk internalJdk = JavaAwareProjectJdkTableImpl.getInstanceEx().getInternalJdk();

        String internalJavaBinaryPath = new StringBuilder()
                .append(internalJdk.getHomePath())
                .append(File.separator)
                .append("bin")
                .append(File.separator)
                .append("java")
                .toString();

        try {
            String[] cmdArray = new String[] {
                internalJavaBinaryPath, "-jar", getJarPath(sdkHome)
            };

            Process process = Runtime.getRuntime().exec(cmdArray);
            process.waitFor();

            String output = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);

            Matcher matcher = SDK_VERSION_PATTERN.matcher(output);

            if (matcher.find()) {
                return matcher.group(0).trim();
            }
        } catch (Exception e) {
            log.warn("got exception determining sdk version from jar", e);
        }

        return null;
    }
}
