package com.example.we25.easycafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.we25.jinju.OrderActivity;
import com.example.we25.jinju.ShoppingBasketActivity;
import com.google.firebase.iid.FirebaseInstanceId;

import customclass.MemberInfo;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper viewFlipper;
    TextView username , userphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //로그인한 정보를 인텐트에 실어 받아옴

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Test", "token:" + token);

        //네이게이션뷰? 를 설정하는 부분으로 학습필요
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //네비게이션뷰의 헤더부분을 관리하는 부분
        View headerView = navigationView.getHeaderView(0); //헤더를 뷰로 받아서
        username = (TextView) headerView.findViewById(R.id.userName); //헤더뷰에서 찾는담
        userphone = (TextView) headerView.findViewById(R.id.userPhone);
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        username.setText(pref.getString("Name" , null));
        userphone.setText( "전화번호:" +pref.getString("Phone_Num" ,"000-0000-0000")+"\n" +
                "이메일:" + pref.getString("ID", "조회실패"));

        if( !pref.getBoolean("MGCode", false)) {
            MenuItem menuView = navigationView.getMenu().getItem(5);
            menuView.setVisible(false);
        }


        //뷰페이지 설정부분
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        for(int i = 0 ; i < 4 ; i ++){
            ImageView bener = new ImageView(this);
            bener.setScaleType(ImageView.ScaleType.FIT_XY);
            bener.setImageResource(R.drawable.coffe_1+i);
            viewFlipper.addView(bener);
        }
        viewFlipper.setInAnimation(this, R.anim.move_left);
        viewFlipper.setOutAnimation(this,R.anim.move_right);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //네이게이션에 아이템별 클릭시 동작
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.infomation) {
            Intent intent = new Intent(getApplicationContext() , InfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.cafe_Info) {
            Intent intent = new Intent(getApplicationContext() , CafeStatusInfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.order) {
            Intent intent = new Intent(getApplicationContext(), com.example.we25.jinju.MenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.order_list) {
            Intent intent = new Intent(getApplicationContext(), com.example.we25.jinju.OrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(getApplicationContext(), com.example.we25.seohu.setting.Setting_Main.class);
            startActivity(intent);
        }else if (id == R.id.sensor){
            Intent intent = new Intent(getApplicationContext(), com.example.we25.seohu.sensor.Sensor_Main.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickInfo( View v ){
        Intent intent = new Intent(getApplicationContext() , ShoppingBasketActivity.class);
        startActivity(intent);
    }
    public void onClickMejang(View v){
        Intent intent = new Intent(getApplicationContext() , CafeStatusInfoActivity.class);
        startActivity(intent);
    }
    public void onClickMenu(View v){
        Intent intent= new Intent(getApplicationContext(), com.example.we25.jinju.MenuActivity.class);
        startActivity(intent);
    }
}
