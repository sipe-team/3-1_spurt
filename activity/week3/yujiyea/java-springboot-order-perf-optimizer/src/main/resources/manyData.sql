DELIMITER $$

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

        -- !!!

DELIMITER;