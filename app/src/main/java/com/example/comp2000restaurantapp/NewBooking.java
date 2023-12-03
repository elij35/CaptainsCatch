package com.example.comp2000restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_booking);

        loadBookNow();
        loadManageBookings();
        loadAvailableTables();
    }

    private void loadBookNow() {
        Button bookings = findViewById(R.id.book_now_btn);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewBooking.this, Success.class);
                startActivity(intent);
            }
        });
    }

    private void loadManageBookings() {
        Button bookings = findViewById(R.id.manage_bookings);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewBooking.this, ManageBookings.class);
                startActivity(intent);
            }
        });
    }

    private void loadAvailableTables() {
        Button bookings = findViewById(R.id.available_tables);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewBooking.this, AvailableTables.class);
                startActivity(intent);
            }
        });
    }
}