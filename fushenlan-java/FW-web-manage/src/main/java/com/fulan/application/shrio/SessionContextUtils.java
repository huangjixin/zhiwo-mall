package com.fulan.application.shrio;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.fulan.api.security.domain.Account;

public abstract class SessionContextUtils {

    static public Long getLoginUserId() {
		Account account = getCurrentUser();
		return account!=null? account.getId():null;
    }
	
	static public Long getCurrentUserId() {
		Account account = getCurrentUser();
		return account != null ? account.getId() : null;
	}
	
	static public String getLoginName() {
		Account account  = getCurrentUser();
		return account != null ? account.getAccountName(): null;
	}

	static public Account getCurrentUser() {
		Account user = (Account) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	static public String getString(String key) {
		return (String) getAttribute(key);
	}

	static public Long getLong(String key) {
		return (Long) getAttribute(key);
	}

	static public Integer getInteger(String key) {
		return (Integer) getAttribute(key);
	}

	static public Boolean getBoolean(String key) {
		return (Boolean) getAttribute(key);
	}

	static public Float getFloat(String key) {
		return (Float) getAttribute(key);
	}

	static public Double getDouble(String key) {
		return (Double) getAttribute(key);
	}

	static public Date Date(String key) {
		return (Date) getAttribute(key);
	}

	static public Object getAttribute(String key) {
		return getSession().getAttribute(key);
	}

	static public void setAttribute(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	static public Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}
}
