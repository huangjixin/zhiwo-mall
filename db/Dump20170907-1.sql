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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='会员竞猜分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_category`
--

LOCK TABLES `guess_category` WRITE;
/*!40000 ALTER TABLE `guess_category` DISABLE KEYS */;
INSERT INTO `guess_category` VALUES ('150410531466397',NULL,NULL,NULL,'2017-08-30 15:03:57','2017-08-30 15:03:57',0,NULL,NULL,1,0,NULL,'足球','',NULL,NULL,'',NULL,NULL,'football',NULL),('150410543768324',NULL,NULL,NULL,'2017-08-30 15:04:27','2017-08-30 15:04:27',0,NULL,NULL,2,0,NULL,'篮球','篮球竞猜',NULL,NULL,'',NULL,NULL,'basketball',NULL),('150410546750186',NULL,NULL,NULL,'2017-08-30 15:05:27','2017-08-30 15:05:27',0,NULL,NULL,3,0,NULL,'趣味','趣味竞猜',NULL,NULL,'',NULL,NULL,'fun',NULL),('150410552794635',NULL,NULL,NULL,'2017-08-30 15:07:07','2017-08-30 15:07:07',0,NULL,NULL,4,0,NULL,'财经','',NULL,NULL,'',NULL,NULL,'finance',NULL),('150410562713693',NULL,NULL,NULL,'2017-08-30 15:07:39','2017-08-30 15:07:39',0,NULL,NULL,5,0,NULL,'娱乐','娱乐',NULL,NULL,'',NULL,NULL,'entertainment',NULL),('150410565940326',NULL,NULL,NULL,'2017-08-30 15:07:54','2017-08-30 15:07:54',0,NULL,NULL,6,0,NULL,'其它','其它',NULL,NULL,'',NULL,NULL,'other',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='会员竞猜问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question`
--

LOCK TABLES `guess_question` WRITE;
/*!40000 ALTER TABLE `guess_question` DISABLE KEYS */;
INSERT INTO `guess_question` VALUES ('0fb1290936ff4d089a142c289a779c66',NULL,NULL,NULL,'2017-09-07 12:07:14','2017-09-07 12:07:14',0,NULL,NULL,28,0,NULL,'中超第24轮，上海上港（主队） VS 天津泰达（客队）','','',NULL,NULL,'',NULL,NULL,NULL,'2017-09-09 11:35:00'),('a19e3833319b421780680dd0382ca726',NULL,NULL,NULL,'2017-09-07 11:24:30','2017-09-07 11:41:17',0,NULL,NULL,27,0,NULL,'中超第24轮，广州恒大（主队） VS 长春亚泰（客队）','','',NULL,NULL,'88',NULL,NULL,NULL,'2017-09-08 11:35:00');
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
  CONSTRAINT `FK_guess_question_answer_guess_question` FOREIGN KEY (`question_id`) REFERENCES `guess_question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_guess_question_answer_question_options` FOREIGN KEY (`question_options_id`) REFERENCES `guess_question_options` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `payed` int(11) DEFAULT NULL COMMENT '是否已经支付，默认为0为未结算，为1则已经结算。',
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
  `REAL_QUESTION_ID` varchar(32) DEFAULT NULL COMMENT '正式的外键ID',
  PRIMARY KEY (`ID`),
  KEY `GUESS_QUESTION_IDINDEX` (`GUESS_QUESTION_ID`),
  KEY `FK_guess_question_options_question_idx` (`REAL_QUESTION_ID`),
  CONSTRAINT `FK_guess_question_options_question` FOREIGN KEY (`REAL_QUESTION_ID`) REFERENCES `guess_question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞猜活动备选项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_question_options`
--

LOCK TABLES `guess_question_options` WRITE;
/*!40000 ALTER TABLE `guess_question_options` DISABLE KEYS */;
INSERT INTO `guess_question_options` VALUES ('1','字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个字数不可以超过五十个',1,NULL,NULL),('150478575145255','广州恒大胜',1.26,'a19e3833319b421780680dd0382ca726',NULL),('150478576868480','平局',4.98,'a19e3833319b421780680dd0382ca726',NULL),('150478579205995','长春亚泰胜',9.17,'a19e3833319b421780680dd0382ca726',NULL),('150478591922535','上海上港胜',1.1,'0fb1290936ff4d089a142c289a779c66','0fb1290936ff4d089a142c289a779c66'),('150478594640118','平局',8.15,'0fb1290936ff4d089a142c289a779c66','0fb1290936ff4d089a142c289a779c66'),('150478596959975','天津泰达胜',18.19,'0fb1290936ff4d089a142c289a779c66','0fb1290936ff4d089a142c289a779c66');
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
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `MOBIL_PHONE` varchar(120) DEFAULT NULL COMMENT '手机',
  `STREET` varchar(120) DEFAULT NULL COMMENT '街道',
  `MEMBER_ID` varchar(120) DEFAULT NULL,
  `IS_DEFAULT` varchar(1) DEFAULT '0' COMMENT '是否设置为默认1为是,0为否',
  `DISABLE` varchar(1) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_MEMBER_ADDRESS_MEMBER_idx` (`MEMBER_ID`),
  CONSTRAINT `FK_MEMBER_ADDRESS_MEMBER` FOREIGN KEY (`MEMBER_ID`) REFERENCES `member` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_address`
--

LOCK TABLES `member_address` WRITE;
/*!40000 ALTER TABLE `member_address` DISABLE KEYS */;
INSERT INTO `member_address` VALUES ('0b7e6a2a082e4ba4ade09fc245d95c2a','广东','广州',NULL,'黄记新','13926205227','黄埔区茅岗路和贵新街4巷10号',NULL,'0','0'),('2ab7b9107766402a8e1e0ca652dc9084','广东','广州',NULL,'TEST','TEST','TEST',NULL,'0','0'),('849d5cc75fd64c28946dbd95d16aae8c','北京','东城区',NULL,'12','323223','3232',NULL,'0','0');
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
INSERT INTO `member_play_account` VALUES ('150270323164192',NULL,'2017-08-14 09:33:57','2017-08-31 12:46:26',0,4,'150270323164192');
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
  PRIMARY KEY (`ID`),
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_trade`
--

LOCK TABLES `order_trade` WRITE;
/*!40000 ALTER TABLE `order_trade` DISABLE KEYS */;
INSERT INTO `order_trade` VALUES ('016129e0d5514587a4e84328bfae55eb',NULL,NULL,NULL,'2017-09-07 00:50:32','2017-09-07 00:50:32',0,NULL,NULL,22,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('645061a2de1149ec99333ac5f58ac8f9',NULL,NULL,NULL,'2017-09-07 00:41:29','2017-09-07 00:41:29',0,NULL,NULL,20,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('7014524c66154b46894a7d4fe76116c1',NULL,NULL,NULL,'2017-09-06 23:58:18','2017-09-06 23:58:18',0,NULL,NULL,16,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('a524e0360ede42ab90b9b249831de899',NULL,NULL,NULL,'2017-09-06 23:55:43','2017-09-06 23:55:43',0,NULL,NULL,15,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('d0a42b8159894413918ea803d1b1abb5',NULL,NULL,NULL,'2017-09-07 00:45:24','2017-09-07 00:45:24',0,NULL,NULL,21,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('d5d43128e93c4f99a1657d84058e20c8',NULL,NULL,NULL,'2017-09-07 00:00:17','2017-09-07 00:00:17',0,NULL,NULL,17,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('dbfeb5e0bbd94fccbed9179b77c03a28',NULL,NULL,NULL,'2017-09-07 00:08:15','2017-09-07 00:08:15',0,NULL,NULL,19,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('de03ddbb23434e34bf638f3725b15d84',NULL,NULL,NULL,'2017-09-07 00:01:48','2017-09-07 00:01:48',0,NULL,NULL,18,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('e4127fc6903840f2b563f4cb89b205e5',NULL,NULL,NULL,'2017-09-06 10:29:23','2017-09-06 10:29:23',0,NULL,NULL,13,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL),('f506fcf3503946929e0fdef127f484e2',NULL,NULL,NULL,'2017-09-06 10:35:53','2017-09-06 10:35:53',0,NULL,NULL,14,0,NULL,NULL,'toBePayed','150375229485416','150383670510593',NULL,NULL,1,NULL,NULL,NULL,'000000','申通公司',NULL,0,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_category`
--

LOCK TABLES `pr_category` WRITE;
/*!40000 ALTER TABLE `pr_category` DISABLE KEYS */;
INSERT INTO `pr_category` VALUES ('149879109000689',NULL,NULL,NULL,'2017-06-30 02:51:41','2017-08-30 11:04:57',0,NULL,NULL,3,0,'clothes','服饰','服饰',NULL,'','uassets/2017/8/30/1504091064401.webp',NULL,NULL,'fushi'),('150408382323048',NULL,NULL,NULL,'2017-08-30 09:03:43','2017-08-30 09:03:43',0,NULL,NULL,12,0,NULL,'男装','男装',NULL,NULL,'uassets/2017/8/30/1504083814398.webp',NULL,NULL,'nanzhuang'),('1504085428827',NULL,NULL,NULL,'2017-08-30 09:31:41','2017-08-30 11:37:37',0,NULL,NULL,16,0,NULL,'T恤','T恤','150408382323048',NULL,'uassets/2017/8/30/1504090707199.webp',NULL,NULL,'tixu'),('1504091114549',NULL,NULL,NULL,'2017-08-30 11:09:37','2017-08-30 11:09:37',0,NULL,NULL,17,0,NULL,'美食','美食',NULL,NULL,'uassets/2017/8/30/1504091315778.webp',NULL,NULL,'meishi'),('1504091377795',NULL,NULL,NULL,'2017-08-30 11:10:45','2017-08-30 13:21:38',0,NULL,NULL,18,0,NULL,'日用品','家居',NULL,NULL,'uassets/2017/8/30/1504091443006.webp',NULL,NULL,'riyongpin'),('1504091445504',NULL,NULL,NULL,'2017-08-30 11:11:30','2017-08-30 11:11:30',0,NULL,NULL,19,0,NULL,'纸品湿巾','纸品湿巾','1504091377795',NULL,'uassets/2017/8/30/1504091478076.webp',NULL,NULL,'zhipingshijin'),('1504091490955',NULL,NULL,NULL,'2017-08-30 11:12:07','2017-08-30 11:12:07',0,NULL,NULL,20,0,NULL,'饰品首饰','饰品首饰','1504091377795',NULL,'uassets/2017/8/30/1504091522199.webp',NULL,NULL,'shipinshoushi'),('1504091527135',NULL,NULL,NULL,'2017-08-30 11:12:49','2017-08-30 11:12:49',0,NULL,NULL,21,0,NULL,'餐具水具','餐具水具','1504091377795',NULL,'uassets/2017/8/30/1504091557168.webp',NULL,NULL,'canjushuiju'),('1504091709763',NULL,NULL,NULL,'2017-08-30 11:16:21','2017-08-30 11:16:21',0,NULL,NULL,22,0,NULL,'厨房用品','厨房用品','1504091377795',NULL,'uassets/2017/8/30/1504091775222.webp',NULL,NULL,'chufangyongpin'),('1504091934763',NULL,NULL,NULL,'2017-08-30 11:19:36','2017-08-30 11:19:36',0,NULL,NULL,23,0,NULL,'清洁用具','清洁用具','1504091377795',NULL,'uassets/2017/8/30/1504091973745.webp',NULL,NULL,'qingjieyongju'),('1504092428839',NULL,NULL,NULL,'2017-08-30 11:28:19','2017-08-30 11:28:52',0,NULL,NULL,24,0,NULL,'日用百货','日用百货','1504091377795',NULL,'uassets/2017/8/30/1504092492444.webp',NULL,NULL,'riyongbaihuo'),('1504092596212',NULL,NULL,NULL,'2017-08-30 11:30:50','2017-08-30 11:30:50',0,NULL,NULL,25,0,NULL,'休闲零食','休闲零食','1504091114549',NULL,'uassets/2017/8/30/1504092644961.webp',NULL,NULL,'xiuxianlingshi'),('1504092768188',NULL,NULL,NULL,'2017-08-30 11:34:07','2017-08-30 11:34:07',0,NULL,NULL,26,0,NULL,'营养保健','营养保健','1504091114549',NULL,'uassets/2017/8/30/1504092831809.webp',NULL,NULL,'yingyangbaojian'),('1504092847772',NULL,NULL,NULL,'2017-08-30 11:34:48','2017-08-30 11:34:48',0,NULL,NULL,27,0,NULL,'糕点饼干','糕点饼干','1504091114549',NULL,'uassets/2017/8/30/1504092880728.webp',NULL,NULL,'gaodianbinggan'),('1504092888385',NULL,NULL,NULL,'2017-08-30 11:35:35','2017-08-30 11:35:35',0,NULL,NULL,28,0,NULL,'坚果蜜栈','坚果蜜栈','1504091114549',NULL,'uassets/2017/8/30/1504092930446.webp',NULL,NULL,'jianguomizhan'),('1504092936041',NULL,NULL,NULL,'2017-08-30 11:36:12','2017-08-30 11:36:12',0,NULL,NULL,29,0,NULL,'粮油速食','粮油速食','1504091114549',NULL,'uassets/2017/8/30/1504092966674.webp',NULL,NULL,'liangyousushi'),('1504093070005',NULL,NULL,NULL,'2017-08-30 11:38:36','2017-08-30 11:38:36',0,NULL,NULL,30,0,NULL,'衬衫','衬衫','150408382323048',NULL,'uassets/2017/8/30/1504093110702.webp',NULL,NULL,'chenshan'),('1504093116278',NULL,NULL,NULL,'2017-08-30 11:39:38','2017-08-30 11:39:38',0,NULL,NULL,31,0,NULL,'男装','男装子类','150408382323048',NULL,'uassets/2017/8/30/1504093165113.webp',NULL,NULL,'nanzhuangzilei'),('1504093178176',NULL,NULL,NULL,'2017-08-30 11:40:46','2017-08-30 11:40:46',0,NULL,NULL,32,0,NULL,'套装','套装','150408382323048',NULL,'uassets/2017/8/30/1504093240704.webp',NULL,NULL,'taozhuang'),('1504093246711',NULL,NULL,NULL,'2017-08-30 11:41:23','2017-08-30 11:41:23',0,NULL,NULL,33,0,NULL,'内衣裤袜','内衣裤袜','150408382323048',NULL,'uassets/2017/8/30/1504093282668.webp',NULL,NULL,'neiyikuwa'),('1504093484290',NULL,NULL,NULL,'2017-08-30 11:46:08','2017-08-30 13:13:13',0,NULL,NULL,34,0,NULL,'家居','住宅家居',NULL,NULL,'uassets/2017/8/30/1504093559472.webp',NULL,NULL,'zhuzhaijiaju'),('1504093568256',NULL,NULL,NULL,'2017-08-30 11:46:36','2017-08-30 11:46:36',0,NULL,NULL,35,0,NULL,'被子毯子','被子毯子','1504093484290',NULL,'uassets/2017/8/30/1504093589511.webp',NULL,NULL,'beizitanzi'),('1504093596477',NULL,NULL,NULL,'2017-08-30 11:47:04','2017-08-30 11:47:04',0,NULL,NULL,36,0,NULL,'布艺家饰','布艺家饰','1504093484290',NULL,'uassets/2017/8/30/1504093619958.webp',NULL,NULL,'buyijiashi'),('1504093624461',NULL,NULL,NULL,'2017-08-30 11:47:41','2017-08-30 11:48:25',0,NULL,NULL,37,0,NULL,'床单被套','床单被套','1504093484290',NULL,'uassets/2017/8/30/1504093654420.webp',NULL,NULL,'chuangdanbeitao'),('1504093715778',NULL,NULL,NULL,'2017-08-30 11:49:09','2017-08-30 11:49:09',0,NULL,NULL,38,0,NULL,'床品套件','床品套件','1504093484290',NULL,'uassets/2017/8/30/1504093742321.webp',NULL,NULL,'chuangpintaojian'),('1504093749733',NULL,NULL,NULL,'2017-08-30 11:49:36','2017-08-30 11:49:36',0,NULL,NULL,39,0,NULL,'家装建材','家装建材','1504093484290',NULL,'uassets/2017/8/30/1504093771491.webp',NULL,NULL,'jiazhuangjiancai'),('1504093776997',NULL,NULL,NULL,'2017-08-30 11:50:03','2017-08-30 11:50:03',0,NULL,NULL,40,0,NULL,'凉席蚊帐','凉席蚊帐','1504093484290',NULL,'uassets/2017/8/30/1504093790528.webp',NULL,NULL,'liangxiwenzhang'),('1504093803955',NULL,NULL,NULL,'2017-08-30 11:50:32','2017-08-30 11:50:51',0,NULL,NULL,41,0,NULL,'衣柜鞋柜','衣柜鞋柜','1504093484290',NULL,'uassets/2017/8/30/1504093826522.webp',NULL,NULL,'yiguixiegui'),('1504093892650',NULL,NULL,NULL,'2017-08-30 11:52:13','2017-08-30 11:52:13',0,NULL,NULL,42,0,NULL,'枕头床垫','枕头床垫','1504093484290',NULL,'uassets/2017/8/30/1504093928525.webp',NULL,NULL,'zhentouchuangdian'),('1504093933686',NULL,NULL,NULL,'2017-08-30 11:52:49','2017-08-30 11:52:49',0,NULL,NULL,43,0,NULL,'住宅家居','住宅家居','1504093484290',NULL,'uassets/2017/8/30/1504093950652.webp',NULL,NULL,'zhuzhaijiaju'),('1504094118365',NULL,NULL,NULL,'2017-08-30 11:56:13','2017-08-30 11:56:13',0,NULL,NULL,44,0,NULL,'女装','女装','149879109000689',NULL,'uassets/2017/8/30/1504094167005.webp',NULL,NULL,'nvzhuang'),('1504094173900',NULL,NULL,NULL,'2017-08-30 11:57:17','2017-08-30 11:57:17',0,NULL,NULL,45,0,NULL,'秋装','秋装','149879109000689',NULL,'uassets/2017/8/30/1504094232067.webp',NULL,NULL,'qiuzhuang'),('1504094237825',NULL,NULL,NULL,'2017-08-30 11:57:56','2017-08-30 11:57:56',0,NULL,NULL,46,0,NULL,'T恤','T恤','149879109000689',NULL,'uassets/2017/8/30/1504094270750.webp',NULL,NULL,'tixu'),('1504094276144',NULL,NULL,NULL,'2017-08-30 11:58:30','2017-08-30 11:58:30',0,NULL,NULL,47,0,NULL,'大码女装','大码女装','149879109000689',NULL,'uassets/2017/8/30/1504094301691.webp',NULL,NULL,'damanvzhuang'),('1504094310402',NULL,NULL,NULL,'2017-08-30 12:52:35','2017-08-30 12:52:35',0,NULL,NULL,48,0,NULL,'箱包','箱包','149879109000689',NULL,'uassets/2017/8/30/1504097551167.webp',NULL,NULL,'xiangbao'),('1504097555422',NULL,NULL,NULL,'2017-08-30 13:00:34','2017-08-30 13:00:34',0,NULL,NULL,49,0,NULL,'内衣裤袜','内衣裤袜','149879109000689',NULL,'uassets/2017/8/30/1504098032698.webp',NULL,NULL,'neiyikuwa'),('1504098034126',NULL,NULL,NULL,'2017-08-30 13:01:04','2017-08-30 13:01:04',0,NULL,NULL,50,0,NULL,'女裤','女裤','149879109000689',NULL,'uassets/2017/8/30/1504098061664.webp',NULL,NULL,'nvku'),('1504098065074',NULL,NULL,NULL,'2017-08-30 13:10:50','2017-08-30 13:10:50',0,NULL,NULL,51,0,NULL,'女鞋','女鞋','149879109000689',NULL,'uassets/2017/8/30/1504098649350.webp',NULL,NULL,'nvxie'),('1504098864613',NULL,NULL,NULL,'2017-08-30 13:15:03','2017-08-30 13:15:03',0,NULL,NULL,52,0,NULL,'水果','水果',NULL,NULL,'uassets/2017/8/30/1504098901987.webp',NULL,NULL,'shuiguo'),('1504098903180',NULL,NULL,NULL,'2017-08-30 13:15:52','2017-08-30 13:15:52',0,NULL,NULL,53,0,NULL,'芒果街','芒果街','1504098864613',NULL,'uassets/2017/8/30/1504098940088.webp',NULL,NULL,'mangguo'),('1504099380925',NULL,NULL,NULL,'2017-08-30 13:25:16','2017-08-30 13:25:16',0,NULL,NULL,54,0,NULL,'蛋制品','蛋制品','1504098864613',NULL,'uassets/2017/8/30/1504099510287.webp',NULL,NULL,'danzhipin'),('1504099516589',NULL,NULL,NULL,'2017-08-30 13:25:45','2017-08-30 13:25:45',0,NULL,NULL,55,0,NULL,'海鲜水产','海鲜水产','1504098864613',NULL,'uassets/2017/8/30/1504099541970.webp',NULL,NULL,'haixianshuichan'),('1504099546107',NULL,NULL,NULL,'2017-08-30 13:27:37','2017-08-30 13:27:37',0,NULL,NULL,56,0,NULL,'手机','手机',NULL,NULL,'uassets/2017/8/30/1504099651766.webp',NULL,NULL,'shouji'),('1504099657133',NULL,NULL,NULL,'2017-08-30 13:28:14','2017-09-01 01:24:22',0,NULL,NULL,57,0,NULL,'数码配件','数码配件11','1504099546107',NULL,'uassets/2017/8/30/1504099690777.webp',NULL,NULL,'shumapeijian');
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
  `LOCATION` varchar(240) DEFAULT NULL COMMENT '图片物理地址',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `REAL_PRODUCT_ID` varchar(32) DEFAULT NULL COMMENT '真正的外键ID',
  `TYPE` varchar(45) DEFAULT NULL COMMENT '商品的缩略图，也可能是商品属性图，也可能是商品详情描述图\n三种类型的值分别是：thumbnail，detail，prop',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `PRODUCTINDEX` (`PRODUCT_ID`),
  KEY `USER_IDINDEX` (`USER_ID`),
  KEY `FK_pr_image_product_idx` (`REAL_PRODUCT_ID`),
  CONSTRAINT `FK_pr_image_product` FOREIGN KEY (`REAL_PRODUCT_ID`) REFERENCES `pr_product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='产品图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_image`
--

LOCK TABLES `pr_image` WRITE;
/*!40000 ALTER TABLE `pr_image` DISABLE KEYS */;
INSERT INTO `pr_image` VALUES ('150363476314552',NULL,NULL,NULL,'2017-08-25 04:19:23','2017-08-25 04:19:23',0,NULL,NULL,26,0,NULL,'1503634763140.jpg',NULL,'images/passets/2017/8/25/1503634763140.jpg','150363475302263','D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\passets\\2017\\8\\25\\1503634763140.jpg',NULL,NULL,NULL),('150364558697751',NULL,NULL,NULL,'2017-08-25 07:19:47','2017-08-25 07:19:47',0,NULL,NULL,28,0,NULL,'1503645586968.jpg',NULL,'images/passets/2017/8/25/1503645586968.jpg','150364555521851','D:\\images\\passets\\2017\\8\\25\\1503645586968.jpg',NULL,NULL,NULL),('150423362791419',NULL,NULL,NULL,'2017-09-01 02:40:27','2017-09-01 02:40:27',0,NULL,NULL,35,1,NULL,'1504233627910.jpeg',NULL,'passets/2017/9/1/1504233627910.jpeg','150383670510593','D:\\images\\passets\\2017\\9\\1\\1504233627910.jpeg',NULL,NULL,NULL),('150423364884219',NULL,NULL,NULL,'2017-09-01 02:40:48','2017-09-01 02:40:48',0,NULL,NULL,37,1,NULL,'1504233648838.jpeg',NULL,'passets/2017/9/1/1504233648838.jpeg','150383670510593','D:\\images\\passets\\2017\\9\\1\\1504233648838.jpeg',NULL,NULL,NULL),('150424449387618',NULL,NULL,NULL,'2017-09-01 05:41:33','2017-09-01 05:41:33',0,NULL,NULL,39,1,NULL,'1504244493872.jpeg',NULL,'passets/2017/9/1/1504244493872.jpeg','150383670510593','D:\\images\\passets\\2017\\9\\1\\1504244493872.jpeg',NULL,NULL,NULL),('150424536785236',NULL,NULL,NULL,'2017-09-01 05:56:07','2017-09-01 05:56:07',0,NULL,NULL,40,1,NULL,'1504245367848.jpeg',NULL,'passets/2017/9/1/1504245367848.jpeg','150383670510593','D:\\images\\passets\\2017\\9\\1\\1504245367848.jpeg',NULL,NULL,NULL),('150424593796963',NULL,NULL,NULL,'2017-09-01 06:05:37','2017-09-01 06:05:37',0,NULL,NULL,41,1,NULL,'1504245937964.jpeg',NULL,'passets/2017/9/1/1504245937964.jpeg','150383670510593','D:\\images\\passets\\2017\\9\\1\\1504245937964.jpeg',NULL,NULL,NULL),('150467050339986',NULL,NULL,NULL,'2017-09-06 04:01:43','2017-09-06 04:01:43',0,NULL,NULL,42,0,NULL,'1504670503364.jpeg',NULL,'passets/150383670510593/2017/9/6/1504670503364.jpeg','150383670510593','D:\\images\\passets\\150383670510593\\2017\\9\\6\\1504670503364.jpeg',NULL,NULL,NULL);
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
  `STORAGE` int(11) DEFAULT NULL COMMENT '库存',
  `STATUS` varchar(45) DEFAULT 'online' COMMENT '商品状态，上架或者下架，默认上架为：online，下架为offline',
  `MARKET_PRICE` double DEFAULT NULL COMMENT '市价或者划下价',
  `TRANSPORT_FEE` double DEFAULT NULL COMMENT '运费',
  PRIMARY KEY (`ID`),
  KEY `SORT` (`SORT`),
  KEY `fk_product_category_idx` (`CATEGORY_ID`),
  KEY `FK_PRODUCT_SHOP_idx` (`SHOP_ID`),
  KEY `USER_ID_INDEX` (`USER_ID`),
  CONSTRAINT `FK_PRODUCT_SHOP` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `pr_category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pr_product`
--

LOCK TABLES `pr_product` WRITE;
/*!40000 ALTER TABLE `pr_product` DISABLE KEYS */;
INSERT INTO `pr_product` VALUES ('150383670510593',NULL,NULL,NULL,'2017-08-27 12:26:34','2017-09-06 08:50:03',0,NULL,NULL,20,0,NULL,'【第2份减5元】攀枝花凯特芒果4斤（单果400-2000g）新鲜芒果非吉禄贵妃小台农金煌玉芒果','【坏果包赔】芒果属于后熟水果，根据发货地区不同，为确保果子运输途中不易损坏，一般发7-8成熟的果子，收到未熟（请放一个苹果和香蕉密封箱子，温度保持在20度左右，放软即可）芒果的表皮受季节和天气影响，出现花皮黑斑，花斑，是正常现象，这种斑点对果肉没有任何影响，因为近期发货量交大，黄皮和青皮我们随机发货，介意者慎拍！。益果汇小贴士，我们发货以后请及时跟踪物流到货及时签收，避免在快递存放太久损坏，收到水果如有坏果，请在24小时内联系我们客服。','passets/150383670510593/2017/9/6/1504670503364.jpeg',NULL,NULL,'mangguo','1504098903180','&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(239, 239, 239);&quot;&gt;下面在说一下FreeMarker的优缺点吧：&lt;/p&gt;&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(239, 239, 239);&quot;&gt;优点：&lt;/p&gt;&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(239, 239, 239);&quot;&gt;1、易学易用&amp;nbsp;&lt;br/&gt;我是看了一天文档就用得挺熟练了，&lt;strong&gt;&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;&lt;/strong&gt;文档写得太好了，例子丰富，照做一遍全都会了。&amp;nbsp;&lt;br/&gt;&lt;br/&gt;2、功能强大&amp;nbsp;&lt;br/&gt;比Velocity强大多了，还支持JSP Tag。不过最有意义的是macro功能，可以自定义常用的macro，实现页面常规操作的可复用性。&amp;nbsp;&lt;br/&gt;&lt;br/&gt;3、报错信息友好&amp;nbsp;&lt;br/&gt;很多应用服务器的JSP报错信息是无法定位到源代码行的。不过&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;报错定位很准确，丝毫不差，而且信息丰富，一看就知道怎么回事(虽然那个黄黄的页面看起来让人难受) 带着这种兴奋的心情来研究freemarker,但是用它做了几个项目以后问题来了,而且是很讨厌的问题.下面我就来说说FreeMarker的这几大罪状.&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(239, 239, 239);&quot;&gt;&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(239, 239, 239);&quot;&gt;&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding: 0px; font-family: Helvetica, Tahoma, Arial, sans-serif; font-size: 14px; white-space: normal; background-color: rgb(239, 239, 239);&quot;&gt;缺点：&lt;br/&gt;缺点一：&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;的变量必须有值，没有被赋值的变量就会抛出异常，那个黄黄的&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;出错页面，真是让人看了太难过了。&amp;nbsp;&lt;br/&gt;&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;的FAQ上面冠冕堂皇的说，未赋值的变量强制抛错可以杜绝很多潜在的错误，如缺失潜在的变量命名，或者其他变量错误。但是实际的效果是：带来的是非常大的编程麻烦，程序里面几乎所有可能出现空值的变量统统需要加上${xxx?if_exists}，有些循环条件还需要写if判断，这样不但没有杜绝应该杜绝的错误，反而极大增加了编程的麻烦。&amp;nbsp;&lt;br/&gt;&lt;br/&gt;缺点二：&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;的map限定key必须是string，其他数据类型竟然无法操作！这一点就不讲了，JavaEye上面已经有人抱怨过了。连Webwork的开发人员Pat Lightboy都在抱怨这一点。&amp;nbsp;&lt;br/&gt;&lt;br/&gt;&lt;br/&gt;缺点三：&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;为了编程方便把不可序列化的东西往session里面放！&amp;nbsp;&lt;br/&gt;&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;支持在页面里面直接操作Session，request等，例如${Session[...]}，方便确实很方便，但是一旦需要做群集，就会报错。&amp;nbsp;&lt;br/&gt;有朋友今天问起,他在做Tomcat群集的时候发现&lt;span class=&quot;hilite1&quot; style=&quot;background-color: rgb(255, 255, 0);&quot;&gt;freemarker&lt;/span&gt;报错，HttpSessionHashModel不可序列化。他修改该类源代码，让他实现序列化接口，仍然报错。我一看，HttpSessionHashModel包含的属性：&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;',NULL,1,1,'150375229485416',NULL,1,NULL,NULL,6,12,2,NULL,1000,'false',NULL,NULL);
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
INSERT INTO `pr_product_package_price` VALUES ('14343afa40504c15b97d797e22e07992',NULL,13,'150383670510593','7',NULL,'150383677798623_150426761471323_150449841005324'),('2fa3e84f1a1b46a390252ca62d185266',NULL,15,'150383670510593','9',NULL,'150383677798623_150426763581353_150449841005324'),('7545aa59e2ad4feabe7384ef1fd25d63',NULL,12,'150383670510593','6',NULL,'150383677798623_150426761471323_150449840098737'),('a96e4d24ce3048af9a06b63f8a6dcc18',NULL,14,'150383670510593','8',NULL,'150383677798623_150426763581353_150449840098737');
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
INSERT INTO `pr_product_property_value` VALUES ('150383677798623','白色',NULL,NULL,'150284150590025',NULL,0,'150383670510593'),('150426761471323','大码',NULL,NULL,'150284148370969','',0,'150383670510593'),('150426763581353','中码',NULL,NULL,'150284148370969','',0,'150383670510593'),('150449840098737','中国风',NULL,NULL,'15028412955799','',0,'150383670510593'),('150449841005324','美国风',NULL,NULL,'15028412955799','',0,'150383670510593');
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
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户的ID',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `MOBILPHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `ADDRESS` varchar(100) DEFAULT NULL COMMENT '地址',
  `CONTACTOR` varchar(45) DEFAULT NULL COMMENT '联系人姓名',
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='店铺';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES ('150375229485416',NULL,NULL,NULL,'2017-08-26 12:58:14','2017-08-27 04:41:12',1,NULL,NULL,18,0,NULL,'洋洋水果',NULL,'uassets/2017/8/27/1503807062182.jpg',NULL,NULL,'yangyangshuiguo',NULL,'<p>洋洋水果是一家专门经营水果的商店，依托江南水果市场</p>',NULL,'150375229439161','13926205227','517714860@qq.com','13926205227');
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
INSERT INTO `tb_resources` VALUES ('150183915978716','资源管理','150379508975520',NULL,NULL,NULL,NULL,NULL,'','system:resources:view','resources',NULL,'menu',NULL,NULL,'resourcesManage',NULL),('15018393878145','资源管理-新增','150183915978716','150183915978716','2017-08-04 09:36:28','2017-08-04 09:36:28',NULL,NULL,NULL,'system:resources:create',NULL,NULL,'button',NULL,NULL,'resourcesManageCreate',NULL),('150183945124351','资源管理-编辑','150183915978716','150183915978716','2017-08-04 09:37:31','2017-08-04 09:37:31',NULL,NULL,NULL,'system:resources:edit',NULL,NULL,'button',NULL,NULL,'resourcesManageEdit',NULL),('150198229992573','会员',NULL,NULL,NULL,NULL,NULL,NULL,'','member:member:view','member',NULL,'menu',NULL,NULL,'',NULL),('150251102697652','会员-新增','150198229992573',NULL,'2017-08-12 04:10:26','2017-08-12 08:44:50',NULL,NULL,'','member:member:create','',NULL,'menu',NULL,NULL,'memberCreate',NULL),('150379218609524','用户管理','150379508975520',NULL,NULL,NULL,NULL,NULL,'','system:user:view','user',NULL,'menu',NULL,NULL,'userManager',NULL),('150379360110449','用户管理-新增','150379218609524',NULL,'2017-08-27 00:26:41','2017-08-27 00:26:41',NULL,NULL,'','system:user:create','',NULL,'button',NULL,NULL,'userManagerCreate',NULL),('150379366774145','用户管理-编辑','150379218609524',NULL,'2017-08-27 00:27:47','2017-08-27 00:27:47',NULL,NULL,'','system:user:edit','',NULL,'button',NULL,NULL,'userManagerEdit',NULL),('15037937484304','用户管理-删除','150379218609524',NULL,'2017-08-27 00:29:08','2017-08-27 00:29:08',NULL,NULL,'','system:user:delete','',NULL,'button',NULL,NULL,'userManagerDelete',NULL),('150379396460869','个人中心',NULL,NULL,NULL,NULL,NULL,NULL,'个人中心，下面应有我的店铺，我的商品，我的订单，密码修改，分销我的商品会员，我的交易记录等','personal:view','personal',NULL,'menu',NULL,NULL,'personalManager',NULL),('150379406634697','我的店铺','150379396460869',NULL,NULL,NULL,NULL,NULL,'个人中心，我的店铺','personal:myshop:view','/personal/myshop',NULL,'menu',NULL,NULL,'personalMyshop',NULL),('150379504541678','角色管理','150379508975520',NULL,NULL,NULL,NULL,NULL,'角色管理','system:role:view','role',NULL,'menu',NULL,NULL,'roleManager',NULL),('150379508975520','系统管理',NULL,NULL,'2017-08-27 00:51:29','2017-08-27 00:51:29',NULL,NULL,'','system:view','',NULL,'menu',NULL,NULL,'systemManager',NULL),('150379525152179','角色管理-新增','150379504541678',NULL,'2017-08-27 00:54:11','2017-08-27 00:54:11',NULL,NULL,'','system:role:create','',NULL,'button',NULL,NULL,'userManager',NULL),('150379531977827','角色管理-编辑','150379504541678',NULL,NULL,NULL,NULL,NULL,'角色管理-编辑','system:role:edit','',NULL,'button',NULL,NULL,'userManagerEdit',NULL),('150379537058918','用户管理-删除','150379504541678',NULL,'2017-08-27 00:56:10','2017-08-27 00:56:10',NULL,NULL,'','system:role:delete','',NULL,'button',NULL,NULL,'userManagerDelete',NULL),('150379566954642','会员管理-编辑','150198229992573',NULL,'2017-08-27 01:01:09','2017-08-27 01:01:09',NULL,NULL,'会员管理-编辑','member:member:edit','',NULL,'button',NULL,NULL,'memberManagerEdit',NULL),('150379573995920','会员管理-删除','150198229992573',NULL,'2017-08-27 01:02:19','2017-08-27 01:02:19',NULL,NULL,'会员管理-删除','member:member:delete','',NULL,'button',NULL,NULL,'memberManagerDelete',NULL),('150379592744520','用户组管理','150379508975520',NULL,'2017-08-27 01:05:27','2017-08-27 01:05:27',NULL,NULL,'用户组管理','system:userGroup:view','userGroup',NULL,'menu',NULL,NULL,'userGroupManager',NULL),('150379597886185','用户组管理-新增','150379592744520',NULL,NULL,NULL,NULL,NULL,'用户组管理-新增','system:userGroup:create','',NULL,'button',NULL,NULL,'userGroupManagerCreate',NULL),('150379607694585','用户组管理-编辑','150379592744520',NULL,'2017-08-27 01:07:56','2017-08-27 01:07:56',NULL,NULL,'用户组管理-编辑','system:userGroup:edit','',NULL,'button',NULL,NULL,'userGroupManagerEdit',NULL),('150379612348988','用户管理-删除','150379592744520',NULL,'2017-08-27 01:08:43','2017-08-27 01:08:43',NULL,NULL,'用户管理-删除','system:userGroup:delete','',NULL,'button',NULL,NULL,'userGroupManagerDelete',NULL),('150379623171081','我的订单','150379396460869',NULL,'2017-08-27 01:10:31','2017-08-27 01:10:31',NULL,NULL,'我的订单页面','personal:myorder:view','personal/myorder',NULL,'menu',NULL,NULL,'personalmyorderview',NULL),('150379716573586','密码修改','150379396460869',NULL,NULL,NULL,NULL,NULL,'密码修改','personal:changePass:view','personal/changePass',NULL,'menu',NULL,NULL,'personalChangePassView',NULL),('150379754660161','竞猜管理',NULL,NULL,'2017-08-27 01:32:26','2017-08-27 01:32:26',NULL,NULL,'竞猜管理','member:guess:view','',NULL,'menu',NULL,NULL,'memberGuessManager',NULL),('150379759981057','竞猜分类管理','150379754660161',NULL,'2017-08-27 01:33:19','2017-08-27 01:33:19',NULL,NULL,'竞猜分类管理','member:guessCategory:view','guessCategory',NULL,'menu',NULL,NULL,'memberGuessCategoryManager',NULL),('150379766741257','竞猜分类管理-新增','150379759981057',NULL,'2017-08-27 01:34:27','2017-08-27 01:34:27',NULL,NULL,'竞猜分类管理-新增','member:guessCategory:create','',NULL,'button',NULL,NULL,'memberGuessCategoryCreate',NULL),('150379771338447','竞猜分类管理-编辑','150379759981057',NULL,'2017-08-27 01:35:13','2017-08-27 01:35:13',NULL,NULL,'竞猜分类管理-编辑','member:guessCategory:edit','',NULL,'button',NULL,NULL,'memberGuessCategoryEdit',NULL),('150379777604875','竞猜分类管理-删除','150379759981057',NULL,'2017-08-27 01:36:16','2017-08-27 01:36:16',NULL,NULL,'竞猜分类管理-删除','member:guessCategory:delete','',NULL,'button',NULL,NULL,'memberGuessCategoryDelete',NULL),('150379792589547','竞猜问题管理','150379754660161',NULL,'2017-08-27 01:38:45','2017-08-27 01:38:45',NULL,NULL,'竞猜问题管理','member:guessQuestion:view','guessQuestion',NULL,'button',NULL,NULL,'memberGuessQuestion',NULL),('150379807020543','竞猜问题管理-新增','150379792589547',NULL,'2017-08-27 01:41:10','2017-08-27 01:41:10',NULL,NULL,'竞猜问题管理-新增','member:guessQuestion:create','',NULL,'button',NULL,NULL,'memberGuessQuestionCreate',NULL),('15037982173041','竞猜问题管理-编辑','150379792589547',NULL,'2017-08-27 01:43:37','2017-08-27 01:43:37',NULL,NULL,'竞猜问题管理-编辑','member:guessQuestion:edit','',NULL,'button',NULL,NULL,'memberGuessQuestionEdit',NULL),('150379827523241','竞猜问题管理-删除','150379792589547',NULL,'2017-08-27 01:44:35','2017-08-27 01:44:35',NULL,NULL,'竞猜问题管理-删除','member:guessQuestion:delete','',NULL,'button',NULL,NULL,'memberGuessQuestionDelete',NULL),('150379858554549','商品管理',NULL,NULL,'2017-08-27 01:49:45','2017-08-27 01:49:45',NULL,NULL,'商品管理','product:view','',NULL,'menu',NULL,NULL,'productManager',NULL),('150379871535612','商品分类管理','150379858554549',NULL,'2017-08-27 01:51:55','2017-08-27 01:51:55',NULL,NULL,'商品分类管理','mall:prCategory:view','prCategory',NULL,'menu',NULL,NULL,'prCategoryManager',NULL),('150379895415851','商品分类管理-新增','150379871535612',NULL,'2017-08-27 01:55:54','2017-08-27 01:55:54',NULL,NULL,'商品分类管理-新增','mall:prCategory:create','',NULL,'button',NULL,NULL,'prCategoryManagerCreate',NULL),('150379930433113','商品分类管理-编辑','150379871535612',NULL,'2017-08-27 02:01:44','2017-08-27 02:01:44',NULL,NULL,'商品分类管理-编辑','mall:prCategory:edit','',NULL,'button',NULL,NULL,'prCategoryManagerEdit',NULL),('150379934975243','商品分类管理-删除','150379871535612',NULL,'2017-08-27 02:02:29','2017-08-27 02:02:29',NULL,NULL,'','mall:prCategory:delete','',NULL,'button',NULL,NULL,'prCategoryManagerDelete',NULL),('150379971306833','商品管理','150379858554549',NULL,'2017-08-27 02:08:33','2017-08-27 02:08:33',NULL,NULL,'商品管理','mall:product:view','product',NULL,'menu',NULL,NULL,'product',NULL),('150379979238088','商品管理-新增','150379971306833',NULL,'2017-08-27 02:09:52','2017-08-27 02:09:52',NULL,NULL,'商品管理-新增','mall:product:create','',NULL,'button',NULL,NULL,'mall:product:create',NULL),('150379983863770','商品管理-编辑','150379971306833',NULL,'2017-08-27 02:10:38','2017-08-27 02:10:38',NULL,NULL,'商品管理-编辑','mall:product:edit','',NULL,'button',NULL,NULL,'mall:product:edit',NULL),('150380032692268','商品管理-删除','150379971306833',NULL,'2017-08-27 02:18:46','2017-08-27 02:18:46',NULL,NULL,'商品管理-删除','mall:product:delete','',NULL,'button',NULL,NULL,'mall:product:delete',NULL),('150380043673149','商品属性管理','150379858554549',NULL,'2017-08-27 02:20:36','2017-08-27 02:20:36',NULL,NULL,'商品属性管理','mall:productProperty:view','productProperty',NULL,'menu',NULL,NULL,'mall:productProperty:view',NULL),('150380058592219','商品属性管理-新增','150380043673149',NULL,NULL,NULL,NULL,NULL,'商品属性管理-新增','mall:productProperty:create','',NULL,'button',NULL,NULL,'mall:productProperty:create',NULL),('150380070988029','商品属性管理-编辑','150380043673149',NULL,'2017-08-27 02:25:09','2017-08-27 02:25:09',NULL,NULL,'商品属性管理-编辑','mall:productProperty:edit','',NULL,'button',NULL,NULL,'mall:productProperty:edit',NULL),('150380083826115','商品属性管理-删除','150380043673149',NULL,'2017-08-27 02:27:18','2017-08-27 02:27:18',NULL,NULL,'商品属性管理-删除','mall:productProperty:delete','',NULL,'button',NULL,NULL,'mall:productProperty:delete',NULL),('150380109563978','商铺管理',NULL,NULL,'2017-08-27 02:31:35','2017-08-27 02:31:35',NULL,NULL,'','shop:shop:view','',NULL,'menu',NULL,NULL,'',NULL),('150380123402961','商铺分类管理','150380109563978',NULL,'2017-08-27 02:33:54','2017-08-27 02:33:54',NULL,NULL,'商铺分类管理','shop:shopCategory:view','shopCategory',NULL,'menu',NULL,NULL,'shop:shopCategory:view',NULL),('150380130913763','商铺分类管理-新增','150380123402961',NULL,'2017-08-27 02:35:09','2017-08-27 02:35:09',NULL,NULL,'商铺分类管理-新增','shop:shopCategory:create','',NULL,'button',NULL,NULL,'shop:shopCategory:create',NULL),('15038015824771','商铺分类管理-编辑','150380123402961',NULL,'2017-08-27 02:39:42','2017-08-27 02:39:42',NULL,NULL,'商铺分类管理-编辑','shop:shopCategory:edit','',NULL,'button',NULL,NULL,'shop:shopCategory:edit',NULL),('150380162808977','商铺分类管理-删除','150380123402961',NULL,'2017-08-27 02:40:28','2017-08-27 02:40:28',NULL,NULL,'商铺分类管理-删除','shop:shopCategory:delete','',NULL,'button',NULL,NULL,'shop:shopCategory:delete',NULL),('15038017353505','店铺管理','150380109563978',NULL,'2017-08-27 02:42:15','2017-08-27 02:42:15',NULL,NULL,'店铺管理','shop:shop:view','shop',NULL,'menu',NULL,NULL,'shop:shop:view',NULL),('15038017747621','店铺管理-新增','15038017353505',NULL,NULL,NULL,NULL,NULL,'店铺管理-新增','shop:shop:create','',NULL,'button',NULL,NULL,'shop:shop:create',NULL),('150380181148196','店铺管理-编辑','15038017353505',NULL,NULL,NULL,NULL,NULL,'店铺管理-编辑','shop:shop:edit','',NULL,'button',NULL,NULL,'shop:shop:edit',NULL),('150380254929161','店铺管理-删除','15038017353505',NULL,'2017-08-27 02:55:49','2017-08-27 02:55:49',NULL,NULL,'店铺管理-删除','shop:shop:delete','',NULL,'button',NULL,NULL,'shop:shop:delete',NULL),('150380265752454','订单管理',NULL,NULL,NULL,NULL,NULL,NULL,'订单管理','mall:order:view','order',NULL,'menu',NULL,NULL,'mall:order:view',NULL),('150380272979220','资源管理-新增','150183915978716',NULL,'2017-08-27 02:58:49','2017-08-27 02:58:49',NULL,NULL,'资源管理-新增','system:resources:delete','',NULL,'button',NULL,NULL,'system:resources:delete',NULL),('150380310674270','订单管理-新增','150380265752454',NULL,'2017-08-27 03:05:06','2017-08-27 03:05:06',NULL,NULL,'订单管理-新增','mall:order:create','',NULL,'button',NULL,NULL,'mall:order:create',NULL),('150380314132038','订单管理-编辑','150380265752454',NULL,NULL,NULL,NULL,NULL,'订单管理-编辑','mall:order:edit','',NULL,'button',NULL,NULL,'mall:order:edit',NULL),('150380345813745','订单管理-删除','150380265752454',NULL,'2017-08-27 03:10:58','2017-08-27 03:10:58',NULL,NULL,'订单管理-删除','mall:order:edit','',NULL,'button',NULL,NULL,'mall:order:edit',NULL),('150380378289536','会员账户管理',NULL,NULL,'2017-08-27 03:16:22','2017-08-27 03:16:22',NULL,NULL,'会员账户管理','member:memberAccount:view','memberAccount',NULL,'menu',NULL,NULL,'member:memberAccount:view',NULL);
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
INSERT INTO `tb_role_resources` VALUES ('047c3bb95f584ff79a83ca46d5c38b85','150379807020543','150183669467347'),('0df27b1276cf4deaa8465c18a06b992c','150379754660161','150183669467347'),('11e2b4f504a74d4998bc5e0ee70a3458','150379895415851','150183669467347'),('150267749731230','150251102697652','150236810620045'),('150267749731255','15018393878145','150236810620045'),('150267749731275','150198229992573','150236810620045'),('150267749731278','150183915978716','150236810620045'),('150267749731299','150183945124351','150236810620045'),('150eeeeaa513464bb82b134a7f0817d1','150380083826115','150183669467347'),('28b76105d2234135a9215733cf800658','150379858554549','150183669467347'),('2a6f26d7a0a14b85afb3fcbad734c2bb','150379537058918','150183669467347'),('2e95b391776c4705965da1c404f02e2d','150380265752454','150183669467347'),('321c52c4210a4916a0ea7e81d20c19e5','150379366774145','150183669467347'),('33a85f386f7f4106a5d3c650e7d8158e','150379607694585','150183669467347'),('35bf28c0250b42aaad65c8323fb228c9','150379979238088','150183669467347'),('38ca06084c76467dac003e0feaa56d18','150380272979220','150183669467347'),('3db8083f36b64ed5903006f386dac4c3','150380043673149','150183669467347'),('404276e8ca0c4070a488a92864481a84','150379406634697','150183669467347'),('4f50d68c327b41b18449b9311e768a05','150379827523241','150183669467347'),('5114651ec1b54598baa770aaf6f9a84d','150380310674270','150183669467347'),('518c4ab2f34942c686476a10bcb1e0f5','150380254929161','150183669467347'),('53726909b34c4ebfb061d271b9c7aeca','150379531977827','150183669467347'),('5f6faa2c8b2d49aaa3933672a82a0eeb','150380070988029','150183669467347'),('5ff38157106346d7bcc8003223eacee5','150198229992573','150183669467347'),('6667c6463f1f4fc6ac44b8d697d3ff64','150379508975520','150183669467347'),('69b54f40cdb0468a8d1356a243481e38','150379771338447','150183669467347'),('6d318edb64ff493e9a7fa4ad67e318b7','150183915978716','150183669467347'),('6d3a3999c0fe48678f9656f80dddcf15','150380181148196','150183669467347'),('6e1278cbdcf04547a30e9ceba07ec958','150379971306833','150183669467347'),('6edd7a0c92964e3a99027f40414bab2c','15037937484304','150183669467347'),('75267e68e2904340927e5644e55a24a2','150379766741257','150183669467347'),('7557278f8edd48259b262f345dd872f2','150379573995920','150183669467347'),('81a2e5ef307b4d339e4f94c4eab09cf8','150379597886185','150183669467347'),('82629281528a4d7dbfa849c47a1f25b1','150379360110449','150183669467347'),('836833a00b84409487d99f5ed649eb5c','150379396460869','150183669467347'),('849675cb22764dcdb281c14ecb0d8942','150379792589547','150183669467347'),('861c3d832cdd4b509d161799853aa4bf','150379592744520','150183669467347'),('92a980bedd054d908c17fab30ece5899','150379930433113','150183669467347'),('96e53a0b5b9b43238c07fe32351495ed','150379218609524','150183669467347'),('9aafb0c97c244d31a968cb55337e3b21','150251102697652','150183669467347'),('9f7ee1bfcc88424ab1c8b9f433256615','150379759981057','150183669467347'),('a2fb835df9c74ebdaec7b27f4f674036','150380345813745','150183669467347'),('a97991e90a65455eb3e4b40255114cd6','150380109563978','150183669467347'),('ab31837c1e7c41089b7450b9344791fa','15038015824771','150183669467347'),('acbb25864ecc43a6840dcf4c769ffff3','15038017353505','150183669467347'),('adba89307cdb4462a145440c3892f8e8','150183945124351','150183669467347'),('b1de130f7dc14f28b7e2b56c84ca48b1','150379777604875','150183669467347'),('b8c75610246a4c119246f0a73f8382bf','15038017747621','150183669467347'),('ba86d5b8463345248a94bac7309c90c5','150379716573586','150183669467347'),('be63a97866be42e99c59289c19d5bcca','150379983863770','150183669467347'),('c3d04a60e9b94e24a1dfe35ed7848801','150379612348988','150183669467347'),('c5e701aeb4d54e63b63a4f78f2a55615','150379504541678','150183669467347'),('c9f61c4d6c2e452196c3d2a9530e1728','150379566954642','150183669467347'),('cb017f78c06345d09f9f35c132dfdbbd','150379934975243','150183669467347'),('cde89ce7a9b54015966878a97fe225af','15018393878145','150183669467347'),('d26a92a82a434b589cfa9568f786affc','150380058592219','150183669467347'),('d40ac20242af49a5810c60f936b17f75','150380314132038','150183669467347'),('d83986a32a2043ba9d152d5e1ad45f09','150380032692268','150183669467347'),('d930ba185b8d4cf78ae03e40f6457a0f','15037982173041','150183669467347'),('de13d460e2fb4fdfaa016da00ceb84bf','150380378289536','150183669467347'),('e2b7a0d8435d4f2c9226e0310fc1d9df','150380130913763','150183669467347'),('e48de9981644477daaeb69ffbf559859','150379525152179','150183669467347'),('e5a1827db5164f699b4a3e35251775ce','150380162808977','150183669467347'),('efee130a15444251b90819bb1b0c67fe','150380123402961','150183669467347'),('f19184005d25470480413db311f11919','150379871535612','150183669467347'),('f61ff655447c4e2b8d558b6df935a335','150379623171081','150183669467347');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('150375229439161','huangjixin','ea48576f30be1669971699c09ad05c94','2017-08-26 15:29:36','2017-08-26 12:58:14','2017-08-26 15:29:36','2017-08-26 12:58:14','517714860@qq.com','13926205227',0,NULL,NULL,NULL,'uassets/2017/8/26/1503761211244.jpg','新建用户为huangjixn,该用户为管理员账户',NULL,NULL,'517714860','13926205227',NULL,NULL,13,NULL,NULL,NULL,NULL,NULL,NULL,'150183679493710',NULL,1,'445202198510308352',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'cooperation');
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
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`ID`),
  KEY `SORTINDEX` (`SORT`),
  KEY `USER_IDINDEX` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8 COMMENT='用户附件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_assets`
--

LOCK TABLES `tb_user_assets` WRITE;
/*!40000 ALTER TABLE `tb_user_assets` DISABLE KEYS */;
INSERT INTO `tb_user_assets` VALUES ('150192413505221',NULL,'1501924135045.png',NULL,'2017-08-05 09:08:56','2017-08-05 09:08:56',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501924135045.png',NULL,NULL,NULL,NULL,NULL,1,'userAssets/\\2017/8/5/1501924135045.png',NULL,NULL),('15019241368541',NULL,'1501924136847.jpg',NULL,'2017-08-05 09:08:56','2017-08-05 09:08:56',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501924136847.jpg',NULL,NULL,NULL,NULL,NULL,2,'userAssets/\\2017/8/5/1501924136847.jpg',NULL,NULL),('150192474307979',NULL,'1501924743023.jpg',NULL,'2017-08-05 09:19:03','2017-08-05 09:19:03',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501924743023.jpg',NULL,NULL,NULL,NULL,NULL,3,'userAssets/2017/8/5/1501924743023.jpg',NULL,NULL),('150192640000137',NULL,'1501926399980.jpg',NULL,'2017-08-05 09:46:40','2017-08-05 09:46:40',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501926399980.jpg',NULL,NULL,NULL,NULL,NULL,4,'uassets/2017/8/5/1501926399980.jpg',NULL,NULL),('150192640009937',NULL,'1501926400094.jpg',NULL,'2017-08-05 09:46:40','2017-08-05 09:46:40',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\userAssets\\2017\\8\\5\\1501926400094.jpg',NULL,NULL,NULL,NULL,NULL,5,'uassets/2017/8/5/1501926400094.jpg',NULL,NULL),('150192655170447',NULL,'1501926551673.jpg',NULL,'2017-08-05 09:49:11','2017-08-05 09:49:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501926551673.jpg',NULL,NULL,NULL,NULL,NULL,6,'images/uassets/2017/8/5/1501926551673.jpg',NULL,NULL),('150192655181124',NULL,'1501926551805.jpg',NULL,'2017-08-05 09:49:11','2017-08-05 09:49:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501926551805.jpg',NULL,NULL,NULL,NULL,NULL,7,'images/uassets/2017/8/5/1501926551805.jpg',NULL,NULL),('150193785152452',NULL,'1501937851507.jpg',NULL,'2017-08-05 12:57:34','2017-08-05 12:57:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501937851507.jpg',NULL,NULL,NULL,NULL,NULL,8,'images/uassets/2017/8/5/1501937851507.jpg',NULL,NULL),('150193794371878',NULL,'1501937943709.jpg',NULL,'2017-08-05 12:59:03','2017-08-05 12:59:03',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\5\\1501937943709.jpg',NULL,NULL,NULL,NULL,NULL,9,'images/uassets/2017/8/5/1501937943709.jpg',NULL,NULL),('150193798868715',NULL,'1501937988683.jpg',NULL,'2017-08-05 12:59:48','2017-08-05 12:59:48',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\5\\1501937988683.jpg',NULL,NULL,NULL,NULL,NULL,10,'images/uassets/2017/8/5/1501937988683.jpg',NULL,NULL),('150194046403243',NULL,'1501940464020.jpg',NULL,'2017-08-05 13:41:06','2017-08-05 13:41:06',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\5\\1501940464020.jpg',NULL,NULL,NULL,NULL,NULL,11,'images/uassets/2017/8/5/1501940464020.jpg',NULL,NULL),('150194051716486',NULL,'1501940517098.jpg',NULL,'2017-08-05 13:41:57','2017-08-05 13:41:57',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501940517098.jpg',NULL,NULL,NULL,NULL,NULL,12,'images/uassets/2017/8/5/1501940517098.jpg',NULL,NULL),('150194057301019',NULL,'1501940573002.jpg',NULL,'2017-08-05 13:42:53','2017-08-05 13:42:53',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501940573002.jpg',NULL,NULL,NULL,NULL,NULL,13,'images/uassets/2017/8/5/1501940573002.jpg',NULL,NULL),('150194061426168',NULL,'1501940614251.jpg',NULL,'2017-08-05 13:43:34','2017-08-05 13:43:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-fileupload-web\\images\\uassets\\2017\\8\\5\\1501940614251.jpg',NULL,NULL,NULL,NULL,NULL,14,'images/uassets/2017/8/5/1501940614251.jpg',NULL,NULL),('150223603894197',NULL,'1502236038922.jpg',NULL,'2017-08-08 23:47:22','2017-08-08 23:47:22',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\9\\1502236038922.jpg',NULL,NULL,NULL,NULL,NULL,15,'images/uassets/2017/8/9/1502236038922.jpg',NULL,NULL),('150223675330225',NULL,'1502236753286.jpg',NULL,'2017-08-08 23:59:13','2017-08-08 23:59:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502236753286.jpg',NULL,NULL,NULL,NULL,NULL,16,'images/uassets/2017/8/9/1502236753286.jpg',NULL,NULL),('150223717785159',NULL,'1502237177847.jpg',NULL,'2017-08-09 00:06:17','2017-08-09 00:06:17',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502237177847.jpg',NULL,NULL,NULL,NULL,NULL,17,'images/uassets/2017/8/9/1502237177847.jpg',NULL,NULL),('150224297174312',NULL,'1502242971734.jpg',NULL,'2017-08-09 01:42:54','2017-08-09 01:42:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502242971734.jpg',NULL,NULL,NULL,NULL,NULL,18,'images/uassets/2017/8/9/1502242971734.jpg',NULL,NULL),('150224319740092',NULL,'1502243197394.jpg',NULL,'2017-08-09 01:46:37','2017-08-09 01:46:37',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502243197394.jpg',NULL,NULL,NULL,NULL,NULL,19,'images/uassets/2017/8/9/1502243197394.jpg',NULL,NULL),('15022436102652',NULL,'1502243610260.ch5.cc.jpg',NULL,'2017-08-09 01:53:30','2017-08-09 01:53:30',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502243610260.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,20,'images/uassets/2017/8/9/1502243610260.ch5.cc.jpg',NULL,NULL),('150224408063250',NULL,'1502244080627.ch5.cc.jpg',NULL,'2017-08-09 02:01:20','2017-08-09 02:01:20',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244080627.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,21,'images/uassets/2017/8/9/1502244080627.ch5.cc.jpg',NULL,NULL),('150224414529638',NULL,'1502244145290.COM.gif',NULL,'2017-08-09 02:02:25','2017-08-09 02:02:25',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244145290.COM.gif',NULL,NULL,NULL,NULL,NULL,22,'images/uassets/2017/8/9/1502244145290.COM.gif',NULL,NULL),('150224436832148',NULL,'1502244368314.COM.jpg',NULL,'2017-08-09 02:06:08','2017-08-09 02:06:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244368314.COM.jpg',NULL,NULL,NULL,NULL,NULL,23,'images/uassets/2017/8/9/1502244368314.COM.jpg',NULL,NULL),('15022444956228',NULL,'1502244495617.COM.jpg',NULL,'2017-08-09 02:08:15','2017-08-09 02:08:15',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244495617.COM.jpg',NULL,NULL,NULL,NULL,NULL,24,'images/uassets/2017/8/9/1502244495617.COM.jpg',NULL,NULL),('150224493257821',NULL,'1502244932572.COM.jpg',NULL,'2017-08-09 02:15:32','2017-08-09 02:15:32',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244932572.COM.jpg',NULL,NULL,NULL,NULL,NULL,25,'images/uassets/2017/8/9/1502244932572.COM.jpg',NULL,NULL),('150224497542966',NULL,'1502244975424.COM.jpg',NULL,'2017-08-09 02:16:15','2017-08-09 02:16:15',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502244975424.COM.jpg',NULL,NULL,NULL,NULL,NULL,26,'images/uassets/2017/8/9/1502244975424.COM.jpg',NULL,NULL),('150224534737462',NULL,'1502245347364.COM.jpg',NULL,'2017-08-09 02:22:27','2017-08-09 02:22:27',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245347364.COM.jpg',NULL,NULL,NULL,NULL,NULL,27,'images/uassets/2017/8/9/1502245347364.COM.jpg',NULL,NULL),('150224542696440',NULL,'1502245426960.COM.jpg',NULL,'2017-08-09 02:23:46','2017-08-09 02:23:46',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245426960.COM.jpg',NULL,NULL,NULL,NULL,NULL,28,'images/uassets/2017/8/9/1502245426960.COM.jpg',NULL,NULL),('150224570263796',NULL,'1502245702631.COM.jpg',NULL,'2017-08-09 02:28:22','2017-08-09 02:28:22',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245702631.COM.jpg',NULL,NULL,NULL,NULL,NULL,29,'images/uassets/2017/8/9/1502245702631.COM.jpg',NULL,NULL),('150224580621076',NULL,'1502245806205.COM.jpg',NULL,'2017-08-09 02:30:06','2017-08-09 02:30:06',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245806205.COM.jpg',NULL,NULL,NULL,NULL,NULL,30,'images/uassets/2017/8/9/1502245806205.COM.jpg',NULL,NULL),('150224581469646',NULL,'1502245814690.ch5.cc.jpg',NULL,'2017-08-09 02:30:14','2017-08-09 02:30:14',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502245814690.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,31,'images/uassets/2017/8/9/1502245814690.ch5.cc.jpg',NULL,NULL),('150224601486773',NULL,'1502246014813.ch5.cc.jpg',NULL,'2017-08-09 02:33:34','2017-08-09 02:33:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502246014813.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,32,'images/uassets/2017/8/9/1502246014813.ch5.cc.jpg',NULL,NULL),('150224603783250',NULL,'1502246037828.png',NULL,'2017-08-09 02:33:57','2017-08-09 02:33:57',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502246037828.png',NULL,NULL,NULL,NULL,NULL,33,'images/uassets/2017/8/9/1502246037828.png',NULL,NULL),('150224605331354',NULL,'1502246053309.png',NULL,'2017-08-09 02:34:13','2017-08-09 02:34:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502246053309.png',NULL,NULL,NULL,NULL,NULL,34,'images/uassets/2017/8/9/1502246053309.png',NULL,NULL),('150224833424824',NULL,'1502248334244.com~自由な成人映画、最も速く&最新ポルノ映画サイト.jpg',NULL,'2017-08-09 03:12:14','2017-08-09 03:12:14',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248334244.com~自由な成人映画、最も速く&最新ポルノ映画サイト.jpg',NULL,NULL,NULL,NULL,NULL,35,'images/uassets/2017/8/9/1502248334244.com~自由な成人映画、最も速く&最新ポルノ映画サイト.jpg',NULL,NULL),('150224834418382',NULL,'1502248344179.COM.gif',NULL,'2017-08-09 03:12:24','2017-08-09 03:12:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248344179.COM.gif',NULL,NULL,NULL,NULL,NULL,36,'images/uassets/2017/8/9/1502248344179.COM.gif',NULL,NULL),('150224835817145',NULL,'1502248358167.COM.jpg',NULL,'2017-08-09 03:12:38','2017-08-09 03:12:38',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248358167.COM.jpg',NULL,NULL,NULL,NULL,NULL,37,'images/uassets/2017/8/9/1502248358167.COM.jpg',NULL,NULL),('150224879545698',NULL,'1502248795389.COM.jpg',NULL,'2017-08-09 03:19:55','2017-08-09 03:19:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502248795389.COM.jpg',NULL,NULL,NULL,NULL,NULL,38,'images/uassets/2017/8/9/1502248795389.COM.jpg',NULL,NULL),('15022499902346',NULL,'1502249990210.COM.jpg',NULL,'2017-08-09 03:39:50','2017-08-09 03:39:50',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502249990210.COM.jpg',NULL,NULL,NULL,NULL,NULL,39,'images/uassets/2017/8/9/1502249990210.COM.jpg',NULL,NULL),('150225144561859',NULL,'1502251445612.ch5.cc.jpg',NULL,'2017-08-09 04:04:08','2017-08-09 04:04:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502251445612.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,40,'images/uassets/2017/8/9/1502251445612.ch5.cc.jpg',NULL,NULL),('150225956087211',NULL,'1502259560865.COM.jpg',NULL,'2017-08-09 06:19:22','2017-08-09 06:19:22',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\9\\1502259560865.COM.jpg',NULL,NULL,NULL,NULL,NULL,41,'images/uassets/2017/8/9/1502259560865.COM.jpg',NULL,NULL),('150232955909379',NULL,'1502329559049.ch5.cc.jpg',NULL,'2017-08-10 01:45:59','2017-08-10 01:45:59',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502329559049.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,42,'images/uassets/2017/8/10/1502329559049.ch5.cc.jpg',NULL,NULL),('150233171072720',NULL,'1502331710721.COM.jpg',NULL,'2017-08-10 02:21:51','2017-08-10 02:21:51',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502331710721.COM.jpg',NULL,NULL,NULL,NULL,NULL,43,'images/uassets/2017/8/10/1502331710721.COM.jpg',NULL,NULL),('150233187532978',NULL,'1502331875325.COM.jpg',NULL,'2017-08-10 02:24:35','2017-08-10 02:24:35',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502331875325.COM.jpg',NULL,NULL,NULL,NULL,NULL,44,'images/uassets/2017/8/10/1502331875325.COM.jpg',NULL,NULL),('150233199304378',NULL,'1502331993039.COM.jpg',NULL,'2017-08-10 02:26:33','2017-08-10 02:26:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502331993039.COM.jpg',NULL,NULL,NULL,NULL,NULL,45,'images/uassets/2017/8/10/1502331993039.COM.jpg',NULL,NULL),('150233228618160',NULL,'1502332286158.COM.jpg',NULL,'2017-08-10 02:31:26','2017-08-10 02:31:26',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502332286158.COM.jpg',NULL,NULL,NULL,NULL,NULL,46,'images/uassets/2017/8/10/1502332286158.COM.jpg',NULL,NULL),('150233284529953',NULL,'1502332845274.COM.jpg',NULL,'2017-08-10 02:40:45','2017-08-10 02:40:45',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502332845274.COM.jpg',NULL,NULL,NULL,NULL,NULL,47,'images/uassets/2017/8/10/1502332845274.COM.jpg',NULL,NULL),('150233323089450',NULL,'1502333230888.COM.jpg',NULL,'2017-08-10 02:47:10','2017-08-10 02:47:10',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333230888.COM.jpg',NULL,NULL,NULL,NULL,NULL,48,'images/uassets/2017/8/10/1502333230888.COM.jpg',NULL,NULL),('15023334681759',NULL,'1502333468169.COM.jpg',NULL,'2017-08-10 02:51:08','2017-08-10 02:51:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333468169.COM.jpg',NULL,NULL,NULL,NULL,NULL,49,'images/uassets/2017/8/10/1502333468169.COM.jpg',NULL,NULL),('150233354450478',NULL,'1502333544442.COM.jpg',NULL,'2017-08-10 02:52:24','2017-08-10 02:52:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333544442.COM.jpg',NULL,NULL,NULL,NULL,NULL,50,'images/uassets/2017/8/10/1502333544442.COM.jpg',NULL,NULL),('150233388845966',NULL,'1502333888455.COM.jpg',NULL,'2017-08-10 02:58:08','2017-08-10 02:58:08',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502333888455.COM.jpg',NULL,NULL,NULL,NULL,NULL,51,'images/uassets/2017/8/10/1502333888455.COM.jpg',NULL,NULL),('150233457915951',NULL,'1502334579030.COM.jpg',NULL,'2017-08-10 03:09:39','2017-08-10 03:09:39',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502334579030.COM.jpg',NULL,NULL,NULL,NULL,NULL,53,'images/uassets/2017/8/10/1502334579030.COM.jpg',NULL,NULL),('150233499465914',NULL,'1502334994652.COM.jpg',NULL,'2017-08-10 03:16:35','2017-08-10 03:16:35',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502334994652.COM.jpg',NULL,NULL,NULL,NULL,NULL,54,'images/uassets/2017/8/10/1502334994652.COM.jpg',NULL,NULL),('150233512833591',NULL,'1502335128330.COM.jpg',NULL,'2017-08-10 03:18:48','2017-08-10 03:18:48',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502335128330.COM.jpg',NULL,NULL,NULL,NULL,NULL,55,'images/uassets/2017/8/10/1502335128330.COM.jpg',NULL,NULL),('150233598271260',NULL,'1502335982656.COM.jpg',NULL,'2017-08-10 03:33:02','2017-08-10 03:33:02',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502335982656.COM.jpg',NULL,NULL,NULL,NULL,NULL,56,'images/uassets/2017/8/10/1502335982656.COM.jpg',NULL,NULL),('150233761979843',NULL,'1502337619793.ch5.cc.jpg',NULL,'2017-08-10 04:00:19','2017-08-10 04:00:19',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337619793.ch5.cc.jpg',NULL,NULL,NULL,NULL,NULL,57,'images/uassets/2017/8/10/1502337619793.ch5.cc.jpg',NULL,NULL),('150233768403072',NULL,'1502337684024.COM.jpg',NULL,'2017-08-10 04:01:24','2017-08-10 04:01:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337684024.COM.jpg',NULL,NULL,NULL,NULL,NULL,58,'images/uassets/2017/8/10/1502337684024.COM.jpg',NULL,NULL),('150233782103077',NULL,'1502337821025.COM.gif',NULL,'2017-08-10 04:03:41','2017-08-10 04:03:41',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337821025.COM.gif',NULL,NULL,NULL,NULL,NULL,59,'images/uassets/2017/8/10/1502337821025.COM.gif',NULL,NULL),('150233793754245',NULL,'1502337937537.COM.gif',NULL,'2017-08-10 04:05:37','2017-08-10 04:05:37',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502337937537.COM.gif',NULL,NULL,NULL,NULL,NULL,60,'images/uassets/2017/8/10/1502337937537.COM.gif',NULL,NULL),('150233838791728',NULL,'1502338387910.COM.jpg',NULL,'2017-08-10 04:13:10','2017-08-10 04:13:10',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502338387910.COM.jpg',NULL,NULL,NULL,NULL,NULL,61,'images/uassets/2017/8/10/1502338387910.COM.jpg',NULL,NULL),('150233976693339',NULL,'1502339766912.COM.gif',NULL,'2017-08-10 04:36:07','2017-08-10 04:36:07',1,NULL,NULL,'E:\\软件\\wildfly-10.1.0.Final\\standalone\\deployments\\zhiwo-system-web.warimages\\uassets\\2017\\8\\10\\1502339766912.COM.gif',NULL,NULL,NULL,NULL,NULL,62,'images/uassets/2017/8/10/1502339766912.COM.gif',NULL,NULL),('150234702398617',NULL,'1502347023963.COM.gif',NULL,'2017-08-10 06:37:04','2017-08-10 06:37:04',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347023963.COM.gif',NULL,NULL,NULL,NULL,NULL,63,'images/uassets/2017/8/10/1502347023963.COM.gif',NULL,NULL),('150234710893628',NULL,'1502347108866.COM.gif',NULL,'2017-08-10 06:38:28','2017-08-10 06:38:28',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347108866.COM.gif',NULL,NULL,NULL,NULL,NULL,64,'images/uassets/2017/8/10/1502347108866.COM.gif',NULL,NULL),('150234716514832',NULL,'1502347165098.COM.gif',NULL,'2017-08-10 06:39:25','2017-08-10 06:39:25',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347165098.COM.gif',NULL,NULL,NULL,NULL,NULL,65,'images/uassets/2017/8/10/1502347165098.COM.gif',NULL,NULL),('150234731679131',NULL,'1502347316731.COM.gif',NULL,'2017-08-10 06:41:56','2017-08-10 06:41:56',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347316731.COM.gif',NULL,NULL,NULL,NULL,NULL,66,'images/uassets/2017/8/10/1502347316731.COM.gif',NULL,NULL),('150234756210021',NULL,'1502347562043.COM.gif',NULL,'2017-08-10 06:46:02','2017-08-10 06:46:02',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502347562043.COM.gif',NULL,NULL,NULL,NULL,NULL,67,'images/uassets/2017/8/10/1502347562043.COM.gif',NULL,NULL),('150234851548797',NULL,'1502348515483.COM.gif',NULL,'2017-08-10 07:01:55','2017-08-10 07:01:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502348515483.COM.gif',NULL,NULL,NULL,NULL,NULL,68,'images/uassets/2017/8/10/1502348515483.COM.gif',NULL,NULL),('150234883745072',NULL,'1502348837446.COM.gif',NULL,'2017-08-10 07:07:17','2017-08-10 07:07:17',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502348837446.COM.gif',NULL,NULL,NULL,NULL,NULL,69,'images/uassets/2017/8/10/1502348837446.COM.gif',NULL,NULL),('150235149273954',NULL,'1502351492732.COM.jpg',NULL,'2017-08-10 07:51:33','2017-08-10 07:51:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502351492732.COM.jpg',NULL,NULL,NULL,NULL,NULL,70,'images/uassets/2017/8/10/1502351492732.COM.jpg',NULL,NULL),('150235829247058',NULL,'1502358292463.JPG',NULL,'2017-08-10 09:44:52','2017-08-10 09:44:52',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502358292463.JPG',NULL,NULL,NULL,NULL,NULL,71,'images/uassets/2017/8/10/1502358292463.JPG',NULL,NULL),('150235913561222',NULL,'1502359135606.jpg',NULL,'2017-08-10 09:58:55','2017-08-10 09:58:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502359135606.jpg',NULL,NULL,NULL,NULL,NULL,72,'images/uassets/2017/8/10/1502359135606.jpg',NULL,NULL),('150236435862640',NULL,'1502364358603.jpg',NULL,'2017-08-10 11:25:58','2017-08-10 11:25:58',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\10\\1502364358603.jpg',NULL,NULL,NULL,NULL,NULL,73,'images/uassets/2017/8/10/1502364358603.jpg',NULL,NULL),('150241345781979',NULL,'1502413457729.jpg',NULL,'2017-08-11 01:04:18','2017-08-11 01:04:18',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502413457729.jpg',NULL,NULL,NULL,NULL,NULL,74,'images/uassets/2017/8/11/1502413457729.jpg',NULL,NULL),('150241461191046',NULL,'1502414611871.jpg',NULL,'2017-08-11 01:23:31','2017-08-11 01:23:31',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502414611871.jpg',NULL,NULL,NULL,NULL,NULL,75,'images/uassets/2017/8/11/1502414611871.jpg',NULL,NULL),('150241467326320',NULL,'1502414673207.jpg',NULL,'2017-08-11 01:24:33','2017-08-11 01:24:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502414673207.jpg',NULL,NULL,NULL,NULL,NULL,76,'images/uassets/2017/8/11/1502414673207.jpg',NULL,NULL),('150241999867811',NULL,'1502419998673.jpg',NULL,'2017-08-11 02:53:19','2017-08-11 02:53:19',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502419998673.jpg',NULL,NULL,NULL,NULL,NULL,77,'images/uassets/2017/8/11/1502419998673.jpg',NULL,NULL),('150242004220787',NULL,'1502420042192.jpeg',NULL,'2017-08-11 02:54:02','2017-08-11 02:54:02',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp6\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502420042192.jpeg',NULL,NULL,NULL,NULL,NULL,78,'images/uassets/2017/8/11/1502420042192.jpeg',NULL,NULL),('150242513408087',NULL,'1502425134024.jpg',NULL,'2017-08-11 04:18:54','2017-08-11 04:18:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502425134024.jpg',NULL,NULL,NULL,NULL,NULL,79,'images/uassets/2017/8/11/1502425134024.jpg',NULL,NULL),('150243367560912',NULL,'1502433675587.jpg',NULL,'2017-08-11 06:41:15','2017-08-11 06:41:15',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502433675587.jpg',NULL,NULL,NULL,NULL,NULL,80,'images/uassets/2017/8/11/1502433675587.jpg',NULL,NULL),('150243519383316',NULL,'1502435193826.jpg',NULL,'2017-08-11 07:06:34','2017-08-11 07:06:34',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502435193826.jpg',NULL,NULL,NULL,NULL,NULL,81,'images/uassets/2017/8/11/1502435193826.jpg',NULL,NULL),('150243744549536',NULL,'1502437445489.jpg',NULL,'2017-08-11 07:44:05','2017-08-11 07:44:05',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502437445489.jpg',NULL,NULL,NULL,NULL,NULL,82,'images/uassets/2017/8/11/1502437445489.jpg',NULL,NULL),('150243750765533',NULL,'1502437507627.gif',NULL,'2017-08-11 07:45:07','2017-08-11 07:45:07',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502437507627.gif',NULL,NULL,NULL,NULL,NULL,83,'images/uassets/2017/8/11/1502437507627.gif',NULL,NULL),('150244078702481',NULL,'1502440787019.jpg',NULL,'2017-08-11 08:39:47','2017-08-11 08:39:47',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502440787019.jpg',NULL,NULL,NULL,NULL,NULL,84,'images/uassets/2017/8/11/1502440787019.jpg',NULL,NULL),('150244970655574',NULL,'1502449706546.jpg',NULL,'2017-08-11 11:08:26','2017-08-11 11:08:26',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502449706546.jpg',NULL,NULL,NULL,NULL,NULL,85,'images/uassets/2017/8/11/1502449706546.jpg',NULL,NULL),('150244977893974',NULL,'1502449778933.jpg',NULL,'2017-08-11 11:09:38','2017-08-11 11:09:38',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502449778933.jpg',NULL,NULL,NULL,NULL,NULL,86,'images/uassets/2017/8/11/1502449778933.jpg',NULL,NULL),('150244978689351',NULL,'1502449786887.jpg',NULL,'2017-08-11 11:09:46','2017-08-11 11:09:46',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\11\\1502449786887.jpg',NULL,NULL,NULL,NULL,NULL,87,'images/uassets/2017/8/11/1502449786887.jpg',NULL,NULL),('15025292039925',NULL,'1502529203958.jpg',NULL,'2017-08-12 09:13:24','2017-08-12 09:13:24',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502529203958.jpg',NULL,NULL,NULL,NULL,NULL,88,'images/uassets/2017/8/12/1502529203958.jpg',NULL,NULL),('150252979084234',NULL,'1502529790774.jpg',NULL,'2017-08-12 09:23:11','2017-08-12 09:23:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502529790774.jpg',NULL,NULL,NULL,NULL,NULL,89,'images/uassets/2017/8/12/1502529790774.jpg',NULL,NULL),('150253015351321',NULL,'1502530153474.jpg',NULL,'2017-08-12 09:29:13','2017-08-12 09:29:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502530153474.jpg',NULL,NULL,NULL,NULL,NULL,90,'images/uassets/2017/8/12/1502530153474.jpg',NULL,NULL),('150253020598419',NULL,'1502530205924.jpg',NULL,'2017-08-12 09:30:06','2017-08-12 09:30:06',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502530205924.jpg',NULL,NULL,NULL,NULL,NULL,91,'images/uassets/2017/8/12/1502530205924.jpg',NULL,NULL),('150253026642943',NULL,'1502530266424.jpg',NULL,'2017-08-12 09:31:06','2017-08-12 09:31:06',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502530266424.jpg',NULL,NULL,NULL,NULL,NULL,92,'images/uassets/2017/8/12/1502530266424.jpg',NULL,NULL),('150253127544349',NULL,'1502531275385.jpg',NULL,'2017-08-12 09:47:55','2017-08-12 09:47:55',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502531275385.jpg',NULL,NULL,NULL,NULL,NULL,93,'images/uassets/2017/8/12/1502531275385.jpg',NULL,NULL),('150253129814561',NULL,'1502531298140.jpg',NULL,'2017-08-12 09:48:18','2017-08-12 09:48:18',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502531298140.jpg',NULL,NULL,NULL,NULL,NULL,94,'images/uassets/2017/8/12/1502531298140.jpg',NULL,NULL),('150253599038014',NULL,'1502535990354.jpg',NULL,'2017-08-12 11:06:30','2017-08-12 11:06:30',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502535990354.jpg',NULL,NULL,NULL,NULL,NULL,95,'images/uassets/2017/8/12/1502535990354.jpg',NULL,NULL),('150253656671855',NULL,'1502536566713.jpg',NULL,'2017-08-12 11:16:07','2017-08-12 11:16:07',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502536566713.jpg',NULL,NULL,NULL,NULL,NULL,96,'images/uassets/2017/8/12/1502536566713.jpg',NULL,NULL),('150254091663445',NULL,'1502540916163.jpg',NULL,'2017-08-12 12:28:36','2017-08-12 12:28:36',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502540916163.jpg',NULL,NULL,NULL,NULL,NULL,97,'images/uassets/2017/8/12/1502540916163.jpg',NULL,NULL),('15025411459114',NULL,'1502541145906.jpg',NULL,'2017-08-12 12:32:25','2017-08-12 12:32:25',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502541145906.jpg',NULL,NULL,NULL,NULL,NULL,98,'images/uassets/2017/8/12/1502541145906.jpg',NULL,NULL),('150254163378243',NULL,'1502541633776.jpg',NULL,'2017-08-12 12:40:33','2017-08-12 12:40:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502541633776.jpg',NULL,NULL,NULL,NULL,NULL,99,'images/uassets/2017/8/12/1502541633776.jpg',NULL,NULL),('150254249406418',NULL,'1502542494009.jpg',NULL,'2017-08-12 12:54:54','2017-08-12 12:54:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502542494009.jpg',NULL,NULL,NULL,NULL,NULL,100,'images/uassets/2017/8/12/1502542494009.jpg',NULL,NULL),('150254293183985',NULL,'1502542931834.jpg',NULL,'2017-08-12 13:02:11','2017-08-12 13:02:11',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502542931834.jpg',NULL,NULL,NULL,NULL,NULL,101,'images/uassets/2017/8/12/1502542931834.jpg',NULL,NULL),('150254390099838',NULL,'1502543900991.jpg',NULL,'2017-08-12 13:18:21','2017-08-12 13:18:21',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\12\\1502543900991.jpg',NULL,NULL,NULL,NULL,NULL,102,'images/uassets/2017/8/12/1502543900991.jpg',NULL,NULL),('150268160216529',NULL,'1502681602155.jpg',NULL,'2017-08-14 03:33:22','2017-08-14 03:33:22',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\14\\1502681602155.jpg',NULL,NULL,NULL,NULL,NULL,103,'images/uassets/2017/8/14/1502681602155.jpg',NULL,NULL),('150268444857038',NULL,'1502684448564.jpg',NULL,'2017-08-14 04:20:48','2017-08-14 04:20:48',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\14\\1502684448564.jpg',NULL,NULL,NULL,NULL,NULL,104,'images/uassets/2017/8/14/1502684448564.jpg',NULL,NULL),('15027032203839',NULL,'1502703220376.jpg',NULL,'2017-08-14 09:33:40','2017-08-14 09:33:40',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\14\\1502703220376.jpg',NULL,NULL,NULL,NULL,NULL,105,'images/uassets/2017/8/14/1502703220376.jpg',NULL,NULL),('150310003164596',NULL,'1503100031577.jpg',NULL,'2017-08-18 23:47:12','2017-08-18 23:47:12',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\19\\1503100031577.jpg',NULL,NULL,NULL,NULL,NULL,106,'images/uassets/2017/8/19/1503100031577.jpg',NULL,NULL),('150310035591784',NULL,'1503100355911.jpg',NULL,'2017-08-18 23:52:35','2017-08-18 23:52:35',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\19\\1503100355911.jpg',NULL,NULL,NULL,NULL,NULL,107,'images/uassets/2017/8/19/1503100355911.jpg',NULL,NULL),('150310473362841',NULL,'1503104733603.jpg',NULL,'2017-08-19 01:05:33','2017-08-19 01:05:33',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\19\\1503104733603.jpg',NULL,NULL,NULL,NULL,NULL,108,'images/uassets/2017/8/19/1503104733603.jpg',NULL,NULL),('15032468671632',NULL,'1503246867136.jpg',NULL,'2017-08-20 16:34:27','2017-08-20 16:34:27',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\21\\1503246867136.jpg',NULL,NULL,NULL,NULL,NULL,109,'images/uassets/2017/8/21/1503246867136.jpg',NULL,NULL),('150327743398683',NULL,'1503277433981.jpg',NULL,'2017-08-21 01:03:54','2017-08-21 01:03:54',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\21\\1503277433981.jpg',NULL,NULL,NULL,NULL,NULL,110,'images/uassets/2017/8/21/1503277433981.jpg',NULL,NULL),('150366547266580',NULL,'1503665472644.jpg',NULL,'2017-08-25 12:51:13','2017-08-25 12:51:13',1,NULL,NULL,'D:\\zwoweb_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp8\\wtpwebapps\\zhiwo-system-web\\images\\uassets\\2017\\8\\25\\1503665472644.jpg',NULL,NULL,NULL,NULL,NULL,111,'images/uassets/2017/8/25/1503665472644.jpg',NULL,NULL),('150375225313983',NULL,'1503752253115.jpg',NULL,'2017-08-26 12:57:33','2017-08-26 12:57:33',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\26\\1503752253115.jpg',NULL,NULL,NULL,NULL,NULL,112,'images/uassets/2017/8/26/1503752253115.jpg',NULL,NULL),('150376063239576',NULL,'1503760632386.jpg',NULL,'2017-08-26 15:17:12','2017-08-26 15:17:12',1,NULL,NULL,'D:\\uassets\\2017\\8\\26\\1503760632386.jpg',NULL,NULL,NULL,NULL,NULL,113,'uassets/2017/8/26/1503760632386.jpg',NULL,NULL),('150376081559413',NULL,'1503760815589.jpg',NULL,'2017-08-26 15:20:16','2017-08-26 15:20:16',1,NULL,NULL,'D:\\uassets\\2017\\8\\26\\1503760815589.jpg',NULL,NULL,NULL,NULL,NULL,114,'uassets/2017/8/26/1503760815589.jpg',NULL,NULL),('150376106167372',NULL,'1503761061663.jpg',NULL,'2017-08-26 15:24:21','2017-08-26 15:24:21',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\26\\1503761061663.jpg',NULL,NULL,NULL,NULL,NULL,115,'uassets/2017/8/26/1503761061663.jpg',NULL,NULL),('150376112665213',NULL,'1503761126647.jpg',NULL,'2017-08-26 15:25:26','2017-08-26 15:25:26',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\26\\1503761126647.jpg',NULL,NULL,NULL,NULL,NULL,116,'uassets/2017/8/26/1503761126647.jpg',NULL,NULL),('150376121124848',NULL,'1503761211244.jpg',NULL,'2017-08-26 15:26:51','2017-08-26 15:26:51',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\26\\1503761211244.jpg',NULL,NULL,NULL,NULL,NULL,117,'uassets/2017/8/26/1503761211244.jpg',NULL,NULL),('150380706222853',NULL,'1503807062182.jpg',NULL,'2017-08-27 04:11:02','2017-08-27 04:11:02',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\27\\1503807062182.jpg',NULL,NULL,NULL,NULL,NULL,118,'uassets/2017/8/27/1503807062182.jpg',NULL,NULL),('150408381442598',NULL,'1504083814398.webp',NULL,'2017-08-30 09:03:35','2017-08-30 09:03:35',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504083814398.webp',NULL,NULL,NULL,NULL,NULL,119,'uassets/2017/8/30/1504083814398.webp',NULL,NULL),('150409073258486',NULL,'1504090707199.webp','1504085428827','2017-08-30 10:58:58','2017-08-30 10:58:58',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504090707199.webp',NULL,NULL,NULL,NULL,NULL,120,'uassets/2017/8/30/1504090707199.webp',NULL,NULL),('150409090906036',NULL,'1504091064401.webp','149879109000689','2017-08-30 11:01:55','2017-08-30 11:01:55',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091064401.webp',NULL,NULL,NULL,NULL,NULL,121,'uassets/2017/8/30/1504091064401.webp',NULL,NULL),('150409137048038',NULL,'1504091315778.webp','1504091114549','2017-08-30 11:09:30','2017-08-30 11:09:30',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091315778.webp',NULL,NULL,NULL,NULL,NULL,122,'uassets/2017/8/30/1504091315778.webp',NULL,NULL),('150409144304828',NULL,'1504091443006.webp','1504091377795','2017-08-30 11:10:43','2017-08-30 11:10:43',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091443006.webp',NULL,NULL,NULL,NULL,NULL,123,'uassets/2017/8/30/1504091443006.webp',NULL,NULL),('150409147811246',NULL,'1504091478076.webp','1504091445504','2017-08-30 11:11:18','2017-08-30 11:11:18',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091478076.webp',NULL,NULL,NULL,NULL,NULL,124,'uassets/2017/8/30/1504091478076.webp',NULL,NULL),('150409152223672',NULL,'1504091522199.webp','1504091490955','2017-08-30 11:12:02','2017-08-30 11:12:02',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091522199.webp',NULL,NULL,NULL,NULL,NULL,125,'uassets/2017/8/30/1504091522199.webp',NULL,NULL),('150409155720735',NULL,'1504091557168.webp','1504091527135','2017-08-30 11:12:37','2017-08-30 11:12:37',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091557168.webp',NULL,NULL,NULL,NULL,NULL,126,'uassets/2017/8/30/1504091557168.webp',NULL,NULL),('15040917752688',NULL,'1504091775222.webp','1504091709763','2017-08-30 11:16:15','2017-08-30 11:16:15',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091775222.webp',NULL,NULL,NULL,NULL,NULL,127,'uassets/2017/8/30/1504091775222.webp',NULL,NULL),('150409197379638',NULL,'1504091973745.webp','1504091934763','2017-08-30 11:19:33','2017-08-30 11:19:33',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504091973745.webp',NULL,NULL,NULL,NULL,NULL,128,'uassets/2017/8/30/1504091973745.webp',NULL,NULL),('150409249248618',NULL,'1504092492444.webp','1504092428839','2017-08-30 11:28:12','2017-08-30 11:28:12',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504092492444.webp',NULL,NULL,NULL,NULL,NULL,129,'uassets/2017/8/30/1504092492444.webp',NULL,NULL),('150409264499917',NULL,'1504092644961.webp','1504092596212','2017-08-30 11:30:45','2017-08-30 11:30:45',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504092644961.webp',NULL,NULL,NULL,NULL,NULL,130,'uassets/2017/8/30/1504092644961.webp',NULL,NULL),('150409283185526',NULL,'1504092831809.webp','1504092768188','2017-08-30 11:33:51','2017-08-30 11:33:51',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504092831809.webp',NULL,NULL,NULL,NULL,NULL,131,'uassets/2017/8/30/1504092831809.webp',NULL,NULL),('150409288076716',NULL,'1504092880728.webp','1504092847772','2017-08-30 11:34:40','2017-08-30 11:34:40',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504092880728.webp',NULL,NULL,NULL,NULL,NULL,132,'uassets/2017/8/30/1504092880728.webp',NULL,NULL),('150409293049692',NULL,'1504092930446.webp','1504092888385','2017-08-30 11:35:30','2017-08-30 11:35:30',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504092930446.webp',NULL,NULL,NULL,NULL,NULL,133,'uassets/2017/8/30/1504092930446.webp',NULL,NULL),('150409296671218',NULL,'1504092966674.webp','1504092936041','2017-08-30 11:36:06','2017-08-30 11:36:06',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504092966674.webp',NULL,NULL,NULL,NULL,NULL,134,'uassets/2017/8/30/1504092966674.webp',NULL,NULL),('150409311073849',NULL,'1504093110702.webp','1504093070005','2017-08-30 11:38:30','2017-08-30 11:38:30',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093110702.webp',NULL,NULL,NULL,NULL,NULL,135,'uassets/2017/8/30/1504093110702.webp',NULL,NULL),('150409316515364',NULL,'1504093165113.webp','1504093116278','2017-08-30 11:39:25','2017-08-30 11:39:25',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093165113.webp',NULL,NULL,NULL,NULL,NULL,136,'uassets/2017/8/30/1504093165113.webp',NULL,NULL),('150409324074034',NULL,'1504093240704.webp','1504093178176','2017-08-30 11:40:40','2017-08-30 11:40:40',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093240704.webp',NULL,NULL,NULL,NULL,NULL,137,'uassets/2017/8/30/1504093240704.webp',NULL,NULL),('15040932827064',NULL,'1504093282668.webp','1504093246711','2017-08-30 11:41:22','2017-08-30 11:41:22',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093282668.webp',NULL,NULL,NULL,NULL,NULL,138,'uassets/2017/8/30/1504093282668.webp',NULL,NULL),('15040935595072',NULL,'1504093559472.webp','1504093484290','2017-08-30 11:45:59','2017-08-30 11:45:59',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093559472.webp',NULL,NULL,NULL,NULL,NULL,139,'uassets/2017/8/30/1504093559472.webp',NULL,NULL),('150409358955095',NULL,'1504093589511.webp','1504093568256','2017-08-30 11:46:29','2017-08-30 11:46:29',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093589511.webp',NULL,NULL,NULL,NULL,NULL,140,'uassets/2017/8/30/1504093589511.webp',NULL,NULL),('150409361999567',NULL,'1504093619958.webp','1504093596477','2017-08-30 11:47:00','2017-08-30 11:47:00',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093619958.webp',NULL,NULL,NULL,NULL,NULL,141,'uassets/2017/8/30/1504093619958.webp',NULL,NULL),('150409365445589',NULL,'1504093654420.webp','1504093624461','2017-08-30 11:47:34','2017-08-30 11:47:34',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093654420.webp',NULL,NULL,NULL,NULL,NULL,142,'uassets/2017/8/30/1504093654420.webp',NULL,NULL),('150409374235787',NULL,'1504093742321.webp','1504093715778','2017-08-30 11:49:02','2017-08-30 11:49:02',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093742321.webp',NULL,NULL,NULL,NULL,NULL,143,'uassets/2017/8/30/1504093742321.webp',NULL,NULL),('15040937715253',NULL,'1504093771491.webp','1504093749733','2017-08-30 11:49:31','2017-08-30 11:49:31',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093771491.webp',NULL,NULL,NULL,NULL,NULL,144,'uassets/2017/8/30/1504093771491.webp',NULL,NULL),('150409379056456',NULL,'1504093790528.webp','1504093776997','2017-08-30 11:49:50','2017-08-30 11:49:50',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093790528.webp',NULL,NULL,NULL,NULL,NULL,145,'uassets/2017/8/30/1504093790528.webp',NULL,NULL),('150409382655651',NULL,'1504093826522.webp','1504093803955','2017-08-30 11:50:26','2017-08-30 11:50:26',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093826522.webp',NULL,NULL,NULL,NULL,NULL,146,'uassets/2017/8/30/1504093826522.webp',NULL,NULL),('150409392856457',NULL,'1504093928525.webp','1504093892650','2017-08-30 11:52:08','2017-08-30 11:52:08',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093928525.webp',NULL,NULL,NULL,NULL,NULL,147,'uassets/2017/8/30/1504093928525.webp',NULL,NULL),('150409395070995',NULL,'1504093950652.webp','1504093933686','2017-08-30 11:52:30','2017-08-30 11:52:30',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504093950652.webp',NULL,NULL,NULL,NULL,NULL,148,'uassets/2017/8/30/1504093950652.webp',NULL,NULL),('150409416703972',NULL,'1504094167005.webp','1504094118365','2017-08-30 11:56:07','2017-08-30 11:56:07',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504094167005.webp',NULL,NULL,NULL,NULL,NULL,149,'uassets/2017/8/30/1504094167005.webp',NULL,NULL),('15040942321685',NULL,'1504094232067.webp','1504094173900','2017-08-30 11:57:12','2017-08-30 11:57:12',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504094232067.webp',NULL,NULL,NULL,NULL,NULL,150,'uassets/2017/8/30/1504094232067.webp',NULL,NULL),('150409427079164',NULL,'1504094270750.webp','1504094237825','2017-08-30 11:57:50','2017-08-30 11:57:50',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504094270750.webp',NULL,NULL,NULL,NULL,NULL,151,'uassets/2017/8/30/1504094270750.webp',NULL,NULL),('150409430180614',NULL,'1504094301691.webp','1504094276144','2017-08-30 11:58:21','2017-08-30 11:58:21',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504094301691.webp',NULL,NULL,NULL,NULL,NULL,152,'uassets/2017/8/30/1504094301691.webp',NULL,NULL),('150409755135332',NULL,'1504097551167.webp','1504094310402','2017-08-30 12:52:31','2017-08-30 12:52:31',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504097551167.webp',NULL,NULL,NULL,NULL,NULL,153,'uassets/2017/8/30/1504097551167.webp',NULL,NULL),('150409803273478',NULL,'1504098032698.webp','1504097555422','2017-08-30 13:00:32','2017-08-30 13:00:32',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504098032698.webp',NULL,NULL,NULL,NULL,NULL,154,'uassets/2017/8/30/1504098032698.webp',NULL,NULL),('150409806169972',NULL,'1504098061664.webp','1504098034126','2017-08-30 13:01:01','2017-08-30 13:01:01',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504098061664.webp',NULL,NULL,NULL,NULL,NULL,155,'uassets/2017/8/30/1504098061664.webp',NULL,NULL),('150409864940128',NULL,'1504098649350.webp','1504098065074','2017-08-30 13:10:49','2017-08-30 13:10:49',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504098649350.webp',NULL,NULL,NULL,NULL,NULL,156,'uassets/2017/8/30/1504098649350.webp',NULL,NULL),('150409890203567',NULL,'1504098901987.webp','1504098864613','2017-08-30 13:15:02','2017-08-30 13:15:02',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504098901987.webp',NULL,NULL,NULL,NULL,NULL,157,'uassets/2017/8/30/1504098901987.webp',NULL,NULL),('150409894013195',NULL,'1504098940088.webp','1504098903180','2017-08-30 13:15:40','2017-08-30 13:15:40',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504098940088.webp',NULL,NULL,NULL,NULL,NULL,158,'uassets/2017/8/30/1504098940088.webp',NULL,NULL),('15040995103249',NULL,'1504099510287.webp','1504099380925','2017-08-30 13:25:10','2017-08-30 13:25:10',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504099510287.webp',NULL,NULL,NULL,NULL,NULL,159,'uassets/2017/8/30/1504099510287.webp',NULL,NULL),('15040995420061',NULL,'1504099541970.webp','1504099516589','2017-08-30 13:25:42','2017-08-30 13:25:42',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504099541970.webp',NULL,NULL,NULL,NULL,NULL,160,'uassets/2017/8/30/1504099541970.webp',NULL,NULL),('150409965180363',NULL,'1504099651766.webp','1504099546107','2017-08-30 13:27:31','2017-08-30 13:27:31',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504099651766.webp',NULL,NULL,NULL,NULL,NULL,161,'uassets/2017/8/30/1504099651766.webp',NULL,NULL),('150409969087144',NULL,'1504099690777.webp','1504099657133','2017-08-30 13:28:10','2017-08-30 13:28:10',1,NULL,NULL,'D:\\images\\uassets\\2017\\8\\30\\1504099690777.webp',NULL,NULL,NULL,NULL,NULL,162,'uassets/2017/8/30/1504099690777.webp',NULL,NULL);
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
