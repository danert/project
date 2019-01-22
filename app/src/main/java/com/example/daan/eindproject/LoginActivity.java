package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText usernameView, passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // grab edittexts
        usernameView = findViewById(R.id.usernameView);
        passwordView = findViewById(R.id.passwordView);
    }

    // when user has pressed login button
    public void loginClick(View v) {

        // grab username and password
        final String username = usernameView.getText().toString();
        final String password = passwordView.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/profiles?username=%s", username);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                // if username doesn't exist, notify user
                if (response.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Gebruikersnaam niet gevonden!", Toast.LENGTH_SHORT).show();
                }

                // if username does exist
                else {

                    try {

                        // grab account
                        JSONObject account = response.getJSONObject(0);

                        // grab password of account
                        String accountPassword = account.getString("password");

                        // if password is correct, move to homepage
                        if (accountPassword.equals(password)) {

                            Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }

                        // if password is incorrect, notify user
                        else {
                            Toast.makeText(getApplicationContext(), "Verkeerd wachtwoord ingevuld!", Toast.LENGTH_SHORT).show();
                        }

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

    // moves user to register activity
    public void registerClick(View v) {

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
