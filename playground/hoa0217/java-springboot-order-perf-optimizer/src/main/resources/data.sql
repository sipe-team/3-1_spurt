-- Deliverys 데이터 삽입
INSERT INTO deliverys (created_at, updated_at, address, delivery_momo, mobile, order_id, recipient_name, store_password, zip_code)
VALUES (NOW(), NOW(), '123 Test St', 'Leave at door', '010-1234-5678', 1, 'John Doe', 'password123', '12345'),
       (NOW(), NOW(), '456 Sample Ave', 'Call before delivery', '010-8765-4321', 2, 'Jane Smith', 'securepass', '67890');

-- Order_products 데이터 삽입
INSERT INTO order_products (created_at, updated_at, order_id, product_id)
VALUES (NOW(), NOW(), 1, 1),
       (NOW(), NOW(), 1, 2),
       (NOW(), NOW(), 2, 3);

-- Orders 데이터 삽입
INSERT INTO orders (created_at, updated_at, order_number, order_status)
VALUES (NOW(), NOW(), 1001, 'PENDING'),
       (NOW(), NOW(), 1002, 'COMPLETED');

-- Payments 데이터 삽입
INSERT INTO payments (created_at, updated_at, order_id, payment_amount, payment_method, payment_method_name)
VALUES (NOW(), NOW(), 1, 5000, 'Credit Card', 'Visa'),
       (NOW(), NOW(), 2, 15000, 'Bank Transfer', 'Wire Transfer');

-- Products 데이터 삽입
INSERT INTO products (created_at, updated_at, bundle_name, bundle_quantity, description, price, product_name)
VALUES (NOW(), NOW(), 'Starter Pack', 1, 'A starter pack for new customers', 5000, 'Starter Kit'),
       (NOW(), NOW(), 'Premium Pack', 2, 'Includes premium items', 7500, 'Premium Kit'),
       (NOW(), NOW(), NULL, 1, 'Single item purchase', 2000, 'Single Product');

-- Refunds 데이터 삽입
INSERT INTO refunds (created_at, updated_at, order_id, refund_amount, refund_method_name, refund_status)
VALUES (NOW(), NOW(), 2, 15000, 'Bank Transfer', 'PROCESSING');
