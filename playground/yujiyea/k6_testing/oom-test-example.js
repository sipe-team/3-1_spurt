import http from "k6/http";
import { check } from 'k6';

export const options = {
    vus: 50,
    duration: '60s',
    noConnectionReuse: true,
}

export default async function() {
    const baseUrl = 'http://host.docker.internal:8080/external/countries';
    const res = http.get(baseUrl);
    check(res, {
        'Post status is not 200': (r) => res.status !== 200
    })
}