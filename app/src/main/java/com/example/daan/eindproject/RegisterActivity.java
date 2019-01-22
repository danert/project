package com.example.daan.eindproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Registreren");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    // create account for user and put in database
    public void createAccount(View v) {















        // move back to login screen
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
