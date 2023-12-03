package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    private void loadNewBooking() {
        Button manage_bookings = findViewById(R.id.load_new_booking);
        manage_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, NewBooking.class);
                startActivity(intent);
            }
        });

    }

    private void loadAvailableTables() {
        Button manage_bookings = findViewById(R.id.load_available_tables);
        manage_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, AvailableTables.class);
                startActivity(intent);
            }
        });

    }

    private void loadManageBookings() {
        Button manage_bookings = findViewById(R.id.load_manage_bookings);
        manage_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, ManageBookings.class);
                startActivity(intent);
            }
        });

    }

    private void loadReviews() {
        Button manage_bookings = findViewById(R.id.load_reviews);
        manage_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Reviews.class);
                startActivity(intent);
            }
        });
    }

    private void loadMyAccount() {
        Button manage_bookings = findViewById(R.id.load_my_account);
        manage_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MyAccount.class);
                startActivity(intent);
            }
        });

    }
}