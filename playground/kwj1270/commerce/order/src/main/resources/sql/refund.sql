CREATE TABLE REFUNDS
(
    ID                BIGINT PRIMARY KEY AUTO_INCREMENT,
    REFUND_PRODUCT_ID BIGINT       NOT NULL,
    REFUND_METHOD     VARCHAR(255) NOT NULL,
    REFUND_STATUS     VARCHAR(255) NOT NULL,
    CREATED_AT        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE REFUND_PRODUCTS
(
    ID                    BIGINT PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID              BIGINT       NOT NULL,
    PRODUCT_ID            BIGINT       NOT NULL,
    REFUND_COUNT          INT          NOT NULL,
    REFUND_PRICE          BIGINT       NOT NULL,
    REFUND_PRODUCT_STATUS VARCHAR(255) NOT NULL,
    CREATED_AT            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
