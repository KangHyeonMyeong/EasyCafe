package com.example.we25.easycafe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JoinActivity extends AppCompatActivity {

    private String e_mail;
    private String password;
    private String name;
    private String birth;
    private String phone;
    private boolean validate = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        final EditText IDText = (EditText) findViewById(R.id.IDText);
        final EditText PasswordText = (EditText) findViewById(R.id.PasswordText);
        final EditText PasswordConFirmText = (EditText) findViewById(R.id.PasswordConFirmText);
        final EditText NameText = (EditText) findViewById(R.id.NameText);
        final EditText BirthText = (EditText) findViewById(R.id.BirthText);
        final EditText PhoneText = (EditText) findViewById(R.id.PhoneText);




        // 비밀번호 일치 검사 -> 비밀번호 일치 시 배경색 노랑 / 불일치 시 배경색 빨강
        PasswordConFirmText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = PasswordText.getText().toString();
                String confirm = PasswordConFirmText.getText().toString();

                if (password.equals(confirm)) {
                    PasswordText.setBackgroundColor(Color.YELLOW);
                    PasswordConFirmText.setBackgroundColor(Color.YELLOW);
                } else {
                    PasswordText.setBackgroundColor(Color.RED);
                    PasswordConFirmText.setBackgroundColor(Color.RED);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //중복확인 버튼 클릭 시 작동
        final Button overlop = (Button) findViewById(R.id.overlop);
        overlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_mail = IDText.getText().toString();
                if (validate) {
                    return;
                }
                if (e_mail.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("아이디는 빈칸일 수 없습니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    return;
                }


                if(!checkEmail(e_mail)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("이메일 형식이 아닙니다")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String permission = jsonResponse.getString("permission");
                            if (permission.equals("possible")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage("사용 가능한 아이디 입니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();

                                validate = true;

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage("사용할 수 없는 아이디 입니다.")
                                        .setPositiveButton("확인", null)
                                        .create()
                                        .show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };
                Response.ErrorListener error = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext() , "통신실패", Toast.LENGTH_LONG).show();
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(e_mail, responseListener , error);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(validateRequest);
            }
        });



        // 가입하기 버튼 클릭 시 작동
        Button joinButton = (Button) findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 비밀번호 일치 확인 -> 비밀번호 불일치 시 토스트 메시지
                if (!PasswordText.getText().toString().equals(PasswordConFirmText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다!", Toast.LENGTH_LONG).show();
                    PasswordText.setText("");
                    PasswordConFirmText.setText("");
                    PasswordText.requestFocus();
                    return;
                }


                String e_mail = IDText.getText().toString();
                String password = PasswordText.getText().toString();
                String name = NameText.getText().toString();
                String birth = BirthText.getText().toString();
                String phone = PhoneText.getText().toString();


                if(!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("중복 체크를 먼저 해주세요.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    return;

                }

                if(e_mail.equals("")|| password.equals("") || name.equals("") || birth.equals("") || phone.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    return;

                }





                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String resultCode = jsonObject.getString("resultCode");

                            if (resultCode.equals("200")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //확인 버튼 클릭 시 현재 액티비티 종료
                                                        finish();
                                                    }
                                                });
                                builder.show();



                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                };
                JoinRequest joinRequest = new JoinRequest(e_mail, password, name, birth, phone, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(joinRequest);

            }

        });
    }



    // Email 형식 체크
    public boolean checkEmail(String email) {
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;

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
        actionBar.setTitle("회원가입");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        return  true;
    }

}

