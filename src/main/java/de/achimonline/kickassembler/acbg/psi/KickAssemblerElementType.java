package de.achimonline.kickassembler.acbg.psi;

import com.intellij.psi.tree.IElementType;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;
import org.jetbrains.annotations.NotNull;

public class KickAssemblerElementType extends IElementType {
    public KickAssemblerElementType(@NotNull String debugName) {
        super(debugName, KickAssemblerLanguage.INSTANCE);
    }
}
