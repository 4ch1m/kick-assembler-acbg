package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.ide.macro.Macro;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import de.achimonline.kickassembler.acbg.exception.JdkException;
import de.achimonline.kickassembler.acbg.exception.SdkException;
import de.achimonline.kickassembler.acbg.project.KickAssemblerProjectJdkTable;
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdk;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static de.achimonline.kickassembler.acbg.notifications.KickAssemblerNotifications.notifyError;
import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KickAssemblerCommandLine {
    private static final MacroManager MACRO_MANAGER = MacroManager.getInstance();

    public static GeneralCommandLine generate(KickAssemblerRunConfiguration kickAssemblerRunConfiguration, DataContext dataContext) {
        KickAssemblerRunConfiguration.KickAssemblerProgramParameters kickAssemblerProgramParameters = kickAssemblerRunConfiguration.getKickAssemblerProgramParameters();

        try {
            String workingDirectory = resolveWorkingDirectoryAndExpandMacros(kickAssemblerRunConfiguration, dataContext);

            String javaExecutablePath = KickAssemblerProjectJdkTable.getJavaExecutableFromJdkNameOrPath(KickAssemblerSettings.storedSettings(ApplicationManager.getApplication()).getJrePathOrName());
            String kickAssemblerHomePath = KickAssemblerSdk.determineHomePathFromSdkNameOrPath(kickAssemblerRunConfiguration.getKickAssemblerSdkPathOrName());
            String kickAssemblerJarPath = KickAssemblerSdk.determineJarPathFromSdkHome(kickAssemblerHomePath);

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
        } catch (JdkException jdke) {
            notifyError(message("notification.jre.exception"), jdke.getMessage());
        } catch (SdkException sdke) {
            notifyError(message("notification.sdk.exception"), sdke.getMessage());
        } catch (Exception e) {
            notifyError(e.getMessage());
        }

        throw new RuntimeException("unable to build command-line for run-config");
    }

    protected static String resolveWorkingDirectoryAndExpandMacros(KickAssemblerRunConfiguration kickAssemblerRunConfiguration, DataContext dataContext) throws Exception {
        String workingDirectory = kickAssemblerRunConfiguration.getKickAssemblerProgramParameters().getWorkingDirectory();

        if (StringUtils.isEmpty(workingDirectory)) {
            workingDirectory = kickAssemblerRunConfiguration.getConfigurationModule().getProject().getBasePath();
        } else {
            try {
                workingDirectory = MACRO_MANAGER.expandMacrosInString(workingDirectory, true, dataContext);
            } catch (Macro.ExecutionCancelledException e) {
                throw new Exception(String.format("exception while processing working directory in run configuration [working-directory: %s]", workingDirectory));
            }
        }

        return workingDirectory;
    }

    protected static List<String> expandMacrosAndGenerateParametersList(String programParameters, DataContext dataContext) throws Exception {
        try {
            String expandedProgramParameters = MACRO_MANAGER.expandMacrosInString(programParameters, true, dataContext);

            ParametersList parametersList = new ParametersList();
            parametersList.addParametersString(expandedProgramParameters);

            return parametersList.getParameters();
        } catch (Macro.ExecutionCancelledException e) {
            throw new Exception(String.format("exception while processing program parameters in run configuration [program-parameters: %s].", programParameters));
        }
    }
}
