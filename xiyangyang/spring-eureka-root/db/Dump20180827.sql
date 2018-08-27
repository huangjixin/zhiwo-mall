-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: guagua
-- ------------------------------------------------------
-- Server version	5.6.33-log

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
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(1200) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(240) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(240) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `URL` varchar(240) DEFAULT NULL COMMENT '地址',
  `IP` varchar(32) DEFAULT NULL COMMENT 'IP',
  `CMS_ID` varchar(120) DEFAULT NULL COMMENT '外键ID，外键可能不太确定',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_assets`
--

LOCK TABLES `cms_assets` WRITE;
/*!40000 ALTER TABLE `cms_assets` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_assets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_category`
--

DROP TABLE IF EXISTS `cms_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_category` (
  `ID` varchar(120) NOT NULL COMMENT 'id标志符',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(120) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `IS_TOOIC` tinyint(1) DEFAULT '0' COMMENT '是否为专题',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用，0表示可用，1表示禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(120) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(320) DEFAULT NULL COMMENT '父类IDS',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(120) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(120) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `LEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_CMS_CATEGORY_CMS_CATEGORY_idx` (`PARENT_ID`),
  CONSTRAINT `FK_CMS_CATEGORY_CMS_CATEGORY` FOREIGN KEY (`PARENT_ID`) REFERENCES `cms_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_category`
--

LOCK TABLES `cms_category` WRITE;
/*!40000 ALTER TABLE `cms_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_comment`
--

DROP TABLE IF EXISTS `cms_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_comment` (
  `ID` varchar(32) NOT NULL COMMENT 'id标志符',
  `MEMBER_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用，0为可用，1表示不可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(240) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `CMS_DOCUMENT_ID` varchar(32) DEFAULT NULL,
  `CONTENT` varchar(120) DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_CMS_COMMENT_CMS_COMMENT_idx` (`PARENT_ID`),
  KEY `FK_CMS_COMMENT_CMS_DOCUMENT_idx` (`CMS_DOCUMENT_ID`),
  CONSTRAINT `FK_CMS_COMMENT_CMS_COMMENT` FOREIGN KEY (`PARENT_ID`) REFERENCES `cms_comment` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CMS_COMMENT_CMS_DOCUMENT` FOREIGN KEY (`CMS_DOCUMENT_ID`) REFERENCES `cms_document` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单评论';
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
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `IS_TOOIC` tinyint(1) DEFAULT NULL COMMENT '是否为专题',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(240) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
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
  `CONTENT` text COMMENT '内容',
  `TO_INDEX` tinyint(1) DEFAULT '0' COMMENT '是否推荐到首页',
  `TO_CHANNEL_INDEX` tinyint(1) DEFAULT '0' COMMENT '是否推荐到频道首页',
  `CMS_CATEGORY_ID` varchar(120) DEFAULT NULL,
  `AUTHOR` varchar(45) DEFAULT NULL COMMENT '作者',
  `START_TIME` datetime DEFAULT NULL COMMENT '開始時間',
  `END_TIME` datetime DEFAULT NULL COMMENT '結束時間',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_CMS_DOCUMENT_CMS_CATEGORY_idx` (`CMS_CATEGORY_ID`),
  CONSTRAINT `FK_CMS_DOCUMENT_CMS_CATEGORY` FOREIGN KEY (`CMS_CATEGORY_ID`) REFERENCES `cms_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_document`
--

LOCK TABLES `cms_document` WRITE;
/*!40000 ALTER TABLE `cms_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cms_topic`
--

DROP TABLE IF EXISTS `cms_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cms_topic` (
  `ID` varchar(32) NOT NULL COMMENT 'id标志符',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `PATH` varchar(240) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  PRIMARY KEY (`ID`),
  KEY `SORT_INDEX` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_topic`
--

LOCK TABLES `cms_topic` WRITE;
/*!40000 ALTER TABLE `cms_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `cms_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_account`
--

DROP TABLE IF EXISTS `guess_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_account` (
  `ID` varchar(120) NOT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用,0否,1是',
  `DESCRIPTION` varchar(120) DEFAULT NULL COMMENT '描述',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '实名',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  `LOCKED` tinyint(1) DEFAULT '0' COMMENT '是否锁定,0否,1是',
  `BALANCE` double DEFAULT NULL COMMENT '账号余额',
  `DEPOSIT` double DEFAULT NULL COMMENT '存款变动金额',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SORT_UNIQUE` (`SORT`),
  KEY `FK_MEM_ACCOUNT_MEM_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MEM_ACCOUNT_MEM_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `mem_member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='会员账户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_account`
--

LOCK TABLES `guess_account` WRITE;
/*!40000 ALTER TABLE `guess_account` DISABLE KEYS */;
INSERT INTO `guess_account` VALUES ('1','2018-03-12 10:31:45','2018-03-26 09:20:23',1,NULL,NULL,1,NULL,'1',0,0,0),('2','2018-03-13 03:22:23','2018-03-26 10:17:20',1,NULL,NULL,2,NULL,'2',0,360,360);
/*!40000 ALTER TABLE `guess_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_account_his`
--

DROP TABLE IF EXISTS `guess_account_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_account_his` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用,0否,1是',
  `DESCRIPTION` varchar(120) DEFAULT NULL COMMENT '描述',
  `REAL_NAME` varchar(32) DEFAULT NULL COMMENT '实名',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  `LOCKED` tinyint(1) DEFAULT '0' COMMENT '是否锁定,0否,1是',
  `BALANCE` double DEFAULT NULL COMMENT '账号余额',
  `DEPOSIT` double DEFAULT NULL COMMENT '存款变动金额',
  `TYPE` varchar(45) DEFAULT NULL COMMENT '类型add，进账，reduce,出账。',
  `CONTENT` varchar(120) DEFAULT NULL COMMENT '竞猜内容',
  `DEVOTE` varchar(45) DEFAULT NULL COMMENT '投入',
  `RATE` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MEM_ACCOUNT_HIS_MEM_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MEM_ACCOUNT_HIS_MEM_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `mem_member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='会员账户记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_account_his`
--

LOCK TABLES `guess_account_his` WRITE;
/*!40000 ALTER TABLE `guess_account_his` DISABLE KEYS */;
INSERT INTO `guess_account_his` VALUES (108,'2018-03-26 10:17:20','2018-03-26 10:17:20',1,'test',NULL,NULL,'2',0,360,360,'赢360','偶数','200',1.8);
/*!40000 ALTER TABLE `guess_account_his` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_bet_rate`
--

DROP TABLE IF EXISTS `guess_bet_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_bet_rate` (
  `ID` varchar(120) NOT NULL,
  `BET_RATE` double DEFAULT NULL COMMENT '竞猜赔率',
  `COMBINE_ID` varchar(120) DEFAULT NULL,
  `SORT` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `COMBINEINDEX` (`COMBINE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜赔率表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_bet_rate`
--

LOCK TABLES `guess_bet_rate` WRITE;
/*!40000 ALTER TABLE `guess_bet_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_bet_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_category`
--

DROP TABLE IF EXISTS `guess_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_category` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '种类名称',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(1200) DEFAULT NULL COMMENT '父类IDS',
  `ICON` varchar(240) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(240) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `USER_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_GUESS_CATEGORY_GUESS_CATEGORY_idx` (`PARENT_ID`),
  CONSTRAINT `FK_GUESS_CATEGORY_GUESS_CATEGORY` FOREIGN KEY (`PARENT_ID`) REFERENCES `guess_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='会员竞猜分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_category`
--

LOCK TABLES `guess_category` WRITE;
/*!40000 ALTER TABLE `guess_category` DISABLE KEYS */;
INSERT INTO `guess_category` VALUES ('2637aeb84a3647a99fdfe73e305cfbd3',NULL,NULL,NULL,'2018-03-18 07:35:52',NULL,1,NULL,NULL,5,0,NULL,'广东11选5奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,'<i class=\"fa fa-futbol-o\" aria-hidden=\"true\"></i>',NULL,NULL,'gd11x5',NULL),('31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,'2018-03-18 07:20:13',NULL,1,NULL,NULL,1,NULL,NULL,'趣味竞猜',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('61c34bf8762b4bd3b11520a6de21ab1b',NULL,NULL,NULL,'2018-03-18 07:21:13',NULL,1,NULL,NULL,4,0,NULL,'福彩3D奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'fc3d',NULL),('8ab7fbfb95684e60bcfba6a785b34752',NULL,NULL,NULL,'2018-03-18 07:20:20',NULL,1,NULL,NULL,3,0,NULL,'排列5奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'pl5',NULL),('c454a46b59b8453da3ca1acdfa2ec941',NULL,NULL,NULL,'2018-03-18 07:19:06',NULL,1,NULL,NULL,2,0,NULL,'排列3奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'pl3',NULL);
/*!40000 ALTER TABLE `guess_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_mem_options`
--

DROP TABLE IF EXISTS `guess_mem_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_mem_options` (
  `ID` varchar(120) NOT NULL,
  `OPTION_ID` varchar(120) DEFAULT NULL COMMENT '名称竞猜选项ID',
  `MEM_ID` varchar(120) DEFAULT NULL COMMENT '会员ID',
  `QUESTION_ID` varchar(120) DEFAULT NULL COMMENT '竞猜问题ID',
  `BET_VALUE` int(11) DEFAULT NULL COMMENT '下注数量',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `GUESS_OPTIONS_COMBINE_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_GUESS_MEM_OPTION_GUESS_OPTION_idx` (`OPTION_ID`),
  KEY `FK_GUESS_MEM_OPTION_MEM_MEMBER_idx` (`MEM_ID`),
  KEY `FK_GUESS_MEM_OPTION_GUESS_QUESTION_idx` (`QUESTION_ID`),
  CONSTRAINT `FK_GUESS_MEM_OPTION_GUESS_OPTION` FOREIGN KEY (`OPTION_ID`) REFERENCES `guess_options` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GUESS_MEM_OPTION_GUESS_QUESTION` FOREIGN KEY (`QUESTION_ID`) REFERENCES `guess_question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_GUESS_MEM_OPTION_MEM_MEMBER` FOREIGN KEY (`MEM_ID`) REFERENCES `mem_member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员竞猜项目的答案';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_mem_options`
--

LOCK TABLES `guess_mem_options` WRITE;
/*!40000 ALTER TABLE `guess_mem_options` DISABLE KEYS */;
INSERT INTO `guess_mem_options` VALUES ('1',NULL,NULL,NULL,NULL,'2018-04-15 09:45:50','1');
/*!40000 ALTER TABLE `guess_mem_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_options`
--

DROP TABLE IF EXISTS `guess_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_options` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `BET_RATE` double DEFAULT NULL COMMENT '赔率',
  `GUESS_QUESTION_ID` varchar(32) DEFAULT NULL COMMENT '竞猜问题ID',
  `REAL_QUESTION_ID` varchar(32) DEFAULT NULL COMMENT '正式的外键ID',
  `IS_RIGHT` tinyint(1) DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`ID`),
  KEY `FK_GUESS_OPTIONS_GUESS_QUESTION_idx` (`GUESS_QUESTION_ID`),
  CONSTRAINT `FK_GUESS_OPTIONS_GUESS_QUESTION` FOREIGN KEY (`GUESS_QUESTION_ID`) REFERENCES `guess_question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜问题备选项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_options`
--

LOCK TABLES `guess_options` WRITE;
/*!40000 ALTER TABLE `guess_options` DISABLE KEYS */;
INSERT INTO `guess_options` VALUES ('000561c0767f43a295280c2dac3cf72f','偶数',1.8,'0a1a3cf4da834a7cb46af2c886511bd8',NULL,1,'2018-04-07 14:27:51'),('0027ff171bd447fe9ea0993e2e0c5f0b','偶数',1.8,'c07733bce27a4ef49dae2a6ca2a11da8',NULL,NULL,'2018-04-07 14:36:50'),('04792c2719034c1398550707db783be7','奇数',1.8,'98e4c6b0bd7c49fbb8bd1c41d0299039',NULL,NULL,'2018-04-07 14:37:48'),('08aa42d333f241da8afd82ca3ded6db0','奇数',1.8,'987243e99b994b7391468bad9cc37f4a',NULL,1,'2018-04-07 14:16:48'),('109901f7bf5c4c7abfe915bf66217797','偶数',1.8,'388186e8e5a24763b580cccc4fbb6e07',NULL,1,'2018-04-07 14:17:51'),('12482e9e102f4e1bab3f58437934f180','偶数',1.8,'28095cadcea44005921144fd59eafbd5',NULL,1,'2018-04-07 13:58:25'),('1980499856624fd2acb8c318c440f975','奇数',1.8,'d3d59c0c7c244f6bb0d2857e513ef97c',NULL,1,'2018-04-07 14:06:47'),('1d00b15b7c064a459c6e33fda34f6156','偶数',1.8,'74821381e88b4ad892db3dc6b0139a18',NULL,1,'2018-04-07 13:57:25'),('1d2af0c6ba8e4e5d9b177c4009aba8ed','偶数',1.8,'1068137b44e54bcab00ce64d9fcbb761',NULL,1,'2018-04-07 14:17:51'),('24d8e89233fd435ebfc72f596362ae9c','偶数',1.8,'10195c8017254de697cef8b7e858697d',NULL,1,'2018-04-07 14:07:48'),('2595f87dfea148c9bfac3b4e575f56c5','奇数',1.8,'388186e8e5a24763b580cccc4fbb6e07',NULL,1,'2018-04-07 14:17:51'),('25be1db2410641dd8c78eb5ecf767820','奇数',1.8,'ff8a97349f6f4f34aeb0689aa84ebfd6',NULL,1,'2018-04-07 14:26:48'),('280890b05217472aa38e45984fa95e0c','偶数',1.8,'d3d59c0c7c244f6bb0d2857e513ef97c',NULL,1,'2018-04-07 14:06:47'),('2cc9cb50591148bf822c017406509b7d','偶数',1.8,'ff8a97349f6f4f34aeb0689aa84ebfd6',NULL,1,'2018-04-07 14:26:48'),('3e6747450b1b40c7bb584dce2aaec9ec','奇数',1.8,'38f3b2e8b74d4c708cbf66e1fcc317a4',NULL,1,'2018-04-07 14:27:51'),('3ecfeefb78d249fe9b983a3bcf71bec4','奇数',1.8,'f5f0bb2a991e4afb9f44252aea933a36',NULL,NULL,'2018-04-07 14:39:03'),('4156ee60a69842b89fa40f515f108ad7','偶数',1.8,'987243e99b994b7391468bad9cc37f4a',NULL,1,'2018-04-07 14:16:48'),('4a8044d06c1b4f87b9021657e8757bbf','奇数',1.8,'74821381e88b4ad892db3dc6b0139a18',NULL,1,'2018-04-07 13:57:25'),('4ade503861b34fcf85dff5d649d83bbf','偶数',1.8,'9d407c3cd8e74b088b5b56989c921bdb',NULL,1,'2018-04-07 14:07:48'),('55902f554808428fb6fef2ee491c9417','奇数',1.8,'9d138bca2ada4f1a86a0ded81b23ff30',NULL,NULL,'2018-04-07 14:08:47'),('59c7f7b2fb6a41ef94bbe8d08e0a2bd6','奇数',1.8,'f2192a82888f4a638e081c2594522782',NULL,1,'2018-04-07 14:07:47'),('5a374f40c9f649bbbe322cf8d16ba083','偶数',1.8,'38f3b2e8b74d4c708cbf66e1fcc317a4',NULL,1,'2018-04-07 14:27:51'),('6be75ebbe2a544988891d35121964e53','奇数',1.8,'28095cadcea44005921144fd59eafbd5',NULL,1,'2018-04-07 13:58:25'),('6f297a6c92f5480aaa146a37fd54e2f5','奇数',1.8,'1068137b44e54bcab00ce64d9fcbb761',NULL,1,'2018-04-07 14:17:51'),('7010f9955e5d41bba9e12a4efab8ec7c','奇数',1.8,'c07733bce27a4ef49dae2a6ca2a11da8',NULL,NULL,'2018-04-07 14:36:50'),('70bd4535f6a049b9849b5397d5d5dd8b','奇数',1.8,'2732855f9e9d4909ac29cf2d802a55d4',NULL,1,'2018-04-07 13:58:25'),('75b035bf3fce4b75bb3d80d48af4751b','偶数',1.8,'d0916d88f2294e88a9640cc763543c51',NULL,1,'2018-04-07 14:07:48'),('785fa6c1a5684467b4823ff70b86e2d9','偶数',1.8,'f2192a82888f4a638e081c2594522782',NULL,1,'2018-04-07 14:07:47'),('78eaa9f4cd4a4f8ba919151e269c3b12','偶数',1.8,'6e7072fbc23546a7ad13ade8c4c07352',NULL,1,'2018-04-07 14:28:47'),('7c8f554d3bff41718cd676a50808cf55','奇数',1.8,'7e95ddc6690b472f82ec44dccc90e8ac',NULL,1,'2018-04-07 14:17:51'),('7cb028eda4b24b518ef13e971eea1098','奇数',1.8,'31c58db2d25e4bef9585774d63ef9add',NULL,NULL,'2018-04-07 14:37:48'),('7dbeac510afe4b3d92e27d1c95bc9766','偶数',1.8,'09420ba9079847bcb678505991537594',NULL,1,'2018-04-07 14:27:51'),('7e911ba7b512405c8713989434c16cd1','偶数',1.8,'7e95ddc6690b472f82ec44dccc90e8ac',NULL,1,'2018-04-07 14:17:51'),('91b2e695277e47c59a74fb74db131c43','偶数',1.8,'5d6fdaa1e6264851af0f576a0341409f',NULL,NULL,'2018-04-07 14:37:48'),('9b9690537cbc411d9f66436856c7aae9','偶数',1.8,'910bcc3051f7490b86a4d7f61fbcece9',NULL,1,'2018-04-07 13:58:25'),('a10acd83b5a04f79b027db20468de966','偶数',1.8,'98e4c6b0bd7c49fbb8bd1c41d0299039',NULL,NULL,'2018-04-07 14:37:48'),('aa92a8538ec7415c9552e50925650954','奇数',1.8,'9d407c3cd8e74b088b5b56989c921bdb',NULL,1,'2018-04-07 14:07:48'),('ad1ba7449fa7472aa227152077af0057','奇数',1.8,'09420ba9079847bcb678505991537594',NULL,1,'2018-04-07 14:27:51'),('ad8c41b6306f42e09cbf38a438d43d21','偶数',1.8,'34ffc1e254f04753bf45c502a208aba0',NULL,1,'2018-04-07 13:57:25'),('ae7e67d1a23a40f09b5d969e5a76651e','偶数',1.8,'2732855f9e9d4909ac29cf2d802a55d4',NULL,1,'2018-04-07 13:58:25'),('b00565d47229497497461fe1f703e0a2','奇数',1.8,'2ba4e33f4d0844bb92bd7ccb076aa797',NULL,1,'2018-04-07 13:59:25'),('b66d4a58c69841e699085b37be4def75','奇数',1.8,'5d6fdaa1e6264851af0f576a0341409f',NULL,NULL,'2018-04-07 14:37:48'),('cec9a55879b44ca4b641db1c11b51648','偶数',1.8,'4b86a6d4aea340ac898867b18418c941',NULL,1,'2018-04-07 14:17:51'),('d03f3ee5f1ac42f4874e577f4d12dd0b','偶数',1.8,'9d138bca2ada4f1a86a0ded81b23ff30',NULL,NULL,'2018-04-07 14:08:47'),('ded2653a7b0240eba213aca19747a74c','偶数',1.8,'31c58db2d25e4bef9585774d63ef9add',NULL,NULL,'2018-04-07 14:37:48'),('e42c6972439c4bca99009d448bf76189','奇数',1.8,'10195c8017254de697cef8b7e858697d',NULL,1,'2018-04-07 14:07:48'),('e458e6876e7b4cb7bc0c3e563ee0f50f','偶数',1.8,'f5f0bb2a991e4afb9f44252aea933a36',NULL,NULL,'2018-04-07 14:39:03'),('e79c92f9dbad437b9bafb9980bbccec7','偶数',1.8,'2ba4e33f4d0844bb92bd7ccb076aa797',NULL,1,'2018-04-07 13:59:25'),('ece50c79a0ca4bf39df90a3f10de2e15','奇数',1.8,'6e7072fbc23546a7ad13ade8c4c07352',NULL,1,'2018-04-07 14:28:47'),('f17edf09dcd14118bab812460aee1ec6','奇数',1.8,'0a1a3cf4da834a7cb46af2c886511bd8',NULL,1,'2018-04-07 14:27:51'),('f2f30543bf3a496ca68adbb243e323a3','奇数',1.8,'910bcc3051f7490b86a4d7f61fbcece9',NULL,1,'2018-04-07 13:58:25'),('f3846138e09e49ee8812c10d9e791735','奇数',1.8,'d0916d88f2294e88a9640cc763543c51',NULL,1,'2018-04-07 14:07:48'),('f5ff90df02ef445b975ba6490a3e83de','奇数',1.8,'34ffc1e254f04753bf45c502a208aba0',NULL,1,'2018-04-07 13:57:25'),('f86debffcc4c464a93f6f7f001ecb03a','奇数',1.8,'4b86a6d4aea340ac898867b18418c941',NULL,1,'2018-04-07 14:17:51');
/*!40000 ALTER TABLE `guess_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_options_combine`
--

DROP TABLE IF EXISTS `guess_options_combine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_options_combine` (
  `ID` varchar(120) NOT NULL,
  `OPTIONS_ID` varchar(120) DEFAULT NULL COMMENT '竞猜问题ID',
  `COMBINE_ID` varchar(120) DEFAULT NULL COMMENT '联合Id',
  `QUESTION_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_guess_options_combine_idx` (`OPTIONS_ID`),
  KEY `FK_GUESS_OPTIONS_COMBINE_GUESS_QUESTION_idx` (`QUESTION_ID`),
  CONSTRAINT `FK_GUESS_OPTIONS_COMBINE_GUESS_QUESTION` FOREIGN KEY (`QUESTION_ID`) REFERENCES `guess_question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_guess_options_combine` FOREIGN KEY (`OPTIONS_ID`) REFERENCES `guess_options` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多个选项联合表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_options_combine`
--

LOCK TABLES `guess_options_combine` WRITE;
/*!40000 ALTER TABLE `guess_options_combine` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_options_combine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_question`
--

DROP TABLE IF EXISTS `guess_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_question` (
  `ID` varchar(120) NOT NULL,
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `START_TIME` datetime DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` datetime DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `USER_ID` varchar(32) DEFAULT NULL,
  `GUESS_CATEGORY_ID` varchar(120) DEFAULT NULL,
  `GUESS_ANSWER_ID` varchar(120) DEFAULT NULL COMMENT '问题答案ID引用',
  `QUESTION_END_TIME` datetime DEFAULT NULL COMMENT '竞猜活动截止时间',
  `CHECKED` int(1) DEFAULT '-1' COMMENT '是否已经校对了答案，0：非，1：是,默认-1表示没有校验',
  `PARENT_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_GUESS_QUESTION_GUESS_CATEGORY_idx` (`GUESS_CATEGORY_ID`),
  CONSTRAINT `FK_GUESS_QUESTION_GUESS_CATEGORY` FOREIGN KEY (`GUESS_CATEGORY_ID`) REFERENCES `guess_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8 COMMENT='会员竞猜问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question`
--

LOCK TABLES `guess_question` WRITE;
/*!40000 ALTER TABLE `guess_question` DISABLE KEYS */;
INSERT INTO `guess_question` VALUES ('09420ba9079847bcb678505991537594',NULL,NULL,NULL,'2018-04-07 14:27:51',NULL,1,NULL,NULL,221,0,NULL,'2018040782','2018040782期北京11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'bj11x5',NULL,NULL,NULL,'2018-04-07 22:31:00',1,NULL),('0a1a3cf4da834a7cb46af2c886511bd8',NULL,NULL,NULL,'2018-04-07 14:27:51',NULL,1,NULL,NULL,222,0,NULL,'2018040782','2018040782期广西11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gx11x5',NULL,NULL,NULL,'2018-04-07 22:32:00',1,NULL),('10195c8017254de697cef8b7e858697d',NULL,NULL,NULL,'2018-04-07 14:07:48',NULL,1,NULL,NULL,213,0,NULL,'2018040779','2018040779期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gd11x5',NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-04-07 22:10:40',1,NULL),('1068137b44e54bcab00ce64d9fcbb761',NULL,NULL,NULL,'2018-04-07 14:17:52',NULL,1,NULL,NULL,219,0,NULL,'2018040780','2018040780期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gd11x5',NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-04-07 22:20:40',1,NULL),('2732855f9e9d4909ac29cf2d802a55d4',NULL,NULL,NULL,'2018-04-07 13:58:26',NULL,1,NULL,NULL,206,0,NULL,'2018040779','2018040779期北京11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'bj11x5',NULL,NULL,NULL,'2018-04-07 22:01:00',1,NULL),('28095cadcea44005921144fd59eafbd5',NULL,NULL,NULL,'2018-04-07 13:58:26',NULL,1,NULL,NULL,205,0,NULL,'2018040772','2018040772期甘肃11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gs11x5',NULL,NULL,NULL,'2018-04-07 22:02:00',1,NULL),('2ba4e33f4d0844bb92bd7ccb076aa797',NULL,NULL,NULL,'2018-04-07 13:59:26',NULL,1,NULL,NULL,208,0,NULL,'2018040781','2018040781期安徽11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'ah11x5',NULL,NULL,NULL,'2018-04-07 22:01:40',1,NULL),('31c58db2d25e4bef9585774d63ef9add',NULL,NULL,NULL,'2018-04-07 14:37:48',NULL,1,NULL,NULL,227,0,NULL,'2018040783','2018040783期广西11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gx11x5',NULL,NULL,NULL,'2018-04-07 22:42:00',0,NULL),('34ffc1e254f04753bf45c502a208aba0',NULL,NULL,NULL,'2018-04-07 13:57:26',NULL,1,NULL,NULL,204,0,NULL,'2018040778','2018040778期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gd11x5',NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-04-07 22:00:40',1,NULL),('388186e8e5a24763b580cccc4fbb6e07',NULL,NULL,NULL,'2018-04-07 14:17:51',NULL,1,NULL,NULL,216,0,NULL,'2018040774','2018040774期甘肃11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gs11x5',NULL,NULL,NULL,'2018-04-07 22:22:00',1,NULL),('38f3b2e8b74d4c708cbf66e1fcc317a4',NULL,NULL,NULL,'2018-04-07 14:27:51',NULL,1,NULL,NULL,223,0,NULL,'2018040781','2018040781期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gd11x5',NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-04-07 22:30:40',1,NULL),('4b86a6d4aea340ac898867b18418c941',NULL,NULL,NULL,'2018-04-07 14:17:52',NULL,1,NULL,NULL,218,0,NULL,'2018040781','2018040781期广西11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gx11x5',NULL,NULL,NULL,'2018-04-07 22:22:00',1,NULL),('5d6fdaa1e6264851af0f576a0341409f',NULL,NULL,NULL,'2018-04-07 14:37:48',NULL,1,NULL,NULL,228,0,NULL,'2018040782','2018040782期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gd11x5',NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-04-07 22:40:40',0,NULL),('6e7072fbc23546a7ad13ade8c4c07352',NULL,NULL,NULL,'2018-04-07 14:28:48',NULL,1,NULL,NULL,224,0,NULL,'2018040775','2018040775期甘肃11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gs11x5',NULL,NULL,NULL,'2018-04-07 22:32:00',1,NULL),('74821381e88b4ad892db3dc6b0139a18',NULL,NULL,NULL,'2018-04-07 13:57:26',NULL,1,NULL,NULL,203,0,NULL,'2018040784','2018040784期福建11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'fj11x5',NULL,NULL,NULL,'2018-04-07 22:01:40',1,NULL),('7e95ddc6690b472f82ec44dccc90e8ac',NULL,NULL,NULL,'2018-04-07 14:17:52',NULL,1,NULL,NULL,217,0,NULL,'2018040781','2018040781期北京11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'bj11x5',NULL,NULL,NULL,'2018-04-07 22:21:00',1,NULL),('910bcc3051f7490b86a4d7f61fbcece9',NULL,NULL,NULL,'2018-04-07 13:58:26',NULL,1,NULL,NULL,207,0,NULL,'2018040779','2018040779期广西11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gx11x5',NULL,NULL,NULL,'2018-04-07 22:02:00',1,NULL),('987243e99b994b7391468bad9cc37f4a',NULL,NULL,NULL,'2018-04-07 14:16:48',NULL,1,NULL,NULL,215,0,NULL,'2018040786','2018040786期福建11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'fj11x5',NULL,NULL,NULL,'2018-04-07 22:21:40',1,NULL),('98e4c6b0bd7c49fbb8bd1c41d0299039',NULL,NULL,NULL,'2018-04-07 14:37:48',NULL,1,NULL,NULL,226,0,NULL,'2018040783','2018040783期北京11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'bj11x5',NULL,NULL,NULL,'2018-04-07 22:41:00',0,NULL),('9d138bca2ada4f1a86a0ded81b23ff30',NULL,NULL,NULL,'2018-04-07 14:08:48',NULL,1,NULL,NULL,214,0,NULL,'2018040782','2018040782期安徽11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'ah11x5',NULL,NULL,NULL,'2018-04-07 22:11:40',0,NULL),('9d407c3cd8e74b088b5b56989c921bdb',NULL,NULL,NULL,'2018-04-07 14:07:48',NULL,1,NULL,NULL,212,0,NULL,'2018040780','2018040780期广西11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gx11x5',NULL,NULL,NULL,'2018-04-07 22:12:00',1,NULL),('c07733bce27a4ef49dae2a6ca2a11da8',NULL,NULL,NULL,'2018-04-07 14:36:51',NULL,1,NULL,NULL,225,0,NULL,'2018040788','2018040788期福建11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'fj11x5',NULL,NULL,NULL,'2018-04-07 22:41:40',0,NULL),('d0916d88f2294e88a9640cc763543c51',NULL,NULL,NULL,'2018-04-07 14:07:48',NULL,1,NULL,NULL,211,0,NULL,'2018040780','2018040780期北京11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'bj11x5',NULL,NULL,NULL,'2018-04-07 22:11:00',1,NULL),('d3d59c0c7c244f6bb0d2857e513ef97c',NULL,NULL,NULL,'2018-04-07 14:06:48',NULL,1,NULL,NULL,209,0,NULL,'2018040785','2018040785期福建11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'fj11x5',NULL,NULL,NULL,'2018-04-07 22:11:40',1,NULL),('f2192a82888f4a638e081c2594522782',NULL,NULL,NULL,'2018-04-07 14:07:48',NULL,1,NULL,NULL,210,0,NULL,'2018040773','2018040773期甘肃11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gs11x5',NULL,NULL,NULL,'2018-04-07 22:12:00',1,NULL),('f5f0bb2a991e4afb9f44252aea933a36',NULL,NULL,NULL,'2018-04-07 14:39:04',NULL,1,NULL,NULL,229,0,NULL,'2018040776','2018040776期甘肃11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'gs11x5',NULL,NULL,NULL,'2018-04-07 22:42:00',0,NULL),('ff8a97349f6f4f34aeb0689aa84ebfd6',NULL,NULL,NULL,'2018-04-07 14:26:48',NULL,1,NULL,NULL,220,0,NULL,'2018040787','2018040787期福建11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,'fj11x5',NULL,NULL,NULL,'2018-04-07 22:31:40',1,NULL);
/*!40000 ALTER TABLE `guess_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_question_api`
--

DROP TABLE IF EXISTS `guess_question_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_question_api` (
  `ID` varchar(120) NOT NULL COMMENT 'id',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `TYPE` varchar(45) DEFAULT NULL COMMENT '类型',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `CONTENT` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TYPEINDEX` (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜问题API取值表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question_api`
--

LOCK TABLES `guess_question_api` WRITE;
/*!40000 ALTER TABLE `guess_question_api` DISABLE KEYS */;
INSERT INTO `guess_question_api` VALUES ('160970b0807c4e5aa95f5b382d958126','广西11选5','gx11x5','2018-04-07 13:55:25','2018-04-07 22:42:48','{\"rows\":19,\"code\":\"gx11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018040782\",\"opencode\":\"06,01,02,04,10\",\"opentime\":\"2018-04-07 22:32:00\",\"opentimestamp\":1523111520},{\"expect\":\"2018040781\",\"opencode\":\"11,06,09,07,08\",\"opentime\":\"2018-04-07 22:22:00\",\"opentimestamp\":1523110920},{\"expect\":\"2018040780\",\"opencode\":\"07,02,10,04,05\",\"opentime\":\"2018-04-07 22:12:00\",\"opentimestamp\":1523110320},{\"expect\":\"2018040779\",\"opencode\":\"04,11,03,10,01\",\"opentime\":\"2018-04-07 22:02:00\",\"opentimestamp\":1523109720},{\"expect\":\"2018040778\",\"opencode\":\"10,05,03,02,01\",\"opentime\":\"2018-04-07 21:52:00\",\"opentimestamp\":1523109120},{\"expect\":\"2018040777\",\"opencode\":\"07,10,03,01,06\",\"opentime\":\"2018-04-07 21:42:00\",\"opentimestamp\":1523108520},{\"expect\":\"2018040776\",\"opencode\":\"08,04,05,01,07\",\"opentime\":\"2018-04-07 21:32:00\",\"opentimestamp\":1523107920},{\"expect\":\"2018040775\",\"opencode\":\"04,10,01,03,02\",\"opentime\":\"2018-04-07 21:22:00\",\"opentimestamp\":1523107320},{\"expect\":\"2018040774\",\"opencode\":\"09,04,02,08,07\",\"opentime\":\"2018-04-07 21:12:00\",\"opentimestamp\":1523106720},{\"expect\":\"2018040773\",\"opencode\":\"07,11,05,02,06\",\"opentime\":\"2018-04-07 21:02:00\",\"opentimestamp\":1523106120},{\"expect\":\"2018040772\",\"opencode\":\"06,01,07,09,02\",\"opentime\":\"2018-04-07 20:52:00\",\"opentimestamp\":1523105520},{\"expect\":\"2018040771\",\"opencode\":\"06,07,01,02,08\",\"opentime\":\"2018-04-07 20:42:00\",\"opentimestamp\":1523104920},{\"expect\":\"2018040770\",\"opencode\":\"09,10,11,02,07\",\"opentime\":\"2018-04-07 20:32:00\",\"opentimestamp\":1523104320},{\"expect\":\"2018040769\",\"opencode\":\"10,09,07,02,11\",\"opentime\":\"2018-04-07 20:22:00\",\"opentimestamp\":1523103720},{\"expect\":\"2018040768\",\"opencode\":\"09,05,03,07,01\",\"opentime\":\"2018-04-07 20:12:00\",\"opentimestamp\":1523103120},{\"expect\":\"2018040767\",\"opencode\":\"10,07,05,09,03\",\"opentime\":\"2018-04-07 20:02:00\",\"opentimestamp\":1523102520},{\"expect\":\"2018040766\",\"opencode\":\"02,08,03,07,05\",\"opentime\":\"2018-04-07 19:52:00\",\"opentimestamp\":1523101920},{\"expect\":\"2018040765\",\"opencode\":\"04,07,03,01,06\",\"opentime\":\"2018-04-07 19:42:00\",\"opentimestamp\":1523101320},{\"expect\":\"2018040764\",\"opencode\":\"05,08,04,10,09\",\"opentime\":\"2018-04-07 19:32:00\",\"opentimestamp\":1523100720}]}'),('3c45c7e8ff354415876636702cbf5627','广东11选5','gd11x5','2018-04-07 13:55:25','2018-04-07 22:42:48','{\"rows\":19,\"code\":\"gd11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018040781\",\"opencode\":\"08,11,03,10,05\",\"opentime\":\"2018-04-07 22:30:40\",\"opentimestamp\":1523111440},{\"expect\":\"2018040780\",\"opencode\":\"04,06,07,09,08\",\"opentime\":\"2018-04-07 22:20:40\",\"opentimestamp\":1523110840},{\"expect\":\"2018040779\",\"opencode\":\"11,09,07,06,10\",\"opentime\":\"2018-04-07 22:10:40\",\"opentimestamp\":1523110240},{\"expect\":\"2018040778\",\"opencode\":\"02,07,06,04,10\",\"opentime\":\"2018-04-07 22:00:40\",\"opentimestamp\":1523109640},{\"expect\":\"2018040777\",\"opencode\":\"10,11,02,03,05\",\"opentime\":\"2018-04-07 21:50:40\",\"opentimestamp\":1523109040},{\"expect\":\"2018040776\",\"opencode\":\"04,02,07,06,11\",\"opentime\":\"2018-04-07 21:40:40\",\"opentimestamp\":1523108440},{\"expect\":\"2018040775\",\"opencode\":\"07,06,04,05,09\",\"opentime\":\"2018-04-07 21:30:40\",\"opentimestamp\":1523107840},{\"expect\":\"2018040774\",\"opencode\":\"11,02,06,08,04\",\"opentime\":\"2018-04-07 21:20:40\",\"opentimestamp\":1523107240},{\"expect\":\"2018040773\",\"opencode\":\"10,01,02,06,09\",\"opentime\":\"2018-04-07 21:10:40\",\"opentimestamp\":1523106640},{\"expect\":\"2018040772\",\"opencode\":\"03,02,11,06,08\",\"opentime\":\"2018-04-07 21:00:40\",\"opentimestamp\":1523106040},{\"expect\":\"2018040771\",\"opencode\":\"10,07,11,08,05\",\"opentime\":\"2018-04-07 20:50:40\",\"opentimestamp\":1523105440},{\"expect\":\"2018040770\",\"opencode\":\"11,07,02,10,09\",\"opentime\":\"2018-04-07 20:40:40\",\"opentimestamp\":1523104840},{\"expect\":\"2018040769\",\"opencode\":\"01,08,09,06,03\",\"opentime\":\"2018-04-07 20:30:40\",\"opentimestamp\":1523104240},{\"expect\":\"2018040768\",\"opencode\":\"02,09,05,10,07\",\"opentime\":\"2018-04-07 20:20:40\",\"opentimestamp\":1523103640},{\"expect\":\"2018040767\",\"opencode\":\"09,04,11,02,10\",\"opentime\":\"2018-04-07 20:10:40\",\"opentimestamp\":1523103040},{\"expect\":\"2018040766\",\"opencode\":\"07,08,05,11,02\",\"opentime\":\"2018-04-07 20:00:40\",\"opentimestamp\":1523102440},{\"expect\":\"2018040765\",\"opencode\":\"09,02,01,08,11\",\"opentime\":\"2018-04-07 19:50:40\",\"opentimestamp\":1523101840},{\"expect\":\"2018040764\",\"opencode\":\"05,08,11,07,06\",\"opentime\":\"2018-04-07 19:40:40\",\"opentimestamp\":1523101240},{\"expect\":\"2018040763\",\"opencode\":\"01,10,11,08,07\",\"opentime\":\"2018-04-07 19:30:40\",\"opentimestamp\":1523100640}]}'),('6d22b4146f56475dac952f1ee123de13','北京11选5','bj11x5','2018-04-07 13:55:25','2018-04-07 22:42:48','{\"rows\":19,\"code\":\"bj11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018040782\",\"opencode\":\"06,11,07,04,01\",\"opentime\":\"2018-04-07 22:31:00\",\"opentimestamp\":1523111460},{\"expect\":\"2018040781\",\"opencode\":\"05,09,11,10,07\",\"opentime\":\"2018-04-07 22:21:00\",\"opentimestamp\":1523110860},{\"expect\":\"2018040780\",\"opencode\":\"05,10,03,08,06\",\"opentime\":\"2018-04-07 22:11:00\",\"opentimestamp\":1523110260},{\"expect\":\"2018040779\",\"opencode\":\"08,05,02,09,06\",\"opentime\":\"2018-04-07 22:01:00\",\"opentimestamp\":1523109660},{\"expect\":\"2018040778\",\"opencode\":\"05,09,08,03,06\",\"opentime\":\"2018-04-07 21:51:00\",\"opentimestamp\":1523109060},{\"expect\":\"2018040777\",\"opencode\":\"11,08,04,02,05\",\"opentime\":\"2018-04-07 21:41:00\",\"opentimestamp\":1523108460},{\"expect\":\"2018040776\",\"opencode\":\"10,05,03,06,07\",\"opentime\":\"2018-04-07 21:31:00\",\"opentimestamp\":1523107860},{\"expect\":\"2018040775\",\"opencode\":\"02,06,11,10,01\",\"opentime\":\"2018-04-07 21:21:00\",\"opentimestamp\":1523107260},{\"expect\":\"2018040774\",\"opencode\":\"01,07,03,06,08\",\"opentime\":\"2018-04-07 21:11:00\",\"opentimestamp\":1523106660},{\"expect\":\"2018040773\",\"opencode\":\"06,08,05,02,03\",\"opentime\":\"2018-04-07 21:01:00\",\"opentimestamp\":1523106060},{\"expect\":\"2018040772\",\"opencode\":\"01,03,07,11,10\",\"opentime\":\"2018-04-07 20:51:00\",\"opentimestamp\":1523105460},{\"expect\":\"2018040771\",\"opencode\":\"04,02,05,07,06\",\"opentime\":\"2018-04-07 20:41:00\",\"opentimestamp\":1523104860},{\"expect\":\"2018040770\",\"opencode\":\"03,11,10,08,09\",\"opentime\":\"2018-04-07 20:31:00\",\"opentimestamp\":1523104260},{\"expect\":\"2018040769\",\"opencode\":\"08,03,06,07,09\",\"opentime\":\"2018-04-07 20:21:00\",\"opentimestamp\":1523103660},{\"expect\":\"2018040768\",\"opencode\":\"06,05,07,02,01\",\"opentime\":\"2018-04-07 20:11:00\",\"opentimestamp\":1523103060},{\"expect\":\"2018040767\",\"opencode\":\"11,07,04,09,06\",\"opentime\":\"2018-04-07 20:01:00\",\"opentimestamp\":1523102460},{\"expect\":\"2018040766\",\"opencode\":\"05,03,11,09,08\",\"opentime\":\"2018-04-07 19:51:00\",\"opentimestamp\":1523101860},{\"expect\":\"2018040765\",\"opencode\":\"11,06,10,03,09\",\"opentime\":\"2018-04-07 19:41:00\",\"opentimestamp\":1523101260},{\"expect\":\"2018040764\",\"opencode\":\"04,03,10,09,06\",\"opentime\":\"2018-04-07 19:31:00\",\"opentimestamp\":1523100660}]}'),('adba07fa45424315b5c8d7b5c6a2043c','安徽11选5','ah11x5','2018-04-07 13:55:25','2018-04-07 22:42:48','{\"rows\":20,\"code\":\"ah11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018040781\",\"opencode\":\"01,04,08,06,11\",\"opentime\":\"2018-04-07 22:01:40\",\"opentimestamp\":1523109700},{\"expect\":\"2018040780\",\"opencode\":\"10,06,08,03,02\",\"opentime\":\"2018-04-07 21:51:40\",\"opentimestamp\":1523109100},{\"expect\":\"2018040779\",\"opencode\":\"11,06,10,04,03\",\"opentime\":\"2018-04-07 21:41:40\",\"opentimestamp\":1523108500},{\"expect\":\"2018040778\",\"opencode\":\"07,05,04,03,11\",\"opentime\":\"2018-04-07 21:31:40\",\"opentimestamp\":1523107900},{\"expect\":\"2018040777\",\"opencode\":\"08,10,01,11,03\",\"opentime\":\"2018-04-07 21:21:40\",\"opentimestamp\":1523107300},{\"expect\":\"2018040776\",\"opencode\":\"08,02,09,06,03\",\"opentime\":\"2018-04-07 21:11:40\",\"opentimestamp\":1523106700},{\"expect\":\"2018040775\",\"opencode\":\"08,01,05,10,11\",\"opentime\":\"2018-04-07 21:01:40\",\"opentimestamp\":1523106100},{\"expect\":\"2018040774\",\"opencode\":\"02,04,08,06,07\",\"opentime\":\"2018-04-07 20:51:40\",\"opentimestamp\":1523105500},{\"expect\":\"2018040773\",\"opencode\":\"10,06,09,07,03\",\"opentime\":\"2018-04-07 20:41:40\",\"opentimestamp\":1523104900},{\"expect\":\"2018040772\",\"opencode\":\"03,02,05,08,06\",\"opentime\":\"2018-04-07 20:31:40\",\"opentimestamp\":1523104300},{\"expect\":\"2018040771\",\"opencode\":\"08,09,03,11,10\",\"opentime\":\"2018-04-07 20:21:40\",\"opentimestamp\":1523103700},{\"expect\":\"2018040770\",\"opencode\":\"10,07,01,03,09\",\"opentime\":\"2018-04-07 20:11:40\",\"opentimestamp\":1523103100},{\"expect\":\"2018040769\",\"opencode\":\"11,06,09,02,04\",\"opentime\":\"2018-04-07 20:01:40\",\"opentimestamp\":1523102500},{\"expect\":\"2018040768\",\"opencode\":\"07,11,08,04,06\",\"opentime\":\"2018-04-07 19:51:40\",\"opentimestamp\":1523101900},{\"expect\":\"2018040767\",\"opencode\":\"11,08,07,06,01\",\"opentime\":\"2018-04-07 19:41:40\",\"opentimestamp\":1523101300},{\"expect\":\"2018040766\",\"opencode\":\"01,05,06,09,10\",\"opentime\":\"2018-04-07 19:31:40\",\"opentimestamp\":1523100700},{\"expect\":\"2018040765\",\"opencode\":\"01,09,07,10,04\",\"opentime\":\"2018-04-07 19:21:40\",\"opentimestamp\":1523100100},{\"expect\":\"2018040764\",\"opencode\":\"11,05,01,06,10\",\"opentime\":\"2018-04-07 19:11:40\",\"opentimestamp\":1523099500},{\"expect\":\"2018040763\",\"opencode\":\"10,08,07,01,02\",\"opentime\":\"2018-04-07 19:01:40\",\"opentimestamp\":1523098900},{\"expect\":\"2018040762\",\"opencode\":\"11,02,08,07,05\",\"opentime\":\"2018-04-07 18:51:40\",\"opentimestamp\":1523098300}]}'),('b65f2b9fc14a4692846cd56f0ce804a0','福建11选5','fj11x5','2018-04-07 13:55:25','2018-04-07 22:42:48','{\"rows\":19,\"code\":\"fj11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018040787\",\"opencode\":\"01,08,03,02,05\",\"opentime\":\"2018-04-07 22:31:40\",\"opentimestamp\":1523111500},{\"expect\":\"2018040786\",\"opencode\":\"07,09,06,11,03\",\"opentime\":\"2018-04-07 22:21:40\",\"opentimestamp\":1523110900},{\"expect\":\"2018040785\",\"opencode\":\"01,05,03,08,06\",\"opentime\":\"2018-04-07 22:11:40\",\"opentimestamp\":1523110300},{\"expect\":\"2018040784\",\"opencode\":\"02,09,03,10,04\",\"opentime\":\"2018-04-07 22:01:40\",\"opentimestamp\":1523109700},{\"expect\":\"2018040783\",\"opencode\":\"02,09,05,08,10\",\"opentime\":\"2018-04-07 21:51:40\",\"opentimestamp\":1523109100},{\"expect\":\"2018040782\",\"opencode\":\"02,04,05,03,09\",\"opentime\":\"2018-04-07 21:41:40\",\"opentimestamp\":1523108500},{\"expect\":\"2018040781\",\"opencode\":\"07,11,04,08,06\",\"opentime\":\"2018-04-07 21:31:40\",\"opentimestamp\":1523107900},{\"expect\":\"2018040780\",\"opencode\":\"07,09,05,04,02\",\"opentime\":\"2018-04-07 21:21:40\",\"opentimestamp\":1523107300},{\"expect\":\"2018040779\",\"opencode\":\"06,08,09,03,07\",\"opentime\":\"2018-04-07 21:11:40\",\"opentimestamp\":1523106700},{\"expect\":\"2018040778\",\"opencode\":\"07,03,02,04,11\",\"opentime\":\"2018-04-07 21:01:40\",\"opentimestamp\":1523106100},{\"expect\":\"2018040777\",\"opencode\":\"01,07,05,10,03\",\"opentime\":\"2018-04-07 20:51:40\",\"opentimestamp\":1523105500},{\"expect\":\"2018040776\",\"opencode\":\"03,06,04,09,07\",\"opentime\":\"2018-04-07 20:41:40\",\"opentimestamp\":1523104900},{\"expect\":\"2018040775\",\"opencode\":\"09,07,10,04,01\",\"opentime\":\"2018-04-07 20:31:40\",\"opentimestamp\":1523104300},{\"expect\":\"2018040774\",\"opencode\":\"09,04,01,08,03\",\"opentime\":\"2018-04-07 20:21:40\",\"opentimestamp\":1523103700},{\"expect\":\"2018040773\",\"opencode\":\"04,03,11,09,08\",\"opentime\":\"2018-04-07 20:11:40\",\"opentimestamp\":1523103100},{\"expect\":\"2018040772\",\"opencode\":\"02,03,01,09,10\",\"opentime\":\"2018-04-07 20:01:40\",\"opentimestamp\":1523102500},{\"expect\":\"2018040771\",\"opencode\":\"10,02,03,05,07\",\"opentime\":\"2018-04-07 19:51:40\",\"opentimestamp\":1523101900},{\"expect\":\"2018040770\",\"opencode\":\"09,07,01,02,08\",\"opentime\":\"2018-04-07 19:41:40\",\"opentimestamp\":1523101300},{\"expect\":\"2018040769\",\"opencode\":\"07,04,01,02,10\",\"opentime\":\"2018-04-07 19:31:40\",\"opentimestamp\":1523100700}]}'),('ca1ad418ab884f8faab9f9b7725120d1','甘肃11选5','gs11x5','2018-04-07 13:55:25','2018-04-07 22:42:48','{\"rows\":19,\"code\":\"gs11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018040775\",\"opencode\":\"11,08,07,03,04\",\"opentime\":\"2018-04-07 22:32:00\",\"opentimestamp\":1523111520},{\"expect\":\"2018040774\",\"opencode\":\"08,04,01,02,06\",\"opentime\":\"2018-04-07 22:22:00\",\"opentimestamp\":1523110920},{\"expect\":\"2018040773\",\"opencode\":\"07,08,09,05,04\",\"opentime\":\"2018-04-07 22:12:00\",\"opentimestamp\":1523110320},{\"expect\":\"2018040772\",\"opencode\":\"10,07,03,11,04\",\"opentime\":\"2018-04-07 22:02:00\",\"opentimestamp\":1523109720},{\"expect\":\"2018040771\",\"opencode\":\"05,11,06,04,10\",\"opentime\":\"2018-04-07 21:52:00\",\"opentimestamp\":1523109120},{\"expect\":\"2018040770\",\"opencode\":\"11,06,07,05,02\",\"opentime\":\"2018-04-07 21:42:00\",\"opentimestamp\":1523108520},{\"expect\":\"2018040769\",\"opencode\":\"03,10,02,01,04\",\"opentime\":\"2018-04-07 21:32:00\",\"opentimestamp\":1523107920},{\"expect\":\"2018040768\",\"opencode\":\"07,02,04,11,06\",\"opentime\":\"2018-04-07 21:22:00\",\"opentimestamp\":1523107320},{\"expect\":\"2018040767\",\"opencode\":\"06,02,10,01,03\",\"opentime\":\"2018-04-07 21:12:00\",\"opentimestamp\":1523106720},{\"expect\":\"2018040766\",\"opencode\":\"10,09,04,01,08\",\"opentime\":\"2018-04-07 21:02:00\",\"opentimestamp\":1523106120},{\"expect\":\"2018040765\",\"opencode\":\"03,04,02,09,10\",\"opentime\":\"2018-04-07 20:52:00\",\"opentimestamp\":1523105520},{\"expect\":\"2018040764\",\"opencode\":\"11,10,08,01,03\",\"opentime\":\"2018-04-07 20:42:00\",\"opentimestamp\":1523104920},{\"expect\":\"2018040763\",\"opencode\":\"03,09,05,07,11\",\"opentime\":\"2018-04-07 20:32:00\",\"opentimestamp\":1523104320},{\"expect\":\"2018040762\",\"opencode\":\"03,01,02,10,04\",\"opentime\":\"2018-04-07 20:22:00\",\"opentimestamp\":1523103720},{\"expect\":\"2018040761\",\"opencode\":\"02,11,04,07,05\",\"opentime\":\"2018-04-07 20:12:00\",\"opentimestamp\":1523103120},{\"expect\":\"2018040760\",\"opencode\":\"08,01,05,04,09\",\"opentime\":\"2018-04-07 20:02:00\",\"opentimestamp\":1523102520},{\"expect\":\"2018040759\",\"opencode\":\"11,06,09,04,01\",\"opentime\":\"2018-04-07 19:52:00\",\"opentimestamp\":1523101920},{\"expect\":\"2018040758\",\"opencode\":\"11,05,07,09,10\",\"opentime\":\"2018-04-07 19:42:00\",\"opentimestamp\":1523101320},{\"expect\":\"2018040757\",\"opencode\":\"10,06,11,01,03\",\"opentime\":\"2018-04-07 19:32:00\",\"opentimestamp\":1523100720}]}');
/*!40000 ALTER TABLE `guess_question_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mem_address`
--

DROP TABLE IF EXISTS `mem_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mem_address` (
  `ID` varchar(120) NOT NULL,
  `PROVINCE` varchar(120) DEFAULT NULL COMMENT '省',
  `CITY` varchar(120) DEFAULT NULL COMMENT '市',
  `AREA` varchar(120) DEFAULT NULL COMMENT '区',
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `STREET` varchar(120) DEFAULT NULL COMMENT '街道',
  `MEMBER_ID` varchar(120) DEFAULT NULL,
  `IS_DEFAULT` varchar(1) DEFAULT '0' COMMENT '是否设置为默认1为是,0为否',
  `ENABLED` varchar(1) DEFAULT '1',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`ID`),
  KEY `FK_MEM_ADDRESS_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MEM_ADDRESS` FOREIGN KEY (`MEMBER_ID`) REFERENCES `mem_member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mem_address`
--

LOCK TABLES `mem_address` WRITE;
/*!40000 ALTER TABLE `mem_address` DISABLE KEYS */;
INSERT INTO `mem_address` VALUES ('001084bbf2ec4b4ea9d17dd6fd6876da','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 10:11:25',NULL),('0270b09fb66a4473ba69331994898e07','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:02',NULL),('07a6372683a040d59580e28d06786aea','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:54:44',NULL),('098fd9f307c54e5eb386675ddf95dc84','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:01',NULL),('09d18f3dbe444827ab5c12d1482e3c7a','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:51',NULL),('0e140a9984484c7b8baf15206ba9f6f8','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:06',NULL),('11886149d8dd4ffebe24818a41f52864','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:47',NULL),('1211ccfbe9144a5a99fcbc9bfb7629ad','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:05',NULL),('15b1936516cd4ec1b0f6323ee937a3a8','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:16',NULL),('1880df677c4c4c89af852c9f76a00b6c','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:08',NULL),('1adba4b9de0e476a8d0763f205802464','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:58',NULL),('1bf18d53404149e292ff75c6ef8f6561','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:03',NULL),('24a35e8137354a988e513c78ce6010ef','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:12',NULL),('25ee9fbaae1c4836b83583e209545344','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:43',NULL),('26aa75cde5da4970bc28480641cef4ca','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:11',NULL),('277c8a373ffa401ea4d3749cfc1c9688','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:02',NULL),('28ee8d98239f404d9106b27309861212','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:41',NULL),('2a6cd8adf53c4560b938adc44b717df9','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:44',NULL),('2f034083b49741b1b8dcc660b029d20a','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:05',NULL),('31da286276cf4a9ea0fcd337dd6a353f','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:09',NULL),('31db45841e654ba69514fff412170175','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:07',NULL),('3a03da250279497d82f6fb19d991b0aa','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:04',NULL),('425321ab888f467aaee2fe10313d1819','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:50',NULL),('460348521e954a3f8237471a8ab164f0','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:11',NULL),('505d408f8dc24c2fad117fbfa61516ee','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:14',NULL),('56fcea27c1604ad0a5fa691a1abc3520','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:09',NULL),('5fd4cf494fa54ecca491e2c1406b443d','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:41',NULL),('61b95b37b6f14bf3a13b75d018ae8b97','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:46',NULL),('64fa3edf23394771aacac0c69c78ce0e','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:19',NULL),('65208d8fc0b6430384e459b169f25a0e','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:40',NULL),('73f79b88c10e417a85120321e9d8def8','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:11',NULL),('7d4a0e5d094a49bd837a312a4ad2233b','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:57',NULL),('7fae9e0a659b49f0b5fdbcbc515200e5','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:46',NULL),('8420de49b2ee4f25ac374881dbf1a3c3','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:10',NULL),('86e95c07bb454f7a81b189b32be1a8b9','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:59:18',NULL),('8773baa053294c29a66d296a9839bf4e','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:12',NULL),('890d91ca021241c5aed6c45f724b66c8','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:09',NULL),('8925c252a5e4482f8362cdb3c1444150','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:14',NULL),('8bda8558e3a94a0c9b9b3fd38abc8ae4','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:42',NULL),('8f4a804a27c142558ac04db35c9902eb','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:00',NULL),('8f7a67d5303042b2a3e997abcf7ae793','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:46',NULL),('905d81fb01fa48a6b6afd2cf8576abcf','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:42',NULL),('9aea6193b7314cd5a544a69a74645abc','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:04',NULL),('a1449ff9132d45faa28b0b507c43c3d9','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:52',NULL),('a85aeacd753748d18ade3b7db67121a5','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:53',NULL),('a9a5b710836a48e68b6af631ba669d28','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:01',NULL),('a9c0cad327b548b2a607af8026b8c592','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:13',NULL),('b2d71f50eccd444791f999939c4e991d','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:45',NULL),('c8d4e587808141cea5ac52431305d4ad','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:48',NULL),('c90e7054a86e4daf98294116e4ca107f','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:03',NULL),('c975a3b7392b42449c97f967bfb5cdbc','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:06',NULL),('cf9fdb6cd62745b8980bb76d568aebe0','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:48',NULL),('d10f4bc1490040c899aa82973c7fddf2','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:45',NULL),('d7f85899a7444b99b121634b307045cd','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:59',NULL),('d8349e73fd694e849d47aaf14663b1ee','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:43',NULL),('d91c00e4059642ddab223bd6ee6e0536','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:49',NULL),('db07bbe94ebd48fe9d286b477bf56022','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:49',NULL),('dc1bc0fcb9054a11bbd45609e74425b4','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:39',NULL),('e71996e5dfa843da9fda99aea9641b46','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:06',NULL),('e833712125294a87a450cffdfd51265b','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:15',NULL),('eae41d6e62a14daea3079f2228c5e00d','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:08',NULL),('ecb7d99816f744ad8bc8c073b829e85b','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:50',NULL),('ed7dddfeec4c4c208236ed88c2c17bed','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:11:59',NULL),('eeac700d9be44760bc1cdf40e6f8a2e4','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:13',NULL),('f8f909b400bf46428e42d43be3b15ed8','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:13',NULL),('f945c35d142b4055a810b19da83c4129','广东省','广州市','黄埔区','黄记新','13926205227','鱼珠街道茅岗新村4巷10号','1','0','1','2018-03-29 09:12:08',NULL);
/*!40000 ALTER TABLE `mem_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mem_category`
--

DROP TABLE IF EXISTS `mem_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mem_category` (
  `ID` varchar(120) NOT NULL COMMENT 'id',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用,0为否，1为是',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员种类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mem_category`
--

LOCK TABLES `mem_category` WRITE;
/*!40000 ALTER TABLE `mem_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `mem_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mem_member`
--

DROP TABLE IF EXISTS `mem_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mem_member` (
  `ID` varchar(120) NOT NULL,
  `USERNAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(120) DEFAULT NULL COMMENT '密码',
  `LOGIN_DATE` timestamp NULL DEFAULT NULL COMMENT '登录日期',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `LAST_LOGIN_DATE` timestamp NULL DEFAULT NULL COMMENT '上次登录日期',
  `EMAIL` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SEX` tinyint(1) DEFAULT NULL COMMENT '性别',
  `ICON` varchar(540) DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(240) DEFAULT NULL COMMENT '描述',
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
  `MEM_CATEGORY_ID` varchar(120) DEFAULT NULL,
  `NICKNAME` varchar(45) DEFAULT NULL COMMENT '昵称',
  `OPEN_ID` varchar(120) DEFAULT NULL COMMENT '微信的open_id',
  `BINDING_WECHAT` tinyint(1) DEFAULT '0' COMMENT '是否绑定微信',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_MEM_MEMBER_MEM_MEMBER_idx` (`PARENT_ID`),
  KEY `FK_MEM_MEMBER_MEM_CATEGORY_idx` (`MEM_CATEGORY_ID`),
  CONSTRAINT `FK_MEM_MEMBER_MEM_CATEGORY` FOREIGN KEY (`MEM_CATEGORY_ID`) REFERENCES `mem_category` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MEM_MEMBER_MEM_MEMBER` FOREIGN KEY (`PARENT_ID`) REFERENCES `mem_member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='会员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mem_member`
--

LOCK TABLES `mem_member` WRITE;
/*!40000 ALTER TABLE `mem_member` DISABLE KEYS */;
INSERT INTO `mem_member` VALUES ('1','1',NULL,NULL,'2018-03-12 10:31:10',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,0),('2','2',NULL,NULL,'2018-03-13 03:21:49',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,0),('2afdfd14d09f487dbafab3b653ef5b35',NULL,NULL,NULL,'2018-03-29 09:07:50',NULL,NULL,NULL,'13926205227',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,NULL,NULL,NULL,NULL,NULL,NULL,0),('3','Tony',NULL,NULL,'2018-03-16 01:38:21',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,0),('3d87a85776624ee5bdd04b3bfd5718a6',NULL,NULL,NULL,'2018-03-29 09:04:21',NULL,NULL,NULL,'13926205227',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,8,NULL,NULL,NULL,NULL,NULL,NULL,0),('9a2446ff55d443929922caa90affcd7a',NULL,NULL,NULL,'2018-03-29 09:04:16',NULL,NULL,NULL,'13926205227',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,0),('ad9ab0a1a315477bbfc4d1041c3aed86',NULL,NULL,NULL,'2018-03-29 09:04:23',NULL,NULL,NULL,'13926205227',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,9,NULL,NULL,NULL,NULL,NULL,NULL,0),('c4383ea2b1274919b151a0c680b45f85',NULL,NULL,NULL,'2018-03-29 09:04:19',NULL,NULL,NULL,'13926205227',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,NULL,NULL,NULL,0),('e12c7968f1084a8b93f66596b01c1c6f',NULL,NULL,NULL,'2018-03-29 09:04:20',NULL,NULL,NULL,'13926205227',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `mem_member` ENABLE KEYS */;
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
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用，0否，1是',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '种类名称',
  `DESCRIPTION` varchar(120) DEFAULT NULL COMMENT '描述',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(3200) DEFAULT NULL COMMENT '父类IDS',
  `ICON` varchar(1200) DEFAULT NULL COMMENT '图标',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_PR_CATEGORY_idx` (`PARENT_ID`),
  CONSTRAINT `FK_PR_CATEGORY` FOREIGN KEY (`PARENT_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品种类管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_category`
--

LOCK TABLES `pr_category` WRITE;
/*!40000 ALTER TABLE `pr_category` DISABLE KEYS */;
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
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `URL` varchar(120) DEFAULT NULL COMMENT '图片连接地址',
  `PRODUCT_ID` varchar(120) DEFAULT NULL COMMENT '产品ID',
  `LOCATION` varchar(240) DEFAULT NULL COMMENT '图片物理地址',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `TEMP_PRODUCT_ID` varchar(32) DEFAULT NULL COMMENT '真正的外键ID',
  `TYPE` varchar(45) DEFAULT NULL COMMENT '商品的缩略图，也可能是商品属性图，也可能是商品详情描述图\n三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail',
  `IP` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_PR_IMAGE_PR_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_PR_IMAGE_PR_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
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
-- Table structure for table `pr_price`
--

DROP TABLE IF EXISTS `pr_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_price` (
  `ID` varchar(120) NOT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT '属性表描述',
  `PRODUCT_ID` varchar(32) DEFAULT NULL COMMENT '属性ID',
  `ENABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用，0否，1是',
  `VALUE_PRICE_ID` varchar(120) DEFAULT NULL,
  `GOURP_PRICE` varchar(45) DEFAULT NULL COMMENT '团购价',
  `INDEPENDENT_PRICE` varchar(45) DEFAULT NULL COMMENT '属性值组合价',
  `ICON` varchar(120) DEFAULT NULL COMMENT '属性对应的图片',
  `COMBINE_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMBINE_ID` (`COMBINE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_price`
--

LOCK TABLES `pr_price` WRITE;
/*!40000 ALTER TABLE `pr_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_price` ENABLE KEYS */;
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
  `ORG_ID` varchar(120) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '0' COMMENT '是否可用，1是，0否，默认商品应该是经过审核才可以用的。',
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
  `CONTENT` varchar(10000) DEFAULT NULL,
  `EN_CONTENT` varchar(3600) DEFAULT NULL COMMENT '英文内容',
  `ALLOW_DISTRIBUTION` tinyint(1) DEFAULT NULL COMMENT '是否允许分销',
  `DISTRIBUTION_VALUE` double DEFAULT NULL COMMENT '分销让利值,是指该商品允许分销以后,销售出去给分销者的钱',
  `SHOP_ID` varchar(120) DEFAULT NULL COMMENT '该商品所属的店',
  `USER_ID` varchar(120) DEFAULT NULL COMMENT '用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余',
  `PURCHASING_COST` double DEFAULT NULL COMMENT '进货价',
  `DIST_INTRUEDUTION` varchar(200) DEFAULT NULL COMMENT '分销介绍',
  `SUPPLIER_ID` varchar(32) DEFAULT NULL COMMENT '供应商ID',
  `GOURP_SALE_PRICE` double DEFAULT '0' COMMENT '团购价',
  `INDEPENDENT_PRICE` double DEFAULT NULL COMMENT '独立销售价',
  `NUMBER_COUNT` int(11) DEFAULT NULL COMMENT '开团人数',
  `AUDIT_DESCRIPTION` varchar(360) DEFAULT NULL COMMENT '审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因',
  `STORAGE` int(11) DEFAULT NULL COMMENT '库存',
  `STATUS` varchar(45) DEFAULT 'online' COMMENT '商品状态，上架或者下架，默认上架为：online，下架为offline',
  `MARKET_PRICE` double DEFAULT NULL COMMENT '市价或者划下价',
  `TRANSPORT_FEE` double DEFAULT NULL COMMENT '运费',
  `SHOP_NAME` varchar(120) DEFAULT NULL COMMENT '商店名称',
  `IS_SENT_UNPAY` int(11) DEFAULT NULL COMMENT '是否支持货到付款，1是，0否',
  `CHECK_STATUS` int(11) DEFAULT '0' COMMENT '审核状态，0未审核，1审核通过，2驳回',
  `TYPE` varchar(45) DEFAULT NULL COMMENT '类型，字段值暂未确定。',
  `SALE_COUNT` int(11) DEFAULT NULL COMMENT '销售数量',
  `TO_INDEX` tinyint(1) DEFAULT '0' COMMENT '是否推荐到首页',
  `TO_SUB_INDEX` tinyint(1) DEFAULT '0' COMMENT '是否推荐到子栏目首页',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_PR_PRODUCT_PR_CATEGORY_idx` (`CATEGORY_ID`),
  KEY `FK_PR_PRODUCT_SHOP_idx` (`SHOP_ID`),
  CONSTRAINT `FK_PR_PRODUCT_PR_CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PR_PRODUCT_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product`
--

LOCK TABLES `pr_product` WRITE;
/*!40000 ALTER TABLE `pr_product` DISABLE KEYS */;
INSERT INTO `pr_product` VALUES ('1',NULL,NULL,NULL,'2018-03-31 14:08:34',NULL,0,NULL,NULL,1,0,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,'online',NULL,NULL,NULL,NULL,0,NULL,NULL,0,0);
/*!40000 ALTER TABLE `pr_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_property`
--

DROP TABLE IF EXISTS `pr_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_property` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL COMMENT '属性名称',
  `CODE` varchar(45) DEFAULT NULL COMMENT '英文代码',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT '属性表描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_property`
--

LOCK TABLES `pr_property` WRITE;
/*!40000 ALTER TABLE `pr_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_property_value`
--

DROP TABLE IF EXISTS `pr_property_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_property_value` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL COMMENT '属性名称',
  `CODE` varchar(45) DEFAULT NULL COMMENT '英文代码',
  `DESCRIPTION` varchar(45) DEFAULT NULL COMMENT '属性表描述',
  `IMAGE_ID` varchar(120) DEFAULT NULL COMMENT '属性值对应的图片',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用，0否，1是',
  `PRODUCT_ID` varchar(120) DEFAULT NULL,
  `PROPERTY_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PR_PROPERTY_VALUE_PR_IMAGE_idx` (`IMAGE_ID`),
  KEY `FK_PR_PROPERTY_VALUE_idx` (`PROPERTY_ID`),
  KEY `FK_PR_PROPERTY_VALUE_PR_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_PR_PROPERTY_VALUE_PR_IMAGE` FOREIGN KEY (`IMAGE_ID`) REFERENCES `pr_image` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PR_PROPERTY_VALUE_PR_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PR_PROPERTY_VALUE_PR_PROPERTY` FOREIGN KEY (`PROPERTY_ID`) REFERENCES `pr_property` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品属性值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_property_value`
--

LOCK TABLES `pr_property_value` WRITE;
/*!40000 ALTER TABLE `pr_property_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_property_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pr_value_price`
--

DROP TABLE IF EXISTS `pr_value_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pr_value_price` (
  `ID` varchar(120) NOT NULL,
  `PRODUCT_ID` varchar(32) DEFAULT NULL,
  `VALUE_ID` varchar(120) DEFAULT NULL,
  `COMBINE_ID` varchar(120) DEFAULT NULL COMMENT '联合VALUE_ID,是多个value_id的联合，例如123 1234 456。',
  PRIMARY KEY (`ID`),
  KEY `FK_PR_VALUE_PRICE_PR_PROPRERTY_VALUE_idx` (`VALUE_ID`),
  KEY `FK_PR_VALUE_PRICE_PR_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `COMBINE_IDX` (`COMBINE_ID`),
  CONSTRAINT `FK_PR_VALUE_PRICE_PR_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PR_VALUE_PRICE_PR_PROPRERTY_VALUE` FOREIGN KEY (`VALUE_ID`) REFERENCES `pr_property_value` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性值表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_value_price`
--

LOCK TABLES `pr_value_price` WRITE;
/*!40000 ALTER TABLE `pr_value_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `pr_value_price` ENABLE KEYS */;
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
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `START_TIME` datetime DEFAULT NULL COMMENT '开始时间，用于查询绑定',
  `END_TIME` datetime DEFAULT NULL COMMENT '结束时间，用于查询绑定',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `IS_DEFAULT` tinyint(1) DEFAULT '0' COMMENT '是否为默认，0：非，1：是',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` varchar(1200) DEFAULT NULL COMMENT '描述',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `THUMBNAIL` varchar(1200) DEFAULT NULL COMMENT '缩略图',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `CATEGORY_ID` varchar(120) DEFAULT NULL COMMENT '商品种类外键ID',
  `CONTENT` varchar(1200) DEFAULT NULL,
  `EN_CONTENT` varchar(1200) DEFAULT NULL COMMENT '英文内容',
  `USER_ID` varchar(120) DEFAULT NULL,
  `CONTACT_TELEPHONE` varchar(45) DEFAULT NULL COMMENT '联系人',
  `EMAIL` varchar(45) DEFAULT NULL COMMENT '邮件',
  `ADMIN_NAME` varchar(45) DEFAULT NULL COMMENT '管理员姓名',
  `SHOP_ICON` varchar(120) DEFAULT NULL COMMENT '商标地址',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_SHOP_PR_CATEGORY_idx` (`CATEGORY_ID`),
  CONSTRAINT `FK_SHOP_PR_CATEGORY` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='店铺';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES ('1',NULL,NULL,NULL,'2018-03-31 14:08:06',NULL,1,NULL,NULL,1,0,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_group_role`
--

DROP TABLE IF EXISTS `sys_group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_group_role` (
  `ID` varchar(120) NOT NULL,
  `USERGROUP_ID` varchar(120) DEFAULT NULL,
  `ROLE_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SYS_GROUP_ROLE_SYS_GROUP_idx` (`USERGROUP_ID`),
  KEY `FK_SYS_GROUP_ROLE_SYS_ROLE_idx` (`ROLE_ID`),
  CONSTRAINT `FK_SYS_GROUP_ROLE_SYS_GROUP` FOREIGN KEY (`USERGROUP_ID`) REFERENCES `sys_user_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SYS_GROUP_ROLE_SYS_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_group_role`
--

LOCK TABLES `sys_group_role` WRITE;
/*!40000 ALTER TABLE `sys_group_role` DISABLE KEYS */;
INSERT INTO `sys_group_role` VALUES ('1','1','1');
/*!40000 ALTER TABLE `sys_group_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_login_log`
--

DROP TABLE IF EXISTS `sys_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_login_log` (
  `ID` varchar(120) NOT NULL,
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `LOGIN_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '登录日期',
  `IP` varchar(45) DEFAULT NULL COMMENT '登录的IP地址',
  `NAME` varchar(45) DEFAULT NULL COMMENT '登录人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_login_log`
--

LOCK TABLES `sys_login_log` WRITE;
/*!40000 ALTER TABLE `sys_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_org`
--

DROP TABLE IF EXISTS `sys_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_org` (
  `ID` varchar(120) NOT NULL COMMENT 'id标志符',
  `EN_NAME` varchar(120) DEFAULT NULL COMMENT '英文名称',
  `NAME` varchar(120) DEFAULT NULL COMMENT '名称',
  `ORG_ID` varchar(120) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用，0表示可用，1表示禁用',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `PATH` varchar(120) DEFAULT NULL COMMENT '路径',
  `ICON` varchar(120) DEFAULT NULL COMMENT '图标',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(1200) DEFAULT NULL COMMENT '父类IDS',
  `CODE` varchar(120) DEFAULT NULL COMMENT '代码',
  `KEYWORDS` varchar(120) DEFAULT NULL COMMENT '关键字',
  `DESCRIPTION` varchar(120) DEFAULT NULL COMMENT '描述',
  `THUMBNAIL` varchar(120) DEFAULT NULL COMMENT '缩略图',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `LEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `FK_SYS_ORG_SYS_ORG_idx` (`PARENT_ID`),
  CONSTRAINT `FK_SYS_ORG_SYS_ORG` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_org` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_org`
--

LOCK TABLES `sys_org` WRITE;
/*!40000 ALTER TABLE `sys_org` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_resources`
--

DROP TABLE IF EXISTS `sys_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resources` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(120) DEFAULT NULL COMMENT '资源名称',
  `PARENT_ID` varchar(120) DEFAULT NULL COMMENT '父类ID',
  `PARENTIDS` varchar(340) DEFAULT NULL COMMENT '父类IDS',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
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
  `URL` varchar(120) DEFAULT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_SYS_RESOURCES_SYS_RESOURCES_idx` (`PARENT_ID`),
  CONSTRAINT `FK_SYS_RESOURCES_SYS_RESOURCES` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_resources` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resources`
--

LOCK TABLES `sys_resources` WRITE;
/*!40000 ALTER TABLE `sys_resources` DISABLE KEYS */;
INSERT INTO `sys_resources` VALUES ('1','1',NULL,NULL,'2018-03-19 03:04:58',NULL,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `sys_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用,0否，1是',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CODE` varchar(45) DEFAULT NULL COMMENT '代码，比如管理员角色，可填写为admin_role',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('1','1','2018-03-19 03:05:22',NULL,1,NULL,NULL,1,NULL,'1');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_resources`
--

DROP TABLE IF EXISTS `sys_role_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_resources` (
  `ID` varchar(120) NOT NULL,
  `RESOURCES_ID` varchar(120) DEFAULT NULL,
  `ROLE_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SYS_ROLE_RESOURCES_ROLE_idx` (`ROLE_ID`),
  KEY `FK_SYS_ROLE_RESOURCES_SYS_RESOURCES_idx` (`RESOURCES_ID`),
  CONSTRAINT `FK_SYS_ROLE_RESOURCES_SYS_RESOURCES` FOREIGN KEY (`RESOURCES_ID`) REFERENCES `sys_resources` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_SYS_ROLE_RESOURCES_SYS_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_resources`
--

LOCK TABLES `sys_role_resources` WRITE;
/*!40000 ALTER TABLE `sys_role_resources` DISABLE KEYS */;
INSERT INTO `sys_role_resources` VALUES ('1','1','1');
/*!40000 ALTER TABLE `sys_role_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `ID` varchar(120) NOT NULL,
  `USERNAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(120) DEFAULT NULL COMMENT '密码',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '登录日期',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `LAST_LOGIN_DATE` datetime DEFAULT NULL COMMENT '上次登录日期',
  `EMAIL` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
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
  `ORG_ID` varchar(120) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
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
  `SALT` varchar(120) DEFAULT NULL COMMENT '密码盐',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_SYS_USER_SYS_USER_GROUP_idx` (`USERGROUP_ID`),
  CONSTRAINT `FK_SYS_USER_SYS_USER_GROUP` FOREIGN KEY (`USERGROUP_ID`) REFERENCES `sys_user_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1','1','a9bf1d6c56d5b870dc20f08f4d74aadd',NULL,'2018-03-15 17:58:43','2018-03-20 11:34:02',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'1',NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation','db3940ad627ac25b213c7f01aad2050e'),('2','2',NULL,NULL,'2018-08-27 16:58:49','2018-08-27 16:58:49',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_group`
--

DROP TABLE IF EXISTS `sys_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_group` (
  `ID` varchar(120) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `ENABLED` tinyint(1) DEFAULT '1' COMMENT '是否可用,0否，1是',
  `CREATOR` varchar(120) DEFAULT NULL COMMENT '创建人',
  `UPDATER` varchar(120) DEFAULT NULL COMMENT '更新人',
  `SORT` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '组织结构表ID，该字段用于过滤数据，不做外键关联',
  `CODE` varchar(45) DEFAULT NULL COMMENT '代码，比如管理员角色，可填写为admin_role',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_group`
--

LOCK TABLES `sys_user_group` WRITE;
/*!40000 ALTER TABLE `sys_user_group` DISABLE KEYS */;
INSERT INTO `sys_user_group` VALUES ('1','1','2018-03-19 03:08:57',NULL,1,NULL,NULL,1,NULL,'1');
/*!40000 ALTER TABLE `sys_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(120) DEFAULT NULL,
  `ROLE_ID` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,'1','1');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'guagua'
--

--
-- Dumping routines for database 'guagua'
--
/*!50003 DROP FUNCTION IF EXISTS `selectCmsCategoryChildren` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectCmsCategoryChildren`(categoryId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(categoryId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(id) INTO sTempChd FROM cms_category WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectCmsCategoryParents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectCmsCategoryParents`(categoryId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(categoryId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM cms_category WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM cms_category WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectGuessCategoryChildren` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectGuessCategoryChildren`(categoryId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(categoryId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(id) INTO sTempChd FROM guess_category WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectGuessCategoryParents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectGuessCategoryParents`(categoryId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(categoryId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM guess_category WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM guess_category WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectGuessQuestionChildren` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectGuessQuestionChildren`(qId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(qId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(id) INTO sTempChd FROM guess_question WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectGuessQuestionParents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectGuessQuestionParents`(pId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(pId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM guess_question WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM guess_question WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectPrCategoryChildren` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectPrCategoryChildren`(categoryId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(categoryId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(id) INTO sTempChd FROM Pr_category WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectPrCategoryParents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectPrCategoryParents`(categoryId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(categoryId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM Pr_category WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM Pr_category WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectResourcesChildren` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectResourcesChildren`(resId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(resId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(id) INTO sTempChd FROM sys_resources WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `selectResourcesParents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `selectResourcesParents`(resId varchar(120)) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(resId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parent_id INTO sTempChd FROM sys_resources WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parent_id INTO sTempChd FROM sys_resources WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-27 18:23:03
