package com.fulan.application.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DataTag  extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Map<String,Object> tagMap = new HashMap<>();
	
	public DataTag(){
		tagMap.put("1","初选面试");
		tagMap.put("2","甄选面试");
		tagMap.put("3","决定面试");
	}
	private String id;//添加的属性
    public void setId(String id) {
        this.id = id;
    }
    private String option;//添加的属性
    public void setOption(String option) {
    	this.option = option;
    }
    private String type;//添加的属性
    public void setType(String type) {
    	this.type = type;
    }
    public int doStartTag(){         
        try{  
        	JspWriter out=pageContext.getOut(); 
        	 if(null == type || "".equals(type)){
        		 out.println(tagMap.get(id));
        	 }
        	 if("option".equals(type)){
        		 out.println((char)(Integer.valueOf(option)+64));
        	 }
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
        return SKIP_BODY;      
    }
    public int doEndTag() throws JspException{
        return EVAL_PAGE;
    }
}
