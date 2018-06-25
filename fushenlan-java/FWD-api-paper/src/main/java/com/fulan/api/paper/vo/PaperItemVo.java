package com.fulan.api.paper.vo;

import com.fulan.api.paper.domain.er.PaperItem;
import com.fulan.api.paper.domain.er.PaperItemInfo;
import lombok.Data;

import java.util.List;
@Data
public class PaperItemVo {
	private PaperItem paperItem;
	private List<PaperItemInfo> paperItemInfo;
}
