import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
  // Key configurations for avg load test in this section
  stages: [{duration: '1m', target: 100}, // traffic ramp-up from 1 to 50 users over 1 minutes.
    {duration: '5m', target: 100}, // stay at 100 users for 5 minutes
    {duration: '1m', target: 0}, // ramp-down to 0 users
  ],
};

export default () => {
  // k6 run --out influxdb=http://localhost:8086/k6 load-test.js
  const urlRes = http.get('http://localhost:8083/api/v1/display');
  sleep(1);
  // MORE STEPS
  // Here you can have more steps or complex script
  // Step1
  // Step2
  // etc.
};