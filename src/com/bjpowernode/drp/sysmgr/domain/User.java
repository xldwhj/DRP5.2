package com.bjpowernode.drp.sysmgr.domain;
import java.util.Date;
public class User {
	private String userId;
	private String userName;
	private String password;
	private String contactTel;
	private String email;
	private Date createDate;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userID) {
		this.userId = userID;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
