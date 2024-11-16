# 1. 결과 
## 1.1. 스크립트

```js
import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  preAllocatedVUs: 2,
  vus: 50,
  rate: 30,
  timeUnit: '1s',
  duration: '60s',
  executor: 'constant-arrival-rate',
  discardResponseBodies: true,
  noConnectionReuse: true,
};

export default function () {
  const testurl = 'http://localhost:8080/external/countries';
  http.get(testurl);
}
```
[스크립트 예시](./oom.js)

## 1.2. 메트릭

![image.png](/.asset/kwj1270/week2-1.png)

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

WARN[0000] There were unknown fields in the options exported in the script  error="json: unknown field \"executor\""
  execution: local
     script: oomtest.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 1m30s max duration (incl. graceful stop):
           * default: 50 looping VUs for 1m0s (gracefulStop: 30s)

^C
running (1m02.2s), 00/50 VUs, 9179 complete and 49 interrupted iterations
default ✗ [======================================] 50 VUs  1m0s

     data_received..................: 124 MB 2.0 MB/s
     data_sent......................: 1.1 MB 17 kB/s
     http_req_blocked...............: avg=391.69µs min=58µs     med=278µs    max=16.03ms p(90)=559µs    p(95)=920.39µs
     http_req_connecting............: avg=325.61µs min=43µs     med=225µs    max=15.98ms p(90)=485.2µs  p(95)=758.09µs
     http_req_duration..............: avg=230.66ms min=74.69ms  med=190.04ms max=10.4s   p(90)=349.28ms p(95)=444ms
       { expected_response:true }...: avg=225.03ms min=114.37ms med=191.95ms max=10.4s   p(90)=335.49ms p(95)=406.81ms
     http_req_failed................: 12.12% ✓ 1113       ✗ 8066
     http_req_receiving.............: avg=126.74µs min=18µs     med=73µs     max=14.62ms p(90)=178µs    p(95)=309µs
     http_req_sending...............: avg=46.92µs  min=7µs      med=29µs     max=14.85ms p(90)=56µs     p(95)=80µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s      p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=230.49ms min=74.55ms  med=189.87ms max=10.4s   p(90)=349.14ms p(95)=443.9ms
     http_reqs......................: 9179   147.459534/s
     iteration_duration.............: avg=231.12ms min=75.2ms   med=190.41ms max=10.4s   p(90)=349.89ms p(95)=444.27ms
     iterations.....................: 9179   147.459534/s
     vus............................: 49     min=49       max=50
     vus_max........................: 50     min=50       max=50
```
- executor 가 Unkown 이어서 생각한 엔진으로 실행은 되지 않았음 
- 에러율은 12% 이지만, 시간이 지날수록 모든 요청을 수행하지도 못함 
- 응답 시간도 평균 230.66ms 이지만, max 가 max=10.4s 로 지연이 크게 발생함(90 -> 95 수치 증가)


# 2. 원인 파악

## 2.1. 라이브 스레드 증가 
![image.png](/.asset/kwj1270/week2-2.png)
![image.png](/.asset/kwj1270/week2-3.png)
