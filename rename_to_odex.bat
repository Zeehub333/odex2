@echo off
setlocal enabledelayedexpansion

title ODEX - Batch Rebranding Tool (Axelor to Odex)
color 0A

echo ==============================================================================
echo              RENAME ALL FILES, FOLDERS AND CONTENTS TO ODEX
echo ==============================================================================
echo.

cd /d "%~dp0"

:: Check for Python
set "PYTHON_EXEC="
if exist "C:\ZKBioTime\Python311\python.exe" (
    set "PYTHON_EXEC=C:\ZKBioTime\Python311\python.exe"
) else (
    where python >nul 2>&1
    if !ERRORLEVEL! equ 0 set "PYTHON_EXEC=python"
)

if not defined PYTHON_EXEC (
    color 0C
    echo [ERROR] Python was not found in PATH or C:\ZKBioTime\Python311.
    pause
    exit /b 1
)

echo [INFO] Running refactoring script with !PYTHON_EXEC!...
"!PYTHON_EXEC!" "%~dp0refactor_axelor_to_odex.py"

echo.
pause
