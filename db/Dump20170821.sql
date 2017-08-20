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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='会员竞猜分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_category`
--

LOCK TABLES `guess_category` WRITE;
/*!40000 ALTER TABLE `guess_category` DISABLE KEYS */;
INSERT INTO `guess_category` VALUES ('150243598907095',NULL,NULL,NULL,'2017-08-11 07:19:49','2017-08-11 07:45:11',0,NULL,NULL,14,0,NULL,'亲亲我','轻武器速度多少',NULL,NULL,'images/uassets/2017/8/11/1502437507627.gif',NULL,NULL,'我去请我',NULL),('150243697697466',NULL,NULL,NULL,'2017-08-11 07:36:16','2017-08-11 07:44:08',0,NULL,NULL,15,0,NULL,'32','32额额我',NULL,NULL,'images/uassets/2017/8/11/1502437445489.jpg',NULL,NULL,'23',NULL),('150254249687736',NULL,NULL,NULL,'2017-08-12 12:54:56','2017-08-12 12:54:56',0,NULL,NULL,16,0,NULL,'ewq','eqeq',NULL,NULL,'images/uassets/2017/8/12/1502542494009.jpg',NULL,NULL,'qe',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='会员竞猜问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question`
--

LOCK TABLES `guess_question` WRITE;
/*!40000 ALTER TABLE `guess_question` DISABLE KEYS */;
INSERT INTO `guess_question` VALUES ('150244079002122',NULL,NULL,NULL,'2017-08-11 08:39:50','2017-08-11 08:39:50',0,NULL,NULL,1,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244079408968',NULL,NULL,NULL,'2017-08-11 08:39:54','2017-08-11 08:39:54',0,NULL,NULL,2,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244079575939',NULL,NULL,NULL,'2017-08-11 08:39:55','2017-08-11 08:39:55',0,NULL,NULL,3,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244079714028',NULL,NULL,NULL,'2017-08-11 08:39:57','2017-08-11 08:39:57',0,NULL,NULL,4,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244079929385',NULL,NULL,NULL,'2017-08-11 08:39:59','2017-08-11 08:39:59',0,NULL,NULL,5,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('15024408009145',NULL,NULL,NULL,'2017-08-11 08:40:00','2017-08-11 08:40:00',0,NULL,NULL,6,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244080221899',NULL,NULL,NULL,'2017-08-11 08:40:02','2017-08-11 08:40:02',0,NULL,NULL,7,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244080343549',NULL,NULL,NULL,'2017-08-11 08:40:03','2017-08-11 08:40:03',0,NULL,NULL,8,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244080478298',NULL,NULL,NULL,'2017-08-11 08:40:04','2017-08-11 08:40:04',0,NULL,NULL,9,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244080590782',NULL,NULL,NULL,'2017-08-11 08:40:05','2017-08-11 08:40:05',0,NULL,NULL,10,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244080744152',NULL,NULL,NULL,'2017-08-11 08:40:07','2017-08-11 08:40:07',0,NULL,NULL,11,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('15024408086824',NULL,NULL,NULL,'2017-08-11 08:40:08','2017-08-11 08:40:08',0,NULL,NULL,12,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL),('150244081009069',NULL,NULL,NULL,'2017-08-11 08:40:10','2017-08-11 08:40:10',0,NULL,NULL,13,0,NULL,'wqwq','wqw','images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL,'wqw',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `guess_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_question_answer`
--

DROP TABLE IF EXISTS `guess_question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_question_answer` (
  `ID` varchar(32) NOT NULL,
  `question_id` varchar(32) DEFAULT NULL,
  `question_options_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_guess_question_answer_guess_question_idx` (`question_id`),
  KEY `FK_guess_question_answer_question_options_idx` (`question_options_id`),
  CONSTRAINT `FK_guess_question_answer_guess_question` FOREIGN KEY (`question_id`) REFERENCES `guess_question` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_guess_question_answer_question_options` FOREIGN KEY (`question_options_id`) REFERENCES `guess_question_options` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜问题答案';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question_answer`
--

LOCK TABLES `guess_question_answer` WRITE;
/*!40000 ALTER TABLE `guess_question_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_question_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guess_question_memanswer`
--

DROP TABLE IF EXISTS `guess_question_memanswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_question_memanswer` (
  `ID` varchar(32) NOT NULL,
  `MEMBER_ID` varchar(32) DEFAULT NULL,
  `question_id` varchar(32) DEFAULT NULL,
  `question_options_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_GUESS_QUESTION_MEMANSWER_MEMBER_idx` (`MEMBER_ID`),
  KEY `FK_GUESS_QUESTION_MEMANSWER_QUESTION_idx` (`question_id`),
  KEY `FK_GUESS_QUESTION_MEMANSWER_OPTIONS_idx` (`question_options_id`),
  CONSTRAINT `FK_GUESS_QUESTION_MEMANSWER_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_GUESS_QUESTION_MEMANSWER_OPTIONS` FOREIGN KEY (`question_options_id`) REFERENCES `guess_question_options` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_GUESS_QUESTION_MEMANSWER_QUESTION` FOREIGN KEY (`question_id`) REFERENCES `guess_question` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员竞猜的答案';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question_memanswer`
--

LOCK TABLES `guess_question_memanswer` WRITE;
/*!40000 ALTER TABLE `guess_question_memanswer` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_question_memanswer` ENABLE KEYS */;
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
  PRIMARY KEY (`ID`),
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
  `OPEN_ID` varchar(120) DEFAULT NULL COMMENT '微信的open_id',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_MEMBER_MEMBER_idx` (`PARENT_ID`),
  KEY `FK_MEMBER_MEMBER_GROUP_idx` (`MEMBER_GROUP_ID`),
  CONSTRAINT `FK_MEMBER_MEMBER` FOREIGN KEY (`PARENT_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MEMBER_MEMBER_GROUP` FOREIGN KEY (`MEMBER_GROUP_ID`) REFERENCES `member_group` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('150270323164192','test','05a671c66aefea124cc08b76ea6d30bb','2017-08-14 09:33:51','2017-08-14 09:33:51','2017-08-14 09:33:51','2017-08-14 09:33:51','','13926205227',0,NULL,NULL,NULL,'images/uassets/2017/8/14/1502703220376.jpg','13926205227',NULL,NULL,NULL,'13926205227',NULL,NULL,9,NULL,NULL,NULL,NULL,NULL,NULL);
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
  `DEPOSIT` double DEFAULT NULL COMMENT '存款金额',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_account`
--

LOCK TABLES `member_account` WRITE;
/*!40000 ALTER TABLE `member_account` DISABLE KEYS */;
INSERT INTO `member_account` VALUES ('150268445155524','2017-08-14 04:20:59','2017-08-14 04:20:59',0,NULL,NULL,1,NULL,'150268445155524',0,NULL,NULL),('150270323164192','2017-08-14 09:33:57','2017-08-14 13:25:20',0,'21','ewr',2,NULL,'150270323164192',1,12,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='存款流水表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_account_his`
--

LOCK TABLES `member_account_his` WRITE;
/*!40000 ALTER TABLE `member_account_his` DISABLE KEYS */;
INSERT INTO `member_account_his` VALUES (12,NULL,NULL,'150270593791613','2017-08-14 10:19:02','2017-08-14 10:19:02',0,'21','ewr',1,NULL,'150270323164192'),(12,NULL,NULL,'150271712110684','2017-08-14 13:25:21','2017-08-14 13:25:21',0,'21','ewr',2,NULL,'150270323164192'),(12,NULL,NULL,'150271713919912','2017-08-14 13:25:39','2017-08-14 13:25:39',0,'21','ewr',3,NULL,'150270323164192'),(12,NULL,NULL,'150272132289310','2017-08-14 14:35:23','2017-08-14 14:35:23',0,'21','ewr',4,NULL,'150270323164192');
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
INSERT INTO `member_play_account` VALUES ('150270323164192',NULL,'2017-08-14 09:33:57','2017-08-14 09:33:57',0,NULL,'150270323164192');
/*!40000 ALTER TABLE `member_play_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_play_his_account`
--

DROP TABLE IF EXISTS `member_play_his_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_play_his_account` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(120) DEFAULT NULL COMMENT '用户名',
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `DISABLED` tinyint(1) DEFAULT '0' COMMENT '是否禁用',
  `ZHIHUIDOU_COUNT` int(11) DEFAULT NULL COMMENT '智慧豆数量',
  `MEMBER_ID` varchar(32) DEFAULT NULL COMMENT '会员外键',
  PRIMARY KEY (`ID`),
  KEY `FK_MPAHIS_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MPAHIS_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员智惠豆帐号历史记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_play_his_account`
--

LOCK TABLES `member_play_his_account` WRITE;
/*!40000 ALTER TABLE `member_play_his_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_play_his_account` ENABLE KEYS */;
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
  `TRANSPORT_FEE` double DEFAULT NULL COMMENT '运费',
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
  KEY `order_delivery_order_trade_idx` (`ORDER_ID`),
  CONSTRAINT `order_delivery_order_trade` FOREIGN KEY (`ORDER_ID`) REFERENCES `order_trade` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `order_trade`
--

DROP TABLE IF EXISTS `order_trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_trade` (
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
  `TRANSPORT_FEE` double DEFAULT NULL COMMENT '运费',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `FK_ORDER_SHOP_idx` (`SHOP_ID`),
  KEY `FK_ORDER_PRODUCT_idx` (`PRODUCT_ID`),
  KEY `FK_ORDER_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_ORDER_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ORDER_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_trade`
--

LOCK TABLES `order_trade` WRITE;
/*!40000 ALTER TABLE `order_trade` DISABLE KEYS */;
INSERT INTO `order_trade` VALUES ('15023552813846',NULL,NULL,NULL,'2017-08-10 08:54:41','2017-08-10 08:54:41',0,NULL,NULL,2,0,'wee','eew',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('15023558717273',NULL,NULL,NULL,'2017-08-10 09:04:31','2017-08-10 09:04:31',0,NULL,NULL,3,0,'asd','das',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL);
/*!40000 ALTER TABLE `order_trade` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_category`
--

LOCK TABLES `pr_category` WRITE;
/*!40000 ALTER TABLE `pr_category` DISABLE KEYS */;
INSERT INTO `pr_category` VALUES ('149879109000689',NULL,NULL,NULL,'2017-06-30 02:51:41','2017-08-12 12:29:02',0,NULL,NULL,3,0,'clothes','服饰','easd',NULL,'','images/uassets/2017/8/12/1502540916163.jpg',NULL,NULL,'fushi'),('149879256868151',NULL,NULL,NULL,'2017-06-30 03:16:08','2017-08-12 12:32:27',0,NULL,NULL,5,0,'wewe','we','',NULL,'','images/uassets/2017/8/12/1502541145906.jpg',NULL,NULL,''),('149879295537938',NULL,NULL,NULL,'2017-06-30 03:22:35','2017-06-30 03:22:35',0,NULL,NULL,7,0,'wwr','rrw',NULL,'149879109000689','149879109000689',NULL,NULL,NULL,NULL),('150234883909111',NULL,NULL,NULL,'2017-08-10 07:07:19','2017-08-10 07:51:04',0,NULL,NULL,11,0,NULL,'篮球','dsfdsfsdf放松放松方式是方法',NULL,NULL,'images/uassets/2017/8/10/1502348837446.COM.gif',NULL,NULL,'basketball');
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
  CONSTRAINT `FK_PRODUCT_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product`
--

LOCK TABLES `pr_product` WRITE;
/*!40000 ALTER TABLE `pr_product` DISABLE KEYS */;
INSERT INTO `pr_product` VALUES ('150324208842652',NULL,NULL,NULL,'2017-08-20 15:16:19','2017-08-20 16:33:53',1,NULL,NULL,14,0,NULL,'test','test','',NULL,NULL,'test','149879109000689','&lt;p&gt;发发&lt;/p&gt;',NULL,0,1,NULL,NULL,1,NULL,NULL,30,34,NULL,NULL);
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
  `PROPERTY_ID` varchar(32) DEFAULT NULL COMMENT '属性ID',
  `PROPERTY_VAlUE_ID` varchar(330) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PACKAGE_PRICE_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_PACKAGE_PRICE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品有不同的属性，这个时候不同的规格，大小，颜色，可能产生不同价格，比如苹果手机白色的比黑色的要贵，这个时候需要一个表来记录不同属性产生的价格，';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product_package_price`
--

LOCK TABLES `pr_product_package_price` WRITE;
/*!40000 ALTER TABLE `pr_product_package_price` DISABLE KEYS */;
INSERT INTO `pr_product_package_price` VALUES ('150324679482727',NULL,43,'150324208842652','34',NULL,'150324678473529_150324679246153'),('150324679482744',NULL,43,'150324208842652','34',NULL,'150324678473529_150324679482674'),('15032467948278',NULL,34,'150324208842652','34',NULL,'150324678244661_150324679482674'),('150324679482789',NULL,34,'150324208842652','443',NULL,'150324678244661_150324679246153');
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
INSERT INTO `pr_product_property` VALUES ('15028412955799','款式','style','',NULL),('150284145896292','规格','specification','',NULL),('150284148370969','尺寸','size','',NULL),('150284150590025','颜色','color','',NULL),('15028415409685','容量','capacity','',NULL),('150284156334250','套餐','combo','',NULL),('150284157674586','其它','other','',NULL);
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
  `PRODUCT_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PRO_VALUE_PROPERTY_idx` (`PROPERTY_ID`),
  KEY `FK_PRO_VALUE_PRODUCT_idx` (`PRODUCT_ID`),
  CONSTRAINT `FK_PRO_VALUE_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PRO_VALUE_PROPERTY` FOREIGN KEY (`PROPERTY_ID`) REFERENCES `pr_product_property` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品属性值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product_property_value`
--

LOCK TABLES `pr_product_property_value` WRITE;
/*!40000 ALTER TABLE `pr_product_property_value` DISABLE KEYS */;
INSERT INTO `pr_product_property_value` VALUES ('150324678244661','qq',NULL,NULL,'15028412955799',NULL,0,'150324208842652'),('150324678473529','ww',NULL,NULL,'15028412955799',NULL,0,'150324208842652'),('150324679246153','wqr',NULL,NULL,'150284145896292',NULL,0,'150324208842652'),('150324679482674','trrt',NULL,NULL,'150284145896292',NULL,0,'150324208842652');
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='店铺';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES ('15023386847563',NULL,NULL,NULL,'2017-08-10 04:18:04','2017-08-10 04:30:49',1,NULL,NULL,16,0,NULL,'test',NULL,'',NULL,NULL,'test',NULL,'<p>test的士速递收拾收拾</p>',NULL,NULL,NULL,NULL,NULL),('150233870984044',NULL,NULL,NULL,'2017-08-10 04:18:29','2017-08-10 04:18:29',1,NULL,NULL,17,0,NULL,'sdfasfa',NULL,'',NULL,NULL,'sdfasd',NULL,'<p>sdfasdad</p>',NULL,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='店铺分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_category`
--

LOCK TABLES `shop_category` WRITE;
/*!40000 ALTER TABLE `shop_category` DISABLE KEYS */;
INSERT INTO `shop_category` VALUES ('150234567614056',NULL,NULL,NULL,'2017-08-10 06:14:36','2017-08-12 09:38:43',0,NULL,NULL,27,0,NULL,'123','sasasa','150234567614056',NULL,'images/uassets/2017/8/12/1502530153474.jpg',NULL,NULL,'123'),('150234567908665',NULL,NULL,NULL,'2017-08-10 06:14:39','2017-08-12 09:31:09',0,NULL,NULL,28,0,NULL,'as','sasasa','150234567614056',NULL,'images/uassets/2017/8/12/1502530266424.jpg',NULL,NULL,'as'),('150252980535768',NULL,NULL,NULL,'2017-08-12 09:23:25','2017-08-12 09:53:34',0,NULL,NULL,32,0,NULL,'我的店铺','testdsdsds',NULL,NULL,'images/uassets/2017/8/12/1502529790774.jpg',NULL,NULL,'myshopCategory'),('15025312782676',NULL,NULL,NULL,'2017-08-12 09:47:58','2017-08-12 09:48:20',0,NULL,NULL,34,0,NULL,'sdf','afsfa',NULL,NULL,'images/uassets/2017/8/12/1502531298140.jpg',NULL,NULL,'sdaf');
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
INSERT INTO `tb_resources` VALUES ('150183915978716','资源管理',NULL,NULL,'2017-08-04 09:32:40','2017-08-12 03:06:15',NULL,NULL,'','system:resources:view','resources',NULL,'menu',NULL,NULL,'resourcesManage',NULL),('15018393878145','资源管理-新增','150183915978716','150183915978716','2017-08-04 09:36:28','2017-08-04 09:36:28',NULL,NULL,NULL,'system:resources:create',NULL,NULL,'button',NULL,NULL,'resourcesManageCreate',NULL),('150183945124351','资源管理-编辑','150183915978716','150183915978716','2017-08-04 09:37:31','2017-08-04 09:37:31',NULL,NULL,NULL,'system:resources:edit',NULL,NULL,'button',NULL,NULL,'resourcesManageEdit',NULL),('150198229992573','会员',NULL,NULL,'2017-08-06 01:18:20','2017-08-06 01:18:20',NULL,NULL,NULL,'member:member:view',NULL,NULL,'menu',NULL,NULL,NULL,NULL),('150251102697652','会员-新增','150198229992573',NULL,'2017-08-12 04:10:26','2017-08-12 08:44:50',NULL,NULL,'','member:member:create','',NULL,'menu',NULL,NULL,'memberCreate',NULL),('150251104916735','ew','150198229992573',NULL,'2017-08-12 04:10:49','2017-08-12 08:10:29',NULL,NULL,'we','ew','we',NULL,'menu',NULL,NULL,'ewwe',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES ('150183669467347','超级管理员','2017-08-04 08:51:34','2017-08-08 02:34:52',0,NULL,NULL,1,NULL,'adminRole'),('150236810620045','123','2017-08-10 12:28:26','2017-08-14 02:48:02',0,NULL,NULL,2,NULL,'123wr');
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
INSERT INTO `tb_role_resources` VALUES ('150184254929024','150183915978716','150183669467347'),('150184254929119','150183945124351','150183669467347'),('150184254929132','15018393878145','150183669467347'),('150267749731219','150251104916735','150236810620045'),('150267749731230','150251102697652','150236810620045'),('150267749731255','15018393878145','150236810620045'),('150267749731275','150198229992573','150236810620045'),('150267749731278','150183915978716','150236810620045'),('150267749731299','150183945124351','150236810620045');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('150181922197730','黄记新','e1e4f718b04d5467d4fda0299328176c','2017-08-11 11:10:02',NULL,'2017-08-11 11:10:02',NULL,'517714860@qq.com','13926205227',0,NULL,NULL,NULL,'images/uassets/2017/8/11/1502449786887.jpg','fwfwfdaaassa',NULL,NULL,NULL,'13926205227',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'150236982949469',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('15018198221054','yangmiaoqing','6823d04d06ac25e4afde8d09de6751dc','2017-08-12 23:40:57','2017-08-04 04:10:21','2017-08-12 23:40:57',NULL,'yangmiaoqing@163.com','sssss',0,NULL,NULL,NULL,'images/uassets/2017/8/11/1502449706546.jpg','dsfsdfsdfsd',NULL,NULL,NULL,'wechat','杨苗青',NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,'150183679493710',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('150209629806810','yangmiaoqing','6823d04d06ac25e4afde8d09de6751dc','2017-08-07 08:58:18','2017-08-07 08:58:18','2017-08-07 08:58:18','2017-08-07 08:58:18',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'杨苗青',NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150236436870093','黄洋洋',NULL,'2017-08-10 11:26:08','2017-08-10 11:26:08','2017-08-10 11:26:08','2017-08-10 11:26:08','','123',1,NULL,NULL,NULL,'images/uassets/2017/8/10/1502364358603.jpg','额问问',NULL,NULL,NULL,'123',NULL,NULL,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150236449207268','请问',NULL,'2017-08-10 11:28:12','2017-08-10 11:28:12','2017-08-10 11:28:12','2017-08-10 11:28:12','我期待','',1,NULL,NULL,NULL,'','',NULL,NULL,NULL,'',NULL,NULL,6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150236480764471','we',NULL,'2017-08-10 11:33:28','2017-08-10 11:33:28','2017-08-10 11:33:28','2017-08-10 11:33:28','ew','ew',1,NULL,NULL,NULL,'','we',NULL,NULL,NULL,'we',NULL,NULL,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150236496279964','ww',NULL,'2017-08-10 11:36:05','2017-08-10 11:36:05','2017-08-10 11:36:05','2017-08-10 11:36:05','ww','',1,NULL,NULL,NULL,'','w',NULL,NULL,NULL,'',NULL,NULL,8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150236503988156','ew',NULL,'2017-08-10 11:37:19','2017-08-10 11:37:19','2017-08-10 11:37:19','2017-08-10 11:37:19','ew','ew',1,NULL,NULL,NULL,'','ew',NULL,NULL,NULL,'',NULL,NULL,9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150243694831683','12',NULL,'2017-08-11 07:35:49','2017-08-11 07:35:49','2017-08-11 07:35:49','2017-08-11 07:35:49','21','21',1,NULL,NULL,NULL,'','12',NULL,NULL,NULL,'',NULL,NULL,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation'),('150244520568287',NULL,NULL,'2017-08-11 09:53:25','2017-08-11 09:53:25','2017-08-11 09:53:25','2017-08-11 09:53:25',NULL,NULL,1,NULL,NULL,NULL,'','2332',NULL,NULL,NULL,NULL,NULL,NULL,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation');
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
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='用户附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_assets`
--

LOCK TABLES `tb_user_assets` WRITE;
/*!40000 ALTER TABLE `tb_user_assets` DISABLE KEYS */;
INSERT INTO `tb_user_assets` VALUES ('150192413505221',NULL,'1501924135045.png',NULL,'2017-08-05 09:08:56','2017-08-05 09:08:56',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501924135045.png',NULL,NULL,NULL,NULL,NULL,1,'userAssets/\\2017/8/5/1501924135045.png',NULL),('15019241368541',NULL,'1501924136847.jpg',NULL,'2017-08-05 09:08:56','2017-08-05 09:08:56',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501924136847.jpg',NULL,NULL,NULL,NULL,NULL,2,'userAssets/\\2017/8/5/1501924136847.jpg',NULL),('150192474307979',NULL,'1501924743023.jpg',NULL,'2017-08-05 09:19:03','2017-08-05 09:19:03',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501924743023.jpg',NULL,NULL,NULL,NULL,NULL,3,'userAssets/2017/8/5/1501924743023.jpg',NULL),('150192640000137',NULL,'1501926399980.jpg',NULL,'2017-08-05 09:46:40','2017-08-05 09:46:40',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501926399980.jpg',NULL,NULL,NULL,NULL,NULL,4,'uassets/2017/8/5/1501926399980.jpg',NULL),('150192640009937',NULL,'1501926400094.jpg',NULL,'2017-08-05 09:46:40','2017-08-05 09:46:40',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501926400094.jpg',NULL,NULL,NULL,NULL,NULL,5,'uassets/2017/8/5/1501926400094.jpg',NULL),('150192655170447',NULL,'1501926551673.jpg',NULL,'2017-08-05 09:49:11','2017-08-05 09:49:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501926551673.jpg',NULL,NULL,NULL,NULL,NULL,6,'images/uassets/2017/8/5/1501926551673.jpg',NULL),('150192655181124',NULL,'1501926551805.jpg',NULL,'2017-08-05 09:49:11','2017-08-05 09:49:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501926551805.jpg',NULL,NULL,NULL,NULL,NULL,7,'images/uassets/2017/8/5/1501926551805.jpg',NULL),('150193785152452',NULL,'1501937851507.jpg',NULL,'2017-08-05 12:57:34','2017-08-05 12:57:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501937851507.jpg',NULL,NULL,NULL,NULL,NULL,8,'images/uassets/2017/8/5/1501937851507.jpg',NULL),('150193794371878',NULL,'1501937943709.jpg',NULL,'2017-08-05 12:59:03','2017-08-05 12:59:03',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\5\\1501937943709.jpg',NULL,NULL,NULL,NULL,NULL,9,'images/uassets/2017/8/5/1501937943709.jpg',NULL),('150193798868715',NULL,'1501937988683.jpg',NULL,'2017-08-05 12:59:48','2017-08-05 12:59:48',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\5\\1501937988683.jpg',NULL,NULL,NULL,NULL,NULL,10,'images/uassets/2017/8/5/1501937988683.jpg',NULL),('150194046403243',NULL,'1501940464020.jpg',NULL,'2017-08-05 13:41:06','2017-08-05 13:41:06',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\5\\1501940464020.jpg',NULL,NULL,NULL,NULL,NULL,11,'images/uassets/2017/8/5/1501940464020.jpg',NULL),('150194051716486',NULL,'1501940517098.jpg',NULL,'2017-08-05 13:41:57','2017-08-05 13:41:57',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501940517098.jpg',NULL,NULL,NULL,NULL,NULL,12,'images/uassets/2017/8/5/1501940517098.jpg',NULL),('150194057301019',NULL,'1501940573002.jpg',NULL,'2017-08-05 13:42:53','2017-08-05 13:42:53',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501940573002.jpg',NULL,NULL,NULL,NULL,NULL,13,'images/uassets/2017/8/5/1501940573002.jpg',NULL),('150194061426168',NULL,'1501940614251.jpg',NULL,'2017-08-05 13:43:34','2017-08-05 13:43:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501940614251.jpg',NULL,NULL,NULL,NULL,NULL,14,'images/uassets/2017/8/5/1501940614251.jpg',NULL),('150223603894197',NULL,'1502236038922.jpg',NULL,'2017-08-08 23:47:22','2017-08-08 23:47:22',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\9\\1502236038922.jpg',NULL,NULL,NULL,NULL,NULL,15,'images/uassets/2017/8/9/1502236038922.jpg',NULL),('150223675330225',NULL,'1502236753286.jpg',NULL,'2017-08-08 23:59:13','2017-08-08 23:59:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502236753286.jpg',NULL,NULL,NULL,NULL,NULL,16,'images/uassets/2017/8/9/1502236753286.jpg',NULL),('150223717785159',NULL,'1502237177847.jpg',NULL,'2017-08-09 00:06:17','2017-08-09 00:06:17',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502237177847.jpg',NULL,NULL,NULL,NULL,NULL,17,'images/uassets/2017/8/9/1502237177847.jpg',NULL),('150224297174312',NULL,'1502242971734.jpg',NULL,'2017-08-09 01:42:54','2017-08-09 01:42:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502242971734.jpg',NULL,NULL,NULL,NULL,NULL,18,'images/uassets/2017/8/9/1502242971734.jpg',NULL),('150224319740092',NULL,'1502243197394.jpg',NULL,'2017-08-09 01:46:37','2017-08-09 01:46:37',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502243197394.jpg',NULL,NULL,NULL,NULL,NULL,19,'images/uassets/2017/8/9/1502243197394.jpg',NULL),('15022436102652',NULL,'1502243610260.ch5.cc.jpg',NULL,'2017-08-09 01:53:30','2017-08-09 01:53:30',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502243610260.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,20,'images/uassets/2017/8/9/1502243610260.ch5.cc.jpg',NULL),('150224408063250',NULL,'1502244080627.ch5.cc.jpg',NULL,'2017-08-09 02:01:20','2017-08-09 02:01:20',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244080627.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,21,'images/uassets/2017/8/9/1502244080627.ch5.cc.jpg',NULL),('150224414529638',NULL,'1502244145290.COM.gif',NULL,'2017-08-09 02:02:25','2017-08-09 02:02:25',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244145290.COM.gif',NULL,NULL,NULL,NULL,NULL,22,'images/uassets/2017/8/9/1502244145290.COM.gif',NULL),('150224436832148',NULL,'1502244368314.COM.jpg',NULL,'2017-08-09 02:06:08','2017-08-09 02:06:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244368314.COM.jpg',NULL,NULL,NULL,NULL,NULL,23,'images/uassets/2017/8/9/1502244368314.COM.jpg',NULL),('15022444956228',NULL,'1502244495617.COM.jpg',NULL,'2017-08-09 02:08:15','2017-08-09 02:08:15',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244495617.COM.jpg',NULL,NULL,NULL,NULL,NULL,24,'images/uassets/2017/8/9/1502244495617.COM.jpg',NULL),('150224493257821',NULL,'1502244932572.COM.jpg',NULL,'2017-08-09 02:15:32','2017-08-09 02:15:32',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244932572.COM.jpg',NULL,NULL,NULL,NULL,NULL,25,'images/uassets/2017/8/9/1502244932572.COM.jpg',NULL),('150224497542966',NULL,'1502244975424.COM.jpg',NULL,'2017-08-09 02:16:15','2017-08-09 02:16:15',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244975424.COM.jpg',NULL,NULL,NULL,NULL,NULL,26,'images/uassets/2017/8/9/1502244975424.COM.jpg',NULL),('150224534737462',NULL,'1502245347364.COM.jpg',NULL,'2017-08-09 02:22:27','2017-08-09 02:22:27',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245347364.COM.jpg',NULL,NULL,NULL,NULL,NULL,27,'images/uassets/2017/8/9/1502245347364.COM.jpg',NULL),('150224542696440',NULL,'1502245426960.COM.jpg',NULL,'2017-08-09 02:23:46','2017-08-09 02:23:46',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245426960.COM.jpg',NULL,NULL,NULL,NULL,NULL,28,'images/uassets/2017/8/9/1502245426960.COM.jpg',NULL),('150224570263796',NULL,'1502245702631.COM.jpg',NULL,'2017-08-09 02:28:22','2017-08-09 02:28:22',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245702631.COM.jpg',NULL,NULL,NULL,NULL,NULL,29,'images/uassets/2017/8/9/1502245702631.COM.jpg',NULL),('150224580621076',NULL,'1502245806205.COM.jpg',NULL,'2017-08-09 02:30:06','2017-08-09 02:30:06',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245806205.COM.jpg',NULL,NULL,NULL,NULL,NULL,30,'images/uassets/2017/8/9/1502245806205.COM.jpg',NULL),('150224581469646',NULL,'1502245814690.ch5.cc.jpg',NULL,'2017-08-09 02:30:14','2017-08-09 02:30:14',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245814690.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,31,'images/uassets/2017/8/9/1502245814690.ch5.cc.jpg',NULL),('150224601486773',NULL,'1502246014813.ch5.cc.jpg',NULL,'2017-08-09 02:33:34','2017-08-09 02:33:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502246014813.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,32,'images/uassets/2017/8/9/1502246014813.ch5.cc.jpg',NULL),('150224603783250',NULL,'1502246037828.png',NULL,'2017-08-09 02:33:57','2017-08-09 02:33:57',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502246037828.png',NULL,NULL,NULL,NULL,NULL,33,'images/uassets/2017/8/9/1502246037828.png',NULL),('150224605331354',NULL,'1502246053309.png',NULL,'2017-08-09 02:34:13','2017-08-09 02:34:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502246053309.png',NULL,NULL,NULL,NULL,NULL,34,'images/uassets/2017/8/9/1502246053309.png',NULL),('150224833424824',NULL,'1502248334244.com~自由な成人映画、最も速く&最新ポルノ映画サイト.jpg',NULL,'2017-08-09 03:12:14','2017-08-09 03:12:14',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248334244.com~自由な成人映画、最も速く&最新ポルノ映画サイト.jpg',NULL,NULL,NULL,NULL,NULL,35,'images/uassets/2017/8/9/1502248334244.com~自由な成人映画、最も速く&最新ポルノ映画サイト.jpg',NULL),('150224834418382',NULL,'1502248344179.COM.gif',NULL,'2017-08-09 03:12:24','2017-08-09 03:12:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248344179.COM.gif',NULL,NULL,NULL,NULL,NULL,36,'images/uassets/2017/8/9/1502248344179.COM.gif',NULL),('150224835817145',NULL,'1502248358167.COM.jpg',NULL,'2017-08-09 03:12:38','2017-08-09 03:12:38',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248358167.COM.jpg',NULL,NULL,NULL,NULL,NULL,37,'images/uassets/2017/8/9/1502248358167.COM.jpg',NULL),('150224879545698',NULL,'1502248795389.COM.jpg',NULL,'2017-08-09 03:19:55','2017-08-09 03:19:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248795389.COM.jpg',NULL,NULL,NULL,NULL,NULL,38,'images/uassets/2017/8/9/1502248795389.COM.jpg',NULL),('15022499902346',NULL,'1502249990210.COM.jpg',NULL,'2017-08-09 03:39:50','2017-08-09 03:39:50',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502249990210.COM.jpg',NULL,NULL,NULL,NULL,NULL,39,'images/uassets/2017/8/9/1502249990210.COM.jpg',NULL),('150225144561859',NULL,'1502251445612.ch5.cc.jpg',NULL,'2017-08-09 04:04:08','2017-08-09 04:04:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502251445612.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,40,'images/uassets/2017/8/9/1502251445612.ch5.cc.jpg',NULL),('150225956087211',NULL,'1502259560865.COM.jpg',NULL,'2017-08-09 06:19:22','2017-08-09 06:19:22',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502259560865.COM.jpg',NULL,NULL,NULL,NULL,NULL,41,'images/uassets/2017/8/9/1502259560865.COM.jpg',NULL),('150232955909379',NULL,'1502329559049.ch5.cc.jpg',NULL,'2017-08-10 01:45:59','2017-08-10 01:45:59',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502329559049.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,42,'images/uassets/2017/8/10/1502329559049.ch5.cc.jpg',NULL),('150233171072720',NULL,'1502331710721.COM.jpg',NULL,'2017-08-10 02:21:51','2017-08-10 02:21:51',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502331710721.COM.jpg',NULL,NULL,NULL,NULL,NULL,43,'images/uassets/2017/8/10/1502331710721.COM.jpg',NULL),('150233187532978',NULL,'1502331875325.COM.jpg',NULL,'2017-08-10 02:24:35','2017-08-10 02:24:35',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502331875325.COM.jpg',NULL,NULL,NULL,NULL,NULL,44,'images/uassets/2017/8/10/1502331875325.COM.jpg',NULL),('150233199304378',NULL,'1502331993039.COM.jpg',NULL,'2017-08-10 02:26:33','2017-08-10 02:26:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502331993039.COM.jpg',NULL,NULL,NULL,NULL,NULL,45,'images/uassets/2017/8/10/1502331993039.COM.jpg',NULL),('150233228618160',NULL,'1502332286158.COM.jpg',NULL,'2017-08-10 02:31:26','2017-08-10 02:31:26',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502332286158.COM.jpg',NULL,NULL,NULL,NULL,NULL,46,'images/uassets/2017/8/10/1502332286158.COM.jpg',NULL),('150233284529953',NULL,'1502332845274.COM.jpg',NULL,'2017-08-10 02:40:45','2017-08-10 02:40:45',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502332845274.COM.jpg',NULL,NULL,NULL,NULL,NULL,47,'images/uassets/2017/8/10/1502332845274.COM.jpg',NULL),('150233323089450',NULL,'1502333230888.COM.jpg',NULL,'2017-08-10 02:47:10','2017-08-10 02:47:10',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333230888.COM.jpg',NULL,NULL,NULL,NULL,NULL,48,'images/uassets/2017/8/10/1502333230888.COM.jpg',NULL),('15023334681759',NULL,'1502333468169.COM.jpg',NULL,'2017-08-10 02:51:08','2017-08-10 02:51:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333468169.COM.jpg',NULL,NULL,NULL,NULL,NULL,49,'images/uassets/2017/8/10/1502333468169.COM.jpg',NULL),('150233354450478',NULL,'1502333544442.COM.jpg',NULL,'2017-08-10 02:52:24','2017-08-10 02:52:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333544442.COM.jpg',NULL,NULL,NULL,NULL,NULL,50,'images/uassets/2017/8/10/1502333544442.COM.jpg',NULL),('150233388845966',NULL,'1502333888455.COM.jpg',NULL,'2017-08-10 02:58:08','2017-08-10 02:58:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333888455.COM.jpg',NULL,NULL,NULL,NULL,NULL,51,'images/uassets/2017/8/10/1502333888455.COM.jpg',NULL),('150233457915951',NULL,'1502334579030.COM.jpg',NULL,'2017-08-10 03:09:39','2017-08-10 03:09:39',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502334579030.COM.jpg',NULL,NULL,NULL,NULL,NULL,53,'images/uassets/2017/8/10/1502334579030.COM.jpg',NULL),('150233499465914',NULL,'1502334994652.COM.jpg',NULL,'2017-08-10 03:16:35','2017-08-10 03:16:35',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502334994652.COM.jpg',NULL,NULL,NULL,NULL,NULL,54,'images/uassets/2017/8/10/1502334994652.COM.jpg',NULL),('150233512833591',NULL,'1502335128330.COM.jpg',NULL,'2017-08-10 03:18:48','2017-08-10 03:18:48',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502335128330.COM.jpg',NULL,NULL,NULL,NULL,NULL,55,'images/uassets/2017/8/10/1502335128330.COM.jpg',NULL),('150233598271260',NULL,'1502335982656.COM.jpg',NULL,'2017-08-10 03:33:02','2017-08-10 03:33:02',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502335982656.COM.jpg',NULL,NULL,NULL,NULL,NULL,56,'images/uassets/2017/8/10/1502335982656.COM.jpg',NULL),('150233761979843',NULL,'1502337619793.ch5.cc.jpg',NULL,'2017-08-10 04:00:19','2017-08-10 04:00:19',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337619793.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,57,'images/uassets/2017/8/10/1502337619793.ch5.cc.jpg',NULL),('150233768403072',NULL,'1502337684024.COM.jpg',NULL,'2017-08-10 04:01:24','2017-08-10 04:01:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337684024.COM.jpg',NULL,NULL,NULL,NULL,NULL,58,'images/uassets/2017/8/10/1502337684024.COM.jpg',NULL),('150233782103077',NULL,'1502337821025.COM.gif',NULL,'2017-08-10 04:03:41','2017-08-10 04:03:41',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337821025.COM.gif',NULL,NULL,NULL,NULL,NULL,59,'images/uassets/2017/8/10/1502337821025.COM.gif',NULL),('150233793754245',NULL,'1502337937537.COM.gif',NULL,'2017-08-10 04:05:37','2017-08-10 04:05:37',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337937537.COM.gif',NULL,NULL,NULL,NULL,NULL,60,'images/uassets/2017/8/10/1502337937537.COM.gif',NULL),('150233838791728',NULL,'1502338387910.COM.jpg',NULL,'2017-08-10 04:13:10','2017-08-10 04:13:10',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502338387910.COM.jpg',NULL,NULL,NULL,NULL,NULL,61,'images/uassets/2017/8/10/1502338387910.COM.jpg',NULL),('150233976693339',NULL,'1502339766912.COM.gif',NULL,'2017-08-10 04:36:07','2017-08-10 04:36:07',1,NULL,NULL,'E:\\软件\\wildfly-10.1.0.Final\\standalone\\deployments\\zhiwo-system-web.warimages\\uassets\\2017\\8\\10\\1502339766912.COM.gif',NULL,NULL,NULL,NULL,NULL,62,'images/uassets/2017/8/10/1502339766912.COM.gif',NULL),('150234702398617',NULL,'1502347023963.COM.gif',NULL,'2017-08-10 06:37:04','2017-08-10 06:37:04',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347023963.COM.gif',NULL,NULL,NULL,NULL,NULL,63,'images/uassets/2017/8/10/1502347023963.COM.gif',NULL),('150234710893628',NULL,'1502347108866.COM.gif',NULL,'2017-08-10 06:38:28','2017-08-10 06:38:28',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347108866.COM.gif',NULL,NULL,NULL,NULL,NULL,64,'images/uassets/2017/8/10/1502347108866.COM.gif',NULL),('150234716514832',NULL,'1502347165098.COM.gif',NULL,'2017-08-10 06:39:25','2017-08-10 06:39:25',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347165098.COM.gif',NULL,NULL,NULL,NULL,NULL,65,'images/uassets/2017/8/10/1502347165098.COM.gif',NULL),('150234731679131',NULL,'1502347316731.COM.gif',NULL,'2017-08-10 06:41:56','2017-08-10 06:41:56',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347316731.COM.gif',NULL,NULL,NULL,NULL,NULL,66,'images/uassets/2017/8/10/1502347316731.COM.gif',NULL),('150234756210021',NULL,'1502347562043.COM.gif',NULL,'2017-08-10 06:46:02','2017-08-10 06:46:02',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347562043.COM.gif',NULL,NULL,NULL,NULL,NULL,67,'images/uassets/2017/8/10/1502347562043.COM.gif',NULL),('150234851548797',NULL,'1502348515483.COM.gif',NULL,'2017-08-10 07:01:55','2017-08-10 07:01:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502348515483.COM.gif',NULL,NULL,NULL,NULL,NULL,68,'images/uassets/2017/8/10/1502348515483.COM.gif',NULL),('150234883745072',NULL,'1502348837446.COM.gif',NULL,'2017-08-10 07:07:17','2017-08-10 07:07:17',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502348837446.COM.gif',NULL,NULL,NULL,NULL,NULL,69,'images/uassets/2017/8/10/1502348837446.COM.gif',NULL),('150235149273954',NULL,'1502351492732.COM.jpg',NULL,'2017-08-10 07:51:33','2017-08-10 07:51:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502351492732.COM.jpg',NULL,NULL,NULL,NULL,NULL,70,'images/uassets/2017/8/10/1502351492732.COM.jpg',NULL),('150235829247058',NULL,'1502358292463.JPG',NULL,'2017-08-10 09:44:52','2017-08-10 09:44:52',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502358292463.JPG',NULL,NULL,NULL,NULL,NULL,71,'images/uassets/2017/8/10/1502358292463.JPG',NULL),('150235913561222',NULL,'1502359135606.jpg',NULL,'2017-08-10 09:58:55','2017-08-10 09:58:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502359135606.jpg',NULL,NULL,NULL,NULL,NULL,72,'images/uassets/2017/8/10/1502359135606.jpg',NULL),('150236435862640',NULL,'1502364358603.jpg',NULL,'2017-08-10 11:25:58','2017-08-10 11:25:58',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502364358603.jpg',NULL,NULL,NULL,NULL,NULL,73,'images/uassets/2017/8/10/1502364358603.jpg',NULL),('150241345781979',NULL,'1502413457729.jpg',NULL,'2017-08-11 01:04:18','2017-08-11 01:04:18',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502413457729.jpg',NULL,NULL,NULL,NULL,NULL,74,'images/uassets/2017/8/11/1502413457729.jpg',NULL),('150241461191046',NULL,'1502414611871.jpg',NULL,'2017-08-11 01:23:31','2017-08-11 01:23:31',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502414611871.jpg',NULL,NULL,NULL,NULL,NULL,75,'images/uassets/2017/8/11/1502414611871.jpg',NULL),('150241467326320',NULL,'1502414673207.jpg',NULL,'2017-08-11 01:24:33','2017-08-11 01:24:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502414673207.jpg',NULL,NULL,NULL,NULL,NULL,76,'images/uassets/2017/8/11/1502414673207.jpg',NULL),('150241999867811',NULL,'1502419998673.jpg',NULL,'2017-08-11 02:53:19','2017-08-11 02:53:19',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502419998673.jpg',NULL,NULL,NULL,NULL,NULL,77,'images/uassets/2017/8/11/1502419998673.jpg',NULL),('150242004220787',NULL,'1502420042192.jpeg',NULL,'2017-08-11 02:54:02','2017-08-11 02:54:02',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502420042192.jpeg',NULL,NULL,NULL,NULL,NULL,78,'images/uassets/2017/8/11/1502420042192.jpeg',NULL),('150242513408087',NULL,'1502425134024.jpg',NULL,'2017-08-11 04:18:54','2017-08-11 04:18:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502425134024.jpg',NULL,NULL,NULL,NULL,NULL,79,'images/uassets/2017/8/11/1502425134024.jpg',NULL),('150243367560912',NULL,'1502433675587.jpg',NULL,'2017-08-11 06:41:15','2017-08-11 06:41:15',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502433675587.jpg',NULL,NULL,NULL,NULL,NULL,80,'images/uassets/2017/8/11/1502433675587.jpg',NULL),('150243519383316',NULL,'1502435193826.jpg',NULL,'2017-08-11 07:06:34','2017-08-11 07:06:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502435193826.jpg',NULL,NULL,NULL,NULL,NULL,81,'images/uassets/2017/8/11/1502435193826.jpg',NULL),('150243744549536',NULL,'1502437445489.jpg',NULL,'2017-08-11 07:44:05','2017-08-11 07:44:05',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502437445489.jpg',NULL,NULL,NULL,NULL,NULL,82,'images/uassets/2017/8/11/1502437445489.jpg',NULL),('150243750765533',NULL,'1502437507627.gif',NULL,'2017-08-11 07:45:07','2017-08-11 07:45:07',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502437507627.gif',NULL,NULL,NULL,NULL,NULL,83,'images/uassets/2017/8/11/1502437507627.gif',NULL),('150244078702481',NULL,'1502440787019.jpg',NULL,'2017-08-11 08:39:47','2017-08-11 08:39:47',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502440787019.jpg',NULL,NULL,NULL,NULL,NULL,84,'images/uassets/2017/8/11/1502440787019.jpg',NULL),('150244970655574',NULL,'1502449706546.jpg',NULL,'2017-08-11 11:08:26','2017-08-11 11:08:26',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502449706546.jpg',NULL,NULL,NULL,NULL,NULL,85,'images/uassets/2017/8/11/1502449706546.jpg',NULL),('150244977893974',NULL,'1502449778933.jpg',NULL,'2017-08-11 11:09:38','2017-08-11 11:09:38',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502449778933.jpg',NULL,NULL,NULL,NULL,NULL,86,'images/uassets/2017/8/11/1502449778933.jpg',NULL),('150244978689351',NULL,'1502449786887.jpg',NULL,'2017-08-11 11:09:46','2017-08-11 11:09:46',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502449786887.jpg',NULL,NULL,NULL,NULL,NULL,87,'images/uassets/2017/8/11/1502449786887.jpg',NULL),('15025292039925',NULL,'1502529203958.jpg',NULL,'2017-08-12 09:13:24','2017-08-12 09:13:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502529203958.jpg',NULL,NULL,NULL,NULL,NULL,88,'images/uassets/2017/8/12/1502529203958.jpg',NULL),('150252979084234',NULL,'1502529790774.jpg',NULL,'2017-08-12 09:23:11','2017-08-12 09:23:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502529790774.jpg',NULL,NULL,NULL,NULL,NULL,89,'images/uassets/2017/8/12/1502529790774.jpg',NULL),('150253015351321',NULL,'1502530153474.jpg',NULL,'2017-08-12 09:29:13','2017-08-12 09:29:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502530153474.jpg',NULL,NULL,NULL,NULL,NULL,90,'images/uassets/2017/8/12/1502530153474.jpg',NULL),('150253020598419',NULL,'1502530205924.jpg',NULL,'2017-08-12 09:30:06','2017-08-12 09:30:06',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502530205924.jpg',NULL,NULL,NULL,NULL,NULL,91,'images/uassets/2017/8/12/1502530205924.jpg',NULL),('150253026642943',NULL,'1502530266424.jpg',NULL,'2017-08-12 09:31:06','2017-08-12 09:31:06',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502530266424.jpg',NULL,NULL,NULL,NULL,NULL,92,'images/uassets/2017/8/12/1502530266424.jpg',NULL),('150253127544349',NULL,'1502531275385.jpg',NULL,'2017-08-12 09:47:55','2017-08-12 09:47:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502531275385.jpg',NULL,NULL,NULL,NULL,NULL,93,'images/uassets/2017/8/12/1502531275385.jpg',NULL),('150253129814561',NULL,'1502531298140.jpg',NULL,'2017-08-12 09:48:18','2017-08-12 09:48:18',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502531298140.jpg',NULL,NULL,NULL,NULL,NULL,94,'images/uassets/2017/8/12/1502531298140.jpg',NULL),('150253599038014',NULL,'1502535990354.jpg',NULL,'2017-08-12 11:06:30','2017-08-12 11:06:30',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502535990354.jpg',NULL,NULL,NULL,NULL,NULL,95,'images/uassets/2017/8/12/1502535990354.jpg',NULL),('150253656671855',NULL,'1502536566713.jpg',NULL,'2017-08-12 11:16:07','2017-08-12 11:16:07',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502536566713.jpg',NULL,NULL,NULL,NULL,NULL,96,'images/uassets/2017/8/12/1502536566713.jpg',NULL),('150254091663445',NULL,'1502540916163.jpg',NULL,'2017-08-12 12:28:36','2017-08-12 12:28:36',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502540916163.jpg',NULL,NULL,NULL,NULL,NULL,97,'images/uassets/2017/8/12/1502540916163.jpg',NULL),('15025411459114',NULL,'1502541145906.jpg',NULL,'2017-08-12 12:32:25','2017-08-12 12:32:25',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502541145906.jpg',NULL,NULL,NULL,NULL,NULL,98,'images/uassets/2017/8/12/1502541145906.jpg',NULL),('150254163378243',NULL,'1502541633776.jpg',NULL,'2017-08-12 12:40:33','2017-08-12 12:40:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502541633776.jpg',NULL,NULL,NULL,NULL,NULL,99,'images/uassets/2017/8/12/1502541633776.jpg',NULL),('150254249406418',NULL,'1502542494009.jpg',NULL,'2017-08-12 12:54:54','2017-08-12 12:54:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502542494009.jpg',NULL,NULL,NULL,NULL,NULL,100,'images/uassets/2017/8/12/1502542494009.jpg',NULL),('150254293183985',NULL,'1502542931834.jpg',NULL,'2017-08-12 13:02:11','2017-08-12 13:02:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502542931834.jpg',NULL,NULL,NULL,NULL,NULL,101,'images/uassets/2017/8/12/1502542931834.jpg',NULL),('150254390099838',NULL,'1502543900991.jpg',NULL,'2017-08-12 13:18:21','2017-08-12 13:18:21',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502543900991.jpg',NULL,NULL,NULL,NULL,NULL,102,'images/uassets/2017/8/12/1502543900991.jpg',NULL),('150268160216529',NULL,'1502681602155.jpg',NULL,'2017-08-14 03:33:22','2017-08-14 03:33:22',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\14\\1502681602155.jpg',NULL,NULL,NULL,NULL,NULL,103,'images/uassets/2017/8/14/1502681602155.jpg',NULL),('150268444857038',NULL,'1502684448564.jpg',NULL,'2017-08-14 04:20:48','2017-08-14 04:20:48',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\14\\1502684448564.jpg',NULL,NULL,NULL,NULL,NULL,104,'images/uassets/2017/8/14/1502684448564.jpg',NULL),('15027032203839',NULL,'1502703220376.jpg',NULL,'2017-08-14 09:33:40','2017-08-14 09:33:40',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\14\\1502703220376.jpg',NULL,NULL,NULL,NULL,NULL,105,'images/uassets/2017/8/14/1502703220376.jpg',NULL),('150310003164596',NULL,'1503100031577.jpg',NULL,'2017-08-18 23:47:12','2017-08-18 23:47:12',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\19\\1503100031577.jpg',NULL,NULL,NULL,NULL,NULL,106,'images/uassets/2017/8/19/1503100031577.jpg',NULL),('150310035591784',NULL,'1503100355911.jpg',NULL,'2017-08-18 23:52:35','2017-08-18 23:52:35',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\19\\1503100355911.jpg',NULL,NULL,NULL,NULL,NULL,107,'images/uassets/2017/8/19/1503100355911.jpg',NULL),('150310473362841',NULL,'1503104733603.jpg',NULL,'2017-08-19 01:05:33','2017-08-19 01:05:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\19\\1503104733603.jpg',NULL,NULL,NULL,NULL,NULL,108,'images/uassets/2017/8/19/1503104733603.jpg',NULL),('15032468671632',NULL,'1503246867136.jpg',NULL,'2017-08-20 16:34:27','2017-08-20 16:34:27',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\21\\1503246867136.jpg',NULL,NULL,NULL,NULL,NULL,109,'images/uassets/2017/8/21/1503246867136.jpg',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_group`
--

LOCK TABLES `tb_user_group` WRITE;
/*!40000 ALTER TABLE `tb_user_group` DISABLE KEYS */;
INSERT INTO `tb_user_group` VALUES ('150183679493710','超级管理员组','2017-08-04 08:53:15','2017-08-08 04:03:58',0,NULL,NULL,1,NULL,'adminGroup'),('150236982949469','weew','2017-08-10 12:57:09','2017-08-10 12:57:09',0,NULL,NULL,4,NULL,'ewew'),('150250019597417','test','2017-08-12 01:09:56','2017-08-12 01:20:37',0,NULL,NULL,9,NULL,'test');
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
INSERT INTO `tb_user_group_role` VALUES ('150183758332847','150183679493710','150183669467347'),('150250083811733','150250019597417','150183669467347'),('15025008381185454','150250019597417','150236810620045');
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
