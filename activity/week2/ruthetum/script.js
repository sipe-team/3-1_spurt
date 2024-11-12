import http from "k6/http";
import { sleep } from 'k6';

export const options = {
    vus: 50,
    duration: '300s',
    noConnectionReuse: true,
}

export default async function() {
    const url = 'http://localhost:8080/external/countries';
    call(url)
}

async function call(url) {
    http.get(url);
}