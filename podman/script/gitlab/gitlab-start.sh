#!/bin/bash

DATA_PATH=/home/data/gitlab
LOG_PATH=/home/log/gitlab
CONFIG_PATH=/home/program/gitlab/config

podman run -d
        -p 8061:3000
        --hostname 172.30.5.240
        --name gitlab
        --restart always
        -v ${DATA_PATH}:/data:z
        -v ${LOG_PATH}:/var/log/gitlab:z
        -v ${CONFIG_PATH}:/etc/gitlab:z
        docker.io/gitlab/gitlab-ce:latest

