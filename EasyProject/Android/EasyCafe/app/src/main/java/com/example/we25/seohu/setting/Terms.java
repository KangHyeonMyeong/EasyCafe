package com.example.we25.seohu.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.we25.easycafe.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Terms extends AppCompatActivity {

    TextView txt;
    private static final String url = "http://kukjae.iptime.org:8080/temp/terms.app";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
    }

    public void onButtonServiceInfoClicked(View v) {
        final ScrollView scrollView = ((ScrollView)findViewById(R.id.scrollView));
        txt = (TextView)findViewById(R.id.txt);
        scrollView.scrollTo(0,0);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txt.setText(response.getString("term"));
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                responseGetMessageErrorListener
        );
        queue.add(request);
    }

    public void onButtonPrivacyUsageClicked(View v) {
        final ScrollView scrollView = ((ScrollView)findViewById(R.id.scrollView));
        txt = (TextView)findViewById(R.id.txt);
        scrollView.scrollTo(0,0);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txt.setText(response.getString("policy"));
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                responseGetMessageErrorListener
        );
        queue.add(request);
    }

    public void onButtonPrivacyOfferClicked(View v) {
        final ScrollView scrollView = ((ScrollView)findViewById(R.id.scrollView));
        txt = (TextView)findViewById(R.id.txt);
        scrollView.scrollTo(0,0);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txt.setText(response.getString("agreement"));
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                responseGetMessageErrorListener
        );
        queue.add(request);
    }


    private Response.ErrorListener responseGetMessageErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            txt.setText(error.getMessage());
        }
    };


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
        actionBar.setTitle("이용약관");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }

}
