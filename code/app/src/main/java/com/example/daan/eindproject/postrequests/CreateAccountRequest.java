package com.example.daan.eindproject.postrequests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountRequest extends StringRequest {

    String username, password;

    // Constructor
    public CreateAccountRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String username, String password) {
        super(method, url, listener, errorListener);

        this.username = username;
        this.password = password;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        return params;
    }
}
