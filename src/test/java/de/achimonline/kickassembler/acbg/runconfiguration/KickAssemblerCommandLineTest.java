package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ServiceManager.class, MacroManager.class})
public class KickAssemblerCommandLineTest {
    private static final String BASE_PATH = "basePath";

    private AutoCloseable autoCloseable;

    @Mock
    MacroManager macroManager;

    @Mock
    KickAssemblerRunConfiguration kickAssemblerRunConfiguration;

    @Mock
    KickAssemblerRunConfiguration.KickAssemblerProgramParameters kickAssemblerProgramParameters;

    @Mock
    RunConfigurationModule runConfigurationModule;

    @Mock
    Project project;

    @Mock
    DataContext dataContext;

    @Before
    public void setUp() throws Exception {
        mockStatic(MacroManager.class);

        autoCloseable = openMocks(this);

        when(MacroManager.getInstance()).thenReturn(macroManager);
    }

    @Test
    public void resolveWorkingDirectoryAndExpandMacros_empty() throws Exception {
        when(kickAssemblerRunConfiguration.getKickAssemblerProgramParameters()).thenReturn(kickAssemblerProgramParameters);
        when(kickAssemblerProgramParameters.getWorkingDirectory()).thenReturn(StringUtils.EMPTY);
        when(kickAssemblerRunConfiguration.getConfigurationModule()).thenReturn(runConfigurationModule);
        when(runConfigurationModule.getProject()).thenReturn(project);
        when(project.getBasePath()).thenReturn(BASE_PATH);

        String workingDirectory = KickAssemblerCommandLine.resolveWorkingDirectoryAndExpandMacros(kickAssemblerRunConfiguration, dataContext);

        assertEquals(BASE_PATH, workingDirectory);

        Mockito.verifyNoMoreInteractions(macroManager);
    }

    @After
    public void releaseMocks() throws Exception {
        autoCloseable.close();
    }
}
