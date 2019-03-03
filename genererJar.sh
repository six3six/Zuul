#!/usr/bin/env bash

mkdir temp

shopt -s extglob

cp !(temp/) temp -r

cd temp

javac ./*/*.java
javac ./*.java

echo -n "\nDo you want to créer la javadocs ? (Y/n) "
read answer
if echo "$answer" | grep -iq "^Y" ;then
    javadoc -d progdoc -author -version -private -linksource pkg_* *.java
    javadoc -d userdoc -author -version pkg_* *.java
    javadoc -d progdoc -author -version -private -linksource *.java
    javadoc -d userdoc -author -version *.java
fi

echo -n "\nNom du fichier : "
read name
jar cf ../"²".jar ./*.* Assets LevelDesigner progdoc
jar ufe ../"$name".jar Game

echo -n "\Voulez vous lister les fichier du jar ? (Y/n) "
read answer
if echo "$answer" | grep -iq "^Y" ;then
    #Lister les fichiers
    jar tf ../"$name".jar
else
    echo "$name.jar is ready"
fi

rm ../temp -rf