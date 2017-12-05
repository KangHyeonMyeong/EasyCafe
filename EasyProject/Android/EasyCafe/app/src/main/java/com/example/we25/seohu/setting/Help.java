package com.example.we25.seohu.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.android.volley.Request;
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
import java.util.HashMap;

public class Help extends AppCompatActivity {

    private ExpandableListView mListView;
    private BaseExpandableAdapter listAdapter;
    private ArrayList<String> mGroupList = new ArrayList<String>();
    private HashMap<String, String> mChildList = new HashMap<String, String>();

    String url = "http://kukjae.iptime.org:8080/temp/help.app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        mListView = (ExpandableListView) findViewById(R.id.exListView);

        getData();
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    mGroupList = new ArrayList<>();
                    mChildList = new HashMap<>();
                    final int result = jsonArray.length();
                    for(int i=0; i < result; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String content = jsonObject.getString("content");

                        mGroupList.add(title);
                        mChildList.put(mGroupList.get(i), content);

                    }
                    listAdapter = new BaseExpandableAdapter(Help.this, mGroupList, mChildList);
                    mListView.setAdapter(listAdapter);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
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
        actionBar.setTitle("도움말");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }
}
