package com.example.we25.easycafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import customclass.MemberInfo;

public class IntroPage extends AppCompatActivity {
    boolean login= false;
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if(login){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                SharedPreferences pref = getSharedPreferences("pref" , MODE_PRIVATE);
                String name = pref.getString("Name" ,"");
                String phone = pref.getString("Phone","");
                String ID = pref.getString("ID","");
                String birth = pref.getString("Birth","");
                boolean manageCode = pref.getBoolean("MGCode" , false);
                intent.putExtra("MemberInfo" , new MemberInfo(ID,null, name, phone, birth, manageCode));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        login = LoginStatus();

        ImageView imageView = (ImageView) findViewById(R.id.loading);
        Glide.with(this).load(R.raw.cuploading).into(imageView);

    }


    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(r, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }


    public boolean LoginStatus(){
        SharedPreferences pref = getSharedPreferences("pref" , MODE_PRIVATE);
        return pref.getBoolean("Login", false);
    }
}
