package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        loadNewBooking();
        loadAvailableTables();
        loadManageBookings();
        loadReviews();
        loadMyAccount();
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

    private void loadNewBooking() {
        Button manage_bookings = findViewById(R.id.load_new_booking);
        manage_bookings.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void loadAvailableTables() {
        Button available_tables = findViewById(R.id.load_available_tables);
        available_tables.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, AvailableTables.class);
            startActivity(intent);
        });
    }

    private void loadManageBookings() {
        Button manage_bookings = findViewById(R.id.load_manage_bookings);
        manage_bookings.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, ManageBookings.class);
            startActivity(intent);
        });
    }

    private void loadReviews() {
        Button reviews = findViewById(R.id.load_reviews);
        reviews.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Reviews.class);
            startActivity(intent);
        });
    }

    private void loadMyAccount() {
        Button account = findViewById(R.id.load_my_account);
        account.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Settings.class);
            startActivity(intent);
        });
    }
}