DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

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
