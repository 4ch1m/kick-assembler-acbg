package de.achimonline.kickassembler.acbg.settings;

import com.intellij.openapi.application.Application;
import lombok.Data;

import java.io.Serializable;

@Data
public class KickAssemblerSettings implements Serializable {
    private String jrePathOrName;

    public static KickAssemblerSettings storedSettings(Application application) {
        KickAssemblerSettingsComponent kickAssemblerSettingsComponent = application.getComponent(KickAssemblerSettingsComponent.class);

        if (kickAssemblerSettingsComponent == null) {
            return new KickAssemblerSettings();
        }

        return kickAssemblerSettingsComponent.getState();
    }
}
