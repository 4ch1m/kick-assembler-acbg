import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.11.0"
    id("org.jetbrains.changelog") version "2.0.0"
    id("org.jetbrains.grammarkit") version "2021.2.2"
    id("com.github.ben-manes.versions") version "0.44.0"
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
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.10.0")
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
        if (project.hasProperty("JB_PLUGIN_PUBLISH_TOKEN")) {
            token.set(project.property("JB_PLUGIN_PUBLISH_TOKEN").toString())
        }
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
java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

tasks.withType<JavaCompile> {
    dependsOn(generateParser)
    dependsOn(generateLexer)
}
