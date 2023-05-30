echo off
echo Warning: Pressing any key will delete all DB files
echo All save data will be lost.
set /p input=Are you sure you want to run it? [y/n]
if "%input%"=="y" (
	del .\resource\data\*.db
	pause
) else (
	pause
)
