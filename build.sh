#!/usr/bin/env bash
cd MovieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package
docker build -t user-app .
cd ..
cd MovieCruiserService
source ./env-variable.sh
mvn clean package
docker build -t movie-app .
cd ..

