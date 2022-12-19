# 스케쥴 백엔드 프로그램

### 더미데이터 테스트 API 주소
```txt
http://localhost:8080/join

{
    "username":"ssar",
    "password":"1234",
    "email":"ssar@nate.com"
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

### 체크사항
- fromUser -> toUser 가 같을 수 없다.
- fromUser -> toUser 가 중복될 수 없다.