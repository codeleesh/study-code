# jenkinsfile 설명

## jenkins란?

- 소프트웨어 개발시 지속적 통합(continuous integration) 서비스를 제공하는 도구입니다.
- 다수의 개발자들이 하나의 프로그램을 개발할 때 버전 충돌을 방지하기 위해 각자 작업한 내용을 공유 영역에 있는 Git, Svn 등의 저장소에 빈번히 업로드함으로써 지속적 통합이 가능하도록 지원합니다.
- 비슷한 CI/CD 도구로는 `Travis CI`, `CircleCI`, `TeamCity`, `Buildkite`, `GitLab Runner` 가 있습니다. 
- MIT 라이선스입니다.

> 지속적 통합이란?
> 지속적으로 품질 관리(Quality Control)를 적용하는 프로세스를 실행하는 것입니다.
> 작은 단위의 작업, 빈번한 적용. 지속적인 통합은 모든 개발을 완료한 뒤에 품질 관리를 적용하는 고전적인 방법을 대체하는 방법으로서 소프트웨어의 질적 향상과 소프트웨어를 배포하는데 걸리는 시간을 줄이는 데 초점이 맞추어져 있습니다.

> MIT 라이선스
> 매사추세츠 공과대학교(MIT)를 기원으로 하는 소프트웨어 라이선스 중 가장 대표적인 것입니다.
> 오픈 소스 여부에 관계없이 재사용을 인정합니다.

## pipeline이란?

- 소프트웨어의 대한 모든 변경은 릴리즈되는 과정에서 프로세스를 거쳐야 합니다. 프로세스는 다음과 비슷합니다.
  - 단위/인수/통합 테스트 수행
  - 빌드 수행
  - 배포 수행(feat. 재시작)
- 위 프로세스를 수동으로 한다면 처음 몇 번은 어렵지 않게 해낼 수 있습니다. 하지만 소프트웨어는 변경 작업은 더욱 많아질 것이고 그럴때마다 반복적인 수행을 해야 합니다.
- 반복적인 수행을 수동으로 한다면 휴먼에러가 발생할 여지가 충분히 있습니다.
- `pipeline`을 통해 여러 단계의 테스트 및 빌드, 그리고 배포를 진행할 수 있으며, 안정적인 반복 작업이 가능합니다.

## 왜 필요할까?

- 모든 브랜치의 대한 `PR`(Pull Request)의 대해서 `pipeline` 빌드 프로세스를 생성하기 때문에 쉽게 배포할 수 있습니다.
- `pipeline` 프로세스 실행 시 로그 추적이 가능합니다.
- `pipeline`은 단일 소스로 관리되기 때문에 개발팀원들이 모두 볼 수 있으며 수정도 가능합니다.

## 사용 방법

- `pipeline` 을 사용하기 위해서는 프로젝트의 저장소(feat. Git, Svn)의 `Jenkinsfile` 파일이 작성되어야 합니다.
- `Jenkinsfile` 은 선언형과 스크립트형 `pipeline` 으로 2가지로 분리되어 있습니다.
- 여기서는 최신 기능인 선언형 `pipeline` 을 사용하여서 기본 구조를 다음과 같이 잡았습니다.

```groovy
pipeline {
  agent any

  environment {
    //
  }

  stages {
    stage('Build') {
      when {
        //
      }
      step {
        //
      }
    }
    stage('Backup & Copy') {
      when {
        //
      }
      step {
        //
      }
    }
    stage('Deploy') {
      when {
        //
      }
      step {
        //
      }
    }
  }
}
```
  - 하나의 구간 안에 지시(`Directives`) 또는 단계(`Steps`)를 포함하는 코드로 구성되어 있습니다.
    - 블록은 시작과 끝이 `{ }` 로 묶인 코드의 묶음입니다.
  - 구간은 전체 파이프라인의 흐름에서 특정한 시점에 실행이 필요한 것들을 묶을 수 있습니다.
    - 각 구간에는 지시와 단계, 조건문이 존재합니다.

## Jenkins Pipeline 문법

- `Jenkinsfile` 을 작성하기 위한 문법에 대해서 정리하였습니다.
- 크게 다음으로 정리하였습니다.
  - Sections
  - Directives
  - Sequential Stages

## Sections

- `Sections`은 `Directives`와 `Steps`를 포함하는 코드로 구성되어 있습니다.
- `Sections`의 포함된 항목은 다음과 같습니다.
  - agent
  - post
  - stages
  - steps

## agent

- `pipeline` 또는 `stage`에서 조건부로 사용할 수 있는 하나의 구간입니다.
- 선언형 파이프라인 구문에서 사용 가능합니다.
- 사용할 수 있는 종류는 다음과 같습니다.
  - `any`
  - `none`
  - `label`
  - `docker`
  - `kubernetes`

### any

- 사용 가능한 agent에서 `pipeline` 또는 `stage`에 상관없이 실행할 수 있습니다.
```groovy
agent any
```

### none

- `pipeline` 블록의 최상위 레벨에 적용되는 경우 전체 `pipeline`의 실행에 전역 `agent`가 할당되지 않으면, 각 단계마다 `agent`를 포함하고 있어야 합니다.
- 사용한다면 여러(ex: linux, windows) 서버에서 `pipeline` 또는 `stage` 단계를 진행해야 되는 경우 해당 기능을 사용하면 됩니다.
```groovy
agent none
```

### label

- 제공된 label이 있는 환경에서 사용 가능합니다.
```groovy
agent { label 'my-defined-label' }
```
- `pipeline` 또는 `stage`가 'my-defined-label'이라는 레이블을 가진 에이전트에서 실행될 수 있다는 의미입니다.

### docker

- Docker Registry 사용 및 Dockerfile 사용 가능
```groovy
agent {
  docker {     
    image 'maven:3.8.1-adoptopenjdk-11'
    label 'my-defined-label'
    args '-v /tmp:/tmp'
  } 
}
```

### kubernetes

- k8s 클러스터에 배포된 파드 내부에서 pipeline을 실행합니다.
```groovy
agent {
  kubernetes {
    defaultContainer ''
    yaml '''
    // k8s 내용 작성
         '''
  }
}
```

## post

- `pipeline` 또는 `stage`에서 조건부로 사용할 수 있는 하나의 구간입니다.
- Job의 빌드 후처리 동작에 대해서 상세 설정을 할 수 있습니다.
- `post`에서 제공하는 옵션들은 다음과 같습니다.
  - always
  - changed
  - fixed
  - regression
  - aborted
  - failure
  - success
  - unstable
  - unsuccessful
  - cleanup

### always

- `pipreline` 또는 `stage`의 실행 완료 상태에 상관없이 실행 후 무조건 사후 단계를 실행합니다.

### changed

- 현재 `pipreline` 또는 `stage`의 실행이 이전 실행과 다른 완료 상태를 가진 경우에만 단계를 사후 단계를 실행합니다.

### fixed

- 현재 `pipreline` 또는 `stage`의 실행이 성공하고 이전 실행이 실패했거나 불안정한 상태인 경우 사후 단계를 실행합니다.

### regression

- 현재 `pipreline` 또는 `stage`의 실행이 실패 또는 불안정 또는 중단이고 이전 실행이 성공한 경우 사후 단계를 실행합니다.

### aborted

- 현재 `pipreline` 또는 `stage`의 실행이 `aborted` 상태인 경우 사후 단계를 실행합니다.

### failure

- 현재 `pipreline` 또는 `stage`의 실행이 `failed` 상태인 경우 사후 단계를 실행합니다.

### success

- 현재 `pipreline` 또는 `stage`의 실행이 `success` 상태인 경우 사후 단계를 실행합니다.

### unstable

- 현재 `pipreline` 또는 `stage`의 실행이 `unstable` 상태인 경우 사후 단계를 실행합니다.

### unsuccessful

- 현재 `pipreline` 또는 `stage`의 실행이 성공 상태가 아닌 경우 사후 단계를 실행합니다.

### cleanup

- 현재 `pipreline` 또는 `stage`의 실행이 상태에 관계없이 다른 모든 post 조건이 진행된 후 단계를 실행합니다.

## stages

- `pipeline` 또는 `stage`에서 조건부로 사용할 수 있는 하나의 구간입니다.
- 하나 이상의 `stage`를 포함합니다.
- 선언형과 스크립트 파이프라인 모두에서 코드의 `step`를 정의합니다.

## steps

- `pipeline` 또는 `stage`에서 조건부로 사용할 수 있는 하나의 구간입니다.
- 지정된 `stage`에서 실행할 하나 이상의 `step`를 정의합니다.
- 단일 작업을 나타냅니다.
```groovy
steps {
  echo 'Build Start'
  sh './gradlew clean build -x test'
  echo 'Build End'
}
```

## Directives

### environment

- 환경 변수를 이용해서 `pipeline`을 작성할때 사용할 수 있습니다.
- 최상위 `pipeline` 블록에 사용되는 환경 변수는 `pipeline` 내의 모든 단계에 적용됩니다.
- `stage` 내 선언한 환경 변수는 `stage` 내에서만 사용 가능합니다.
- 사용할 수 있는 환경 변수는 다음과 같습니다.
  - Jenkins environment variables
  - environment variables
  - environment variables dynamically

#### Jenkins environment variables

- Jenkins에서 기본적으로 제공해주는 환경 변수입니다.
- BUILD_ID, BULD_NUMBER, BUILD_TAG 등등

#### environment variables

- `pipeline` 내에 환경 변수를 설정하여서 사용할 수 있습니다.

#### environment variables dynamically

- 환경 변수는 런타임에 설정할 수 있습니다.

### String 변수 처리

- 애플리케이션의 이름이 반복된다면 상수로 관리하여서 재사용이 가능합니다. 그 외 상수가 필요하다면 비슷한 내용으로 작성이 가능합니다.
- 사용은 다음과 같이할 수 있습니다.
```groovy
def APPLICATION = 'api-app'
echo 'Build Start ${APPLICATION}'
echo "Build End ${APPLICATION}"
```

- 결과
```shell
Build Start ${APPLICATION}
  Build End api-app
```
- 상수를 사용하기 위해서는 `' '`가 아니라 `" "` 를 사용해야 합니다.

### tools

- `path`에 자동 설치 및 설정하기 위한 도구를 정의할 수 있습니다.
- `agent none`으로 설정했다면 tools를 활성화 시킬 노드나 에이전트가 없디 때문에 동작하지 않습니다.
- 해당 옵션을 사용하기 위해서는 `Manage Jenkins -> Global Tool Configuration`에서 환경변수를 지정해야 합니다.
- 지원하는 tools는 다음과 같습니다.
  - maven
  - jdk
  - gradle
  - git
  - node..

```groovy
tools {
  maven 'apache-maven-3.0.1'
}
```

### stage

- `stage`에는 하나 이상의 DSL `step` 이 들어 있습니다.
  - `step` 구간은 필수로 존재해야 합니다.
```groovy
stage('Build') {
  steps {
    
  }
}
stage('Test') {
  steps {
      
  }
}
stage('Deploy') {
  steps {
    
  }
}
```

### when

- 주어진 조건에 따라 `stage` 실행해야 하는지 여부를 결정할 수 있습니다.
- 조건이 하나 이상 포함되어야 합니다.
- 조건문의 상태는 다음과 같습니다.
  - `allOf`
    - 지시문이 둘 이상의 조건을 포함하는 경우 단계가 실행되기 위해서는 모든 조건이 참인 경우 실행합니다.
  - `anyOf`
    - 지시문이 둘 이상의 조건을 포함하는 경우 단계가 실행되기 위해서는 하나의 조건이 참인 경우 실행합니다.
  - `not`
    - 조건문이 참이 아닌 경우 실행합니다.
- `when`을 이용해서 사용할 수 있는 기본 옵션들은 다음과 같습니다.

#### branch

- 빌드 중인 브랜치가 브랜치 패턴과 일치하는 경우에 단계를 실행합니다.
- `master` 브랜치일때 단계가 실행됩니다.
```groovy
when {
  branch 'master'
}
```
- 단순한 문자열 비교 및 정규 표현식 활용하여 추가가 가능합니다.
```groovy
when {
  branch pattern: "release-\\d+", comparator: "REGEXP"
}
 ```

#### buildingTag

- 빌드가 태그를 빌드해야할 때 해당 옵션을 이용합니다.
```groovy
when {
  buildingTag()
} 
```

#### changelog

- 커밋 로그 중 지정된 정규식 패턴에 해당되는 경우 단계를 실행합니다.
```groovy
when {
  changelog '.*^\\[DEPENDENCY\\].+$'
}
```

#### changeset

- *실제 프로젝트에서 자주 사용할 수 있는 옵션입니다.*
- 변경된 파일이 변경 집합에 지정된 패턴과 일치하는 경우 단계를 실행합니다.
```groovy
when {
  changeset "app-api/**/*"
}
```
- 단순한 문자열 비교 및 정규 표현식 활용 등을 사용할 수 있습니다.
```groovy
when {
  changeset pattern: ".TEST\\.java", comparator: "REGEXP"
}
```

#### changeRequest

- 빌드가 `PR`(Pull Request)인 경우 단계를 실행합니다.
```groovy
when {
  changeRequest()
}
```

#### environment

- 지정된 환경 변수가 주어진 값으로 설정된 경우 단계를 실행합니다.
```groovy
when {
  environment name : "DEPLOY_TO", value : "production"
}
```

#### equals

- 예상 값이 실제 값과 같을 때 단계를 실행합니다.
```groovy
when {
  equals expected: 2, actual: currentBuild.number
}
 ```

#### expression

- 지정된 조건식 true일 때 단계를 실행합니다.
```groovy
when {
  expression {
    return params.DEBUG_BUILD
  }
}
```
- 문자열을 반환할 때는 boolean으로 변환하거나 null을 반환하여 false로 처리해야 합니다. 단순히 문자열을 "0" 또는 "false"로 반환한다면 true로 인식합니다.

#### tag

- TAG_NAME 변수가 지정된 패턴과 일치하는 경우 단계를 실행합니다.
```groovy
when {
  tag "release-*"
}
```

#### not

- 조건문이 참이 아닌 경우 실행합니다.

#### allOf

- 지시문이 둘 이상의 조건을 포함하는 경우 단계가 실행되기 위해서는 모든 조건이 참인 경우 실행합니다.

#### anyOf

- 지시문이 둘 이상의 조건을 포함하는 경우 단계가 실행되기 위해서는 하나의 조건이 참인 경우 실행합니다.

#### triggeredBy

## Sequential Stages


### pipeline

- 애플리케이션의 빌드, 테스트 및 배포 단계를 포함하는 전체 빌드 프로세스를 정의합니다.
- `pipeline`의 블록은 선언형 파이프라인 구문의 핵심 부분입니다.
- `Jenkinsfile`의 시작 지점에 선언해야 합니다.
```groovy
pipeline {
  //
}
```

### node

- `node`의 블록은 스크립트형 파이프라인 구문의 핵심 부분입니다.
- 스크립트형 파이프라인 구문은 여기서는 알아보지 않도록 하겠습니다.


### parallel

- 병렬로 `stage`를 실행합니다.
```groovy
parallel {
  stage('build-service1') {
    steps {
      //
    }
  }
  stage('build-service2') {
    steps {
      //
    }
  }
  stage('build-service3') {
    steps {
      //
    }
  }
}
```
- service1, service2, service3에 대해서 순차적으로 실행하는 것이 아닌, 병렬로 수행하여서 실행시간을 단축할 수 있습니다.

#### `echo`

- single-quotes를 사용합니다.
- 해당 내용을 출력할 수 있습니다.

#### `sh`

- single-quotes를 사용합니다.
- sh 파일을 실행할 수 있습니다.


