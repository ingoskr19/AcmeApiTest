@ECHO OFF
cmd /c mvn clean install
del /Q /F "/logs/ibmtest/"
cmd /c java -Dapp.name=ibmtest -Xms128M -Xmx256M -jar .\target\ibm-0.0.1-SNAPSHOT.jar
pause