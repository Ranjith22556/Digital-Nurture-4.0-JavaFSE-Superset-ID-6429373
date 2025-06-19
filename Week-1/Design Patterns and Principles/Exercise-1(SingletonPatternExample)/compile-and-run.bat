@echo off
echo ===============================================
echo    Singleton Pattern Example - Java Project
echo ===============================================
echo.

:: Create output directory if it doesn't exist
if not exist "bin" mkdir bin

:: Compile all Java files with Java 8 compatibility
echo [1/3] Compiling Java files...
javac -source 8 -target 8 -d bin src/main/java/com/example/singleton/*.java

if %ERRORLEVEL% == 0 (
    echo [2/3] Compilation successful!
    echo.
    echo [3/3] Running the application...
    echo =====================================
    java -cp bin com.example.singleton.Main
    echo.
    echo =====================================
    echo Application completed successfully!
) else (
    echo [ERROR] Compilation failed!
    echo Please check for syntax errors.
)

echo.
echo Press any key to exit...
pause > nul 