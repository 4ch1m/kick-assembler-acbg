package de.achimonline.kickassembler.acbg.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import de.achimonline.kickassembler.acbg.filetype.KickAssemblerFileType;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;
import org.jetbrains.annotations.NotNull;

import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

public class KickAssemblerFile extends PsiFileBase {
    public KickAssemblerFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, KickAssemblerLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return KickAssemblerFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return message("psi.file.string");
    }
}
