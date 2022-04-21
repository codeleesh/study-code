# Jenkinsfile 설명

## Jenkins란?

- 소프트웨어 개발시 지속적 통합(continuous integration) 서비스를 제공하는 도구
- 다수의 개발자들이 하나의 프로그램을 개발할 때 버전 충돌을 방지하기 위해 각자 작업한 내용을 공유 영역에 있는 Git, Svn 등의 저장소에 빈번히 업로드함으로써 지속적 통합이 가능하도록 지원
- MIT 라이선스

> 지속적 통합이란?
> 지속적으로 품질 관리(Quality Control)를 적용하는 프로세스를 실행하는 것
> 작은 단위의 작업, 빈번한 적용. 지속적인 통합은 모든 개발을 완료한 뒤에 품질 관리를 적용하는 고전적인 방법을 대체하는 방법으로서 소프트웨어의 질적 향상과 소프트웨어를 배포하는데 걸리는 시간을 줄이는 데 초점이 맞추어져 있음

> MIT 라이선스
> 매사추세츠 공과대학교(MIT)를 기원으로 하는 소프트웨어 라이선스 중 가장 대표적인 것. 
> 오픈 소스 여부에 관계없이 재사용을 인정

## pipeline이란?

- 소프트웨어의 대한 모든 변경은 릴리즈되는 과정에서 프로세스를 거쳐야 합니다.
  - 소프트웨어의 대한 단위/인수/통합 테스트 수행
  - 소프트웨어 빌드 수행
  - 소프트웨어 배포 수행(feat. 재시작)
- 이러한 작업은 변경 작업의 대한 릴리즈시 반복적인 수행을 해야 합니다. 
- pipeline을 통해 여러 단계의 테스트 및 빌드, 그리고 배포를 진행하는 것뿐 아니라 안정적이고 반복 가능한 방식으로 수행합니다.

## 왜 필요할까?

- 모든 브랜치의 대한 `PR`(Pull Request)의 대해서 `pipeline` 빌드 프로세스를 생성할 수 있습니다.
- 코드 리뷰/반복
- `pipeline` 감사 추적
- `pipeline`은 단일 소스로 관리되기 때문에 개발팀원들이 볼 수 있고 수정 가능

## 사용 방법

- Jenkins pipeline을 사용하기 위해서는 프로젝트의 저장소(feat. Git, Svn)의 `Jenkins` 파일이 작성되어야 합니다.
- Jnekisfile은 선언
- 직접 사용한 Pipeline은 다음을 참고 부탁드립니다.

```groovy
pipeline {
    agent any

    environment {
    }
    
    stages {
        stage('Build') {
            parallel {
                stage('build-application-admin') {
                    when {
                        anyOf {
                            //
                        }
                    }
                    steps {
                        //
                    }
                }
                stage('build-domain-module') {
                    when {
                        anyOf {
                            //
                        }
                    }
                    steps {
                        //
                    }
                }
            }
        }
        stage('Backup & Copy') {
            parallel {
                stage('back-copy-application-admin') {
                    when {
                        anyOf {
                            //
                        }
                    }
                    steps {
                        //
                    }
                }
            }
        }
        stage('Deploy') {
            parallel {
                stage('deploy-application-admin') {
                    when {
                        anyOf {
                            //
                        }
                    }
                    steps {
                        //
                    }
                }
            }
        }
    }
}
```

### Pipeline
- `pipeline`은 사용자가 정의한 모델
- `pipeline`의 코드는 일반적으로 애플리케이션 빌드, 테스트 및 제공 단계를 포함하는 전체 빌드 프로세스를 정의
- 전체 `pipeline`을 실행하기 위한 모든 컨텐츠와 지침을 포함하는 선언적 `pipeline` 관련 구문
- `pipeline`의 블록은 선언적 파이프라인 구문의 핵심 부분

### Node
- `Node`의 블록은 스크립트 파이프라인 구문의 핵심 부분입니다.

### agent
- 전체 pipeline에 대한 실행과 작업 공간을 할당하도록 지시하는 구문입니다.

### environment

#### Jenkins environment variables

- Jenkins에서 기본적으로 제공해주는 환경 변수
- BUILD_ID, BULD_NUMBER, BUILD_TAG 등등


### stages

### stage
- Jenkins 파이프라인 상태/진행 상황을 표시하기 위해 아래와 같이 사용합니다.
    ```groovy
    stage('Build') {
        // build 작성
    }
    stage('Test') {
        // test 작성
    }
    stage('Deploy') {
        // deploy 작성
    }
    ```
- 전체 pipeline을 통해 수행되는 작업의 하위 집합을 정의합니다.

### parallel

### stage

#### when

#### anyOf

- `changeset`

#### steps
- 단일 작업을 나타냅니다.
    ```groovy
    steps {
        echo 'Build Start'
        sh './gradlew clean build -x test'
        echo 'Build End'
    }
    ```

- `echo`
- `sh`


