-- 주문 테이블 생성
CREATE TABLE ORDERS (
        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        delivery_id BIGINT, -- 배송 ID
        refund_id BIGINT,   -- 환불 ID
        payment_id BIGINT,  -- 결제 ID
        order_number INT NOT NULL,
        order_status VARCHAR(50) NOT NULL,
        created_at DATETIME(6) NOT NULL,
        updated_at DATETIME(6) NOT NULL,
        INDEX idx_delivery_id (delivery_id), -- 배송 ID 인덱스
        INDEX idx_refund_id (refund_id),     -- 환불 ID 인덱스
        INDEX idx_payment_id (payment_id)   -- 결제 ID 인덱스
);

-- 상품 테이블 생성
CREATE TABLE PRODUCTS (
        product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        order_id BIGINT, -- 주문 ID
        product_name VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,
        price INT NOT NULL,
        bundle_name VARCHAR(255) NOT NULL,
        bundle_quantity INT NOT NULL,
        created_at DATETIME(6) NOT NULL,
        updated_at DATETIME(6) NOT NULL,
        FOREIGN KEY (order_id) REFERENCES ORDERS(order_id),
        INDEX idx_order_id (order_id) -- 주문 ID 인덱스
);

-- 결제 테이블 생성
CREATE TABLE PAYMENTS (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        payment_method VARCHAR(255) NOT NULL,
        payment_method_name VARCHAR(255) NOT NULL,
        payment_amount INT NOT NULL,
        created_at DATETIME(6) NOT NULL,
        updated_at DATETIME(6) NOT NULL
);

-- 배송 테이블 생성
CREATE TABLE DELIVERYS (
        delivery_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        recipient_name VARCHAR(255) NOT NULL,
        mobile VARCHAR(20) NOT NULL,
        address VARCHAR(255) NOT NULL,
        zip_code VARCHAR(10) NOT NULL,
        store_password VARCHAR(255) NOT NULL,
        delivery_memo VARCHAR(255) NOT NULL,
        created_at DATETIME(6) NOT NULL,
        updated_at DATETIME(6) NOT NULL
);

-- 환불 테이블 생성
CREATE TABLE REFUNDS (
        refund_id BIGINT AUTO_INCREMENT PRIMARY KEY,
        refund_method_name VARCHAR(255) NOT NULL,
        refund_amount INT NOT NULL,
        refund_status VARCHAR(50) NOT NULL,
        created_at DATETIME(6) NOT NULL,
        updated_at DATETIME(6) NOT NULL
);
