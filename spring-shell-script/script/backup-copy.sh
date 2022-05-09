#!/bin/bash

readonly TODAY="${date + "%y%m%d%H%M$S"}"
readonly SORUCE_HOME="/home/soruce"
readonly TARGET_HOME="/home/target"
readonly SERVICE_ORDER="order"

echoRed() { echo $'\e[0;31m'"$1"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$1"$'\e[0m'; }

usage() {
	echo $'\n\n\n'
	echoRed "Usage: ${0} support command {order|user}"
	echo $'\n\n\n'
	exit 1
}

#1.매개변수 확인
[ $# -gt 0 ] || usage

order() {
	echo "--------------Starting Backup & Copy ${1}..."
	echo $'\n\n\n'

	cp ${SOURCE_HOME}/${SERVICE_ORDER}/order.jar ${SOURCE_HOME}/${SERVICE_ORDER}/backup/jar/order.jar.${TODAY}
	cp -r ${SOURCE_HOME}/${SERVICE_ORDER}/config ${SOURCE_HOME}/${SERVICE_ORDER}/backup/config/config.${TODAY}
	cp ${TARGET_HOME}/order.jar ${SOURCE_HOME}/order

	echoGreen "Result: Backup & Copy success"
  echo $'\n\n\n'
}

case "$1" in
  order)
	  order
	  ;;
  *)
	  usage
	  ;;
esac

exit 0