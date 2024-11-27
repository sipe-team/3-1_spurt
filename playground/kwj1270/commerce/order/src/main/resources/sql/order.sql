CREATE TABLE ORDERS
(
    ID             BIGINT PRIMARY KEY AUTO_INCREMENT,
    USER_ID        BIGINT       NOT NULL,
    SHOP_ID        BIGINT       NOT NULL,
    PAYMENT_METHOD VARCHAR(255) NOT NULL,
    STATUS         VARCHAR(255) NOT NULL,
    CREATED_AT     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ORDERED_AT     TIMESTAMP    NULL
);

CREATE TABLE ORDER_LINE_ITEMS
(
    ID         BIGINT PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID   BIGINT       NOT NULL,
    PRODUCT_ID BIGINT       NOT NULL,
    NAME       VARCHAR(255) NOT NULL,
    QUANTITY   INT          NOT NULL,
    CREATED_AT TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE ORDER_OPTION_GROUPS
(
    ID                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    ORDER_LINE_ITEM_ID BIGINT       NOT NULL,
    NAME               VARCHAR(255) NOT NULL,
    CREATED_AT         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE ORDER_OPTIONS
(
    ID                      BIGINT PRIMARY KEY AUTO_INCREMENT,
    PRODUCT_OPTION_GROUP_ID BIGINT       NOT NULL,
    NAME                    VARCHAR(255) NOT NULL,
    PRICE                   BIGINT       NOT NULL,
    CREATED_AT              TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT              TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
