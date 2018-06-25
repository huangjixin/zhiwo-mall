package com.fulan.api.paper.vo;

import com.fulan.api.paper.domain.er.Paper;
import lombok.Data;

import java.util.List;
@Data
public class PaperVo {
	private Paper paper;
	
	private List<PaperItemVo> paperItemVo;
}
