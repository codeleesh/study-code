![PostgreSQL](./images/PostgreSQL.png)



## MacOS PostgreSQL 설치



### 설치 진행

#### 사전 준비

```bash
brew search postgresql
```

```bash
(base) lsh@isanghoui-MacBookPro ~ % brew search postgresql
==> Formulae
postgresql      postgresql@10   postgresql@11   postgresql@12   postgresql@9.4  postgresql@9.5  postgresql@9.6  qt-postgresql
==> Casks
navicat-for-postgresql
```



#### 설치

```bash
brew install postgresql
```



#### 설치 확인

```bash
postgres --version
```

```bash
(base) lsh@isanghoui-MacBookPro ~ % postgres --version
postgres (PostgreSQL) 13.3
```



### 서비스 

#### 시작

```bash
pg_ctl -D /usr/local/var/postgres start && brew services start postgresql
```

```bash
(base) lsh@isanghoui-MacBookPro postgres % pg_ctl -D /usr/local/var/postgres start && brew services start postgresql
waiting for server to start....2021-07-14 22:18:35.758 KST [90613] LOG:  starting PostgreSQL 13.3 on x86_64-apple-darwin20.4.0, compiled by Apple clang version 12.0.5 (clang-1205.0.22.9), 64-bit
2021-07-14 22:18:35.759 KST [90613] LOG:  listening on IPv6 address "::1", port 5432
2021-07-14 22:18:35.759 KST [90613] LOG:  listening on IPv4 address "127.0.0.1", port 5432
2021-07-14 22:18:35.761 KST [90613] LOG:  listening on Unix socket "/tmp/.s.PGSQL.5432"
2021-07-14 22:18:35.765 KST [90614] LOG:  database system was shut down at 2021-07-14 22:18:22 KST
2021-07-14 22:18:35.769 KST [90613] LOG:  database system is ready to accept connections
 done
server started
```



#### 종료

```bash
pg_ctl -D /usr/local/var/postgres stop && brew services stop postgresql
```

```bash
(base) lsh@isanghoui-MacBookPro postgres % brew services stop postgresql
Stopping `postgresql`... (might take a while)
==> Successfully stopped `postgresql` (label: homebrew.mxcl.postgresql)
```



### 접속 테스트

```bash
psql postgres
```

```bash
(base) lsh@isanghoui-MacBookPro postgres % psql postgres
psql (13.3)
Type "help" for help.
```



### database 및 user 생성

#### database

```sql
CREATE DATABASE {database} ENCODING 'utf-8';
```

```bash
postgres=# CREATE DATABASE testdb ENCODING 'utf-8';
CREATE DATABASE
```



#### user

```sql
CREATE USER {user} PASSWORD {'password'};
```

```bash
postgres=# create user admin password 'passwd';
CREATE ROLE
```



#### database 소유권 user에게 주기

```sql
ALTER DATABASE {database} OWNER TO {user};
```

```bash
postgres=# alter database testdb owner to admin;
ALTER DATABASE
```



#### user 권한 주기

```sql
GRANT ALL ON DATABASE {database} TO {user} WITH GRANT OPTION;
```

```bash
postgres=# grant all on database testdb to admin with grant option;
GRANT
```



### user 접속 테스트

```sql
psql –U {user} –d {password}
```



### Error

- 서비스가 정상적으로 중단되지 않아서 발생한 문제
- 시작과 종료 시 `pg_ctl`과 `brew` 동일하게 진행했는지 체크

```
(base) lsh@isanghoui-MacBookPro ~ % pg_ctl -D /usr/local/var/postgres start && b
rew services start postgresql
pg_ctl: another server might be running; trying to start server anyway
waiting for server to start....2021-07-18 15:33:40.353 KST [24135] FATAL:  lock file "postmaster.pid" already exists
2021-07-18 15:33:40.353 KST [24135] HINT:  Is another postmaster (PID 90613) running in data directory "/usr/local/var/postgres"?
```

```
(base) lsh@isanghoui-MacBookPro postgres % ps -ef | grep postgres
  501 24095 90613   0  3:33PM ??         0:00.01 postgres: admin testdb 127.0.0.1(57157) idle
  501 24097 90613   0  3:33PM ??         0:00.03 postgres: admin testdb 127.0.0.1(57158) idle
  501 24098 90613   0  3:33PM ??         0:00.00 postgres: admin testdb 127.0.0.1(57159) idle
  501 90613     1   0 수10PM ??         0:02.24 /usr/local/Cellar/postgresql/13.3/bin/postgres -D /usr/local/var/postgres
```



### 참고

- https://neulpeumbomin.tistory.com/9