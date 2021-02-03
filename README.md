![Kick Assembler - ACBG](/src/main/resources/icons/icon_64x64.png?raw=true) ![Retro Rules](/src/main/resources/retro_rules_564x64.png?raw=true)

# Kick Assembler <small><small><small>(... and chew bubble gum)</small></small></small>
> A [Kick Assembler](http://theweb.dk/KickAssembler) language plugin for [IntelliJ IDEA](https://www.jetbrains.com/idea/).

![editor](/screenshots/editor.png?raw=true)

##### Build Status
[![Build Status](https://travis-ci.org/4ch1m/kick-assembler-acbg.svg?branch=master)](https://travis-ci.org/4ch1m/kick-assembler-acbg)

## Table of contents
* [Disclaimer](#disclaimer)
* [Features](#features)
* [Prerequisites](#prerequisites)
* [Installation](#installation)
* [Setup](#setup)
  * [Project](#project)
  * [JRE and SDK](#jre-and-sdk)
    * [JRE](#jre)
    * [SDK](#sdk)
  * [New File Template](#new-file-template)
  * [Run Configuration](#run-configuration)
* [FAQs](#faqs)
* [Credits](#credits)
* [Author](#author)
* [License](#license)

## Disclaimer
This plugin is still at an early stage of development.
Not all features are fully implemented or work as expected at this point.
Feedback, help or any other contributions are very welcome. 

## Features
In addition to the IntelliJ's wide range of built-in functionality (like version control, the excellent editor, etc.), this plugin provides the following Kick Assembler specific features:

* Syntax Highlighting :wrench:
* Run Configurations :heavy_check_mark:
* SDK Management :heavy_check_mark:
* Project Generation :heavy_check_mark:
* "New File" Template :heavy_check_mark:
* Refactoring Functionality :construction:

## Prerequisites
* [Java Runtime Environment](https://www.java.com/en/download/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download) - Community Edition or Ultimate Edition
* [Kick Assembler](http://theweb.dk/KickAssembler) &hellip; obviously :smirk:

## Installation
Use the IDE's built-in plugin system:

* <kbd>File</kbd> &rarr; <kbd>Settings</kbd> &rarr; <kbd>Plugins</kbd> &rarr; <kbd>Marketplace</kbd>
* search for "Kick Assembler"
* click the <kbd>Install</kbd>-button

Or go to the [plugin page](https://plugins.jetbrains.com/plugin/11988) on the [JetBrains](https://www.jetbrains.com)-website, download the archive-file and install manually.

## Setup
Here's how to use/setup the plugin &hellip;

### Project
Simply create a new `Kick Assembler project` and complete all following steps to finish the project initialization.

![new_project](/screenshots/new_project.png?raw=true)

### JRE and SDK
#### JRE
The JRE - used to run Kick Assembler - must be configured via <kbd>File</kbd> &rarr; <kbd>Settings&hellip;</kbd> &rarr; <kbd>Languages &amp; Frameworks</kbd> &rarr; <kbd>Kick Assembler</kbd>.

![setup_jre](/screenshots/setup_jre.png?raw=true)

This step is **mandatory**, since the following SDK-setup relies on an existing/configured JRE. 

#### SDK
In the project's setup-page create a new Kick Assembler SDK:

![setup_project_sdk](/screenshots/setup_project_sdk.png?raw=true)

(Navigate to your Kick Assembler installation directory and click <kbd>OK</kbd>.)

### New File Template
New Kick Assembler files can be created via <kbd>File</kbd> &rarr; <kbd>New</kbd> &rarr; <kbd>Kick Assembler file</kbd>.

![new_file](/screenshots/new_file.png?raw=true)

(Which provides a `BasicUpstart2` stub.)

### Run Configuration
Run Configurations can be used to invoke Kick Assembler with a selected source-file:

![run_configuration](/screenshots/run_configuration.png?raw=true)

IntelliJ's built-in [macros](https://github.com/JetBrains/intellij-community/tree/master/platform/lang-impl/src/com/intellij/ide/macro) can be combined with for your individual `Program parameters`: 

e.g.

* `$ProjectName$`
* `$Projectpath$`
* `$ProjectFileDir$`
* `$FileDir$`
* `$FileDirName$`
* `$FileDirPathFromParent$`
* `$FileExt$`
* `$FileName$`
* `$FileNameWithoutAllExtensions$`
* `$FileNameWithoutExtension$`
* `$FileParentDir$`
* `$FilePath$`
* etc.

<strong>NOTE:</strong> It is also possible to automatically create and run a Run Configuration by right-clicking on a `*.asm`-file.

## FAQs
> How can I integrate [VICE](http://vice-emu.sourceforge.net/)?

IntelliJ has a neat feature called  [External Tools](https://www.jetbrains.com/help/idea/settings-tools-external-tools.html). It can be used to run third-party tools on (selected) files.

![external_tools_vice](/screenshots/external_tools_vice.png?raw=true)

<strong>NOTE:</strong> The `Arguments:`-field not only contains the `$FileName$`-macro, but can also include any additional VICE arguments as needed.

VICE then easily can be run via the context-menu by right-clicking on a `*.prg` file.

You even can assign a keyboard shortcut for VICE!

Just go to <kbd>File</kbd> &rarr; <kbd>Settings&hellip;</kbd> &rarr; <kbd>Keymap</kbd>; search for `External Tools`; and set a shortcut of your liking. (This will allow you to simply mark a `*.prg`-file and press <kbd>Alt+V</kbd> to run VICE.)

![keymap_vice](/screenshots/keymap_vice.png?raw=true)

> Is it possible to compile/build the prg-file and run VICE with just one click?

The short answer: Yes.

Here's the detailed guide (for *nix-like systems at least :wink:) ...

To achieve this we need **two** Run Configurations.

* the Kick Assembler Run Configuration (that compiles the code and generates the prg-file)
* a separate Run Configuration which invokes the emulator (VICE)

You can chain multiple Run Configurations together and have them executed in sequence.

However, IntelliJ only lets you choose other configs to be run **before** launch (not after) the actual Run Configuration.
So the second Run Configuration will have to be the main config we're working with.

Step-by-step example:

1. Create your Kick Assembler Run Configuration as desired.
2. Then create a tiny helper shell-script which will ...
    * find and kill any currently running x64-emulator instances
    * determine the latest prg-file in your working directory
    * and run it with x64.
    
    e.g.
    ```
    #!/bin/bash
    killall x64 &> /dev/null; x64 +confirmonexit $(ls -1t *.prg | head -n1)
    ```
   (You can save this script wherever you like; for reuse in other projects.)
3. Now create another Run Configuration. This time a [Shell Script Run Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration-shell-script.html).

    Here you'll run the above script and add the Kick Assembler Run Configuration to the "before launch"-section.
    
    ![shell_script_run_config](/screenshots/shell_script_run_config.png?raw=true)
    
    (Make sure your working-directory is identical to to prg-file's location!)

That's about it; a one-click "build n' run".   

## Credits
* [Kick Assembler](http://theweb.dk/KickAssembler) created by Mads Nielsen
* _Sunglasses_ icon made by [Yannick](https://www.flaticon.com/authors/yannick) ([www.flaticon.com](www.flaticon.com)) 

## Author
:email: [Achim Seufert](mailto:kickassembler-acbg@achimonline.de)

## License
Please read the [license](license) file.

![Code 6502](/src/main/resources/code_6502_587x60.png?raw=true)
