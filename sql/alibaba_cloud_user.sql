/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : alibaba_cloud_user

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 28/09/2020 10:45:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `nick` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `status` tinyint(2) NOT NULL COMMENT '状态',
  `valid` tinyint(1) NOT NULL COMMENT '逻辑删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` bigint(32) NOT NULL COMMENT '创建人',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` bigint(32) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
