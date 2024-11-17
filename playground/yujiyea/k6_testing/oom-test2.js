import http from 'k6/http';

export const options = {
    discardResponseBodies: true,
    scenarios: {
        contacts: {
            executor: 'constant-arrival-rate',
            rate: 50,
            duration: '1m',
            preAllocatedVUs: 2,
            maxVUs: 50,
        }
    },
    noConnectionReuse: true,
};

export default function () {
    const testurl = 'http://host.docker.internal:8080/external/countries';
    http.get(testurl);
}