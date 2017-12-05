package menu.service;

public class RegisterRequest {
	private String menu_id;
	private String menu_name;
	private int price;
	private String menu_info;
	private String menu_image;
	private String menu_category;
	private String price_category;

	public RegisterRequest(String menu_id, String menu_name, int price, String menu_info, String menu_image, String menu_category, String price_category) {
		this.menu_id=menu_id;
		this.menu_name=menu_name;
		this.price=price;
		this.menu_info=menu_info;
		this.menu_image=menu_image;
		this.menu_category=menu_category;
		this.price_category=price_category;
		
		
	}
	
	public RegisterRequest() {}
	
	public String getMenu_id() {
		return menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public int getPrice() {
		return price;
	}

	public String getMenu_info() {
		return menu_info;
	}

	public String getMenu_image() {
		return menu_image;
		
	}

	public String getMenu_category() {
		return menu_category;
	}

	public String getPrice_category() {
		return price_category;
	}
	
	
	
	


}
