package com.example.comp2000restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

            try {
                storeLogin();
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void storeLogin() throws JSONException, IOException {
        TextView username = (TextView) findViewById(R.id.username);
        TextView phoneNumber = (TextView) findViewById(R.id.phone_number);

        Context context = getApplicationContext();
        String FILE_NAME = "login.json";

        if (username.getText().toString().length() != 0) {
            if (phoneNumber.getText().toString().length() != 0) {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("customerName", username.getText());
                jsonBody.put("customerPhoneNumber", phoneNumber.getText());
                final String requestBody = jsonBody.toString();

                Storage.writeJson(context, FILE_NAME, requestBody);
                createJson();

                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            } else {
                Toast.makeText(context, "Must enter a phone number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Must enter full name", Toast.LENGTH_SHORT).show();
        }
    }

    private void createJson() throws IOException, JSONException {
        String fileBookings = "booking.json";
        String fileReviews = "reviews.json";
        String notifications = "notifications.json";

        JSONObject jsonBody = new JSONObject();

        final String bookingBody = jsonBody.toString();
        final String reviewsBody = jsonBody.toString();

        jsonBody.put("notificationsOn", "On");
        final String notificationBody = jsonBody.toString();

        Storage.writeJson(getApplicationContext(), fileBookings, bookingBody);
        Storage.writeJson(getApplicationContext(), fileReviews, reviewsBody);
        Storage.writeJson(getApplicationContext(), notifications, notificationBody);
    }
}