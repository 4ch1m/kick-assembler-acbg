package de.achimonline.kickassembler.acbg.settings

import com.intellij.execution.ui.DefaultJreSelector
import com.intellij.execution.ui.JrePathEditor
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Pair
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.builder.toMutableProperty
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message

class KickAssemblerSettingsConfigurable : BoundConfigurable(message("settings.name")) {
    private val jrePathEditor = JrePathEditor(object : DefaultJreSelector() {
        override fun getNameAndDescription(): Pair<String?, String> {
            return Pair.empty()
        }
    })

    private val settings
        get() = KickAssemblerSettingsState.instance.settings

    override fun createPanel(): DialogPanel {
        return panel {
            group(message("settings.jre")) {
                row {
                    cell(jrePathEditor)
                        .align(AlignX.FILL)
                        .bind(
                            JrePathEditor::getJreNameOrPath,
                            JrePathEditor::setJreNameOrPath,
                            settings::jreNameOrPath.toMutableProperty()
                        )
                }.rowComment(message("settings.jre.comment"))
            }
        }
    }
}
