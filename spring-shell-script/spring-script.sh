#!/bin/bash

readonly APP_HOME=$(pwd)
readonly LOG_DIR="${APP_HOME}/logs"
readonly PID_FILE="${APP_HOME}/app.pid"
readonly PROC_NAME="api-server"
readonly PROC_JAR="api-server.jar"
readonly APP_NAME=${APP_JAR##*/}
readonly APP_NAME=${APP_NAME%.jar}
readonly PROFILES_ACTIVE="-Dspring.profiles.active=local"
readonly CONFIG_PATH="-Dspring.config.location=file:/config/application.yml"
readonly LOGBACK_PATH="-Dlogging.config=file:/config/logback-spring.xml"
readonly RUN_EXE="java -jar ${PROFILES_ACTIVE} ${CONFIG_PATH} ${LOGBACK_PATH} ${PROC_JAR}"

echoRed() { echo $'\e[0;31m'"$1"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$1"$'\e[0m'; }

usage() {
	echo $'\n\n\n'
	echoRed "Usage: ${0} support command {start|stop|restart|status}"
	echo $'\n\n\n'
	exit 1
}

psCheck() {
	echo "--------------All instances on this machine--------------"
	echo "USER       PID   %CPU %MEM VSZ    RSS    TTY   STAT  START   TIME COMMAND" && echo ""
	ps aux | grep "${PROC_NAME}" | grep -E -v "grep"
}

isRunning() {
	[[ -f "${PID_FILE}" ]] || return 1
	ps -p "$(<"${PID_FILE}")" &>/dev/null
}

#1.매개변수 확인
[ $# -gt 0 ] || usage

start() {
	echo "--------------Starting ${PROC_NAME}:"
	echo $'\n\n\n'

	# 서버 실행 확인
	if (isRunning); then
		echoYellow "Result: Running, no need to start"
		echo $'\n\n\n'
		exit 0
	fi

	# 서버 실행
	echo "-------Boot Command: "
	echo "nohup ${RUN_EXE} >/dev/null 2>${LOG_DIR}/error.log &"
	echo $'\n\n\n'

	# 오류로그 파일 생성
	mkdir -p "${LOG_DIR}" && touch "${LOG_DIR}/error.log"

	# 웹 어플리케이션 실행
	nohup ${RUN_EXE} >/dev/null 2>>"${LOG_DIR}/error.log" &

	# pid 파일에 기록
	echo $! >"${PID_FILE}"

	# 실행 실패
	sleep 2
	if (! isRunning); then
		echoRed "Result: Start failed" && rm -f "${PID_FILE}"
		echo $'\n\n\n'
		exit 1
	fi

	echoGreen "Result: Start success,Running (PID: $(<"${PID_FILE}"))"
	echo $'\n\n\n'

	# 인스턴스 확인
	psCheck
}

stop() {
	echo "--------------Stopping ${PROC_NAME}:"
	echo $'\n\n\n'

  # 서버 실행 확인
	if (! isRunning); then
		echoYellow "Result: Not running" && rm -f "${PID_FILE}"
		echo $'\n\n\n'
		return 0
	fi

  # 서버 종료
	kill -9 "$(<"${PID_FILE}")" 2>/dev/null
	# PID 파일 제거
	rm -f "${PID_FILE}"
	echoGreen "Result: Stop success"
	echo $'\n\n\n'
}

restart() {
	echo "--------------Restarting ${PROC_NAME}:"
	echo $'\n\n\n'

	if (! isRunning); then
		start
		echo $'\n\n\n'
		exit 0
	fi

	if (isRunning); then
		stop
		sleep 1
		start
		echo $'\n\n\n'
		exit 0
	fi
}

status() {
	echo "--------------Status ${PROC_NAME}:"
	echo $'\n\n\n'

  # 서버 실행 확인
	if isRunning; then
		echoGreen "Result: Running （PID: $(<"${PID_FILE}"))"
	else
		echoYellow "Result: Not running"
	fi

	echo $'\n\n\n'
	psCheck
}

case "${ACTION}" in
start)
	start
	;;
stop)
	stop
	;;
restart)
	restart
	;;
status)
	status
	;;
*)
	usage
	;;
esac

exit 0