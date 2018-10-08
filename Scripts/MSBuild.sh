#!/bin/sh

MS_FOLDER=sonar-scanner-msbuild-4.3.1.1372-netcoreapp2.0

chmod +x ./$MS_FOLDER/ -R

#ls /usr/bin/mono/
#mono ./$MS_FOLDER/SonarScanner.MSBuild.exe begin /k:"FlowTest" 
#xbuild CIEN_TutoProject_Stone.sln
#mono ./$MS_FOLDER/SonarScanner.MSBuild.exe end


dotnet ./$MS_FOLDER/SonarScanner.MSBuild.dll begin /k:"FlowTest"
#dotnet build -f netcoreapp2.0 CIEN_TutoProject_Stone.sln
dotnet msbuild /p:TargetFramework=net35 Assembly-CSharp.csproj 
dotnet ./$MS_FOLDER/SonarScanner.MSBuild.dll end