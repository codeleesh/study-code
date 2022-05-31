# TDD 연습

TDD 연습을 하기 위해서 나름 아껴놓고 있다가 진행하게 되었습니다.
유투브 영상의 앞부분 요구사항 나오는 부분만 참고하고 `pause`로 멈춰놓았습니다.
그리고 직접 TDD로 연습하면서 개발을 진행 및 완료한 후 실제 코드와 내가 진행한 코드가 어떤지 비교를 하였는데 꽤 재미있었습니다.

> 출처
> 최범균님 유투브 - https://www.youtube.com/watch?v=5PDYHNCcjYM&t=755s

## 요구사항 

문자열 목록에서 note가 포함된 문자열을 강조해서 출력하는 프로그램을 만든다.
단, 정확하게 원하는 알파벳만으로 이루어진 단어만을 확인해야 한다.
예를 들어, note1이나 keynote는 원하는 단어가 아니다.
note의 앞이나 뒤에 숫자나 알파벳이 나오면 다른 단어라고 판단한다.
출력 결과는 note 단어를 강조하기 위해 단어의 앞뒤에 중광호{ }를 한다.

문자열은 알파벳 소문자, 공백, 숫자로만 구성되어 있다고 가정한다.

변환 예:

* abc -> abc
* note -> {note}
* 1 note -> 1 {note}
* 1 note 2 -> 1 {note} 2
* keynote -> keynote
* ke1note -> ke1note
* yes note1 -> yes note1
* yes notea -> yes notea
* no a note -> no a {note}
* no a note note -> no a {note} {note}
* no a note note anote -> no a {note} {note} anote

## 구현 방법

### 첫번째 생각한 방법

생각나는 것은 정규식 패턴을 이용한 방법이여서 바로 TDD 연습을 시작하였습니다.

#### `note` 문자열만 존재하는 경우

- ex) `note`
  ```java
  if (str.equals("note")) {
      return "{note}";
  }
  ```
#### `note` 가 아닌 경우

- ex) `abc`
  ```java
  if (str.equals("note")) {
      return str;
  }
  ```

#### 문자열 속에 `note`가 함께 있는 경우 
 
- ex) `1 note`, `1 note 2`, `keynote`, `ke1note`, `yes note1`, `yes notea`, `no a note`, `no a note note`, `no a note note anote`
  - 문자열 속에 `note` 가 존재하는 반복 패턴은 다음과 같습니다.
  - 문자열 속에 ` note `로, 앞뒤로 공백과 문자열이 올 수도 있고 안 올수도 있습니다.
  - 문자열 속에 ` note`로, 앞에 공백과 문자열이 올 수 있습니다.
  - 문자열 속에 `note `로, 뒤에 공백과 문자열이 올 수 있습니다.
    ```java
    Matcher matcher = Pattern.compile(".*( note ).*|.*( note)|(note ).*").matcher(this.name);
    boolean matches = matcher.matches();
    if (matches == false) {
        return this.name;
    }
    
    return str.replace(STRING_NOTE, STRING_CORRECT_NOTE);
    ```
#### 문제점

- 딱 하나 케이스를 제외하고 모두 통과합니다.
- 통과하지 않는 케이스는 `no a note note anote`
- 문자열속에 문자 단위별로 비교해서 변경하는 것이 필요하였습니다.
  
그래서, 다음과 같이 생각해보았습니다.

### 두번째 생각한 방법 (최종 결과)

바로 공백(` `) 문자 기준으로 문자열을 `split` 하여서 문자 단위별로 `note` 를 비교하는 것이었습니다.
마지막 케이스를 자세히 보겠습니다.

#### 구현 방법

- ex) `no a note note anote`
  - 공백(` `) 문자 기준으로 문자열 `split` 처리하여 아래는 결과
    - `no`
    - `a`
    - `note`
    - `note`
    - `anote`
  - 각 문자열을 `note` 와 비교(`equals`)하며 일치하면 `{note}` 문자열로 변경하고 일치하지 않으면 그대로 반환
  - 각 문자열 반환 시 공백(` `) 문자열 추가
  - 반복문이 끝나면 마지막 공백(` `) 문자 제거