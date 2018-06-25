package com.fulan.api.paper.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import lombok.Data;
@Data
public class PaperInfoVo {
	
	private String paperItemName;
	
	private Integer totalScore;
	
	private Integer warningScore;
	
	private String paperItemDesc;
	
	private Integer seq;
	
	private Integer paperType;
	
	private Integer score;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long paperItemId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long paperId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long personnelId;
}
