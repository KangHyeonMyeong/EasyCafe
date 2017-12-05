package model;

public class Member extends Result{
	private	String e_mail;
	private String password;
	private String name;
	private String birth;
	private String phone;
	private String manage_flag;
	
	
	public Member(String e_mail, String password, String name, String birth, String phone, String manage_flag) {
		super();
		this.e_mail = e_mail;
		this.password = password;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.manage_flag = manage_flag;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getManage_flag() {
		return manage_flag;
	}
	public void setManage_flag(String manage_flag) {
		this.manage_flag = manage_flag;
	}
	
}
