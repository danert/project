package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Vrienden");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        showFriendList();
    }

    // add friend to friend list
    public void addFriend(View v) {

        // grab username
        EditText usernameText = findViewById(R.id.usernameText);
        final String friendName = usernameText.getText().toString();

        // prevent user from adding itself
        if (friendName.equals(username)) {
            Toast.makeText(getApplicationContext(), "Je kunt jezelf niet als vriend toevoegen!", Toast.LENGTH_SHORT).show();
            return;
        }

        // check if username exists in database
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/profiles?username=%s", friendName);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                // if user doesn't exist, notify user
                if (response.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Gebruiker niet gevonden!", Toast.LENGTH_SHORT).show();
                }

                else {

                    // add user to personal friend list
                    String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sfriendlist", username);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    FriendListPostRequest request = new FriendListPostRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }, friendName);
                    queue.add(request);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    // show list of friends to user
    public void showFriendList() {

        // get friend list from database
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/%sfriendlist", username);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                ArrayList<String> friendList = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {

                    try {

                        // add friend name to friend list
                        JSONObject friend = response.getJSONObject(i);
                        String friendName = friend.getString("friendName");
                        friendList.add(friendName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);


    }
}
