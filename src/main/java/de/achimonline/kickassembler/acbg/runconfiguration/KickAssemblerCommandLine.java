package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.ide.macro.Macro;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.projectRoots.Sdk;
import de.achimonline.kickassembler.acbg.jdk.KickAssemblerJdkException;
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdkException;
import de.achimonline.kickassembler.acbg.jdk.KickAssemblerJdk;
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdk;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettings;
import de.achimonline.kickassembler.acbg.settings.KickAssemblerSettingsState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static de.achimonline.kickassembler.acbg.notifications.KickAssemblerNotifications.notifyError;
import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KickAssemblerCommandLine {
    private static final MacroManager MACRO_MANAGER = MacroManager.getInstance();

    public static @NotNull GeneralCommandLine generate(@NotNull KickAssemblerRunConfiguration<?> kickAssemblerRunConfiguration, DataContext dataContext) {
        KickAssemblerRunConfiguration.KickAssemblerProgramParameters kickAssemblerProgramParameters = kickAssemblerRunConfiguration.getKickAssemblerProgramParameters();

        try {
            KickAssemblerSettings kickAssemblerSettings = ApplicationManager.getApplication().getService(KickAssemblerSettingsState.class).getSettings();

            String workingDirectory = resolveWorkingDirectoryAndExpandMacros(kickAssemblerRunConfiguration, dataContext);
            String javaExecutablePath = KickAssemblerJdk.getJavaExecutableFromJdkNameOrPath(kickAssemblerSettings.getJreNameOrPath());
            Sdk kickAssemblerSdk = KickAssemblerSdk.getSdkFromHomePathOrName(kickAssemblerRunConfiguration.getKickAssemblerSdkPathOrName());

            String kickAssemblerSdkHomePath = kickAssemblerSdk.getHomePath();

            if (kickAssemblerSdkHomePath == null) {
                throw new KickAssemblerSdkException("home-path not available");
            }

            String kickAssemblerJarPath = KickAssemblerSdk.determineJarPathFromSdkHomePath(kickAssemblerSdkHomePath);

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
        } catch (KickAssemblerJdkException jdke) {
            notifyError(message("notification.jre.exception"), jdke.getMessage());
        } catch (KickAssemblerSdkException sdke) {
            notifyError(message("notification.sdk.exception"), sdke.getMessage());
        } catch (Exception e) {
            notifyError(e.getMessage());
        }

        throw new RuntimeException("unable to build commandline for run-config");
    }

    protected static String resolveWorkingDirectoryAndExpandMacros(KickAssemblerRunConfiguration<?> kickAssemblerRunConfiguration, DataContext dataContext) throws Exception {
        String workingDirectory = kickAssemblerRunConfiguration.getKickAssemblerProgramParameters().getWorkingDirectory();

        if (StringUtils.isEmpty(workingDirectory)) {
            workingDirectory = kickAssemblerRunConfiguration.getProject().getBasePath();
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
