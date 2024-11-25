import http from 'k6/http';
import { check } from 'k6';

export const options = {
    discardResponseBodies: true,
    stages: [
        {duration: '10s', target: 10},
        {duration: '30s', target: 130},
        {duration: '1m', target: 130},
        {duration: '10s', target: 10}
    ]
};

export default async function (){
    const baseUrl = 'http://host.docker.internal:8080/api/orders/1';
    const res = http.get(baseUrl);
    check(res, {
        'Post status is not 200': (r) => res.status !== 200
    })
}