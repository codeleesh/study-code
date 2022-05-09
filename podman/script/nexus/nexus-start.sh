#!/bin/bash

DATA_PATH=/home/data/nexus
LOG_PATH=/home/log/nexus
CONFIG_PATH=/home/program/nexus/config

podman run -d
        --name nexus
        --rm --user root:root
        -p 8071:3000
        -v ${DATA_PATH}:/nexus-data:z
        -v ${LOG_PATH}:/var/log/nexus:z
        -v ${CONFIG_PATH}:/etc/nexus:z
        docker.io/sonatype/nexus3:latest