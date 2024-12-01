
DELIMITER $$
DROP PROCEDURE IF EXISTS insertLoop$$

CREATE PROCEDURE insertLoop()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    WHILE i <= 100 DO
        INSERT INTO orders (order_id, delivery_id, refund_id, payment_id, order_number, order_status, created_at, updated_at)
        VALUES (i, i, i, i, 12345, 'CONFIRMED', NOW(), NOW());
        -- 배송 데이터 삽입
INSERT INTO deliverys (delivery_id, recipient_name, mobile, address, zip_code, store_password, delivery_memo, created_at, updated_at)
VALUES (i, '팬시', '010-1234-5678', '서울특별시 강남구', '13579', 'qwer1234', '문 앞에 놔주세요', NOW(), NOW());
-- 결제 데이터 삽입
INSERT INTO payments (id, payment_method, payment_method_name, payment_amount, created_at, updated_at)
VALUES (i, 'BILLING_NAVER_PAY', '네이버', 7500, NOW(), NOW());
SET j = 1;
        WHILE j <= 10 DO
          -- 상품 데이터 삽입 (order_id 포함)
            INSERT INTO products (product_id, order_id, product_name, description, price, bundle_name, bundle_quantity, created_at, updated_at)
                VALUES (j + (i -1) * 10, i, '베스트코 왕교자만두 1.4kg', '쫄깃한 만두피와 씹는 맛이 살도록 굵게 썰어낸 속재료가 만난 환상적인 식감', 7500, '크리스마스 선물 세트', 10, NOW(), NOW());

            SET j = j + 1;
END WHILE;
        SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL insertLoop;