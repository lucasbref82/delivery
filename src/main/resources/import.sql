INSERT INTO kitchen (name) VALUES ('Tailandesa');
INSERT INTO kitchen (name) VALUES ('Indiana');
INSERT INTO kitchen (name) VALUES ('Brasileira');
INSERT INTO kitchen (name) VALUES ('Japonesa');

INSERT INTO payment_method (description) values ("Pix.");
INSERT INTO payment_method (description) values ("Credit Card.");
INSERT INTO payment_method (description) values ("Debit Card.");
INSERT INTO payment_method (description) values ("Money.");

INSERT INTO state (name) values ("Minas Gerais.");
INSERT INTO state (name) values ("Goiás.");
INSERT INTO state (name) values ("Rio Grande do Norte.");
INSERT INTO state (name) values ("Alagoas.")

INSERT INTO city (name, state_id) values ("Nova Serrana.", 1);
INSERT INTO city (name, state_id) values ("Goiânia", 2);
INSERT INTO city (name, state_id) values ("Natal.", 3);
INSERT INTO city (name, state_id) values ("Maceió", 4);

INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date,  address_city_id, address_zip_code, address_public_place, address_number, address_neighborhood) VALUES ('Thai Gourmet', 10.00, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date) VALUES ('Sabor Samosa', 25.65, 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date) VALUES ('Coco Bambu', 50.00, 3, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date) VALUES ('Sushi Sakura', 100.00, 4, utc_timestamp, utc_timestamp);


INSERT INTO permission (name, description) values ("READ_ONLY", "User has read only permission");
INSERT INTO permission (name, description) values ("READ_WRITE", "User has read and write permissions");
INSERT INTO permission (name, description) values ("ADMIN", "User has all permissions");


insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);







