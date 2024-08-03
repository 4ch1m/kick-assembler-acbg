import java.util.*

fun properties(key: String) = project.findProperty(key).toString()

group = properties("pluginGroup")
version = properties("pluginVersion")
description = properties("pluginDescription")

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.intellij") version "1.17.4"
    id("org.jetbrains.changelog") version "2.2.1"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
    id("com.github.ben-manes.versions") version "0.51.0"

    kotlin("plugin.lombok") version "1.9.23"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.12.0")
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
        java.srcDir("src/generated/java")
    }
}

kotlin {
    jvmToolchain(properties("kotlinJvmTarget").toInt())
}

intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
    updateSinceUntilBuild.set(false)
}

changelog {
    version.set(properties("pluginVersion"))
}

tasks {
    properties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it

            dependsOn(
                generateParser,
                generateLexer
            )
        }
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        dependsOn(
            generateParser,
            generateLexer
        )
    }

    dependencyUpdates {
        rejectVersionIf {
            (
                listOf("RELEASE", "FINAL", "GA").any { candidate.version.uppercase(Locale.getDefault()).contains(it) }
                ||
                "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
            ).not()
        }
    }

    generateLexer {
        sourceFile.set(File("src/main/resources/grammar/KickAssembler.flex"))
        targetOutputDir.set(File("src/generated/java/de/achimonline/kickassembler/acbg/lexer"))
        purgeOldFiles.set(true)
    }

    generateParser {
        sourceFile.set(File("src/main/resources/grammar/KickAssembler.bnf"))
        targetRootOutputDir.set(File("src/generated/java"))
        pathToParser.set("/de/achimonline/kickassembler/acbg/parser/KickAssemblerParser.java")
        pathToPsiRoot.set("/de/achimonline/kickassembler/acbg/psi")
        purgeOldFiles.set(true)
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        changeNotes.set(provider {
            changelog.renderItem(
                changelog.getLatest(),
                org.jetbrains.changelog.Changelog.OutputType.HTML
            )
        })
    }

    signPlugin {
        if (listOf(
                "JB_PLUGIN_SIGN_CERTIFICATE_CHAIN",
                "JB_PLUGIN_SIGN_PRIVATE_KEY",
                "JB_PLUGIN_SIGN_PRIVATE_KEY_PASSWORD").all { System.getenv(it) != null }) {
            certificateChainFile.set(file(System.getenv("JB_PLUGIN_SIGN_CERTIFICATE_CHAIN")))
            privateKeyFile.set(file(System.getenv("JB_PLUGIN_SIGN_PRIVATE_KEY")))
            password.set(File(System.getenv("JB_PLUGIN_SIGN_PRIVATE_KEY_PASSWORD")).readText())
        }
    }

    publishPlugin {
        if (project.hasProperty("JB_PLUGIN_PUBLISH_TOKEN")) {
            token.set(project.property("JB_PLUGIN_PUBLISH_TOKEN").toString())
        }
    }
}
