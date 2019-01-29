package de.achimonline.kickassembler.acbg.sdk;

import com.intellij.openapi.projectRoots.SdkType;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public abstract class KickAssemblerSdkType extends SdkType {
    public static final String JAR_FILENAME = "KickAss.jar";

    public KickAssemblerSdkType(@NotNull String name) {
        super(name);
    }

    public String getJarPath(@NotNull String sdkHome) throws Exception {
        String jarPath = sdkHome + File.separator + JAR_FILENAME;

        if (new File(jarPath).exists()) {
            return jarPath;
        } else {
            jarPath = sdkHome + File.separator + JAR_FILENAME.toLowerCase();

            if (new File(jarPath).exists()) {
                return jarPath;
            }
        }

        throw new Exception(String.format("Unable to find Kick Assembler JAR in '%s'!", sdkHome));
    }

    public static KickAssemblerSdkType getInstance() {
        return SdkType.findInstance(KickAssemblerSdk.class);
    }
}
