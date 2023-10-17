package com.ssafy.pjt.member.dto;

public class MemberDto {
	private String userId;
	private String userPwd;
	private String name;
	private String email;
	private String addr;
	private String phone;
	private boolean admin;

	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String userId, String userPwd, String name, String email, String addr, String phone, boolean admin) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.name = name;
		this.email = email;
		this.addr = addr;
		this.phone = phone;
		this.admin = admin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userPwd=" + userPwd + ", name=" + name + ", email=" + email
				+ ", addr=" + addr + ", phone=" + phone + ", admin=" + admin + "]";
	}
	
	



}
