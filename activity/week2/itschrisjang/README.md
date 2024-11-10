# SIPE 3기 1차 미션: 스프링 퍼포먼스 트랙 - 2주차
> 선택한 도구에 대한 학습 및 실습

> k6 학습 및 실습
> 설정 방법 또는 실습에 사용한 스크립트는 주차 활동(activity) 디렉토리에 추가해주세요~!
> 각자가 선택한 모니터링&프로파일러 도구를 통해 메트릭 측정 및 병목 파악해보기

### k6란?
Grafana Labs가 운영하는 오픈소스 성능 테스트 도구로, JMeter 보다는 최근인 2017년에 출시되었습니다. 그런데도 불구하고 k6는 뛰어난 성능과 개발 편의성을 발판으로 빠르게 점유율을 높여나가고 있습니다. CLI에 친숙한 개발자라면 오히려 더 쉽게 설치와 세팅이 가능하고, k6 공식문서도 현대적이면서, 깔끔한 Demo 코드도 지원하고 있기 때문에 빠르게 설치하고 테스트를 할 수 있습니다.
![](image/k6_log.png)

### 1. 간단한 설치 및 테스트
- JMeter는 설치가 쉬우며 Homebrew를 통해 빠르게 설치할 수 있지만, Java 설치와 환경변수 설정이 필요하고 일부 테스트에서는 추가 플러그인 설치가 필요하여 번거로울 수 있습니다. 그에 반해, k6는 해당 작업이 필요 없습니다.

### 2. 스크립트의 용이성
- JMeter는 스크립트를 Import/Export할 때 XML 기반으로 관리가 가능합니다. 하지만, 테스트 시나리오를 파악하려면 수백 라인의 XML을 읽거나 GUI로 Import해야 이해가 쉽습니다.
- 반면에 k6는 웹 개발자에게 친숙한 JavaScript로 작성되어 있어 테스트 시나리오를 쉽게 확인할 수 있습니다.

#### 공식문서 Demo Code
```javascript
import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 10,                // 10 Users
    duration: '30s',        
};

// 1. init Code
export function setup() {
    // 2. Setup Code
}

export default function () {
    // 3. VU Code
    http.get('https://test.k6.io');
    sleep(1);
}
```
### 4. 성능
k6는 다음 두 가지 대표적인 장점을 가지고 있습니다.
1. Go 언어로 작성된 뛰어난 처리 성능: k6 라이브러리는 Go 언어로 작성되어 있어, 더 적은 수의 로드 생성기로도 많은 사용자를 만들어낼 수 있습니다.
2. Java 의존성 제거: JMeter는 Java에 의존하여 설치 및 최소 메모리 요구 사항이 있습니다. JMeter 공식 문서에서는 Java Heap Memory 확보를 위해 1GB 이상의 메모리를 권장합니다. k6는 JavaScript 파일을 실행하기 때문에 Java 설치가 필요 없으며 고정 메모리 할당 부담이 적습니다. AWS EC2 인스턴스에서 메모리 비용을 절감할 수 있어 최적화된 비용으로 테스트를 수행할 수 있습니다.

### 5. 의존적인 플러그인
JMeter는 GUI 또는 테스트에 필요한 플러그인을 추가로 설치해야 하며, 환경이 바뀔 때마다 다시 설치하는 번거로움이 있습니다.
반면, k6는 JavaScript 라이브러리로 필요한 기능이 대부분 기본 제공되며 import 후 바로 사용할 수 있습니다.


#### k6 Options
k6는 테스트 코드에 작성된 option 조건대로 테스트가 수행됩니다.
option은 시나리오에 따라 설정을 직접 할 수 있고, 설정할 수 있는 필드도 많기 때문에 [여기에서](https://k6.io/docs/using-k6/options) 읽어보시길 바라며, 저희 팀이 간단하게 설정했던 옵션은 아래와 같습니다.

```javascript
// 모수는 테스트마다 변경하며 진행하였고, 아래 코드는 예시로 작성되어있습니다.

export const options = {
    batchPerHost: 10,
    scenarios: {
        payment_scenario: {
            vus: 100,
            exec: 'payment_scenario',
            executor: 'per-vu-iterations',
            iterations: 1
        }       
    }
};
```
- batchPerHost
동일한 호스트 이름에 동시/병렬 연결의 최대수를 가상 사용자에게 세팅합니다.
- scenarios
실행할 시나리오를 나열합니다. 각 시나리오는 시나리오의 설정대로 다르게 동작할 수 있습니다. 저희는 결제 시나리오대로 각 사용자별로 딱 한 번만 진행하면 되었기에 위와 같이 작성하였습니다.
[Scenarios Documents](https://k6.io/docs/using-k6/scenarios/)
- vus
테스트할 가상 사용자를 설정합니다.
- executor
k6에는 테스트 컨셉의 다양한 로드 발생기가 있습니다. 아래는 로드 발생기의 리스트와 간단한 설명을 공유드립니다. 자세한 설명은 [Executors Documents](https://k6.io/docs/using-k6/executors)에서 읽어보신 후, 용도에 맞게 도입을 해보시기 바랍니다.
[k6_executors_documents](image/k6_executors_documents.png)
저희는 사용자들이 정해진 시간에 상관없이 딱 한 번의 결제만 이루어지기 때문에, `per-vu-iterations` 모듈에 1회 반복으로 설정하여 테스트를 진행하였습니다.

#### k6 lifeCycle
- API 응답을 기다려야 하는 문제  
시나리오대로 결제 API를 호출하려면 사용자의 토큰이 필요했는데, 토큰을 받으려면 “로그인 API”를 호출하여 응답이 오기를 기다려야 합니다. 이 과정을 JavaScript로 작성하기 때문에, `async/await`로 토큰을 응답 받아 처리할 수 있습니다.

```javascript
async function login() {
    let response = await http.asyncRequest('POST', api, JSON.stringify({
        'email': `test@naver.com`,
        'userPwd': '0000'
    }), {
        headers: { 'Content-Type' : 'application/json' }
    });
    
    return response.json().data.token.accessToken;
}
```
하지만 응답을 기다리는 과정(`await`)으로 인해, 3000명의 사용자가 순차적인 테스트를 실행하게 되지만, 실환경에서는 Dynamic하게 각각의 유저가 로그인 ~ 결제를 시도하기 때문에 실환경과 가깝게 테스트 할 수 없었습니다.
이 문제를 해결하기 위해 공식 문서를 읽다가 k6의 Lifecycle을 발견했습니다.

#### Lifecycle functions
> 초기화 코드를 제외하고, 각 단계는 k6 런타임의 특정 시퀀스에 호출되는 라이프사이클 함수가 있습니다.

```javascript
// 1. init code

export function setup() {
  // 2. setup code
}

export default function (data) {
  // 3. Virtual User Scenario code
}

export function teardown(data) {
  // 4. teardown code
}
```
- setup()
k6 런타임 시 최초 한 번 호출되며 데이터를 전처리하거나, 가상 사용자 간 데이터 공유가 필요할 때 사용합니다.
- default function()
일반적인 테스트 시나리오 코드를 이 함수에 작성하며, k6 옵션에 따라 반복 호출됩니다.
- teardown()
예상한 결과를 얻었는지 확인하고 테스트가 완료되었음을 알리는 웹훅 함수입니다.

저희는 이 k6 lifeCycle을 활용하여 `setup()` 함수에서 미리 테스트할 사용자의 로그인 토큰을 생성해놓고 `default function()` 함수로 토큰을 전달하여 이 함수에서는 오직 시나리오대로 API를 Dynamic하게 호출하였습니다.

그 결과 실환경과 최대한 비슷한 환경을 만들 수 있었고, 아래는 저희가 테스트하고자 하는 방향과 예시 코드입니다.
[k6_payment_scenario_lifecyle](image/k6_payment_scenario_lifecyle.png)
```javascript
import http from 'k6/http';

export const options = {
    // ...
};

export function setup() {
  // 토큰 생성 함수 호출
    const tokens = generateTokens(requests);

    return { tokens };
}

export function payment_scenario({ tokens }) {
  // 생성한 토큰들을 각 가상 사용자 시퀀스를 이용하여 할당
    const token = tokens[__VU - 1];

  // 시나리오 API Start

  mainProductApi(token); // 메인 페이지의 상품 관련 Api

  productDetailApi(__VU, productId); // 상품의 상세정보 관련 Api

  paymentDetailApi(__VU, productId); // 상품 결제 상세창 관련 Api

  prePaymentApi(__VU, token, productId); // 상품 점유 Api

  paymentApi(__VU, token, productId); // 상품 결제 Api

  paymentWebhook(__VU, token, productId); // 상품 최종 결제 정산 Api
}

/** 
 * k6 http 모듈을 활용한 POST 요청 예시
 */
function paymentApi(vu, token, productId) {
  const api = `${host}/payment`;
        http.asyncRequest('POST', api, JSON.stringify({
         "productId": productId,
         "pg": "kcp.IP2RK",
     "payMethod": "card",
     }), {
     headers: { 
         'Authorization': `Bearer ${token}`,
         'Content-Type' : 'application/json',
         'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36' 
     }
    });
}

export function teardown(data) {
  // 정산 확인 Api 호출
  paymentWebhook(__VU, token, productId);
}
```

#### REFERENCE
[월급쟁이부자들의 부하테스트를 위한 k6 도입기](https://weolbu.medium.com/%EC%9B%94%EA%B8%89%EC%9F%81%EC%9D%B4%EB%B6%80%EC%9E%90%EB%93%A4%EC%9D%98-%EB%B6%80%ED%95%98%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%A5%BC-%EC%9C%84%ED%95%9C-k6-%EB%8F%84%EC%9E%85%EA%B8%B0-d7c82e7fe65f)
[k6 공식 문서](https://grafana.com/docs/k6/latest/)
