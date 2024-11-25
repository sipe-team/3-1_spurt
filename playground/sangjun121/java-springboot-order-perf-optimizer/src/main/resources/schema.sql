-- 주문 테이블 생성
CREATE TABLE ORDERS
(
    order_id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    delivery_id  BIGINT, -- 배송 ID
    refund_id    BIGINT, -- 환불 ID
    payment_id   BIGINT, -- 결제 ID
    order_number INT         NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    created_at   DATETIME(6) NOT NULL,
    updated_at   DATETIME(6) NOT NULL
);

-- 상품 테이블 생성
CREATE TABLE PRODUCTS
(
    product_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name    VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    price           INT          NOT NULL,
    bundle_name     VARCHAR(255) NOT NULL,
    bundle_quantity INT          NOT NULL,
    created_at      DATETIME(6) NOT NULL,
    updated_at      DATETIME(6) NOT NULL
);

-- 주문 상품 테이블 생성
CREATE TABLE ORDER_PRODUCTS
(
    order_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id         BIGINT, -- 주문 ID
    product_id       BIGINT, -- 상품 ID
    count            INT         NOT NULL,
    created_at       DATETIME(6) NOT NULL,
    updated_at       DATETIME(6) NOT NULL,
    CONSTRAINT FK_ORDER FOREIGN KEY (order_id) REFERENCES ORDERS(order_id) ON DELETE CASCADE,
    CONSTRAINT FK_PRODUCT FOREIGN KEY (product_id) REFERENCES PRODUCTS(product_id) ON DELETE CASCADE
);

-- 결제 테이블 생성
CREATE TABLE PAYMENTS
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    payment_method      VARCHAR(255) NOT NULL,
    payment_method_name VARCHAR(255) NOT NULL,
    payment_amount      INT          NOT NULL,
    created_at          DATETIME(6) NOT NULL,
    updated_at          DATETIME(6) NOT NULL
);

-- 배송 테이블 생성
CREATE TABLE DELIVERYS
(
    delivery_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipient_name VARCHAR(255) NOT NULL,
    mobile         VARCHAR(20)  NOT NULL,
    address        VARCHAR(255) NOT NULL,
    zip_code       VARCHAR(10)  NOT NULL,
    store_password VARCHAR(255) NOT NULL,
    delivery_memo  VARCHAR(255) NOT NULL,
    created_at     DATETIME(6) NOT NULL,
    updated_at     DATETIME(6) NOT NULL
);

-- 환불 테이블 생성
CREATE TABLE REFUNDS
(
    refund_id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    refund_method_name VARCHAR(255) NOT NULL,
    refund_amount      INT          NOT NULL,
    refund_status      VARCHAR(50)  NOT NULL,
    created_at         DATETIME(6) NOT NULL,
    updated_at         DATETIME(6) NOT NULL
);
