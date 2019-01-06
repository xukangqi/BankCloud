DROP database if exists bank;

CREATE DATABASE bank DEFAULT CHARACTER SET utf8;

USE bank;

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
