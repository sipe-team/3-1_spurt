-- 주문 데이터 삽입
INSERT INTO ORDERS (order_id, delivery_id, refund_id, payment_id, order_number, order_status, created_at, updated_at)
VALUES
    (1, 1, 1, 1, 10001, 'CONFIRMED', NOW(), NOW()),
(2, 2, 2, 2, 10002, 'PENDING', NOW(), NOW()),
(3, 3, 3, 3, 10003, 'SHIPPED', NOW(), NOW()),
(4, 4, 4, 4, 10004, 'DELIVERED', NOW(), NOW()),
(5, 5, 5, 5, 10005, 'CANCELLED', NOW(), NOW()),
(6, 6, 6, 6, 10006, 'RETURNED', NOW(), NOW()),
(7, 7, 7, 7, 10007, 'PROCESSING', NOW(), NOW()),
(8, 8, 8, 8, 10008, 'COMPLETED', NOW(), NOW()),
(9, 9, 9, 9, 10009, 'CONFIRMED', NOW(), NOW()),
(10, 10, 10, 10, 10010, 'PENDING', NOW(), NOW());

-- 상품 데이터 삽입 (order_id 포함)
INSERT INTO PRODUCTS (product_id, order_id, product_name, description, price, bundle_name, bundle_quantity, created_at, updated_at)
VALUES
    (1, 1, '베스트코 왕교자만두 1.4kg', '쫄깃한 만두피와 씹는 맛이 살도록 굵게 썰어낸 속재료가 만난 환상적인 식감', 7500, '크리스마스 선물 세트', 10, NOW(), NOW()),
(2, 2, 'CJ 햇반 210g x 24개입', '100% 국내산 쌀로 지은 햇반', 23000, '추석 선물 세트', 5, NOW(), NOW()),
(3, 3, '풀무원 얇은피 만두 1.2kg', '고소한 만두피와 꽉 찬 속이 특징', 8200, '명절 선물 세트', 15, NOW(), NOW()),
(4, 4, '비비고 김치왕만두 1kg', '김치와 고기의 완벽 조화', 6800, '가정 간편식 세트', 12, NOW(), NOW()),
(5, 5, '교촌 치킨 양념소스 500ml', '달콤한 양념 맛을 그대로', 4900, '소스 패키지', 8, NOW(), NOW()),
(6, 6, '롯데 찰떡 아이스크림 6개입', '쫄깃한 찰떡과 부드러운 아이스크림의 조화', 6000, '간식 세트', 6, NOW(), NOW()),
(7, 7, '오뚜기 진라면 매운맛 5개입', '칼칼한 매운맛의 라면', 2800, '라면 묶음 세트', 20, NOW(), NOW()),
(8, 8, '농심 짜파게티 5개입', '짜장 소스가 맛있는 짜파게티', 3200, '라면 묶음 세트', 18, NOW(), NOW()),
(9, 9, '삼양 불닭볶음면 5개입', '매운맛의 진수를 보여주는 라면', 3500, '매운맛 패키지', 25, NOW(), NOW()),
(10, 10, '빙그레 바나나우유 8개입', '달콤한 바나나맛 우유', 5600, '음료 세트', 10, NOW(), NOW());

-- 결제 데이터 삽입
INSERT INTO PAYMENTS (id, payment_method, payment_method_name, payment_amount, created_at, updated_at)
VALUES
    (1, 'BILLING_NAVER_PAY', '네이버', 7500, NOW(), NOW()),
(2, 'BILLING_KAKAO_PAY', '카카오', 23000, NOW(), NOW()),
(3, 'BILLING_CARD', '신용카드', 8200, NOW(), NOW()),
(4, 'BILLING_CARD', '신용카드', 6800, NOW(), NOW()),
(5, 'BILLING_BANK', '계좌이체', 4900, NOW(), NOW()),
(6, 'BILLING_NAVER_PAY', '네이버', 6000, NOW(), NOW()),
(7, 'BILLING_KAKAO_PAY', '카카오', 2800, NOW(), NOW()),
(8, 'BILLING_CARD', '신용카드', 3200, NOW(), NOW()),
(9, 'BILLING_BANK', '계좌이체', 3500, NOW(), NOW()),
(10, 'BILLING_NAVER_PAY', '네이버', 5600, NOW(), NOW());

-- 배송 데이터 삽입
INSERT INTO DELIVERIES (delivery_id, recipient_name, mobile, address, zip_code, store_password, delivery_memo, created_at, updated_at)
VALUES
    (1, '팬시', '010-1234-5678', '서울특별시 강남구', '13579', 'qwer1234', '문 앞에 놔주세요', NOW(), NOW()),
(2, '민지', '010-1111-2222', '서울특별시 송파구', '54321', 'asdf5678', '경비실에 맡겨주세요', NOW(), NOW()),
(3, '수현', '010-3333-4444', '부산광역시 해운대구', '67890', 'zxcv0987', '현관에 놔주세요', NOW(), NOW()),
(4, '하준', '010-5555-6666', '인천광역시 남동구', '12321', 'qazx1234', '배송 전 연락주세요', NOW(), NOW()),
(5, '예린', '010-7777-8888', '대구광역시 중구', '99887', 'wsxc3456', '뒤편에 놔주세요', NOW(), NOW()),
(6, '지훈', '010-9999-0000', '대전광역시 서구', '11223', 'edcv0987', '문고리에 걸어주세요', NOW(), NOW()),
(7, '채은', '010-2222-1111', '광주광역시 북구', '77665', 'rfvb5678', '인터폰 눌러주세요', NOW(), NOW()),
(8, '주연', '010-4444-3333', '울산광역시 동구', '55664', 'tgby8765', '아파트 우편함에 놔주세요', NOW(), NOW()),
(9, '다연', '010-6666-5555', '경기도 성남시', '22334', 'yhnb3456', '문 앞 화분 뒤에 놔주세요', NOW(), NOW()),
(10, '재민', '010-8888-7777', '경기도 고양시', '33221', 'ujmn2345', '차 뒤편에 놔주세요', NOW(), NOW());

-- 환불 데이터 삽입
INSERT INTO REFUNDS (refund_id, refund_method_name, refund_amount, refund_status, created_at, updated_at)
VALUES
    (1, 'NAVER_PAY', 7500, 'PENDING', NOW(), NOW()),
(2, 'KAKAO_PAY', 8200, 'APPROVED', NOW(), NOW()),
(3, 'TOSS_PAY', 4900, 'REJECTED', NOW(), NOW()),
(4, 'NAVER_PAY', 6000, 'PROCESSING', NOW(), NOW()),
(5, 'KAKAO_PAY', 23000, 'COMPLETED', NOW(), NOW()),
(6, 'TOSS_PAY', 3500, 'FAILED', NOW(), NOW()),
(7, 'NAVER_PAY', 2800, 'PENDING', NOW(), NOW()),
(8, 'KAKAO_PAY', 3200, 'APPROVED', NOW(), NOW()),
(9, 'TOSS_PAY', 5600, 'PROCESSING', NOW(), NOW()),
(10, 'NAVER_PAY', 12300, 'COMPLETED', NOW(), NOW());
