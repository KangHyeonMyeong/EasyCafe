package model;

public class Menu {
	private String menu_name;
	private String menu_info;
	private String menu_image;
	private String menu_category;
	private int price_HS;
	private int price_HL;
	private int price_IS;
	private int price_IL;
	
	
	public Menu( String menu_name, String menu_info, String menu_image, String menu_category, int price_HS, int price_HL, int price_IS, int price_IL) {
		this.menu_name= menu_name;
		this.menu_info=menu_info;
		this.menu_image= menu_image;
		this.menu_category = menu_category;
	
		this.price_HS=price_HS;
		this.price_HL=price_HL;
		this.price_IS=price_IS;
		this.price_IL=price_IL;
		
		
		
	}
	
	
	public Menu() {}



	public String getMenu_name() {
		return menu_name;
	}


	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}


	public String getMenu_info() {
		return menu_info;
	}


	public void setMenu_info(String menu_info) {
		this.menu_info = menu_info;
	}


	public String getMenu_image() {
		return menu_image;
	}


	public void setMenu_image(String menu_image) {
		this.menu_image = menu_image;
	}


	public String getMenu_category() {
		return menu_category;
	}


	public void setMenu_category(String menu_category) {
		this.menu_category = menu_category;
	}



	public int getPrice_HS() {
		return price_HS;
	}


	public void setPrice_HS(int price_HS) {
		this.price_HS = price_HS;
	}


	public int getPrice_HL() {
		return price_HL;
	}


	public void setPrice_HL(int price_HL) {
		this.price_HL = price_HL;
	}


	public int getPrice_IS() {
		return price_IS;
	}


	public void setPrice_IS(int price_IS) {
		this.price_IS = price_IS;
	}


	public int getPrice_IL() {
		return price_IL;
	}


	public void setPrice_IL(int price_IL) {
		this.price_IL = price_IL;
	}
	
	
}

	
	
	

	



