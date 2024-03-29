package de.achimonline.kickassembler.acbg.project;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;

public class KickAssemblerProjectListener implements ToolWindowManagerListener {
    private final Project project;

    public KickAssemblerProjectListener(Project project) {
        this.project = project;
    }

    // TODO handle state-change ...
}
