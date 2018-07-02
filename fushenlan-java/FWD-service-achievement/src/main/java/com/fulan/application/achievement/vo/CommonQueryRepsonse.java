package com.fulan.application.achievement.vo;

import lombok.Data;

@Data
public class CommonQueryRepsonse<T> {
	private String statusCode;
	private String statusMessage;
	private T response;
}
