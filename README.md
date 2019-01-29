![Kick Assembler - ACBG](/src/main/resources/icons/icon_64x64.png?raw=true) ![Retro Rules](/src/main/resources/retro_rules_564x64.png?raw=true)

# Kick Assembler <small><small><small>(... and chew bubble gum)</small></small></small>
> A [Kick Assembler](http://theweb.dk/KickAssembler) language plugin for IntelliJ IDEA [IntelliJ IDEA](https://www.jetbrains.com/idea/).

##### Build Status
[![Build Status](https://travis-ci.org/4ch1m/kick-assembler-acbg.svg?branch=master)](https://travis-ci.org/4ch1m/ChangelistOrganizer)

## Disclaimer

This plugin is still at an early stage of development.
Not all of the planned features are implemented or working as expected at this point.
Any help or contributions (bug reports, pull requests, etc.) are much appreciated. 

## Features

In addition to the IntelliJ's wide range of built-in functionality (like version control, the excellent editor, etc.), this plugin provides the following Kick Assembler specific features:

* Syntax Highlighting :wrench:
* Run Configurations :heavy_check_mark:
* SDK Management :heavy_check_mark:
* "New File" Template :heavy_check_mark:
* Refactoring Functionality :construction:
* "New Project" Generation :construction:

## Prerequisites
* [Java Runtime Environment](https://www.java.com/en/download/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download) - Community Edition or Ultimate Edition
* [Kick Assembler](http://theweb.dk/KickAssembler) &hellip; obviously :smirk:

## Installation
Use the IDE's built-in plugin system:

<kbd>Preferences</kbd> &rarr; <kbd>Plugins</kbd> &rarr; <kbd>Browse repositories...</kbd> &rarr; <kbd>Search for "Kick Assembler"</kbd> &rarr; <kbd>Install Plugin</kbd>

Or go to the [plugin page](https://plugins.jetbrains.com/plugin/___?pr=idea) on the [JetBrains](https://www.jetbrains.com)-website, download the archive-file and install manually.

## Setup

Here's how to use/setup the plugin &hellip;

### Project

Simply create a new `Empty Project` and complete all following steps to finish the project initialization.

![setup_empty_project](/screenshots/setup_empty_project.png?raw=true)

### JRE and SDK

In the project's setup-page create a new Kick Assembler SDK:

![setup_new_sdk](/screenshots/setup_new_sdk.png?raw=true)

(Navigate to your Kick Assembler installation directory and click <kbd>OK</kbd>.)

### New File Template

New Kick Assembler files can be created via <kbd>File</kbd> &rarr; <kbd>New</kbd> &rarr; <kbd>Kick Assembler file</kbd>.

![setup_new_file](/screenshots/setup_new_file.png?raw=true)

(&hellip; generating a `BasicUpstart2` stub.)

### Run Configuration

Run Configurations can be used to invoke Kick Assembler with a selected source-file:

![setup_run_configuration](/screenshots/setup_run_configuration.png?raw=true)

IntelliJ's built-in [macros](https://github.com/JetBrains/intellij-community/tree/master/platform/lang-impl/src/com/intellij/ide/macro) can be combined with for your individual `Program parameters`: 

e.g.

* $ProjectName$
* $Projectpath$
* $ProjectFileDir$
* $FileDir$
* $FileDirName$
* $FileDirPathFromParent$
* $FileExt$
* $FileName$
* $FileNameWithoutAllExtensions$
* $FileNameWithoutExtension$
* $FileParentDir$
* $FilePath$
* etc.

<strong>NOTE:</strong> It is also possible to automatically create and run a Run Configuration by right-clicking on a `*.asm`-file.

### JRE <small><small><small>[optional]</small></small></small>

The JRE - used to run Kick Assembler - can be chosen via <kbd>File</kbd> &rarr; <kbd>Settings&hellip;</kbd> &rarr; <kbd>Languages &amp; Frameworks</kbd> &rarr; <kbd>Kick Assembler</kbd>.

(Otherwise IntelliJ's internal JRE will be used.)

![setup_run_configuration](/screenshots/setup_run_configuration.png?raw=true)

## FAQs

> How can I integrate [VICE](http://vice-emu.sourceforge.net/)?

IntelliJ has a neat feature called  [External Tools](https://www.jetbrains.com/help/idea/settings-tools-external-tools.html). It can be used to run third-party tools on (selected) files.

![external_tool_vice](/screenshots/external_tool_vice.png?raw=true)

<strong>NOTE:</strong> The `Arguments:` not only include the `$FileName$`-macro, but can also include any additional VICE arguments as needed.

VICE then easily can be run via the context-menu by right-clicking on a `*.prg` file.

You even can assign a keyboard shortcut for VICE!

Just go to <kbd>File</kbd> &rarr; <kbd>Settings&hellip;</kbd> &rarr; <kbd>Keymap</kbd>; search for `External Tools`; and set a shortcut of your liking. (This will allow you to simply mark a `*.prg`-file and press <kbd>Alt+V</kbd> to run VICE.)

![external_tool_keymap](/screenshots/external_tool_keymap.png?raw=true)

---

## Credits

* [Kick Assembler](http://theweb.dk/KickAssembler) created by Mads Nielsen
* _Sunglasses_ icon made by [Yannick](https://www.flaticon.com/authors/yannick) ([www.flaticon.com](www.flaticon.com)) 

## Author

:email: [Achim Seufert](mailto:kickassembler-acbg@achimonline.de)

## License

Please read the [license](license) file.
