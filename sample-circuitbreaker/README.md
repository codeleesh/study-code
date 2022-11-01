

## 횟수 기반 슬라이딩 윈도우(Count-based sliding window)

- 들어온 횟수를 기반으로 슬라이딩 윈도우를 잡는 방법입니다. 
- N 크기의 circular array를 만들어서 해당 array의 실패률을 이용하여 circuit의 상태를 확정합니다. 
- 상태를 확인하는데 걸리는 시간은 O(1)이며 메모리 소비는 O(N)입니다.

## 시간 기반 슬라이딩 윈도우(Time-based sliding window)

- 시간을 기반으로 슬라이딩 윈도우를 잡는 방법입니다. 
- N 초의 슬라이딩 윈도우 크기라면 N 개의 circular array를 만듭니다. 
- 각 버킷에 특정 초에 발생한 호출의 결과를 집계하여 저장하고 있고 이를 초가 지남에 따라 밀어내고 가지는 형식입니다. 
- N 초가 지나기 시작하면 가장 오래된 버킷은 제거되고 총 집계는 새롭게 이루어집니다. 
- 집계는 실패한 호출 수, 느린 호출 수, 그리고 총 호출 수 3가지를 가지고 있습니다. 
- 별도 집계를 하고 결과를 가지고 있으므로 상태 확인은 O(1)이며 메모리는 O(N)의 부분집계와 O(1)의 총 집계로 구성됩니다.

## 옵션

- failureRateThreshold ( 기본값 : 50 )
  - 실패율 임계값을 설정합니다. 
  - 해당 %가 넘거나 같아지면 circuitBreaker의 상태가 Open으로 변경되며 실제 코드를 호출하지 않고 fallback 또는 fail 처리 됩니다.
- slowCallRateThreshold ( 기본값 : 100 )
  - slow call이 일어날 때 임계값을 설정합니다. 
  - 해당 %가 넘거나 같아지면 circuitBreaker의 상태가 Open으로 변경되며 실제 코드를 호출하지 않고 fallback 또는 fail 처리 됩니다.
- slowCallDurationThreshold ( 기본값 : 60_000 [ms] )
  - slow call이라고 간주하는 시간을 설정합니다.
- permittedNumberOfCallsInHalfOpenState ( 기본값 : 10 )
  - circuit이 HALF_OPEN 상태일 때 허용되는 call 수이며 해당 call로 들어온 실패율에 따라서 close 또는 open으로 변경된다.
- maxWaitDurationInHalfOpenState ( 기본값 : 0 [ms] )
  - circuit이 HALF_OPEN 상태일 때 call을 얼마나 기다릴지 정합니다. 0은 무한정 기다린다는 뜻입니다.
- slidingWindowType ( 기본값 : COUNT_BASED )
  - sliding window로 어떤 값을 사용할지 정합니다. 기본은 COUNT_BASED 이며 TIME_BASED로 사용할 수 있습니다.
- slidingWindowSize ( 기본값 : 100 )
  - sliding window 크기입니다. COUNT_BASED라면 array 크기이며 TIME_BASED라면 초 입니다.
- minimumNumberOfCalls ( 기본값 : 100 )
  - circuit을 동작시키기위한 최소한의 call 수 입니다. 
  - 실패율이 failureRateThreshold를 넘었다고해도 최소 호출량을 만족시키지 않으면 circuit은 열리지 않습니다.
- waitDurationInOpenState ( 기본값 : 60_000 [ms] )
  - circuit이 OPEN 상태가 되고나서 대기하는 시간입니다.
- automaticTransitionFromOpenToHalfOpenEnabled ( 기본값 : false )
  - circuit이 OPEN에서 HALF_OPEN으로 변경시키는 트리거를 위한 모니터링 thread를 별도로 둘지 여부입니다. 
  - 두지 않으면 call이 들어왔을때만 판단합니다.
- recordExceptions ( 기본값 : empty )
  - 실패로 처리할 Exception을 명시할 수 있습니다. 
  - 명시하면 명시하지 않은 Exception은 success로 간주합니다.
- ignoreExceptions ( 기본값 : empty )
  - 실패로 처리하지 않을 Exception을 명시할 수 있습니다.
- recordFailurePredicate
  - 성공과 실패 여부를 좀 더 커스터마이징 할 수 있습니다.
- ignoreException
  - 실패로 처리하지 않을 exception에 대해서 좀 더 커스터마이징 할 수 있습니다.