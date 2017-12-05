package model;



public class CafeStatus extends Result{
	private int total_seat;
	private int current_seat;
	private int temp_level;
	private int humidity_level;
	private int noise_level;
	private String tissue_state;
	private String trash_can_state;
	private String toilet;
	
	public CafeStatus(int total_seat, int current_seat, int temp_level, int humidity_level, int noise_level,
			String tissue_state, String trash_can_state, String toilet ) {
		super();
		this.total_seat = total_seat;
		this.current_seat = current_seat;
		this.temp_level = temp_level;
		this.humidity_level = humidity_level;
		this.noise_level = noise_level;
		this.tissue_state = tissue_state;
		this.trash_can_state = trash_can_state;
		this.toilet = toilet;
	}

	public int getTotal_seat() {
		return total_seat;
	}

	public int getCurrent_seat() {
		return current_seat;
	}

	public int getTemp_level() {
		return temp_level;
	}

	public int getHumidity_level() {
		return humidity_level;
	}

	public int getNoise_level() {
		return noise_level;
	}

	public String getTissue_state() {
		return tissue_state;
	}

	public String getTrash_can_state() {
		return trash_can_state;
	}

	public String getToilet() {
		return toilet;
	}

	
	
	
	
}
