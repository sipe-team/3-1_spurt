import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';

const getTrend = new Trend('Get_Books');
const getErrorRate = new Rate('Get_Books_error');

export let options = {
    vus: 100,
    iterations: 500,
    maxDuration: '10s'
    // stages: [
      // { duration: "10s", target: `${__ENV.USERS}` }, // Ramp up to users
      // { duration: "100s", target: `${__ENV.USERS}` },  // Spike to users
      // { duration: "10s", target: 0 }  // Ramp down to 0 users
  // ]
};

export default function () {
  const url = `http://nginx:4000/spring-${__ENV.TYPE}/`

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const requests = {
      'Get_books': {
        method: 'GET',
        url: url +'books/simple',
        params: params,
      }
    };

  const responses = http.batch(requests);
  const getResp = responses['Get_books'];

  check(getResp, {
    'status is 200': (r) => r.status === 200,
  }) || getErrorRate.add(1);

  getTrend.add(getResp.timings.duration);

  // const responseSizeBytes = getResp.body.length; // 응답 바디 크기
  // throughput.add(responseSizeBytes);
}