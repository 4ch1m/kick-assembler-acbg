package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.LazyRunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import de.achimonline.kickassembler.acbg.KickAssemblerLanguage;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("rawtypes")
public class KickAssemblerRunConfigurationProducer extends LazyRunConfigurationProducer<KickAssemblerRunConfiguration> {
    @Override
    protected boolean setupConfigurationFromContext(@NotNull KickAssemblerRunConfiguration configuration, @NotNull ConfigurationContext context, @NotNull Ref<PsiElement> sourceElement) {
        Location<?> location = context.getLocation();

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

        Sdk projectSdk = ProjectRootManager.getInstance(context.getProject()).getProjectSdk();

        if (projectSdk == null) {
            return false;
        }

        configuration.setKickAssemblerSdkPathOrName(projectSdk.getHomePath());

        return true;
    }

    @Override
    public boolean isConfigurationFromContext(@NotNull KickAssemblerRunConfiguration configuration, @NotNull ConfigurationContext context) {
        Location location = context.getLocation();

        if (location != null) {
            VirtualFile virtualFile = location.getVirtualFile();

            if (virtualFile != null) {
                return configuration.getKickAssemblerFile().equals(virtualFile.getPath());
            }
        }

        return false;
    }

    @NotNull
    @Override
    public ConfigurationFactory getConfigurationFactory() {
        return new KickAssemblerRunConfigurationType().getConfigurationFactories()[0];
    }
}
