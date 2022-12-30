CREATE DATABASE `book`
CREATE TABLE user (
    `id` VARCHAR(40) NOT NULL,
	`name` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`user` VARCHAR(20),
	`password` VARCHAR(100),
	PRIMARY KEY (`id`)
)

CREATE TABLE book  (
    `id` VARCHAR(40) NOT NULL,
	`name` VARCHAR(50) NULL COLLATE 'utf8mb4_general_ci',
	`author` VARCHAR(20),
	`price` VARCHAR(10),
    `describe` VARCHAR(100),
    `picture` varchar(100),
	PRIMARY KEY (`id`)
)

insert into user(id,name,user,password) values('0','0','admin','123456Zxc')
