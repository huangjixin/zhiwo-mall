package com.fulan.api.system.condition;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: shengchenglong
 * @Date: 2018/1/12 11:27
 */
@Getter
@Setter
public class DictionaryCondition {

    private Long id;

    private String code;

    private Boolean enable;

    private Integer layer;

    private Long rootId;

}
