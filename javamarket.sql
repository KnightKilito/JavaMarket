/*
 Navicat Premium Data Transfer

 Source Server         : T1
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : javamarket

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 07/01/2021 17:05:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (1, 'java', 23.00, 0);
INSERT INTO `good` VALUES (2, 'python', 32.00, 0);
INSERT INTO `good` VALUES (3, 'c++', 20.00, 0);
INSERT INTO `good` VALUES (5, 'html', 29.00, 0);
INSERT INTO `good` VALUES (6, 'php', 80.20, 0);
INSERT INTO `good` VALUES (10, 'VUE', 38.30, 0);
INSERT INTO `good` VALUES (11, 'html1', 29.00, 2);
INSERT INTO `good` VALUES (12, 'html1', 29.00, 100);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `workhour` int(0) NOT NULL,
  `salary` decimal(10, 2) NOT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'boss', '123123', 45, 2375.00, '');
INSERT INTO `user` VALUES (2, 'lisi', '2333', 200, 2585.00, '12121212121');
INSERT INTO `user` VALUES (3, 'wanghon', '0112', 10, 2030.00, '');
INSERT INTO `user` VALUES (4, 'zs', '123456', 0, 2000.00, '');
INSERT INTO `user` VALUES (6, 'lihua', '123', 0, 0.00, '15022222222');

SET FOREIGN_KEY_CHECKS = 1;
