INSERT INTO Storage
VALUES (1, 100000.00);

INSERT INTO Category
VALUES (1, 'frescos'), (2, 'refrigerados'), (3, 'congelados');

INSERT INTO Section(id, name, temperature, volume, category_id, storage_id)
VALUES (1, 'frescos', 20, 1000, 1, 1), (2, 'refrigerados', 8, 1000, 2, 1), (3, 'congelados', 1, 1000, 3, 1);

INSERT INTO Batch
VALUES (1, 1);

INSERT INTO Product(id, name, price, volume, category_id)
VALUES (1, 'banana', 4.00, 5.00, 1), (2, 'queijo', 4.00, 5.00, 2), (3, 'frango', 4.00, 5.00, 3), (4, 'carne bovina', 10.0, 7.0, 3);

INSERT INTO Batch_product(id, expiration_date, manufacturing_date, quantity, remaining_quantity, batch_id, product_id, section_id)
VALUES (1, '2090-12-31', '2020-01-01', 10, 10, 1, 1, 1), (2, '2090-12-31', '2020-01-01', 10, 10, 1, 2, 2), (3, '2020-12-31', '2020-01-01', 10, 10, 1, 3, 3);

INSERT INTO UserType(id, type)
VALUES (1, 'costumer'), (2, 'seller');

INSERT INTO Useru(id, email, name, usertype_id)
VALUES (1, 'user@email.com', 'joao', 1)