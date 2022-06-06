package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class KickAssemblerConfigurationFactory extends ConfigurationFactory {
    protected KickAssemblerConfigurationFactory(@NotNull ConfigurationType configurationType) {
        super(configurationType);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new KickAssemblerRunConfiguration(project, this, KickAssemblerProperties.message("runconfiguration.name"));
    }

    @Override
    public @NotNull
    @NonNls String getId() {
        return "KickAssemblerConfiguration";
    }
}
