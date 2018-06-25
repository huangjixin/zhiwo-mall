package com.fulan.application.context;

/**
 * 上下文容器
 * @author scott
 *
 */
public class ContextHolder {
	
	private static ThreadLocal<GlobalContext> ctx = new ThreadLocal<GlobalContext>();
	
	public static void set(GlobalContext context) {
		ctx.set(context);
	}
	
	public static GlobalContext get() {
		return ctx.get();
	}
	
	public static void clear() {
		ctx.remove();
	}
	
}
