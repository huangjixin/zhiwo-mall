package com.zwotech.common.utils;

import java.io.File;

public class PathUtil {
	public static String getWebroot() {
		String classpath = PathUtil.class.getResource("/").getPath()
				.replaceFirst("/", "");
		String webappRoot = classpath.replaceAll("WEB-INF/classes/", "");
		webappRoot = webappRoot.replaceAll("WEB-INF"+File.separator+"classes"+File.separator, "");
		return webappRoot;
	}
}
