use `order`;
DELIMITER $$
DROP PROCEDURE IF EXISTS createOrder$$
CREATE PROCEDURE createOrder()
BEGIN
    set @i = 1;
    while @i <= 100000 do
            insert into `orders`(`created_at`, `ordered_at`, `shop_id`, `updated_at`, `user_id`, `order_payment_method`, `order_status`) values (now(), now(), 1, now(), 1, 'NAVER_PAY', 'ORDERED');
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL createOrder();

DELIMITER $$
DROP PROCEDURE IF EXISTS createOrderItem$$
CREATE PROCEDURE createOrderItem()
BEGIN
    set @i = 1;
    while @i <= 100000 do
            insert into `order_line_items`(`order_line_quantity`, `created_at`, `order_id`, `product_id`, `updated_at`, `order_line_name`) values (1, now(), @i, 1, now(), 'test');
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL createOrderItem();

DELIMITER $$
DROP PROCEDURE IF EXISTS createOrderOptionGroup$$
CREATE PROCEDURE createOrderOptionGroup()
BEGIN
    set @i = 1;
    while @i <= 100000 do
            INSERT INTO `order_option_groups` (created_at, order_line_item_id, updated_at, order_option_group_name) VALUES('2024-12-03 11:13:40', @i, '2024-12-03 11:13:40', 'test');
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL createOrderOptionGroup();

DELIMITER $$
DROP PROCEDURE IF EXISTS createOrderOption$$
CREATE PROCEDURE createOrderOption()
BEGIN
    set @i = 1;
    while @i <= 100000 do
            insert into `order_options`(`created_at`, `order_option_group_id`, `order_option_price`, `product_option_id`, `updated_at`, `order_option_name`) values (now(), @i, 1000, 1, now(), 'test');
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL createOrderOption();


