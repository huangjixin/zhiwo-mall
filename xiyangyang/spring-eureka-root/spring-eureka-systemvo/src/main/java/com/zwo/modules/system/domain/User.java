package com.zwo.modules.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Table(name = "bhm_user")
@Data
public class User implements Serializable {
    @Id
    private String id;

    /**
     * 用戶名
     */
    private String username;

    /**
     * 密碼
     */
    private String password;

    /**
     * 是否可用
     */
    @Column(name = "is_valid")
    private String isValid;

    @Column(name = "sort_index")
    private Integer sortIndex;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "edit_time")
    private Date editTime;

    private String email;

    @Column(name = "real_name")
    private String realName;

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

//    private Boolean enabled;

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
     * 获取用戶名
     *
     * @return username - 用戶名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用戶名
     *
     * @param username 用戶名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密碼
     *
     * @return password - 密碼
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密碼
     *
     * @param password 密碼
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取是否可用
     *
     * @return is_valid - 是否可用
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * 设置是否可用
     *
     * @param isValid 是否可用
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
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
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
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
     * @return enabled
     */
//    public Boolean getEnabled() {
//        return enabled;
//    }

    /**
     * @param enabled
     */
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
}