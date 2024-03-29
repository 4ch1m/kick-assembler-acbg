package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.actionSystem.DataContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KickAssemblerCommandLineTest {
    private static final String WORKING_DIRECTORY = "workingDirectory";

    @Mock
    DataContext dataContext;

    @Mock
    MacroManager macroManager;

    @Mock
    KickAssemblerRunConfiguration<?> kickAssemblerRunConfiguration;

    @Mock
    KickAssemblerRunConfiguration.KickAssemblerProgramParameters kickAssemblerProgramParameters;

    @Test
    public void resolveWorkingDirectoryAndExpandMacros() throws Exception {
        try (MockedStatic<MacroManager> macroManagerMockedStatic = mockStatic(MacroManager.class)) {
            macroManagerMockedStatic.when(MacroManager::getInstance).thenReturn(macroManager);

            when(kickAssemblerRunConfiguration.getKickAssemblerProgramParameters()).thenReturn(kickAssemblerProgramParameters);
            when(kickAssemblerProgramParameters.getWorkingDirectory()).thenReturn(WORKING_DIRECTORY);
            when(macroManager.expandMacrosInString(anyString(), anyBoolean(), any(DataContext.class))).thenReturn(WORKING_DIRECTORY);

            String workingDirectory = KickAssemblerCommandLine.resolveWorkingDirectoryAndExpandMacros(kickAssemblerRunConfiguration, dataContext);

            assertEquals(workingDirectory, WORKING_DIRECTORY);
            verify(macroManager).expandMacrosInString(anyString(), anyBoolean(), any(DataContext.class));
        }
    }
}
