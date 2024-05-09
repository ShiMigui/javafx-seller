DROP SCHEMA IF EXISTS javafxSellerDB;
CREATE SCHEMA javafxSellerDB;
USE javafxSellerDB;

CREATE TABLE department(
	cd_department SMALLINT AUTO_INCREMENT,
    nm_department VARCHAR(255) UNIQUE,
    CONSTRAINT pk_department PRIMARY KEY (cd_department)
);

INSERT INTO department (nm_department) VALUES
    ('Livros'),
    ('Computadores'),
    ('Videogames'),
    ('Eletrônicos'),
    ('Roupas'),
    ('Alimentos'),
    ('Decoração'),
    ('Brinquedos'),
    ('Esportes'),
    ('Ferramentas'),
    ('Saúde e Beleza'),
    ('Automotivo'),
    ('Móveis'),
    ('Jardinagem'),
    ('Instrumentos Musicais');