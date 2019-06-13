package com.entity;

public class User {
	public boolean islogin;
	private String id;
	private String name;
	private String nickname;
	private String mascot;
	private String regdate;
	private String phone;
	private String email;
	private int followernum;
	private int postnum;
	public User(){
		islogin=false;
		id="";
		name="";
		nickname="";
		mascot="";
		phone="";
		email="";
	}
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMascot() {
		return mascot;
	}
	public void setMascot(String mascot) {
		this.mascot = mascot;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFollowernum() {
		return followernum;
	}
	public void setFollowernum(int followernum) {
		this.followernum = followernum;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
}
