/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : zwoshop

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-06-26 09:19:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_assets
-- ----------------------------
DROP TABLE IF EXISTS `cms_assets`;
CREATE TABLE `cms_assets` (
  `ID` varchar(32) NOT NULL COMMENT 'id标志符',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `IS_DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(1200) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `URL` varchar(1200) DEFAULT NULL COMMENT '地址',
  `IP` varchar(32) DEFAULT NULL COMMENT 'IP',
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_assets
-- ----------------------------

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `ID` varchar(32) NOT NULL COMMENT 'id标志符',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `IS_TOOIC` tinyint(1) DEFAULT NULL COMMENT '是否为专题',
  `IS_DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(1200) DEFAULT NULL COMMENT '路径',
  `JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT 'JSP模板路径',
  `MOBILE_JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '手机端JSP模板',
  `FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT 'FREEMARKER静态模板',
  `MOBILE_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '手机端FREEMARKER静态模板',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `DOC_JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档JSP模板路径',
  `DOC_MOBILE__JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档手机端JSP模板路基',
  `DOC_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档FREEMARKER静态模板',
  `DOC_MOBILE_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档手机端FREEMARKER静态模板',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `CHANNEL_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '频道模板路径',
  `MCHANNEL_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '移动频道模板路径',
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`),
  KEY `FK_CHANNEL_CHANNEL_idx` (`PARENT_ID`),
  CONSTRAINT `FK_CHANNEL_CHANNEL` FOREIGN KEY (`PARENT_ID`) REFERENCES `cms_channel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_channel
-- ----------------------------

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `ID` varchar(32) NOT NULL COMMENT 'id标志符',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `IS_TOOIC` tinyint(1) DEFAULT NULL COMMENT '是否为专题',
  `IS_DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(1200) DEFAULT NULL COMMENT '路径',
  `MOBILE_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '手机端FREEMARKER静态模板',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `CMS_DOCUMENT_ID` varchar(32) DEFAULT NULL,
  `CONTENT` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`),
  KEY `FK_COMMENT_DOCUMENT_idx` (`CMS_DOCUMENT_ID`),
  KEY `FK_COMMENT_COMMENT_idx` (`PARENT_ID`),
  CONSTRAINT `FK_COMMENT_COMMENT` FOREIGN KEY (`PARENT_ID`) REFERENCES `cms_comment` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_COMMENT_DOCUMENT` FOREIGN KEY (`CMS_DOCUMENT_ID`) REFERENCES `cms_document` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_comment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_document
-- ----------------------------
DROP TABLE IF EXISTS `cms_document`;
CREATE TABLE `cms_document` (
  `ID` varchar(32) NOT NULL COMMENT 'id标志符',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `IS_TOOIC` tinyint(1) DEFAULT NULL COMMENT '是否为专题',
  `IS_DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(1200) DEFAULT NULL COMMENT '路径',
  `JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT 'JSP模板路径',
  `MOBILE_JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '手机端JSP模板',
  `FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT 'FREEMARKER静态模板',
  `MOBILE_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '手机端FREEMARKER静态模板',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `DOC_JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档JSP模板路径',
  `DOC_MOBILE__JSP_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档手机端JSP模板路基',
  `DOC_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档FREEMARKER静态模板',
  `DOC_MOBILE_FREEMARKER_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '文档手机端FREEMARKER静态模板',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `CHANNEL_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '频道模板路径',
  `MCHANNEL_TEMPLATE` varchar(1200) DEFAULT NULL COMMENT '移动频道模板路径',
  `TITLE_EN` varchar(120) DEFAULT NULL COMMENT '英文标题',
  `TITLE` varchar(120) DEFAULT NULL COMMENT '标题',
  `SUB_TITLE_EN` varchar(120) DEFAULT NULL COMMENT '英文副标题',
  `SUB_TITLE` varchar(120) DEFAULT NULL COMMENT '副标题',
  `EN_CONTENT` text COMMENT '英文内容',
  `M_CONTENT` text COMMENT '手机内容',
  `CONTENT` text COMMENT '内容',
  `TO_INDEX` tinyint(1) DEFAULT '0' COMMENT '是否推荐到首页',
  `TO_CHANNEL_INDEX` tinyint(1) DEFAULT '0' COMMENT '是否推荐到频道首页',
  `CMS_CHANNEL_ID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`),
  KEY `FK_DOCUMENT_CHANNEL_idx` (`CMS_CHANNEL_ID`),
  CONSTRAINT `FK_DOCUMENT_CHANNEL` FOREIGN KEY (`CMS_CHANNEL_ID`) REFERENCES `cms_channel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_document
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` varchar(32) NOT NULL,
  `USERNAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(120) DEFAULT NULL COMMENT '密码',
  `LOGIN_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登录日期',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录日期',
  `EMAIL` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `DISABLE` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SEX` tinyint(1) DEFAULT NULL COMMENT '性别',
  `ICON` varchar(120) DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `AGE` int(11) DEFAULT NULL COMMENT '身高',
  `WEIGHT` int(11) DEFAULT NULL COMMENT '体重',
  `QQ` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `WEIXIN` varchar(32) DEFAULT NULL COMMENT '微信',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '实名',
  `LOGIN_COUNT` int(11) DEFAULT NULL COMMENT '登录次数',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `MEMBER_GROUP_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_MEMBER_MEMBER_idx` (`PARENT_ID`),
  KEY `FK_MEMBER_MEMBER_GROUP_idx` (`MEMBER_GROUP_ID`),
  CONSTRAINT `FK_MEMBER_MEMBER` FOREIGN KEY (`PARENT_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MEMBER_MEMBER_GROUP` FOREIGN KEY (`MEMBER_GROUP_ID`) REFERENCES `member_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for member_address
-- ----------------------------
DROP TABLE IF EXISTS `member_address`;
CREATE TABLE `member_address` (
  `ID` varchar(32) NOT NULL,
  `PROVINCE` varchar(120) DEFAULT NULL COMMENT '省',
  `CITY` varchar(120) DEFAULT NULL COMMENT '市',
  `AREA` varchar(120) DEFAULT NULL COMMENT '区',
  `EMAIL` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `DISABLE` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `STREET` varchar(120) DEFAULT NULL COMMENT '街道',
  `MEMBER_ID` varchar(120) DEFAULT NULL,
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否设置为默认1为是,0为否',
  KEY `FK_MEMBER_ADDRESS_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MEMBER_ADDRESS_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_address
-- ----------------------------

-- ----------------------------
-- Table structure for member_group
-- ----------------------------
DROP TABLE IF EXISTS `member_group`;
CREATE TABLE `member_group` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT NULL COMMENT '是否禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CODE` varchar(120) DEFAULT NULL COMMENT '排序',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_MEMBER_GROUP_MEMBER_GROUP_idx` (`PARENT_ID`),
  CONSTRAINT `FK_MEMBER_GROUP_MEMBER_GROUP` FOREIGN KEY (`PARENT_ID`) REFERENCES `member_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员组';

-- ----------------------------
-- Records of member_group
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `STATUS` varchar(45) DEFAULT NULL COMMENT '订单状态',
  `SHOP_ID` varchar(120) DEFAULT NULL COMMENT '商铺ID',
  `PRODUCT_ID` varchar(120) DEFAULT NULL COMMENT '商品ID',
  `DEAL_PRICE` varchar(120) DEFAULT NULL COMMENT '实付',
  `MEMBER_ID` varchar(120) DEFAULT NULL COMMENT '会员ID',
  `BUY_NUM` int(11) DEFAULT NULL COMMENT '购买数量',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_ORDER_SHOP_idx` (`SHOP_ID`),
  KEY `FK_ORDER_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `FK_ORDER_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_ORDER_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for pr_category
-- ----------------------------
DROP TABLE IF EXISTS `pr_category`;
CREATE TABLE `pr_category` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '种类名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `fk_pr_category_category_idx` (`PARENT_ID`),
  CONSTRAINT `fk_pr_category_category` FOREIGN KEY (`PARENT_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pr_category
-- ----------------------------

-- ----------------------------
-- Table structure for pr_image
-- ----------------------------
DROP TABLE IF EXISTS `pr_image`;
CREATE TABLE `pr_image` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `URL` varchar(120) DEFAULT NULL COMMENT '图片连接地址',
  `PRODUCT_ID` varchar(120) DEFAULT NULL COMMENT '产品ID',
  `LOCATION` varchar(120) DEFAULT NULL COMMENT '图片物理地址',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `fk_image_product_idx` (`PRODUCT_ID`),
  CONSTRAINT `fk_image_product` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品图片';

-- ----------------------------
-- Records of pr_image
-- ----------------------------

-- ----------------------------
-- Table structure for pr_product
-- ----------------------------
DROP TABLE IF EXISTS `pr_product`;
CREATE TABLE `pr_product` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `CATEGORY_ID` varchar(120) DEFAULT NULL COMMENT '品牌外键ID',
  `CONTENT` text,
  `EN_CONTENT` text COMMENT '英文内容',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `fk_product_category_idx` (`CATEGORY_ID`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Records of pr_product
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `CATEGORY_ID` varchar(120) DEFAULT NULL COMMENT '品牌外键ID',
  `CONTENT` text,
  `EN_CONTENT` text COMMENT '英文内容',
  `USER_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_SHOP_SHOP_CATEGORY_idx` (`CATEGORY_ID`),
  CONSTRAINT `FK_SHOP_SHOP_CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `shop_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺';

-- ----------------------------
-- Records of shop
-- ----------------------------

-- ----------------------------
-- Table structure for shop_category
-- ----------------------------
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '种类名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `fk_shop_category_shop_category_idx` (`PARENT_ID`),
  CONSTRAINT `fk_shop_category_shop_category` FOREIGN KEY (`PARENT_ID`) REFERENCES `shop_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺分类';

-- ----------------------------
-- Records of shop_category
-- ----------------------------
