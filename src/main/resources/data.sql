INSERT INTO Storage
VALUES (1, 100000.00);

INSERT INTO Category
VALUES (1, 'frescos'), (2, 'refrigerados'), (3, 'congelados');

INSERT INTO Section(id, name, temperature, storage_id, category_id)
VALUES (1, 'frescos', 20, 1, 1), (2, 'refrigerados', 8, 1, 2), (3, 'congelados', 1, 1, 3);

INSERT INTO Batch
VALUES (1, '2022-01-01', 1);

INSERT INTO Product(id, name, price, volume, section_id, category_id)
VALUES (1, 'banana', 4.00, 5.00, 1, 1), (2, 'morango', 4.00, 5.00, 1, 1), (3, 'abacaxi', 4.00, 5.00, 1, 1), (4, 'frango', 10.00, 5.00, 1, 3);

INSERT INTO Batch_Product(id, manufacturing_date, manufacturing_time, quantity, batch_id, product_id, section_id)
VALUES (1, '2020-01-01', '11:00:00', 100, 1, 1, 1), (2, '2020-01-01', '11:00:00', 50, 1, 2, 1);

INSERT INTO Inventory
VALUES (1, 100, 1), (2, 50, 2);
