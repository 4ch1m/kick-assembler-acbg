# Kick Assembler (... and chew bubble gum) | ChangeLog

## [1.10.0]

### Changed
- updated for IntelliJ 2023.3.5
- use default project SDK if none is set in the Kick Assembler plugin settings

## [1.9.0]

### Changed
- dependency upgrades
- Gradle-wrapper update
- other minor updates

## [1.8.0]

### Changed
- upgraded plugin-dependencies
- improved syntax highlighting (revised Flex/BNF definitions)

## [1.7.0]

### Changed
- upgraded library-/plugin-dependencies
- various refactorings (mostly code-inspection warnings)

### Fixed
- fixed RunConfiguration handling (which was unnoticeably broken due to plugin-API changes; showed "SDK is not specified for module")

## [1.6.0]

### Changed
- updated library-/plugin-dependencies ("Gradle IntelliJ Plugin", etc.)
- reorganized directory-structure (including migration to Kotlin DSL for build-script)
- Gradle-wrapper update
- min IDE version set to "2021.3"

## [1.5.0]

### Changed
- necessary adjustments to work with the latest plugin APIs
- automatic lexer/parser source generation upon build
- updated library-/plugin-dependencies
- Gradle-wrapper update
- other minor improvements

## [1.4.0]

### Fixed
- bugfixes (file template, localization, filetype handling)

## [1.3.0]

### Added
- added plugin-icon

### Changed
- several code-changes/refactorings in order to eliminate usage of deprecated APIs
- raised min-version of compatibility to 201 (2020.1)
- Gradle-wrapper update

### Fixed
- fix for KickAssembler SDK-configuration

## [1.2.0]

### Changed
- updated plugin name ("[beta]" removed)
- updated plugin dependencies
- Gradle-wrapper update
- other minor configuration updates

## [1.1.0]

### Fixed
- fixed SDK-/JRE-handling for run-configurations

### Added
- syntax highlighting: missing assembler-directives added ('.cpu', '.fillword', '.lohifill')

### Changed
- updated plugin dependencies

## [1.0.5]

### Fixed
- fixed since-build number

## [1.0.4]

### Changed
- adjusted "IDEA compatibility version"... again ;-)

## [1.0.3]

### Fixed
- updated/fixed description

## [1.0.2]

### Changed
- minimum and maximum "IDEA compatibility version" now being set via Gradle-plugin

## [1.0.1]

### Changed
- updated plugin description

## [1.0.0]

### Changed
- initial release
