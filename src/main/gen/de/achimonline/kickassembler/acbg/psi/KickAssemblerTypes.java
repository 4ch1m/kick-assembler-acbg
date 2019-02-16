// This is a generated file. Not intended for manual editing.
package de.achimonline.kickassembler.acbg.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import de.achimonline.kickassembler.acbg.psi.impl.*;

public interface KickAssemblerTypes {

  IElementType ROOT = new KickAssemblerElementType("ROOT");

  IElementType AND = new KickAssemblerTokenType("AND");
  IElementType ASSIGN = new KickAssemblerTokenType("ASSIGN");
  IElementType BASIC_UPSTART = new KickAssemblerTokenType("BASIC_UPSTART");
  IElementType BIT_AND = new KickAssemblerTokenType("BIT_AND");
  IElementType BIT_NOT = new KickAssemblerTokenType("BIT_NOT");
  IElementType BIT_OR = new KickAssemblerTokenType("BIT_OR");
  IElementType BIT_XOR = new KickAssemblerTokenType("BIT_XOR");
  IElementType COLON = new KickAssemblerTokenType("COLON");
  IElementType COMMA = new KickAssemblerTokenType("COMMA");
  IElementType COMMENT_BLOCK = new KickAssemblerTokenType("COMMENT_BLOCK");
  IElementType COMMENT_LINE = new KickAssemblerTokenType("COMMENT_LINE");
  IElementType DIVIDE = new KickAssemblerTokenType("DIVIDE");
  IElementType DIVIDE_EQUAL = new KickAssemblerTokenType("DIVIDE_EQUAL");
  IElementType DOT = new KickAssemblerTokenType("DOT");
  IElementType DUMMY = new KickAssemblerTokenType("DUMMY");
  IElementType EQUAL = new KickAssemblerTokenType("EQUAL");
  IElementType GREATER = new KickAssemblerTokenType("GREATER");
  IElementType GREATER_EQUALS = new KickAssemblerTokenType("GREATER_EQUALS");
  IElementType HASH = new KickAssemblerTokenType("HASH");
  IElementType LABEL = new KickAssemblerTokenType("LABEL");
  IElementType LEFT_BRACE = new KickAssemblerTokenType("LEFT_BRACE");
  IElementType LEFT_BRACKET = new KickAssemblerTokenType("LEFT_BRACKET");
  IElementType LEFT_PARENTHESES = new KickAssemblerTokenType("LEFT_PARENTHESES");
  IElementType LESS = new KickAssemblerTokenType("LESS");
  IElementType LESS_EQUALS = new KickAssemblerTokenType("LESS_EQUALS");
  IElementType LOCAL_LABEL = new KickAssemblerTokenType("LOCAL_LABEL");
  IElementType MINUS = new KickAssemblerTokenType("MINUS");
  IElementType MINUS_EQUAL = new KickAssemblerTokenType("MINUS_EQUAL");
  IElementType MINUS_MINUS = new KickAssemblerTokenType("MINUS_MINUS");
  IElementType MNEMONIC = new KickAssemblerTokenType("MNEMONIC");
  IElementType NOT = new KickAssemblerTokenType("NOT");
  IElementType NOT_EQUAL = new KickAssemblerTokenType("NOT_EQUAL");
  IElementType NUMBER = new KickAssemblerTokenType("NUMBER");
  IElementType OR = new KickAssemblerTokenType("OR");
  IElementType PLUS = new KickAssemblerTokenType("PLUS");
  IElementType PLUS_EQUAL = new KickAssemblerTokenType("PLUS_EQUAL");
  IElementType PLUS_PLUS = new KickAssemblerTokenType("PLUS_PLUS");
  IElementType PREPROCESSOR = new KickAssemblerTokenType("PREPROCESSOR");
  IElementType QUESTION_MARK = new KickAssemblerTokenType("QUESTION_MARK");
  IElementType RIGHT_BRACE = new KickAssemblerTokenType("RIGHT_BRACE");
  IElementType RIGHT_BRACKET = new KickAssemblerTokenType("RIGHT_BRACKET");
  IElementType RIGHT_PARENTHESES = new KickAssemblerTokenType("RIGHT_PARENTHESES");
  IElementType SEMICOLON = new KickAssemblerTokenType("SEMICOLON");
  IElementType SHIFT_LEFT = new KickAssemblerTokenType("SHIFT_LEFT");
  IElementType SHIFT_RIGHT = new KickAssemblerTokenType("SHIFT_RIGHT");
  IElementType STRING = new KickAssemblerTokenType("STRING");
  IElementType TIMES = new KickAssemblerTokenType("TIMES");
  IElementType TIMES_EQUAL = new KickAssemblerTokenType("TIMES_EQUAL");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ROOT) {
        return new KickAssemblerRootImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
