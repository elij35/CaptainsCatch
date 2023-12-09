package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        loadLoginPage();
        loadBottomBar();

        SwitchCompat notification_switch = findViewById(R.id.notification_switch);

        notification_switch.setOnClickListener(view -> {

            if (notification_switch.isChecked()) {
                SharedPreferences.Editor editor = getSharedPreferences("switchOn", MODE_PRIVATE).edit();
                editor.putBoolean("switchOn", true);
                editor.apply();
            } else {
                SharedPreferences.Editor editor = getSharedPreferences("switchOff", MODE_PRIVATE).edit();
                editor.putBoolean("switchOff", false);
                editor.apply();
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

    private void loadLoginPage() {
        Button bookings = findViewById(R.id.sign_out_btn);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(Settings.this, Login.class);
            startActivity(intent);
        });
    }
}