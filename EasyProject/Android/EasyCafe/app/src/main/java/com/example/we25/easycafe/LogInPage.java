package com.example.we25.easycafe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import customclass.MemberInfo;


public class LogInPage extends AppCompatActivity {
    //로그인
    EditText ID, password;
    //회원 정보를 담을 객체를 인스턴스화
    MemberInfo info = new MemberInfo();

    //회원가입 아이디 중복체크
    boolean IDCheck = false;

    ProgressDialog dialog;
    //결과 코드를 받는 문자열
    String resultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        //아이디와 패스워드를 인플레이션
        ID = (EditText) findViewById(R.id.IDText);
        password = (EditText) findViewById(R.id.passwordText);

        //로그인 버튼 클릭시 동작
        Button login =  (Button) findViewById(R.id.logInBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });


        // 회원가입 텍스트 클릭 시 동작 -> 회원가입 액티비티로 넘어감
        TextView jointext = (TextView) findViewById(R.id.joinText);
                jointext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                        startActivity(intent);
            }
        });

        /*
        //임시 메인엑티비티로 가기위한 버튼
        Button proto = (Button) findViewById(R.id.protobutton);
        proto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info = new MemberInfo("임시아이디", "임시비번", "관리자", "1000/01/01" , "만능비번", true);
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                intent.putExtra("MemberInfo", info);
                saveLogin(info);
                startActivity(intent);
                finish();
            }
        });
        */
    }




    //이메일 형식 판별메서드
    public static boolean checkEmail(String email){
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }


     void parseJsonData(String jsonString) {
        try {
            JSONObject object = new JSONObject(jsonString);
            Log.d("RESULTCODE","TEST");

            resultCode = object.getString("resultCode");

            if(resultCode.equals("200")){
                String name = object.getString("name");
                String phone = object.getString("phone");
                String ID = object.getString("e_mail");
                String birth = object.getString("birth");
                boolean manageCode = false;
                if( object.getInt("manage_flag") == 1) {
                    manageCode = true;
                }

                MemberInfo memberInfo = new MemberInfo(ID,null, name,birth , phone, manageCode);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("MemberInfo" , memberInfo );
                saveLogin(memberInfo);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "정보가 일치하지 않습니다." , Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }


    //로그인~ 큐~
    public void loginRequest() {

        String url = "http://kukjae.iptime.org:8080/temp/login.app";

        dialog = new ProgressDialog(LogInPage.this);
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJsonData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "HTTP 통신 Error" , Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> loginParams = new HashMap<>();

                loginParams.put("e_mail", ID.getText().toString());
                loginParams.put("password", password.getText().toString());
                loginParams.put("token" , FirebaseInstanceId.getInstance().getToken());
                return loginParams;
            }
        };

        RequestQueue queue = Volley.newRequestQueue( getApplicationContext() );
        queue.add(stringRequest);
    }


    public void saveLogin(MemberInfo memberInfo){
        SharedPreferences pref = getSharedPreferences("pref" , MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("ID", memberInfo.getID());
        editor.putString("Name", memberInfo.getName());
        editor.putString("Birth", memberInfo.getBirth());
        editor.putString("Phone_Num", memberInfo.getPhone_Num());
        editor.putBoolean("MGCode" , memberInfo.isManagerCode());
        editor.putBoolean("Login" , true);
        editor.commit();
    }
}