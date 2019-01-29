package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import org.jetbrains.annotations.NotNull;

public class KickAssemblerProgramRunner extends DefaultProgramRunner {
    @NotNull
    @Override
    public String getRunnerId() {
        return "KickAssemblerRunner";
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return profile instanceof KickAssemblerRunConfiguration && DefaultRunExecutor.EXECUTOR_ID.equals(executorId);
    }
}
