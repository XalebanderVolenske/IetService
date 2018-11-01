#!/usr/bin/env bash
/opt/apache-maven/bin/mvn clean install
mv -v target/ietservice.war ./docker/deploy/customization/
