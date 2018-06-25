package com.fulan.application.error.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: shenzhongwu
 * @Date: 2018/1/15 12:52
 */
@Controller
public class WebErrorController implements ErrorController{
	private static final String ERROR_PATH = "/error"; 
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return ERROR_PATH;
	}
	
	@RequestMapping(value=ERROR_PATH) 
	public String handleError(){ 
	      return "errorpage/error-404"; 
	} 

   
}  