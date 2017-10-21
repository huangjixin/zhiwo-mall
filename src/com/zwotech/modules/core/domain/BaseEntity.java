package com.zwotech.modules.core.domain;

public abstract class BaseEntity {
	private String templateName = this.getClass().getName();
	
	public  String name = getTemplateName();
	/**
	 * @return the getTemplateName
	 */
	public String getTemplateName() {
		String name = null;
		int dotIndex = -1;
		
		name = this.templateName;
		dotIndex = name.lastIndexOf(".");
		if(dotIndex != -1){
			name = name.substring(dotIndex+1, name.length());
			if(name.length()>1){
				name = name.substring(0, 1).toLowerCase()+name.substring(1, name.length());
			}else{
				name = name.toLowerCase();
			}
		}
		return name;
	}

	/**
	 * @param templateName 要设置的  templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
