package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AvailableTablesView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_tables_view);

        loadBackToBookings();
        button2();
        button3();
        button4();
        button5();
        button6();
        button7();
        button8();
    }

    private void button2() {
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }


    private void button3() {
        Button button2 = findViewById(R.id.outside_button3);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void button4() {
        Button button2 = findViewById(R.id.outside_button4);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void button5() {
        Button button2 = findViewById(R.id.outside_button5);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void button6() {
        Button button2 = findViewById(R.id.button6);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void button7() {
        Button button2 = findViewById(R.id.outside_button7);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
            startActivity(intent);
        });
    }

    private void button8() {
        Button button2 = findViewById(R.id.button8);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, NewBooking.class);
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
}