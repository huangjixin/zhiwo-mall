package com.fulan.api.paper.vo;



import lombok.Data;

import java.io.Serializable;


@Data
public class PaperPlanNameVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String ClassName;

	private String publicName;
	
	private String studyName;
	
	private String postName;
	
	private String compulsoryName;
}
