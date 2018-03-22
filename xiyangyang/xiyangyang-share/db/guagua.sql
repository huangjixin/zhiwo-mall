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
INSERT INTO `guess_account` VALUES ('1','2018-03-12 10:31:45','2018-03-14 09:15:44',1,NULL,NULL,1,NULL,'1',0,180,180),('2','2018-03-13 03:22:23','2018-03-14 09:15:44',1,NULL,NULL,2,NULL,'2',0,180,180);
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
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='会员账户记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_account_his`
--

LOCK TABLES `guess_account_his` WRITE;
/*!40000 ALTER TABLE `guess_account_his` DISABLE KEYS */;
INSERT INTO `guess_account_his` VALUES (99,'2018-03-13 10:32:46','2018-03-13 10:32:46',1,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,'1',0,180,180,'赢180','奇数','100',1.8),(100,'2018-03-13 10:32:46','2018-03-13 10:32:46',1,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,'2',0,180,180,'赢180','奇数','100',1.8),(102,'2018-03-13 10:32:46','2018-03-13 10:32:46',1,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,'1',0,180,180,'输180','偶数','100',1.8),(103,'2018-03-13 10:32:46','2018-03-13 10:32:46',1,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,'2',0,180,180,'输180','偶数','100',1.8),(105,'2018-03-14 09:16:13','2018-03-14 09:16:13',1,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,'1',0,180,180,'赢180','奇数','100',1.8),(106,'2018-03-14 09:16:13','2018-03-14 09:16:13',1,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,'2',0,180,180,'赢180','奇数','100',1.8);
/*!40000 ALTER TABLE `guess_account_his` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='会员竞猜分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_category`
--

LOCK TABLES `guess_category` WRITE;
/*!40000 ALTER TABLE `guess_category` DISABLE KEYS */;
INSERT INTO `guess_category` VALUES ('2637aeb84a3647a99fdfe73e305cfbd3',NULL,NULL,NULL,'2018-03-18 07:35:52',NULL,1,NULL,NULL,5,0,NULL,'广东11选5奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'gd11x5',NULL),('31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,'2018-03-18 07:20:13',NULL,1,NULL,NULL,1,NULL,NULL,'趣味竞猜',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('61c34bf8762b4bd3b11520a6de21ab1b',NULL,NULL,NULL,'2018-03-18 07:21:13',NULL,1,NULL,NULL,4,0,NULL,'福彩3D奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'fc3d',NULL),('8ab7fbfb95684e60bcfba6a785b34752',NULL,NULL,NULL,'2018-03-18 07:20:20',NULL,1,NULL,NULL,3,0,NULL,'排列5奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'pl5',NULL),('c454a46b59b8453da3ca1acdfa2ec941',NULL,NULL,NULL,'2018-03-18 07:19:06',NULL,1,NULL,NULL,2,0,NULL,'排列3奇偶数竞猜',NULL,'31764b5b98a14974ab7181bfef8632a7',NULL,NULL,NULL,NULL,'pl3',NULL);
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
INSERT INTO `guess_mem_options` VALUES ('1','1','1','1',100,'2018-03-16 01:58:59'),('10','8','3','4',100,'2018-03-16 01:58:59'),('2','2','1','1',100,'2018-03-16 01:58:59'),('3','1','2','1',100,'2018-03-16 01:58:59'),('4','2','2','1',100,'2018-03-16 01:58:59'),('5','3','3','2',100,'2018-03-16 01:58:59'),('6','4','3','2',100,'2018-03-16 01:58:59'),('7','5','3','3',100,'2018-03-16 01:58:59'),('8','6','3','3',100,'2018-03-16 01:58:59'),('9','7','3','4',100,'2018-03-16 01:58:59');
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
  `IS_RIGHT` tinyint(1) DEFAULT '0',
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
INSERT INTO `guess_options` VALUES ('1','奇数',1.8,'1',NULL,1),('10c42cb91b564cadb6d2201a120bd02b','奇数',1.8,'db7b92c97f5a4617b1d52ed646e070f3',NULL,0),('2','偶数',1.8,'1',NULL,0),('3','是',1.8,'2',NULL,1),('345f56e726c448f7a64e4ffb96c846e0','奇数',1.8,'9ff227274b2445ef820083ccdb6865a8',NULL,0),('4','不是',1.8,'2',NULL,0),('5','是',1.8,'3',NULL,1),('5ccc8356124f4860827a041b47c2dcdf','奇数',1.8,'325565d70bed4079891407c3c6a7bfdd',NULL,0),('6','不是',1.8,'3',NULL,0),('6f266260583549dbbf93d69ba0f0a34d','偶数',1.8,'325565d70bed4079891407c3c6a7bfdd',NULL,0),('7','是',1.8,'4',NULL,1),('7f8d77ee1a7848e1ac760b39c272edf4','偶数',1.8,'23766aac783f4ef19c572cb314ff855f',NULL,0),('8','不是',1.8,'4',NULL,0),('b40076a2b3ae4ca2817d268a6c9fa76c','偶数',1.8,'9ff227274b2445ef820083ccdb6865a8',NULL,0),('d635cb3314434a739c7723a51af86c41','奇数',1.8,'23766aac783f4ef19c572cb314ff855f',NULL,0),('dac26ad756fb446faf7bfdeebc88d4ad','偶数',1.8,'db7b92c97f5a4617b1d52ed646e070f3',NULL,1);
/*!40000 ALTER TABLE `guess_options` ENABLE KEYS */;
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
  `CHECKED` tinyint(1) DEFAULT '0' COMMENT '是否已经校对了答案，0：非，1：是',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_GUESS_QUESTION_GUESS_CATEGORY_idx` (`GUESS_CATEGORY_ID`),
  CONSTRAINT `FK_GUESS_QUESTION_GUESS_CATEGORY` FOREIGN KEY (`GUESS_CATEGORY_ID`) REFERENCES `guess_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='会员竞猜问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question`
--

LOCK TABLES `guess_question` WRITE;
/*!40000 ALTER TABLE `guess_question` DISABLE KEYS */;
INSERT INTO `guess_question` VALUES ('1',NULL,NULL,NULL,'2018-03-12 09:13:31',NULL,1,NULL,'2018-12-12 00:00:00',1,0,NULL,'20180102六合彩开盘全加最后奇偶数竞猜',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-12-12 00:00:00',1),('2',NULL,NULL,NULL,'2018-03-14 08:46:30',NULL,1,NULL,NULL,2,0,NULL,'20180103六合彩开盘全加最后奇偶数竞猜',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-09-14 16:46:30',1),('23766aac783f4ef19c572cb314ff855f',NULL,NULL,NULL,'2018-03-18 09:41:28',NULL,NULL,NULL,NULL,41,NULL,NULL,'2018031852','2018031852期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,NULL,NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-03-18 17:39:40',0),('3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,20,NULL,NULL,'中超霸主会不会是恒大拿冠军',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-09-14 16:46:30',1),('325565d70bed4079891407c3c6a7bfdd',NULL,NULL,NULL,'2018-03-18 08:19:09',NULL,NULL,NULL,NULL,35,NULL,NULL,'2018031844','2018031844期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,NULL,NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-03-18 16:19:40',0),('4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,21,NULL,NULL,'英超的冠军会不会是曼城',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-09-14 16:46:30',0),('5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,24,NULL,NULL,'NBA总冠军是不是勇士',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-09-14 16:46:30',0),('6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,23,NULL,NULL,'NBA总冠军是不是火箭',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-09-14 16:46:30',0),('9ff227274b2445ef820083ccdb6865a8',NULL,NULL,NULL,'2018-03-18 09:07:06',NULL,NULL,NULL,NULL,36,NULL,NULL,'2018031848','2018031848期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,NULL,NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-03-18 16:59:40',0),('db7b92c97f5a4617b1d52ed646e070f3',NULL,NULL,NULL,'2018-03-18 09:30:23',NULL,NULL,NULL,NULL,40,NULL,NULL,'2018031851','2018031851期广东11选5所有开奖数字累计结果相加是',NULL,NULL,NULL,NULL,NULL,'2637aeb84a3647a99fdfe73e305cfbd3',NULL,'2018-03-18 17:29:40',1);
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
  `CONTENT` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TYPEINDEX` (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜问题API取值表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question_api`
--

LOCK TABLES `guess_question_api` WRITE;
/*!40000 ALTER TABLE `guess_question_api` DISABLE KEYS */;
INSERT INTO `guess_question_api` VALUES ('dd9da259a3a343efa2258d3a7c53b4eb','广东11选5','gd11x5',NULL,'2018-03-18 17:29:40','{\"rows\":2,\"code\":\"gd11x5\",\"info\":\"免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费\",\"data\":[{\"expect\":\"2018031851\",\"opencode\":\"04,11,02,05,06\",\"opentime\":\"2018-03-18 17:30:40\",\"opentimestamp\":1521365440},{\"expect\":\"2018031850\",\"opencode\":\"05,04,03,01,02\",\"opentime\":\"2018-03-18 17:20:40\",\"opentimestamp\":1521364840}]}');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mem_member`
--

LOCK TABLES `mem_member` WRITE;
/*!40000 ALTER TABLE `mem_member` DISABLE KEYS */;
INSERT INTO `mem_member` VALUES ('1','1',NULL,NULL,'2018-03-12 10:31:10',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,0),('2','2',NULL,NULL,'2018-03-13 03:21:49',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,0),('3','Tony',NULL,NULL,'2018-03-16 01:38:21',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,0);
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
  PRIMARY KEY (`ID`)
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product`
--

LOCK TABLES `pr_product` WRITE;
/*!40000 ALTER TABLE `pr_product` DISABLE KEYS */;
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
  PRIMARY KEY (`ID`),
  KEY `FK_PR_PROPERTY_VALUE_idx` (`PRODUCT_ID`),
  KEY `FK_PR_PROPERTY_VALUE_PR_IMAGE_idx` (`IMAGE_ID`),
  CONSTRAINT `FK_PR_PROPERTY_VALUE_PR_IMAGE` FOREIGN KEY (`IMAGE_ID`) REFERENCES `pr_image` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PR_PROPERTY_VALUE_PR_PROPERTY` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_property` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
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
  PRIMARY KEY (`ID`),
  KEY `FK_PR_VALUE_PRICE_PR_PROPRERTY_VALUE_idx` (`VALUE_ID`),
  KEY `FK_PR_VALUE_PRICE_PR_PRODUCT_idx` (`PRODUCT_ID`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
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
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_SYS_USER_SYS_USER_GROUP_idx` (`USERGROUP_ID`),
  CONSTRAINT `FK_SYS_USER_SYS_USER_GROUP` FOREIGN KEY (`USERGROUP_ID`) REFERENCES `sys_user_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1','1','1',NULL,'2018-03-15 17:58:43','2018-03-15 17:58:43',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_group`
--

LOCK TABLES `sys_user_group` WRITE;
/*!40000 ALTER TABLE `sys_user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-19  9:52:06
