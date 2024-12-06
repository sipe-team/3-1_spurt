import http from 'k6/http';

export const options = {
    preAllocatedVUs: 2,
    vus: 50,
    rate: 30,
    timeUnit: '1s',
    duration: '60s',
    discardResponseBodies: true,
    noConnectionReuse: true,
};

export default function () {
    const url = 'http://localhost:12290/display/orders/1';

    http.get(url);
}
