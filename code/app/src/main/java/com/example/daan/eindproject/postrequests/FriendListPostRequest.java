package com.example.daan.eindproject.postrequests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FriendListPostRequest extends StringRequest {

    String friendName;

    // Constructor
    public FriendListPostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String friendName) {
        super(method, url, listener, errorListener);

        this.friendName = friendName;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("friendName", friendName);
        return params;
    }
}
