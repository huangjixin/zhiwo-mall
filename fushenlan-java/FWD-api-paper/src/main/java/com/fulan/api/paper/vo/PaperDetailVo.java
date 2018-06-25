package com.fulan.api.paper.vo;

import lombok.Data;

import java.util.List;
@Data
public class PaperDetailVo {
	
	private String paperName;
	
	private int paperType;
	
	private String evaluate;
	
	List<PaperInfoVo> paperInfoList;
}
