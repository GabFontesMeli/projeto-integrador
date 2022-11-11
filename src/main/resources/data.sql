INSERT INTO user_type (id, type)
VALUES (1, "seller"), (2, "costumer");

INSERT INTO product (id, name, price, volume)
VALUES (1, "laranja", 4.00, 5.00), (2, "morango", 4.00, 5.00), (3, "abacaxi", 4.00, 5.00), (4, "uva", 4.00, 5.00);

INSERT INTO storage (id, volume)
VALUES (1, 100000.00);

INSERT INTO section (id, name, temperature, storage_id)
VALUES (1, "frescos", 20, 1), (2, "refrigerados", 8, 1), (3, "congelados", 1, 1);