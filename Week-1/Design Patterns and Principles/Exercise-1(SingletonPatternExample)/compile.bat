@echo off
echo Compiling Singleton Pattern Example...
echo.

:: Create output directory if it doesn't exist
if not exist "bin" mkdir bin

:: Compile all Java files
javac -d bin src/main/java/com/example/singleton/*.java

if %ERRORLEVEL% == 0 (
    echo Compilation successful!
    echo.
    echo Running the application...
    echo =====================================
    java -cp bin com.example.singleton.Main
) else (
    echo Compilation failed!
    echo Please check for syntax errors.
)

echo.
pause 