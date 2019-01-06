DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

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
