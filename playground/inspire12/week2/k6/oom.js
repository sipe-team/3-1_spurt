import http from 'k6/http';


export const options = {
    vus: 50,
    duration: '60s',
    executor: 'constant-arrival-rate',
    discardResponseBodies: true,
    noConnectionReuse: true,
};

export default function () {
    const testurl = 'http://localhost:8080/external/countries';
    http.get(testurl);
}