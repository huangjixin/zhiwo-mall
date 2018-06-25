package com.fulan.api.achievement.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(tags = "PromoteCheckVo", description = "指标考核VO")
public class PromoteCheckVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8799563159206571103L;

	private Integer gradeStandard;// 职级标准

	private String checkTime;// 考核时间，晋升时间

	private String serverTime;//服务器时间

	private String currentLevelName;// 当前职级

	private String currentShortLevelName;// 当前职级简称

	private String nextLevelName;// 下一职级

	private String nextShortLevelName;// 下一职级简称

	private List<IndexVo> indexVos;

}
