package auth.service;

public class User {

	private String e_mail;
	private String name;
	private int manager_flag;
	
	public User(String e_mail, String name, int manager_flag) {
		this.e_mail = e_mail;
		this.name = name;
		this.manager_flag = manager_flag;
	}

	public String getE_mail() {
		return e_mail;
	}

	public String getName() {
		return name;
	}	
	
	public int getManager_flag() {
		return manager_flag;
	}	

}
