DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

-- ----------------------------
-- Table structure for bank_deposit
-- ----------------------------
DROP TABLE IF EXISTS `bank_deposit`;
CREATE TABLE `bank_deposit` (
  `deposit_id` varchar(20) NOT NULL,
  `cust_id` varchar(32) DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  `deposit_type` varchar(20) DEFAULT NULL,
  `deposit_money` double DEFAULT NULL,
  `deposit_rate` double DEFAULT NULL,
  `deposit_date` varchar(20) DEFAULT NULL,
  `deposit_duration` varchar(20) DEFAULT NULL,
  `transfer_way` varchar(20) DEFAULT NULL,
  `reviewer_id` varchar(16) DEFAULT NULL,
  `deposit_flag` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`deposit_id`),
  KEY `cust_id` (`cust_id`),
  KEY `account` (`account`),
  CONSTRAINT `bank_deposit_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `bank_customer` (`cust_id`),
  CONSTRAINT `bank_deposit_ibfk_2` FOREIGN KEY (`account`) REFERENCES `bank_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_deposit
-- ----------------------------
BEGIN;
INSERT INTO `bank_deposit` VALUES ('18731825316892672', '18729916246011904', '6668541233587412', '活期存款', 10000, 0.3, '1541234015176', '活期', '自动转存', '18805862675', '0');
INSERT INTO `bank_deposit` VALUES ('18731989469368320', '18729916246011904', '6668541233587412', '整存整取', 321, 2, '1541234054313', '一年', '到期存活', '18805862675', '0');
INSERT INTO `bank_deposit` VALUES ('19446140302921728', '19444414749618176', '6668541404022215', '活期存款', 100000000, 0.3, '1541404321140', '活期', '自动转存', '17855824057', '0');
INSERT INTO `bank_deposit` VALUES ('20938238403416064', '18729916246011904', '6668541233587412', '活期存款', 1000, 0.3, '1541760065049', '活期', '自动转存', '18805862675', '0');
INSERT INTO `bank_deposit` VALUES ('20939508124094464', '18729916246011904', '6668541233587412', '活期存款', 1000, 0.3, '1541760367774', '活期', '自动转存', '18805862675', '0');
INSERT INTO `bank_deposit` VALUES ('21618674314121216', '21616484916932608', '6668541921988419', '活期存款', 2000, 0.3, '1541922293612', '一年', '自动转存', '15958360766', '0');
COMMIT;

-- ----------------------------
-- Table structure for bank_deposit_rate
-- ----------------------------
DROP TABLE IF EXISTS `bank_deposit_rate`;
CREATE TABLE `bank_deposit_rate` (
  `update_date` varchar(20) NOT NULL,
  `current_rate` double DEFAULT NULL,
  `zczq_tm_rate` double DEFAULT NULL,
  `zczq_hy_rate` double DEFAULT NULL,
  `zczq_oy_rate` double DEFAULT NULL,
  `zczq_twy_rate` double DEFAULT NULL,
  `zczq_ty_rate` double DEFAULT NULL,
  `zczq_fy_rate` double DEFAULT NULL,
  `other_oy_rate` double DEFAULT NULL,
  `other_ty_rate` double DEFAULT NULL,
  `other_fy_rate` double DEFAULT NULL,
  PRIMARY KEY (`update_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_deposit_rate
-- ----------------------------
BEGIN;
INSERT INTO `bank_deposit_rate` VALUES ('1541138029081', 0.3, 1.35, 1.55, 1.75, 2.25, 2.75, 2.75, 1.35, 1.55, 1.55);
COMMIT;

-- ----------------------------
-- Table structure for bank_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `bank_withdraw`;
CREATE TABLE `bank_withdraw` (
  `withdraw_id` varchar(20) NOT NULL,
  `cust_id` varchar(32) DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  `withdraw_money` double DEFAULT NULL,
  `withdraw_date` varchar(20) DEFAULT NULL,
  `arrive_time` varchar(20) DEFAULT NULL,
  `reviewer_id` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`withdraw_id`),
  KEY `cust_id` (`cust_id`),
  KEY `account` (`account`),
  CONSTRAINT `bank_withdraw_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `bank_customer` (`cust_id`),
  CONSTRAINT `bank_withdraw_ibfk_2` FOREIGN KEY (`account`) REFERENCES `bank_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_withdraw
-- ----------------------------
BEGIN;
INSERT INTO `bank_withdraw` VALUES ('18732146428743680', '18729916246011904', '6668541233587412', 321, '1541234091735', '1541234093735', '18805862675');
INSERT INTO `bank_withdraw` VALUES ('18732675548581888', '18729916246011904', '6668541233587412', 1000, '1541234217886', '1541234219886', '18805862675');
INSERT INTO `bank_withdraw` VALUES ('20939626994995200', '18729916246011904', '6668541233587412', 1000, '1541760396115', '1541760398115', '18805862675');
INSERT INTO `bank_withdraw` VALUES ('21619267518861312', '21616484916932608', '6668541921988419', 100, '1541922435043', '1541922437043', '15958360766');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
