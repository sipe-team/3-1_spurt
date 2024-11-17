import http from 'k6/http';
import { check } from 'k6';

export const options = {
    discardResponseBodies: true,
    stages: [
        {duration: '10s', target: 100},
        {duration: '30s', target: 300},
        {duration: '1m', target: 300},
        {duration: '10s', target: 50}
    ]
};

export default async function (){
    const baseUrl = 'http://host.docker.internal:8080/api/orders/1';
    const res = http.get(baseUrl);
    check(res, {
        'Post status is not 200': (r) => res.status !== 200
    })
}