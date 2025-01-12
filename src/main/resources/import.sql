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

INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date,  address_city_id, address_zip_code, address_public_place, address_number, address_neighborhood) VALUES ('Thai Gourmet', 10.00, 1, utc_timestamp, utc_timestamp, 1, '38400999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date) VALUES ('Sabor Samosa', 25.65, 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date) VALUES ('Coco Bambu', 50.00, 3, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, shipping_fee, kitchen_id, registration_date, change_date) VALUES ('Sushi Sakura', 100.00, 4, utc_timestamp, utc_timestamp);


insert into permission (name, description) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (name, description) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');


insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);







