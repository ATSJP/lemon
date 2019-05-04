/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50725
Source Host           : 127.0.0.1:3306
Source Database       : lemon

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-04 18:55:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_file
-- ----------------------------
DROP TABLE IF EXISTS `biz_file`;
CREATE TABLE `biz_file` (
  `file_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `link_id` bigint(20) NOT NULL COMMENT '关联id',
  `link_type` smallint(1) NOT NULL COMMENT '关联类型 ：0 视频 1 图片 ',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `file_suffix` varchar(20) DEFAULT NULL COMMENT '文件后缀',
  `is_del` smallint(1) DEFAULT NULL COMMENT '逻辑删除标识（0：显示；1：隐藏）',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(20) DEFAULT NULL COMMENT '更新人 ',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`file_id`),
  KEY `index_link_id` (`link_id`),
  KEY `index_link_type` (`link_type`),
  KEY `index_is_del` (`is_del`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id，不可更改',
  `category_name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '父级id',
  `display` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否显示（0：显示；1：隐藏）',
  `create_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  KEY `index_parent_id` (`parent_id`),
  KEY `index_display` (`display`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for login_info
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(255) NOT NULL COMMENT '用户名',
  `login_pwd` varchar(255) NOT NULL COMMENT '密码',
  `login_name_update_times` smallint(1) DEFAULT '0' COMMENT '用户名更改次数',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `email_status` smallint(1) DEFAULT '0' COMMENT '邮箱验证状态：0 待验证 1 已验证 2 验证失败',
  `mobile` char(11) DEFAULT NULL COMMENT '手机',
  `mobile_status` smallint(1) DEFAULT '0' COMMENT '手机验证状态：0 待验证 1 已验证 2 验证失败',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `invite_code` varchar(255) DEFAULT NULL COMMENT '邀请码',
  `audit_status` smallint(1) DEFAULT '0' COMMENT '审核状态： 0 未审核 1 审核通过 2 审核未通过 3 已删除',
  `audit_remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `create_id` bigint(20) NOT NULL COMMENT '添加人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`login_id`) USING BTREE,
  KEY `index_mobile` (`mobile`),
  KEY `index_email` (`email`),
  KEY `index_login_name` (`login_name`),
  KEY `index_invite_code` (`invite_code`),
  KEY `index_audit_status` (`audit_status`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `message_context` varchar(255) DEFAULT NULL COMMENT '留言内容',
  `video_id` bigint(20) NOT NULL COMMENT '视频id',
  `repeat_id` bigint(20) DEFAULT NULL COMMENT '客服id',
  `login_id` bigint(20) NOT NULL COMMENT '用户id',
  `parent_id` bigint(20) DEFAULT '-1' COMMENT '父级id',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识（0：显示；1：隐藏）',
  `create_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`message_id`),
  KEY `index_video_id` (`video_id`),
  KEY `index_repeat_id` (`repeat_id`),
  KEY `index_login_id` (`login_id`),
  KEY `index_parent_id` (`parent_id`),
  KEY `index_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for play_detail
-- ----------------------------
DROP TABLE IF EXISTS `play_detail`;
CREATE TABLE `play_detail` (
  `detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '明细id',
  `video_id` bigint(20) NOT NULL COMMENT '视频id',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `sid` varchar(20) DEFAULT NULL COMMENT '平台',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`detail_id`),
  KEY `index_video_id` (`video_id`),
  KEY `index_sid` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for remark
-- ----------------------------
DROP TABLE IF EXISTS `remark`;
CREATE TABLE `remark` (
  `remark_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `remark_context` varchar(255) NOT NULL COMMENT '评论内容',
  `repeat_id` bigint(20) DEFAULT '-1' COMMENT '回复用户id',
  `repeat_name` varchar(255) DEFAULT NULL COMMENT '回复人用户名',
  `login_id` bigint(20) NOT NULL COMMENT '用户id',
  `login_name` varchar(255) NOT NULL COMMENT '用户名',
  `video_id` bigint(20) NOT NULL COMMENT '视频id，不可更改',
  `parent_id` bigint(20) DEFAULT '-1' COMMENT '父级id',
  `del_flag` smallint(1) DEFAULT '0' COMMENT '逻辑删除标识（0：显示；1：隐藏）',
  `create_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`remark_id`),
  KEY `index_repeat_id` (`repeat_id`),
  KEY `index_login_id` (`login_id`),
  KEY `index_video_id` (`video_id`),
  KEY `index_parent_id` (`parent_id`),
  KEY `index_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `login_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '姓名',
  `eng_name` varchar(255) DEFAULT NULL COMMENT '英文名',
  `gender` smallint(1) DEFAULT NULL COMMENT '性别: 0 女 1 男',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `grade` int(11) DEFAULT '0' COMMENT '等级',
  `user_type` char(2) DEFAULT '0' COMMENT '用户类型 0 普通会员 1 vip会员',
  `id_card` char(18) DEFAULT NULL COMMENT '身份证',
  `qq_account` varchar(255) DEFAULT NULL COMMENT 'qq',
  `we_chat_account` varchar(255) DEFAULT NULL COMMENT '微信',
  `driving_license` varchar(255) DEFAULT NULL,
  `arm_card` varchar(255) DEFAULT NULL,
  `pass_port` varchar(255) DEFAULT NULL,
  `simplified_chinese` varchar(255) DEFAULT NULL,
  `create_id` bigint(20) NOT NULL COMMENT '添加人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_id` bigint(20) NOT NULL COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`login_id`),
  KEY `index_we_chat_account` (`we_chat_account`),
  KEY `index_id_card` (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `video_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '视频Id',
  `video_name` varchar(50) NOT NULL COMMENT '视频名称-主标题',
  `video_context` text COMMENT '视频文字说明内容',
  `time` varchar(20) DEFAULT NULL COMMENT '视频播放时间',
  `category_id` bigint(20) NOT NULL COMMENT '分类id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '作者，不可更改',
  `audit_status` smallint(1) DEFAULT '0' COMMENT '审核状态： 0 未审核 1 审核通过 2 审核未通过 3 已删除',
  `create_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`video_id`),
  KEY `index_userId` (`user_id`) USING BTREE,
  KEY `index_categoryId` (`category_id`) USING BTREE,
  KEY `index_audit_status` (`audit_status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
