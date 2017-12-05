package com.example.we25.easycafe;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by com on 2017-11-07.
 */

public class ModifyRequest extends StringRequest {

    final static private  String URL = "http://kukjae.iptime.org:8080/temp/modify.app" ;
    private Map<String, String> parameters;

    public ModifyRequest(String e_mail, String newPhone, String newBirth, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("e_mail", e_mail);
        parameters.put("newPhone", newPhone);
        parameters.put("newBirth", newBirth);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }


}
