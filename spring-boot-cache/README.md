# Spring Boot Cache 적용

- 회사에서 Spring Boot Cache를 활용하여 적용할 기회가 생겨서 진행한 내용을 공유드립니다.
- 현재 사용 중인 기술이 Spring Webflux를 사용 중이어서 해당 내용으로 진행하였습니다.

## 캐시(cache)란?
프로그램이 수행될 때 나타나는 지역성을 이용해 메모리나 디스크에서 사용되었던 내용을 빠르게 접근할 수 있는 곳에 보관하고 관리함으로써 두 번째 접근 부터는 보다 빠르게 참조하도록 하는 것입니다.
```
하드디스크는 용량이 아주 크지만 속도가 느리고, 메인 메모리(RAM)의 용량은 1/100정도로 작지만 속도는 십만배 정도 빠르다. 캐시 메모리는 메인 메모리(RAM)의 1/100 정도 용량이지만 속도는 훨씬 빠르다.
```
즉, 사용되었던 데이터는 다시 사용되어질 가능성이 높다는 개념을 이용하였습니다.
다시 사용될 확률이 높은 것은 더 빠르게 접근 가능한 저장소를 사용한다는 개념입니다.

## 사용 예

- 동일한 데이터를 반복적으로 제공해야하는 경우
- 데이터의 변경주기가 빈번하지 않고, 처리 시간이 오래걸리는 경우

## 캐싱이 사용되는 계층??

### 웹 서버

### 데이터베이스

### HTTP


여기까지 캐시에 대해서 알아보았습니다.
이제 실습을 진행하여 보겠습니다.

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

- 간단한 `Book` 도메인과 컨트롤러, 서비스를 만들었습니다.
  - `Book` 도메인
    ```java
    @Getter
    @Setter
    @Entity
    @Table(name = "book")
    public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Column
    private String name;
  
    protected Book() {}
  
    private Book(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }
  
    public static Book of(final Long id, final String name) {
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
      @AllArgsConstructor
      public class BookContoller { 
    
          private final BookService bookService;
  
          @Cacheable("book")
          @GetMapping("book")
          public Mono<Book> findBook() {
              return bookService.findBook();
          }
      }
      ```
  - 서비스
    ```java
    @Slf4j
    @Service
    public class BookService {
        @Autowired
        private BookRepository bookRepository;
    
        public Mono<Book> findBook() {
            log.info("BookService findBook 메소드 시작");
            final Book book = Book.of(1L, "자바의정석");
            return Mono.just(book).log();
        }
    }
    ```
    
### 실행
