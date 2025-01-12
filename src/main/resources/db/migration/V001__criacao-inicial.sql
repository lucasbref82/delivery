CREATE TABLE kitchen (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(60) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET = utf8;


CREATE TABLE state (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(60)
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE city (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255),
  state_id bigint NOT NULL,
  FOREIGN KEY (state_id) REFERENCES state(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;


CREATE TABLE restaurant (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(60) NOT NULL,
  shipping_fee decimal(10,2),
  kitchen_id bigint NOT NULL,
  registration_date datetime,
  change_date datetime,
  address_zip_code varchar(255),
  address_public_place varchar(255),
  address_number varchar(60),
  address_complement varchar(255),
  address_neighborhood varchar(60),
  address_city_id bigint,
  FOREIGN KEY (kitchen_id) REFERENCES kitchen(id),
  FOREIGN KEY (address_city_id) REFERENCES city(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;


CREATE TABLE permission (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  description varchar(4000) NOT null
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE group_table (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(60) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE group_permissions (
  group_id bigint NOT NULL,
  permission_id BIGINT NOT NULL,
  PRIMARY KEY (group_id, permission_id),
  FOREIGN KEY (group_id) REFERENCES group_table(id),
  FOREIGN KEY (permission_id) REFERENCES permission(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE product (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(60) NOT NULL,
  description varchar(255) NOT NULL,
  price decimal(10,2) NOT NULL,
  active bit(1) NOT NULL DEFAULT 0,
  restaurant_id bigint NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE data_request (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    request_date DATETIME NOT NULL,
    uri VARCHAR(8000) NOT NULL,
    request_body LONGTEXT,
    method VARCHAR(10) NOT NULL,
    headers LONGTEXT,
    responde_date DATETIME,
    status_code INT,
    response_body LONGTEXT,
    duration DECIMAL(17, 6)
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE payment_method (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE restaurant_payment_method (
    restaurant_id BIGINT NOT NULL,
    payment_method_id BIGINT NOT NULL,
    PRIMARY KEY (restaurant_id, payment_method_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;
