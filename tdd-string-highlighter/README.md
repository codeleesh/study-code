# TDD 연습

TDD 연습을 하기 위해서 나름 아껴놓고 있다가 진행하게 되었습니다. 유투브 영상의 앞부분 요구사항 나오는 부분만 확인한 후 실습을 진행하였습니다.

그리고 직접 TDD로 연습하면서 개발을 진행 및 완료한 후 영상에서 진행하신 부분과 내가 진행한 코드가 어떤지 비교를 하였는데 꽤 재미있었습니다.

> 출처
> 최범균님 유투브 - https://www.youtube.com/watch?v=5PDYHNCcjYM&t=755s

직접 진행한 소스는 github에서 확인할 수 있습니다. - [소스 바로가기](https://github.com/codeleesh/study-code/tree/main/tdd-string-highlighter)

## 요구사항

```
문자열 목록에서 note가 포함된 문자열을 강조해서 출력하는 프로그램을 만든다.
단, 정확하게 원하는 알파벳만으로 이루어진 단어만을 확인해야 한다.
예를 들어, note1이나 keynote는 원하는 단어가 아니다.
note의 앞이나 뒤에 숫자나 알파벳이 나오면 다른 단어라고 판단한다.
출력 결과는 note 단어를 강조하기 위해 단어의 앞뒤에 중괄호{ }를 한다.
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
```

## 구현 방법

정확한 케이스는 빠르게 넘어갔습니다. 바로 다음과 같은 경우입니다.

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

위의 경우처럼, 몇 가지 정확한 케이스는 어렵지 않게 해결이 됩니다.
하지만 문자열 속에 `note` 가 함께 있는 경우는 좀 고민을 해보게 되었습니다. 정규식을 이용해보면 어떨까 싶어서 진행을 해보게 되었습니다.

### 첫번째 생각한 방법

#### 문자열 속에 `note`가 함께 있는 경우

해당 케이스를 나열해보면 다음과 같습니다.

- ex) `1 note`, `1 note 2`, `keynote`, `ke1note`, `yes note1`, `yes notea`, `no a note`, `no a note note`, `no a note note anote`
- 문자열 속에 ` note ` 앞뒤로 공백과 문자열이 올 수도 있고 안 올수도 있습니다.
- 문자열 속에 ` note` 앞에만 공백과 문자열이 올 수 있습니다.
- 문자열 속에 `note ` 뒤에만 공백과 문자열이 올 수 있습니다.

그래서 가장 먼저 들었던 생각이 정규식이었습니다.

- `note` 앞뒤로 공백이 존재하면 문자열 속 중간에 나온 부분으로 체크해볼 수 있습니다. : `.*( note ).*`
- `note` 앞에만 공백이 존재하고 끝에는 존재하지 않으면 문자열 끝으로 체트해볼 수 있습니다. : `.*( note)`
- `note` 뒤에만 공백이 존재하고 앞에는 존재하지 않으면 문자열 끝으로 체트해볼 수 있습니다. : `(note ).*`

자바에서 정규식 문자열을 찾는 부분은  `Matcher` 와 `Pattern` 을 이용해서 다음과 같이 작성하였습니다.

```java
Matcher matcher = Pattern.compile(".*( note ).*|.*( note)|(note ).*").matcher(this.name);
boolean matches = matcher.matches();
if (matches == false) {
    return this.name;
}

return str.replace(STRING_NOTE, STRING_CORRECT_NOTE);
```

#### 문제점

요구사항 중 딱 하나 케이스를 제외하고 모두 통과합니다. 통과하지 않는 케이스는 `no a note note anote` 바로 이것이었습니다.
정규식만 생각하다보니 오히려 단순한 부분을 생각을 하지 못하다보니 다음과 같은 문제점이 생겼습니다.

- 정규식이 `or` 조건이라서 `matches` 를 true로 결과를 반환하여서 결과가 다음과 같이 나타나게 됩니다.
  - `no a {note} {note} a{note}`
-  `Matcher` 와 `Pattern` 을 이용해서 좀 더 세분화하여서 하는 방법도 생각을 하였지만 `Effective Java` 의 item6에서 `불필요한 객체를 생성하지 마라` 고 설명을 하고 있습니다.
- 비싼 객체를 많이 생성해도 될까? 라는 답을 내리지 못해서 주저하게 만들었습니다.
- 좀 더 자세한 내용은 다음을 참고해주셔도 됩니다. - [[Item6] 불필요한 객체를 생성하지 마라](https://lovethefeel.tistory.com/63)

그래서 정규식은 진행을 하지 않기로 결정을 하게 되었고, 문자열속에 문자 단위별로 비교해서 변경하는 것이 필요하다고 생각을 하게 되었습니다.
그래서, 다음과 같이 진행을 해보았습니다.

### 두번째 생각한 방법 (최종 결과)

바로 공백(` `) 문자 기준으로 문자열을 `split` 하여서 문자 단위별로 `note` 를 비교하는 것이었습니다.
마지막 케이스를 자세히 보겠습니다.

#### 구현 방법

- ex) `no a note note anote`
  - 공백(` `) 문자 기준으로 문자열 `split` 처리하면 다음과 같이 나옵니다.
    - `no`
    - `a`
    - `note`
    - `note`
    - `anote`
  - 그렇게 나온 각 문자열을 `note` 와 비교(`equals`)하여 일치하면 `{note}` 문자열로 변경하고 일치하지 않으면 그대로 반환합니다.
  - 공백(` `) 문자 기준으로 문자열 `split` 하였으니, 각 문자열 비교가 끝난 후 반환 시 공백(` `) 문자열 추가합니다.
  - 마지막 문자열에서 반복문이 끝나면 마지막 공백(` `) 문자 제거합니다.

## 마무리

- 요구사항이 확실한 내용을 통해서 TDD를 연습