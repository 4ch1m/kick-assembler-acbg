package de.achimonline.kickassembler.acbg.module;

import com.intellij.compiler.CompilerWorkspaceConfiguration;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilderListener;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.CompilerModuleExtension;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdkType;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import static com.intellij.openapi.util.io.FileUtil.toSystemIndependentName;
import static org.jetbrains.java.generate.velocity.VelocityFactory.getVelocityEngine;

public class KickAssemblerModuleBuilder extends ModuleBuilder implements ModuleBuilderListener {
    @Override
    public void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException {
        LocalFileSystem localFileSystem = LocalFileSystem.getInstance();

        VirtualFile root = localFileSystem.refreshAndFindFileByPath(toSystemIndependentName(getContentEntryPath()));

        ContentEntry contentEntry = modifiableRootModel.addContentEntry(root);
        contentEntry.addSourceFolder(root, false, StringUtils.EMPTY);

        try {
            VirtualFile out = root.createChildDirectory(null, "out");
            contentEntry.addExcludeFolder(out);

            CompilerModuleExtension compilerModuleExtension = modifiableRootModel.getModuleExtension(CompilerModuleExtension.class);
            compilerModuleExtension.setCompilerOutputPath(out);
            compilerModuleExtension.setExcludeOutput(true);

            VirtualFile main = root.createChildData(null, "main.asm");

            createMainFileFromTemplate(main);
        } catch (IOException e) {
            throw new ConfigurationException(e.getMessage());
        }

    }

    @Override
    public ModuleType getModuleType() {
        return KickAssemblerModuleType.getInstance();
    }

    @Override
    public boolean isTemplateBased() {
        return true;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkType) {
        return sdkType instanceof KickAssemblerSdkType;
    }

    @Override
    public void moduleCreated(@NotNull Module module) {
        CompilerWorkspaceConfiguration.getInstance(module.getProject()).CLEAR_OUTPUT_DIRECTORY = false;
    }

    private void createMainFileFromTemplate(VirtualFile mainFile) throws IOException {
        FileTemplate template = FileTemplateManager.getDefaultInstance().getInternalTemplate("Kick Assembler file");

        VfsUtil.saveText(mainFile, template.getText());

        StringWriter stringWriter = new StringWriter();

        getVelocityEngine().evaluate(createVelocityContext(), stringWriter, "Kick Assembler", new FileReader(VfsUtil.virtualToIoFile(mainFile)));

        VfsUtil.saveText(mainFile, stringWriter.toString());
    }

    private VelocityContext createVelocityContext() {
        return new VelocityContext(); // TODO add needed substitutions
    }
}
