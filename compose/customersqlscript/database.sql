DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

DROP TABLE IF EXISTS `bank_customer`;
CREATE TABLE `bank_customer`
(
  `cust_id`       varchar(32) NOT NULL,
  `cust_name`     varchar(32)  DEFAULT NULL,
  `password`      varchar(64)  DEFAULT NULL,
  `identity_card` varchar(64)  DEFAULT NULL,
  `cust_type`     varchar(16)  DEFAULT NULL,
  `sex`           tinyint(1) DEFAULT '0',
  `phone`         varchar(32)  DEFAULT NULL,
  `address`       varchar(256) DEFAULT NULL,
  `email`         varchar(256) DEFAULT NULL,
  `credit`        varchar(16)  DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bank_account`;

CREATE TABLE `bank_account`
(
  `account`          varchar(32) NOT NULL,
  `cust_id`          varchar(32)  DEFAULT NULL,
  `deposit_bank`     varchar(256) DEFAULT NULL,
  `balances`         double       DEFAULT '0',
  `blocked_balances` double       DEFAULT '0',
  `open_date`        varchar(20)  DEFAULT NULL,
  `cancel_date`      varchar(20)  DEFAULT NULL,
  `account_kind`     varchar(16)  DEFAULT NULL,
  `account_type`     varchar(16)  DEFAULT NULL,
  `account_status`   varchar(16)  DEFAULT NULL,
  `password`         varchar(64)  DEFAULT NULL,
  PRIMARY KEY (`account`),
  FOREIGN KEY (`cust_id`) REFERENCES bank_customer (cust_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `bank`.`bank_customer`(`cust_id`, `cust_name`, `password`, `identity_card`, `cust_type`, `sex`, `phone`, `address`, `email`, `credit`) VALUES ('18729916246011904', 'kangqi xu', '202cb962ac59075b964b07152d234b70', '331023199601110559', '1', 1, '18805862675', '吉林省辽源市东丰县123', 'x331705233@gmail.com', '1');
INSERT INTO `bank`.`bank_customer`(`cust_id`, `cust_name`, `password`, `identity_card`, `cust_type`, `sex`, `phone`, `address`, `email`, `credit`) VALUES ('19444414749618176', '周健', '202cb962ac59075b964b07152d234b70', '330523199503204718', '1', 1, '17855824057', '天津市天津和平区宁大', 'x331705233@gmail.com', '1');
INSERT INTO `bank`.`bank_customer`(`cust_id`, `cust_name`, `password`, `identity_card`, `cust_type`, `sex`, `phone`, `address`, `email`, `credit`) VALUES ('21616484916932608', 'jiafeng lu', 'e10adc3949ba59abbe56e057f20f883e', '330483199510012010', '1', 1, '15958360766', '浙江省嘉兴市桐乡市浙江省乌镇', '729957621@qq.com', '1');

INSERT INTO `bank`.`bank_account`(`account`, `cust_id`, `deposit_bank`, `balances`, `blocked_balances`, `open_date`, `cancel_date`, `account_kind`, `account_type`, `account_status`, `password`) VALUES ('6668541233587412', '18729916246011904', '河北省秦皇岛市北戴河区123', 24390.15, 321, '1541233587489', NULL, '1', '2', '1', '202cb962ac59075b964b07152d234b70');
INSERT INTO `bank`.`bank_account`(`account`, `cust_id`, `deposit_bank`, `balances`, `blocked_balances`, `open_date`, `cancel_date`, `account_kind`, `account_type`, `account_status`, `password`) VALUES ('6668541404022215', '19444414749618176', '北京市北京市西城区人民都', 99991300, 0, '1541404022274', NULL, '1', '1', NULL, '202cb962ac59075b964b07152d234b70');
INSERT INTO `bank`.`bank_account`(`account`, `cust_id`, `deposit_bank`, `balances`, `blocked_balances`, `open_date`, `cancel_date`, `account_kind`, `account_type`, `account_status`, `password`) VALUES ('6668541921988419', '21616484916932608', '浙江省宁波市鄞州区中国工商银行宁波梅墟支行', 26974, 0, '1541921988423', NULL, '1', '1', NULL, 'e10adc3949ba59abbe56e057f20f883e');
