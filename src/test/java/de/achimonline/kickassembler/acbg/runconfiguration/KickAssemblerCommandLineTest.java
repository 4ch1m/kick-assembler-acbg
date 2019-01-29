package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

// TODO ...

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ServiceManager.class, MacroManager.class})
public class KickAssemblerCommandLineTest {
//    private static final String WORKING_DIRECTORY = "workingDirectory";
//    private static final String BASE_PATH = "basePath";
//
//    @Mock
//    ProjectJdkTable projectJdkTable;
//
//    @Mock
//    MacroManager macroManager;
//
//    @Mock
//    KickAssemblerRunConfiguration kickAssemblerRunConfiguration;
//
//    @Mock
//    KickAssemblerRunConfiguration.KickAssemblerProgramParameters kickAssemblerProgramParameters;
//
//    @Mock
//    RunConfigurationModule runConfigurationModule;
//
//    @Mock
//    Project project;
//
//    @Mock
//    DataContext dataContext;
//
//    @Before
//    public void setUp() throws Exception {
//        mockStatic(ServiceManager.class);
//        mockStatic(MacroManager.class);
//
//        initMocks(this);
//
//        when(ServiceManager.getService(any(Class.class))).thenReturn(projectJdkTable);
//        when(MacroManager.getInstance()).thenReturn(macroManager);
//    }
//
//    @Test
//    public void resolveWorkingDirectoryAndExpandMacros_empty() {
//        when(kickAssemblerRunConfiguration.getKickAssemblerProgramParameters()).thenReturn(kickAssemblerProgramParameters);
//        when(kickAssemblerProgramParameters.getWorkingDirectory()).thenReturn(StringUtils.EMPTY);
//        when(kickAssemblerRunConfiguration.getConfigurationModule()).thenReturn(runConfigurationModule);
//        when(runConfigurationModule.getProject()).thenReturn(project);
//        when(project.getBasePath()).thenReturn(BASE_PATH);
//
//        String workingDirectory = KickAssemblerCommandLine.resolveWorkingDirectoryAndExpandMacros(kickAssemblerRunConfiguration, dataContext);
//
//        assertEquals(BASE_PATH, workingDirectory);
//
//        Mockito.verifyNoMoreInteractions(macroManager);
//    }
}
