import http from 'k6/http';
import { check, sleep } from 'k6';

//100명의 사용자의 1분동안 동시 요청 수행
export const options = {
    vus: 100,
    duration: '1m'
};

const users = Array.from({ length: 100 }, (_, i) => ({
    id: i + 1,
}));

export default function () {
    const user = users[__VU - 1];

    //async의 경우
    const res = http.get(`http://localhost:8080/api/orders/async/${user.id}`);

    check(res, {
        'status was 200': (r) => r.status === 200
    });

    sleep(1);
}