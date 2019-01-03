DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

 DROP TABLE IF EXISTS `bank_user`;

CREATE TABLE `bank_user`
 (
  `user_id`   varchar(32) NOT NULL,
  `user_name` varchar(32)  DEFAULT NULL,
  `password`  varchar(64)  DEFAULT NULL,
  `user_type` varchar(16)  DEFAULT NULL,
  `phone`     varchar(32)  DEFAULT NULL,
  `address`   varchar(256) DEFAULT NULL,
  `email`     varchar(256) DEFAULT NULL,
   PRIMARY KEY (`user_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
