/**
 * 
 */
package com.zwo.modules.mall.domain;

import java.util.List;

/**
 * @author hjx
 *
 */
public class CategoryTree extends PrCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PrCategory parent;

	private List<CategoryTree> children;

	private int level;

	private String text;

	private String rootId;

	private String type;

	private boolean isLeaf;

	/**
	 * @return the parent
	 */
	public PrCategory getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(PrCategory parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public List<CategoryTree> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<CategoryTree> children) {
		this.children = children;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the rootId
	 */
	public String getRootId() {
		return rootId;
	}

	/**
	 * @param rootId
	 *            the rootId to set
	 */
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isLeaf
	 */
	public boolean isLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
