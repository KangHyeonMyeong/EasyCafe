package com.example.we25.seohu.sensor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.we25.easycafe.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Sensor_Main extends AppCompatActivity {
    ProgressBar progressBar;
    TextView trashValue;
    TextView temprature;
    TextView humidity;
    TextView toliletT;
    TextView holeT;
    TextView usingSeat;
    Button refreshBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_main);
        getStatus();
        progressBar = (ProgressBar) findViewById(R.id.garbegePro);
        trashValue = (TextView) findViewById(R.id.garbegeLevel);
        temprature = (TextView) findViewById(R.id.tempValue);
        humidity = (TextView) findViewById(R.id.humiValue);
        toliletT = (TextView) findViewById(R.id.tissueState);
        holeT = (TextView) findViewById(R.id.holeState);
        usingSeat = (TextView) findViewById(R.id.usingSeat);

        refreshBtn = (Button) findViewById(R.id.refresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStatus();
            }
        });
    }       // onCreate end


    private void getStatus(){
        String Url = "http://kukjae.iptime.org:8080/temp/cafestatus.app";
        StringRequest request = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    if(object.getString("resultCode").equals("200")) {
                        progressBar.setProgress(object.getInt("trash_can_state"));
                        trashValue.setText(object.getInt("trash_can_state") + "%");
                        temprature.setText(object.getInt("temp_level") + "℃");
                                humidity.setText(object.getInt("humidity_level") + "%");
                        if (object.getString("toilet").equals("1")) toliletT.setText("없음");
                        else toliletT.setText("있음");
                        if (object.getString("tissue_state").equals("1")) holeT.setText("없음");
                        else holeT.setText("있음");
                        usingSeat.setText(object.getInt("current_seat") + "/" + object.getInt("total_seat"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("센서설정");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }
}       // Sensor_Main end
