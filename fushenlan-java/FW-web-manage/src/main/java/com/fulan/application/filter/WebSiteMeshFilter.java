package com.fulan.application.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
/**
 * jsp页面装饰器
 * @author shenzhongwu
 * **/
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
	/**
	 * 方法addDecoratorPath(param1,param2)可以进行增加装饰页面,param1为装饰路径,param2为装饰页面路径
	 * 方法addExcludedPath(param)将当前装饰页面从装饰器中移除
	 * **/
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    	for(String[] url:urls){
    		builder.addDecoratorPath(url[0], url[1]);
    		if(url.length>2){
    			for(int i=2;i<url.length;i++){
    				builder.addExcludedPath(url[i]);
    			}
    		}
    	}
    }
    /**
     * 二维数组中，每一个数组前两个位置为配置装饰使用，其他为去除装饰效果使用
     * **/
    String[][] urls = new String[][]{
    	{	
    		//装饰全部访问路径
    		"/*",
    		//后台主体装饰器页面
    		"/WEB-INF/jsp/decorators/index.jsp",
    		//登录
    		"/login",
    		//首页
    		"/main",
    		"/layerdemo",
    		"/manageAccounts/openRoles",
    		"/manage/role/resourceLayer",
    		"/manage/elearning/selectOne",
    		"/manage/elearning/selectPaper",
    		"/manage/elearning/selectLineOne",
    		"/manage/findByRoleId",
    		"/manage/paper/selectQuestion",
    		"/manage/elearning/choosePerson",
    		"/manage/classPlan/courseList",
    		"/manage/classPlan/lecturerList",
    		"/manage/classPlan/paperList",
    		"/manage/studyPlanManage/onlineCourse_add",
    		"/manage/studyPlanManage/testPaper_add",
    		"/manage/studyPlanManage/findByRoleId",
    		"/manage/publicMaterialListByCourse",
    		"/manage/classPlan/choosePerson",
    		"/manage/development/selectPaper",
    		"/manage/development/selectCourse",
    		"/manage/offlineManage/addCourse",
    		"/manage/classPlan/choosePerson",
    		"/manage/offlineManage/choosePerson",
    		"/manage/question/addQuestionOther"

    	}
    };

}
