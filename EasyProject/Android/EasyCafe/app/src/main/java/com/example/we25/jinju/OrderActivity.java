package com.example.we25.jinju;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.we25.easycafe.JoinActivity;
import com.example.we25.easycafe.MainActivity;
import com.example.we25.easycafe.R;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import customclass.ItemBasket;

import static android.os.Build.ID;

/**
 * Created by com on 2017-10-26.
 */


// 네이게이션바 주문내역 엑티비티
// order_list.xml -> 리스트뷰 정의


public class OrderActivity extends AppCompatActivity {

    ListView listView;
    OrderListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);
        orderList();

        listView = (ListView)findViewById(R.id.listView);
        adapter = new OrderListAdapter();

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setMessage( ((OrderItem)adapter.getItem(position)).getOrder_content())
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                builder.show();

            }
        });

    }


    private void orderList() {

        String Url = "http://kukjae.iptime.org:8080/temp/orderList.app";
        StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    parseData(object);

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                map.put("e_mail" , pref.getString("ID", ""));
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void parseData(JSONObject jsonObject) throws JSONException{
        JSONArray array = jsonObject.getJSONArray("orderList");
        for(int i = 0 ; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            adapter.addItem(new OrderItem( object.getString("order_date"), object.getString("order_status"),object.getString("order_content") ));
        }

    }






    //뒤로가기 버튼
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
        actionBar.setTitle("주문내역");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }

    public class OrderListAdapter extends BaseAdapter{
        ArrayList<OrderItem> list = new ArrayList<>();
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        public void addItem(OrderItem item) {
            list.add(item);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            OrderItemView orderItemView = new OrderItemView(getBaseContext());
            orderItemView.setOrderNum(""+(position+1));
            OrderItem orderItem = list.get(position);
            orderItemView.setOrderDate(orderItem.getOrder_date());
            orderItemView.setOrderStatus(orderItem.getOrder_status());
            return orderItemView;
        }
    }
}



