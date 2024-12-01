import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';

const getTrend = new Trend('Get_Orders_Join');
const getErrorRate = new Rate('Get_Orders_Join_error');

export let options = {
  stages: [
      { duration: "10s", target: `${__ENV.USERS}` },
      { duration: "100s", target: `${__ENV.USERS}` },
      { duration: "10s", target: 0 }
  ]
};

export default function () {
  const url = `http://nginx:4000/spring-order/`

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const requests = {
      'Get_Orders_Join': {
        method: 'GET',
          url: url +'api/orders/join/1',
        params: params,
      }
    };

  const responses = http.batch(requests);
  const getResp = responses['Get_Orders_Join'];

  check(getResp, {
    'status is 200': (r) => r.status === 200,
  }) || getErrorRate.add(1);

  getTrend.add(getResp.timings.duration);

}