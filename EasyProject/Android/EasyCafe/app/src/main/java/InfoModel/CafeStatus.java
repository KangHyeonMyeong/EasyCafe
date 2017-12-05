package InfoModel;

import java.util.Date;

/**
 * Created by com on 2017-08-29.
 */

public class CafeStatus {
    private int total_seat;
    private int current_seat;
    private int temp_level;
    private int humidity_level;
    private int noise_level;
    private int tissue_state;
    private int trash_can_state;
    private int toilet;

    public CafeStatus(int total_seat, int current_seat, int temp_level, int humidity_level, int noise_level, int tissue_state, int trash_can_state, int toilet) {
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

    public int getTissue_state() {
        return tissue_state;
    }

    public int getTrash_can_state() {
        return trash_can_state;
    }

    public int getToilet() {
        return toilet;
    }
}
