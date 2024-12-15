INSERT INTO kitchens (name) VALUES ('Tailandesa');
INSERT INTO kitchens (name) VALUES ('Indiana');
INSERT INTO kitchens (name) VALUES ('Brasileira');
INSERT INTO kitchens (name) VALUES ('Japonesa');

INSERT INTO payment_method (description) values ("Pix.");
INSERT INTO payment_method (description) values ("Credit Card.");
INSERT INTO payment_method (description) values ("Debit Card.");
INSERT INTO payment_method (description) values ("Money.");

INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ("Pad Thai", 19.99, 1);
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Sabor Samosa', 25.65, 2);
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Coco Bambu', 50.00, 3);
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Sushi Sakura', 100.00, 4);


INSERT INTO permission (name, description) values ("READ_ONLY", "User has read only permission");
INSERT INTO permission (name, description) values ("READ_WRITE", "User has read and write permissions");
INSERT INTO permission (name, description) values ("ADMIN", "User has all permissions");


INSERT INTO state (name) values ("Minas Gerais.");
INSERT INTO state (name) values ("Goiás.");
INSERT INTO state (name) values ("Rio Grande do Norte.");
INSERT INTO stete (name) values ("Alagoas.")

INSERT INTO city (name, state_id) values ("Nova Serrana.", 1);
INSERT INTO city (name, state_id) values ("Goiânia", 2);
INSERT INTO city (name, state_id) values ("Natal.", 3);
INSERT INTO city (name, state_id) values ("Maceió", 4);







