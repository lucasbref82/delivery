CREATE TABLE user_table (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    registration_date datetime NOT NULL
) ENGINE=INNODB DEFAULT CHARSET = utf8;

CREATE TABLE user_groups (
    user_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES user_table(id),
    FOREIGN KEY (group_id) REFERENCES group_table(id)
) ENGINE=INNODB DEFAULT CHARSET = utf8;