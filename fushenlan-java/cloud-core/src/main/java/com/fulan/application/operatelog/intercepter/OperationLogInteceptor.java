package com.fulan.application.operatelog.intercepter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.application.operatelog.constant.QueueConstant;
import com.fulan.application.operatelog.domain.OperateLog;
import com.fulan.application.operatelog.source.Browser;
import com.fulan.application.operatelog.source.OperatingSystem;
import com.fulan.application.operatelog.source.UserAgent;
import com.fulan.application.operatelog.source.Version;
import com.fulan.application.util.json.JsonUtil;

 
public class OperationLogInteceptor implements HandlerInterceptor {

	@Autowired
    private AmqpTemplate rabbitTemplate;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {


		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				String classPath = handlerMethod.getBeanType().toString();
				String methodName = handlerMethod.getMethod().toString();
					// 获取操作系统等信息
					OperateLog operateLog = new OperateLog();
					Map<String, String[]> map = request.getParameterMap();
					String jsonData = JsonUtil.getJSONString(map);
					String head = getAllHeaderInfo(request);
					if (head.length() > 3000) {
						head = head.substring(0, 2999);
					}
					// String moduleName=request.getClass().getName();
					operateLog.setCreateById(null); // 创建人
					operateLog.setCreateByName(null); // 创建人
					operateLog.setCreateDate(new Date()); // 创建时间
					operateLog.setLogType((long) 0); // 日志类型，默认0
					operateLog.setRequestUrl(request.getRequestURI()); // 请求URL
					operateLog.setRequestIp(getIpAddr(request)); // 请求ip
					operateLog.setMethod(request.getMethod()); // 获取http方法信息
					//operateLog.setSessionId(request.getSession().getId());// 获取sessionid
					operateLog.setParams(jsonData); // 获取参数信息
					operateLog.setHead(head);
					operateLog.setClassPath(classPath);
					operateLog.setMethodName(methodName);
					// 如果controller报错，则记录异常错误
					if (e != null) {
						String emsg = "";
						if (e.getMessage().length() > 3000) {
							emsg = e.getMessage().substring(0, 2999);
						}
						operateLog.setExceptionCode(emsg);
						operateLog.setExceptionDetail(emsg);
					}
					// 获取操作系统和浏览器信息
					setBrowserAndOS(request, operateLog);
					rabbitTemplate.convertAndSend(QueueConstant.OPERATE_LOG_QUEUE, operateLog);
					logger.info("log create a new log ======", operateLog);
				}
		} catch (Exception el) {
			el.printStackTrace();
		}

	}

	/**
	 * 获取Ip地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getAllHeaderInfo(HttpServletRequest request) {
		Map<String, String> headerMap = new HashMap<String, String>();
		Enumeration enu = request.getHeaderNames();// 取得全部头信息
		while (enu.hasMoreElements()) {// 以此取出头信息
			String headerName = (String) enu.nextElement();
			headerMap.put(headerName, request.getHeader(headerName));
		}
		return JsonUtil.object2Json(headerMap);
	}

	private void setBrowserAndOS(HttpServletRequest request, OperateLog systemLog) {
		String uastr = request.getHeader("User-Agent");
		UserAgent userAgent = new UserAgent(uastr);

		Browser browser = userAgent.getBrowser();
		Version version = userAgent.getBrowserVersion();
		OperatingSystem os = userAgent.getOperatingSystem();

		String userAgentStr = "";
		if (os != null) {
			String osVersion = os.getName();
			userAgentStr += "OS:" + osVersion;
		}

		if (browser != null) {
			String browerVersion = browser.getName();
			if (version != null) {
				browerVersion += " version:" + version.getVersion();
			}
			userAgentStr += " Brower:" + browerVersion;
		}
		systemLog.setUserAgent(userAgentStr);
	}

	public static byte[] writeInto(Object obj) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			// 读取对象并转换成二进制数据
			oos.writeObject(obj);
			return bos.toByteArray();
		} catch (IOException e) {
			System.out.println("对象转换成二级制数据失败");
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
