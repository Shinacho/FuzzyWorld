rem 本番用
rem jre-min\bin\javaw.exe -server -Xms2048m -Xmx2048m -ea -Dsun.java2d.opengl=True -jar .\dist\FuzzyWorld1.jar
rem テスト用
jre-min\bin\java.exe -server -Xms2048m -Xmx2048m -ea -Dsun.java2d.opengl=True -Dfile.encoding=UTF-8 -jar .\dist\FuzzyWorld1.jar
pause