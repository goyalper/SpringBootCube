#!/bin/bash

echo "Building SpringBootCube application..."
mvn clean package -DskipTests

echo "Running SpringBootCube application..."
java -jar target/springbootcube-0.0.1-SNAPSHOT.jar
