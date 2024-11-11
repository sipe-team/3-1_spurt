import http from 'k6/http';

export const options = {
    discardResponseBodies: true,
    noConnectionReuse: true,
    scenarios: {
        contacts: {
            executor: 'constant-arrival-rate',
            duration: '60s',
            rate: 30,
            timeUnit: '1s',
            preAllocatedVUs: 2,
            maxVUs: 50,
        },
    },
};

export default function () {
    http.get('http://host.docker.internal:8080/external/countries');
}