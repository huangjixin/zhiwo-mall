package com.zwo.modules.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Table(name = "bhm_role")
@Data
public class Role implements Serializable {
    @Id
    private String id;

    /**
     * 名稱
     */
    private String name;

    @Column(name = "sort_index")
    private Integer sortIndex;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 鹽
     */
    private String salt;

    @Column(name = "add_by")
    private String addBy;

    @Column(name = "edit_by")
    private String editBy;

    /**
     * 描述
     */
    private String descrtion;

    /**
     * 是否可用
     */
    @Column(name = "is_valid")
    private Byte isValid;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取名稱
     *
     * @return name - 名稱
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名稱
     *
     * @param name 名稱
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return sort_index
     */
    public Integer getSortIndex() {
        return sortIndex;
    }

    /**
     * @param sortIndex
     */
    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    /**
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return edit_time
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 获取鹽
     *
     * @return salt - 鹽
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置鹽
     *
     * @param salt 鹽
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * @return add_by
     */
    public String getAddBy() {
        return addBy;
    }

    /**
     * @param addBy
     */
    public void setAddBy(String addBy) {
        this.addBy = addBy == null ? null : addBy.trim();
    }

    /**
     * @return edit_by
     */
    public String getEditBy() {
        return editBy;
    }

    /**
     * @param editBy
     */
    public void setEditBy(String editBy) {
        this.editBy = editBy == null ? null : editBy.trim();
    }

    /**
     * 获取描述
     *
     * @return descrtion - 描述
     */
    public String getDescrtion() {
        return descrtion;
    }

    /**
     * 设置描述
     *
     * @param descrtion 描述
     */
    public void setDescrtion(String descrtion) {
        this.descrtion = descrtion == null ? null : descrtion.trim();
    }

    /**
     * 获取是否可用
     *
     * @return is_valid - 是否可用
     */
    public Byte getIsValid() {
        return isValid;
    }

    /**
     * 设置是否可用
     *
     * @param isValid 是否可用
     */
    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }
}