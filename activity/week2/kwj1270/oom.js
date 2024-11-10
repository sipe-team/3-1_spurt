import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  preAllocatedVUs: 2,
  vus: 50,
  rate: 30,
  timeUnit: '1s',
  duration: '60s',
  executor: 'constant-arrival-rate',
  discardResponseBodies: true,
  noConnectionReuse: true,
};

export default function () {
  const testurl = 'http://localhost:8080/external/countries';
  http.get(testurl);
}