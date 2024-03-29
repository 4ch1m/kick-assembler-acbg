package de.achimonline.kickassembler.acbg.jdk;

import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class KickAssemblerJdk {
    public static @NotNull String getJavaExecutableFromJdkNameOrPath(String jdkNameOrPath) throws KickAssemblerJdkException {
        if (StringUtils.isNotEmpty(jdkNameOrPath)) {
            for (Sdk jdk : ProjectJdkTable.getInstance().getSdksOfType(JavaSdk.getInstance())) {
                String homePath = jdk.getName().equals(jdkNameOrPath) ? jdk.getHomePath() : jdkNameOrPath;
                String binPath = homePath + File.separator + "bin";
                File javaExecutable = new File(
                        binPath +
                        File.separator +
                        (SystemInfo.isWindows ? "java.exe" : "java")
                );

                if (javaExecutable.exists() &&
                    javaExecutable.isFile() &&
                    javaExecutable.canExecute()) {
                    return javaExecutable.getPath();
                }
            }
        }

        throw new KickAssemblerJdkException(String.format("missing or invalid JDK in given path [path: %s]", jdkNameOrPath));
    }
}
