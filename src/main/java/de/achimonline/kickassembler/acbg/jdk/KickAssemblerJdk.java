package de.achimonline.kickassembler.acbg.jdk;

import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.SystemInfo;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class KickAssemblerJdk {
    public static @NotNull String getJavaExecutableFromJdkNameOrPath(String jdkNameOrPath) throws KickAssemblerJdkException {
        for (Sdk javaSdk : ProjectJdkTable.getInstance().getSdksOfType(JavaSdk.getInstance())) {
            File javaExecutable = new File(String.join(File.separator, new String[]{
                    StringUtils.isEmpty(jdkNameOrPath) || javaSdk.getName().equals(jdkNameOrPath) ? javaSdk.getHomePath() : jdkNameOrPath,
                    "bin",
                    (SystemInfo.isWindows ? "java.exe" : "java")
            }));

            if (javaExecutable.exists() &&
                javaExecutable.isFile() &&
                javaExecutable.canExecute()) {
                return javaExecutable.getPath();
            }
        }

        throw new KickAssemblerJdkException(String.format("unable to determine JDK executable from given path or name [%s]", jdkNameOrPath));
    }
}
