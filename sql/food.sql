/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : food

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 18/04/2021 17:06:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` date NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1', 'fawai', '男', '张三', '111111', '2021-04-01', '13427584359');
INSERT INTO `tb_admin` VALUES ('10', 'aile', '女', '王爱乐', '123456', '2021-04-12', '17584968789');
INSERT INTO `tb_admin` VALUES ('2', 'kuangtu', '男', '李四', '222222', '2021-04-01', '14672849303');
INSERT INTO `tb_admin` VALUES ('3', 'xiaoyu', '女', '李玉婷', '333333', '2021-04-03', '18435758454');
INSERT INTO `tb_admin` VALUES ('4', 'dalong', '男', '王恩龙', '444444', '2021-04-04', '18492857685');
INSERT INTO `tb_admin` VALUES ('5', 'hanhan', '女', '邓诗涵', '555555', '2021-04-06', '19284736584');
INSERT INTO `tb_admin` VALUES ('6', 'junming', '男', '王俊明', '666666', '2021-04-06', '12647535213');
INSERT INTO `tb_admin` VALUES ('7', 'xiaxia', '女', '夏雅慧', '777777', '2021-04-08', '18594738675');
INSERT INTO `tb_admin` VALUES ('8', 'fengli', '男', '李兴峰', '888888', '2021-04-10', '13859375831');
INSERT INTO `tb_admin` VALUES ('9', 'yile', '女', '王亿乐', '999999', '2021-04-12', '13958475869');

-- ----------------------------
-- Table structure for tb_buyer
-- ----------------------------
DROP TABLE IF EXISTS `tb_buyer`;
CREATE TABLE `tb_buyer`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_buyer
-- ----------------------------
INSERT INTO `tb_buyer` VALUES ('张杰豪', '男', '13427584359');
INSERT INTO `tb_buyer` VALUES ('黄诗涵', '女', '14672849303');
INSERT INTO `tb_buyer` VALUES ('郭子铭', '男', '18435758454');
INSERT INTO `tb_buyer` VALUES ('凌一峰', '男', '18492857685');
INSERT INTO `tb_buyer` VALUES ('李婷婷', '女', '19284736584');
INSERT INTO `tb_buyer` VALUES ('王伟荣', '男', '12647535213');
INSERT INTO `tb_buyer` VALUES ('陈曦', '女', '18594738675');
INSERT INTO `tb_buyer` VALUES ('苏安', '女', '13859375831');
INSERT INTO `tb_buyer` VALUES ('李佳乐', '女', '13958475869');
INSERT INTO `tb_buyer` VALUES ('李俊龙', '男', '17584968789');

-- ----------------------------
-- Table structure for tb_enterprise_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_enterprise_user`;
CREATE TABLE `tb_enterprise_user`  (
  `eid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` date NULL DEFAULT NULL,
  `last_login_time` date NULL DEFAULT NULL,
  `login_times` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_enterprise_user
-- ----------------------------
INSERT INTO `tb_enterprise_user` VALUES ('1', 'daniao', '111111', '13427584359', '2021-04-01', '2021-04-06', '8', '张鸿信', '男', '腾达');
INSERT INTO `tb_enterprise_user` VALUES ('10', 'lisheng', '123456', '17584968789', '2021-04-08', '2021-04-12', '5', '李伦生', '男', '和贝');
INSERT INTO `tb_enterprise_user` VALUES ('2', 'xioaai', '222222', '14672849303', '2021-04-02', '2021-04-04', '4', '陈怡爱', '女', '汇易');
INSERT INTO `tb_enterprise_user` VALUES ('3', 'liangchen', '333333', '18435758454', '2021-04-03', '2021-04-06', '4', '徐辰航', '男', '龙广');
INSERT INTO `tb_enterprise_user` VALUES ('4', 'junan', '444444', '18492857685', '2021-04-05', '2021-04-12', '15', '苏俊安', '男', '振韦');
INSERT INTO `tb_enterprise_user` VALUES ('5', 'xiaowen', '555555', '19284736584', '2021-04-06', '2021-04-10', '5', '黄静雯', '女', '庆万');
INSERT INTO `tb_enterprise_user` VALUES ('6', 'yuege', '666666', '12647535213', '2021-04-02', '2021-04-08', '8', '许岳平', '男', '庆万');
INSERT INTO `tb_enterprise_user` VALUES ('7', 'jialing', '777777', '18594738675', '2021-04-09', '2021-04-12', '6', '张嘉玲', '女', '安丰');
INSERT INTO `tb_enterprise_user` VALUES ('8', 'yihui', '888888', '13859375831', '2021-04-07', '2021-04-11', '5', '蔡宜慧', '女', '华环');
INSERT INTO `tb_enterprise_user` VALUES ('9', 'jiajia', '999999', '13958475869', '2021-04-10', '2021-04-12', '2', '何佳佳', '女', '生源');

-- ----------------------------
-- Table structure for tb_food
-- ----------------------------
DROP TABLE IF EXISTS `tb_food`;
CREATE TABLE `tb_food`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `food_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `production_date` date NULL DEFAULT NULL,
  `expiry_date` date NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `producer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `storage_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `risk_degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_food
-- ----------------------------
INSERT INTO `tb_food` VALUES ('1', '旺旺雪饼', '2020-10-24', '2021-03-24', '湖南长沙', '湖南飘香食品有限公司', '073189916124', '12', '中等');
INSERT INTO `tb_food` VALUES ('10', '沙琪玛', '2020-12-01', '2021-06-01', '中国大陆', '莒县众诺食品有限公司', '4008060222', '12', '中等');
INSERT INTO `tb_food` VALUES ('2', '泰国炒米', '2020-08-19', '2021-05-19', '湖南长沙', '湖南飘香食品有限公司', '073189916129', '12', '中等');
INSERT INTO `tb_food` VALUES ('3', '乐事薯片', '2020-09-22', '2021-06-22', '上海北京武汉', '唐山市汇旺行工贸有限公司', '15070786227', '12', '中等');
INSERT INTO `tb_food` VALUES ('4', '炭烧腰果', '2020-10-04', '2021-04-09', '中国大陆', '三只松鼠股份有限公司', '4008004900', '11', '中等');
INSERT INTO `tb_food` VALUES ('5', '芒果干', '2020-09-16', '2021-04-12', '中国大陆', '杭州赫姆斯食品有限公司', '4008810118', '10', '中等');
INSERT INTO `tb_food` VALUES ('6', '碧根果', '2020-09-20', '2021-09-24', '中国大陆', '杭州富阳松之皇食品厂', '13857151360', '10', '中等');
INSERT INTO `tb_food` VALUES ('7', '手撕面包', '2021-02-13', '2021-04-06', '中国大陆', '福建省新麦食品有限公司', '4008004900', '12', '中等');
INSERT INTO `tb_food` VALUES ('8', '火鸡面', '2020-08-01', '2020-08-31', '中国大陆', '广西南宁市韩太食品有限责任公司', '15154510492', '12', '中等');
INSERT INTO `tb_food` VALUES ('9', '夹心饼干', '2021-01-01', '2021-06-01', '中国大陆', '龙海市鸿祺食品有限公司', '05925800750', '12', '中等');

-- ----------------------------
-- Table structure for tb_manifest
-- ----------------------------
DROP TABLE IF EXISTS `tb_manifest`;
CREATE TABLE `tb_manifest`  (
  `consignor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `starting_site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `travel_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consignee_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recieving_site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrval_point` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manifest_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `consignor_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consignee_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`manifest_id`) USING BTREE,
  INDEX `food_id`(`food_id`) USING BTREE,
  CONSTRAINT `food_id` FOREIGN KEY (`food_id`) REFERENCES `tb_food` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_manifest
-- ----------------------------
INSERT INTO `tb_manifest` VALUES ('刘来龙', '广东广州', '已签收', '张海峰', '广西南宁', '广西南宁', '113330806140', '刘来龙', '张海峰', NULL);
INSERT INTO `tb_manifest` VALUES ('张玉林', '新疆维吾尔', '已签收', '朱发军', '甘肃天元', '甘肃天元', '214083015015', '张玉林', '朱发军', NULL);
INSERT INTO `tb_manifest` VALUES ('李科峰', '河南新乡', '已签收', '李辉', '福建福州', '福建福州', '268424460636', '李科峰', '李辉', NULL);
INSERT INTO `tb_manifest` VALUES ('黄雅慧', '深圳南山', '已签收', '陶作茹', '浙江温州', '浙江温州', '400657788897', '买特网', '陶作茹', NULL);
INSERT INTO `tb_manifest` VALUES ('魏居达', '江苏苏州', '已签收', '刘星星', '湖南岳阳', '湖南岳阳', '4312775124608', '豪靓仔', '刘星星', NULL);
INSERT INTO `tb_manifest` VALUES ('曾璐', '四川成都', '已签收', '陈北辰', '沙市韵达', '沙市韵达', '4606657438288', '曾璐', '陈哥', NULL);
INSERT INTO `tb_manifest` VALUES ('陈晓菲', '上海长宁', '已签收', '樊海亮', '山东河泽', '山东河泽', '568579568671', '陈晓菲', '樊海亮', NULL);
INSERT INTO `tb_manifest` VALUES ('齐海', '浙江金华', '已签收', '陈继然', '广西东春', '广西东春', '718932020961', '齐海', '陈继然', NULL);
INSERT INTO `tb_manifest` VALUES ('黄梅林', '深圳兴隆', '已签收', '张希德', '山东济南', '山东济南', '920330001689', '黄梅林', '张希德', NULL);
INSERT INTO `tb_manifest` VALUES ('邓文强', '浙江金华', '已签收', '苏浩', '沙市圆通', '沙市圆通', 'YT5212324322164', '豪靓仔', '浩', NULL);

-- ----------------------------
-- Table structure for tb_seller
-- ----------------------------
DROP TABLE IF EXISTS `tb_seller`;
CREATE TABLE `tb_seller`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_seller
-- ----------------------------
INSERT INTO `tb_seller` VALUES ('周卓浩', '男', '13427584359');
INSERT INTO `tb_seller` VALUES ('孙莱笙', '女', '14672849303');
INSERT INTO `tb_seller` VALUES ('易江维', '男', '18435758454');
INSERT INTO `tb_seller` VALUES ('宁雨御', '女', '18492857685');
INSERT INTO `tb_seller` VALUES ('周逸依', '女', '19284736584');
INSERT INTO `tb_seller` VALUES ('卢钦均', '男', '12647535213');
INSERT INTO `tb_seller` VALUES ('梁震', '男', '18594738675');
INSERT INTO `tb_seller` VALUES ('陈琳', '女', '13859375831');
INSERT INTO `tb_seller` VALUES ('周意竹', '男', '13958475869');
INSERT INTO `tb_seller` VALUES ('刘玉', '女', '17584968789');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `register_time` date NULL DEFAULT NULL,
  `last_login_time` date NULL DEFAULT NULL,
  `login_times` int(50) NULL DEFAULT NULL,
  `true_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'jiege', '学生', '111111', '13427584359', '2021-04-01', '2021-04-06', 8, '张冠杰', '男');
INSERT INTO `tb_user` VALUES ('10', 'shupei', '学生', '123456', '17584968789', '2021-04-12', '2021-04-12', 2, '李淑佩', '女');
INSERT INTO `tb_user` VALUES ('2', 'yahui', '学生', '222222', '14672849303', '2021-04-01', '2021-04-04', 4, '黄雅慧', '女');
INSERT INTO `tb_user` VALUES ('3', 'zihao', '学生', '333333', '18435758454', '2021-04-03', '2021-04-06', 4, '郭子豪', '男');
INSERT INTO `tb_user` VALUES ('4', 'shengwang', '学生', '444444', '18492857685', '2021-04-04', '2021-04-12', 15, '王圣如', '男');
INSERT INTO `tb_user` VALUES ('5', 'tingting', '学生', '555555', '19284736584', '2021-04-06', '2021-04-10', 5, '林依婷', '女');
INSERT INTO `tb_user` VALUES ('6', 'jiajia', '学生', '666666', '12647535213', '2021-04-03', '2021-04-08', 7, '廖佳容', '女');
INSERT INTO `tb_user` VALUES ('7', 'rongge', '学生', '777777', '18594738675', '2021-04-08', '2021-04-12', 6, '陈伟荣', '男');
INSERT INTO `tb_user` VALUES ('8', 'meiling', '学生', '888888', '13859375831', '2021-04-10', '2021-04-11', 2, '王美玲', '女');
INSERT INTO `tb_user` VALUES ('9', 'yizhongge', '学生', '999999', '13958475869', '2021-04-07', '2021-04-12', 10, '谢一忠', '男');

-- ----------------------------
-- Table structure for tb_worker
-- ----------------------------
DROP TABLE IF EXISTS `tb_worker`;
CREATE TABLE `tb_worker`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_worker
-- ----------------------------
INSERT INTO `tb_worker` VALUES ('吴相宜', '女', '13427584359');
INSERT INTO `tb_worker` VALUES ('颜舒爱', '女', '14672849303');
INSERT INTO `tb_worker` VALUES ('贺世方', '男', '18435758454');
INSERT INTO `tb_worker` VALUES ('梁乐', '女', '18492857685');
INSERT INTO `tb_worker` VALUES ('周阳书', '男', '19284736584');
INSERT INTO `tb_worker` VALUES ('周述', '男', '12647535213');
INSERT INTO `tb_worker` VALUES ('陈欣', '女', '18594738675');
INSERT INTO `tb_worker` VALUES ('王军', '男', '13859375831');
INSERT INTO `tb_worker` VALUES ('苏北', '男', '13958475869');
INSERT INTO `tb_worker` VALUES ('黎薇', '女', '17584968789');

SET FOREIGN_KEY_CHECKS = 1;
