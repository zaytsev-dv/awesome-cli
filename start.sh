#!/bin/bash
DIRECTORY=target
JAR_NAME=target/awesome-cli.jar

if [[ -d "$DIRECTORY" ]]
then
    echo "$DIRECTORY exists on your filesystem."
    echo "check Jar exist"
            if [[ -f "$JAR_NAME" ]]
then
    echo "$JAR_NAME exists on your filesystem."
    cd target
    java -jar awesome-cli.jar
else echo mvnw package
            fi
else ./mvnw clean package
  cd target
 java -jar awesome-cli.jar
fi



