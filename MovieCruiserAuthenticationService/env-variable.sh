#!/usr/bin/env bash
export MYSQL_DATABASE=movieDb
export MYSQL_USER=poc
export MYSQL_PASSWORD=poc
if [[ -z "${MYSQL_CI_URL}" ]]; then
export MYSQL_CI_URL=jdbc:mysql://localhost:3306/movieDb
fi
