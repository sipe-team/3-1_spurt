import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';

const getTrend = new Trend('Get_books');
const getErrorRate = new Rate('Get_books error');

const postTrend = new Trend('Add Book');
const postErrorRate = new Rate('Add Book error');

const orderTrend = new Trend('Add Order');
const orderErrorRate = new Rate('Add Order error');

export let options = {
  stages: [
      { duration: "10s", target: `${__ENV.USERS}` },
      { duration: "100s", target: `${__ENV.USERS}` },
      { duration: "10s", target: 0 }
  ]
};

export default function () {
  const url = `http://localhost:${__ENV.PORT}/`

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const addBookBody = JSON.stringify({
      author: `Author Name ${__ITER}`,
      isbn: `${__VU}`,
      title: `always the same title`,
      year: 1900
  });

  const requests = {
      'Get_books': {
        method: 'GET',
        url: url +'books',
        params: params,
      },
      'Add Book': {
        method: 'POST',
        url: url+'books',
        params: params,
        body: addBookBody,
      },
      'Add Order': {
        method: 'POST',
        url: url + 'orders?bookIsbn=11111111&firstName=Gaetano',
        params: params,
        body: null
      }
    };

  const responses = http.batch(requests);
  const getResp = responses['Get_books'];
  const postResp = responses['Add Book'];
  const addOrderResp = responses['Add Order'];

  check(getResp, {
    'status is 200': (r) => r.status === 200,
  }) || getErrorRate.add(1);

  getTrend.add(getResp.timings.duration);

  check(postResp, {
    'status is 200': (r) => r.status === 200,
  }) || postErrorRate.add(1);

  postTrend.add(postResp.timings.duration);

  check(addOrderResp, {
    'status is 200': (r) => r.status === 200,
  }) || orderErrorRate.add(1);

  orderTrend.add(addOrderResp.timings.duration);
}