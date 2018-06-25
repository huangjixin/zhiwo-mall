package com.fulan.api.erecruitment.paper.vo;

import java.util.List;

import com.fulan.api.erecruitment.paper.domain.PaperItem;
import com.fulan.api.erecruitment.paper.domain.PaperItemInfo;

import lombok.Data;
@Data
public class PaperItemVo {
	private PaperItem paperItem;
	private List<PaperItemInfo> paperItemInfo;
}
