## k6, grafana, influxdb 환경 세팅

> 아래는 localhost 에서 k6, grafana, influxdb 환경 세팅을 구성한 내용입니다.

> 사전 작업

influx db를 설치하는 방법(macOS)

* influxdb 설치: brew install influxdb

* influxdb 실행: brew services start influxdb

* influxdb 서버 상태 확인: influxd

* 포트 설정 확인: lsof -i :8086

* influxdb 클라이언트 연결 확인: `curl http://localhost:8086/health`

---

* 아래와 같이 influxdb, grafana 를 위한 `docker-compose.yml` 파일을 작성한다.

```yaml
version: "3.7"

services:
  influxdb:
    image: bitnami/influxdb:1.8.5
    container_name: influxdb
    ports:
      - "8087:8086"
      - "8088:8088"
    environment:
      - INFLUXDB_ADMIN_USER_PASSWORD=devfancy
      - INFLUXDB_ADMIN_USER_TOKEN=devfancyToken
      - INFLUXDB_HTTP_AUTH_ENABLED=false
      - INFLUXDB_DB=myk6db
    networks:
      - my_network

  grafana:
    image: bitnami/grafana:latest
    container_name: grafana
    ports:
      - "3000:3000"
    networks:
      - my_network

networks:
  my_network:
```

* 해당 컨테이너를 실행한다.

    * 명령어: docker-compose up -d

    * 해당 docker-compose.yml 파일이 `commerce` 모듈 안에 있으면 해당 이름으로 컨테이너가 생성된다.

* 실행 중인 컨테이너 확인

    * 명령어: docker ps

---

* grafana 접속: `http://localhost:3000`

    * 아이디, 비밀번호: admin


* 아래와 같이 Data source 부분 클릭

![](/image/grafana_datasource.png)


* 이후 database를 `InfluxDB`로 선택한다.

![](/image/grafana_influxdb.png)

* 아래와 같이 url과 database를 설정한다. 두 컨테이너 모두 도커 위에서 돌아가고 있으므로, `http://localhost:8086`이 아니라 `http://influxdb:8086`으로 설정을 해줘야 한다.
  (만약 다른 컨테이너 이름이라면 해당 이름을 Name 부분에 입력한다)

![](/image/grafana_influxdb_setting.png)

* “Save & test” 버튼을 누르면 아래와 같이 나온다.

![](/image/grafana_influxdb_save_test.png)

* 그리고 나서 Dashboards 를 import 한다.

![](/image/grafana_dashboards_import.png)

* InfluxDB 1.x: 대시보드 ID는 2587이므로 해당 부분을 입력하고 `Load` 버튼을 클릭한다.

![](/image/grafana_dashboards_import_setting.png)

* 마지막으로 k6 부분에 influxdb를 선택하고 `Import` 버튼을 누른다.

![](/image/grafana_dashboards_import_result.png)

* 이제 k6의 결과를 influxdb로 export하면 시각화를 할 수 있다.

    * k6 스크립트가 있는 위치로 이동한 뒤 k6를 실행한다. (e.g. 3-1_spurt/playground/devfancy/commerce2/monitoring/k6.script)

    * 명령어: k6 run --out influxdb=http://localhost:8087/myk6db order-load.js

![](/image/grafana_k6_test_result.png)