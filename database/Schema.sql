CREATE DATABASE IF NOT EXISTS unblock_prod;

USE unblock_prod;

CREATE TABLE IF NOT EXISTS User (
    id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS FacebookLink (
    id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL,
    facebook_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);