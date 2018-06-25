package com.fulan.api.paper.vo;

import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuestion;
import lombok.Data;

import java.util.List;
@Data
public class PaperQuestionVo {
	private Paper paper;
	
	private List<PaperQuestion> paperQuestionList;
}
