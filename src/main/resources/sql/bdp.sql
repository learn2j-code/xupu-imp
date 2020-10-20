CREATE DATABASE  IF NOT EXISTS `bdp`  DEFAULT CHARACTER SET utf8 ;

USE `bdp`;

/*家族信息表*/
DROP TABLE IF EXISTS `family_info`;
CREATE TABLE `family_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '家族id',
  `family_name` varchar(100) DEFAULT NULL COMMENT '家族名称',
  `family_title` varchar(100) DEFAULT NULL COMMENT '家族堂号',
  `surname_id` int(11) DEFAULT NULL COMMENT '姓氏ID',
  `surname` varchar(50) DEFAULT NULL COMMENT '姓氏',
  `ancestor_name` varchar(50) DEFAULT NULL COMMENT '始迁祖',
  `settlement_roughly` varchar(255) DEFAULT NULL COMMENT '定居地-省市县',
  `settlement_exact` varchar(1024) DEFAULT NULL COMMENT '定居地-详细',
  `settlement_time` timestamp NULL COMMENT '定居时间',
  `emigration_roughly` varchar(255) DEFAULT NULL COMMENT '迁出地-省市县',
  `emigration_exact` varchar(1024) DEFAULT NULL COMMENT '迁出地-详细',
  `emigration_time` timestamp NULL COMMENT '迁出时间',
  `totem_address` varchar(1024) DEFAULT NULL COMMENT '图腾图片地址',
  `totem_story` varchar(2048) DEFAULT NULL COMMENT '图腾解说',
  `seniority_content` varchar(1024) DEFAULT NULL COMMENT '派语',
  `family_intro` text COMMENT '家族简介',
  `ancestor_story` text COMMENT '姓氏来源-得姓始祖',
  `other_info` text COMMENT '其他信息',
  `contactor` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `location_code` varchar(255) DEFAULT NULL COMMENT '位置编码',
  `frozen_flag` int(4) DEFAULT '0' COMMENT '冻结标志，默认为0：不冻结，1：冻结',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '家族信息表'  ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*姓氏信息表*/
DROP TABLE IF EXISTS `surname_info`;
CREATE TABLE `surname_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '姓氏ID',
  `surname` varchar(50) DEFAULT NULL COMMENT '姓氏名称',
  `surname_pinyin` varchar(100) DEFAULT NULL COMMENT '姓氏拼音',
  `word_num` int(4) DEFAULT NULL COMMENT '姓氏字数',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '姓氏信息表' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*服务中心*/
DROP TABLE IF EXISTS `service_center`;
CREATE TABLE `service_center` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务ID',
  `service_no` varchar(45) DEFAULT NULL COMMENT '代号',
  `service_name` varchar(100) DEFAULT NULL COMMENT '服务名称',
  `service_intro` varchar(1024) DEFAULT NULL COMMENT '服务简介',
  `price_unit` varchar(45) DEFAULT NULL COMMENT '计价单位，1：元',
  `time_unit` varchar(45) DEFAULT NULL COMMENT '计时单位，1：时；2：日；3：周；4：月；5季；6年',
  `measure_unit` varchar(45) DEFAULT NULL COMMENT '计量单位，1：个；2：g；3：kg；',
  `discount` varchar(10) DEFAULT NULL COMMENT '折扣',
  `price` varchar(100) DEFAULT NULL COMMENT '单价',
  `conf_type` varchar(100) DEFAULT NULL COMMENT '服务配置类型',
  `conf_framework` varchar(1024) DEFAULT NULL COMMENT '服务配置内容框架地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '服务中心' ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*家族服务*/
DROP TABLE IF EXISTS `family_service`;
CREATE TABLE `family_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '家族服务ID',
  `service_id` int(11) DEFAULT NULL COMMENT '家族服务id',
  `conf_content` varchar(1024) DEFAULT NULL COMMENT '服务配置具体内容',
  `bought_time` timestamp NULL COMMENT '购买日期',
  `end_time` timestamp NULL COMMENT '到期日期',
  `end_type` int(4) DEFAULT NULL COMMENT '期限类型：默认为1：按月',
  `bought_num` int(4) DEFAULT NULL COMMENT '购买数量',
  `discount` varchar(10) DEFAULT NULL COMMENT '折扣',
  `service_fee` varchar(100) DEFAULT NULL COMMENT '该项服务总费用',
  `family_id` int(11) DEFAULT NULL COMMENT '家族id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '家族服务' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*族谱管理*/
/*谱书管理信息*/
DROP TABLE IF EXISTS `clan_book`;
CREATE TABLE `clan_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '谱书ID',
  `family_id` int(11) DEFAULT NULL COMMENT '家族id',
  `book_name` varchar(100) DEFAULT NULL COMMENT '谱书名',
  `volume_num` int(4) DEFAULT NULL COMMENT '卷数',
  `cover_image` int(4) DEFAULT NULL COMMENT '封面地址',
  `status` int(4) DEFAULT NULL COMMENT '谱书状态,1：原图，2：审核被退回，3：审核中，4：审核通过-电子化进行中，5：已电子化',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '谱书管理信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*卷管理信息*/
DROP TABLE IF EXISTS `clan_volume`;
CREATE TABLE `clan_volume` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '谱书ID',
  `book_id` int(11) DEFAULT NULL COMMENT '谱书id',
  `volume_name` varchar(100) DEFAULT NULL COMMENT '卷名',
  `volume_no` int(4) DEFAULT NULL COMMENT '卷号',
  `status` int(4) DEFAULT NULL COMMENT '卷状态,1：原图，2：审核被退回，3：审核中，4：审核通过-电子化进行中，5：已电子化',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '卷管理信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*图管理信息*/
DROP TABLE IF EXISTS `clan_picture`;
CREATE TABLE `clan_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '谱书ID',
  `volume_id` int(11) DEFAULT NULL COMMENT '卷id',
  `picture_name` varchar(100) DEFAULT NULL COMMENT '图名',
  `picture_address` varchar(1024) DEFAULT NULL COMMENT '图的相对地址',
  `picture_no` int(4) DEFAULT NULL COMMENT '图号',
  `status` int(4) DEFAULT NULL COMMENT '图状态，1：原图，2：审核被退回，3：审核中，4：审核通过-电子化进行中，5：已电子化',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) COMMENT = '图管理信息' ENGINE=InnoDB DEFAULT CHARSET=utf8;


