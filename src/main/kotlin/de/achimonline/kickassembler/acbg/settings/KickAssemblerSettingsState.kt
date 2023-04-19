package de.achimonline.kickassembler.acbg.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "de.achimonline.kickassembler.acbg.settings.KickAssemblerSettingsState",
    storages = [Storage("kickassembler-settings.xml")]
)
class KickAssemblerSettingsState : PersistentStateComponent<KickAssemblerSettingsState?> {
    var settings = KickAssemblerSettings()

    override fun getState(): KickAssemblerSettingsState {
        return this
    }

    override fun loadState(state: KickAssemblerSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: KickAssemblerSettingsState
            get() = ApplicationManager.getApplication().getService(KickAssemblerSettingsState::class.java)
    }
}
