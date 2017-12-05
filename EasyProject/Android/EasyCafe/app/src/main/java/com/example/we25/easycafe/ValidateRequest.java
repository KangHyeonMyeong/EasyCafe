package com.example.we25.easycafe;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by com on 2017-11-06.
 */

public class ValidateRequest extends StringRequest {

    final static private  String URL = "http://kukjae.iptime.org:8080/temp/overlopCheck.app" ;
    private Map<String, String> parameters;

    public ValidateRequest(String e_mail,  Response.Listener<String> listener , Response.ErrorListener ErrorListener ) {
        super(Method.POST, URL, listener, ErrorListener);
        parameters = new HashMap<>();
        parameters.put("e_mail", e_mail);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }


}
