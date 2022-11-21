INSERT INTO Storage
VALUES (1, 100000.00);
INSERT INTO Category
VALUES (1, 'frescos'), (2, 'refrigerados'), (3, 'congelados');
INSERT INTO Section(id, name, temperature, volume, storage_id, category_id)
VALUES (1, 'frescos', 20, 1000, 1, 1), (2, 'refrigerados', 8, 1000, 1, 2), (3, 'congelados', 1, 1000, 1, 3);
INSERT INTO Batch
VALUES (1, 1), (2, 1), (3, 1);
INSERT INTO Product(id, name, price, volume, category_id)
VALUES (1, 'banana', 4.00, 5.00, 1), (2, 'morango', 4.00, 5.00, 1), (3, 'abacaxi', 4.00, 5.00, 1), (4, 'frango', 10.00, 5.00, 3);
INSERT INTO Batch_Product(id, expiration_date, manufacturing_date, quantity, remaining_quantity, batch_id, product_id, section_id)
VALUES (1, '2024-12-01','2020-01-01', 100, 100, 3, 1, 1), (2, '2024-01-01','2020-01-01', 30, 50, 1, 2, 1), (3, '2026-01-01','2020-01-01', 50, 50, 2, 1, 1), (4, '2025-01-01','2020-01-01', 20, 20, 1, 1, 1);
INSERT INTO user_type (id, type)
VALUES (1, 'seller'), (2, 'costumer');
UPDATE batch_product set remaining_quantity = 100 where id = 1;
UPDATE batch_product set remaining_quantity = 50 where id = 2;