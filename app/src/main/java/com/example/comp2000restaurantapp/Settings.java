package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        loadLoginPage();
        loadBottomBar();
        setNotificationStatus();
    }

    private void loadLoginPage() {
        Button bookings = findViewById(R.id.sign_out_btn);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.this, Login.class);
            startActivity(intent);
        });
    }

    private void setNotificationStatus() {
        SwitchCompat notification_switch = findViewById(R.id.notification_switch);

        try {
            notification_switch.setChecked(Notifications.checkData(getApplicationContext()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        notification_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String notificationFile = "notifications.json";

            if (isChecked) {
                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("notificationsOn", "On");
                    final String bookingBody = jsonBody.toString();
                    Storage.writeJson(getApplicationContext(), notificationFile, bookingBody);
                } catch (JSONException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("notificationsOff", "Off");
                    final String bookingBody = jsonBody.toString();
                    Storage.writeJson(getApplicationContext(), notificationFile, bookingBody);
                } catch (JSONException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void loadBottomBar() {
        ImageButton home = findViewById(R.id.home_icon);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        ImageButton bookings = findViewById(R.id.calendar_icon);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });

        ImageButton reviews = findViewById(R.id.reviews_icon);
        reviews.setOnClickListener(view -> {
            Intent intent = new Intent(this, Reviews.class);
            startActivity(intent);
        });

        ImageButton settings = findViewById(R.id.account_icon);
        settings.setOnClickListener(view -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });
    }
}