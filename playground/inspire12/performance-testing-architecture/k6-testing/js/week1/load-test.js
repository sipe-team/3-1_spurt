import http from 'k6/http';
import { check, sleep } from 'k6';
import { Trend, Rate } from 'k6/metrics';

const getTrend = new Trend('Get_books');
const getErrorRate = new Rate('Get_books_error');

const postTrend = new Trend('Add_Book');
const postErrorRate = new Rate('Add_Book_error');

const orderTrend = new Trend('Add_Order');
const orderErrorRate = new Rate('Add_Order_error');

export let options = {
  stages: [
      { duration: "10s", target: `${__ENV.USERS}` },
      { duration: "100s", target: `${__ENV.USERS}` },
      { duration: "10s", target: 0 }
  ]
};

export default function () {
  const url = `http://nginx:4000/spring-${__ENV.TYPE}/`

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
      'Add_Book': {
        method: 'POST',
        url: url+'books',
        params: params,
        body: addBookBody,
      },
      'Add_Order': {
        method: 'POST',
        url: url + 'orders?bookIsbn=11111111&firstName=Gaetano',
        params: params,
        body: null
      }
    };

  const responses = http.batch(requests);
  const getResp = responses['Get_books'];
  const postResp = responses['Add_Book'];
  const addOrderResp = responses['Add_Order'];

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