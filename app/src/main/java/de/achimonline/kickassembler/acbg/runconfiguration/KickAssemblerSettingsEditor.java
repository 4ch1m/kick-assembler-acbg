package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.execution.ui.DefaultJreSelector;
import com.intellij.execution.ui.JrePathEditor;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

public class KickAssemblerSettingsEditor extends SettingsEditor<KickAssemblerRunConfiguration> {
    private JPanel panel;

    private LabeledComponent<TextFieldWithBrowseButton> kickAssemblerFile;
    private JrePathEditor jrePathEditor;
    private CommonProgramParametersPanel commonProgramParametersPanel;

    public KickAssemblerSettingsEditor(Project project) {
        FileChooserDescriptor kickAssemblerFileChooserDescriptor = new FileChooserDescriptor(true, false, false, true, false, false);
        kickAssemblerFileChooserDescriptor.setTitle(message("runconfiguration.main.file.browse.title"));
        kickAssemblerFileChooserDescriptor.setDescription(message("runconfiguration.main.file.browse.description"));

        kickAssemblerFile.getComponent().addBrowseFolderListener(kickAssemblerFileChooserDescriptor.getTitle(), kickAssemblerFileChooserDescriptor.getDescription(), project, kickAssemblerFileChooserDescriptor);
        jrePathEditor.setDefaultJreSelector(DefaultJreSelector.projectSdk(project));
    }

    @Override
    protected void resetEditorFrom(@NotNull KickAssemblerRunConfiguration kickAssemblerRunConfiguration) {
        kickAssemblerFile.getComponent().setText(kickAssemblerRunConfiguration.getKickAssemblerFile());
        jrePathEditor.setPathOrName(kickAssemblerRunConfiguration.getKickAssemblerSdkPathOrName(), true);
        commonProgramParametersPanel.reset(kickAssemblerRunConfiguration.getKickAssemblerProgramParameters());
    }

    @Override
    protected void applyEditorTo(@NotNull KickAssemblerRunConfiguration kickAssemblerRunConfiguration) throws ConfigurationException {
        kickAssemblerRunConfiguration.setKickAssemblerFile(kickAssemblerFile.getComponent().getText());
        kickAssemblerRunConfiguration.setKickAssemblerSdkPathOrName(jrePathEditor.getJrePathOrName());
        commonProgramParametersPanel.applyTo(kickAssemblerRunConfiguration.getKickAssemblerProgramParameters());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return panel;
    }
}
