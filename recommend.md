# 함께 읽어보면 좋은 자료
스터디 내용과 관련하여 참고하면 좋을 글이나 자료들을 모아 놓는 공간입니다.

```markdown
### [제목](링크)
{추천 사유}
```

<br>

### [Oracle - Java 21 Troubleshooting Guide](https://docs.oracle.com/en/java/javase/21/troubleshoot/index.html)
오라클에서 제공하는 공식 자바 트러블슈팅 가이드이고, 버전에 따라 문서가 존재해서 사용하는 버전에 대한 문서를 한 번씩 읽어보면 좋을 것 같아요.

### [[NHN FORWARD 2020] 내가 만든 WebFlux가 느렸던 이유](https://www.youtube.com/watch?v=I0zMm6wIbRI)
조금 오래된 영상이긴한데, 과거에 제가 프로젝트에 WebFlux 도입을 고민했을 때 많이 도움이 됐던 영상이에요.

Spring MVC와 비교하면서 기본적인 WebFlux의 동작 방식에 대한 설명과 함께 WebFlux를 사용 시 코드 레벨에서 주의해야 할 점들을 알려주는 좋은 발표 영상입니다.

### [스프링 성능 확인 관련 블로그 글 (by 영학)](https://inspire12.tistory.com/327) 
스프링 성능 확인 관련해서 영학님이 작성한 블로그 글입니다. 

함께 보면 좋은 아티클: https://www.baeldung.com/spring-6-virtual-threads

### [Let’s use OpenTelemetry with Spring](https://spring.io/blog/2024/10/28/lets-use-opentelemetry-with-spring)
우재님이 공유해주신 OpenTelemetry와 Spring 연동에 관한 글

### [토스의 서버 인프라 모니터링](https://youtu.be/rxurfKT2lD8?feature=shared), [USE Method](http://www.brendangregg.com/usemethod.html)
이것도 우재님이 공유해주신 글로 서버 인프라 모니터링과 USE Method에 대한 글입니다.

### [k6: Test Types](https://grafana.com/docs/k6/latest/testing-guides/test-types/)
한국어로 ‘부하 테스트’라고 검색하면 load test, stress test, spike test 등의 다양한 용어로 표현해서 정리되어 있는 글이 많은데요.

부하 테스트(load testing) 안에서 특수한 목표 및 그에 따른 시나리오를 기준으로 용어를 구분하기도 합니다.

그래서 처음 부하 테스트 학습을 하셔서 여러가지 용어나 정의가 혼동되는 경우 위 글을 한번 읽어보시면 전체적인 윤곽을 잡는데 도움이 될 것 같아요~

### [DEVOCEAN: k6](https://www.youtube.com/live/MqdQc4vd_ws?feature=shared)
저(`우재님`) 같은 경우 K6 라는 성능 테스트 도구를 정말 좋아하는데 일전에 데보션에서 강의 영상이 있었습니다.

저(`우재님`)도 이번 미션 정리하면서 오랜만에 영상 돌려보기 하는데 정말 좋은 내용들이 많아서 공유드립니다.

### [Top 20 Performance Testing Tools in 2024 | BrowserStack](https://www.browserstack.com/guide/performance-testing-tools)
TOP 20 성능 테스트 툴 참고하시면 좋을 거 같네요.

### [인프라: 인프라공방 - 그럴듯한 서비스 만들기](https://www.inflearn.com/course/%EC%9D%B8%ED%94%84%EB%9D%BC-%EA%B3%B5%EB%B0%A9-%EC%84%9C%EB%B9%84%EC%8A%A4-%EB%A7%8C%EB%93%A4%EA%B8%B0?srsltid=AfmBOopQm6Ezb9Lczgh7zeC45W6pf4tgW0QAHFwiPJkz7DaDpM0ekMi3)
추천(?) 강의

### [Comparing JDBC and R2DBC in a real-world scenario](https://github.com/GaetanoPiazzolla/spring-boot-jdbc-vs-r2dbc)
k6 를 이용해서 jdbc, r2dbc 성능 비교하는 프로젝트입니다.

### [코루틴과 Virtual Thread 비교와 사용 | 카카오페이 기술 블로그](https://tech.kakaopay.com/post/coroutine_virtual_thread_wayne/)
코루틴과 Virtual Thread 비교와 사용에 대한 글입니다.


### [JPA 모든 N+1 발생 케이스과 해결책](https://velog.io/@jinyoungchoi95/JPA-%EB%AA%A8%EB%93%A0-N1-%EB%B0%9C%EC%83%9D-%EC%BC%80%EC%9D%B4%EC%8A%A4%EA%B3%BC-%ED%95%B4%EA%B2%B0%EC%B1%85)
Spring jpa에서 연관관계를 쓸때(join) 주의해야할 n+1 성능 문제에 대한 글입니다.

### [jOOQ 소개](https://github.com/lazyconf-dev/2024-lazydevconf/blob/main/presentation/lazydev_%EB%9E%9C%EB%94%A9_%EC%84%B8%EC%85%982_jOOQ,%20SQL%EC%9D%84%20%EC%9E%91%EC%84%B1%ED%95%98%EB%8A%94%20%EB%98%90%EB%8B%A4%EB%A5%B8%20%EB%B0%A9%EB%B2%95.pdf)
jOOQ 소개 자료입니다.

### [마이크로서비스의 외부 API 패턴 (2)](https://medium.com/@greg.shiny82/%EB%A7%88%EC%9D%B4%ED%81%AC%EB%A1%9C%EC%84%9C%EB%B9%84%EC%8A%A4%EC%9D%98-%EC%99%B8%EB%B6%80-api-%ED%8C%A8%ED%84%B4-2-be7888f2857e)
머터럴라이즈드뷰

### [Moduler Monolithic 아키텍처](https://giljae.com/2022/10/13/Moduler-Monolithic-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98.html#google_vignette)
모듈러 모놀리틱 아키텍처에 대한 글입니다.

