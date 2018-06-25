package com.fulan.api.erecruitment.paper.vo;

import java.util.List;

import com.fulan.api.erecruitment.paper.domain.Paper;

import lombok.Data;
@Data
public class PaperVo {
	private Paper paper;
	
	private List<PaperItemVo> paperItemVo;
}
