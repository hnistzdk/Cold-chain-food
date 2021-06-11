/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : food

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 11/06/2021 20:12:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_login_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_times` int(0) NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '1:true 0:false'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('277804', '张五风', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '王恩龙', '男', '18492857685', '369365576@qq.com', '2021-04-04', '2021-04-23', 5, '货单管理员', 4, 1);
INSERT INTO `tb_admin` VALUES ('436839', 'fawai', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张三', '男', '13427584359', '369365576@qq.com', '2021-04-01', '2021-05-23', 15, '食品管理员', 3, 1);
INSERT INTO `tb_admin` VALUES ('701670', 'fengli', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '李兴峰', '女', '13859375831', '369365576@qq.com', '2021-04-10', '2021-05-30', 7, '货单管理员', 4, 1);
INSERT INTO `tb_admin` VALUES ('763366', 'xiaxia', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '夏雅慧', '女', '18594738675', '369365576@qq.com', '2021-04-08', '2021-05-29', 8, '用户管理员', 2, 1);
INSERT INTO `tb_admin` VALUES ('941623', 'kuangtu', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '李四', '男', '14672849303', '369365576@qq.com', '2021-04-01', '2021-04-23', 5, '管理员', 5, 1);
INSERT INTO `tb_admin` VALUES ('981498', 'xiaoyu', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '李玉婷', '女', '18435758454', '369365576@qq.com', '2021-04-03', '2021-06-10', 6, '管理员', 5, 1);
INSERT INTO `tb_admin` VALUES ('369365576', 'zdk2', '$2a$10$N0rT1IGdwVfpJ90C5MzU/uKm2m8Fi.352xGXBNfEeHJ0ewY0b9Om6', '张迪凯', '男', '13696086376', '369365576@qq.com', '2021-04-03', '2021-06-11', 276, '超级管理员', 1, 1);
INSERT INTO `tb_admin` VALUES ('edc8d43', '测试添加3', '$2a$10$iKIu2rr3IBnkGsd3vAw4NOW5g/1vMWp1KTkQu/YPQ1BHBwG2PzFsu', NULL, '男', '13222222221', '1221', '2021-06-09', NULL, 0, '管理员', 5, 1);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '食品类别名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, '蔬菜');
INSERT INTO `tb_category` VALUES (2, '水果');
INSERT INTO `tb_category` VALUES (3, '肉类');
INSERT INTO `tb_category` VALUES (4, '零食');
INSERT INTO `tb_category` VALUES (10, '测试222');
INSERT INTO `tb_category` VALUES (11, '测试');
INSERT INTO `tb_category` VALUES (12, '测试222');

-- ----------------------------
-- Table structure for tb_employee
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee`  (
  `employee_id` int(0) NOT NULL COMMENT '从业人员编号',
  `storage_id` int(0) NULL DEFAULT NULL COMMENT '站点id',
  `uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `detection` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核酸检测信息',
  `notification` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '核酸检测异常通知',
  PRIMARY KEY (`employee_id`) USING BTREE,
  INDEX `FK_Reference_1`(`uid`) USING BTREE,
  INDEX `FK_Reference_2`(`storage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_employee
-- ----------------------------

-- ----------------------------
-- Table structure for tb_enterprise_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_enterprise_user`;
CREATE TABLE `tb_enterprise_user`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_login_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_times` int(0) NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '1:true 0:false',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_enterprise_user
-- ----------------------------
INSERT INTO `tb_enterprise_user` VALUES ('1fdb0c', 'ceshi ', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', 'ceshi', '女', '测试', '13213213111', '222@qq.com', '2021-05-30', NULL, 0, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('2255ed', '企业用户再测试', '$2a$10$Df8kByV71ZC.d2R4MbR19O697kUYLq9N.HKcTQBI.uM67KCw0GhV6', '6.112', '男', '测试', '13333333333', '369365576@qq.com', '2021-06-11', '2021-06-11', 19, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('2518 ', '测试企业用户修改', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张嘉玲22', '男', '华为', '18594738675', '369365576@qq.com', '2021-04-09', '2021-06-11', 78, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('259587', '测试2222', '$2a$10$JS84bug6vze8o0U8NSM2I.G8mEDCTwC8XjRuEDjIYdq5qnIIOhSvi', '打开22', NULL, NULL, NULL, '369365576@qq.com', '2021-06-09', NULL, 0, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('2d0fbf', '再次测试注册加密', '$2a$10$W8u5M8PLrePfLyhM3OkadelkqSXUqq1piNqBJm6HaMI/WYc8XZ8Im', '张迪凯', NULL, NULL, NULL, '369365576@qq.com', '2021-06-09', '2021-06-09', 1, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('305296', '测试5302', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '测试修改真实姓名', '男', NULL, '13212369521', '369365576@qq.com', '2021-05-30', '2021-06-09', 1, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('36ef0d', 'salj', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '打艾克', '女', 'asdasd', '11111111111', '11@qq.com', '2021-05-23', NULL, 0, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('386f78', '测试id回显', '$2a$10$Gf/4tydU/ledjD8dB88Kt.Lmy0Ygx.UsFjLnXGOpF6C/qCawPtdKW', '张迪凯', NULL, NULL, NULL, '369365576@qq.com', '2021-06-09', NULL, 0, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('508cd6', '张振明', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张振明', '男', '小米', '11111111111', '222@qq.com', '2021-04-25', '2021-06-11', 3, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('5632 ', 'yihui', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '蔡宜慧', '女', '华环', '13859375831', '369365576@qq.com', '2021-04-07', '2021-04-11', 5, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('5da045', '风主主', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '测试', '女', '测试', '13211111111', '369365576@qq.com', '2021-06-09', '2021-06-11', 2, '企业用户', 7, 1);
INSERT INTO `tb_enterprise_user` VALUES ('6933 ', 'xiaowen', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '黄静雯', '女', '庆万', '19284736584', '369365576@qq.com', '2021-04-06', '2021-04-10', 5, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('71dc65', '测试密码加密', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, NULL, NULL, NULL, '369365576@qq.com', '2021-06-07', '2021-06-07', 7, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('7225 ', 'daniao', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张鸿信', '男', '腾达', '13427584359', '369365576@qq.com', '2021-04-01', '2021-04-06', 8, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('76636d', '邮箱测试', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, '女', '腾讯', '16161718181', '369365576@qq.com', '2021-04-28', '2021-04-28', 1, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('8729', 'jiajia', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '何佳佳', '女', '生源', '13958475869', '369365576@qq.com', '2021-04-10', '2021-04-12', 2, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('8893 ', 'junan', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '苏俊安', '男', '振韦', '18492857685', '369365576@qq.com', '2021-04-05', '2021-04-12', 15, '企业用户', 7, 0);
INSERT INTO `tb_enterprise_user` VALUES ('97c6d8', '6.9测试', '$2a$10$qHr3NH5pQlXK4.PTP80Ece9llfoXe0TAqjUkMXeewlBM.1oxp.Dy2', '张振明', NULL, NULL, NULL, '369365576@qq.com', '2021-06-09', NULL, 0, '企业用户', 7, NULL);

-- ----------------------------
-- Table structure for tb_food
-- ----------------------------
DROP TABLE IF EXISTS `tb_food`;
CREATE TABLE `tb_food`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `food_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food_category` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '食品类别名称',
  `production_date` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产日期',
  `expiry_date` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过期日期',
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产地',
  `producer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产厂家',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂家联系电话',
  `storage_id` int(0) NULL DEFAULT NULL COMMENT '储存站点id',
  `risk_degree` int(0) NULL DEFAULT NULL COMMENT '风险等级',
  `category_id` int(0) NULL DEFAULT NULL COMMENT '食品类别id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_food
-- ----------------------------
INSERT INTO `tb_food` VALUES (1, '旺旺雪饼', '零食', '2020-10-24', '2021-03-24', '湖南长沙', '湖南飘香食品有限公司', '073189916124', 4, 2, 4);
INSERT INTO `tb_food` VALUES (2, '泰国炒米', '零食', '2020-08-19', '2021-05-19', '湖南长沙', '湖南飘香食品有限公司', '073189916129', 4, 1, 4);
INSERT INTO `tb_food` VALUES (3, '乐事薯片', '零食', '2020-09-22', '2021-06-22', '上海北京武汉', '唐山市汇旺行工贸有限公司', '15070786227', 4, 1, 4);
INSERT INTO `tb_food` VALUES (4, '炭烧腰果', '零食', '2020-10-04', '2021-04-09', '中国大陆', '三只松鼠股份有限公司', '4008004900', 4, 1, 4);
INSERT INTO `tb_food` VALUES (5, '芒果干', '零食', '2020-09-16', '2021-04-12', '中国大陆', '杭州赫姆斯食品有限公司', '4008810118', 4, 1, 4);
INSERT INTO `tb_food` VALUES (6, '碧根果', '零食', '2020-09-20', '2021-09-24', '中国大陆', '杭州富阳松之皇食品厂', '13857151360', 4, 1, 4);
INSERT INTO `tb_food` VALUES (7, '手撕面包', '零食', '2021-02-13', '2021-04-06', '中国大陆', '福建省新麦食品有限公司', '4008004900', 4, 1, 4);
INSERT INTO `tb_food` VALUES (8, '火鸡面', '零食', '2020-08-01', '2020-08-31', '中国大陆', '广西南宁市韩太食品有限责任公司', '15154510492', 4, 1, 4);
INSERT INTO `tb_food` VALUES (9, '夹心饼干', '零食', '2021-01-01', '2021-06-01', '中国大陆', '龙海市鸿祺食品有限公司', '05925800750', 4, 1, 4);
INSERT INTO `tb_food` VALUES (10, '沙琪玛', '零食', '2020-12-01', '2021-06-01', '中国大陆', '莒县众诺食品有限公司', '4008060222', 4, 1, 4);
INSERT INTO `tb_food` VALUES (11, '猪肉', '肉类', '2021-12-02', '2022-12-02', '湖南长沙', '菜市场', '4008060222', 3, 1, 3);
INSERT INTO `tb_food` VALUES (12, '牛肉', '肉类', '2021-12-02', '2022-12-02', '湖南长沙', '菜市场', '4008060222', 3, 1, 3);
INSERT INTO `tb_food` VALUES (14, '土豆', '蔬菜', '2021-12-02', '2022-12-02', '湖南长沙', '菜市场', '4008060222', 1, 1, 1);
INSERT INTO `tb_food` VALUES (15, '萝卜', '蔬菜', '2021-12-02', '2022-12-02', '湖南长沙', '菜市场', '4008060222', 1, 1, 1);
INSERT INTO `tb_food` VALUES (16, '苹果', '水果', '2021-12-02', '2022-12-02', '湖南长沙', '菜市场', '4008060222', 2, 1, 2);
INSERT INTO `tb_food` VALUES (22, '西瓜', '水果', '2021-12-02', '2022-12-02', '湖南长沙', '菜市场', '4008060222', 2, 1, 2);
INSERT INTO `tb_food` VALUES (23, '测试531', NULL, '2020-01-01', '2021-01-01', '531测试', '531测试', '123456', NULL, 1, NULL);
INSERT INTO `tb_food` VALUES (25, '牛腩', '肉类', '2020-01-01', '2021-01-01', '湖南', '人', '13153', NULL, 2, 1);

-- ----------------------------
-- Table structure for tb_manifest
-- ----------------------------
DROP TABLE IF EXISTS `tb_manifest`;
CREATE TABLE `tb_manifest`  (
  `manifest_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '货单码',
  `food_id` int(0) NULL DEFAULT NULL COMMENT '食品id',
  `food_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '食品名称',
  `consignor_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发货人姓名',
  `consignee_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `starting_site` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发货站点',
  `received_site` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货站点',
  `travel_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运输状态',
  `storage_id` int(0) NULL DEFAULT NULL COMMENT '站点id',
  `arrived_point` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '到达站点',
  `consignor_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人id',
  `consignee_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发货人id',
  `risk_editor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否进行了风险检测',
  `is_received` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否已经收货',
  PRIMARY KEY (`manifest_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_manifest
-- ----------------------------
INSERT INTO `tb_manifest` VALUES ('30c90841a2', 2, '手撕面包', '张嘉玲22', '张迪凯', '青岛', '厦门', '未发货', 9, '厦门', '2518', '2518', NULL, '已收货');
INSERT INTO `tb_manifest` VALUES ('3f90d57e0e', 2, '泰国炒米', '测试', '测试', '测试', '测试', '未发货', NULL, '岳阳', NULL, NULL, NULL, NULL);
INSERT INTO `tb_manifest` VALUES ('520', 2, '泰国炒米', '张迪凯', '张振明', '岳阳', '成都', '运输中', 1, '岳阳', '1', '1', NULL, NULL);
INSERT INTO `tb_manifest` VALUES ('8d637e8b23', 5, '芒果干', '张嘉玲', '测试', '青岛', '武汉', NULL, 10, '武汉', '2518', '2518', NULL, '已收货');
INSERT INTO `tb_manifest` VALUES ('9c515e992b', 25, '牛腩', '张嘉玲22', '25615', '青岛', '青岛', NULL, 8, '青岛', '2518 ', '2518', NULL, '已收货');
INSERT INTO `tb_manifest` VALUES ('a831291248', 2, '泰国炒米', '测试5.30', '测试5.30', '测试5.30', '测试5.30', '已送达', 2, '长沙', NULL, NULL, NULL, NULL);
INSERT INTO `tb_manifest` VALUES ('a9ed179271', 4, '炭烧腰果', '张振明', '张迪凯', '北京', '天津', NULL, 7, '天津', '508cd6', '2518', NULL, '已收货');
INSERT INTO `tb_manifest` VALUES ('b4fac61a21', 4, '炭烧腰果', '测试61', '测试61', '测试61', '测试61', '未发货', NULL, '天津', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_right
-- ----------------------------
DROP TABLE IF EXISTS `tb_right`;
CREATE TABLE `tb_right`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `right_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `right_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限路径',
  `right_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_right
-- ----------------------------
INSERT INTO `tb_right` VALUES (1, 'adminList', '/adminUsers', '获取管理员列表');
INSERT INTO `tb_right` VALUES (2, 'primaryList', '/primary', '获取普通用户列表');
INSERT INTO `tb_right` VALUES (3, 'enterpriseList', '/enterpriseUsers', '获取企业用户列表');
INSERT INTO `tb_right` VALUES (4, 'roleList', '/role', '获取角色列表');
INSERT INTO `tb_right` VALUES (5, 'rightList', '/rights/list', '获取权限列表');
INSERT INTO `tb_right` VALUES (6, 'removeAdmin', '/users/{id}', '删除管理员用户');
INSERT INTO `tb_right` VALUES (7, 'removeEnterprise', '/enterpriseUsers/{id}', '删除企业用户');
INSERT INTO `tb_right` VALUES (8, 'addEnterprise', '/addEnterpriseUsers', '添加企业用户');
INSERT INTO `tb_right` VALUES (9, 'showEnterpriseUsers', '/showEditEnterpriseUsers/{id}', '展示企业用户编辑信息');
INSERT INTO `tb_right` VALUES (10, 'editEnterpriseUsers', '/editEnterpriseUsers/{id}', '修改企业用户信息');
INSERT INTO `tb_right` VALUES (11, 'removePrimaryUsers', '/PrimaryUsers/{id}', '删除普通用户');
INSERT INTO `tb_right` VALUES (12, 'addUser', '/addUsers', '添加普通用户');
INSERT INTO `tb_right` VALUES (13, 'showEditPrimaryUsers', '/showEditPrimaryUsers/{id}', '展示普通用户编辑信息');
INSERT INTO `tb_right` VALUES (14, 'editPrimaryUsers', '/editPrimaryUsers/{id}', '修改普通用户');
INSERT INTO `tb_right` VALUES (15, 'addRoles', '/roles', '添加角色');
INSERT INTO `tb_right` VALUES (16, 'showRoles', '/roles/{id}', '展示角色编辑信息');
INSERT INTO `tb_right` VALUES (17, 'modifyRoles', '/roles/{id}', '修改角色');
INSERT INTO `tb_right` VALUES (18, 'deleteRoles', '/roles/{id}', '删除角色');
INSERT INTO `tb_right` VALUES (19, 'showSetRightDialog', '/rights/tree', '展示树形权限');
INSERT INTO `tb_right` VALUES (20, 'getFoodCategory', '/getFoodCategory', '获取食品分类列表');
INSERT INTO `tb_right` VALUES (21, 'addFood', '/addFood', '添加食品');
INSERT INTO `tb_right` VALUES (22, 'deleteFood', '/deleteFood', '删除食品');
INSERT INTO `tb_right` VALUES (23, 'modifyFood', '/modifyFood', '修改食品信息');
INSERT INTO `tb_right` VALUES (24, 'getFood', '/getFood', '获取食品信息列表');
INSERT INTO `tb_right` VALUES (25, 'getFoodCategory', '/getFoodCategory', '食品分类列表');
INSERT INTO `tb_right` VALUES (26, 'getManifest', '/getManifest', '获取货单信息列表');
INSERT INTO `tb_right` VALUES (27, 'addManifest', '/addManifest', '添加货单信息');
INSERT INTO `tb_right` VALUES (28, 'addFoodCategory', '/addFoodCategory', '添加食品分类');
INSERT INTO `tb_right` VALUES (29, 'deleteFoodCategory', '/deleteFoodCategory', '删除食品分类信息');
INSERT INTO `tb_right` VALUES (30, 'modifyFoodCategory', '/modifyFoodCategory', '修改食品分类信息');
INSERT INTO `tb_right` VALUES (31, 'categories', '/categories/{id}', '编辑查看食品类别信息');
INSERT INTO `tb_right` VALUES (32, 'rightListRole', '/rights/list/{id}', '分配权限弹出框');
INSERT INTO `tb_right` VALUES (33, 'getFoodById', '/foods/{id}', '获取想编辑的食品信息');
INSERT INTO `tb_right` VALUES (34, 'getManifestById', '/manifests/{id}', '获取要编辑的货单信息');
INSERT INTO `tb_right` VALUES (35, 'modifyManifest', '/modifyManifest', '修改货单信息');
INSERT INTO `tb_right` VALUES (36, 'deleteManifest', ' /deleteManifest/{id}', '删除货单信息');
INSERT INTO `tb_right` VALUES (37, 'assignRight', '/roles/{id}/rights', '分配权限');
INSERT INTO `tb_right` VALUES (38, 'getAllStorageName', '/getAllStorageName', '获取所有储存站点名称');
INSERT INTO `tb_right` VALUES (39, 'userInfo', '/userInfo/{id}', '获取当前用户信息');
INSERT INTO `tb_right` VALUES (40, 'getRiskList', '/getRiskList', '获取食品检测列表');
INSERT INTO `tb_right` VALUES (41, 'getStorage', '/getStorage', '获取站点信息');
INSERT INTO `tb_right` VALUES (42, 'modifyStorage', '/modifyStorage', '修改站点信息');
INSERT INTO `tb_right` VALUES (43, 'deleteStorage', '/deleteStorage', '删除站点');
INSERT INTO `tb_right` VALUES (44, 'showStorage', '/showStorage', '显示要修改的站点的信息');
INSERT INTO `tb_right` VALUES (45, 'addRisk', '/addRisk', '添加食品检测信息');
INSERT INTO `tb_right` VALUES (46, 'deleteRisk', '/deleteRisk/{id}', '删除食品检测信息');
INSERT INTO `tb_right` VALUES (47, 'showRiskInfo', '/risks/{id}', '展示要编辑的食品检测信息');
INSERT INTO `tb_right` VALUES (48, 'modifyRisks', '/modifyRisks', '修改食品检测信息');
INSERT INTO `tb_right` VALUES (49, 'getSendManifest', '/getSendManifest', '获取发货列表信息');
INSERT INTO `tb_right` VALUES (50, 'getReceiveManifest', '/getReceiveManifest', '获取收货列表信息');
INSERT INTO `tb_right` VALUES (51, 'receive', '/receive', '更新收货状态');
INSERT INTO `tb_right` VALUES (52, 'addAdmin', '/addAdmin', '添加企业用户');
INSERT INTO `tb_right` VALUES (53, 'changeState', '/changeState', '修改用户是否被锁定');

-- ----------------------------
-- Table structure for tb_risk
-- ----------------------------
DROP TABLE IF EXISTS `tb_risk`;
CREATE TABLE `tb_risk`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '风险记录id',
  `food_id` int(0) NULL DEFAULT NULL COMMENT '食品id',
  `food_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '食品名称',
  `manifest_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '货单码',
  `pathway` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '途径地区',
  `risk_degree` int(0) NULL DEFAULT NULL COMMENT '风险等级',
  `risk_editor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否进行了风险检测',
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  INDEX `manifest_id`(`manifest_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_risk
-- ----------------------------
INSERT INTO `tb_risk` VALUES (7, 4, '炭烧腰果', NULL, NULL, 1, '已检测');
INSERT INTO `tb_risk` VALUES (9, 2, '泰国炒米', NULL, NULL, 2, '已检测');
INSERT INTO `tb_risk` VALUES (10, 2, '泰国炒米', NULL, NULL, 1, '已检测');
INSERT INTO `tb_risk` VALUES (11, 6, '碧根果', NULL, NULL, 2, '已检测');
INSERT INTO `tb_risk` VALUES (12, 16, '苹果', NULL, NULL, 1, '已检测');
INSERT INTO `tb_risk` VALUES (13, 25, '牛腩', NULL, NULL, 3, '已检测');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `right_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拥有的权限的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '超级管理员', '至高', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53');
INSERT INTO `tb_role` VALUES (2, '用户管理员', '管理用户', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,53');
INSERT INTO `tb_role` VALUES (3, '食品管理员', '管理食品', '20,21,22,23,24,25,28,29,30,31,33');
INSERT INTO `tb_role` VALUES (4, '货单管理员', '管理货单', '1,2,3,6,7,8,9,10,11,12,13,14,20,21,22,23,24,25,26,27,28,29,30,31,33,34,35,36');
INSERT INTO `tb_role` VALUES (5, '管理员', '不能分配权限的管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,26,27,28,29,30,31,32,33,34,35,36,38,39,40,41,42,43,44,45,46,47,48,49,50,51,53');
INSERT INTO `tb_role` VALUES (6, '普通用户', '普普通通的人罢了', '20,24,26,38,40,41');
INSERT INTO `tb_role` VALUES (7, '企业用户', '资本家', '24,25,26,27,39,40,41,49,50,51');
INSERT INTO `tb_role` VALUES (8, '风险监测管理员', '有风险！！！！', '1,2,3,6,7,8,9,10,11,12,13,14,20,21,22,23,24,25,26,27,28,29,30,31,33,34,35,36,38,40,41,42,43,44,45,46,47,48');
INSERT INTO `tb_role` VALUES (9, '工作人员', '工具人', NULL);

-- ----------------------------
-- Table structure for tb_storage
-- ----------------------------
DROP TABLE IF EXISTS `tb_storage`;
CREATE TABLE `tb_storage`  (
  `storage_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '储存站点id',
  `uid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业用户id',
  `storage_area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '储存站点的地区',
  `temper` double NULL DEFAULT NULL COMMENT '空气温度',
  `humidity` double NULL DEFAULT NULL COMMENT '空气湿度',
  `alarm_record` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报警记录',
  `power_supply` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市电监控',
  PRIMARY KEY (`storage_id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_storage
-- ----------------------------
INSERT INTO `tb_storage` VALUES (2, '1fdb0c', '长沙', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (3, '305296', '成都', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (4, '36ef0d', '上海', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (5, '508cd6', '北京', 36, 50, '无', '无测试');
INSERT INTO `tb_storage` VALUES (6, '5632 ', '重庆', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (7, '6933 ', '天津', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (8, '2518', '青岛', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (9, '76636d', '厦门', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (10, '8729', '武汉', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (11, '8893 ', '南京', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (13, '369365576', '眉山', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (18, '2255ed', '测试22', 36, 50, '无', '无');
INSERT INTO `tb_storage` VALUES (21, '5da045', '测', 36, 50, '无', '无');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_login_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_times` int(0) NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '1:true 0:false',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('078ca', '测试1', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '卡德加', '男', '13216516511', '131@qq.com', '2021-05-23', '2021-06-07', 1, '普通用户', 6, 1);
INSERT INTO `tb_user` VALUES ('15fe4', '测试531', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, NULL, NULL, '369365576@qq.com', '2021-05-31', '2021-06-06', 1, '普通用户', 6, 1);
INSERT INTO `tb_user` VALUES ('1a451', '张三丰', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张三丰', '男', '11222222222', '222@qq.com', '2021-04-26', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('39611 ', '吴凯', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '黄雅慧', '女', '14672849303', '369365576@qq.com', '2021-04-01', '2021-04-04', 4, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('48856', 'yizhongge', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '谢一忠', '男', '13958475869', '369365576@qq.com', '2021-04-07', '2021-04-12', 10, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('60913 ', 'meiling', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '王美玲', '女', '13859375831', '369365576@qq.com', '2021-04-10', '2021-04-11', 2, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('61bc7', '再次测试', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, NULL, NULL, '369365576@qq.com', '2021-05-31', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('62448 ', 'tingting', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '林依婷', '女', '19284736584', '369365576@qq.com', '2021-04-06', '2021-04-10', 5, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('69556', '张振明', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张振明', '男', '11111111111', '222@qq.com', '2021-04-25', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('6f1a6', '测试531', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, NULL, NULL, '369365576@qq.com', '2021-05-31', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('70903', '测试登录跳转', '$2a$10$a05/cH0EWMcuSl0o2qtk9.T75XmRCzLNMJPCpuFPQaKOEtSxlOD2e', '张迪凯', NULL, NULL, '369365576@qq.com', '2021-06-09', '2021-06-09', 1, '普通用户', 6, 1);
INSERT INTO `tb_user` VALUES ('72050', '测试密码加密', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, NULL, NULL, '369365576@qq.com', '2021-06-07', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('72905', '测试530', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', NULL, NULL, NULL, '369365576@qq.com', '2021-05-30', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('78013', 'wosjo', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '我是', '男', '11100000000', '000@qq.com', '2021-04-26', NULL, 0, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('80383 ', 'shengwang', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '王圣如', '男', '18492857685', '369365576@qq.com', '2021-04-04', '2021-04-12', 15, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('81963 ', 'rongge', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '陈伟荣', '男', '18594738675', '369365576@qq.com', '2021-04-08', '2021-04-12', 6, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('8598a', '普通用户再测试', '$2a$10$RjHdiwxp7AgvoCaetzFFluWWG/b3CqbYEaLGE91VkX4Ie5yvZGaLG', '6.11', NULL, NULL, '369365576@qq.com', '2021-06-11', '2021-06-11', 1, '普通用户', 6, 1);
INSERT INTO `tb_user` VALUES ('87375 ', 'zihao', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '郭子豪', '男', '18435758454', '369365576@qq.com', '2021-04-03', '2021-04-06', 4, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('95001', 'jiege', '$2a$10$MDNQzgggcbg9RY/RhdlXz.LfFkcsXPYoAv3R/DCFKb29eebonsfmS', '张冠杰', '男', '13427584359', '369365576@qq.com', '2021-04-01', '2021-04-06', 8, '普通用户', 6, 0);
INSERT INTO `tb_user` VALUES ('e59b7', '普通用户注册加密测试', '$2a$10$.zB5HYAxnxVN99owxHwOIegrh/CvRbMN3etMVW31vJEk9wAvAk/U6', '张迪凯', '女', '13696086376', '369365576@qq.com', '2021-06-09', '2021-06-11', 2, '普通用户', 6, 1);

SET FOREIGN_KEY_CHECKS = 1;
