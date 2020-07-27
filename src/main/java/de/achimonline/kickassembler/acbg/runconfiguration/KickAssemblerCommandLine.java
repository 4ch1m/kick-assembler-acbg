package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.ide.macro.Macro;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.SdkType;
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdk;
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdkType;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;

import static de.achimonline.kickassembler.acbg.notifications.KickAssemblerNotifications.notifyError;
import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KickAssemblerCommandLine {
    private static final ProjectJdkTable PROJECT_JDK_TABLE = ProjectJdkTable.getInstance();
    private static final MacroManager MACRO_MANAGER = MacroManager.getInstance();

    public static GeneralCommandLine generate(KickAssemblerRunConfiguration kickAssemblerRunConfiguration, DataContext dataContext) {
        KickAssemblerRunConfiguration.KickAssemblerProgramParameters kickAssemblerProgramParameters = kickAssemblerRunConfiguration.getKickAssemblerProgramParameters();

        String workingDirectory = resolveWorkingDirectoryAndExpandMacros(kickAssemblerRunConfiguration, dataContext);

        String javaExecutablePath = determineJavaExecutable(KickAssemblerSettings.storedSettings(ApplicationManager.getApplication()).getJrePathOrName());
        String kickAssemblerHomePath = determineKickAssemblerHomePath(kickAssemblerRunConfiguration.getKickAssemblerSdkPathOrName());
        String kickAssemblerJarPath = determineKickAssemblerJarPath(kickAssemblerHomePath);

        GeneralCommandLine generalCommandLine = new GeneralCommandLine()
                .withEnvironment(kickAssemblerProgramParameters.getEnvs())
                .withWorkDirectory(workingDirectory)
                .withExePath(javaExecutablePath)
                .withParameters("-jar", kickAssemblerJarPath);

        if (StringUtils.isNotEmpty(kickAssemblerProgramParameters.getProgramParameters())) {
            generalCommandLine.addParameters(expandMacrosAndGenerateParametersList(kickAssemblerProgramParameters.getProgramParameters(), dataContext));
        }

        generalCommandLine.addParameter(kickAssemblerRunConfiguration.getKickAssemblerFile());

        return generalCommandLine;
    }

    protected static String resolveWorkingDirectoryAndExpandMacros(KickAssemblerRunConfiguration kickAssemblerRunConfiguration, DataContext dataContext) {
        String workingDirectory = kickAssemblerRunConfiguration.getKickAssemblerProgramParameters().getWorkingDirectory();

        if (StringUtils.isEmpty(workingDirectory)) {
            workingDirectory = kickAssemblerRunConfiguration.getConfigurationModule().getProject().getBasePath();
        } else {
            try {
                workingDirectory = MACRO_MANAGER.expandMacrosInString(workingDirectory, true, dataContext);
            } catch (Macro.ExecutionCancelledException e) {
                String msg = message("runconfiguration.exception.working.directory", workingDirectory);
                notifyError(msg);
                throw new RuntimeException(msg);
            }
        }

        return workingDirectory;
    }

    protected static String determineJavaExecutable(String jreNameOrPath) {
        if (StringUtils.isNotEmpty(jreNameOrPath)) {
            for (Sdk jdk : PROJECT_JDK_TABLE.getAllJdks()) {
                if (jdk.getSdkType() instanceof JavaSdk) {
                    String homePath = jdk.getName().equals(jreNameOrPath) ? jdk.getHomePath() : jreNameOrPath;
                    File javaExecutable = new File(homePath + File.separator + "bin" + File.separator + "java");

                    if (javaExecutable.exists() && javaExecutable.isFile() && javaExecutable.canExecute()) {
                        return javaExecutable.getPath();
                    }
                }
            }
        }

        String msg = message("runconfiguration.exception.missing.or.invalid.jre", jreNameOrPath);
        notifyError(msg);
        throw new RuntimeException(msg);
    }

    protected static String determineKickAssemblerHomePath(String sdkNameOrPath) {
        if (StringUtils.isNotEmpty(sdkNameOrPath)) {
            for (Sdk sdk : PROJECT_JDK_TABLE.getAllJdks()) {
                if (sdk.getSdkType() instanceof KickAssemblerSdk) {
                    File sdkHomePathFile = new File(sdk.getName().equals(sdkNameOrPath) ? sdk.getHomePath() : sdkNameOrPath);

                    if (sdkHomePathFile.exists() && sdkHomePathFile.isDirectory()) {
                        return sdkHomePathFile.getPath();
                    }
                }
            }
        } else {
            Sdk mostRecentSdk = PROJECT_JDK_TABLE.findMostRecentSdkOfType(SdkType.findInstance(KickAssemblerSdk.class));

            if (mostRecentSdk != null) {
                return mostRecentSdk.getHomePath();
            }

            for (Sdk sdk : PROJECT_JDK_TABLE.getAllJdks()) {
                if (sdk.getSdkType() instanceof KickAssemblerSdk) {
                    return sdk.getHomePath();
                }
            }

            String msg = message("project.exception.sdk.not.set");
            notifyError(msg);
            throw new RuntimeException(message(msg));
        }

        throw new RuntimeException(message("runconfiguration.exception.invalid.sdk"));
    }

    protected static String determineKickAssemblerJarPath(String kickAssemblerHomePath) {
        try {
            return KickAssemblerSdkType.getInstance().getJarPath(kickAssemblerHomePath);
        } catch (Exception e) {
            String msg = message("runconfiguration.exception.kickass.jar", kickAssemblerHomePath);
            notifyError(message(msg));
            throw new RuntimeException(msg);
        }
    }

    protected static List<String> expandMacrosAndGenerateParametersList(String programParameters, DataContext dataContext) {
        try {
            String expandedProgramParameters = MACRO_MANAGER.expandMacrosInString(programParameters, true, dataContext);

            ParametersList parametersList = new ParametersList();
            parametersList.addParametersString(expandedProgramParameters);

            return parametersList.getParameters();
        } catch (Macro.ExecutionCancelledException e) {
            String msg = message("runconfiguration.exception.program.parameters", programParameters);
            notifyError(msg);
            throw new RuntimeException(msg);
        }
    }
}
