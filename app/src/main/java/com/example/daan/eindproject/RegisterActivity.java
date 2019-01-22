package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    EditText usernameView, passwordView, password2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Registreren");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // grab edit texts
        usernameView = findViewById(R.id.usernameView);
        passwordView = findViewById(R.id.passwordView);
        password2View = findViewById(R.id.password2View);
    }

    // create account for user and put in database
    public void createAccount(View v) {

        // grab username and passwords
        final String username = usernameView.getText().toString();
        final String password = passwordView.getText().toString();
        final String password2 = password2View.getText().toString();

        // check if user has filled in everything
        if (username.length() == 0 || password.length() == 0 || password2.length() == 0) {
            Toast.makeText(getApplicationContext(), "Vul alle velden in!", Toast.LENGTH_SHORT).show();
            return;
        }

        // check if username already exists
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://ide50-danert.legacy.cs50.io:8080/profiles?username=%s", username);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                // if username is already taken, notify user
                if (response.length() != 0) {
                    Toast.makeText(getApplicationContext(), "Gebruikersnaam al in gebruik!", Toast.LENGTH_SHORT).show();
                }

                // if not taken
                else {

                    // check if two passwords are the same (if not, notify user and clear edit texts
                    if (!password.equals(password2)) {
                        Toast.makeText(getApplicationContext(), "Wachtwoorden komen niet overeen!", Toast.LENGTH_SHORT).show();
                        passwordView.setText("");
                        password2View.setText("");
                    }

                    // add account to database
                    else {

                        String url = "https://ide50-danert.legacy.cs50.io:8080/profiles";
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        CreateAccountRequest request = new CreateAccountRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                // move back to login screen
                                Toast.makeText(getApplicationContext(), "Account aangemaakt! Je kunt nu inloggen.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }, username, password);
                        queue.add(request);
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
