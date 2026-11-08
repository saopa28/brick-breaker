# Brick Breaker 🧱
Brick Breaker written in ***Java*** and ***JavaFX***


### Description
**Engine.java** is simple game engine that handles physics and rendering and timing concurrently

----------


**LoadSave.java** you can save game and load that session in custom file structure with **.mdds** extention file

----------

**Block.java** and **Choco.java** is For Drawing and Logic Brick and Chocolates


## How to run

### Requirements
- **JDK 17 or newer** (tested with JDK 21) — e.g. [Eclipse Temurin](https://adoptium.net/)
- **JavaFX SDK 21** — download it for your OS from [gluonhq.com/products/javafx](https://gluonhq.com/products/javafx/) and unzip it somewhere (e.g. `C:\javafx-sdk-21`)

### Windows (quick start)
Point the script at your JDK and the JavaFX SDK's `lib` folder, then run it:

```bat
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21
set FX_LIB=C:\javafx-sdk-21\lib
run.bat
```

`run.bat` compiles everything into `build/`, copies the images and stylesheet next to the classes, and starts the game.

### Any OS (manual)
From the project folder, replacing `/path/to/javafx-sdk-21/lib` with your own path (on Windows use `;` instead of `:` in `-cp`):

```bash
mkdir -p build/sample
javac --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls -d build src/sample/*.java
cp -r src/sample/assets src/sample/style.css build/sample/
java --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls -cp build sample.Main
```

### IntelliJ IDEA
1. Open the project folder and set the Project SDK to JDK 17+.
2. *File → Project Structure → Libraries → + Java*, and add the JavaFX SDK `lib` folder.
3. Create an Application run configuration with main class `sample.Main` and VM options:
   `--module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls`

### Controls
| Key | Action |
| --- | --- |
| ← / → | Move the paddle |
| S | Save the game |

Saved games are stored in `brick-breaker-save/save.mdds` in your home folder; use **Load Game** on the start screen to continue.

**Special bricks:** chocolate bricks drop a treat worth +3 if you catch it, heart bricks give an extra life, and star bricks turn the ball gold for 5 seconds (no lives lost while gold).


