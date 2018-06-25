package com.fulan.api.agent.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 代理人信息
 * </p>
 *
 * @since 2018-03-20
 */
@Data
public class ValidateUserReq  implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
	private String userName;

	
	
}
