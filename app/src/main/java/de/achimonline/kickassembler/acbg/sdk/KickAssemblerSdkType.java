package de.achimonline.kickassembler.acbg.sdk;

import com.intellij.openapi.projectRoots.SdkType;
import org.jetbrains.annotations.NotNull;

public abstract class KickAssemblerSdkType extends SdkType {

    public KickAssemblerSdkType(@NotNull String name) {
        super(name);
    }

}
