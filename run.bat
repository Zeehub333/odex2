@echo off
setlocal enabledelayedexpansion

title Odex Open Suite - Build ^& Run (Tomcat)
color 0B

echo ==============================================================================
echo                      ODEX OPEN SUITE - TOMCAT RUNNER
echo ==============================================================================
echo.

set "SCRIPT_DIR=%~dp0"
cd /d "%SCRIPT_DIR%"

:: -----------------------------------------------------------------------------
:: 1. Java Environment Setup (Requires JDK 21+)
:: -----------------------------------------------------------------------------
echo [1/4] Checking Java Environment...

if exist "C:\AndroidDev\JDK21" (
    set "JAVA_HOME=C:\AndroidDev\JDK21"
) else if "%JAVA_HOME%"=="" (
    for /d %%D in ("C:\Program Files\Java\jdk-21*" "C:\Program Files\Eclipse Adoptium\jdk-21*") do (
        if exist "%%D" set "JAVA_HOME=%%D"
    )
)

if defined JAVA_HOME (
    set "PATH=%JAVA_HOME%\bin;%PATH%"
    echo       Using JAVA_HOME: %JAVA_HOME%
) else (
    echo       [WARN] JAVA_HOME not set. Using system PATH java.
)

java -version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    color 0C
    echo [ERROR] Java JDK is not found or not working properly.
    echo Please install JDK 21 or configure JAVA_HOME.
    pause
    exit /b 1
)

:: -----------------------------------------------------------------------------
:: 2. Axelor Webapp Runner Setup (open-suite-webapp)
:: -----------------------------------------------------------------------------
echo.
echo [2/4] Verifying Axelor Webapp Workspace...

set "WEBAPP_DIR=%SCRIPT_DIR%open-suite-webapp"
set "MODULE_TARGET=%WEBAPP_DIR%\modules\odex-open-suite"

if not exist "%WEBAPP_DIR%" (
    echo       open-suite-webapp not found locally.
    echo       Cloning official open-suite-webapp repository...
    git clone -b dev https://github.com/axelor/open-suite-webapp.git "%WEBAPP_DIR%"
    if %ERRORLEVEL% neq 0 (
        color 0C
        echo [ERROR] Failed to clone open-suite-webapp repository.
        echo Please verify your internet connection and git installation.
        pause
        exit /b 1
    )
)

if not exist "%WEBAPP_DIR%\modules" (
    mkdir "%WEBAPP_DIR%\modules" 2>nul
)

if not exist "%MODULE_TARGET%" (
    echo       Linking Odex Open Suite modules to webapp...
    mklink /J "%MODULE_TARGET%" "%SCRIPT_DIR%" >nul 2>&1
    if %ERRORLEVEL% neq 0 (
        echo       [NOTE] Direct link created or fallback directory mapping active.
    )
)

:: -----------------------------------------------------------------------------
:: 3. Database Check (ZKBioTime PostgreSQL on port 7496)
:: -----------------------------------------------------------------------------
echo.
echo [3/4] Checking Database Connectivity...
if exist "C:\ZKBioTime\pgsql\bin\psql.exe" (
    "C:\ZKBioTime\pgsql\bin\psql.exe" -h 127.0.0.1 -p 7496 -U axelor -d odex-open-suite -c "SELECT 1;" >nul 2>&1
    if !ERRORLEVEL! equ 0 (
        echo       PostgreSQL database 'odex-open-suite' connected on port 7496.
    ) else (
        echo       [INFO] Creating database on port 7496...
        "C:\ZKBioTime\pgsql\bin\createdb.exe" -h 127.0.0.1 -p 7496 -U postgres odex-open-suite >nul 2>&1
    )
)

:: -----------------------------------------------------------------------------
:: 4. Tomcat Detection
:: -----------------------------------------------------------------------------
echo.
echo [4/4] Detecting Apache Tomcat...

set "DETECTED_TOMCAT="
if defined CATALINA_HOME if exist "%CATALINA_HOME%\bin\catalina.bat" set "DETECTED_TOMCAT=%CATALINA_HOME%"
if not defined DETECTED_TOMCAT if defined TOMCAT_HOME if exist "%TOMCAT_HOME%\bin\catalina.bat" set "DETECTED_TOMCAT=%TOMCAT_HOME%"

if not defined DETECTED_TOMCAT (
    for /d %%T in ("C:\apache-tomcat*" "C:\tomcat*" "C:\Program Files\Apache Software Foundation\Tomcat*") do (
        if exist "%%T\bin\catalina.bat" set "DETECTED_TOMCAT=%%T"
    )
)

if defined DETECTED_TOMCAT (
    echo       Found Tomcat: %DETECTED_TOMCAT%
) else (
    echo       No standalone Tomcat directory detected. (Embedded Tomcat / Gradle runner will be used by default)
)

:: -----------------------------------------------------------------------------
:: Execution Menu
:: -----------------------------------------------------------------------------
echo.
echo ==============================================================================
echo Select Run / Build Mode:
echo ==============================================================================
echo [1] Build and Run on Embedded Tomcat / Server (./gradlew run) [Recommended]
echo [2] Build WAR and Deploy to Standalone Apache Tomcat
echo [3] Build WAR Package Only (./gradlew build -x test)
echo [4] Clean and Rebuild Everything
echo [5] Start Detected Apache Tomcat Directly
echo [Q] Quit
echo ==============================================================================
echo.

set /p CHOICE="Enter choice [1-5, Q] (Default: 1): "
if "%CHOICE%"=="" set CHOICE=1
if /i "%CHOICE%"=="Q" exit /b 0

cd /d "%WEBAPP_DIR%"

if "%CHOICE%"=="1" goto :MODE_EMBEDDED
if "%CHOICE%"=="2" goto :MODE_TOMCAT_DEPLOY
if "%CHOICE%"=="3" goto :MODE_BUILD_ONLY
if "%CHOICE%"=="4" goto :MODE_CLEAN_REBUILD
if "%CHOICE%"=="5" goto :MODE_START_TOMCAT

echo Invalid choice. Defaulting to Embedded Run.
goto :MODE_EMBEDDED

:: -----------------------------------------------------------------------------
:: Mode: Embedded Tomcat / Server
:: -----------------------------------------------------------------------------
:MODE_EMBEDDED
echo.
echo ==============================================================================
echo [INFO] Building and starting embedded Axelor Tomcat server...
echo [INFO] Application will be available at: http://localhost:8080
echo ==============================================================================
call gradlew.bat run
if %ERRORLEVEL% neq 0 (
    echo [ERROR] Server terminated with error code %ERRORLEVEL%.
)
pause
exit /b 0

:: -----------------------------------------------------------------------------
:: Mode: Build and Deploy to Standalone Tomcat
:: -----------------------------------------------------------------------------
:MODE_TOMCAT_DEPLOY
echo.
echo ==============================================================================
echo [INFO] Building WAR package for Apache Tomcat...
echo ==============================================================================
call gradlew.bat --stop >nul 2>&1
call gradlew.bat -x test clean build
if %ERRORLEVEL% neq 0 (
    color 0C
    echo [ERROR] Build failed! Check Gradle build errors above.
    pause
    exit /b 1
)

set "WAR_FILE="
for /f "delims=" %%F in ('dir /b /s "build\libs\*.war" 2^>nul') do set "WAR_FILE=%%F"

if "%WAR_FILE%"=="" (
    color 0C
    echo [ERROR] No .war file generated in build\libs.
    pause
    exit /b 1
)

echo.
echo Generated WAR: %WAR_FILE%

if not defined DETECTED_TOMCAT (
    if exist "C:\Program Files\Apache Software Foundation\Tomcat 11.0" (
        set "DETECTED_TOMCAT=C:\Program Files\Apache Software Foundation\Tomcat 11.0"
    ) else (
        echo.
        set /p DETECTED_TOMCAT="Enter Tomcat installation directory (e.g. C:\Program Files\Apache Software Foundation\Tomcat 11.0): "
    )
)

if not exist "%DETECTED_TOMCAT%\webapps" (
    color 0C
    echo [ERROR] Invalid Tomcat directory '%DETECTED_TOMCAT%'. Webapps folder not found.
    pause
    exit /b 1
)

echo Deploying WAR to: %DETECTED_TOMCAT%\webapps\odex-erp.war...
copy /Y "%WAR_FILE%" "%DETECTED_TOMCAT%\webapps\odex-erp.war" >nul 2>&1

echo.
echo [INFO] Deployment complete!
echo Checking Tomcat Windows Service...
sc query Tomcat11 >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo [INFO] Restarting Apache Tomcat 11 service...
    net stop Tomcat11 >nul 2>&1
    net start Tomcat11 >nul 2>&1
    echo [SUCCESS] Tomcat 11 service started.
    echo [INFO] Application URL: http://localhost:8080/odex-erp/
) else (
    echo [INFO] Starting Apache Tomcat via catalina.bat...
    set "CATALINA_HOME=%DETECTED_TOMCAT%"
    call "%DETECTED_TOMCAT%\bin\catalina.bat" run
)
pause
exit /b 0

:: -----------------------------------------------------------------------------
:: Mode: Build Only
:: -----------------------------------------------------------------------------
:MODE_BUILD_ONLY
echo.
echo ==============================================================================
echo [INFO] Building WAR package...
echo ==============================================================================
call gradlew.bat -x test build
if %ERRORLEVEL% equ 0 (
    echo.
    echo [SUCCESS] Build completed successfully.
    echo WAR location:
    dir /b /s "build\libs\*.war" 2>nul
) else (
    color 0C
    echo [ERROR] Build failed.
)
pause
exit /b 0

:: -----------------------------------------------------------------------------
:: Mode: Clean & Rebuild
:: -----------------------------------------------------------------------------
:MODE_CLEAN_REBUILD
echo.
echo ==============================================================================
echo [INFO] Cleaning workspace and rebuilding...
echo ==============================================================================
call gradlew.bat --stop >nul 2>&1
call gradlew.bat clean build -x test
echo.
echo [INFO] Starting application...
call gradlew.bat run
pause
exit /b 0

:: -----------------------------------------------------------------------------
:: Mode: Start Tomcat
:: -----------------------------------------------------------------------------
:MODE_START_TOMCAT
if not defined DETECTED_TOMCAT (
    echo.
    set /p DETECTED_TOMCAT="Enter Tomcat directory: "
)
if exist "%DETECTED_TOMCAT%\bin\catalina.bat" (
    set "CATALINA_HOME=%DETECTED_TOMCAT%"
    call "%DETECTED_TOMCAT%\bin\catalina.bat" run
) else (
    echo [ERROR] Could not find catalina.bat in "%DETECTED_TOMCAT%\bin".
)
pause
exit /b 0
