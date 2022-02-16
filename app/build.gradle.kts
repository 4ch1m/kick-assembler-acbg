import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.3.0"
    id("org.jetbrains.changelog") version "1.3.1"
    id("org.jetbrains.grammarkit") version "2021.2.1"
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java.srcDir("src/main/java")
        java.srcDir("src/main/gen")
    }
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.9")
    testImplementation("org.powermock:powermock-module-junit4:2.0.9")
}

changelog {
    version.set(properties("pluginVersion"))
    path.set("${project.projectDir}/../CHANGELOG.md")
}

tasks {
    properties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it
        }
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        changeNotes.set(provider {
            changelog.run {
                getOrNull(properties("pluginVersion")) ?: getLatest()
            }.toHTML()
        })
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(project.property("JB_PLUGIN_PUBLISH_TOKEN").toString())
    }
}

apply(plugin = "org.jetbrains.grammarkit")

val generateParser = tasks.register<GenerateParserTask>("generateParserTask") {
    source.set("src/main/java/de/achimonline/kickassembler/acbg/psi/KickAssembler.bnf")
    targetRoot.set("src/main/gen")
    pathToParser.set("/de/achimonline/kickassembler/acbg/parser/KickAssemblerParser.java")
    pathToPsiRoot.set("/de/achimonline/kickassembler/acbg/psi")
    purgeOldFiles.set(true)
}

val generateLexer = tasks.register<GenerateLexerTask>("generateLexerTask") {
    source.set("src/main/java/de/achimonline/kickassembler/acbg/lexer/KickAssembler.flex")
    targetDir.set("src/main/gen/de/achimonline/kickassembler/acbg/lexer")
    targetClass.set("KickAssemblerLexer")
    purgeOldFiles.set(true)
}

tasks.withType<JavaCompile> {
    dependsOn(generateParser)
    dependsOn(generateLexer)
}
