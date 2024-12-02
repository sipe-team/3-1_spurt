//console에서 실행하면 됨

CREATE PROCEDURE GenerateTestData(IN numberOfRecords INT)

BEGIN
    DECLARE num INT DEFAULT 1;

    -- 주문 데이터 생성
WHILE num <= numberOfRecords DO
INSERT INTO ORDERS (delivery_id, refund_id, payment_id, order_number, order_status, created_at, updated_at)
VALUES (
           num,
           num,
           num,
           FLOOR(1000 + RAND() * 9000),
           CASE FLOOR(RAND() * 3)
               WHEN 0 THEN 'CONFIRMED'
               WHEN 1 THEN 'CANCELLED'
               ELSE 'SHIPPED'
               END,
           NOW(),
           NOW()
       );

-- 상품 데이터 생성
INSERT INTO PRODUCTS (order_id, product_name, description, price, bundle_name, bundle_quantity, created_at, updated_at)
VALUES (
           num,
           CONCAT('상품 ', num),
           'asdfsdfwef',
           FLOOR(1000 + RAND() * 9000),
           CONCAT('세트 ', FLOOR(RAND() * 10) + 1),
           FLOOR(RAND() * 10) + 1,
           NOW(),
           NOW()
       );

-- 결제 데이터 생성
INSERT INTO PAYMENTS(id, payment_method, payment_method_name, payment_amount, created_at, updated_at)
values (
           num,
           case floor(rand()*3)
               WHEN 0 THEN 'BILLING_CREDIT_CARD'
               WHEN 1 THEN 'BILLING_NAVER_PAY'
               ELSE 'BILLING_PAYCO'
               end,
           case floor(rand()*3)
               when 0 then '신용카드'
               when 1 then '네이버페이'
               else '페이코'
               end,
           floor(10000+rand()*19000),
           now(),
           now()
       );

-- 배송 데이터 생성
INSERT INTO DELIVERYS (delivery_id, recipient_name, mobile, address, zip_code, store_password, delivery_memo, created_at, updated_at)
VALUES (
           num,
           concat('수령인', num),
           concat('010-', floor(1000+rand()*9000), '-', floor(1000+rand()*9000)),
           concat('주소', num),
           LPAD(floor(rand()*10000), 4, '0'),
           concat('password', num),
           concat('asdjklfjskldfjklsdjflk'),
           now(),
           now()
       );

-- 환불 데이터!
INSERT INTO REFUNDS (refund_id, refund_method_name, refund_amount, refund_status, created_at, updated_at)
values (
           num,
           case floor(rand()*2)
               WHEN 0 THEN 'CREDIT_CARD'
               ELSE 'NAVER_PAY'
               end,
           floor(10000+rand()*19000),
           case floor(rand()*3)
               when 0 then 'PENDING'
               when 1 then 'COMPLETED'
               else 'FAILED'
               end,
           now(),
           now()
       );

SET num = num + 1;
END WHILE;
END;

call GenerateTestData(100);