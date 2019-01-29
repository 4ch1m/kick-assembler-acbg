package de.achimonline.kickassembler.acbg.settings;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "KickAssemblerSettings", storages = {@Storage("kickassembler-settings.xml")})
public class KickAssemblerSettingsComponent implements PersistentStateComponent<KickAssemblerSettings>, ApplicationComponent {
    private KickAssemblerSettings kickAssemblerSettings = new KickAssemblerSettings();

    @Nullable
    @Override
    public KickAssemblerSettings getState() {
        return kickAssemblerSettings;
    }

    @Override
    public void loadState(@NotNull KickAssemblerSettings kickAssemblerSettings) {
        this.kickAssemblerSettings = kickAssemblerSettings;
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Kick Assembler Language Settings";
    }
}
