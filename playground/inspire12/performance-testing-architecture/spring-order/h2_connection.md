> 이 글은 테스트 진행 시 H2 DB 연결에 대해 가이드 라인 입니다. (필수 X, 선택 O)

## H2 DB 연결

아래와 같이 `application.yml` 파일을 작성한다.

```yaml
spring:
  datasource:
    url: 'jdbc:h2:mem:order_perf'
    username: 'user'
    password: ''
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: true
        show_sql: true
  sql:
    init:
      mode: never
  h2:
    console:
      enabled: true
      path: /h2-console
```

그런 다음, 서버를 실행(RUN)한 뒤, "http://localhost:8080/h2-console" 를 입력한다.  그러면 아래와 같이 화면이 보이게 된다.

Test Connection 버튼을 클릭하면 'Test successful' 이라는 성공 표시가 나오게 된다.

![](../img/h2_console.png)

그리고 'Connect' 버튼을 클릭하면, 내가 선언한 엔티티 클래스들이 아래와 같이 표시된다.

![](../img/h2_console_connect_success.png)

## IntelliJ

인텔리제이에서는 아래와 같은 순서로 진행하면 된다.

> 순서: 우측 상단 Database → “+”(New) → Data Source → H2

![](../img/h2_console_intellij_connect.png)