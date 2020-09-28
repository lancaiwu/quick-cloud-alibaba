/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : alibaba_cloud_goods

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 28/09/2020 10:45:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_info`;
CREATE TABLE `t_goods_info`  (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `sku_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品skucode',
  `status` int(2) NOT NULL COMMENT '状态',
  `valid` tinyint(1) NOT NULL COMMENT '逻辑删除 1、未删除  0 已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user` bigint(32) NOT NULL COMMENT '创建人',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user` bigint(32) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
