package com.fulan.application.util.clazz;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;

import org.apache.commons.lang3.StringUtils;

public class ClassUtil {
	
	public static boolean existMethod(Class clazz,String methodName) {
		Method method = null;
		try {
			method = clazz.getMethod(methodName, null);
		} catch (Exception e) {
		}
		
		return method != null;
	}
	
	
	protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }
        } catch (Exception e) {
        }
        return id;
}
	
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (StringUtils.isNotEmpty(name)) {
            /*
             * GET jvmPid
			 */
            mpid.append(name.split("@")[0]);
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
		 */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
}

	public static void main(String[] args) {
		getMaxWorkerId(0,31);
	}
}
