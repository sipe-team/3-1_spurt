create table products
(
    id              bigint auto_increment primary key,
    bundle_quantity int          null comment '상품 묶음 수량',
    price           int          null comment '상품 가격',
    bundle_name     varchar(255) null comment '상품 묶음 이름',
    description     varchar(255) null comment '상품 설명',
    name            varchar(255) null comment '상품 이름'
)


--

DELIMITER $$

DROP PROCEDURE IF EXISTS dummy$$

CREATE PROCEDURE dummy()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE randName VARCHAR(255);
    DECLARE randBundleName VARCHAR(255);
    DECLARE randDescription VARCHAR(255);

    WHILE i <= 10000 DO
        SET randName = CONCAT('Product', LPAD(FLOOR(RAND()*1000000), 6, '0'));
        SET randBundleName = CONCAT('Bundle', LPAD(FLOOR(RAND()*1000000), 6, '0'));
        SET randDescription = CONCAT('Description', LPAD(FLOOR(RAND()*1000000), 6, '0'));

        INSERT INTO products (bundle_quantity, price, bundle_name, description, name)
        VALUES (FLOOR(RAND()*100), FLOOR(RAND()*1000000), randBundleName, randDescription, randName);
        SET i = i + 1;
    END WHILE;

    SET i = 1;
END$$
DELIMITER ;

CALL dummy();