package de.achimonline.kickassembler.acbg.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "KickAssemblerSettings", storages = {@Storage("kickassembler-settings.xml")})
public class KickAssemblerSettingsService implements PersistentStateComponent<KickAssemblerSettings> {

    KickAssemblerSettings kickAssemblerSettings = new KickAssemblerSettings();

    @Nullable
    @Override
    public KickAssemblerSettings getState() {
        return kickAssemblerSettings;
    }

    @Override
    public void loadState(@NotNull KickAssemblerSettings kickAssemblerSettings) {
        this.kickAssemblerSettings = kickAssemblerSettings;
    }

}
