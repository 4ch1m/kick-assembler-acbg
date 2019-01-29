package de.achimonline.kickassembler.acbg.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import de.achimonline.kickassembler.acbg.lexer.KickAssemblerLexerAdapter;
import de.achimonline.kickassembler.acbg.psi.KickAssemblerTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class KickAssemblerSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey VALUE = createTextAttributesKey("KickAssembler_VALUE", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey STRING = createTextAttributesKey("KickAssembler_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("KickAssembler_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    public static final TextAttributesKey PREPROCESSOR = createTextAttributesKey("KickAssembler_PREPROCESSOR", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("KickAssembler_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("KickAssembler_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey INSTRUCTION = createTextAttributesKey("KickAssembler_INSTRUCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey LABEL = createTextAttributesKey("KickAssembler_LABEL", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey REGISTER_GENERAL = createTextAttributesKey("KickAssembler_REGISTER_GENERAL", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey REGISTER_SPECIAL = createTextAttributesKey("KickAssembler_REGISTER_SPECIAL", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey REGISTER_SEGMENT = createTextAttributesKey("KickAssembler_REGISTER_SEGMENT", DefaultLanguageHighlighterColors.KEYWORD);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] INSTRUCTION_KEYS = new TextAttributesKey[]{INSTRUCTION};
    private static final TextAttributesKey[] REGISTER_GENERAL_KEYS = new TextAttributesKey[]{REGISTER_GENERAL};
    private static final TextAttributesKey[] REGISTER_SPECIAL_KEYS = new TextAttributesKey[]{REGISTER_SPECIAL};
    private static final TextAttributesKey[] REGISTER_SEGMENT_KEYS = new TextAttributesKey[]{REGISTER_SEGMENT};
    private static final TextAttributesKey[] LABEL_KEYS = new TextAttributesKey[]{LABEL};
    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] PREPROCESSOR_KEYS = new TextAttributesKey[]{PREPROCESSOR};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new KickAssemblerLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(KickAssemblerTypes.INSTRUCTION)) {
            return INSTRUCTION_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.REGISTER_GENERAL)) {
            return REGISTER_GENERAL_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.REGISTER_SPECIAL)) {
            return REGISTER_SPECIAL_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.REGISTER_SEGMENT)) {
            return REGISTER_SEGMENT_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.LABEL)) {
            return LABEL_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.COMMENT_BLOCK)) {
            return BLOCK_COMMENT_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.COMMENT_LINE)) {
            return LINE_COMMENT_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.PREPROCESSOR)) {
            return PREPROCESSOR_KEYS;
        } else if (tokenType.equals(KickAssemblerTypes.VALUE)) {
            return VALUE_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
