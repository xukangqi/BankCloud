DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bank_account
-- ----------------------------
DROP TABLE IF EXISTS `bank_account`;
CREATE TABLE `bank_account` (
  `account` varchar(32) NOT NULL,
  `cust_id` varchar(32) DEFAULT NULL,
  `deposit_bank` varchar(256) DEFAULT NULL,
  `balances` double DEFAULT '0',
  `blocked_balances` double DEFAULT '0',
  `open_date` varchar(20) DEFAULT NULL,
  `cancel_date` varchar(20) DEFAULT NULL,
  `account_kind` varchar(16) DEFAULT NULL,
  `account_type` varchar(16) DEFAULT NULL,
  `account_status` varchar(16) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`account`),
  KEY `cust_id` (`cust_id`),
  CONSTRAINT `bank_account_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `bank_customer` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_account
-- ----------------------------
BEGIN;
INSERT INTO `bank_account` VALUES ('6668541233587412', '18729916246011904', '河北省秦皇岛市北戴河区123', 24390.15, 321, '1541233587489', NULL, '1', '2', '1', '202cb962ac59075b964b07152d234b70');
INSERT INTO `bank_account` VALUES ('6668541404022215', '19444414749618176', '北京市北京市西城区人民都', 99991300, 0, '1541404022274', NULL, '1', '1', NULL, '202cb962ac59075b964b07152d234b70');
INSERT INTO `bank_account` VALUES ('6668541921988419', '21616484916932608', '浙江省宁波市鄞州区中国工商银行宁波梅墟支行', 26974, 0, '1541921988423', NULL, '1', '1', NULL, 'e10adc3949ba59abbe56e057f20f883e');
COMMIT;

-- ----------------------------
-- Table structure for bank_customer
-- ----------------------------
DROP TABLE IF EXISTS `bank_customer`;
CREATE TABLE `bank_customer` (
  `cust_id` varchar(32) NOT NULL,
  `cust_name` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `identity_card` varchar(64) DEFAULT NULL,
  `cust_type` varchar(16) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT '0',
  `phone` varchar(32) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `credit` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_customer
-- ----------------------------
BEGIN;
INSERT INTO `bank_customer` VALUES ('18729916246011904', 'kangqi xu', '202cb962ac59075b964b07152d234b70', '331023199601110559', '1', 1, '18805862675', '吉林省辽源市东丰县123', 'x331705233@gmail.com', '1');
INSERT INTO `bank_customer` VALUES ('19444414749618176', '周健', '202cb962ac59075b964b07152d234b70', '330523199503204718', '1', 1, '17855824057', '天津市天津和平区宁大', 'x331705233@gmail.com', '1');
INSERT INTO `bank_customer` VALUES ('21616484916932608', 'jiafeng lu', 'e10adc3949ba59abbe56e057f20f883e', '330483199510012010', '1', 1, '15958360766', '浙江省嘉兴市桐乡市浙江省乌镇', '729957621@qq.com', '1');
COMMIT;

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

-- ----------------------------
-- Table structure for bank_loan_paylog
-- ----------------------------
DROP TABLE IF EXISTS `bank_loan_paylog`;
CREATE TABLE `bank_loan_paylog` (
  `paylog_id` varchar(20) NOT NULL,
  `trans_id` varchar(20) DEFAULT NULL,
  `pay_amount` double DEFAULT NULL,
  `pay_date` varchar(20) DEFAULT NULL,
  `account` varchar(32) NOT NULL,
  PRIMARY KEY (`paylog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_loan_paylog
-- ----------------------------
BEGIN;
INSERT INTO `bank_loan_paylog` VALUES ('18738208947183616', '18733822011379712', 1000, '1541235537152', '6668541233587412');
INSERT INTO `bank_loan_paylog` VALUES ('18739056754434048', '18733822011379712', 5000, '1541235739285', '6668541233587412');
INSERT INTO `bank_loan_paylog` VALUES ('19446575986515968', '19445647598292992', 10000, '1541404425015', '6668541404022215');
INSERT INTO `bank_loan_paylog` VALUES ('19861358590173184', '18733822011379712', 100, '1541503316894', '6668541233587412');
INSERT INTO `bank_loan_paylog` VALUES ('19864848477593600', '18733822011379712', 100, '1541504148948', '6668541233587412');
INSERT INTO `bank_loan_paylog` VALUES ('20939995934498816', '20939860991152128', 1000, '1541760484077', '6668541233587412');
INSERT INTO `bank_loan_paylog` VALUES ('21623609865150464', '21620609104875520', 3000, '1541923470339', '6668541921988419');
INSERT INTO `bank_loan_paylog` VALUES ('21623739724996608', '21620609104875520', 2000, '1541923501300', '6668541921988419');
COMMIT;

-- ----------------------------
-- Table structure for bank_loan_payment
-- ----------------------------
DROP TABLE IF EXISTS `bank_loan_payment`;
CREATE TABLE `bank_loan_payment` (
  `payment_id` varchar(20) NOT NULL,
  `trans_id` varchar(20) DEFAULT NULL,
  `ins_num` smallint(6) DEFAULT NULL,
  `payment_amount` double DEFAULT NULL,
  `payment_date` varchar(20) DEFAULT NULL,
  `is_finished` varchar(16) DEFAULT NULL,
  `fine_rate` double DEFAULT NULL,
  `all_payment_amount` double DEFAULT NULL,
  `reimbursement` double DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `trans_id` (`trans_id`),
  CONSTRAINT `bank_loan_payment_ibfk_1` FOREIGN KEY (`trans_id`) REFERENCES `bank_loan` (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_loan_payment
-- ----------------------------
BEGIN;
INSERT INTO `bank_loan_payment` VALUES ('18733822070099968', '18733822011379712', 1, 1025, '1543852799000', 'true', 0.05, 1025, 1025);
INSERT INTO `bank_loan_payment` VALUES ('18733822091071488', '18733822011379712', 2, 1025, '1546531199000', 'true', 0.05, 1025, 1025);
INSERT INTO `bank_loan_payment` VALUES ('18733822099460096', '18733822011379712', 3, 1025, '1549209599000', 'true', 0.05, 1025, 1025);
INSERT INTO `bank_loan_payment` VALUES ('18733822116237312', '18733822011379712', 4, 1025, '1551628799000', 'true', 0.05, 1025, 1025);
INSERT INTO `bank_loan_payment` VALUES ('18733822124625920', '18733822011379712', 5, 1025, '1554307199000', 'true', 0.05, 1025, 1025);
INSERT INTO `bank_loan_payment` VALUES ('18733822133014528', '18733822011379712', 6, 1025, '1556899199000', 'true', 0.05, 1025, 1025);
INSERT INTO `bank_loan_payment` VALUES ('18733822145597440', '18733822011379712', 7, 1025, '1559577599000', 'false', 0.05, 1025, 50);
INSERT INTO `bank_loan_payment` VALUES ('18733822158180352', '18733822011379712', 8, 1025, '1562169599000', 'false', 0.05, 1025, 0);
INSERT INTO `bank_loan_payment` VALUES ('18733822170763264', '18733822011379712', 9, 1025, '1564847999000', 'false', 0.05, 1025, 0);
INSERT INTO `bank_loan_payment` VALUES ('18733822187540480', '18733822011379712', 10, 1025, '1567526399000', 'false', 0.05, 1025, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806445428736', '18742806378319872', 1, 1061.89, '1543852799000', 'false', 0.08, 1061.89, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806466400256', '18742806378319872', 2, 1061.92, '1546531199000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806487371776', '18742806378319872', 3, 1061.92, '1549209599000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806504148992', '18742806378319872', 4, 1061.92, '1551628799000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806516731904', '18742806378319872', 5, 1061.92, '1554307199000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806533509120', '18742806378319872', 6, 1061.92, '1556899199000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806546092032', '18742806378319872', 7, 1061.92, '1559577599000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806562869248', '18742806378319872', 8, 1061.92, '1562169599000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806575452160', '18742806378319872', 9, 1061.92, '1564847999000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('18742806604812288', '18742806378319872', 10, 1061.92, '1567526399000', 'false', 0.08, 1061.92, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647707344896', '19445647598292992', 1, 458333.41, '1544025599000', 'false', 0.05, 458333.41, 10000);
INSERT INTO `bank_loan_payment` VALUES ('19445647728316416', '19445647598292992', 2, 458333.33, '1546703999000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647757676544', '19445647598292992', 3, 458333.33, '1549382399000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647778648064', '19445647598292992', 4, 458333.33, '1551801599000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647803813888', '19445647598292992', 5, 458333.33, '1554479999000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647837368320', '19445647598292992', 6, 458333.33, '1557071999000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647854145536', '19445647598292992', 7, 458333.33, '1559750399000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647870922752', '19445647598292992', 8, 458333.33, '1562342399000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647891894272', '19445647598292992', 9, 458333.33, '1565020799000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647908671488', '19445647598292992', 10, 458333.33, '1567699199000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647938031616', '19445647598292992', 11, 458333.33, '1570291199000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647963197440', '19445647598292992', 12, 458333.33, '1572969599000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647971586048', '19445647598292992', 13, 458333.33, '1575561599000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647984168960', '19445647598292992', 14, 458333.33, '1578239999000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445647996751872', '19445647598292992', 15, 458333.33, '1580918399000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648026112000', '19445647598292992', 16, 458333.33, '1583423999000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648047083520', '19445647598292992', 17, 458333.33, '1586102399000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648063860736', '19445647598292992', 18, 458333.33, '1588694399000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648101609472', '19445647598292992', 19, 458333.33, '1591372799000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648118386688', '19445647598292992', 20, 458333.33, '1593964799000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648151941120', '19445647598292992', 21, 458333.33, '1596643199000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648193884160', '19445647598292992', 22, 458333.33, '1599321599000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648256798720', '19445647598292992', 23, 458333.33, '1601913599000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('19445648273575936', '19445647598292992', 24, 458333.33, '1604591999000', 'false', 0.05, 458333.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861083430912', '20939860991152128', 1, 1037.5, '1544371199000', 'false', 0.05, 1037.5, 1000);
INSERT INTO `bank_loan_payment` VALUES ('20939861129568256', '20939860991152128', 2, 1037.5, '1547049599000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861146345472', '20939860991152128', 3, 1037.5, '1549727999000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861192482816', '20939860991152128', 4, 1037.5, '1552147199000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861209260032', '20939860991152128', 5, 1037.5, '1554825599000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861226037248', '20939860991152128', 6, 1037.5, '1557417599000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861247008768', '20939860991152128', 7, 1037.5, '1560095999000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861267980288', '20939860991152128', 8, 1037.5, '1562687999000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861280563200', '20939860991152128', 9, 1037.5, '1565366399000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20939861347672064', '20939860991152128', 10, 1037.5, '1568044799000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911531630592', '20948911380631552', 1, 1037.5, '1544371199000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911556796416', '20948911380631552', 2, 1037.5, '1547049599000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911577767936', '20948911380631552', 3, 1037.5, '1549727999000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911640682496', '20948911380631552', 4, 1037.5, '1552147199000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911661654016', '20948911380631552', 5, 1037.5, '1554825599000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911720374272', '20948911380631552', 6, 1037.5, '1557417599000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911770705920', '20948911380631552', 7, 1037.5, '1560095999000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911804260352', '20948911380631552', 8, 1037.5, '1562687999000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911816843264', '20948911380631552', 9, 1037.5, '1565366399000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('20948911850397696', '20948911380631552', 10, 1037.5, '1568044799000', 'false', 0.05, 1037.5, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609180377088', '21620609104875520', 1, 4358.37, '1544543999000', 'true', 0.05, 4358.37, 4358.37);
INSERT INTO `bank_loan_payment` VALUES ('21620609213931520', '21620609104875520', 2, 4358.33, '1547222399000', 'false', 0.05, 4358.33, 641.63);
INSERT INTO `bank_loan_payment` VALUES ('21620609230708736', '21620609104875520', 3, 4358.33, '1549900799000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609239097344', '21620609104875520', 4, 4358.33, '1552319999000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609251680256', '21620609104875520', 5, 4358.33, '1554998399000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609260068864', '21620609104875520', 6, 4358.33, '1557590399000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609264263168', '21620609104875520', 7, 4358.33, '1560268799000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609276846080', '21620609104875520', 8, 4358.33, '1562860799000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609289428992', '21620609104875520', 9, 4358.33, '1565539199000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609302011904', '21620609104875520', 10, 4358.33, '1568217599000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609310400512', '21620609104875520', 11, 4358.33, '1570809599000', 'false', 0.05, 4358.33, 0);
INSERT INTO `bank_loan_payment` VALUES ('21620609318789120', '21620609104875520', 12, 4358.33, '1573487999000', 'false', 0.05, 4358.33, 0);
COMMIT;

-- ----------------------------
-- Table structure for bank_loan_type
-- ----------------------------
DROP TABLE IF EXISTS `bank_loan_type`;
CREATE TABLE `bank_loan_type` (
  `loan_type_name` varchar(255) NOT NULL,
  `period_one` double DEFAULT NULL,
  `period_two` double DEFAULT NULL,
  `period_three` double DEFAULT NULL,
  `fine_rate` double DEFAULT NULL,
  PRIMARY KEY (`loan_type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_loan_type
-- ----------------------------
BEGIN;
INSERT INTO `bank_loan_type` VALUES ('住房贷款', 4.6, 5, 5.15, 0.05);
INSERT INTO `bank_loan_type` VALUES ('小微贷款', 7.3, 7.7, 7.9, 0.08);
INSERT INTO `bank_loan_type` VALUES ('消费贷款', 5.2, 5.7, 6, 0.06);
COMMIT;

-- ----------------------------
-- Table structure for bank_remit_log
-- ----------------------------
DROP TABLE IF EXISTS `bank_remit_log`;
CREATE TABLE `bank_remit_log` (
  `remit_id` varchar(20) NOT NULL,
  `remit_out_account` varchar(20) DEFAULT NULL,
  `remit_in_account` varchar(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `remit_generate_date` varchar(20) DEFAULT NULL,
  `remit_arrive_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`remit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_remit_log
-- ----------------------------
BEGIN;
INSERT INTO `bank_remit_log` VALUES ('19809682885971968', '6668541233587412', '6668541404022215', 1000, '1541490996458', '1541491057837');
INSERT INTO `bank_remit_log` VALUES ('20940316094238720', '6668541233587412', '6668541404022215', 100, '1541760560414', '1541760597264');
INSERT INTO `bank_remit_log` VALUES ('21624516254371840', '6668541921988419', '6668541233587412', 4000, '1541923686441', '1541923751411');
COMMIT;

-- ----------------------------
-- Table structure for bank_transfer_log
-- ----------------------------
DROP TABLE IF EXISTS `bank_transfer_log`;
CREATE TABLE `bank_transfer_log` (
  `transfer_id` varchar(20) NOT NULL,
  `transfer_out_account` varchar(20) DEFAULT NULL,
  `transfer_in_account` varchar(20) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transfer_date` varchar(20) DEFAULT NULL,
  `receive_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`transfer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_transfer_log
-- ----------------------------
BEGIN;
INSERT INTO `bank_transfer_log` VALUES ('19810579955191808', '6668541233587412', '6668541404022215', 100, '1541491210327', '1541491210329');
INSERT INTO `bank_transfer_log` VALUES ('20940646684954624', '6668541233587412', '6668541404022215', 100, '1541760639230', '1541760639232');
INSERT INTO `bank_transfer_log` VALUES ('21624930676641792', '6668541921988419', '6668541233587412', 10000, '1541923785246', '1541923785247');
COMMIT;

-- ----------------------------
-- Table structure for bank_user
-- ----------------------------
DROP TABLE IF EXISTS `bank_user`;
CREATE TABLE `bank_user` (
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `user_type` varchar(16) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_user
-- ----------------------------
BEGIN;
INSERT INTO `bank_user` VALUES ('15958360766', 'ljf', 'e10adc3949ba59abbe56e057f20f883e', '1', '15958360766', '浙江省宁波市鄞州区浙江大学软件学院', '729957621@qq.com');
INSERT INTO `bank_user` VALUES ('17855824057', 'zhoujian', '202cb962ac59075b964b07152d234b70', '1', '17855824057', '浙江省宁波市江北区宁波大学', 'x331705233@gmail.com');
INSERT INTO `bank_user` VALUES ('18805862675', '许康琪', '202cb962ac59075b964b07152d234b70', '1', '18805862675', '北京市北京市东城区123456', 'x331705233@gmail.com');
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
