import http from 'k6/http';
import { check } from 'k6';

export const options = {
    discardResponseBodies: true,
    executor: 'ramping-arrival-rate',
    stages: [
        {duration: '10s', target: 100},
        {duration: '30s', target: 300},
        {duration: '30s', target: 300},
        {duration: '10s', target: 50}
    ]
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