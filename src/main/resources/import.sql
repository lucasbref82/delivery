INSERT INTO kitchens (name) VALUES ('Tailandesa');
INSERT INTO kitchens (name) VALUES ('Indiana');
INSERT INTO kitchens (name) VALUES ('Brasileira');
INSERT INTO kitchens (name) VALUES ('Japonesa');

INSERT INTO payment_methods (description) values ("Pix.");
INSERT INTO payment_methods (description) values ("Credit Card.");
INSERT INTO payment_methods (description) values ("Debit Card.");
INSERT INTO payment_methods (description) values ("Money.");

INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ("Pad Thai", 19.99, 1);
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Sabor Samosa', 25.65, 2);
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Coco Bambu', 50.00, 3);
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Sushi Sakura', 100.00, 4);


INSERT INTO permissions (name, description) values ("READ_ONLY", "User has read only permission");
INSERT INTO permissions (name, description) values ("READ_WRITE", "User has read and write permissions");
INSERT INTO permissions (name, description) values ("ADMIN", "User has all permissions");


INSERT INTO states (name) values ("Minas Gerais.");
INSERT INTO states (name) values ("Goiás.");
INSERT INTO states (name) values ("Rio Grande do Norte.");
INSERT INTO states (name) values ("Alagoas.")

INSERT INTO cities (name, state_id) values ("Nova Serrana.", 1);
INSERT INTO cities (name, state_id) values ("Goiânia", 2);
INSERT INTO cities (name, state_id) values ("Natal.", 3);
INSERT INTO cities (name, state_id) values ("Maceió", 4);







