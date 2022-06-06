package de.achimonline.kickassembler.acbg.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiDirectory;
import de.achimonline.kickassembler.acbg.psi.KickAssemblerFile;
import org.jetbrains.annotations.NotNull;

import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

@SuppressWarnings("DialogTitleCapitalization")
public class KickAssemblerFileAction extends CreateFileFromTemplateAction {
    public KickAssemblerFileAction() {
        super(message("filetype.name"), message("filetype.description"), IconLoader.getIcon("/icons/icon_16x16.png", KickAssemblerFile.class));
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(message("filetype.new")).addKind(message("filetype.name"), IconLoader.getIcon("/icons/icon_16x16.png", KickAssemblerFile.class), "Kick Assembler file.asm");
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return message("filetype.create");
    }
}
