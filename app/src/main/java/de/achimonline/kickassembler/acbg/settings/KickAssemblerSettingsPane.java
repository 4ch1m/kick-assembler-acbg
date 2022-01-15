package de.achimonline.kickassembler.acbg.settings;

import com.intellij.execution.ui.DefaultJreSelector;
import com.intellij.execution.ui.JrePathEditor;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class KickAssemblerSettingsPane implements Disposable {
    private JPanel panel;
    private JrePathEditor jrePathEditor;

    private boolean modified = false;

    public KickAssemblerSettingsPane() {
        jrePathEditor.setDefaultJreSelector(new DefaultJreSelector() {
            @NotNull
            @Override
            public Pair<String, String> getNameAndDescription() {
                return Pair.empty();
            }
        });
    }

    public void setData(KickAssemblerSettings kickAssemblerSettings) {
        jrePathEditor.setPathOrName(kickAssemblerSettings.getJrePathOrName(), true);
        jrePathEditor.getComponent().addActionListener(e -> modified = true);
    }

    public void storeSettings(KickAssemblerSettings kickAssemblerSettings) {
        kickAssemblerSettings.setJrePathOrName(jrePathEditor.getJrePathOrName());
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void dispose() {
    }

    public boolean isModified() {
        return modified;
    }
}
