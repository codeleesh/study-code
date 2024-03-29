# Spring kafka

## application 설정

### consumer

#### bootstrap-servers 

- Kafka 클러스터에 대한 초기 연결에 사용할 호스트:포트쌍의 쉼표로 구분된 목록입니다. 
- 글로벌 설정이 있어도, consumer.bootstrap-servers가 존재하면 consuemer 전용으로 오버라이딩 합니다. 

#### group-id 
 
- Consumer는 Consumer Group이 존재하기 때문에, 유일하게 식별 가능한 Consumer Group을 작성합니다.

#### auto-offset-reset
 
- Kafka 서버에 초기 offset이 없거나, 서버에 현재 offset이 더 이상 없는 경우 수행할 작업을 작성합니다.
- Consumer Group의 Consumer는 메시지를 소비할 때 Topic내에 Partition에서 다음에 소비할 offset이 어디인지 공유를 하고 있습니다. 그런데 오류 등으로 인해. 이러한 offset 정보가 없어졌을 때 어떻게 offeset을 reset 할 것인지를 명시한다고 보시면 됩니다.
  - latest : 가장 최근에 생산된 메시지로 offeset reset
  - earliest : 가장 오래된 메시지로 offeset reset
  - none : offset 정보가 없으면 Exception 발생
- 직접 Kafka Server에 접근하여 offset을 reset할 수 있지만, Spring에서 제공해주는 방식은 위와 같습니다.

#### key-deserializer / value-deserializer
 
- Kafka에서 데이터를 받아올 때, key / value를 역직렬화 합니다.
- 여기서 key와 value는 뒤에서 살펴볼 KafkaTemplate의 key, value를 의미합니다.
- 이 글에서는 메시지가 문자열 데이터이므로 StringDeserializer를 사용했습니다. JSON 데이터를 넘겨줄 것이라면 JsonDeserializer도 가능합니다.

### producer

#### bootstrap-servers
 
- consumer.bootstrap-servers와 동일한 내용이며, producer 전용으로 오버라이딩 하려면 작성합니다.

#### key-serializer / value-serializer
 
- Kafka에 데이터를 보낼 때, key / value를 직렬화 합니다.
- consumer에서 살펴본 key-deserializer, value-deserializer와 동일한 내용입니다.

## source 

### producer

#### kafkaTemplate

#### RoutingKafka Templte

#### ReplyingKafka template

### consumer

#### Message listener

#### @KafkaListener

## 메시지 큐 활용 연습

메시지 큐를 활용하여 대량 처리를 해보려고 한다.

### 개발 환경 설정

- JDK 11
- Spring Boot 2.7.5
- Spring Kafka 2.8.10
  - Apache Kafka 3.1.2
- MySql

### 전체 시나리오 

- 대량의 주문 대기 중인 데이터가 데이터베이스에 등록되어 있다.
- `주문대기` 중인 전체 데이터를 조회하여 메시지 큐로 `주문완료` 요청한다.(`Producer`)
- `주문대기` 중인 주문을 전달받아 `주문완료` 처리한다.(`Consumer`)

### 테스트 시나리오

- 프로듀서, 컨슈머 모두 직렬 처리
- 병렬 처리는 프로듀서, 직렬처리는 컨슈머
- 직렬 처리는 프로듀서, 병렬처리는 컨슈머
- 프로듀서, 컨슈머 모두 병렬 처리

#### 병렬 처리

- 개발로 병렬처리
- 메시지큐에서 제공하는 병렬처리