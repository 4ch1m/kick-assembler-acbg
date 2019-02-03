package de.achimonline.kickassembler.acbg.module;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.util.IconLoader;
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class KickAssemblerModuleType extends ModuleType<KickAssemblerModuleBuilder> {
    private static final String KICK_ASSEMBLER_MODULE = "KickAssemblerModule";

    public KickAssemblerModuleType() {
        super(KICK_ASSEMBLER_MODULE);
    }

    @NotNull
    public static KickAssemblerModuleType getInstance() {
        return (KickAssemblerModuleType) ModuleTypeManager.getInstance().findByID(KICK_ASSEMBLER_MODULE);
    }

    @NotNull
    @Override
    public KickAssemblerModuleBuilder createModuleBuilder() {
        return new KickAssemblerModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return KickAssemblerProperties.message("module.name");
    }

    @NotNull
    @Override
    public String getDescription() {
        return KickAssemblerProperties.message("module.description");
    }

    @Override
    public Icon getNodeIcon(boolean isOpened) {
        return IconLoader.findIcon("/icons/icon_16x16.png");
    }
}
