package com.example.we25.jinju;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.we25.easycafe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import customclass.DBName;
import customclass.DBOpenHelper;


// activity_menu.xml -> 액션바 설정

public class MenuActivity extends AppCompatActivity {

    // 툴바와 액션바 탭에 들어갈 각각의 프래그먼트 객체 정의(커피, 에이드, 주스)

//    Toolbar toolbar;

    Coffee coffee;
    Ade ade;
    Juice juice;

    ArrayList<CafeItem> coffeeList = new ArrayList<>();
    ArrayList<CafeItem> adeList = new ArrayList<>();
    ArrayList<CafeItem> juiceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getMenuList();

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

//        getSupportFragmentManager().beginTransaction().replace(R.id.container, coffee).commit();


// 탭 버튼 추가

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("커피"));
        tabs.addTab(tabs.newTab().setText("에이드"));
        tabs.addTab(tabs.newTab().setText("주스"));


// position 에 따라 각각의 프래그먼트로 이동
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();


                Fragment selected = null;
                if (position == 0) {
                    selected = coffee;
                } else if (position == 1) {
                    selected = ade;
                } else if (position == 2) {
                    selected = juice;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }
    //액션바 설정
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
        actionBar.setTitle("메뉴"); //액션바 타이틀
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return  true;
    }

    private void getMenuList(){
        String Url = "http://kukjae.iptime.org:8080/temp/menulist.app";
        StringRequest request = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    parseData(object);

                    coffee = newCInstance(coffeeList);
                    ade = newAInstance(adeList);
                    juice = newJInstance(juiceList);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, coffee).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void parseData(JSONObject jsonObject) throws JSONException{
        JSONArray array = jsonObject.getJSONArray("MENU");
        for(int i = 0 ; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            if( object.getString("menu_category").equals("coffee")) {
                coffeeList.add(new CafeItem(
                        object.getString("menu_image"),
                        object.getString("menu_name"),
                        object.getString("menu_category"),
                        object.getString("menu_info"),
                        object.getString("price_HS"),
                        object.getString("price_HL"),
                        object.getString("price_IS"),
                        object.getString("price_IL")
                ));
            }else  if( object.getString("menu_category").equals("ade")) {
                adeList.add(new CafeItem(
                        object.getString("menu_image"),
                        object.getString("menu_name"),
                        object.getString("menu_category"),
                        object.getString("menu_info"),
                        object.getString("price_HS"),
                        object.getString("price_HL"),
                        object.getString("price_IS"),
                        object.getString("price_IL")
                ));
            }else  if( object.getString("menu_category").equals("juice")){
                juiceList.add(new CafeItem(
                        object.getString("menu_image"),
                        object.getString("menu_name"),
                        object.getString("menu_category"),
                        object.getString("menu_info"),
                        object.getString("price_HS"),
                        object.getString("price_HL"),
                        object.getString("price_IS"),
                        object.getString("price_IL")
                ));
            }
        }
    }

    private static Coffee newCInstance(ArrayList list){
        Coffee coffee =new Coffee();
        Bundle args = new Bundle();
        args.putSerializable("list" , list);
        coffee.setArguments(args);
        return coffee;
    }
    private static Juice newJInstance(ArrayList list){
        Juice juice =new Juice();
        Bundle args = new Bundle();
        args.putStringArrayList("list",list);
        juice.setArguments(args);
        return juice;
    }
    private static Ade newAInstance(ArrayList list){
        Ade ade =new Ade();
        Bundle args = new Bundle();
        args.putStringArrayList("list",list);
        ade.setArguments(args);
        return ade;
    }
}
