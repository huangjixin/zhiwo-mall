package com.fulan.api.paper.vo;

import java.util.List;

import com.fulan.api.paper.domain.er.PaperItemInfo;

import lombok.Data;
@Data
public class PaperItemInfoVo {
	private PaperItemPVo paperItemPVo;
	private List<PaperItemInfo> paperItemInfo;
}
