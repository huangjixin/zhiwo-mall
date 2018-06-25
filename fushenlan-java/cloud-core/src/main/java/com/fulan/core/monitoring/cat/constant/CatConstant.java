package com.fulan.core.monitoring.cat.constant;
/**
 * URL
 * type,Server,Method
 * Service
 * type,Method,Params
 * Remote
 * type,Url,Method,Params
 * @author c_liziqing
 *
 */
public enum CatConstant {
	
	TYPE_URL("URL"),TYPE_SERVICE("Service"),TYPE_REMOTE("Remote"),TYPE_SQL("SQL"),
	TYPE_REMOTE_REST("RestTemplate"),TYPE_REMOTE_HTTP("HttpClient"),TYPE_REMOTE_FEIGN("Feign");
	private CatConstant(String type){
        this.type = type;
    }
	
	private String type;

	public String value() {
		return type;
	}
	
}
