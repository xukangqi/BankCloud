DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

-- ----------------------------
-- Table structure for bank_loan
-- ----------------------------
DROP TABLE IF EXISTS `bank_loan`;
CREATE TABLE `bank_loan` (
  `trans_id` varchar(20) NOT NULL,
  `cust_id` varchar(32) NOT NULL,
  `account` varchar(32) NOT NULL,
  `trans_date` varchar(20) DEFAULT NULL,
  `loan_amount` double DEFAULT NULL,
  `ins_count` smallint(6) DEFAULT NULL,
  `loan_interest` double DEFAULT NULL,
  `loan_amount_sum` double DEFAULT NULL,
  `expiration_date` varchar(20) DEFAULT NULL,
  `recovered_amount` double DEFAULT NULL,
  `loan_status` varchar(16) DEFAULT NULL,
  `reviewer_id` varchar(16) DEFAULT NULL,
  `loan_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`trans_id`),
  KEY `loan_type_name` (`loan_type_name`),
  KEY `cust_id` (`cust_id`),
  KEY `account` (`account`),
  CONSTRAINT `bank_loan_ibfk_1` FOREIGN KEY (`loan_type_name`) REFERENCES `bank_loan_type` (`loan_type_name`),
  CONSTRAINT `bank_loan_ibfk_2` FOREIGN KEY (`cust_id`) REFERENCES `bank_customer` (`cust_id`),
  CONSTRAINT `bank_loan_ibfk_3` FOREIGN KEY (`account`) REFERENCES `bank_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_loan
-- ----------------------------
BEGIN;
INSERT INTO `bank_loan` VALUES ('18733822011379712', '18729916246011904', '6668541233587412', '1541234491229', 10000, 10, 3, 10250, '1567526399000', 6200, '未到期', NULL, '住房贷款');
INSERT INTO `bank_loan` VALUES ('18742806378319872', '18729916246011904', '6668541233587412', '1541236633270', 10000, 10, 7.43, 10619.17, '1567526399000', 0, '未到期', '18805862675', '小微贷款');
INSERT INTO `bank_loan` VALUES ('19445647598292992', '19444414749618176', '6668541404022215', '1541404203679', 10000000, 24, 5, 11000000, '1604591999000', 10000, '未到期', '17855824057', '住房贷款');
INSERT INTO `bank_loan` VALUES ('20939860991152128', '18729916246011904', '6668541233587412', '1541760451907', 10000, 10, 4.5, 10375, '1568044799000', 1000, '未到期', '18805862675', '住房贷款');
INSERT INTO `bank_loan` VALUES ('20948911380631552', '18729916246011904', '6668541233587412', '1541762609696', 10000, 10, 4.5, 10375, '1568044799000', 0, '未到期', '18805862675', '住房贷款');
INSERT INTO `bank_loan` VALUES ('21620609104875520', '21616484916932608', '6668541921988419', '1541922754910', 50000, 12, 4.6, 52300, '1573487999000', 5000, '未到期', '15958360766', '住房贷款');
COMMIT;
