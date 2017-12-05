package com.example.we25.easycafe;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {
    TextView name, ID;
    EditText phone, birth;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        name = (TextView) findViewById(R.id.memberName);
        ID = (TextView) findViewById(R.id.memberID);
        phone = (EditText) findViewById(R.id.memberPhone);
        birth = (EditText) findViewById(R.id.memberBirth);

        name.setText(pref.getString("Name", "조회실패"));
        ID.setText(pref.getString("ID", "조회실패"));
        phone.setText(pref.getString("Phone_Num", "조회실패"));
        birth.setText(pref.getString("Birth", "조회실패"));


        //관리자 일때만 보여지는 이미지(스마일)
        imageView = (ImageView) findViewById(R.id.managerMark);
        if (pref.getBoolean("MGCode", false)) {
            imageView.setVisibility(View.VISIBLE);
        }



/*

        // 비밀번호 변경 버튼 클릭시 비밀번호 변경 엑티비티로 화면 전환
        Button changePassword = (Button) findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);

                startActivity(intent);

            }
        });

*/


        // 회원탈퇴 버튼 클릭시 동작
        Button unjoin = (Button) findViewById(R.id.unjoin);
        unjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setMessage("정말로 탈퇴 하시겠습니까?");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            String resultCode = jsonResponse.getString("resultCode");

                                            if (resultCode.equals("200")) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                                                builder.setMessage("탈퇴가 정상적으로 처리되었습니다.")
                                                        .setPositiveButton("확인",
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        // 확인 버튼 클릭 시 로그아웃 처리 후 로그인페이지로 이동
                                                                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                                                        String e_mail = pref.getString("ID" , "");
                                                                        logoutRequest(e_mail);
                                                                        Toast.makeText(getApplicationContext(), "탈퇴가 처리되었습니다", Toast.LENGTH_LONG).show();
                                                                        Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                                                                        logOut();
                                                                        startActivity(intent);
                                                                        finishAffinity();
                                                                        finish();

                                                                    }
                                                                });
                                                builder.show();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                DeleteRequest deleteRequest = new DeleteRequest(ID.getText().toString(), responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                queue.add(deleteRequest);

                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();

            }

        });


        // 정보수정 버튼 클릭시 동작
        Button changeInfo = (Button) findViewById(R.id.changeInfo);
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                builder.setMessage("정보를 수정하시겠습니까?");
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String e_mail = ID.getText().toString();
                                final String newPhone = phone.getText().toString();
                                final String newBirth = birth.getText().toString();


                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            String resultCode = jsonResponse.getString("resultCode");

                                            if (resultCode.equals("200")) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                                                builder.setMessage("정보가 수정되었습니다.")
                                                        .setPositiveButton("확인",
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = pref.edit();
                                                                        editor.putString("Birth" , newBirth);
                                                                        editor.putString("Phone_Num" , newPhone);
                                                                        editor.commit();

                                                                    }
                                                                });
                                                builder.show();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                ModifyRequest modifyRequest = new ModifyRequest(e_mail, newPhone, newBirth, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                queue.add(modifyRequest);

                            }
                        });

                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();

            }

        });



    }
    //뒤로 가기 설정
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("회원정보");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
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


}






