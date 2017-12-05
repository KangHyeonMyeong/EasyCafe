package com.example.we25.easycafe;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import customclass.StatuslistAdapter;

public class CafeStatusInfoActivity extends AppCompatActivity {
    StatuslistAdapter adapter = null; // 리스트뷰로 보여주기 위한 어답터
    String content;

    int count_s = 0;

    //리스트뷰와 어뎁터에 넣어줄 파라미터들
    ArrayList<String> listTitle = new ArrayList<String>();
    HashMap<String, String> listStatus = new HashMap<String, String>();
    ListView listView;

    //리스트뷰에 보여줄 아이템들
    String[] menu = {"사용좌석", "온도", "습도", "소음"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_status_info);


        //어뎁터 파라미터의 초기화
        listTitle.add(menu[0]);
        listTitle.add(menu[1]);
        listTitle.add(menu[2]);
        listTitle.add(menu[3]);
        listStatus.put(listTitle.get(0), "");
        listStatus.put(listTitle.get(1), "");
        listStatus.put(listTitle.get(2), "");
        listStatus.put(listTitle.get(3), "");

        readStatus();
        //웹뷰로 카페상태를 보여준다.
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.setInitialScale(50);

        webView.loadUrl("http://kukjae.iptime.org:8080/temp/view/cafestatus/seatdata_app.jsp");

        //리스트뷰를 생성하기위한 어뎁터 설정 및 클릭리스너
        adapter = new StatuslistAdapter(this, listTitle, listStatus);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }



    //서버로 부터 소음도를 받아오는 메서드
    public void readStatus() {
        String url = "http://kukjae.iptime.org:8080/temp/cafestatus.app";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int total = 20;
                int cur = 0;
                int temp = 0;
                int humi =0;
                int dB = 0;
                try {
                    JSONObject object = new JSONObject(response);
                    Log.d("Test", object.toString());
                    Log.d("Test", object.getString("resultCode"));
                    total =object.getInt("total_seat");
                    cur = object.getInt("current_seat");
                    temp =object.getInt("temp_level");
                    humi =object.getInt("humidity_level");
                    dB =object.getInt("noise_level");

                    Log.d("Test", "카페정보 받아옴");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listStatus.put(listTitle.get(0), cur+"/"+ total);
                listStatus.put(listTitle.get(1), temp+"℃");
                listStatus.put(listTitle.get(2), humi + "%");
                listStatus.put(listTitle.get(3), dB+"dB");
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test", "정보받기 실패");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    //액션바 설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
        actionBar.setTitle("매장정보"); //액션바 타이틀
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }
}