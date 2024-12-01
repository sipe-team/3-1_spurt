import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    discardResponseBodies: true,
    scenarios: {
        orderApi: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '60s', target: 500 },  // 1분 동안 0에서 500까지 증가
                { duration: '120s', target: 1000 }, // 2분 동안 1000명 유지
                { duration: '60s', target: 500 },  // 1분 동안 500으로 감소
                { duration: '60s', target: 0 },    // 1분 동안 0으로 감소
            ],
            gracefulRampDown: '30s', // 램프 다운 동안 VU들이 작업을 완료하도록 설정
        },
        thresholds: {
            http_req_duration: ['p(95)<200'], // 95% 요청이 200ms 미만이어야 함
            http_req_failed: ['rate<0.01'],   // 실패율이 1% 미만이어야 함
        },
    },
};

export default function () {
    http.get('http://localhost:8080/api/orders/1');
    sleep(0.5);
}