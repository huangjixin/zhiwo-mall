package com.fulan.application.orm.page;

import java.io.Serializable;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询数据响应
 * @author scotthu
 *
 */
@Api(tags = "pageResponse", description = "分页查询数据响应")
public class PageResponse  implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "1";
	public static final String SUCCESS_MESSAGE = "操作成功！";
	public static final String ERROR = "0";
	public static final String ERROR_MESSAGE = "服务异常！";

	@ApiModelProperty(notes = "响应代码  1:成功 0:失败",required=true)
	private String code = "1";
	@ApiModelProperty(notes = "响应信息",required=true)
	private String msg;
	@ApiModelProperty(notes = "结果数据")
	private transient Object data;
	@ApiModelProperty(notes = "分页信息")
	private transient PageInfo page;

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public PageResponse(String resultCode, String resultMsg) {
		super();
		this.setCode(resultCode);
		this.setMsg(resultMsg);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public PageResponse() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * 返回成功查询分页响应
	 * @param oldPage
	 * @return
	 */
	public  static PageResponse ok(Page oldPage){
		PageResponse resp=new PageResponse(PageResponse.SUCCESS, "查询成功");
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(oldPage.getCurrent());
		pageInfo.setPageSize(oldPage.getSize());
		pageInfo.setPageTotal(oldPage.getTotal());
		pageInfo.setPages(oldPage.getPages());
		resp.setPage(pageInfo);
		
		resp.setData(oldPage.getRecords());
		return resp;
	}
	
	/**
	 * 返回失败查询响应
	 * @param oldPage
	 * @return
	 */
	public  static PageResponse error(){
		return new PageResponse(PageResponse.ERROR, "查询失败");
	}
}
