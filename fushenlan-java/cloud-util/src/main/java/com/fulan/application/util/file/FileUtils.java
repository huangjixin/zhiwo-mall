package com.fulan.application.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
import java.util.Properties;   
import com.jcraft.jsch.Channel;  
import com.jcraft.jsch.ChannelSftp;  
import com.jcraft.jsch.JSch;  
import com.jcraft.jsch.Session;  
/**
 * File工具类
 */
public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 将某个目录下的文件coay到另外的目录下
	 * 
	 * @param originDirectory
	 * @param targetDirectory
	 * @param imgs
	 */
	public static void copy(String originDirectory, String targetDirectory, List<String> imgs) throws IOException {
		// File origindirectory = new File(originDirectory); //源路径File实例
		originDirectory = originDirectory + File.separator;
		File targetdirectory = new File(targetDirectory); // 目标路径File实例
		if (!targetdirectory.isDirectory()) { // 判断是不是正确的路径
			return;
		}

		// 获取某个图片名字
		for (String oneImg : imgs) {
			String imgNames = oneImg.concat(".jpg");
			File file = new File(originDirectory + imgNames);
			// try {
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);
			PrintStream pout = new PrintStream(targetdirectory.getAbsolutePath() + "/" + file.getName());
			BufferedOutputStream bout = new BufferedOutputStream(pout);
			while ((bin.available()) != 0) {
				int c = bin.read(); // 从输入流中读一个字节
				bout.write(c); // 将字节（字符）写到输出流中
			}
			bout.close();
			pout.close();
			bin.close();
			fin.close();
		}
	}

	/**
	 * 将某个目录下的文件coay到另外的目录下
	 * 
	 * @param originDirectory
	 * @param targetDirectory
	 * @param imgs
	 */
	public static void copyFile(String targetDirectory, List<File> imgs) {
		File targetdirectory = new File(targetDirectory); // 目标路径File实例
		if (!targetdirectory.isDirectory()) { // 判断是不是正确的路径
			return;
		}
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		PrintStream pout = null;
		BufferedOutputStream bout = null;
		try {
			for (File file : imgs) {
				fin = new FileInputStream(file);
				bin = new BufferedInputStream(fin);
				pout = new PrintStream(targetdirectory.getAbsolutePath() + "/" + file.getName());
				bout = new BufferedOutputStream(pout);
				while ((bin.available()) != 0) {
					int c = bin.read(); // 从输入流中读一个字节
					bout.write(c); // 将字节（字符）写到输出流中
					bout.flush();
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (bout != null)
					bout.close();
				if (pout != null)
					pout.close();
				if (bin != null)
					bin.close();
				if (fin != null)
					fin.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	public static void copyImg(String sourcePath, String desPath, List<String> imageName) throws IOException {
		File file = new File(sourcePath);
		FileWriter fw = new FileWriter(desPath);
		File arr[] = file.listFiles();
		if (arr == null || arr.length == 0) {
			logger.error(sourcePath + "目录下没有文件！");
		} else {
			for (File a : arr) {
				if (a.toString().endsWith(".jpg")
						&& imageName.contains(a.getName().substring(0, a.getName().indexOf(".")))) {
					fw.write(a.toString());

				} else {
					break;
				}
			}
		}
		fw.close();

	}

	/**
	 * 删除指定目录下的img
	 * 
	 * @param allimg
	 * @param uplodurl
	 */
	public static void DeleteAllImg(List<File> allimg) {
		for (File file : allimg) {
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}
	}

	// public static void DeleteAllImg(List<String> allimg, String uplodurl) {
	// for (String imgName : allimg) {
	// String deleteurl = uplodurl + File.separator + imgName.concat(".jpg");
	// deleteurl----------------------------------" + deleteurl);
	// FileUtils.deleteFile(deleteurl);
	// deleteurl----------------------------------end");
	// }
	// }

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		Boolean flag;
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		return true;
		// 删除当前目录
		// if (dirFile.delete()) {
		// return true;
		// } else {
		// return false;
		// }
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 上传影响失败或者成功 删除指定目录下uploadimage下面的文件
	 * 
	 * @param sPath
	 * @param allimg
	 * @return
	 */
	public static boolean deleteImgaFile(String sPath, List<String> allimg) {
		Boolean flag = false;
		File file = new File(sPath);
		File arr[] = file.listFiles();
		for (File a : arr) {
			if (a.toString().endsWith(".jpg") && allimg.contains(a.getName().substring(0, a.getName().indexOf(".")))) {
				if (file.isFile() && file.exists()) {
					file.delete();
					flag = true;
				}
			}
		}
		// 路径为文件且不为空则进行删除
		return flag;
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
	}

	/***
	 * 获取包下面所有的class
	 * 
	 * @param packageName
	 * @return
	 */
	public static List<Class<?>> getClasses(String packageName) {
		// 第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											logger.error("getClasses", e);
										}
									}
								}
							}
						}
					} catch (IOException e) {
						logger.error("getClasses", e);
					}
				}
			}
		} catch (IOException e) {
			logger.error("getClasses", e);
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
			List<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
						classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					logger.error("findAndAddClassesInPackageByFile", e);
				}
			}
		}
	}
	/**
	 * 上传文件到sftp服务器
	 * @param host  主机ip
	 * @param port  端口
	 * @param username  用户名
	 * @param password  密码
	 * @param dir  目录
	 * @param file 上传的文件
	 * @return
	 */
	public static boolean listFileNames(String host, int port,String username, final String password, String dir,File file) {
		//List<String> list = new ArrayList<String>();
		ChannelSftp sftp = null;
		Channel channel = null;
		Session sshSession = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			sshSession = jsch.getSession(username, host, port);
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.debug("Session connected!");
			channel = sshSession.openChannel("sftp");
			channel.connect();
			logger.debug("Channel connected!");
			sftp = (ChannelSftp) channel;
			//File file = new File("D:\\test.txt");
			FileInputStream in = new FileInputStream(file);
			sftp.put(in, file.getName());
			in.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeChannel(sftp);
			closeChannel(channel);
			closeSession(sshSession);
		}
		return false;
	}  
  
    private static void closeChannel(Channel channel) {  
        if (channel != null) {  
            if (channel.isConnected()) {  
                channel.disconnect();  
            }  
        }  
    }  
    private static void closeSession(Session session) {  
        if (session != null) {  
            if (session.isConnected()) {  
                session.disconnect();  
            }  
        }  
    }  
}
