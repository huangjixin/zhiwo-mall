-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: zwoshop
-- ------------------------------------------------------
-- Server version	5.6.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cms_assets`
--

DROP TABLE IF EXISTS `cms_assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_assets`
--

LOCK TABLES `cms_assets` WRITE;
/*!40000 ALTER TABLE `cms_assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_channel`
--

DROP TABLE IF EXISTS `cms_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_channel`
--

LOCK TABLES `cms_channel` WRITE;
/*!40000 ALTER TABLE `cms_channel` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_comment`
--

DROP TABLE IF EXISTS `cms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_comment`
--

LOCK TABLES `cms_comment` WRITE;
/*!40000 ALTER TABLE `cms_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_document`
--

DROP TABLE IF EXISTS `cms_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`),
  KEY `FK_DOCUMENT_CHANNEL_idx` (`CMS_CHANNEL_ID`),
  CONSTRAINT `FK_DOCUMENT_CHANNEL` FOREIGN KEY (`CMS_CHANNEL_ID`) REFERENCES `cms_channel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_document`
--

LOCK TABLES `cms_document` WRITE;
/*!40000 ALTER TABLE `cms_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_purcse`
--

DROP TABLE IF EXISTS `group_purcse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_purcse` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `MEMEBER_ID` varchar(120) DEFAULT NULL COMMENT '会员ID',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `PRODUCT_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `FK_GROUP_PURCSE_MEMBER_idx` (`MEMEBER_ID`),
  KEY `FK_GROUP_PURCSE_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_GROUP_PURCSE_MEMBER` FOREIGN KEY (`MEMEBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GROUP_PURCSE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开团表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_purcse`
--

LOCK TABLES `group_purcse` WRITE;
/*!40000 ALTER TABLE `group_purcse` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_purcse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_purcse_member`
--

DROP TABLE IF EXISTS `group_purcse_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_purcse_member` (
  `ID` varchar(32) NOT NULL,
  `GROUP_PURCSE_ID` varchar(32) DEFAULT NULL COMMENT '团ID',
  `MEMBER_ID` varchar(32) DEFAULT NULL COMMENT '会员ID',
  PRIMARY KEY (`ID`),
  KEY `FK_GROUP_PURCSE_idx` (`GROUP_PURCSE_ID`),
  KEY `FK_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_GROUP_PURCSE` FOREIGN KEY (`GROUP_PURCSE_ID`) REFERENCES `group_purcse` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='团——会员中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_purcse_member`
--

LOCK TABLES `group_purcse_member` WRITE;
/*!40000 ALTER TABLE `group_purcse_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_purcse_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_category`
--

DROP TABLE IF EXISTS `guess_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_category` (
  `ID` varchar(32) NOT NULL,
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
  `USER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员竞猜分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_category`
--

LOCK TABLES `guess_category` WRITE;
/*!40000 ALTER TABLE `guess_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_question`
--

DROP TABLE IF EXISTS `guess_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_question` (
  `ID` varchar(32) NOT NULL,
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
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `USER_ID` varchar(32) DEFAULT NULL,
  `GUESS_CATEGORY_ID` varchar(32) DEFAULT NULL,
  `GUESS_ANSWER_ID` varchar(32) DEFAULT NULL COMMENT '问题答案ID引用',
  `QUESTION_END_TIME` timestamp NULL DEFAULT NULL COMMENT '竞猜活动截止时间',
  PRIMARY KEY (`ID`),
  KEY `FK_GQUESTION_GCATEGORY_idx` (`GUESS_CATEGORY_ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `ANSWER_IDINDEX` (`GUESS_ANSWER_ID`),
  CONSTRAINT `FK_GQUESTION_GCATEGORY` FOREIGN KEY (`GUESS_CATEGORY_ID`) REFERENCES `guess_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员竞猜问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question`
--

LOCK TABLES `guess_question` WRITE;
/*!40000 ALTER TABLE `guess_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_question_options`
--

DROP TABLE IF EXISTS `guess_question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_question_options` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `BET_RATE` double DEFAULT NULL COMMENT '赔率',
  `GUESS_QUESTION_ID` varchar(32) DEFAULT NULL COMMENT '竞猜问题ID',
  KEY `FK_GQOPTIONS_QUESTION_idx` (`GUESS_QUESTION_ID`),
  CONSTRAINT `FK_GQOPTIONS_QUESTION` FOREIGN KEY (`GUESS_QUESTION_ID`) REFERENCES `guess_question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜活动备选项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question_options`
--

LOCK TABLES `guess_question_options` WRITE;
/*!40000 ALTER TABLE `guess_question_options` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_question_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SEX` tinyint(1) DEFAULT NULL COMMENT '性别',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '头像',
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
  `NICKNAME` varchar(45) DEFAULT NULL COMMENT '昵称',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_MEMBER_MEMBER_idx` (`PARENT_ID`),
  KEY `FK_MEMBER_MEMBER_GROUP_idx` (`MEMBER_GROUP_ID`),
  CONSTRAINT `FK_MEMBER_MEMBER` FOREIGN KEY (`PARENT_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MEMBER_MEMBER_GROUP` FOREIGN KEY (`MEMBER_GROUP_ID`) REFERENCES `member_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_account`
--

DROP TABLE IF EXISTS `member_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_account` (
  `ID` varchar(32) NOT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用,0否,1是',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '实名',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  `LOCKED` tinyint(1) DEFAULT '0' COMMENT '是否锁定,0否,1是',
  `BALANCE` double DEFAULT NULL COMMENT '账号余额',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `FK_MACCOUNT_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MACCOUNT_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_account`
--

LOCK TABLES `member_account` WRITE;
/*!40000 ALTER TABLE `member_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_account_his`
--

DROP TABLE IF EXISTS `member_account_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_account_his` (
  `BALANCE` double DEFAULT NULL COMMENT '账号余额',
  `WITHDRAWAL` double DEFAULT NULL COMMENT '取款金额',
  `DEPOSIT` double DEFAULT NULL COMMENT '存款金额',
  `ID` varchar(32) NOT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用,0否,1是',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '实名',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `FK_MACCOUNT_HIS_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MACCOUNT_HIS_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存款流水表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_account_his`
--

LOCK TABLES `member_account_his` WRITE;
/*!40000 ALTER TABLE `member_account_his` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_account_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_address`
--

DROP TABLE IF EXISTS `member_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_address`
--

LOCK TABLES `member_address` WRITE;
/*!40000 ALTER TABLE `member_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_group`
--

DROP TABLE IF EXISTS `member_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_group`
--

LOCK TABLES `member_group` WRITE;
/*!40000 ALTER TABLE `member_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_guess_question`
--

DROP TABLE IF EXISTS `member_guess_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_guess_question` (
  `ID` varchar(32) NOT NULL,
  `GUESS_QUESTION_ID` varchar(32) DEFAULT NULL COMMENT '竞猜问题ID',
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  `GUESS_ACCOUNT` double DEFAULT NULL COMMENT '竞猜值',
  PRIMARY KEY (`ID`),
  KEY `FK_MGQ_MEMBER_idx` (`MEMBER_ID`),
  KEY `FK_MGQ_QUESTION_idx` (`GUESS_QUESTION_ID`),
  CONSTRAINT `FK_MGQ_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MGQ_QUESTION` FOREIGN KEY (`GUESS_QUESTION_ID`) REFERENCES `guess_question` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员竞猜中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_guess_question`
--

LOCK TABLES `member_guess_question` WRITE;
/*!40000 ALTER TABLE `member_guess_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_guess_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_level`
--

DROP TABLE IF EXISTS `member_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_level` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用,0为否，1为是',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_level`
--

LOCK TABLES `member_level` WRITE;
/*!40000 ALTER TABLE `member_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_play_account`
--

DROP TABLE IF EXISTS `member_play_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_play_account` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `ZHIHUIDOU_COUNT` int(11) DEFAULT NULL COMMENT '智慧豆数量',
  `MEMBER_ID` varchar(32) DEFAULT NULL COMMENT '会员外键',
  KEY `FK_MPA_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MPA_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员智惠豆帐号，专门用于竞猜充值，变现为优惠券';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_play_account`
--

LOCK TABLES `member_play_account` WRITE;
/*!40000 ALTER TABLE `member_play_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_play_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_product_distribution`
--

DROP TABLE IF EXISTS `member_product_distribution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_product_distribution` (
  `ID` varchar(120) NOT NULL,
  `MEMBER_ID` varchar(45) DEFAULT NULL COMMENT '会员ID',
  `PRODUCT_ID` varchar(45) DEFAULT NULL COMMENT '产品ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员分销产品中间表（越高级的会员能够分销的商品越多）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_product_distribution`
--

LOCK TABLES `member_product_distribution` WRITE;
/*!40000 ALTER TABLE `member_product_distribution` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_product_distribution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_profit`
--

DROP TABLE IF EXISTS `member_profit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_profit` (
  `ID` varchar(32) NOT NULL,
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  `PRODUCT_ID` varchar(45) DEFAULT NULL,
  `SALE_PRICE` double DEFAULT NULL COMMENT '销售价',
  `DEAL_PRICE` double DEFAULT NULL COMMENT '成交价',
  `PROFIT` double DEFAULT NULL COMMENT '盈利',
  `DISTRIBUTION_VALUE` double DEFAULT NULL COMMENT '分销让利值,是指该商品允许分销以后,销售出去给分销者的钱',
  `REAL_PROFIT` double DEFAULT '0',
  KEY `FK_MEMPROFIT_MEMBER_idx` (`MEMBER_ID`),
  KEY `FK_MEMPROFIT_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_MEMPROFIT_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MEMPROFIT_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员盈利表，会员可以分销商品，';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_profit`
--

LOCK TABLES `member_profit` WRITE;
/*!40000 ALTER TABLE `member_profit` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_profit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `RECIVE_NAME` varchar(45) DEFAULT NULL COMMENT '收货人名称',
  `ADDRESS` varchar(45) DEFAULT NULL COMMENT '具体地址',
  `TELEPHONE` varchar(45) DEFAULT NULL COMMENT '电话',
  `MAIL_CODE` varchar(45) DEFAULT '000000' COMMENT '邮码',
  `DELIVERY_COMPANY` varchar(45) DEFAULT '申通公司' COMMENT '物流公司',
  `DELIVERY_ORDER_CODE` varchar(120) DEFAULT NULL COMMENT '物流公司订单号',
  `IS_FORM_SCCUESS` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_ORDER_SHOP_idx` (`SHOP_ID`),
  KEY `FK_ORDER_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `FK_ORDER_MEMBER_idx` (`MEMBER_ID`),
  KEY `DELIVERY_ORDER_CODE` (`DELIVERY_ORDER_CODE`),
  CONSTRAINT `FK_ORDER_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_delivery`
--

DROP TABLE IF EXISTS `order_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_delivery` (
  `ID` varchar(32) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `DELIVERY_COMPANY` varchar(45) DEFAULT '申通公司' COMMENT '物流公司',
  `DELIVERY_ORDER_CODE` varchar(120) DEFAULT NULL COMMENT '物流公司订单号',
  `NAME` varchar(45) DEFAULT NULL COMMENT '名称',
  `ORDER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `DELIVERY_ORDER_CODEINDEX` (`DELIVERY_ORDER_CODE`),
  KEY `ORDER_INDEX` (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单包裹信息，包括物流单号，订单号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_delivery`
--

LOCK TABLES `order_delivery` WRITE;
/*!40000 ALTER TABLE `order_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_trade_payment_order`
--

DROP TABLE IF EXISTS `pay_trade_payment_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_trade_payment_order` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `MEMBER_ID` varchar(120) DEFAULT NULL COMMENT '会员ID外键',
  `ORDER_ID` varchar(120) DEFAULT NULL COMMENT '订单ID外键',
  `PRODUCT_ID` varchar(120) DEFAULT NULL COMMENT '商品ID外键',
  `ORG_ID` varchar(120) DEFAULT NULL COMMENT '用户ID外键',
  `MEM_NAME` varchar(120) DEFAULT NULL COMMENT '会员名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `STATUS` varchar(45) DEFAULT NULL COMMENT '订单状态:',
  `PRODUCT_NAME` varchar(120) DEFAULT NULL,
  `ORDER_AMOUNT` double DEFAULT NULL COMMENT '订单金额',
  `MERCHANT_ORDER_NO` varchar(120) DEFAULT NULL COMMENT '订单商户号',
  `ORDER_FROM` varchar(120) DEFAULT NULL COMMENT '订单来源',
  `MERCHANT_NAME` varchar(120) DEFAULT NULL COMMENT '商家名称',
  `MERCHANT_NO` varchar(120) DEFAULT NULL COMMENT '商户编号',
  `ORDER_TIME` timestamp NULL DEFAULT NULL COMMENT '订单时间戳',
  `ORDER_DATE` date DEFAULT NULL COMMENT '订单日期',
  `ORDER_IP` varchar(20) DEFAULT NULL COMMENT '订单IP地址',
  `ORDER_REFERER_URL` varchar(240) DEFAULT NULL COMMENT '从哪个页面链接过来的(可用于防诈骗)',
  `RETURN_URL` varchar(600) DEFAULT NULL COMMENT '页面回调通知url',
  `NOTIFY_URL` varchar(600) DEFAULT NULL COMMENT '后台异步通知url',
  `CANCEL_REASON` varchar(600) DEFAULT NULL COMMENT '订单撤销原因',
  `ORDER_PERIOD` int(11) DEFAULT NULL COMMENT '订单有效期(单位分钟)',
  `EXPIRE_TIME` date DEFAULT NULL COMMENT '到期时间',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '支付备注',
  `PAY_WAY_NAME` varchar(45) DEFAULT NULL COMMENT '支付方式名称',
  `PAY_WAY_CODE` varchar(120) DEFAULT NULL COMMENT '支付方式编号',
  `TRX_TYPE` varchar(45) DEFAULT NULL COMMENT '交易业务类型  ：消费、充值等',
  `TRX_NO` varchar(120) DEFAULT NULL COMMENT '支付流水号',
  `PAY_TYPE_CODE` varchar(120) DEFAULT NULL,
  `PAY_TYPE_NAME` varchar(45) DEFAULT NULL COMMENT '支付类型名称',
  `FUND_INTO_TYPE` varchar(45) DEFAULT NULL COMMENT '资金流入类型',
  `IS_REFUND` tinyint(1) DEFAULT '0' COMMENT '是否退款(1:是,0:否,默认值为:0)',
  `REFUND_TIMES` int(11) DEFAULT '0' COMMENT '退款次数(默认值为:0)',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_trade_payment_order`
--

LOCK TABLES `pay_trade_payment_order` WRITE;
/*!40000 ALTER TABLE `pay_trade_payment_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `pay_trade_payment_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_category`
--

DROP TABLE IF EXISTS `pr_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_category`
--

LOCK TABLES `pr_category` WRITE;
/*!40000 ALTER TABLE `pr_category` DISABLE KEYS */;
INSERT INTO `pr_category` VALUES ('149879109000689',NULL,NULL,NULL,'2017-06-30 02:51:41','2017-06-30 02:51:41',0,NULL,NULL,3,0,'clothes','服饰',NULL,NULL,'',NULL,NULL,NULL,NULL),('149879256868151',NULL,NULL,NULL,'2017-06-30 03:16:08','2017-06-30 03:16:08',0,NULL,NULL,5,0,'wewe','we',NULL,NULL,'',NULL,NULL,NULL,NULL),('149879258353633',NULL,NULL,NULL,'2017-06-30 03:16:23','2017-06-30 03:16:23',0,NULL,NULL,6,0,'ew','ewewew',NULL,'149879109000689','149879109000689',NULL,NULL,NULL,NULL),('149879295537938',NULL,NULL,NULL,'2017-06-30 03:22:35','2017-06-30 03:22:35',0,NULL,NULL,7,0,'wwr','rrw',NULL,'149879109000689','149879109000689',NULL,NULL,NULL,NULL),('149879297219040',NULL,NULL,NULL,'2017-06-30 03:22:52','2017-06-30 03:22:52',0,NULL,NULL,8,0,'et','tet',NULL,'149879295537938','149879109000689,149879295537938',NULL,NULL,NULL,NULL),('149880539278652',NULL,NULL,NULL,'2017-06-30 06:49:52','2017-06-30 06:49:52',0,NULL,NULL,10,0,'1','1',NULL,'149879258353633','149879109000689,149879258353633',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `pr_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_image`
--

DROP TABLE IF EXISTS `pr_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_image`
--

LOCK TABLES `pr_image` WRITE;
/*!40000 ALTER TABLE `pr_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_product`
--

DROP TABLE IF EXISTS `pr_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_product` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否禁用，产品默认不可以用，必须经过审核',
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
  `CATEGORY_ID` varchar(120) DEFAULT NULL COMMENT '产品分类外键ID',
  `CONTENT` text,
  `EN_CONTENT` text COMMENT '英文内容',
  `ALLOW_DISTRIBUTION` tinyint(1) DEFAULT NULL COMMENT '是否允许分销',
  `DISTRIBUTION_VALUE` double DEFAULT NULL COMMENT '分销让利值,是指该商品允许分销以后,销售出去给分销者的钱',
  `SHOP_ID` varchar(120) DEFAULT NULL COMMENT '该商品所属的店',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余',
  `PURCHASING_COST` double DEFAULT NULL COMMENT '进货价',
  `DIST_INTRUEDUTION` varchar(200) DEFAULT NULL COMMENT '分销介绍',
  `SUPPLIER_ID` varchar(32) DEFAULT NULL COMMENT '供应商ID',
  `GOURP_SALE_PRICE` double DEFAULT '0' COMMENT '团购价',
  `INDEPENDENT_PRICE` double DEFAULT NULL COMMENT '独立销售价',
  `NUMBER_COUNT` int(11) DEFAULT NULL COMMENT '开团人数',
  `AUDIT_DESCRIPTION` varchar(360) DEFAULT NULL COMMENT '审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `fk_product_category_idx` (`CATEGORY_ID`),
  KEY `FK_PRODUCT_SHOP_idx` (`SHOP_ID`),
  KEY `USER_ID_INDEX` (`USER_ID`),
  KEY `FK_PRODUCT_SUPPLIER_idx` (`SUPPLIER_ID`),
  CONSTRAINT `FK_PRODUCT_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PRODUCT_SUPPLIER` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `pr_product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product`
--

LOCK TABLES `pr_product` WRITE;
/*!40000 ALTER TABLE `pr_product` DISABLE KEYS */;
INSERT INTO `pr_product` VALUES ('1498644416575',NULL,NULL,NULL,'2017-06-28 10:06:58','2017-06-28 10:06:58',0,NULL,NULL,10,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),('1498644425913',NULL,NULL,NULL,'2017-06-28 10:07:06','2017-06-28 10:07:06',0,NULL,NULL,11,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `pr_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_product_package_price`
--

DROP TABLE IF EXISTS `pr_product_package_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_product_package_price` (
  `ID` varchar(32) NOT NULL,
  `PROPERTIES` varchar(360) DEFAULT NULL COMMENT '属性组合',
  `INDEPENDENT_PRICE` double DEFAULT NULL COMMENT '属性值组合价',
  `PRODUCT_ID` varchar(32) DEFAULT NULL COMMENT '产品ID，根据ID可以取出产品不同的组合价钱',
  `GOURP_PRICE` varchar(45) DEFAULT NULL COMMENT '团购价',
  PRIMARY KEY (`ID`),
  KEY `FK_PACKAGE_PRICE_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_PACKAGE_PRICE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品有不同的属性，这个时候不同的规格，大小，颜色，可能产生不同价格，比如苹果手机白色的比黑色的要贵，这个时候需要一个表来记录不同属性产生的价格，';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product_package_price`
--

LOCK TABLES `pr_product_package_price` WRITE;
/*!40000 ALTER TABLE `pr_product_package_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_product_package_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_product_property`
--

DROP TABLE IF EXISTS `pr_product_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_product_property` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL COMMENT '属性名称',
  `CODE` varchar(45) DEFAULT NULL COMMENT '英文代码',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT '属性表描述',
  `PRODUCT_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROPERTY_PRODUCT_idx` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product_property`
--

LOCK TABLES `pr_product_property` WRITE;
/*!40000 ALTER TABLE `pr_product_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_product_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_product_property_value`
--

DROP TABLE IF EXISTS `pr_product_property_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_product_property_value` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL COMMENT '属性名称',
  `CODE` varchar(45) DEFAULT NULL COMMENT '英文代码',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT '属性表描述',
  `PROPERTY_ID` varchar(32) DEFAULT NULL COMMENT '属性ID',
  `IMAGE_ID` varchar(120) DEFAULT NULL COMMENT '属性值对应的图片',
  `DISABLE` tinyint(1) DEFAULT '0' COMMENT '是否禁用，0否，1是',
  PRIMARY KEY (`ID`),
  KEY `FK_PRO_VALUE_PROPERTY_idx` (`PROPERTY_ID`),
  CONSTRAINT `FK_PRO_VALUE_PROPERTY` FOREIGN KEY (`PROPERTY_ID`) REFERENCES `pr_product_property` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品属性值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product_property_value`
--

LOCK TABLES `pr_product_property_value` WRITE;
/*!40000 ALTER TABLE `pr_product_property_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_product_property_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_supplier`
--

DROP TABLE IF EXISTS `pr_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_supplier` (
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
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品供应商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_supplier`
--

LOCK TABLES `pr_supplier` WRITE;
/*!40000 ALTER TABLE `pr_supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `CONTACT_TELEPHONE` varchar(45) DEFAULT NULL COMMENT '联系人',
  `EMAIL` varchar(45) DEFAULT NULL COMMENT '邮件',
  `ADMIN_NAME` varchar(45) DEFAULT NULL COMMENT '管理员姓名',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_SHOP_SHOP_CATEGORY_idx` (`CATEGORY_ID`),
  KEY `FK_SHOP_TB_USER_idx` (`USER_ID`),
  CONSTRAINT `FK_SHOP_SHOP_CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `shop_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SHOP_TB_USER` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_category`
--

DROP TABLE IF EXISTS `shop_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_category`
--

LOCK TABLES `shop_category` WRITE;
/*!40000 ALTER TABLE `shop_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_org`
--

DROP TABLE IF EXISTS `tb_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_org` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `NAME` varchar(120) DEFAULT NULL COMMENT '机构名称',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `CHECKED` tinyint(1) DEFAULT NULL COMMENT '是否勾选',
  `TYPE` varchar(120) DEFAULT NULL COMMENT '类型',
  `TEXT` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `FK_ORG_TO_ORG_idx` (`PARENT_ID`),
  CONSTRAINT `FK_ORG_TO_ORG` FOREIGN KEY (`PARENT_ID`) REFERENCES `tb_org` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织结构';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_org`
--

LOCK TABLES `tb_org` WRITE;
/*!40000 ALTER TABLE `tb_org` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_resources`
--

DROP TABLE IF EXISTS `tb_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_resources` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(120) DEFAULT NULL COMMENT '资源名称',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(340) DEFAULT NULL COMMENT '父类IDS',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `AUTH_NAME` varchar(120) DEFAULT NULL COMMENT '权限名称',
  `PATH` varchar(120) DEFAULT NULL COMMENT '访问路径',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `TYPE` varchar(120) DEFAULT NULL COMMENT '类型',
  `CHECKED` tinyint(1) DEFAULT NULL COMMENT '是否勾选',
  `TEXT` varchar(120) DEFAULT NULL COMMENT '文本',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_resources`
--

LOCK TABLES `tb_resources` WRITE;
/*!40000 ALTER TABLE `tb_resources` DISABLE KEYS */;
INSERT INTO `tb_resources` VALUES ('150183915978716','资源管理',NULL,NULL,'2017-08-04 09:32:40','2017-08-04 09:32:40',NULL,NULL,NULL,'system:resources:view',NULL,NULL,'menu',NULL,NULL,'resourcesManage',NULL),('15018393878145','资源管理-新增','150183915978716','150183915978716','2017-08-04 09:36:28','2017-08-04 09:36:28',NULL,NULL,NULL,'system:resources:create',NULL,NULL,'button',NULL,NULL,'resourcesManageCreate',NULL),('150183945124351','资源管理-编辑','150183915978716','150183915978716','2017-08-04 09:37:31','2017-08-04 09:37:31',NULL,NULL,NULL,'system:resources:edit',NULL,NULL,'button',NULL,NULL,'resourcesManageEdit',NULL);
/*!40000 ALTER TABLE `tb_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用,0否，1是',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CODE` varchar(45) DEFAULT NULL COMMENT '代码，比如管理员角色，可填写为admin_role',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES ('150183669467347','admin','2017-08-04 08:51:34','2017-08-04 08:51:34',0,NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role_resources`
--

DROP TABLE IF EXISTS `tb_role_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role_resources` (
  `ID` varchar(32) NOT NULL,
  `RESOURCES_ID` varchar(32) DEFAULT NULL,
  `ROLE_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ROLERESOURCES_RESOURCES_idx` (`RESOURCES_ID`),
  KEY `FK_ROLERESOURCE_ROLE_idx` (`ROLE_ID`),
  CONSTRAINT `FK_ROLERESOURCES_RESOURCES` FOREIGN KEY (`RESOURCES_ID`) REFERENCES `tb_resources` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ROLERESOURCE_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `tb_role` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role_resources`
--

LOCK TABLES `tb_role_resources` WRITE;
/*!40000 ALTER TABLE `tb_role_resources` DISABLE KEYS */;
INSERT INTO `tb_role_resources` VALUES ('150184254929024','150183915978716','150183669467347'),('150184254929119','150183945124351','150183669467347'),('150184254929132','15018393878145','150183669467347');
/*!40000 ALTER TABLE `tb_role_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `ID` varchar(32) NOT NULL,
  `USERNAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(120) DEFAULT NULL COMMENT '密码',
  `LOGIN_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登录日期',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录日期',
  `EMAIL` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SEX` tinyint(1) DEFAULT NULL COMMENT '性别',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '头像',
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
  `NICKNAME` varchar(45) DEFAULT NULL COMMENT '昵称',
  `LOGIN_NAME` varchar(45) DEFAULT NULL COMMENT '登录名',
  `USERGROUP_ID` varchar(32) DEFAULT NULL,
  `EMERGECY_CONTACT` varchar(45) DEFAULT NULL COMMENT '紧急情况联系人',
  `IS_CERTIFICATE_INTERNATIONAL` tinyint(1) DEFAULT '1' COMMENT '是否是国内企业证件照,1表示是，0表示否',
  `ID_CARD` varchar(45) DEFAULT NULL COMMENT '身份证号码',
  `COOP_NAME` varchar(45) DEFAULT NULL COMMENT '公司名称',
  `COOP_ADDRESS` varchar(45) DEFAULT NULL COMMENT '公司经营地址',
  `BUSSINESS_LICENSE_CODE` varchar(45) DEFAULT NULL COMMENT '营业执照注册号',
  `ORG_CODE` varchar(45) DEFAULT NULL COMMENT '组织机构代码',
  `TAXPAYER` varchar(45) DEFAULT NULL COMMENT '纳税人识别码',
  `SOCIETY_CODE` varchar(45) DEFAULT NULL COMMENT '统一社会信用代码',
  `LEGAL_REPRESENTATIVE_CARD1` varchar(32) DEFAULT NULL COMMENT '法定代表人身份证正面照片',
  `LEGAL_REPRESENTATIVE_CARD2` varchar(32) DEFAULT NULL COMMENT '法定代表人身份证反面照片',
  `LEGAL_IDCARD_EFFITIVE` varchar(45) DEFAULT NULL COMMENT '法定代表人身份证有效期',
  `BUSSINESS_LICENSE_PIC` varchar(32) DEFAULT NULL COMMENT '营业执照',
  `LICENSE_FOR_OPENING_COUNT` varchar(32) DEFAULT NULL COMMENT '开户许可证',
  `QUANTITY_REPORT_ID` varchar(45) DEFAULT NULL COMMENT '质检报告',
  `TYPE` varchar(45) DEFAULT 'cooperation' COMMENT '商户类型，cooperation表示企业类型，person表示个人',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `FK_USER_USERGROUP_idx` (`USERGROUP_ID`),
  CONSTRAINT `FK_USER_USERGROUP` FOREIGN KEY (`USERGROUP_ID`) REFERENCES `tb_user_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('150181922197730','黄记新','e1e4f718b04d5467d4fda0299328176c','2017-08-04 10:45:25',NULL,'2017-08-04 10:45:25',NULL,NULL,'13926205227',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'150183679493710',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('150181958991064','杨苗青','6823d04d06ac25e4afde8d09de6751dc','2017-08-04 08:58:51','2017-08-04 04:06:29','2017-08-04 08:58:51',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,'150183679493710',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('15018198221054','yangmiaoqing','6823d04d06ac25e4afde8d09de6751dc','2017-08-04 08:58:55','2017-08-04 04:10:21','2017-08-04 08:58:55',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'杨苗青',NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,'150183679493710',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_apply`
--

DROP TABLE IF EXISTS `tb_user_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_apply` (
  `ID` varchar(32) NOT NULL,
  `USERNAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(120) DEFAULT NULL COMMENT '密码',
  `LOGIN_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登录日期',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录日期',
  `EMAIL` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `DISABLE` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SEX` tinyint(1) DEFAULT NULL COMMENT '性别',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `AGE` int(11) DEFAULT NULL COMMENT '身高',
  `WEIGHT` int(11) DEFAULT NULL COMMENT '体重',
  `QQ` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `WEIXIN` varchar(32) DEFAULT NULL COMMENT '微信',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '实名',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `NICKNAME` varchar(45) DEFAULT NULL COMMENT '昵称',
  `LOGIN_NAME` varchar(45) DEFAULT NULL COMMENT '登录名',
  `TYPE` varchar(45) DEFAULT 'cooperation' COMMENT '商户类型，cooperation表示企业类型，person表示个人',
  `EMERGECY_CONTACT` varchar(45) DEFAULT NULL COMMENT '紧急情况联系人',
  `IS_CERTIFICATE_INTERNATIONAL` tinyint(1) DEFAULT '1' COMMENT '是否是国内企业证件照,1表示是，0表示否',
  `ID_CARD` varchar(45) DEFAULT NULL COMMENT '身份证号码',
  `COOP_NAME` varchar(45) DEFAULT NULL COMMENT '公司名称',
  `COOP_ADDRESS` varchar(45) DEFAULT NULL COMMENT '公司经营地址',
  `BUSSINESS_LICENSE_CODE` varchar(45) DEFAULT NULL COMMENT '营业执照注册号',
  `ORG_CODE` varchar(45) DEFAULT NULL COMMENT '组织机构代码',
  `TAXPAYER` varchar(45) DEFAULT NULL COMMENT '纳税人识别码',
  `SOCIETY_CODE` varchar(45) DEFAULT NULL COMMENT '统一社会信用代码',
  `LEGAL_REPRESENTATIVE_CARD1` varchar(32) DEFAULT NULL COMMENT '法定代表人身份证正面照片',
  `LEGAL_REPRESENTATIVE_CARD2` varchar(32) DEFAULT NULL COMMENT '法定代表人身份证反面照片',
  `LEGAL_IDCARD_EFFITIVE` varchar(45) DEFAULT NULL COMMENT '法定代表人身份证有效期',
  `BUSSINESS_LICENSE_PIC` varchar(32) DEFAULT NULL COMMENT '营业执照',
  `LICENSE_FOR_OPENING_COUNT` varchar(32) DEFAULT NULL COMMENT '开户许可证',
  `QUANTITY_REPORT_ID` varchar(45) DEFAULT NULL COMMENT '质检报告',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户申请入驻表，如果申请通过，那么把所有信息给搬进tb_user表里面';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_apply`
--

LOCK TABLES `tb_user_apply` WRITE;
/*!40000 ALTER TABLE `tb_user_apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_assets`
--

DROP TABLE IF EXISTS `tb_user_assets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_assets` (
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
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_assets`
--

LOCK TABLES `tb_user_assets` WRITE;
/*!40000 ALTER TABLE `tb_user_assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_group`
--

DROP TABLE IF EXISTS `tb_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_group` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用,0否，1是',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CODE` varchar(45) DEFAULT NULL COMMENT '代码，比如管理员角色，可填写为admin_role',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_group`
--

LOCK TABLES `tb_user_group` WRITE;
/*!40000 ALTER TABLE `tb_user_group` DISABLE KEYS */;
INSERT INTO `tb_user_group` VALUES ('150183679493710','adminGroup','2017-08-04 08:53:15','2017-08-04 08:53:15',0,NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `tb_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_group_role`
--

DROP TABLE IF EXISTS `tb_user_group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_group_role` (
  `ID` varchar(32) NOT NULL,
  `USERGROUP_ID` varchar(32) DEFAULT NULL,
  `ROLE_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USERGROUP_idx` (`USERGROUP_ID`),
  KEY `FK_USERGROUP_ROLE_idx` (`ROLE_ID`),
  CONSTRAINT `FK_USERGROUP_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `tb_role` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_USERGROUP_USER` FOREIGN KEY (`USERGROUP_ID`) REFERENCES `tb_user_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组——角色中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_group_role`
--

LOCK TABLES `tb_user_group_role` WRITE;
/*!40000 ALTER TABLE `tb_user_group_role` DISABLE KEYS */;
INSERT INTO `tb_user_group_role` VALUES ('150183758332847','150183679493710','150183669467347');
/*!40000 ALTER TABLE `tb_user_group_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_quantity_report`
--

DROP TABLE IF EXISTS `tb_user_quantity_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_quantity_report` (
  `ID` varchar(32) NOT NULL,
  `USER_ASSET_ID` varchar(120) DEFAULT NULL COMMENT '用户名',
  `USER_ID` varchar(120) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质检报告中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_quantity_report`
--

LOCK TABLES `tb_user_quantity_report` WRITE;
/*!40000 ALTER TABLE `tb_user_quantity_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_quantity_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_trade_marke`
--

DROP TABLE IF EXISTS `tb_user_trade_marke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_trade_marke` (
  `ID` varchar(32) NOT NULL,
  `MARK_CODE` varchar(120) DEFAULT NULL COMMENT '商标注册号',
  `MARK_CODE_AUTH` varchar(120) DEFAULT NULL COMMENT '商标注册证明',
  `BRAND_AUTH` varchar(45) DEFAULT NULL COMMENT '品牌授权证明',
  `BRAND_AUTH_INEFFITIVE` varchar(45) DEFAULT NULL COMMENT '品牌授权有效期',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `USER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户商标';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_trade_marke`
--

LOCK TABLES `tb_user_trade_marke` WRITE;
/*!40000 ALTER TABLE `tb_user_trade_marke` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_trade_marke` ENABLE KEYS */;
UNLOCK TABLES;
