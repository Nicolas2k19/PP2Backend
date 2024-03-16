CREATE DATABASE vdg;

USE vdg;

DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS persona;

CREATE TABLE rol (
	id_rol INT(1) AUTO_INCREMENT,
	nombre VARCHAR(50),
	PRIMARY KEY (id_rol)
);

CREATE TABLE usuario (
	id_usuario INT AUTO_INCREMENT,
	email VARCHAR(50),
    contrasena VARCHAR (50),
    id_rol INT,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE persona (
	id_persona INT AUTO_INCREMENT,
	nombre VARCHAR(50),
    apellido VARCHAR (50),
    dni VARCHAR (20),
    email VARCHAR (50),
    telefono VARCHAR (50),
    fecha_nacimiento VARCHAR (50),
    id_direccion INT,
    id_usuario INT,
    PRIMARY KEY (id_persona)
);

INSERT INTO rol (nombre) VALUES ('ADMINISTRATIVO');
INSERT INTO rol (nombre) VALUES ('SUPERVISOR');
INSERT INTO rol (nombre) VALUES ('AGRESOR');
INSERT INTO rol (nombre) VALUES ('VICTIMA');
