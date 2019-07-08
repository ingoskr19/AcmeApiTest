@echo off
cmd /c mvn -e clean install
IF NOT "%1"=="NOPAUSE" pause