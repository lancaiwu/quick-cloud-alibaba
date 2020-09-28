/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : alibaba_cloud_order

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 28/09/2020 10:45:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_order_info`;
CREATE TABLE `t_order_info`  (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `goods_id` bigint(32) NOT NULL COMMENT '商品id',
  `goods_num` int(11) NOT NULL COMMENT '商品件数',
  `user_id` bigint(32) NOT NULL COMMENT '下单用户id',
  `status` int(2) NOT NULL COMMENT '状态',
  `valid` tinyint(1) NOT NULL COMMENT '逻辑删除 1、未删除  0 已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` bigint(32) NOT NULL COMMENT '创建人',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` bigint(32) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_info
-- ----------------------------
INSERT INTO `t_order_info` VALUES (1, 'T3242342342', 1, 1, 1, 1, 1, '2020-09-25 11:04:32', 1, '2020-09-25 11:04:35', 1);

SET FOREIGN_KEY_CHECKS = 1;
