package model;

public class PushList {
	private String e_mail;
	private String manager_flag;
	private String push_token;
	public PushList(String e_mail, String manager_flag, String push_token) {
		super();
		this.e_mail = e_mail;
		this.manager_flag = manager_flag;
		this.push_token = push_token;
	}
	
	public String getE_mail() {
		return e_mail;
	}
	public String getManager_flag() {
		return manager_flag;
	}
	public String getPush_token() {
		return push_token;
	}
	
	
}
