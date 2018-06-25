package com.fulan.core.monitoring.cat.monitor;

import java.lang.reflect.Field;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.fulan.application.util.json.JsonMapper;
import com.fulan.core.monitoring.cat.constant.CatConstant;

@Component
public class CatJPARespositoryMonitor implements MethodInterceptor {

	private static Logger logger = LoggerFactory.getLogger(CatJPARespositoryMonitor.class);
	
	@Value("${spring.datasource.url}")
	private String url = "jdbc:url";

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Transaction transaction = invokeMethodBefore(invocation);
		Object obj = invokeMethod(invocation, transaction);
		invokeMethodAfter(transaction);
		return obj;
	}
	
	public Object invokeMethod(MethodInvocation methodInvocation,Transaction t) throws Throwable{
		if (t != null) {
			try {
				return methodInvocation.proceed();
			} catch (Exception e) {
				catLogException(t,e);
				throw e;
			}
		} else {
			return methodInvocation.proceed();
		}
	}
	
	public Transaction invokeMethodBefore(MethodInvocation invocation){
		Transaction t = null;
		try {
			String type = CatConstant.TYPE_SQL.value();
			String methodName = invocation.getMethod().getName();
			Object[] args = invocation.getArguments();

			//获取原始的ms
			String argsJson = "";
			argsJson = toJsonString(args);
			String repostoryInterfaceName = "";
			if(invocation instanceof ReflectiveMethodInvocation) {
				ReflectiveMethodInvocation ri = (ReflectiveMethodInvocation) invocation;
				Object bean = ri.getProxy();
				Field h = bean.getClass().getSuperclass().getDeclaredField("h");
				h.setAccessible(true);
				AopProxy aopProxy = (AopProxy) h.get(bean);
				Field advised = aopProxy.getClass().getDeclaredField("advised");
				advised.setAccessible(true);
				AdvisedSupport as = (AdvisedSupport) advised.get(aopProxy);
				Class<?>[] proxiedInterfaces = as.getProxiedInterfaces();
				if(proxiedInterfaces != null && proxiedInterfaces.length > 0){
					Class<?> respositoryInterface = proxiedInterfaces[0];
					repostoryInterfaceName = respositoryInterface.getSimpleName();
				}
			}
			
			t = Cat.newTransaction(type, repostoryInterfaceName + "." + methodName);
			Cat.logEvent(type, type + ".Database", Event.SUCCESS, url);
			Cat.logEvent(type, type+".Params", Event.SUCCESS, argsJson);
			return t;
		} catch (Exception e) {
			logger.error("CatJPARespositoryMonitor invokeMethodBefore", e);
			return t;
		}
	}
	
	public void invokeMethodAfter(Transaction t){
		try {
			if (t != null) {
				t.setStatus(Transaction.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("CatBusinessMonitor invokeMethodAfter", e);
		} finally {
			if (t != null)
				t.complete();
		}
		
	}
	
	private void catLogException(Transaction t,Exception e){
		try {
			t.setStatus("-1");
			Cat.getProducer().logError(e);
		} catch (Exception innere) {
			logger.error("CatJPARespositoryMonitor catLogException", innere);
		} finally {
			t.complete();
		}
	}
	
	public String toJsonString(Object obj){
		String result = "";
		if(obj != null) {
			try {
				result = JsonMapper.getInstance().writeValueAsString(obj);
			} catch (Exception e) {
				result = obj.toString();
			}
		}
		return result;
	}
	
	public static String[] getSql(String str) {
		if (StringUtils.isBlank(str))
			return null;

		String strs[] = str.split("Hibernate:");
		String result[] = new String[strs.length];
		for (int i = 0; i < strs.length; i++) {
			String element = strs[i];
			if (element != null && !"".equals(element)) {
				result[i] = element;
			}

		}
		return result;
	}

}
