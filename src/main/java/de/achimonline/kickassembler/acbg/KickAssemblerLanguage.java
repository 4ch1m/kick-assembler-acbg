package de.achimonline.kickassembler.acbg;

import com.intellij.lang.Language;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;

public class KickAssemblerLanguage extends Language {
    public static final KickAssemblerLanguage INSTANCE = new KickAssemblerLanguage();

    private KickAssemblerLanguage() {
        super(KickAssemblerProperties.message("language"));
    }
}
