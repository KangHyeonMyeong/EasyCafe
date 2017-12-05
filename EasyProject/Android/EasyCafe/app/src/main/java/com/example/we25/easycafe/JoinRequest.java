package com.example.we25.easycafe;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by com on 2017-11-03.
 */

public class JoinRequest extends StringRequest {

    final static private  String URL = "http://kukjae.iptime.org:8080/temp/join.app" ;
    private Map<String, String> parameters;

    public JoinRequest(String e_mail, String password, String name, String birth, String phone, Response.Listener<String> listener ) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("e_mail", e_mail);
        parameters.put("password", password);
        parameters.put("name", name);
        parameters.put("birth", birth);
        parameters.put("phone", phone);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }


}


