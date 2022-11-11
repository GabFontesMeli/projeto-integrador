INSERT INTO Product
VALUES (1, 'banana', 4.00, 5.00), (2, 'morango', 4.00, 5.00), (3, 'abacaxi', 4.00, 5.00), (4, 'uva', 4.00, 5.00);

INSERT INTO Storage
VALUES (1, 100000.00);

INSERT INTO Section
VALUES (1, 'frescos', 20, 1), (2, 'refrigerados', 8, 1), (3, 'congelados', 1, 1);

INSERT INTO Batch
VALUES (1, '2022-01-01', 1, 1);

INSERT INTO BATCHPRODUCT
VALUES (1, '2020-01-01', '11:00:00', 100, 1, 1), (2, '2020-01-01', '11:00:00', 50, 1, 2);

INSERT INTO Inventory
VALUES (1, 100, 1), (2, 50, 2);
