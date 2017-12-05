package com.example.we25.jinju;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.we25.easycafe.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import customclass.DBName;
import customclass.DBOpenHelper;

/**
 * Created by we25 on 2017-06-27.
 */

public class MenuInfo extends AppCompatActivity {
    DBOpenHelper dbHleper;
    SQLiteDatabase db;

    final static String IMAGEURL = "http://kukjae.iptime.org:8080/temp/upload/";
    Handler handler = new Handler();

    String name , hotSprice, hotLprice, iceSprice, iceLprice, sangse;
    int num = 0;
    ImageButton plus, minus;

    ArrayList<String> hotprice = new ArrayList<String>();
    ArrayList<String> iceprice = new ArrayList<String>();

    Spinner spinner;
    RadioButton hotBtn;
    RadioButton iceBtn;

    TextView menuName, 상세내용, numb;

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_image);

        dbHleper = new DBOpenHelper(this , 1);
        db = dbHleper.getWritableDatabase();

        Intent intent = getIntent();
        final CafeItem cafeItem = (CafeItem) intent.getSerializableExtra("MenuData");
        readMenu(cafeItem);


        //라디오버튼 , 스피너, 텍스트뷰 인플레이션
        spinner = (Spinner) findViewById(R.id.spinner);
        hotBtn = (RadioButton) findViewById(R.id.HotBut); //뜨거운게 없으면 안보임
        if( hotLprice.equals("0")){
            hotBtn.setVisibility(View.INVISIBLE);
        }
        iceBtn = (RadioButton) findViewById(R.id.IceBut);
        imageView = (ImageView) findViewById(R.id.imageView);
        menuName = (TextView) findViewById(R.id.menuName);
        menuName.setText(name);
        상세내용 = (TextView) findViewById(R.id.content);
        상세내용.setText(sangse);
        numb = (TextView)findViewById(R.id.num);

        //시작시 어뎁터의 초기화
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item, iceprice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //라디오 버튼 클릭시 스피너 재생성
        hotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hotBtn.isChecked()){
                    resetAdater(hotprice);
                }
            }
        });
        iceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iceBtn.isChecked()){
                    resetAdater(iceprice);
                }
            }
        });
        //라디오버튼 설정 끝

        //수량조절 버튼
        plus = (ImageButton)findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num < 10){
                    num++;
                    numb.setText(num+"");
                }
                else Toast.makeText(getApplicationContext(), "최대주문 수량은 10입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        minus = (ImageButton)findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num >1){
                    num--;
                    numb.setText(num+"");
                }
                else Toast.makeText(getApplicationContext(), "최소주문 수량입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //담기버튼 동작
        Button insertBtn = (Button) findViewById(R.id.insertbtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBasket();
                Toast.makeText(getApplicationContext(), "상품을 장바구니에 담았습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        Button gotoBasket = (Button)findViewById(R.id.gotobasket);
        gotoBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoppingBasketActivity.class);
                startActivity(intent);
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한
                try{
                    URL url = new URL(IMAGEURL+cafeItem.getImage());
                    InputStream is = url.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {  // 화면에 그려줄 작업
                            imageView.setImageBitmap(bm);
                        }
                    });
                    imageView.setImageBitmap(bm);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();



    }

    //DB에서 메뉴 정보를 불러오는 메서드
    public void readMenu(CafeItem cafeItem){
        name = cafeItem.getMenu_name();
        hotSprice = cafeItem.getPrice_HS();
        hotLprice = cafeItem.getPrice_HL();
        iceSprice = cafeItem.getPrice_IS();
        iceLprice = cafeItem.getPrice_IL();
        sangse = cafeItem.getMenu_info();

        hotprice.add("(small)"+hotSprice);
        hotprice.add("(large)" + hotLprice);

        iceprice.add("(small)"+iceSprice);
        iceprice.add("(large)"+iceLprice);

    }

    //버튼 클릭에 따라 스피너를 설정
    public void resetAdater(ArrayList<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //*이미지를 지정해주는 메서드로 추후 서버에서에 받아 변경되는 부분으로 변경예정.*

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
        actionBar.setTitle("메뉴정보");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }


    public void insertBasket(){
        String 메뉴, HI, size, 수량, price;
        메뉴 = menuName.getText().toString();
        if( hotBtn.isChecked()){
            HI = "HOT";
            if( spinner.getSelectedItemPosition() == 0){
                size = "small";
                price = hotSprice;
            }
            else{
                size = "large";
                price = hotLprice;
            }
        }else {
            HI = "ICE";
            if( spinner.getSelectedItemPosition() == 0){
                size = "small";
                price = iceSprice;
            }
            else{
                size = "large";
                price = iceLprice;
            }
        }

        수량 = numb.getText().toString();


        String insetDB ="insert into "+DBName.Basket+"(메뉴, 'HOT/ICE',size , 수량, price)" +
                " values('"+메뉴+"', '"+HI+"', '"+size+"', "+수량+", "+price+")";
        db.execSQL(insetDB);
    }


}



