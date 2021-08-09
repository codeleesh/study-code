### Spring-Data-JPA-Bulk-Insert



#### 설정

- jdk 1.8
- Spring Boot 2.5.2
- Spring Data Jpa
- Database : `Postgresql`



#### 테스트 목적

- 테이블 Primary Key 채번 방식을 통해 성능 비교

- JPA에서는 **@Id**와 함께 **@GeneratedValue(sterategy)** 어노테이션을 통해 **대체키 방식**을 제공하고 있다.

  |        **생성 전략**        |                           **설명**                           |
    | :-------------------------: | :----------------------------------------------------------: |
  |   GenerationType.**AUTO**   |           JPA 구현체 (ex: Hibernate) 가 결정한다.            |
  | GenerationType.**IDENTIFY** |    데이터베이스에 위임한다. (ex: MySQL : auto-increment)     |
  | GenerationType.**SEQUENCE** | 데이터베이스 시퀀스를 활용하여 생성한다. (시퀀스를 지원하는 DBMS에서만 가능) |
  |  GenerationType.**TABLE**   |        키 생성 전용 테이블을 따로 만들어서 활용한다.         |



- `GenerationType.IDENTITY`

  - UserIdentity.java

    ```java
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    ```

  - 테이블 생성

    ```sql
    CREATE TABLE TN_USER_IDENTITY (
        id serial primary key,
        name varchar(255),
        created_date timestamp,
        modified_date timestamp
    );
    ```

- `GenerationType.SEQUENCE`

  - UserSequence.java

    ```java
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
    ```

  - 테이블 생성

    ```sql
    CREATE TABLE TN_USER_SEQUENCE (
        id bigserial primary key,
        name varchar(255),
        created_date timestamp,
        modified_date timestamp
    );
    ```

  - application.yml

    ```
    #Database
    #Database 설정 필요
    spring:
      datasource:
        url: jdbc:postgresql://127.0.0.1:5432/testdb?rewriteBatchedInserts=true
        username: admin
        password: passwd
    
        #Connection Pool
        hikari:
          connection-timeout: 20000
          maximum-pool-size: 10
    
      #JPA
      jpa:
        database: POSTGRESQL
        database-platform: org.hibernate.dialect.PostgreSQL95Dialect
        show-sql: false
        hibernate:
          use-new-id-generator-mappings: false
          ddl-auto: create
    ```



- Test Source

  ```java
      @Test
      public void 대량_저장() {
  
          StopWatch stopWatch = new StopWatch();
          stopWatch.start();
  
          List<User> userList = new ArrayList<>();
          for (int i = 0; i < 10; i++) {
              User user = new User();
              userList.add(user);
          };
          springDataJpaUserRepository.saveAll(userList);
  
          stopWatch.stop();
          long totalTimeMillis = stopWatch.getTotalTimeMillis();
          System.out.println("total time : " + totalTimeMillis);
      }
  ```



#### 테스트 결과

- `GenerationType.IDENTITY`
  - 10건 생성 시
    - saveAll
      - total time : 0.082 초
    - saveAll(@Transactional)
      - total time : 0.060 초
  - 1000000건 생성 시
    - saveAll
      - total time : 84.372 초
    - saveAll(@Transactional)
      - total time : 82.045 초

- `GenerationType.SEQUENCE`
  - 10건 생성 시
    - saveAll
      - total time : 0.087 초
    - saveAll(@Transactional)
      - total time : 0.059 초
  - 1000000건 생성 시
    - saveAll
      - total time : 60.343 초
    - saveAll(@Transactional)
      - total time : 14.025 초



#### 후속 테스트

- `jdbc.batch_size` 설정

  ```yaml
    #JPA
    jpa:
      database: POSTGRESQL
      database-platform: org.hibernate.dialect.PostgreSQL95Dialect
      show-sql: false
      hibernate:
        use-new-id-generator-mappings: false
        ddl-auto: none
      properties:
        hibernate:
          jdbc.batch_size: 100
          order_inserts: true
          order_updates: true
  ```

- jdbc.batch_size = 50
- `GenerationType.IDENTITY`
  - 1000000건 생성 시
    - saveAll
      - total time : 83.647 초
    - saveAll(@Transactional)
      - total time : 85.979 초
- `GenerationType.SEQUENCE`
  - 1000000건 생성 시
    - saveAll
      - total time : 26.214 초
    - saveAll(@Transactional)
      - total time : 7.838 초



- jdbc.batch_size = 100
- `GenerationType.IDENTITY`
  - 1000000건 생성 시
    - saveAll
      - total time : 84.372 초
    - saveAll(@Transactional)
      - total time : 85.979 초
- `GenerationType.SEQUENCE`
  - 1000000건 생성 시
    - saveAll
      - total time : 26.122 초
    - saveAll(@Transactional)
      - total time : 7.838 초



- jdbc.batch_size = 300
- `GenerationType.IDENTITY`
  - 1000000건 생성 시
    - saveAll
      - total time : 84.372 초
    - saveAll(@Transactional)
      - total time : 83.530 초
- `GenerationType.SEQUENCE`
  - 1000000건 생성 시
    - saveAll
      - total time : 24.988 초
    - saveAll(@Transactional)
      - total time : 8.861 초



- jdbc.batch_size = 500
- `GenerationType.IDENTITY`
  - 1000000건 생성 시
    - saveAll
      - total time : 84.372 초
    - saveAll(@Transactional)
      - total time : 83.530 초
- `GenerationType.SEQUENCE`
  - 1000000건 생성 시
    - saveAll
      - total time : 25.469 초
    - saveAll(@Transactional)
      - total time : 7.905 초



#### 마치며

백만건 이상의 데이터를 대량으로 Insert하는 내용이 필요해서 정리하였는데, 정리하면서 많은 내용을 알게 되었다.

