-- 주문 데이터 삽입
INSERT INTO ORDERS (order_id, delivery_id, refund_id, payment_id, order_number, order_status, created_at, updated_at)
VALUES (1, 1, 1, 1, 12345, 'CONFIRMED', NOW(), NOW());

-- 상품 데이터 삽입 (order_id 포함)
INSERT INTO PRODUCTS (product_id, order_id, product_name, description, price, bundle_name, bundle_quantity, created_at, updated_at)
VALUES (1, 1, '베스트코 왕교자만두 1.4kg', '쫄깃한 만두피와 씹는 맛이 살도록 굵게 썰어낸 속재료가 만난 환상적인 식감', 7500, '크리스마스 선물 세트', 10, NOW(), NOW());

-- 결제 데이터 삽입
INSERT INTO PAYMENTS (id, payment_method, payment_method_name, payment_amount, created_at, updated_at)
VALUES (1, 'BILLING_NAVER_PAY', '네이버', 7500, NOW(), NOW());

-- 배송 데이터 삽입
INSERT INTO DELIVERYS (delivery_id, recipient_name, mobile, address, zip_code, store_password, delivery_memo, created_at, updated_at)
VALUES (1, '팬시', '010-1234-5678', '서울특별시 강남구', '13579', 'qwer1234', '문 앞에 놔주세요', NOW(), NOW());

-- 환불 데이터 삽입
INSERT INTO REFUNDS (refund_id, refund_method_name, refund_amount, refund_status, created_at, updated_at)
VALUES (1, 'NAVER_PAY', 7500, 'PENDING', NOW(), NOW());