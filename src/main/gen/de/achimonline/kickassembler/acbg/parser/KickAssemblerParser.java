// This is a generated file. Not intended for manual editing.
package de.achimonline.kickassembler.acbg.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static de.achimonline.kickassembler.acbg.psi.KickAssemblerTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class KickAssemblerParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ROOT) {
      r = root(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return kickAssemblerFile(b, l + 1);
  }

  /* ********************************************************** */
  // root*
  static boolean kickAssemblerFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kickAssemblerFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!root(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "kickAssemblerFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // STRING |
  //            NUMBER |
  //            LEFT_PARENTHESES |
  //            RIGHT_PARENTHESES |
  //            LEFT_BRACKET |
  //            RIGHT_BRACKET |
  //            LEFT_BRACE |
  //            RIGHT_BRACE |
  //            HASH |
  //            ASSIGN |
  //            COMMA |
  //            SEMICOLON |
  //            LESS_EQUALS |
  //            GREATER_EQUALS |
  //            LESS |
  //            GREATER |
  //            BIT_AND |
  //            BIT_OR |
  //            BIT_XOR |
  //            BIT_NOT |
  //            SHIFT_LEFT |
  //            SHIFT_RIGHT |
  //            PLUS |
  //            MINUS |
  //            TIMES |
  //            DIVIDE |
  //            NOT_EQUAL |
  //            EQUAL |
  //            AND |
  //            OR |
  //            NOT |
  //            PLUS_PLUS |
  //            MINUS_MINUS |
  //            PLUS_EQUAL |
  //            MINUS_EQUAL |
  //            TIMES_EQUAL |
  //            DIVIDE_EQUAL |
  //            DOT |
  //            COLON |
  //            QUESTION_MARK |
  //            BASIC_UPSTART |
  //            PREPROCESSOR |
  //            LABEL |
  //            LOCAL_LABEL |
  //            MNEMONIC |
  //            COMMENT_LINE |
  //            COMMENT_BLOCK |
  //            DUMMY
  public static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ROOT, "<root>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, LEFT_PARENTHESES);
    if (!r) r = consumeToken(b, RIGHT_PARENTHESES);
    if (!r) r = consumeToken(b, LEFT_BRACKET);
    if (!r) r = consumeToken(b, RIGHT_BRACKET);
    if (!r) r = consumeToken(b, LEFT_BRACE);
    if (!r) r = consumeToken(b, RIGHT_BRACE);
    if (!r) r = consumeToken(b, HASH);
    if (!r) r = consumeToken(b, ASSIGN);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, LESS_EQUALS);
    if (!r) r = consumeToken(b, GREATER_EQUALS);
    if (!r) r = consumeToken(b, LESS);
    if (!r) r = consumeToken(b, GREATER);
    if (!r) r = consumeToken(b, BIT_AND);
    if (!r) r = consumeToken(b, BIT_OR);
    if (!r) r = consumeToken(b, BIT_XOR);
    if (!r) r = consumeToken(b, BIT_NOT);
    if (!r) r = consumeToken(b, SHIFT_LEFT);
    if (!r) r = consumeToken(b, SHIFT_RIGHT);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, TIMES);
    if (!r) r = consumeToken(b, DIVIDE);
    if (!r) r = consumeToken(b, NOT_EQUAL);
    if (!r) r = consumeToken(b, EQUAL);
    if (!r) r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, OR);
    if (!r) r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, PLUS_PLUS);
    if (!r) r = consumeToken(b, MINUS_MINUS);
    if (!r) r = consumeToken(b, PLUS_EQUAL);
    if (!r) r = consumeToken(b, MINUS_EQUAL);
    if (!r) r = consumeToken(b, TIMES_EQUAL);
    if (!r) r = consumeToken(b, DIVIDE_EQUAL);
    if (!r) r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, QUESTION_MARK);
    if (!r) r = consumeToken(b, BASIC_UPSTART);
    if (!r) r = consumeToken(b, PREPROCESSOR);
    if (!r) r = consumeToken(b, LABEL);
    if (!r) r = consumeToken(b, LOCAL_LABEL);
    if (!r) r = consumeToken(b, MNEMONIC);
    if (!r) r = consumeToken(b, COMMENT_LINE);
    if (!r) r = consumeToken(b, COMMENT_BLOCK);
    if (!r) r = consumeToken(b, DUMMY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
