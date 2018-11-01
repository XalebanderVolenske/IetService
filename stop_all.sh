#!/usr/bin/env bash
docker stop iet_service
docker stop iet_mariadb
docker rm iet_service
docker rm iet_mariadb