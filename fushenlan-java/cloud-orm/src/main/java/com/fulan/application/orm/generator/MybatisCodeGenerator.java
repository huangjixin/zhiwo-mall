package com.fulan.application.orm.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MybatisCodeGenerator {
	
	/**
	 * 修改生成配置
	 * */
	public static String dbUrl = "jdbc:mysql://192.168.0.73:3306/cloud_erecruitment?characterEncoding=utf8";
	
	public static String dbName = "root";
	
	public static String dbPassword = "qwe123";
	
	public static String[] removePreTableName = new String[] {"er_" };
	
	//生成路径
	public static String parentpackage = "com.fulan.api.erecruitment.paper";
	//public static String parentpackage = "com.fulan.api.erecruitment.personnel";
	//public static String parentpackage = "com.fulan.api.erecruitment.flow";
	
	//需要执行生成策略的表
	public static String[] tables = new String[] { 
			"er_apply",
			"er_educational",
			"er_family_member",
			"er_flow",
			"er_flow_action",
			"er_flow_item",
			"er_interview_action",
			"er_paper",
			"er_paper_item",
			"er_paper_item_info",
			"er_personnel",
			"er_personnel_paper",
			"er_personnel_paper_info",
			"er_tag",
			"er_work_experience"};
	
	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("D://mybatis-auto-code");
		gc.setFileOverride(true);
		gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setAuthor("fulan");

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				// 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername(dbName);
		dsc.setPassword(dbPassword);
		dsc.setUrl(dbUrl);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setTablePrefix(removePreTableName);// 此处可以修改移除表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(tables); // 需要生成的表
		strategy.setEntityLombokModel(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(parentpackage);
		pc.setModuleName("");
		pc.setEntity("domain");
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};

		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		// 调整 xml 生成目录演示
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		tc.setEntity("/template/domain.java.vm");
		mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();

	}

}