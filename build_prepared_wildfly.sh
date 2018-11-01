#!/usr/bin/env bash
cd ./docker/wildfly
docker rmi prepared_wildfly:latest
docker build -t prepared_wildfly .
cd ../..