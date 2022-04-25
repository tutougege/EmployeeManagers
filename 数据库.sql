/*
 Navicat Premium Data Transfer

 Source Server         : mute
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : project

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 12/09/2021 12:46:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `empID` bigint NOT NULL AUTO_INCREMENT,
  `empName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `empPassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `empPhone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `empMail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deptID` int NULL DEFAULT NULL,
  PRIMARY KEY (`empID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1048 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1007, '孙大喜', 'c9130efba1317c16406d67270f657af3', '15121725105', '1215856341@qq.com', 101);
INSERT INTO `emp` VALUES (1010, '孙梅', '71b3b26aaa319e0cdf6fdb8429c112b0', '15885696898', '15794554652@qq.com', 105);
INSERT INTO `emp` VALUES (1011, '雷羿轩', 'd0fa9ead4ba8f67b80f82334a4beb090', '15647895113', '78245464512@qq.com', 104);
INSERT INTO `emp` VALUES (1012, '雷宇航', '3a715498b51a468e87e07820b3e72734', '12456782556', '15464984546@qq.com', 103);
INSERT INTO `emp` VALUES (1013, '孙志翰', 'b325f436407285d832b9fcdf0f204a70', '16654563463', '45678434646@qq.com', 106);
INSERT INTO `emp` VALUES (1014, '孙梦琦', 'cdc29734930534a7922365ca78461c33', '15498985235', '46543636366@qq.com', 106);
INSERT INTO `emp` VALUES (1015, '孙磊', 'aa3302c70c500da61d089869857e6c3c', '16598975564', '45664365346@qq.com', 101);
INSERT INTO `emp` VALUES (1016, '孙辉', '357ee7d53e13a8fff8febc1629ffa04e', '14898436834', '49772446445@qq.com', 101);
INSERT INTO `emp` VALUES (1017, '王广鑫', 'e01529e041c47530e9c772a80517f75e', '15976546464', '54646446545@qq.com', 103);
INSERT INTO `emp` VALUES (1018, '狂神', '2fb5e413d68dc9839fb258f0ce129e2d', '15677433545', '43654687836@qq.com', 105);
INSERT INTO `emp` VALUES (1019, 'mute', 'e10adc3949ba59abbe56e057f20f883e', '15121725106', '2294407483@qq.com', 102);
INSERT INTO `emp` VALUES (1020, '孔令浩', '96e79218965eb72c92a549dd5a330112', '15976546469', '54646446590@qq.com', 103);
INSERT INTO `emp` VALUES (1022, '孙大喜', '9a3dd9c3adfbb5803beb9d45b6f5c9dd', '15598760289', '12158563787@qq.com', 106);
INSERT INTO `emp` VALUES (1024, 'psych', '202390d60cce0fa7c6f132390de678fa', '15121755124', '45646565561@qq.com', 105);
INSERT INTO `emp` VALUES (1025, 'psy', '202390d60cce0fa7c6f132390de678fa', '15121755167', '45646565456@qq.com', 104);
INSERT INTO `emp` VALUES (1026, 'psy', '202390d60cce0fa7c6f132390de678fa', '15121781568', '45646556789@qq.com', 106);
INSERT INTO `emp` VALUES (1027, '慕容云海', '4607e782c4d86fd5364d7e4508bb10d9', '15121785689', '45646551235@qq.com', 103);
INSERT INTO `emp` VALUES (1028, '上官婉儿', 'e10adc3949ba59abbe56e057f20f883e', '15689653245', '9864546515@qq.com', 102);
INSERT INTO `emp` VALUES (1031, '高露', '4607e782c4d86fd5364d7e4508bb10d9', '15698751249', '1665465465@qq.com', 105);
INSERT INTO `emp` VALUES (1037, '龙明', '14e1b600b1fd579f47433b88e8d85291', '19023584265', '5682656564@qq.com', 101);
INSERT INTO `emp` VALUES (1041, '狂神', '6074c6aa3488f3c2dddff2a7ca821aab', '495455454', '134564654@qq.com', 105);
INSERT INTO `emp` VALUES (1042, '雷艺璇', 'b59c67bf196a4758191e42f76670ceba', '15689721515', '189959595@qq.com', 104);
INSERT INTO `emp` VALUES (1043, '雷宇航', '443dec3062d0286986e21dc0631734c9', '15985646464', '2254949494@qq.com', 105);
INSERT INTO `emp` VALUES (1045, '龙运明明', '14e1b600b1fd579f47433b88e8d85291', '16598444218', '56562655@qq.com', 101);
INSERT INTO `emp` VALUES (1046, '龙运明', 'e3ceb5881a0a1fdaad01296d7554868d', '19845451156', '564645454@qq.com', 101);
INSERT INTO `emp` VALUES (1048, '鲍增威', '14e1b600b1fd579f47433b88e8d85291', '15666077890', '412030730@qq.com', 102);

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
CREATE TABLE dept  (
  `deptID` int NOT NULL AUTO_INCREMENT,
  `dname` varchar(255),
  PRIMARY KEY (`deptID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (101, '技术部');
INSERT INTO `dept` VALUES (102, '财务部');
INSERT INTO `dept` VALUES (103, '后勤部');
INSERT INTO `dept` VALUES (104, '市场部');
INSERT INTO `dept` VALUES (105, '教研部');
INSERT INTO `dept` VALUES (106, '运维部');

SET FOREIGN_KEY_CHECKS = 1;



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `reporterID` int NOT NULL,
  `reporterName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reportName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reportFile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reportDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`reporterID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1007, '孙大喜', 'Oracle测试', 'oracle数据库.txt', '2021-09-12 11:13:46');
INSERT INTO `report` VALUES (1010, '孙梅', '离散数学', '有效沟通.txt', '2021-09-12 11:24:37');
INSERT INTO `report` VALUES (1045, '龙运明明', '测试', '20190148036孙大喜.docx', '2021-09-11 15:05:35');
INSERT INTO `report` VALUES (1046, '龙运明', '测试', 'JavaEE实现Action两种方式.txt', '2021-09-11 15:04:13');
INSERT INTO `report` VALUES (1047, '123', '测试', '8522817.jpg', '2021-09-11 14:17:44');

SET FOREIGN_KEY_CHECKS = 1;
