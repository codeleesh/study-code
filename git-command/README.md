# git 관련 내용 정리

## 기존 git remote repo 제거 및 새로 연결

```git
git remote remove origin
git remote add origin $GIT_URL #GIT_URL http://127.0.0.1/source.git
git remote -v
git add .
git commit -m "init commit"
git push -u origin master
```

## git에서 특정 파일 및 폴더 형상관리 하지 않는 방법
### 로컬과 원격 모두 삭제
#### 파일인 경우

```git
git rm filename
```

#### 폴더인 경우

```git
git rm -f foldername
```

### 원격지만 삭제
#### 파일인 경우

```git
git rm --cached filename
```

#### 폴더인 경우

```git 
git rm --cached foldername
```