// This is a generated file. Not intended for manual editing.
package de.achimonline.kickassembler.acbg.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class KickAssemblerVisitor extends PsiElementVisitor {

  public void visitAddressOperand(@NotNull KickAssemblerAddressOperand o) {
    visitPsiElement(o);
  }

  public void visitInstructionBinary(@NotNull KickAssemblerInstructionBinary o) {
    visitPsiElement(o);
  }

  public void visitInstructionCall(@NotNull KickAssemblerInstructionCall o) {
    visitPsiElement(o);
  }

  public void visitInstructionUnary(@NotNull KickAssemblerInstructionUnary o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
