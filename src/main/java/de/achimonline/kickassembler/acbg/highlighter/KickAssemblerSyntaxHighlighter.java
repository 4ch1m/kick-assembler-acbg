package de.achimonline.kickassembler.acbg.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import de.achimonline.kickassembler.acbg.lexer.KickAssemblerLexerAdapter;
import de.achimonline.kickassembler.acbg.psi.KickAssemblerTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.BLOCK_COMMENT;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.BRACES;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.BRACKETS;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.INSTANCE_FIELD;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.INSTANCE_METHOD;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.KEYWORD;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.LINE_COMMENT;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.METADATA;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.NUMBER;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.PARENTHESES;
import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.STRING;
import static com.intellij.openapi.editor.HighlighterColors.NO_HIGHLIGHTING;
import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class KickAssemblerSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final String EXTERNAL_NAME_PREFIX = "KICK_ASSEMBLER_";

    public static final TextAttributesKey KICK_ASSEMBLER_METADATA = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "METADATA", METADATA);
    public static final TextAttributesKey KICK_ASSEMBLER_LINE_COMMENT = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "LINE_COMMENT", LINE_COMMENT);
    public static final TextAttributesKey KICK_ASSEMBLER_BLOCK_COMMENT = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "BLOCK_COMMENT", BLOCK_COMMENT);
    public static final TextAttributesKey KICK_ASSEMBLER_LABEL = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "LABEL", /* LABEL */ INSTANCE_METHOD);
    public static final TextAttributesKey KICK_ASSEMBLER_NUMBER = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "NUMBER", NUMBER);
    public static final TextAttributesKey KICK_ASSEMBLER_MNEMONIC = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "MNEMONIC", KEYWORD);
    public static final TextAttributesKey KICK_ASSEMBLER_STRING = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "STRING", STRING);
    public static final TextAttributesKey KICK_ASSEMBLER_PARENTHESES = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "PARENTHESES", PARENTHESES);
    public static final TextAttributesKey KICK_ASSEMBLER_BRACES = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "BRACES", BRACES);
    public static final TextAttributesKey KICK_ASSEMBLER_BRACKETS = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "BRACKETS", BRACKETS);
    public static final TextAttributesKey KICK_ASSEMBLER_OPERATION_SIGN = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "OPERATION_SIGN", /* OPERATION_SIGN */ INSTANCE_FIELD);
    public static final TextAttributesKey KICK_ASSEMBLER_DUMMY = createTextAttributesKey(EXTERNAL_NAME_PREFIX + "DUMMY", NO_HIGHLIGHTING);

    private static final TokenSet OPERATOR_TOKENS = TokenSet.create(
            KickAssemblerTypes.HASH,
            KickAssemblerTypes.ASSIGN,
            KickAssemblerTypes.COMMA,
            KickAssemblerTypes.SEMICOLON,
            KickAssemblerTypes.LESS_EQUALS,
            KickAssemblerTypes.GREATER_EQUALS,
            KickAssemblerTypes.LESS,
            KickAssemblerTypes.GREATER,
            KickAssemblerTypes.BIT_AND,
            KickAssemblerTypes.BIT_OR,
            KickAssemblerTypes.BIT_XOR,
            KickAssemblerTypes.BIT_NOT,
            KickAssemblerTypes.SHIFT_LEFT,
            KickAssemblerTypes.SHIFT_RIGHT,
            KickAssemblerTypes.PLUS,
            KickAssemblerTypes.MINUS,
            KickAssemblerTypes.TIMES,
            KickAssemblerTypes.DIVIDE,
            KickAssemblerTypes.NOT_EQUAL,
            KickAssemblerTypes.EQUAL,
            KickAssemblerTypes.AND,
            KickAssemblerTypes.OR,
            KickAssemblerTypes.NOT,
            KickAssemblerTypes.PLUS_PLUS,
            KickAssemblerTypes.MINUS_MINUS,
            KickAssemblerTypes.PLUS_EQUAL,
            KickAssemblerTypes.MINUS_EQUAL,
            KickAssemblerTypes.TIMES_EQUAL,
            KickAssemblerTypes.DIVIDE_EQUAL,
            KickAssemblerTypes.DOT,
            KickAssemblerTypes.COLON,
            KickAssemblerTypes.QUESTION_MARK
    );

    private static final TokenSet METADATA_TOKENS = TokenSet.create(
            KickAssemblerTypes.PREPROCESSOR,
            KickAssemblerTypes.BASIC_UPSTART
    );

    private static final TokenSet LABEL_TOKENS = TokenSet.create(
            KickAssemblerTypes.LABEL,
            KickAssemblerTypes.LOCAL_LABEL
    );

    private static final TokenSet PARENTHESES_TOKENS = TokenSet.create(
            KickAssemblerTypes.LEFT_PARENTHESES,
            KickAssemblerTypes.RIGHT_PARENTHESES
    );

    private static final TokenSet BRACES_TOKENS = TokenSet.create(
            KickAssemblerTypes.LEFT_BRACE,
            KickAssemblerTypes.RIGHT_BRACE
    );

    private static final TokenSet BRACKETS_TOKENS = TokenSet.create(
            KickAssemblerTypes.LEFT_BRACKET,
            KickAssemblerTypes.RIGHT_BRACKET
    );

    private static final Map<IElementType, TextAttributesKey> TYPE_KEY_MAP = createTypeKeyMap();

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new KickAssemblerLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(TYPE_KEY_MAP.get(tokenType));
    }

    private static Map<IElementType, TextAttributesKey> createTypeKeyMap() {
        Map<IElementType, TextAttributesKey> typeKeyMap = new HashMap<>();

        fillMap(typeKeyMap, OPERATOR_TOKENS, KICK_ASSEMBLER_OPERATION_SIGN);
        fillMap(typeKeyMap, METADATA_TOKENS, KICK_ASSEMBLER_METADATA);
        fillMap(typeKeyMap, LABEL_TOKENS, KICK_ASSEMBLER_LABEL);
        fillMap(typeKeyMap, PARENTHESES_TOKENS, KICK_ASSEMBLER_PARENTHESES);
        fillMap(typeKeyMap, BRACES_TOKENS, KICK_ASSEMBLER_BRACES);
        fillMap(typeKeyMap, BRACKETS_TOKENS, KICK_ASSEMBLER_BRACKETS);

        typeKeyMap.put(KickAssemblerTypes.COMMENT_LINE, KICK_ASSEMBLER_LINE_COMMENT);
        typeKeyMap.put(KickAssemblerTypes.COMMENT_BLOCK, KICK_ASSEMBLER_BLOCK_COMMENT);
        typeKeyMap.put(KickAssemblerTypes.NUMBER, KICK_ASSEMBLER_NUMBER);
        typeKeyMap.put(KickAssemblerTypes.MNEMONIC, KICK_ASSEMBLER_MNEMONIC);
        typeKeyMap.put(KickAssemblerTypes.STRING, KICK_ASSEMBLER_STRING);
        typeKeyMap.put(KickAssemblerTypes.DUMMY, KICK_ASSEMBLER_DUMMY);

        return Collections.unmodifiableMap(typeKeyMap);
    }
}
