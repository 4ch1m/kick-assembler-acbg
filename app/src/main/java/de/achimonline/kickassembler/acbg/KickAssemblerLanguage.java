package de.achimonline.kickassembler.acbg;

import com.intellij.lang.Language;

public class KickAssemblerLanguage extends Language {
    public static final KickAssemblerLanguage INSTANCE = new KickAssemblerLanguage();

    private KickAssemblerLanguage() {
        super("Kick Assembler");
    }
}
