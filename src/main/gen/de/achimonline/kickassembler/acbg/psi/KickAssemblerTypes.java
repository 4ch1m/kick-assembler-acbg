// This is a generated file. Not intended for manual editing.
package de.achimonline.kickassembler.acbg.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import de.achimonline.kickassembler.acbg.psi.impl.*;

public interface KickAssemblerTypes {

  IElementType ADDRESS_OPERAND = new KickAssemblerElementType("ADDRESS_OPERAND");
  IElementType INSTRUCTION_BINARY = new KickAssemblerElementType("INSTRUCTION_BINARY");
  IElementType INSTRUCTION_CALL = new KickAssemblerElementType("INSTRUCTION_CALL");
  IElementType INSTRUCTION_UNARY = new KickAssemblerElementType("INSTRUCTION_UNARY");

  IElementType BACKSLASH = new KickAssemblerTokenType("BACKSLASH");
  IElementType COLON = new KickAssemblerTokenType("COLON");
  IElementType COMMA = new KickAssemblerTokenType("COMMA");
  IElementType COMMENT_BLOCK = new KickAssemblerTokenType("COMMENT_BLOCK");
  IElementType COMMENT_LINE = new KickAssemblerTokenType("COMMENT_LINE");
  IElementType DOT = new KickAssemblerTokenType("DOT");
  IElementType IDENTIFIER = new KickAssemblerTokenType("IDENTIFIER");
  IElementType INSTRUCTION = new KickAssemblerTokenType("INSTRUCTION");
  IElementType INSTRUCTION_PREFIX = new KickAssemblerTokenType("INSTRUCTION_PREFIX");
  IElementType LABEL = new KickAssemblerTokenType("LABEL");
  IElementType LEFT_ANGLE = new KickAssemblerTokenType("LEFT_ANGLE");
  IElementType LEFT_PAREN = new KickAssemblerTokenType("LEFT_PAREN");
  IElementType PREPROCESSOR = new KickAssemblerTokenType("PREPROCESSOR");
  IElementType REGISTER_GENERAL = new KickAssemblerTokenType("REGISTER_GENERAL");
  IElementType REGISTER_SEGMENT = new KickAssemblerTokenType("REGISTER_SEGMENT");
  IElementType REGISTER_SPECIAL = new KickAssemblerTokenType("REGISTER_SPECIAL");
  IElementType RIGHT_ANGLE = new KickAssemblerTokenType("RIGHT_ANGLE");
  IElementType RIGHT_PAREN = new KickAssemblerTokenType("RIGHT_PAREN");
  IElementType SEMICOLON = new KickAssemblerTokenType("SEMICOLON");
  IElementType SLASH = new KickAssemblerTokenType("SLASH");
  IElementType STAR = new KickAssemblerTokenType("STAR");
  IElementType STRING = new KickAssemblerTokenType("STRING");
  IElementType VALUE = new KickAssemblerTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADDRESS_OPERAND) {
        return new KickAssemblerAddressOperandImpl(node);
      }
      else if (type == INSTRUCTION_BINARY) {
        return new KickAssemblerInstructionBinaryImpl(node);
      }
      else if (type == INSTRUCTION_CALL) {
        return new KickAssemblerInstructionCallImpl(node);
      }
      else if (type == INSTRUCTION_UNARY) {
        return new KickAssemblerInstructionUnaryImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
