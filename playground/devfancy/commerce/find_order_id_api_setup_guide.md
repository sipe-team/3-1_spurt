> 사전 조건

* `schema.sql`과 `data.sql` 파일을 작성해 임의의 데이터를 생성할 수 있어야 해요.

* `application.yml` 파일도 참고해 주세요.

* 저는 주문 상세 조회 API에 필요한 데이터만 1개 있으면 충분했기 때문에, data.sql에 하나의 데이터만 추가했어요.

---

## 후보 3

* 응답 객체의 setter를 막고, 각 객체를 생성자 파라미터로 전달해서 응답을 구성해요.
    
    * JSON 응답에 필요한 필드를 한 곳에서 설정하게 돼요. 

    * 각 객체에 대한 필드 설정이 한 파일에 40줄 정도로 길어질 수 있어요. 

    * 도메인별로 응답을 분리하지 않고, 모든 정보를 한 곳에서 요약하는 방식이에요.

* 예를 들어, Postman과 같은 툴로 `http://localhost:8080/api/orders/1` 으로 GET 방식으로 조회하면 아래와 같은 응답값을 보여줘요.

```json
{
    "orderItems": [
        {
            "productBundleDetails": {
                "bundleName": "Bundle Name",
                "bundleQuantity": 10
            },
            "productDetails": {
                "productId": 1,
                "name": "베스트코 왕교자만두 1.4kg",
                "description": "쫄깃한 만두피와 씹는 맛이 살도록 굵게 썰어낸 속재료가 만난 환상적인 식감",
                "price": 7500
            },
            "deliveryDetails": {
                "recipientName": "팬시",
                "mobile": "010-1234-5678",
                "address": "서울특별시 강남구",
                "zipCode": "13579",
                "storePassword": "qwer1234",
                "deliveryMemo": "문 앞에 놔주세요"
            },
            "refundDetails": {
                "refundMethodName": "NAVER_PAY",
                "refundAmount": 7500,
                "refundStatus": "PENDING"
            }
        }
    ]
}
```