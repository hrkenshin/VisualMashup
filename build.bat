@echo off

set profile=%1
if ""%1""=="""" set "profile=release"

echo.
echo ##### source update .....
echo.
svn update

echo.
echo ##### maven build .....
echo.
mvn -Dmaven.test.skip=true -Dmode=%profile% clean package