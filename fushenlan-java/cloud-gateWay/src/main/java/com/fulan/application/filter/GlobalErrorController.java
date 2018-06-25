package com.fulan.application.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonMapper;
import com.netflix.zuul.context.RequestContext;

@RestController
@RequestMapping("/error")
public class GlobalErrorController extends AbstractErrorController {

	public GlobalErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(produces = MediaType.ALL_VALUE)
	public String error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		if (status.is5xxServerError()) {
			// log maybe
		}
		RequestContext ctx = RequestContext.getCurrentContext();
		// 响应异常数据
		return JsonMapper.toJsonString(new Response<Object>(ctx.get("error.status_code")+"", ctx.get("error.message")+""));
	}

	public String getErrorPath() {
		return "/error";
	}

}
