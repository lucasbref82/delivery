CREATE TABLE order_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sub_total DECIMAL(19, 2) NOT NULL,
    shipping_fee DECIMAL(19, 2) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    cancellation_date TIMESTAMP NULL,
    delivery_date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    payment_method_id BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    address_zip_code VARCHAR(255),
    address_public_place VARCHAR(255),
    address_number VARCHAR(255),
    address_complement VARCHAR(255),
    address_neighborhood VARCHAR(255),
    address_city_id BIGINT,
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(id),
    FOREIGN KEY (client_id) REFERENCES user_table(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (address_city_id) REFERENCES city(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    unit_price DECIMAL(19, 2),
    total_price DECIMAL(19, 2),
    observation VARCHAR(255),
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES order_table(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;