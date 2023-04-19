package de.achimonline.kickassembler.acbg.psi;

import com.intellij.psi.tree.IElementType;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;
import org.jetbrains.annotations.NotNull;

public class KickAssemblerTokenType extends IElementType {
    public KickAssemblerTokenType(@NotNull String debugName) {
        super(debugName, KickAssemblerLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return KickAssemblerTokenType.class.getSimpleName() + "." + super.toString();
    }
}
