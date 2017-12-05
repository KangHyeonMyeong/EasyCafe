package com.example.we25.jinju;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.we25.easycafe.MainActivity;
import com.example.we25.easycafe.R;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import customclass.DBName;
import customclass.DBOpenHelper;
import customclass.ItemBasket;
import customclass.MenuListAdapter;

public class ShoppingBasketActivity extends AppCompatActivity {
    ListView listView;
    MenuListAdapter adapter = new MenuListAdapter(this);

    DBOpenHelper DBOpenHelper = new DBOpenHelper(this,1);
    SQLiteDatabase db;

    TextView total_Price;
    String menu , info;
    String order_Id, order_name;
    int number, price, total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingbasket);
        db = DBOpenHelper.getWritableDatabase();
        setAdapter();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        total_Price = (TextView) findViewById(R.id.price);
        total_Price.setText("￦ "+total);

        SharedPreferences pref = getSharedPreferences("pref" , MODE_PRIVATE);
        order_Id = pref.getString("ID" , null);
        order_name = pref.getString("Name", null);


        // 장바구니 비우기 버튼 클릭시 장바구니 비워짐
        Button basketCancle = (Button)findViewById(R.id.basketCancle);
        basketCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBasket();
                adapter.resetAdapter();
                adapter.notifyDataSetChanged();
            }
        });



        // 주문 버튼 클릭시 다이얼로그 띄움
        Button order = (Button) findViewById(R.id.basketOrder);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOrder();

            }
        });
    }

    private void setAdapter(){
        for( int i = 0 ; i < menuConut() ; i++){
            readBasket(i);
            adapter.addItem(new ItemBasket(menu , info, number, price));
        }
    }


    public int menuConut(){
        String count = "select * from "+ DBName.Basket;
        Cursor c = db.rawQuery(count, null);
        return c.getCount();
    }

    public void readBasket(int i){
        String read = "select * from "+DBName.Basket;
        Cursor c = db.rawQuery(read, null);
        for (int n = 0 ; n <= i;n++){
            c.moveToNext();
        }
        menu = c.getString(0);
        info = c.getString(1) + " / " + c.getString(2) + " / ￦" + c.getString(4);
        number = c.getInt(3);
        price = c.getInt(4)*number;
        total = total + price;
    }

    public void deleteBasket(){
        db.execSQL("delete from "+DBName.Basket);
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
        actionBar.setTitle("장바구니");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }


    private void sendOrder(){

        //다이얼로그 빌더로 박스를 설정한다.
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        //자신이 원하는 레이아웃을 박스에 삽입
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.order);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.order_dialogbox, viewGroup);
        builder.setView(view);

        final AlertDialog dialog =  builder.create();
        dialog.show();



        //취소 버튼 클릭 시 다이얼로그가 꺼지고 장바구니 목록화면 보여짐
        Button no =(Button) view.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                dialog.cancel();

            }
        });

        //확인 버튼 클릭 시 장바구니 내역을 서버로 보냄 -> 관리자에게 주문이 왔다는 push message 보냄
        // 메인 Activity로 화면 전환
        Button yes = (Button) view.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String URL = "http://kukjae.iptime.org:8080/temp/order.app";
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonrespon = new JSONObject(response);
                            int rs = jsonrespon.getInt("resultCode");
                            if( rs == 200 ) Toast.makeText(getApplicationContext(), "주문되었습니다." , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "네트워크가 불안정합니다.",  Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> order = new HashMap<>();
                        String orderContent = "";
                        order.put("order_id" , order_Id );
                        order.put("order_name", order_name);
                        for( int i = 0 ; i< menuConut() ; i++ ){
                            ItemBasket itemBasket = ((ItemBasket) adapter.getItem(i));
                            orderContent += itemBasket.getMenuName() + ":" + itemBasket.getInfo() + "/ "+ itemBasket.getNum() +"개\n";
                        }
                        order.put("order_content", orderContent);
                        order.put("order_status", "준비중");
                        return order;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplication());
                queue.add(request);
            }

        });

    }
}
