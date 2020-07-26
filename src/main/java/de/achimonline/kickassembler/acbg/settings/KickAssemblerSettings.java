package de.achimonline.kickassembler.acbg.settings;

import com.intellij.openapi.application.Application;
import lombok.Data;

import java.io.Serializable;

@Data
public class KickAssemblerSettings implements Serializable {
    private String jrePathOrName;

    public static KickAssemblerSettings storedSettings(Application application) {
        KickAssemblerSettingsService kickAssemblerSettingsService = application.getService(KickAssemblerSettingsService.class);

        if (kickAssemblerSettingsService == null) {
            return new KickAssemblerSettings();
        }

        return kickAssemblerSettingsService.getState();
    }
}
