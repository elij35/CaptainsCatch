package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login();
    }

    private void login() {

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.login_btn);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        String data = preferences.getString("login", null);

        loginbtn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("user", data);
            editor.putString("password", password.getText().toString());
            editor.apply();

            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);

        });
    }

}