package com.yzhh.backstage.api.entity;

/**
 * @description:管理员实体类
 * @projectName:backstage-api
 * @className:Admin.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:06:39
 * @version 1.0.1
 */
public class Admin extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private String username;		//
    private String password;		//
    private String email;			//
    private Long joinDate;			//
    private Long lastLoginDate;	//
    private Integer role;				//
    private String jurisdiction;	//

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction == null ? null : jurisdiction.trim();
    }
}