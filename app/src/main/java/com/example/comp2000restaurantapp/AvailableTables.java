package com.example.comp2000restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AvailableTables extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_tables);

        loadBackToBookings();
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