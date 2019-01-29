package de.achimonline.kickassembler.acbg.runconfiguration;

import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.ide.macro.Macro;
import com.intellij.ide.macro.ProjectFileDirMacro;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializer;
import lombok.Getter;
import lombok.Setter;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KickAssemblerRunConfiguration extends ModuleBasedConfiguration {
    @Getter
    @Setter
    private String kickAssemblerFile;

    @Getter
    @Setter
    private String kickAssemblerSdkPathOrName;

    @Getter
    @Setter
    private KickAssemblerProgramParameters kickAssemblerProgramParameters = new KickAssemblerProgramParameters();

    public KickAssemblerRunConfiguration(String name, @NotNull RunConfigurationModule configurationModule, @NotNull ConfigurationFactory factory) {
        super(name, configurationModule, factory);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new KickAssemblerSettingsEditor(getProject());
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        return new KickAssemblerCommandLineState(this, environment);
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);

        XmlSerializer.deserializeInto(this, element);
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        super.writeExternal(element);

        XmlSerializer.serializeInto(this, element);
    }

    @Override
    public Collection<Module> getValidModules() {
        return Arrays.stream(ModuleManager.getInstance(getProject()).getModules())
                .filter(module -> module != null && !module.isDisposed())
                .collect(Collectors.toList());
    }

    protected static class KickAssemblerProgramParameters implements CommonProgramRunConfigurationParameters {
        private static final String DEFAULT_PROGRAM_PARAMETERS = createDefaultProgramParameters();

        private String programParameters;
        private String workingDirectory;
        private Map<String, String> envs = new HashMap<>();
        private boolean passParentEnvs;

        public KickAssemblerProgramParameters() {
            programParameters = DEFAULT_PROGRAM_PARAMETERS;
        }

        @Override
        public Project getProject() {
            return null;
        }

        @Override
        public void setProgramParameters(@Nullable String value) {
            programParameters = value;
        }

        @Nullable
        @Override
        public String getProgramParameters() {
            return programParameters;
        }

        @Override
        public void setWorkingDirectory(@Nullable String value) {
            workingDirectory = value;
        }

        @Nullable
        @Override
        public String getWorkingDirectory() {
            return workingDirectory;
        }

        @Override
        public void setEnvs(@NotNull Map<String, String> envs) {
            this.envs.clear();
            this.envs.putAll(envs);
        }

        @NotNull
        @Override
        public Map<String, String> getEnvs() {
            return envs;
        }

        @Override
        public void setPassParentEnvs(boolean passParentEnvs) {
            this.passParentEnvs = passParentEnvs;
        }

        @Override
        public boolean isPassParentEnvs() {
            return passParentEnvs;
        }

        private static String createDefaultProgramParameters() {
            Macro projectFileDirMacro = new ProjectFileDirMacro();

            return new StringBuilder()
                    .append("-symbolfiledir $").append(projectFileDirMacro.getName()).append("$").append(File.separator).append("sym")
                    .toString();
        }
    }
}
