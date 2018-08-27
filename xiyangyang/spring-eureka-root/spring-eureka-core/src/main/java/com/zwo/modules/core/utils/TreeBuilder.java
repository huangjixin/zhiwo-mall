package com.zwo.modules.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.zwo.modules.core.domain.Node;

//import org.apache.commons.collections.CollectionUtils;


public class TreeBuilder<T extends Node> {

	@SuppressWarnings("unchecked")
	public List<T> buildListToTree(List<T> dirs, boolean menuOnly) {
		List<T> roots = findRoots(dirs,menuOnly);
		List<T> notRoots = (List<T>) CollectionUtils.subtract(dirs, roots);
		for (T root : roots) {
			root.setChildren(findChildren(root, notRoots,menuOnly));
		}
		return roots;
	}

	public List<T> findRoots(List<T> allNodes, boolean menuOnly) {
		List<T> results = new ArrayList<T>();
		for (T node : allNodes) {
			boolean isRoot = true;
			if(node.getParentId()!=null){
				isRoot = false;
				for (Node comparedOne : allNodes) {
					if (node.getParentId() == comparedOne.getId()) {
						isRoot = false;
						break;
					}
				}
			}else{
				isRoot = true;
			}
			
			if (isRoot) {
				node.setLevel(0);
				results.add(node);
				node.setRootId(node.getId());
			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	private List<T> findChildren(T root, List<T> allNodes, boolean menuOnly) {
		List<T> children = new ArrayList<T>();

		for (T comparedOne : allNodes) {
			if (root.getId().equals(comparedOne.getParentId()) 
					|| comparedOne.getParentId() == root.getId()) {
//				comparedOne.setParent(root);
				comparedOne.setLevel(root.getLevel() + 1);
				children.add(comparedOne);
			}
		}
		List<T> notChildren = (List<T>) CollectionUtils.subtract(allNodes, children);
		for (T child : children) {
			List<T> tmpChildren = findChildren(child, notChildren, menuOnly);
			if (tmpChildren == null || tmpChildren.size() < 1) {
				child.setLeaf(true);
			} else {
				child.setLeaf(false);
			}
			child.setChildren(tmpChildren);
		}
		return children;
	}

	private List<T> getLeafChildren(List<T> resultList, List<T> children) {
		for (T node : children) {
			if (node.isLeaf()) {
				resultList.add(node);
			} else {
				getLeafChildren(resultList, node.getChildren());
			}
		}
		return resultList;
	}

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		TreeBuilder tb = new TreeBuilder();
		List<Node> allNodes = new ArrayList<Node>();
		allNodes.add(new Node("1", "0", "节点1"));
		allNodes.add(new Node("2", "0", "节点2"));
		allNodes.add(new Node("3", "0", "节点3"));
		allNodes.add(new Node("4", "1", "节点4"));
		allNodes.add(new Node("5", "1", "节点5"));
		allNodes.add(new Node("6", "1", "节点6"));
		allNodes.add(new Node("7", "4", "节点7"));
		allNodes.add(new Node("8", "4", "节点8"));
		allNodes.add(new Node("9", "5", "节点9"));
		allNodes.add(new Node("10", "5", "节点10"));
		allNodes.add(new Node("11", "7", "节点11"));
		allNodes.add(new Node("12", "7", "节点12"));
		// 显示所有节点
		List<Node> roots = tb.buildListToTree(allNodes,true);
		for (Node n : roots) {
			System.out.println(n);
		}
		System.out.println("------------------");
		// 查找所有子节点
		List<Node> children = tb.findChildren(new Node("1", "0", "节点1"), allNodes,true);
		for (Node n : children) {
			System.out.println(n.toString());
		}
		// 查找所有叶子节点
		System.out.println("------------------");
		List<Node> resultList = tb.getLeafChildren(new ArrayList(), children);
		for (Node n : resultList) {
			System.out.println(n.toString());
		}

	}*/
}
