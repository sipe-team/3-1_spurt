-- Deliverys (배송 정보)
CREATE TABLE deliverys
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at     TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP NOT NULL,
    address        VARCHAR(255),
    delivery_momo  VARCHAR(255),
    mobile         VARCHAR(255),
    order_id       BIGINT,
    recipient_name VARCHAR(255),
    store_password VARCHAR(255),
    zip_code       VARCHAR(255)
);

-- Order_products (주문-상품 관계)
CREATE TABLE order_products
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    order_id   BIGINT,
    product_id BIGINT
);

-- Orders (주문 정보)
CREATE TABLE orders
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,
    order_number INTEGER      NOT NULL,
    order_status VARCHAR(255) NOT NULL
);

-- Payments (결제 정보)
CREATE TABLE payments
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at          TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP NOT NULL,
    order_id            BIGINT,
    payment_amount      INTEGER   NOT NULL,
    payment_method      VARCHAR(255),
    payment_method_name VARCHAR(255)
);

-- Products (상품 정보)
CREATE TABLE products
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at      TIMESTAMP NOT NULL,
    updated_at      TIMESTAMP NOT NULL,
    bundle_name     VARCHAR(255),
    bundle_quantity INTEGER   NOT NULL,
    description     VARCHAR(255),
    price           INTEGER   NOT NULL,
    product_name    VARCHAR(255)
);

-- Refunds (환불 정보)
CREATE TABLE refunds
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at         TIMESTAMP NOT NULL,
    updated_at         TIMESTAMP NOT NULL,
    order_id           BIGINT,
    refund_amount      INTEGER   NOT NULL,
    refund_method_name VARCHAR(255),
    refund_status      VARCHAR(255)
);

-- Deliverys 테이블에 인덱스 추가
CREATE INDEX idx_deliverys_order_id ON deliverys(order_id);

-- Order_products 테이블에 인덱스 추가
CREATE INDEX idx_order_products_order_id ON order_products(order_id);

-- Payments 테이블에 인덱스 추가
CREATE INDEX idx_payments_order_id ON payments(order_id);

-- Refunds 테이블에 인덱스 추가
CREATE INDEX idx_refunds_order_id ON refunds(order_id);