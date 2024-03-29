package de.achimonline.kickassembler.acbg.runconfiguration

import com.intellij.execution.ui.CommonProgramParametersPanel
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.SdkType
import com.intellij.openapi.roots.ui.configuration.JdkComboBox
import com.intellij.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import de.achimonline.kickassembler.acbg.properties.KickAssemblerProperties.message
import de.achimonline.kickassembler.acbg.sdk.KickAssemblerSdk
import javax.swing.JComponent

@Suppress("DialogTitleCapitalization")
class KickAssemblerSettingsEditor(val project: Project) : SettingsEditor<KickAssemblerRunConfiguration<*>>() {
    private val kickAssemblerFile = TextFieldWithBrowseButton()
    private var kickAssemblerSdk: JdkComboBox
    private var kickAssemblerProgramParameters: CommonProgramParametersPanel

    private val projectSdksModel = ProjectSdksModel()

    init {
        val kickAssemblerFileChooserDescriptor = FileChooserDescriptor(
            /* chooseFiles = */ true,
            /* chooseFolders = */ false,
            /* chooseJars = */ false,
            /* chooseJarsAsFiles = */ true,
            /* chooseJarContents = */ false,
            /* chooseMultiple = */ false
        )
        kickAssemblerFileChooserDescriptor.title = message("runconfiguration.main.file.browse.title")
        kickAssemblerFileChooserDescriptor.description = message("runconfiguration.main.file.browse.description")

        kickAssemblerFile.addBrowseFolderListener(
            /* title = */ kickAssemblerFileChooserDescriptor.title,
            /* description = */ kickAssemblerFileChooserDescriptor.description,
            /* project = */ project,
            /* fileChooserDescriptor = */ kickAssemblerFileChooserDescriptor
        )

        projectSdksModel.syncSdks()

        val kickAssemblerSdkType = SdkType.findInstance(KickAssemblerSdk::class.java)

        kickAssemblerSdk = JdkComboBox(
            /* project = */ project,
            /* sdkModel = */ projectSdksModel,
            /* sdkTypeFilter = */ { it is KickAssemblerSdk },
            /* sdkFilter = */ { it.sdkType == kickAssemblerSdkType },
            /* suggestedSdkFilter = */ { it.sdkType == kickAssemblerSdkType },
            /* creationFilter = */ null,
            /* onNewSdkAdded = */ null
        )

        kickAssemblerProgramParameters = CommonProgramParametersPanel()
    }

    override fun resetEditorFrom(kickAssemblerRunConfiguration: KickAssemblerRunConfiguration<*>) {
        kickAssemblerFile.text = kickAssemblerRunConfiguration.kickAssemblerFile
        kickAssemblerSdk.selectedJdk = KickAssemblerSdk.getSdkFromHomePathOrName(kickAssemblerRunConfiguration.kickAssemblerSdkPathOrName)
        kickAssemblerProgramParameters.reset(kickAssemblerRunConfiguration.kickAssemblerProgramParameters)
    }

    override fun applyEditorTo(kickAssemblerRunConfiguration: KickAssemblerRunConfiguration<*>) {
        kickAssemblerRunConfiguration.kickAssemblerFile = kickAssemblerFile.text
        kickAssemblerRunConfiguration.kickAssemblerSdkPathOrName = kickAssemblerSdk.selectedJdk?.homePath
        kickAssemblerProgramParameters.applyTo(kickAssemblerRunConfiguration.kickAssemblerProgramParameters)
    }

    override fun createEditor(): JComponent {
        return panel {
            group {
                row(message("runconfiguration.main.file")) {
                    cell(kickAssemblerFile)
                        .align(AlignX.FILL)
                }

                row(message("runconfiguration.sdk")) {
                    cell(kickAssemblerSdk)
                        .align(AlignX.FILL)
                }
            }

            group {
                row {
                    cell(kickAssemblerProgramParameters)
                        .resizableColumn()
                        .align(Align.FILL)
                }
            }
        }
    }
}
