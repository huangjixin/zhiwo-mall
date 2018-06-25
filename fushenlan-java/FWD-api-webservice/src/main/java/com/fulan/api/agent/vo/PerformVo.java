package com.fulan.api.agent.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PerformVo {

    private String indexName;

    private String code;

    private BigDecimal actualVal;
}
