package de.achimonline.kickassembler.acbg.lexer;

import com.intellij.lexer.FlexAdapter;

public class KickAssemblerLexerAdapter extends FlexAdapter {
    public KickAssemblerLexerAdapter() {
        super(new KickAssemblerLexer(null));
    }
}
