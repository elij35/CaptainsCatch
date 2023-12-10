package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AvailableTables extends AppCompatActivity {
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_tables);

        loadBackToBookings();
        loadBottomBar();
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

    private void loadBackToBookings() {
        Button bookings = findViewById(R.id.back_to_bookings);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvailableTables.this, NewBooking.class);
                startActivity(intent);
            }
        });
    }
}