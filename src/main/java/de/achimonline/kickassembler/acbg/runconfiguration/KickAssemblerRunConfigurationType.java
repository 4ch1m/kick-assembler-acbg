package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.project.PossiblyDumbAware;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

public class KickAssemblerRunConfigurationType extends ConfigurationTypeBase implements PossiblyDumbAware {
    public KickAssemblerRunConfigurationType() {
        super("KickAssemblerRunConfigurationType",
              message("runconfiguration.type.name"),
              message("runconfiguration.type.description"),
              IconLoader.getIcon("/icons/icon_16x16.png", KickAssemblerRunConfigurationType.class));

        addFactory(new KickAssemblerConfigurationFactory(this));
    }

    @NotNull
    public static KickAssemblerRunConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(KickAssemblerRunConfigurationType.class);
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }
}
