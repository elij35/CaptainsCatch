package com.example.comp2000restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login();
    }

    private void login() {
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.login_btn);

        loginbtn.setOnClickListener(v -> {

            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);

            try {
                storeLogin();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void storeLogin() throws JSONException {
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        Context context = getApplicationContext();
        String FILE_NAME = "login.json";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("customerName", username.getText().toString());
        jsonBody.put("customerPhoneNumber", password.getText().toString());
        final String requestBody = jsonBody.toString();
        Storage.writeJson(context, FILE_NAME, requestBody);
    }
}