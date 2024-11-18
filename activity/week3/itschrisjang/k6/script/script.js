import http from "k6/http";
import { sleep } from 'k6';

export const options = {
    vus: 50,
    duration: '60s',
    noConnectionReuse: true,
}

export default async function() {
    const url = 'http://localhost:8080/api/orders/1';
    call(url)    
}

async function call(url) {
    http.get(url);
}