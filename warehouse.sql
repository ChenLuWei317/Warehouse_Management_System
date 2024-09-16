/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 16/09/2024 15:49:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 软工2202_09_05_29人员表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29人员表`;
CREATE TABLE `软工2202_09_05_29人员表`  (
  `人员代码` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `密码` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `姓名` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `性别` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `出生日期` date NOT NULL,
  `身份证号` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `籍贯` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `地址` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `联系电话` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`人员代码`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29人员表
-- ----------------------------
INSERT INTO `软工2202_09_05_29人员表` VALUES ('1', '1', 'admin', '男', '2019-10-01', '3', '丰富', '方法', '222', NULL);
INSERT INTO `软工2202_09_05_29人员表` VALUES ('2', '123abc', 'xxx', '男', '1999-11-26', '350125201911260011', 'fjut', 'ds', '13635228932', NULL);

-- ----------------------------
-- Table structure for 软工2202_09_05_29权限管理
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29权限管理`;
CREATE TABLE `软工2202_09_05_29权限管理`  (
  `人员代码` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `人员档案管理` int NULL DEFAULT 0,
  `物料档案管理` int NULL DEFAULT 0,
  `进出仓管理` int NULL DEFAULT 0,
  `管理权限` int NULL DEFAULT 0,
  `统计打印` int NULL DEFAULT 0,
  PRIMARY KEY (`人员代码`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29权限管理
-- ----------------------------
INSERT INTO `软工2202_09_05_29权限管理` VALUES ('1', 1, 1, 1, 1, 1);
INSERT INTO `软工2202_09_05_29权限管理` VALUES ('2', 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for 软工2202_09_05_29物料表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29物料表`;
CREATE TABLE `软工2202_09_05_29物料表`  (
  `物料代码` int NOT NULL AUTO_INCREMENT,
  `物料名称` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `规格型号` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `计量单位` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `库存数量` int NULL DEFAULT 0,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`物料代码`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29物料表
-- ----------------------------
INSERT INTO `软工2202_09_05_29物料表` VALUES (1, '拯救者r720', 'i5 1050', '台', 2223, NULL);
INSERT INTO `软工2202_09_05_29物料表` VALUES (2, 'dd', 'asjdlak', '只', 200, NULL);
INSERT INTO `软工2202_09_05_29物料表` VALUES (3, '小米手环2', '黑色', '个', 2666, '无');
INSERT INTO `软工2202_09_05_29物料表` VALUES (4, '苹果', '阿富汗', '千克', 0, '无');
INSERT INTO `软工2202_09_05_29物料表` VALUES (5, '雪碧', '箱', '卢', 3000, '无');

-- ----------------------------
-- Table structure for 软工2202_09_05_29计量单位
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29计量单位`;
CREATE TABLE `软工2202_09_05_29计量单位`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `单位` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`, `单位`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29计量单位
-- ----------------------------
INSERT INTO `软工2202_09_05_29计量单位` VALUES (1, '箱');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (2, '个');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (3, '台');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (4, '只');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (5, '千克');

-- ----------------------------
-- Table structure for 软工2202_09_05_29进出仓表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29进出仓表`;
CREATE TABLE `软工2202_09_05_29进出仓表`  (
  `单号` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `次数` int NULL DEFAULT NULL,
  `类型` int NOT NULL,
  `物料代码` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `操作人员代码` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `日期` date NOT NULL,
  `数量` int NOT NULL,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`单号`, `物料代码`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29进出仓表
-- ----------------------------
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911241', 0, 1, '2', '1', '2019-11-24', 100, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911242', 0, 1, '1', '1', '2019-11-24', 100, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911243', 0, 1, '3', '1', '2019-11-24', 0, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911251', 0, 1, '1', '1', '2019-11-25', 100, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911252', 0, 1, '1', '1', '2019-11-25', 100, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911253', 1, 1, '1', '1', '2019-11-25', 500, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911253', 0, 1, '2', '1', '2019-11-25', 100, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912061', 0, 1, '1', '1', '2019-12-06', 1223, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912062', 0, 1, '3', '2', '2019-12-06', 2666, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912063', 0, 1, '6', '2', '2019-12-06', 1000, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912064', 0, 1, '6', '2', '2019-12-06', 1000, '无');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912065', 0, 1, '6', '1', '2019-12-06', 1000, '无');

-- ----------------------------
-- Table structure for 软工2202_09_05_29进出单号表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29进出单号表`;
CREATE TABLE `软工2202_09_05_29进出单号表`  (
  `数量` int NOT NULL,
  `类型` int NOT NULL,
  `日期` date NOT NULL,
  PRIMARY KEY (`数量`, `类型`, `日期`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29进出单号表
-- ----------------------------
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (1, 1, '2019-11-24');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (1, 1, '2019-11-25');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (1, 1, '2019-12-06');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (2, 1, '2019-11-24');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (2, 1, '2019-11-25');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (2, 1, '2019-12-06');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (3, 1, '2019-11-24');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (3, 1, '2019-11-25');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (3, 1, '2019-12-06');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (4, 1, '2019-12-06');
INSERT INTO `软工2202_09_05_29进出单号表` VALUES (5, 1, '2019-12-06');

-- ----------------------------
-- Procedure structure for 添加单号
-- ----------------------------
DROP PROCEDURE IF EXISTS `添加单号`;
delimiter ;;
CREATE PROCEDURE `添加单号`(IN 数量s INT,
  IN 类型s INT,
	IN 日期s VARCHAR(20))
BEGIN
  INSERT INTO 软工1702_21_22_进出单号表 VALUES(数量s,类型s,日期s);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for 统计查询
-- ----------------------------
DROP PROCEDURE IF EXISTS `统计查询`;
delimiter ;;
CREATE PROCEDURE `统计查询`(IN 开始时间 VARCHAR(20),
	IN 结束时间 VARCHAR(20))
BEGIN
  select `物料名称`, SUM(`数量`) as 总和 
  from 软工1702_21_22_进出仓表
  RIGHT JOIN 软工1702_21_22_物料表
  ON `软工1702_21_22_进出仓表`.`物料代码` = `软工1702_21_22_物料表`.`物料代码` 
  AND `日期` >= 开始时间 AND `日期` <= 结束时间
  GROUP BY(`物料名称`);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for 账单查询
-- ----------------------------
DROP PROCEDURE IF EXISTS `账单查询`;
delimiter ;;
CREATE PROCEDURE `账单查询`(IN 日期s VARCHAR(20),IN 物料代码s varchar(20))
BEGIN
	select `软工1702_21_22_物料表`.`物料代码`,物料名称,规格型号,计量单位,类型,数量 
  from `软工1702_21_22_物料表`,`软工1702_21_22_进出仓表`
  where `软工1702_21_22_物料表`.`物料代码` = 物料代码s
  AND`软工1702_21_22_物料表`.`物料代码` = `软工1702_21_22_进出仓表`.`物料代码`
  AND `日期` > 日期s;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for 进出仓开单
-- ----------------------------
DROP PROCEDURE IF EXISTS `进出仓开单`;
delimiter ;;
CREATE PROCEDURE `进出仓开单`(IN 单号s VARCHAR(20),
  IN 次数s INT,
	IN 类型s INT,
  IN 物料代码s VARCHAR(20),
  IN 操作人员代码s VARCHAR(20),
  IN 日期s DATE,
  IN 开单数量s INT, 
	IN 备注s VARCHAR(255))
BEGIN
  IF 类型s = 1 THEN
    INSERT
    INTO `软工1702_21_22_进出仓表`
    VALUES(单号s,次数s,类型s,物料代码s,操作人员代码s,日期s,开单数量s,备注s);
  ELSEIF 类型s = 2 THEN
    INSERT
    INTO `软工1702_21_22_进出仓表`
    VALUES(单号s,次数s,类型s,物料代码s,操作人员代码s,日期s,开单数量s,备注s);
  END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
