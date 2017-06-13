package com.zwo;

import java.io.File;

import java.util.ArrayList;

import java.util.List;

public class Test {

	/**
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {

		// 要删除的目录 请勿以\\结尾，及最后一层目录后的分隔符不要

		String rootPath = "D:\\zwoweb_workspace\\zhiwo-parent";

		List<File> list = getAllNullDirectorys(new File(rootPath));

		System.out.println("---------------" + list.size());

		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i).getPath());

		}

		removeNullFile(list, rootPath);

	}

	/** */

	/**
	 * 
	 * 递归列出某文件夹下的最深层的空文件夹绝对路径，储存至list
	 *
	 * 
	 * 
	 * @param root
	 * 
	 * @return
	 * 
	 */

	public static List<File> getAllNullDirectorys(File root) {

		List<File> list = new ArrayList<File>();

		File[] dirs = root.listFiles();

		if (dirs != null) {

			for (int i = 0; i < dirs.length; i++) {

				if (dirs[i].isDirectory() && dirs[i].listFiles().length == 0) {

					System.out.println("name:" + dirs[i].getPath());

					list.add(dirs[i]);

				}

				if (dirs[i].isFile()) {

					System.out.println("文件:" + dirs[i].getPath());

				}

				list.addAll(getAllNullDirectorys(dirs[i]));

			}

		}

		return list;

	}

	/**
	 * 
	 * 由最深一层的空文件，向上（父文件夹）递归，删除空文件夹
	 * 
	 * @param list
	 * 
	 * @param rootPath
	 * 
	 */

	public static void removeNullFile(List<File> list, String rootPath) {

		if (list == null || list.size() == 0) {

			return;

		}

		List<File> plist = new ArrayList<File>();

		for (int i = 0; i < list.size(); i++) {

			File temp = list.get(i);

			if (temp.isDirectory() && temp.listFiles().length <= 0) {

				temp.delete();

				System.out.println("parent:" + temp.getParentFile().getPath());

				File pFile = temp.getParentFile();

				if (pFile.getPath().equals(rootPath)) {

					continue;

				}

				if (!plist.contains(pFile)) {// 父目录去重添加

					plist.add(pFile);

				}

			}

		}

		removeNullFile(plist, rootPath);

	}

}