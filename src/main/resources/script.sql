CREATE TABLE cozinhas(
id bigint(10) AUTO_INCREMENT,
nome varchar(30) NOT NULL,
PRIMARY KEY (ID)
);

CREATE TABLE restaurantes(
id bigint(10) AUTO_INCREMENT,
nome varchar(30) NOT NULL,
taxa_frete decimal(7,2),
PRIMARY KEY (id)
);


INSERT INTO cozinhas (nome) VALUES ('Tailandesa');
INSERT INTO cozinhas (nome) VALUES ('Indiana');
