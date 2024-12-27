set foreign_key_checks = 0;

delete from city;
delete from kitchen;
delete from state;
delete from payment_method;
delete from group_table;
delete from group_permissions;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from user_table;
delete from user_groups;
delete from order_table;
delete from order_item;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table state auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table group_table auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table user_table auto_increment = 1;
alter table user_groups auto_increment = 1;
alter table order_table auto_increment = 1;
alter table order_item auto_increment = 1;

insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Argentina');
insert into kitchen (id, name) values (4, 'Brasileira');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, shipping_fee, kitchen_id, registration_date, change_date, address_city_id, address_public_place, address_zip_code, address_number, address_neighborhood) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, registration_date, change_date) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, registration_date, change_date) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, registration_date, change_date) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, registration_date, change_date) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, registration_date, change_date) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

insert into payment_method (id, description) values (1, 'Cartão de crédito');
insert into payment_method (id, description) values (2, 'Cartão de débito');
insert into payment_method (id, description) values (3, 'Dinheiro');

insert into permission (id, name, description) values (1, 'FIND_KITCHEN', 'Allows you to consult kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHEN', 'Allows you to edit kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

INSERT INTO group_table (id, name) VALUES (1, 'Admin');
INSERT INTO group_table (id, name) VALUES (2, 'User');
INSERT INTO group_table (id, name) VALUES (3, 'Manager');

INSERT INTO user_table (id, name, email, password, registration_date)
VALUES (1, 'Lucas Ferreira', 'lucas@example.com', 'password123', UTC_TIMESTAMP());

INSERT INTO user_table (id, name, email, password, registration_date)
VALUES (2, 'Maria Silva', 'maria@example.com', 'password123', UTC_TIMESTAMP());

INSERT INTO user_table (id, name, email, password, registration_date)
VALUES (3, 'João Souza', 'joao@example.com', 'password123', UTC_TIMESTAMP());

INSERT INTO user_groups (user_id, group_id) VALUES (1, 1);
INSERT INTO user_groups (user_id, group_id) VALUES (2, 2);
INSERT INTO user_groups (user_id, group_id) VALUES (3, 3);

INSERT INTO order_table (id, sub_total, shipping_fee, registration_date, change_date, delivery_date, status, payment_method_id, client_id, restaurant_id, address_zip_code, address_public_place, address_number, address_complement, address_neighborhood, address_city_id)
VALUES (1, 50.00, 5.00, UTC_TIMESTAMP(), UTC_TIMESTAMP(), DATE_ADD(UTC_TIMESTAMP(), INTERVAL 2 DAY), 'CREATED', 1, 1, 1, '62704', 'Main St', '123', 'Apt 4', 'Downtown', 1);

INSERT INTO order_table (id, sub_total, shipping_fee, registration_date, change_date, delivery_date, status, payment_method_id, client_id, restaurant_id, address_zip_code, address_public_place, address_number, address_complement, address_neighborhood, address_city_id)
VALUES (2, 75.00, 7.50, UTC_TIMESTAMP(), UTC_TIMESTAMP(), DATE_ADD(UTC_TIMESTAMP(), INTERVAL 3 DAY), 'CREATED', 2, 2, 2, '62705', '2nd St', '456', 'Suite B', 'Uptown', 2);

INSERT INTO order_item (id, quantity, unit_price, order_id, product_id) VALUES (1, 2, 25.00, 1, 3);
INSERT INTO order_item (id, quantity, unit_price, order_id, product_id) VALUES (2, 1, 50.00, 2, 5);

