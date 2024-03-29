package de.achimonline.kickassembler.acbg.project;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.util.IconLoader;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import de.achimonline.kickassembler.acbg.module.KickAssemblerModuleBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class KickAssemblerProjectTemplatesFactory extends ProjectTemplatesFactory {
    @NotNull
    @Override
    public String @NotNull [] getGroups() {
        return new String[]{"Kick Assembler"};
    }

    @Override
    public Icon getGroupIcon(String group) {
        return IconLoader.getIcon("/icons/icon_16x16.png", KickAssemblerProjectTemplate.class);
    }

    @NotNull
    @Override
    public ProjectTemplate @NotNull [] createTemplates(@Nullable String group, @NotNull WizardContext context) {
        return new ProjectTemplate[]{new KickAssemblerProjectTemplate(new KickAssemblerModuleBuilder())};
    }
}
