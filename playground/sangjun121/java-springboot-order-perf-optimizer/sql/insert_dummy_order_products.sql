DELIMITER $$
DROP PROCEDURE IF EXISTS insertLoop$$

CREATE PROCEDURE insertLoop()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE pk INT DEFAULT 1;
    WHILE i <= 10000 DO
		WHILE j <=3 DO
			INSERT INTO ORDER_PRODUCTS (order_product_id, order_id, product_id, count, created_at, updated_at)
				VALUES (pk, i, j, 1, NOW(), NOW());
			SET pk = pk+1;
            SET j = j+1;
		END WHILE;
		SET j = 1;
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER $$

CALL insertLoop;
$$