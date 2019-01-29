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
    if (t == ADDRESS_OPERAND) {
      r = addressOperand(b, 0);
    }
    else if (t == INSTRUCTION_BINARY) {
      r = instructionBinary(b, 0);
    }
    else if (t == INSTRUCTION_CALL) {
      r = instructionCall(b, 0);
    }
    else if (t == INSTRUCTION_UNARY) {
      r = instructionUnary(b, 0);
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
  // [VALUE] LEFT_PAREN (baseOffsetMultiplier | offsetMultiplier | registers) RIGHT_PAREN
  public static boolean addressOperand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "addressOperand")) return false;
    if (!nextTokenIs(b, "<address operand>", LEFT_PAREN, VALUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ADDRESS_OPERAND, "<address operand>");
    r = addressOperand_0(b, l + 1);
    r = r && consumeToken(b, LEFT_PAREN);
    r = r && addressOperand_2(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [VALUE]
  private static boolean addressOperand_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "addressOperand_0")) return false;
    consumeToken(b, VALUE);
    return true;
  }

  // baseOffsetMultiplier | offsetMultiplier | registers
  private static boolean addressOperand_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "addressOperand_2")) return false;
    boolean r;
    r = baseOffsetMultiplier(b, l + 1);
    if (!r) r = offsetMultiplier(b, l + 1);
    if (!r) r = registers(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // segment | LEFT_PAREN [STAR] registers RIGHT_PAREN | [STAR] registers | VALUE | IDENTIFIER
  static boolean argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = segment(b, l + 1);
    if (!r) r = argument_1(b, l + 1);
    if (!r) r = argument_2(b, l + 1);
    if (!r) r = consumeToken(b, VALUE);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // LEFT_PAREN [STAR] registers RIGHT_PAREN
  private static boolean argument_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PAREN);
    r = r && argument_1_1(b, l + 1);
    r = r && registers(b, l + 1);
    r = r && consumeToken(b, RIGHT_PAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // [STAR]
  private static boolean argument_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_1_1")) return false;
    consumeToken(b, STAR);
    return true;
  }

  // [STAR] registers
  private static boolean argument_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_2_0(b, l + 1);
    r = r && registers(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [STAR]
  private static boolean argument_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_2_0")) return false;
    consumeToken(b, STAR);
    return true;
  }

  /* ********************************************************** */
  // registers COMMA registers COMMA (VALUE | IDENTIFIER)
  static boolean baseOffsetMultiplier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "baseOffsetMultiplier")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = registers(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && registers(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && baseOffsetMultiplier_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VALUE | IDENTIFIER
  private static boolean baseOffsetMultiplier_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "baseOffsetMultiplier_4")) return false;
    boolean r;
    r = consumeToken(b, VALUE);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // [INSTRUCTION_PREFIX] INSTRUCTION (addressOperand | argument) COMMA argument
  public static boolean instructionBinary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionBinary")) return false;
    if (!nextTokenIs(b, "<instruction binary>", INSTRUCTION, INSTRUCTION_PREFIX)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION_BINARY, "<instruction binary>");
    r = instructionBinary_0(b, l + 1);
    r = r && consumeToken(b, INSTRUCTION);
    r = r && instructionBinary_2(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && argument(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [INSTRUCTION_PREFIX]
  private static boolean instructionBinary_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionBinary_0")) return false;
    consumeToken(b, INSTRUCTION_PREFIX);
    return true;
  }

  // addressOperand | argument
  private static boolean instructionBinary_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionBinary_2")) return false;
    boolean r;
    r = addressOperand(b, l + 1);
    if (!r) r = argument(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // [INSTRUCTION_PREFIX] INSTRUCTION
  public static boolean instructionCall(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionCall")) return false;
    if (!nextTokenIs(b, "<instruction call>", INSTRUCTION, INSTRUCTION_PREFIX)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION_CALL, "<instruction call>");
    r = instructionCall_0(b, l + 1);
    r = r && consumeToken(b, INSTRUCTION);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [INSTRUCTION_PREFIX]
  private static boolean instructionCall_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionCall_0")) return false;
    consumeToken(b, INSTRUCTION_PREFIX);
    return true;
  }

  /* ********************************************************** */
  // [INSTRUCTION_PREFIX] INSTRUCTION (addressOperand | argument)
  public static boolean instructionUnary(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionUnary")) return false;
    if (!nextTokenIs(b, "<instruction unary>", INSTRUCTION, INSTRUCTION_PREFIX)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION_UNARY, "<instruction unary>");
    r = instructionUnary_0(b, l + 1);
    r = r && consumeToken(b, INSTRUCTION);
    r = r && instructionUnary_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [INSTRUCTION_PREFIX]
  private static boolean instructionUnary_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionUnary_0")) return false;
    consumeToken(b, INSTRUCTION_PREFIX);
    return true;
  }

  // addressOperand | argument
  private static boolean instructionUnary_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructionUnary_2")) return false;
    boolean r;
    r = addressOperand(b, l + 1);
    if (!r) r = argument(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // instructionBinary|instructionUnary|instructionCall
  static boolean instructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instructions")) return false;
    if (!nextTokenIs(b, "", INSTRUCTION, INSTRUCTION_PREFIX)) return false;
    boolean r;
    r = instructionBinary(b, l + 1);
    if (!r) r = instructionUnary(b, l + 1);
    if (!r) r = instructionCall(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // root_rule_*
  static boolean kickAssemblerFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "kickAssemblerFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!root_rule_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "kickAssemblerFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // COMMA registers COMMA (VALUE | IDENTIFIER)
  static boolean offsetMultiplier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "offsetMultiplier")) return false;
    if (!nextTokenIs(b, COMMA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && registers(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && offsetMultiplier_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VALUE | IDENTIFIER
  private static boolean offsetMultiplier_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "offsetMultiplier_3")) return false;
    boolean r;
    r = consumeToken(b, VALUE);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // REGISTER_GENERAL|REGISTER_SEGMENT|REGISTER_SPECIAL
  static boolean registers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "registers")) return false;
    boolean r;
    r = consumeToken(b, REGISTER_GENERAL);
    if (!r) r = consumeToken(b, REGISTER_SEGMENT);
    if (!r) r = consumeToken(b, REGISTER_SPECIAL);
    return r;
  }

  /* ********************************************************** */
  // registers|instructions|STRING|LEFT_PAREN|RIGHT_PAREN|SLASH|BACKSLASH|STAR|COLON|SEMICOLON|DOT|COMMA|LEFT_ANGLE|RIGHT_ANGLE|IDENTIFIER|VALUE|LABEL|PREPROCESSOR|COMMENT_LINE|COMMENT_BLOCK
  static boolean root_rule_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_rule_")) return false;
    boolean r;
    r = registers(b, l + 1);
    if (!r) r = instructions(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, LEFT_PAREN);
    if (!r) r = consumeToken(b, RIGHT_PAREN);
    if (!r) r = consumeToken(b, SLASH);
    if (!r) r = consumeToken(b, BACKSLASH);
    if (!r) r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, DOT);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, LEFT_ANGLE);
    if (!r) r = consumeToken(b, RIGHT_ANGLE);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, VALUE);
    if (!r) r = consumeToken(b, LABEL);
    if (!r) r = consumeToken(b, PREPROCESSOR);
    if (!r) r = consumeToken(b, COMMENT_LINE);
    if (!r) r = consumeToken(b, COMMENT_BLOCK);
    return r;
  }

  /* ********************************************************** */
  // REGISTER_SEGMENT COLON VALUE
  static boolean segment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "segment")) return false;
    if (!nextTokenIs(b, REGISTER_SEGMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, REGISTER_SEGMENT, COLON, VALUE);
    exit_section_(b, m, null, r);
    return r;
  }

}
