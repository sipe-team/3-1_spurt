use shop;
DELIMITER $$
DROP PROCEDURE IF EXISTS dummy$$
CREATE PROCEDURE dummy()
BEGIN
    set @i = 1;
    while @i <= 10000 do
            insert into shops (created_at, seller_id, updated_at, address, description, name)
            values (now(), @i, now(), CONCAT('address', @i), CONCAT('description', @i), CONCAT('name', @i));
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL dummy();

DELIMITER $$
DROP PROCEDURE IF EXISTS procducts$$
CREATE PROCEDURE procducts()
BEGIN
    set @i = 1;
    while @i <= 10000 do
            insert into products (created_at, shop_id, updated_at, description, name)
            values (now(), @i, now(), CONCAT('description', @i), CONCAT('name', @i));
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL procducts();

DELIMITER $$
DROP PROCEDURE IF EXISTS productGroups$$
CREATE PROCEDURE productGroups()
BEGIN
    set @i = 1;
    while @i <= 10000 do
            insert into product_option_groups (created_at, product_id, updated_at, name) values (now(), @i, now(), CONCAT('groupA', @i));
            insert into product_option_groups (created_at, product_id, updated_at, name) values (now(), @i, now(), CONCAT('groupB', @i));
            insert into product_option_groups (created_at, product_id, updated_at, name) values (now(), @i, now(), CONCAT('groupC', @i));
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL productGroups();

DELIMITER $$
DROP PROCEDURE IF EXISTS productOptions$$
CREATE PROCEDURE productOptions()
BEGIN
    set @i = 1;
    while @i <= 10000 do
            insert into product_options (created_at, product_option_group_id, updated_at, name, price) values (now(), @i, now(), CONCAT('optionA', @i), @i);
            insert into product_options (created_at, product_option_group_id, updated_at, name, price) values (now(), @i, now(), CONCAT('optionB', @i), @i);
            insert into product_options (created_at, product_option_group_id, updated_at, name, price) values (now(), @i, now(), CONCAT('optionC', @i), @i);
            set @i = @i + 1;
        end while;
END$$
DELIMITER ;
CALL productOptions();

