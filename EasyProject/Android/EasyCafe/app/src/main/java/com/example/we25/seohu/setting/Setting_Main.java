package com.example.we25.seohu.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.we25.easycafe.LogInPage;
import com.example.we25.easycafe.MessagingService;
import com.example.we25.easycafe.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import customclass.DBName;
import customclass.DBOpenHelper;

public class Setting_Main extends AppCompatActivity {
    String[] data = { "이용약관", "공지사항", "도움말", "버전정보", "로그아웃"};
    SharedPreferences pref;
    Switch switchpush;

    DBOpenHelper DBOpenHelper = new DBOpenHelper(this,1);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);

        db = DBOpenHelper.getWritableDatabase();

        // 푸시 알림
        switchpush = (Switch) findViewById(R.id.switchpush);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        switchpush.setChecked(pref.getBoolean("PushCheck" , true));
        switchpush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(Setting_Main.this, "푸시ON", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Setting_Main.this, "푸시OFF", Toast.LENGTH_LONG).show();
                }
                pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("PushCheck", isChecked);
                editor.commit();
            }
        });     // switchpush end

        // 리스트 뷰
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        ListView lv = (ListView) findViewById(R.id.listview1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {     // 이용약관
                    Intent intent = new Intent(Setting_Main.this, Terms.class);
                    startActivity(intent);
                }
                if (position == 1) {     // 공지사항
                    Intent intent = new Intent(Setting_Main.this, Notice.class);
                    startActivity(intent);
                }
                if (position == 2) {     // 도움말
                    Intent intent = new Intent(Setting_Main.this, Help.class);
                    startActivity(intent);
                }
                if (position == 3) {     // 버전정보
                    Intent intent = new Intent(Setting_Main.this, VersionInfo.class);
                    startActivity(intent);
                }
                if (position == 4) {     // 로그아웃
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    String e_mail = pref.getString("ID" , "");
                    logoutRequest(e_mail);
                    Toast.makeText(Setting_Main.this, "로그아웃 되었습니다.", Toast.LENGTH_LONG).show();
                    deleteBasket();
                    Intent intent = new Intent(getApplicationContext() , LogInPage.class);
                    logOut();
                    startActivity(intent);
                    finishAffinity();



           // 현재는 액티비티 종료로 대체
                }
            }       // onItemClick end
        });     // lv end
    }   // onCreate end

    public void deleteBasket(){
        db.execSQL("delete from "+ DBName.Basket);
    }


    public void onStop() {
        super.onStop();

    } // onStop end

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
        actionBar.setTitle("설정");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }

    private void logOut(){
        SharedPreferences pref = getSharedPreferences("pref" , MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("Login" , false);
        editor.remove("Name");
        editor.remove("ID");
        editor.remove("Phone");
        editor.remove("Birth");
        editor.remove("MGCode");
        editor.commit();
    }

    private void logoutRequest(final String e_mail){
        String url = "http://kukjae.iptime.org:8080/temp/logout.app";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getInt("resultCode") == 200) {
                        Toast.makeText(getApplicationContext(), "토큰 삭제됨", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "JSON 파일 실패", Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "토큰삭제 요청 실패", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("e_mail" , e_mail );
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}       // Setting_Main end
