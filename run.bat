@echo off
chcp 65001 >nul
set "JAVA_BIN=C:\Program Files\Microsoft\jdk-17.0.19.10-hotspot\bin"
set "PATH=%JAVA_BIN%;%PATH%"
cd /d "%~dp0"

"%JAVA_BIN%\javac.exe" -encoding UTF-8 HelloBiodome01.java
if errorlevel 1 (
    echo 컴파일 실패
    pause
    exit /b 1
)

"%JAVA_BIN%\java.exe" -Dfile.encoding=UTF-8 HelloBiodome01 %*
pause
