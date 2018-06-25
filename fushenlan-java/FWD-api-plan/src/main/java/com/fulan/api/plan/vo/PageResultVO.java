package com.fulan.api.plan.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Api(tags = "PageResultVO", description = "线下活动")
public class PageResultVO {

	@ApiModelProperty(value = "当前页数",name="currentPage")
	private Integer currentPage;

	@ApiModelProperty(value = "显示条数",name="pageSize")
	private Integer pageSize;

	@ApiModelProperty(value = "总页数",name="totalPage")
	private Integer totalPage;

	@ApiModelProperty(value = "模型",name="data")
	private List<?> data;

	@ApiModelProperty(value = "共多少条",name="count")
	private Integer count;

	public PageResultVO(Integer currentPage, Integer pageSize, List<?> data, Integer count) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.data = data;
		this.count = count;
		this.totalPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;

	}

	public static PageResultVO empty(Integer pageSize) {
		return new PageResultVO(1, pageSize, new ArrayList(), 0);
	}

	public Integer getTotalPage() {
		return totalPage == 0 ? 1 : totalPage;
	}
}
