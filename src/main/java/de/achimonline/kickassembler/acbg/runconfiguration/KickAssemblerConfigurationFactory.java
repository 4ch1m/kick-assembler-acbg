package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.RunManagerEx;
import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class KickAssemblerConfigurationFactory extends ConfigurationFactoryEx {
    protected KickAssemblerConfigurationFactory(@NotNull ConfigurationType configurationType) {
        super(configurationType);
    }

    @Override
    public void onNewConfigurationCreated(@NotNull RunConfiguration configuration) {
        RunManagerEx.getInstanceEx(configuration.getProject()).setBeforeRunTasks(configuration, Collections.emptyList(), false);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new KickAssemblerRunConfiguration(KickAssemblerProperties.message("runconfiguration.name"), new RunConfigurationModule(project), this);
    }
}
