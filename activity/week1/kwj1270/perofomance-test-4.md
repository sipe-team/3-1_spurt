# 성능 테스트 도구 비교 
# 1. 성능 테스트 도구들
- nGrinder: 
  - Naver에서 개발하고 오픈소스화 하였다. 
  - 사용이 심플하고, UI, 복수개의 부하 생성기를 구성할 수 있다. 
  - Jython, Groovy 스크립트를 사용하여 부하테스트 시나리오를 작성할 수 있다. 
  - [참고 nGrinder](https://naver.github.io/ngrinder/)
- JMeter:
  - 100% 자바로 개발된 오픈소스 성능 테스트 도구
  - 다양한 통신 프로토콜을 지원한다. 
    - HTTP/HTTPS, SOAP/REST, FTP, DATABASE, LDAP, JMS, SMTP 등등
    - IDE 도구를 이용한 화면기반 테스트 수행가능
    - 풍부한 리포트 기능
    - 멀티 쓰레딩 기반 성능 테스트 수행
    - [참고 JMeter](https://jmeter.apache.org/) 
- Gatling:
  - 오픈소스로 강력한 로드 테스팅 솔루션이다. 
  - 지속적인 로드 테스트를 지원하고, 개발 파이프라인에 로드 테스트를 추가할 수 있다. 
  - 웹 레코더를 활용하여 대상 화면에서 쉽게 시나리오를 생성할 수 있다. 
  - 리포트 도구를 제공한다. 
  - Scala, Java, Kotlin 으로 스크립트를 작성할 수 있다. 
  - [참고 Gatling](https://gatling.io/)
- K6:
  - 오픈소스 성능테스트 솔루션이다. 
  - 사용하기가 쉽고, Grafana등과 연동하여 UI를 구성할 수 있다. 
  - CLI툴을 사용하여 성능 테스트를 수행한다. 
  - 로컬 혹은 원격지의 스크립트를 로드하여 테스트 할 수 있다. 
  - Check/Thresholds 를 제공하여 성능 목표를 다양하게 구성할 수 있다. 
  - Python을 이용하여 스크립트를 작성한다. 
  - [참고 K6](https://k6.io/docs/)

```
💡 UI 여부 
- nGrinder, jMeter 등은 부하 테스트 결과를 직접 UI로 확인 가능
- k6는 influxDB, Grafana 등을 구성해야한다.
```
```
💡 Git Star 수 
- JMeter: 6k stars
- Taurus: 1.7k stars
- Locust: 15.7k stars
- nGrinder: 1.3k stars 
- gatling: 5.1k stars
- k6: 11.7k stars
- Tsung: 2.1k stars
- Siege: 4k stars
```
