/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : husky

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-09-01 22:53:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `account_no` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '账户号',
  `user_id` bigint(64) NOT NULL COMMENT '用户号',
  `role_id` int(5) NOT NULL DEFAULT '0' COMMENT '账户角色id',
  `login_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户密码',
  `level` int(3) NOT NULL DEFAULT '0' COMMENT '账户等级',
  `xp` bigint(64) NOT NULL DEFAULT '0' COMMENT '经验值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`account_no`),
  KEY `FK_ROLE_ID` (`role_id`),
  KEY `FK_USER_ID` (`user_id`),
  CONSTRAINT `FK_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FK_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', '1', 'zxy19901210', '51b0ac33bb90c8096c67363e7b3d6fd1c486a14de25de21281b2ffc370cb209ce8077b8c9c9b21dd6702b342a6f548aeee620f86c805ae0aec265df1bdf46edd', '0', '999999999999', '2016-08-24 11:35:04', '2016-09-01 10:44:05');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `station_id` int(5) NOT NULL COMMENT '岗位id以逗号分开',
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建日期',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_id`),
  KEY `PK_STATION_ID` (`station_id`),
  CONSTRAINT `PK_STATION_ID` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1', '2016-08-24 00:53:52', '2016-08-24 00:53:56');

-- ----------------------------
-- Table structure for `station`
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `station_id` int(5) NOT NULL COMMENT '岗位id',
  `authority` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '岗位权限，以逗号分开',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('1', '1,2', '2016-08-24 00:51:26', '2016-08-24 00:51:23');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '绑定手机号码',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `signed` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '签名自我介绍',
  `register_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '管理员', '999', null, 'xkr5800566357@163.com', 'husky管理', '2016-08-24 11:30:49', '2016-08-24 11:30:52');
