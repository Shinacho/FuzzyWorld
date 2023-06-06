del /Q /S jre-min\*
rmdir /Q /S jre-min\
set CLASSPATH="D:/Project/kinugasa/lib/"
jdeps --module-path "C:/Program Files/OpenJDK/jdk-17.0.2/jmods;D:/Project/kinugasa/lib/;D:/Project/kinugasa/dist;" --multi-release 10 dist\FuzzyWorld1.jar
pause
jlink --verbose --compress=2  --module-path "C:/Program Files/OpenJDK/jdk-17.0.2/jmods/;D:/Project/kinugasa/dist/;" --add-modules kinugasa,java.base,java.desktop,java.logging,java.sql --output jre-min
pause
