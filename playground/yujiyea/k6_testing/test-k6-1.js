import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 10,
    duration: '20s',

    thresholds: {
        http_req_duration: ['p(99)<1500'],
    },
};

export default function () {
    const payload = JSON.stringify({
        name: 'lorem',
        surname: 'ipsum',
    });
    const headers = { 'Content-Type': 'application/json' };
    const res = http.post('https://httpbin.test.k6.io/post', payload, { headers });

    check(res, {
        'Post status is not 200': (r) => res.status !== 200
    })
}