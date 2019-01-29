package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;

public class KickAssemblerRunConfigurationProducer extends RunConfigurationProducer<KickAssemblerRunConfiguration> {
    public KickAssemblerRunConfigurationProducer() {
        super(KickAssemblerRunConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(KickAssemblerRunConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        Location location = context.getLocation();

        if (location == null) {
            return false;
        }

        PsiElement psiElement = location.getPsiElement();

        if (!(psiElement.getLanguage() instanceof KickAssemblerLanguage)) {
            return false;
        }

        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile == null) {
            return false;
        }

        configuration.setName(containingFile.getName());
        configuration.setKickAssemblerFile(containingFile.getVirtualFile().getPath());

        Module contextModule = context.getModule();

        if (contextModule != null) {
            configuration.setModule(contextModule);
        }

        return true;
    }

    @Override
    public boolean isConfigurationFromContext(KickAssemblerRunConfiguration configuration, ConfigurationContext context) {
        return configuration.getKickAssemblerFile().equals(context.getLocation().getVirtualFile().getPath());
    }
}
