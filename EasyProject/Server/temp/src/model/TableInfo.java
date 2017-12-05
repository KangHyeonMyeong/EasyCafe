package model;

public class TableInfo {
	private int table_number;
	private String blanket;
	private String consent;
	private String cushion;
	private String seat_image;
	public TableInfo(int table_number, String blanket, String consent, String cushion, String seat_image) {
		super();
		this.table_number = table_number;
		this.blanket = blanket;
		this.consent = consent;
		this.cushion = cushion;
		this.seat_image = seat_image;
	}
	public int getTable_number() {
		return table_number;
	}
	public String getBlanket() {
		return blanket;
	}
	public String getConsent() {
		return consent;
	}
	public String getCushion() {
		return cushion;
	}
	public String getSeat_image() {
		return seat_image;
	}
	
	
	
}
