package de.achimonline.kickassembler.acbg.project;

import com.intellij.openapi.util.IconLoader;
import com.intellij.platform.templates.BuilderBasedTemplate;
import de.achimonline.kickassembler.acbg.module.KickAssemblerModuleBuilder;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class KickAssemblerProjectTemplate extends BuilderBasedTemplate {

    public KickAssemblerProjectTemplate (KickAssemblerModuleBuilder kickAssemblerModuleBuilder) {
        super(kickAssemblerModuleBuilder);
    }

    @NotNull
    @Override
    public String getName() {
        return KickAssemblerProperties.message("project.name");
    }

    @Nullable
    @Override
    public String getDescription() {
        return KickAssemblerProperties.message("project.description");
    }

    @Override
    public Icon getIcon() {
        return IconLoader.findIcon("/icons/icon_16x16.png");
    }
}
