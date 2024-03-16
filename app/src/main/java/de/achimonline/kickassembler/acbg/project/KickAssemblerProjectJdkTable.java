package de.achimonline.kickassembler.acbg.project;

import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.util.SystemInfo;
import de.achimonline.kickassembler.acbg.exception.JdkException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class KickAssemblerProjectJdkTable {

    private static final ProjectJdkTable PROJECT_JDK_TABLE = ProjectJdkTable.getInstance();

    public static String getJavaExecutableFromJdkNameOrPath(final String jdkNameOrPath) throws JdkException {
        final String nameOrPath;

        if (StringUtils.isEmpty(jdkNameOrPath)) {
            nameOrPath =
                    PROJECT_JDK_TABLE
                            .getSdksOfType(PROJECT_JDK_TABLE.getDefaultSdkType())
                            .stream()
                            .findFirst()
                            .orElseThrow(() -> new JdkException("Didn't find default SDK"))
                            .getHomePath();
        }
        else {
            nameOrPath = jdkNameOrPath;
        }

        for (Sdk jdk : PROJECT_JDK_TABLE.getAllJdks()) {
            if (jdk.getSdkType() instanceof JavaSdk) {
                String homePath = jdk.getName().equals(nameOrPath) ? jdk.getHomePath() : nameOrPath;
                String binPath = homePath + File.separator + "bin";
                File javaExecutable = new File(binPath + File.separator + (SystemInfo.isWindows ? "java.exe" : "java"));

                if (javaExecutable.exists() &&
                        javaExecutable.isFile() &&
                        javaExecutable.canExecute()) {
                    return javaExecutable.getPath();
                }
            }
        }

        throw new JdkException(String.format("missing or invalid jdk in given path [path: %s]", jdkNameOrPath));
    }

    public static Sdk[] getAllJdks() {
        return PROJECT_JDK_TABLE.getAllJdks();
    }

    public static Sdk findMostRecentSdkOfType(SdkTypeId sdkTypeId) {
        return PROJECT_JDK_TABLE.findMostRecentSdkOfType(sdkTypeId);
    }

}
