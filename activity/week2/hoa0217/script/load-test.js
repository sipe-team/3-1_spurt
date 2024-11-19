import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
  // Key configurations for avg load test in this section
  stages: [{duration: '1m', target: 50}, // traffic ramp-up from 1 to 50 users over ₩ minutes.
    {duration: '5m', target: 50}, // stay at 50 users for 5 minutes
    {duration: '1m', target: 0}, // ramp-down to 0 users
  ],
};

export default () => {
  const urlRes = http.get('http://localhost:8080/external/countries');
  sleep(1);
  // MORE STEPS
  // Here you can have more steps or complex script
  // Step1
  // Step2
  // etc.
};