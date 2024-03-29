package de.achimonline.kickassembler.acbg.settings

import com.intellij.execution.ui.JrePathEditor

fun JrePathEditor.setJreNameOrPath(jreNameOrPath: String?) {
    jreNameOrPath?.let {
        this.setPathOrName(jreNameOrPath, true)
    }
}

fun JrePathEditor.getJreNameOrPath() : String? {
    if (! this.isAlternativeJreSelected) {
        return null
    }

    return this.jrePathOrName
}
