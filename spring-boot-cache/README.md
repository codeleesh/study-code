# Spring Boot Cache 적용

- 회사에서 Spring Boot Cache를 활용하여 적용할 기회가 생겨서 진행한 내용을 공유드립니다.
- 버전 3.1 이후 Spring Framework는 기존 Spring 애플리케이션에 캐싱을 추가할 수 있도록 지원합니다. 
- 캐싱 추상화를 통해 코드에 미치는 영향을 최소화하면서 다양한 캐싱 솔루션을 일관되게 사용할 수 있습니다.

## 캐시(cache)란?
프로그램이 수행될 때 나타나는 지역성을 이용해 메모리나 디스크에서 사용되었던 내용을 빠르게 접근할 수 있는 곳에 보관하고 관리함으로써 두 번째 접근 부터는 보다 빠르게 참조하도록 하는 것입니다.
```
하드디스크는 용량이 아주 크지만 속도가 느리고, 메인 메모리(RAM)의 용량은 1/100정도로 작지만 속도는 십만배 정도 빠르다. 캐시 메모리는 메인 메모리(RAM)의 1/100 정도 용량이지만 속도는 훨씬 빠르다.
```
즉, 사용되었던 데이터는 다시 사용되어질 가능성이 높다는 개념을 이용하였습니다.
다시 사용될 확률이 높은 것은 더 빠르게 접근 가능한 저장소를 사용한다는 개념입니다.

### 사용 예

- 동일한 데이터를 반복적으로 제공해야하는 경우
- 데이터의 변경주기가 빈번하지 않고, 처리 시간이 오래걸리는 경우

## 개발 환경

- Java : 1.8
- Spring Boot : 2.6.2

Spring에서 제공하여 주는 캐시 종류는 9가지가 있습니다. 지원 목록은 다음 내용에서 확인할 수 있습니다.
- [Spring 지원 캐시 종류](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html#boot-features-caching-provider)

오늘은 가장 간단하게 구현할 수 있는 `simple`캐시를 통해서 실습을 진행해보도록 하겠습니다.

### Gradle

- 애플리케이션에 캐시 라이브러리가 없는 경우 기본값인 `simple` 캐시를 사용하게 됩니다.
- `simple`캐시는 `ConcurrentHashMap`을 캐시 저장소로 사용합니다.

먼저 간단한 예제를 통해서 캐시가 동작하는지 살펴보도록 하겠습니다.

## 캐시 실습1 - 간단한 테스트

- `@Cacheable`을 캐시가 있으면 캐시의 정보를 가져오고, 캐시가 없으면 등록합니다.

- 간단한 `Book` 도메인과 컨트롤러, 서비스를 만들었습니다.
  - `Book` 도메인
  ```java
    @Getter
    @Setter
    @Entity
    @Table(name = "book")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Book {             
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
    
      @Column
      private String name;
  
      private Book(final Long id, final String name) {
          this.id = id;
          this.name = name;
      }
  
      public static Book from(final Long id, final String name) {
          return new Book(id, name);
      }
  
      @Override
      public String toString() {
          return "Book{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  '}';
      }
    }
  ```
  
  - 컨트롤러
  ```java
      @RestController
      @RequiredArgsConstructor
      public class BookContoller {
  
        private final BookService bookService;
  
        @Cacheable("book")
        @GetMapping("book/{bookId}")
        public ResponseEntity<BookResponse> findBook(final @PathVariable Long bookId) {
            return ResponseEntity.ok(bookService.findBook(bookId));
        }
        
        @Cacheable("books")
        @GetMapping("books")
        public ResponseEntity<List<BookResponse>> findBooks() {
            return ResponseEntity.ok(bookService.findBookAll());
        }
      }
  ```
  - 서비스
  ```java
  @Slf4j
  @Service
  @RequiredArgsConstructor
  public class BookService {
  
      private final BookRepository bookRepository;
  
      public BookResponse findBook(final Long bookId) {
          log.info("BookService findBook 메소드 시작");
          final Book book = bookRepository.findById(bookId)
                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));
          return BookResponse.from(book);
      }
  
      public List<BookResponse> findBookAll() {
          log.info("BookService findBookAll 메소드 시작");
          final List<Book> books = bookRepository.findAll();
          return BookResponse.from(books);
      }
  }
  ```
    
### 실행

- 서버를 시작하여서 캐시가 적용되지 않았을때와 캐시가 적용되었을 때 비교를 해보겠습니다.
- 실행은 `HTTP request` 를 이용하여서 진행하였습니다.
  ```http request
  GET http://localhost:8080/book/1
  ```
  
### 결과

#### 캐시가 적용되지 않았을때

- 실행을 해보면 아래와 같이 결과를 확인할 수 있습니다.
  ```
  GET http://localhost:8080/book/1
  
  HTTP/1.1 200 
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Sun, 30 Jan 2022 04:09:18 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  {
    "id": 1,
    "name": "자바의정석"
  }
  
  Response code: 200; Time: 205ms; Content length: 23 bytes
  ```
- 캐시가 적용되지 않았기 때문에 쿼리로그가 실행된 부분도 확인이 가능합니다.

#### 캐시가 적용되었을때

- 실행을 해보면 아래와 같이 결과를 확인할 수 있습니다.
  ```http request
  GET http://localhost:8080/book/1
  
  HTTP/1.1 200
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Sun, 30 Jan 2022 04:15:23 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  {
    "id": 1,
    "name": "자바의정석"
  }
  
  Response code: 200; Time: 58ms; Content length: 23 bytes
  ```
- 쿼리 로그는 더이상 실행이 되지 않습니다.

### 정리

- 캐시가 적용되지 않았을때와 적용되었을때 상당히 빠른 효과를 보이는 것을 확인할 수 있습니다.
  - 간단한 테스트긴 하지만 수치만 놓고 보았을 때, 205ms -> 58ms, 약 4배 빠른 효과를 보이고 있습니다.

변경주기가 빈번하지 않더라도 변경이 된다면 캐시 데이터는 어떻게 해야 할까요? 
이런 변경 사항(추가 또는 삭제)에 대해 메소드를 제공해주고 있습니다.

## 캐시 실습2 - 생성 테스트

- 서점의 정보가 추가되어야 할 경우 `@CacheEvict`을 사용할 수 있습니다.
- `@CacheEvict`을 사용하면 캐시에서 데이터를 제거하는 트리거로 동작하는 메소드입니다. 
- 키나 조건을 지정해야 할 수 있지만 딱 하나의 엔트리(키에 기반을 둔)가 아니라 제거를 할 캐시의 범위를 나타내는 `allEntries` 파라미터를 추가로 사용할 수 있습니다.
- 캐시 추가 기능을 컨트롤러, 서비스에 작성하였습니다.
  - 컨트롤러
  ```java
  @CacheEvict(cacheNames = "books", allEntries = true)
  @PostMapping("book")
  public ResponseEntity<BookResponse> createBook(final @RequestBody BookRequest bookRequest) {
    return ResponseEntity.ok(bookService.createBook(bookRequest));
  }
  ```
  - 서비스
  ```java
  public BookResponse createBook(final BookRequest bookRequest) {
    log.info("BookService createBook 메소드 시작");
    final Book book = bookRepository.save(bookRequest.toBook());
    return BookResponse.from(book);
  }
  ```
### 실행

- 서버 실행 후 책의 전체 목록을 가져오는 API 실행하여 캐시 데이터를 저장하고 있겠습니다.
- 추가된 책을 저장하는 API 실행하여서 추가하고 기존 캐시 데이터를 삭제하겠습니다.
- 책의 전체 목록을 가져오는 API 실행하였을때 전체 목록을 조회해오는지와 추가된 데이터도 캐싱되는지 확인할 수 있습니다.

### 결과

- 책의 먼제 목록을 가져오는 API를 2번 정도 실행하여서 캐싱하겠습니다.
  - 실행
    ```
    GET http://localhost:8080/books
    ```
  - 결과
    ```
    GET http://localhost:8080/books
    
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 30 Jan 2022 06:31:49 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    [
      {
        "id": 1,
        "name": "자바의정석"
      },
      ...
    ]
  
    Response code: 200; Time: 54ms; Content length: 742 bytes
    ```
- `클린코드` 도서를 추가하도록 하겠습니다.
  - 실행
    ```http request
    POST http://localhost:8080/book
    Content-Type: application/json
    
    {
      "name" : "클린코드"
    }
    ```
  - 결과
    ```http request
    POST http://localhost:8080/book
    
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 30 Jan 2022 06:47:46 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    {
      "id": 31,
      "name": "클린코드"
    }
    
    Response code: 200; Time: 229ms; Content length: 23 bytes
    ```
- 책의 전체 목록을 가져오는 API 실행하였을때 전체 목록을 조회해오는지와 추가된 데이터도 캐싱되는지 확인할 수 있습니다.
  - 실행
  ```
  GET http://localhost:8080/books
  ```
  - 아래 로그와 같이 `findBookAll` 처음 실행되고, 그 다음 `createBook` 실행되고, 마지막으로 `findBookAll` 실행됩니다.
  - `@CacheEvict(cacheNames = "books", allEntries = true)` 옵션이 적용되어서 캐시 데이터가 삭제된것을 확인할 수 있습니다.
    ```
    INFO 35004 --- [nio-8080-exec-1] c.l.springboot.application.BookService   : BookService findBookAll 메소드 시작
    Hibernate: select book0_.id as id1_0_, book0_.name as name2_0_ from book book0_
    INFO 35004 --- [nio-8080-exec-3] c.l.springboot.application.BookService   : BookService createBook 메소드 시작
    Hibernate: insert into book (id, name) values (null, ?)
    INFO 35004 --- [nio-8080-exec-5] c.l.springboot.application.BookService   : BookService findBookAll 메소드 시작
    Hibernate: select book0_.id as id1_0_, book0_.name as name2_0_ from book book0_
    ```

## 캐시 실습3 - 수정 테스트

- 등록된 서점의 이름 변경이 필요한 상황이 발생하였습니다.
- `@CachePut`을 사용하면 캐시를 업데이트 해야하는 경우 사용할 수 있습니다.
- 캐시 데이터 수 기능을 컨트롤러, 서비스에 작성하였습니다.
  - 컨트롤러
  ```java
    @CachePut(cacheNames = "books", key = "#bookId")
    @PatchMapping("book/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("bookId") final Long bookId, @RequestBody final BookRequest bookRequest) {
        final BookResponse response = bookService.updateBook(bookId, bookRequest);
        return ResponseEntity.ok(response);
    }
  ```
  - 서비스
  ```java
    @Transactional
    public BookResponse updateBook(final Long bookId, final BookRequest bookRequest) {
        log.info("BookService updateBook 메소드 시작");
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));
        book.updateName(bookRequest.getName());
        return BookResponse.from(book);
    }
  ```

### 실행

- 서버 실행 후 `bookId`의 1번에 등록되어 있는 책을 조회하여 캐싱합니다.
- 캐싱되어 있는 데이터, 1번에 등록되어 있는 `자바의정석`에서 `클린코드`로 변경하겠습니다.
- 1번에 등록되어 있는 책을 조회하는 API를 실행하였을때 캐싱 초가화 및 쿼리 수행을 하지 않고 변경된 데이터를 확인할 수 있습니다.

### 결과

- 책 1번을 조회하여서 캐싱하도록 하겠습니다.
  - 실행
    ```
    GET http://localhost:8080/book/1
    ```
  - 결과
    ```http request
    GET http://localhost:8080/book/1
    
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 30 Jan 2022 08:46:01 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    {
      "id": 1,
      "name": "자바의정석"
    }
    
    Response code: 200; Time: 275ms; Content length: 23 bytes
    ```
  - 다시 시도하면 Database를 조회하지 않고 캐싱된 데이터를 가져오는 것을 확인할 수 있습니다.
- 위에서 조회한 서점의 이름을 `클린코드`로 변경하겠습니다.
  - 실행
    ```
    PATCH http://localhost:8080/book/1
    Content-Type: application/json
    
    {
      "name" : "클린코드
    }
    ```
  - 결과
    ```http request
    PATCH http://localhost:8080/book/1
    
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 30 Jan 2022 08:48:14 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    {
      "id": 1,
      "name": "클린코드"
    }
    
    Response code: 200; Time: 160ms; Content length: 22 bytes
    ```
    - 변경된 내용을 확인하였으니 캐싱된 데이터도 잘 변경되었는지 확인을 해보겠습니다.
- 다시 조회 API를 호출하여서 변경된 캐싱 데이터를 잘 갖고 오는지 확인해보겠습니다.
  - 실행
    ```
    GET http://localhost:8080/book/1
    ```
  - 결과
    ```http request
    GET http://localhost:8080/book/1
    
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 30 Jan 2022 08:50:45 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    {
      "id": 1,
      "name": "클린코드"
    }
    
    Response code: 200; Time: 134ms; Content length: 23 bytes
    ```
    - 로그 확인을 해보면 다음과 같습니다.
      - `BookService findBook 메소드 시작` : 첫번째 호출
      - `BookService updateBook 메소드 시작` : 두번째 호출
      -  마지막에 호출한 내용은 로그로 남지 않는 것을 보면 캐싱된 데이터가 호출되었다는 것으로 알 수 있습니다.
        ```
        INFO 46562 --- [nio-8080-exec-1] c.l.springboot.application.BookService   : BookService findBook 메소드 시작
        Hibernate: select book0_.book_id as book_id1_0_0_, book0_.name as name2_0_0_ from book book0_ where book0_.book_id=?
        INFO 46562 --- [nio-8080-exec-3] c.l.springboot.application.BookService   : BookService updateBook 메소드 시작
        Hibernate: select book0_.book_id as book_id1_0_0_, book0_.name as name2_0_0_ from book book0_ where book0_.book_id=?
        Hibernate: update book set name=? where book_id=?
        ```