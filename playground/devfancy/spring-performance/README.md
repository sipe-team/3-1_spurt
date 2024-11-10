## K6

## InfluxDB & Grafana Docker-Compose로 실행하기

* docker-compose를 사용하면 Grafana와 InfluxDB를 쉽게 설치할 수 있다.

* docker-compose.yml은 [k6 공식 레포지토리](https://github.com/grafana/k6/blob/master/docker-compose.yml)에서 확인할 수 있다.

```bash
$ cd docker
$ docker-compose up -d influxdb grafana

# 위의 명령어를 입력하면 아래와 같이 나온다.
[+] Running 4/4
 ✔ Network docker_grafana       Created                                                   0.0s 
 ✔ Network docker_k6            Created                                                   0.0s 
 ✔ Container docker-influxdb-1  Started                                                   0.7s 
 ✔ Container docker-grafana-1   Started                                                   2.0s 
```

* 터미널에서 위의 명령어를 입력해서 실행시키면, `http://localhost:3000/` 에서 Grafana 대시보드를 확인할 수 있다.

![](/img/grafana-dashboard.png)

* Grafana는 docker-compose에 의해 시작된 로컬 InfluxDB에서 데이터를 읽도록 구성되어 있다.

* 이제 k6를 실행시켜서 InfluxDB에 데이터를 저장하고 시각화하려면, `/k6-scrips/first_scripts.js` 파일을 아래의 명령어로 실행시킨다.

```bash
# 현재 위치: docker
$ docker-compose run k6 run /scripts/first_scripts.js
```

* 위의 명령어를 입력하고 실행하면 k6 스크립트가 실행되고, 결과가 콘솔에 출력된다.

* 그리고 결과 데이터는 InfluxDB에 저장되며, Grafana에서 시각화할 수 있다.


## Reference

* [[공식문서] Grafana Labs - Install k6](https://grafana.com/docs/k6/latest/set-up/install-k6/)

* [[공식문서] Grafana Labs - Fine-tune OS](https://grafana.com/docs/k6/latest/set-up/fine-tune-os/#fine-tune-os)

* [[공식문서] Grafana Labs - Grafana dashboards](https://grafana.com/docs/k6/latest/results-output/grafana-dashboards/)

* [[공식문서] influxdb](https://docs.influxdata.com/influxdb/v2/)

* [[Github] grafana/k6 - docker-compose.yml](https://github.com/grafana/k6/blob/master/docker-compose.yml)

* [[Github] K6 도구 소개](https://github.com/schooldevops/k6-tutorials/blob/main/UsingK6/99_K6_Seminar.md)

* [[Velog] Spring Boot로 K6 & Grafana를 활용한 부하테스트 해보기](https://velog.io/@eastperson/Spring-Boot-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-K6-Grafana%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EB%B6%80%ED%95%98%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%95%B4%EB%B3%B4%EA%B8%B0)