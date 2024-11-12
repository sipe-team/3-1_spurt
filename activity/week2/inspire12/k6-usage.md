# k6
k6 는 Javascript 파일로 테스트 스크립트를 작성 

_## 주요 개념 
- 
- 성능 기록 
  - Trend
  - Counter_

## Test Lifecycle
[출처](https://grafana.com/docs/k6/latest/using-k6/test-lifecycle)
### The init stage
테스트 옵션 설정, 함수 정의, 모듈 임포트 등 준비 작업을 수행합니다.

```javascript
import http from 'k6/http';
import { check, sleep } from 'k6';
```
```javascript
export let options = {
  stages: [
    { duration: '2m', target: 50 }, // 2분 동안 VU 수를 50까지 점진적으로 증가
    { duration: '5m', target: 50 }, // 5분 동안 VU 수를 50으로 유지
    { duration: '2m', target: 0 },  // 2분 동안 VU 수를 0까지 점진적으로 감소
  ],
};
```

### The VU stage: 가상 사용자(VU) 코드 실행 단계
각 VU는 default 함수 또는 시나리오에서 지정한 함수를 반복적으로 실행합니다.
이 단계에서 실제 테스트 로직이 수행됩니다.
```javascript
export default function () {
    const url = `http://nginx:4000/spring-${__ENV.TYPE}/`

    const requests = {
        'Get_books': {
            method: 'GET',
            url: url + 'books/simple',
            params: params,
        }
    };
    const responses = http.batch(requests);
    const getResp = responses['Get_books'];

    check(getResp, {
        'status is 200': (r) => r.status === 200,
    }) || getErrorRate.add(1);

    getTrend.add(getResp.timings.duration);
}
```
### Setup and teardown stages
- setup: 테스트 전에 한 번 실행되어, 준비 작업과 초기 데이터를 설정합니다.
- teardown: 모든 테스트가 끝난 후 한 번 실행되어, 리소스 해제나 후처리를 수행합니다.
```javascript
export function setup() {
  const res = http.get('https://httpbin.test.k6.io/get');
  return { data: res.json() };
}

export function teardown(data) {
  console.log(JSON.stringify(data));
}
```
skip 도 가능
```bash
k6 run --no-setup --no-teardown ...
```
## 모듈

| Module        | Description                                                                                   |
|---------------|-----------------------------------------------------------------------------------------------|
| `http`        | HTTP 요청을 보내기 위한 모듈입니다. GET, POST, PUT 등 다양한 HTTP 메서드를 지원하며, 응답을 처리할 수 있습니다. |
| `check`       | 응답 데이터를 검증하는 데 사용합니다. 조건을 설정하여 테스트가 통과했는지 확인할 수 있습니다. |
| `sleep`       | 특정 시간 동안 대기하는 기능을 제공합니다. 테스트 간 대기 시간을 설정할 때 사용합니다.         |
| `group`       | 테스트를 그룹으로 묶어, 테스트 시나리오를 더 체계적으로 구성할 수 있도록 합니다.              |
| `fail`        | 특정 조건이 충족되지 않으면 테스트를 강제로 실패시키고, 에러 메시지를 출력합니다.            |
| `SharedArray` | 여러 VU(가상 사용자) 간에 데이터를 공유할 때 사용하는 데이터 구조입니다.                      |
| `crypto`      | 암호화 관련 기능을 제공합니다. 해시 생성 등 보안 관련 작업에 사용할 수 있습니다.             |
| `execution`   | 현재 실행 중인 VU의 정보, 반복 횟수 등을 가져올 수 있는 모듈입니다.                          |
| `k6/metrics`  | 사용자 정의 메트릭을 만들 때 사용하는 모듈입니다. Counter, Gauge, Trend 등의 메트릭을 정의할 수 있습니다. |
| `k6/ws`       | WebSocket 연결을 관리하고 테스트할 때 사용하는 모듈입니다.  

## 실행 

``` bash
k6 run --vus 10 --duration 30s script.js
```
### 옵션
[출처](https://grafana.com/docs/k6/latest/misc/archive/) 

| 옵션명                 | 설명                                                                                   | 설정 방법                                                                                   |
|------------------------|----------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| `vus`                  | 가상 사용자(Virtual Users)의 수를 지정합니다.                                           | 스크립트: `options.vus = 10;`<br>명령줄: `--vus 10`<br>환경 변수: `K6_VUS=10`               |
| `duration`             | 테스트 실행 시간을 설정합니다. 예: `30s`, `1m` 등.                                      | 스크립트: `options.duration = '30s';`<br>명령줄: `--duration 30s`<br>환경 변수: `K6_DURATION=30s` |
| `iterations`           | 총 실행할 반복 횟수를 지정합니다.                                                       | 스크립트: `options.iterations = 100;`<br>명령줄: `--iterations 100`<br>환경 변수: `K6_ITERATIONS=100` |
| `stages`               | 부하를 단계적으로 증가 또는 감소시키는 설정을 정의합니다.                               | 스크립트: `options.stages = [{ duration: '10s', target: 20 }];`                              |
| `thresholds`           | 성능 기준을 설정하여, 테스트 성공 여부를 판단합니다.                                    | 스크립트: `options.thresholds = { http_req_duration: ['p(95)<500'] };`                       |
| `tags`                 | 메트릭에 추가적인 정보를 제공하기 위한 태그를 설정합니다.                               | 스크립트: `options.tags = { environment: 'staging' };`                                      |
| `summaryTrendStats`    | 요약 보고서에 표시할 통계 항목을 지정합니다.                                            | 스크립트: `options.summaryTrendStats = ['avg', 'p(95)', 'max'];`                            |
| `noVUConnectionReuse`  | 각 VU가 새로운 TCP 연결을 사용하도록 설정합니다.                                        | 명령줄: `--no-vu-connection-reuse`<br>환경 변수: `K6_NO_VU_CONNECTION_REUSE=true`           |
| `insecureSkipTLSVerify`| TLS 인증서 검증을 건너뜁니다.                                                          | 명령줄: `--insecure-skip-tls-verify`<br>환경 변수: `K6_INSECURE_SKIP_TLS_VERIFY=true`       |
| `httpDebug`            | HTTP 요청 및 응답에 대한 디버그 정보를 출력합니다.                                      | 명령줄: `--http-debug`<br>환경 변수: `K6_HTTP_DEBUG=true`                                   |




## Reference
[공식 문서](https://k6.io)
[실행 옵션](https://grafana.com/docs/k6/latest/misc/archive/) 