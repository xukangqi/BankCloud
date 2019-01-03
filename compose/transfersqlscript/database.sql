DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

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

