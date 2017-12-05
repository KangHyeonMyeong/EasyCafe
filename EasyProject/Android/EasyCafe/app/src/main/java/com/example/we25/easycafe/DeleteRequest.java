package com.example.we25.easycafe;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-11-07.
 */

//회원 탈퇴를 위한 request
public class DeleteRequest extends StringRequest {

    final static private String URL = "http://kukjae.iptime.org:8080/temp/delete.app";
    private Map<String, String> parameters;

    public DeleteRequest(String ID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("e_mail", ID);

    }

    @Override
    public Map<String, String> getParams()  {
        return parameters;
    }
}
