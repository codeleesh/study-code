#!/bin/bash

APP_HOME=$(pwd)
LOG_DIR="${APP_HOME}/logs"
PID_FILE="${APP_HOME}/app.pid"
APP_JAR="$(find "${APP_HOME}" -name "*.jar" 2>/dev/null | head -n 1)"
APP_NAME=${APP_JAR##*/}
APP_NAME=${APP_NAME%.jar}
ACTION=$1

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
	ps aux | grep "$APP_NAME" | grep -E -v "grep"
}

isRunning() {
	[[ -f "$PID_FILE" ]] || return 1
	ps -p "$(<"$PID_FILE")" &>/dev/null
}

#1.매개변수 확인
[ $# -gt 0 ] || usage

BASE_ARGS="--spring.profiles.active=$PROFILES_ACTIVE"

RUN_EXE="$JAVA_EXE $JVM_ARGS -jar $APP_JAR"

start() {
	echo "--------------Starting $APP_NAME:"
	echo $'\n\n\n'

	# 서버 실행 확인
	if (isRunning); then
		echoYellow "Result: Running, no need to start"
		echo $'\n\n\n'
		exit 0
	fi

	# 서버 실행
	echo "-------Boot Command: "
	echo "nohup $RUN_EXE >/dev/null 2>${LOG_DIR}/error.log &"
	echo $'\n\n\n'

	# 오류로그 파일 생성
	mkdir -p "$LOG_DIR" && touch "${LOG_DIR}/error.log"

	# 웹 어플리케이션 실행
	nohup $RUN_EXE >/dev/null 2>>"${LOG_DIR}/error.log" &

	# pid 파일에 기록
	echo $! >"$PID_FILE"

	# 실행 실패
	sleep 2
	if (! isRunning); then
		echoRed "Result: Start failed" && rm -f "$PID_FILE"
		echo $'\n\n\n'
		exit 1
	fi

	echoGreen "Result: Start success,Running (PID: $(<"$PID_FILE"))"
	echo $'\n\n\n'

	# 인스턴스 확인
	psCheck
}

stop() {
	echo "--------------Stopping $APP_NAME:"
	echo $'\n\n\n'

  # 서버 실행 확인
	if (! isRunning); then
		echoYellow "Result: Not running" && rm -f "$PID_FILE"
		echo $'\n\n\n'
		return 0
	fi

  # 서버 종료
	kill "$(<"$PID_FILE")" 2>/dev/null
	# PID 파일 제거
	rm -f "$PID_FILE"
	echoGreen "Result: Stop success"
	echo $'\n\n\n'
}

status() {
	echo "--------------Status $APP_NAME:"
	echo $'\n\n\n'

  # 서버 실행 확인
	if isRunning; then
		echoGreen "Result: Running （PID: $(<"$PID_FILE"))"
	else
		echoYellow "Result: Not running"
	fi

	echo $'\n\n\n'
	psCheck
}

case "$ACTION" in
start)
	start
	;;
stop)
	stop
	;;
restart)
	stop
	start
	;;
status)
	status
	;;
*)
	usage
	;;
esac

exit 0