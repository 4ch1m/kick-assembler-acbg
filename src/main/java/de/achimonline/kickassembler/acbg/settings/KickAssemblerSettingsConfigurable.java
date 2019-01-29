package de.achimonline.kickassembler.acbg.settings;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class KickAssemblerSettingsConfigurable implements Configurable {
    private Application application;
    private KickAssemblerSettingsPane kickAssemblerSettingsPane;

    public KickAssemblerSettingsConfigurable(Application application) {
        this.application = application;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return KickAssemblerProperties.message("settings.name");
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (kickAssemblerSettingsPane == null) {
            kickAssemblerSettingsPane = new KickAssemblerSettingsPane();
        }

        return kickAssemblerSettingsPane.getPanel();
    }

    @Override
    public boolean isModified() {
        return kickAssemblerSettingsPane != null && kickAssemblerSettingsPane.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        kickAssemblerSettingsPane.storeSettings(KickAssemblerSettings.storedSettings(application));
    }

    @Override
    public void reset() {
        kickAssemblerSettingsPane.setData(KickAssemblerSettings.storedSettings(application));
    }

    @Override
    public void disposeUIResources() {
    }
}
