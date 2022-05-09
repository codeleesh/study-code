# Shell Script

## spring-script script

### Start / Stop / Restart / Status 

웹 어플리케이션을 실행하기 위해서는 다음 명령어를 이용하면 됩니다.
```shell
./spring-script.sh start
```

웹 어플리케이션을 중지하기 위해서는 다음 명령어를 이용하면 됩니다.
```shell
./spring-script.sh stop
```

웹 어플리케이션을 재시작하기 위해서는 다음 명령어를 이용하면 됩니다.
```shell
./spring-script.sh restart
```

웹 어플리케이션의 상태를 확인하기 위해서는 다음 명령어를 이용하면 됩니다.
```shell
./spring-script.sh status
```

## backup-copy script

- 기존 어플리케이션의 `jar` 파일과 `config` 파일을 백업
- 빌드된 어플리케이션의 `jar` 파일 이동
- 파라미터로 해당 서비스 이름을 추가하여 진행

```shell
./backup-copy.sh order
```
