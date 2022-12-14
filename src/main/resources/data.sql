INSERT INTO Storage
VALUES (1, 100000.00);
INSERT INTO Category
VALUES (1, 'frescos'), (2, 'refrigerados'), (3, 'congelados');
INSERT INTO Section(id, name, temperature, volume, storage_id, category_id)
VALUES (1, 'frescos', 20, 1000, 1, 1), (2, 'refrigerados', 8, 1000, 1, 2), (3, 'congelados', 1, 1000, 1, 3);
INSERT INTO Batch
VALUES (1, 1), (2, 1), (3, 1);
INSERT INTO Product(id, name, price, volume, category_id)
VALUES (1, 'banana', 4.00, 3.00, 1), (2, 'morango', 4.00, 3.00, 1), (3, 'abacaxi', 4.00, 3.00, 1), (4, 'frango', 10.00, 3.00, 3), (5, 'batata', 10.00, 3.00, 1);
INSERT INTO Batch_Product(id, expiration_date, manufacturing_date, quantity, remaining_quantity, batch_id, product_id, section_id)
VALUES (1, '2024-12-01','2020-01-01', 100, 100, 3, 1, 1), (2, '2022-11-30','2020-01-01', 50, 50, 1, 2, 1), (3, '2026-01-01','2020-01-01', 50, 50, 2, 1, 1), (4, '2025-01-01','2020-01-01', 20, 20, 1, 1, 1), (5, '2022-11-20','2020-01-01', 20, 20, 1, 5, 1);
INSERT INTO user_type (id, type)
VALUES (1, 'SELLER'), (2, 'COSTUMER'), (3, 'ADMIN');
INSERT INTO useru (id, email, name, secretPassword)
VALUES (1, 'teste@teste.com', 'teste', '$2a$10$EEJsKE0X8DdOVL1oBOG5pOf1VC0538odOr3.p248nVS428QvTdWcq');
INSERT INTO useru (id, email, name, secretPassword)
VALUES (2, 'teste2@teste2.com', 'teste2', '$2a$10$EEJsKE0X8DdOVL1oBOG5pOf1VC0538odOr3.p248nVS428QvTdWcq');
INSERT INTO Users_Roles (useru_id, user_type_id)
VALUES (1, 1), (2, 2);
INSERT INTO cart (id, date, status, total_value, user_id)
VALUES (1, '2020-11-12', 'OPEN', 8.0 , 1), (2, '2022-12-23', 'CLOSED', 20.0 , 1), (3, '2023-04-30', 'CLOSED', 60.0, 1),
(4, '2023-01-21', 'CLOSED', 10.00 , 1), (5, '2022-11-22', 'OPEN', 15.00, 1);
INSERT INTO cart_item (id, cart_id, batch_product_id, quantity, item_value)
VALUES (1, 1, 1, 2, 8.00), (2, 2, 2, 2, 8.00);
