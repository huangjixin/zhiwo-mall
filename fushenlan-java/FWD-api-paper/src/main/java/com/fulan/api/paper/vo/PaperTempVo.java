package com.fulan.api.paper.vo;

import java.util.List;

import lombok.Data;
@Data
public class PaperTempVo {
	private PaperPVo paperPVo;
	
	private List<PaperItemVo> paperItemVo;
}
