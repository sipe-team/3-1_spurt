import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    discardResponseBodies: true,
    scenarios: {
        oom: {
            executor: 'constant-vus',
            vus: 50,
            duration: '60s',
        }
    },
    noConnectionReuse: true,
};

export default function () {
    const testUrl = 'http://localhost:8080/api/external/countries';
    http.get(testUrl);
    sleep(1);
}