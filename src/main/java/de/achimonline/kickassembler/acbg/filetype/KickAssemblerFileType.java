package de.achimonline.kickassembler.acbg.filetype;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message;

public class KickAssemblerFileType extends LanguageFileType {
    public static final KickAssemblerFileType INSTANCE = new KickAssemblerFileType();

    private KickAssemblerFileType() {
        super(KickAssemblerLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return message("filetype.name");
    }

    @NotNull
    @Override
    public String getDescription() {
        return message("filetype.description");
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "asm";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.findIcon("/icons/icon_16x16.png");
    }
}
