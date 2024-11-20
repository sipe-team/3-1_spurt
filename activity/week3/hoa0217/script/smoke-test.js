import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 50, // Key for Smoke test. Keep it at 2, 3, max 5 VUs
  duration: '1m', // This can be shorter or just a few iterations
};

export default () => {
  // k6 run --out influxdb=http://localhost:8086/k6 smoke-test.js
  const urlRes = http.get('http://localhost:8080/api/v1/order/1');
  sleep(1);
  // MORE STEPS
  // Here you can have more steps or complex script
  // Step1
  // Step2
  // etc.
};