jdeps dist\FuzzyWorld1.jar
pause
jlink --compress=2 --module-path "C:/Program Files/OpenJDK/jdk-17.0.2/jmods" --add-modules java.base,java.desktop,java.logging --output jre-min
pause
