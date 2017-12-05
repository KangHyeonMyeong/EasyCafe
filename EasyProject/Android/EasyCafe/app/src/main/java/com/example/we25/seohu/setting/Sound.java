package com.example.we25.seohu.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.we25.easycafe.R;

public class Sound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Switch switchSound = (Switch)findViewById(R.id.switchSound);
        Switch switchVibration = (Switch)findViewById(R.id.switchVibration);

        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    Toast.makeText(Sound.this,"소리ON", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Sound.this,"소리OFF", Toast.LENGTH_LONG).show();
                }
            }
        });

        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    Toast.makeText(Sound.this,"진동ON", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Sound.this,"진동OFF", Toast.LENGTH_LONG).show();
                }
            }
        });

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
        actionBar.setTitle("소리설정");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }
}