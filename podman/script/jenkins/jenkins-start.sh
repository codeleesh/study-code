#!/bin/bash

DATA_PATH=/home/data/jenkins
LOG_PATH=/home/log/jenkins
CONFIG_PATH=/home/program/jenkins/config

podman run -d
        --name jenkins
        -u root:root
        --rm -- privileged --user root:root
        -p 8062:3000
        --env-file ${CONFIG_PATH}/env.list
        -v ${DATA_PATH}:/var/jenkins:z
        -v ${LOG_PATH}:/var/log/jenkins:z
        -v ${CONFIG_PATH}/root:/root:z
        docker.io/jenkinsci/blueocean:latest