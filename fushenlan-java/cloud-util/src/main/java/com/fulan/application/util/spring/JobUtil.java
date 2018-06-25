package com.fulan.application.util.spring;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobUtil {
	private static final Logger logger = LoggerFactory.getLogger(JobUtil.class);
	
	public static boolean isCurrentServer(String execJobHost) {
		String hostAddress = "";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
			logger.info("CurrentServer >>>>>>>>>>>>>>>>>>>>>>>："+hostAddress);
			if (execJobHost.equals(hostAddress)) {
				return true;
			}
		} catch (UnknownHostException e) {
			logger.error("JobUtil.isCurrentServer", e);
		}
		return false;
	}
}
