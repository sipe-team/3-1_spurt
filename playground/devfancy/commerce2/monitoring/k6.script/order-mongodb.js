import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    discardResponseBodies: true,
    scenarios: {
        orderApi: {
            executor: 'constant-vus',
            vus: 500,
            duration: '180s',
        },
    },
};

export default function () {
    http.get('http://localhost:8080/api/orders/mongodb/1');
    sleep(0.5);
}