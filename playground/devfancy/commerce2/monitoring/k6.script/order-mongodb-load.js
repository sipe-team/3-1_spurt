import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    discardResponseBodies: true,
    scenarios: {
        orderApi: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '60s', target: 1000 }, // 60초 동안 0 ~ 1000 목표 VU까지 선형적으로 증가시킴
                { duration: '60s', target: 100 }, // 1000개의 VU에서 60초 동안 0의 VU까지 선형적으로 감소시킴
            ],
            gracefulRampDown: '0s', // 램프 다운 단계에서 일부 반복이 중단될 수 있음
        },
    },
};

export default function () {
    http.get('http://localhost:8080/api/orders/mongodb/1');
    sleep(0.5);
}