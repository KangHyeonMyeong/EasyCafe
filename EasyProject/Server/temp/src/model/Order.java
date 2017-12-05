package model;

public class Order extends Result {
	private String order_id;
	private String order_name;
	private String order_date;
	private String order_content;
	private String order_status;
	
	
	public Order(String order_id, String order_name, String order_date, String order_content, String order_status) {
		super();
		this.order_id = order_id;
		this.order_name = order_name;
		this.order_date = order_date;
		this.order_content = order_content;
		this.order_status = order_status;
	}


	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getOrder_name() {
		return order_name;
	}


	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}


	public String getOrder_date() {
		return order_date;
	}


	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}


	public String getOrder_content() {
		return order_content;
	}


	public void setOrder_content(String order_content) {
		this.order_content = order_content;
	}


	public String getOrder_status() {
		return order_status;
	}


	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	
	
	
	
	
	
	
	
	
	

}
