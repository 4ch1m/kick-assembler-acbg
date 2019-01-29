package de.achimonline.kickassembler.acbg.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiDirectory;

import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

public class KickAssemblerFileAction extends CreateFileFromTemplateAction {
    public KickAssemblerFileAction() {
        super(message("filetype.name"), message("filetype.description"), IconLoader.findIcon("/icons/icon_16x16.png"));
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(message("filetype.new")).addKind(message("filetype.name"), IconLoader.findIcon("/icons/icon_16x16.png"), "Kick Assembler file.asm");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return message("filetype.create");
    }
}
