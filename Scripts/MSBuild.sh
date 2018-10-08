#!/bin/sh

MS_FOLDER=sonar-scanner-msbuild-4.4.0.1504-net46
WRAPPER=build-wrapper-win-x86
chmod +x ./$MS_FOLDER/ -R

#ls /usr/bin/mono/
#mono ./$MS_FOLDER/SonarScanner.MSBuild.exe begin /k:"FlowTest" 
#xbuild CIEN_TutoProject_Stone.sln
#mono ./$MS_FOLDER/SonarScanner.MSBuild.exe end

pwd
./$MS_FOLDER/SonarQube.Scanner.MSBuild.exe begin /k:"SibaDoge1_FlowTest" /d:sonar.organization="sibadoge1-github" /d:sonar.cfamily.build-wrapper-output=bw-output /d:sonar.host.url="https://sonarcloud.io" /d:sonar.login="$SONAR_TOKEN"
./$WRAPPER/build-wrapper-win-x86-64.exe --out-dir bw-output MsBuild.exe /t:Rebuild
./$MS_FOLDER/SonarQube.Scanner.MSBuild.exe end /d:sonar.login="$SONAR_TOKEN"