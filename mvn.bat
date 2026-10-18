@echo off
REM Script de conveniencia para ejecutar Maven en este proyecto
set "IDEA_MAVEN=F:\Programas\IntelliJ IDEA Community Edition 2025.2.6\plugins\maven\lib\maven3\bin\mvn.cmd"
if exist "%IDEA_MAVEN%" (
    "%IDEA_MAVEN%" %*
) else (
    mvn %*
)
