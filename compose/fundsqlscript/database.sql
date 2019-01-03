DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

-- ----------------------------
-- Table structure for bank_fund_hold
-- ----------------------------
DROP TABLE IF EXISTS `bank_fund_hold`;
CREATE TABLE `bank_fund_hold` (
  `cust_id` varchar(32) NOT NULL,
  `account` varchar(32) NOT NULL,
  `fund_id` varchar(16) NOT NULL,
  `share` double NOT NULL,
  PRIMARY KEY (`account`,`fund_id`),
  KEY `cust_id` (`cust_id`),
  CONSTRAINT `bank_fund_hold_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `bank_customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_fund_hold
-- ----------------------------
BEGIN;
INSERT INTO `bank_fund_hold` VALUES ('18729916246011904', '6668541233587412', '02401484', 0);
INSERT INTO `bank_fund_hold` VALUES ('18729916246011904', '6668541233587412', '30663782', 71.54);
INSERT INTO `bank_fund_hold` VALUES ('18729916246011904', '6668541233587412', '39826585', 181.13);
INSERT INTO `bank_fund_hold` VALUES ('21616484916932608', '6668541921988419', '59951923', 1261.9);
COMMIT;

-- ----------------------------
-- Table structure for bank_fund_log
-- ----------------------------
DROP TABLE IF EXISTS `bank_fund_log`;
CREATE TABLE `bank_fund_log` (
  `fund_tx_id` varchar(20) NOT NULL,
  `cust_id` varchar(32) DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  `fund_id` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `share` double DEFAULT NULL,
  `tx_date` varchar(20) DEFAULT NULL,
  `review_id` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`fund_tx_id`),
  KEY `cust_id` (`cust_id`),
  KEY `account` (`account`),
  CONSTRAINT `bank_fund_log_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `bank_customer` (`cust_id`),
  CONSTRAINT `bank_fund_log_ibfk_2` FOREIGN KEY (`account`) REFERENCES `bank_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_fund_log
-- ----------------------------
BEGIN;
INSERT INTO `bank_fund_log` VALUES ('19816576388440064', '18729916246011904', '6668541233587412', '39826585', 'purchase', 100, 18.12, '1541492639985', '18805862675');
INSERT INTO `bank_fund_log` VALUES ('19816936431689728', '18729916246011904', '6668541233587412', '39826585', 'redemption', 96, 18.11, '1541492725828', '18805862675');
INSERT INTO `bank_fund_log` VALUES ('20130550229245952', '18729916246011904', '6668541233587412', '39826585', '0', 1000, 181.13, '1541567497183', '18805862675');
INSERT INTO `bank_fund_log` VALUES ('20130967931592704', '18729916246011904', '6668541233587412', '30663782', '0', 100, 71.54, '1541567596770', '18805862675');
INSERT INTO `bank_fund_log` VALUES ('20940850864070656', '18729916246011904', '6668541233587412', '02401484', '0', 100, 26.49, '1541760687909', '18805862675');
INSERT INTO `bank_fund_log` VALUES ('20940972037513216', '18729916246011904', '6668541233587412', '02401484', '1', 98.013, 26.49, '1541760716803', '18805862675');
INSERT INTO `bank_fund_log` VALUES ('21626580120641536', '21616484916932608', '6668541921988419', '59951923', '0', 10000, 2261.9, '1541924178504', '15958360766');
INSERT INTO `bank_fund_log` VALUES ('21627280644907008', '21616484916932608', '6668541921988419', '59951923', '1', 4200, 1000, '1541924345523', '15958360766');
COMMIT;

-- ----------------------------
-- Table structure for bank_fund_product
-- ----------------------------
DROP TABLE IF EXISTS `bank_fund_product`;
CREATE TABLE `bank_fund_product` (
  `fund_id` varchar(16) NOT NULL,
  `type` varchar(16) DEFAULT NULL,
  `purchase_rate` double DEFAULT NULL,
  `net_asset_value` double DEFAULT NULL,
  `redemption_rate` double DEFAULT NULL,
  `purchase_date` varchar(20) NOT NULL,
  PRIMARY KEY (`purchase_date`,`fund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_fund_product
-- ----------------------------
BEGIN;
INSERT INTO `bank_fund_product` VALUES ('39826585', '申购', 0.04, 5.3, 0.03, '1541491506308');
INSERT INTO `bank_fund_product` VALUES ('02401484', '申购', 0.02, 3.7, 0.01, '1541491545080');
INSERT INTO `bank_fund_product` VALUES ('59951923', '申购', 0.05, 4.2, 0.03, '1541491560476');
INSERT INTO `bank_fund_product` VALUES ('67717120', '申购', 0.03, 2.9, 0.01, '1541491579568');
INSERT INTO `bank_fund_product` VALUES ('30663782', '申购', 0.07, 1.3, 0.04, '1541491642899');
INSERT INTO `bank_fund_product` VALUES ('26334259', '申购', 0.067, 3.3, 0.032, '1541491672691');
INSERT INTO `bank_fund_product` VALUES ('64942489', '申购', 0.02, 3.9, 0.01, '1541491697817');
INSERT INTO `bank_fund_product` VALUES ('79106867', '申购', 0.027, 4.6, 0.022, '1541491726461');
INSERT INTO `bank_fund_product` VALUES ('70162688', '申购', 0.029, 4.1, 0.025, '1541491735738');
INSERT INTO `bank_fund_product` VALUES ('23080140', '申购', 0.033, 7.1, 0.027, '1541491756845');
COMMIT;


