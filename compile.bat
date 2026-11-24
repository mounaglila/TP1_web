@echo off
setlocal enabledelayedexpansion
REM Compile toutes les classes de TP1 (Java 17, utilise par Tomcat).
cd /d "%~dp0"

set "SERVLET_API=C:\apache-tomcat-9.0.96\lib\servlet-api.jar"
set "OUT=WEB-INF\classes"
set "LIST=WEB-INF\sources.txt"

REM 1. Nettoyage : supprime les anciens .class (evite les doublons de listeners)
if exist "%OUT%" rmdir /s /q "%OUT%"
mkdir "%OUT%"

REM 2. Liste des sources, chaque chemin entre guillemets avec des /
if exist "%LIST%" del "%LIST%"
for /r "WEB-INF\src" %%f in (*.java) do (
  set "p=%%f"
  set "p=!p:\=/!"
  echo "!p!">> "%LIST%"
)

REM 3. Compilation
javac --release 17 -encoding UTF-8 -cp "%SERVLET_API%" -d "%OUT%" @"%LIST%"
if errorlevel 1 (
  echo.
  echo *** ERREUR DE COMPILATION : lisez les messages ci-dessus ***
) else (
  echo.
  echo Compilation reussie. Redemarrez Tomcat pour prendre en compte les changements.
)
pause