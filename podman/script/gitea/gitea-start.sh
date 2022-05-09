#!/bin/bash

DATA_PATH=/home/data/gitea
LOG_PATH=/home/log/gitea
CONFIG_PATH=/home/program/gitea/config

podman run -d
        -p 8061:3000
        -p 2222:22
        --name gitea
        --restart always
        -v ${DATA_PATH}:/data:z
        -v /etc/localtime:/etc/localtime:ro
        docker.io/gitea/gitea:latest

