package com.cafe.model;

import java.util.Date;

public class Member {

	private int num;
	private String e_mail;
	private String password;
	private String name;
	private Date birth;
	private String phone;
	private int manager_flag;
	
	
	
	public Member(int num, String e_mail, String password, String name, Date birth, String phone, int manager_flag) {
		super();
		this.num = num;
		this.e_mail = e_mail;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.manager_flag = manager_flag;
		
	}
	
	public Member() {}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getManager_flag() {
		return manager_flag;
	}
	public void setManager_flag(int manager_flag) {
		this.manager_flag = manager_flag;
	}
	
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}
	
	
	
}
