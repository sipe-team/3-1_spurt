import http from "k6/http";
import { sleep, check } from 'k6';

export const options = {
    scenarios: {
        constant_rps: {
            executor: 'constant-arrival-rate',
            rate: 1000, // 초당 50 요청
            timeUnit: '1s', // 초 기준
            duration: '1m',
            preAllocatedVUs: 10, // 사전에 할당할 VU 수
            maxVUs: 100, // 최대 VU 수
        },
    },
};

export default function () {
    const res = http.get('http://localhost:8080/api/orders/7');
    check(res, { 'status is 200': (r) => r.status === 200 });
}