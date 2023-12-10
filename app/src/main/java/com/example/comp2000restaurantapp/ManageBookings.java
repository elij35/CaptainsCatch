package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ManageBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_bookings);

        loadBackToBookings();
        loadBottomBar();
        loadPreviousBookings();
        loadEditBooking();
        loadCancelBooking();
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

    private void loadEditBooking() {
        Button bookings = findViewById(R.id.edit_booking);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditBooking.class);
            startActivity(intent);
        });
    }

    private void loadCancelBooking() {
        Button bookings = findViewById(R.id.cancel_booking);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(this, Error.class);
            startActivity(intent);
        });
    }

    private void loadBackToBookings() {
        Button bookings = findViewById(R.id.back_to_bookings);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void loadPreviousBookings() {
        Button previous_bookings = findViewById(R.id.previous_bookings);
        previous_bookings.setOnClickListener(view -> {
            Intent intent = new Intent(this, PreviousBookings.class);
            startActivity(intent);
        });
    }
}