package de.achimonline.kickassembler.acbg.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;
import de.achimonline.kickassembler.acbg.lexer.KickAssemblerLexerAdapter;
import de.achimonline.kickassembler.acbg.psi.KickAssemblerFile;
import de.achimonline.kickassembler.acbg.psi.KickAssemblerTypes;
import org.jetbrains.annotations.NotNull;

public class KickAssemblerParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(KickAssemblerTypes.COMMENT_BLOCK, KickAssemblerTypes.COMMENT_LINE);
    public static final IFileElementType FILE = new IFileElementType(Language.findInstance(KickAssemblerLanguage.class));

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new KickAssemblerLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new KickAssemblerParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new KickAssemblerFile(viewProvider);
    }

    @NotNull
    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return KickAssemblerTypes.Factory.createElement(node);
    }
}
