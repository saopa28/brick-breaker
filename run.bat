@echo off
REM Builds and runs Brick Breaker.
REM Needs a JDK 17+ and the OpenJFX 21 jars. Override JAVA_HOME / FX_DIR if yours live elsewhere.
setlocal
if "%JAVA_HOME%"=="" set "JAVA_HOME=%USERPROFILE%\.jdks\openjdk-21.0.1"
if "%FX_DIR%"=="" set "FX_DIR=%USERPROFILE%\.m2\repository\org\openjfx"
set "FXV=21-ea+24"
set "MP=%FX_DIR%\javafx-base\%FXV%\javafx-base-%FXV%-win.jar;%FX_DIR%\javafx-graphics\%FXV%\javafx-graphics-%FXV%-win.jar;%FX_DIR%\javafx-controls\%FXV%\javafx-controls-%FXV%-win.jar"

cd /d "%~dp0"
if exist build rmdir /s /q build
mkdir build
"%JAVA_HOME%\bin\javac" --module-path "%MP%" --add-modules javafx.controls -d build src\sample\*.java || exit /b 1
xcopy /e /i /q src\sample\assets build\sample\assets >nul
copy /y src\sample\style.css build\sample\ >nul

"%JAVA_HOME%\bin\java" --module-path "%MP%" --add-modules javafx.controls -cp build sample.Main
