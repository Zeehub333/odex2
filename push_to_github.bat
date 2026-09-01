@echo off
setlocal enabledelayedexpansion

title ODEX - Push to GitHub (https://github.com/Zeehub333/odex2)
color 0A

echo ==============================================================================
echo              PUSH ODEX 2.0 TO GITHUB REPOSITORY
echo              Target: https://github.com/Zeehub333/odex2.git
echo ==============================================================================
echo.

cd /d "%~dp0"

set "GIT_EXE=C:\Program Files\Git\cmd\git.exe"
if not exist "%GIT_EXE%" (
    where git >nul 2>&1
    if %ERRORLEVEL% equ 0 (
        set "GIT_EXE=git"
    ) else (
        color 0C
        echo [ERROR] Git is not installed or not found.
        pause
        exit /b 1
    )
)

echo [1/3] Checking Git status...
"%GIT_EXE%" status

echo.
echo [2/3] Adding and committing any pending changes...
"%GIT_EXE%" add -A
"%GIT_EXE%" commit -m "Update Odex 2.0 branding, logo, and module refactoring" >nul 2>&1

echo.
echo [3/3] Pushing to origin main...
echo If prompted, please sign in via browser or enter your GitHub Personal Access Token (PAT).
echo.
"%GIT_EXE%" push -u origin main

if %ERRORLEVEL% equ 0 (
    echo.
    echo ==============================================================================
    echo  [SUCCESS] Code successfully pushed to https://github.com/Zeehub333/odex2!
    echo ==============================================================================
) else (
    echo.
    echo [NOTE] If authentication failed:
    echo 1. Generate a Personal Access Token (classic) on GitHub with 'repo' scope:
    echo    https://github.com/settings/tokens
    echo 2. When prompted for password, paste your GitHub Personal Access Token.
)

echo.
pause
