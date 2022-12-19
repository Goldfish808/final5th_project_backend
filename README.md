# 스케쥴 백엔드 프로그램

### 남은 일정
- todo 작성 (save)
- schedule 작성 (사진 포함) (save)
- follow, unFollow 하기 (save or update)
- todo finished api 만들기 (update)

### 체크사항
- fromUser -> toUser 가 같을 수 없다.
- fromUser -> toUser 가 중복될 수 없다.

### 더미데이터 테스트 API 주소
```txt
http://localhost:8080/join

{
    "username":"hello",
    "password":"1234",
    "email":"hello@nate.com"
}
```

```txt
http://localhost:8080/login

{
    "username":"ssar",
    "password":"1234"
}
```

```txt
http://localhost:8080/category
```

```txt
http://localhost:8080/s/schedule

토큰필요
```

```txt
http://localhost:8080/s/todo

토큰필요
```

```txt
http://localhost:8080/s/follow

토큰필요
```

