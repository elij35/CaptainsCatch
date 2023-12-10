package com.example.comp2000restaurantapp;

import static com.example.comp2000restaurantapp.Storage.readJson;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;

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

        try {
            loadBookingDetails();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBookingDetails() throws IOException, ParseException {

        String booking = getFilesDir() + "/" + "booking.json";

        TextView date = findViewById(R.id.booking1);
        date.setText(MessageFormat.format("Date: {0}", readJson(booking).get("date").textValue()));

        TextView tableSize = findViewById(R.id.textBox1_tableSize);
        tableSize.setText(MessageFormat.format("Table Size: {0}", readJson(booking).get("tableSize").textValue()));

        TextView location = findViewById(R.id.textBox1_location);
        location.setText(MessageFormat.format("Location: {0}", readJson(booking).get("seatingArea").textValue()));

        TextView mealTime = findViewById(R.id.textBox1_mealTime);
        mealTime.setText(MessageFormat.format("Meal: {0}", readJson(booking).get("meal").textValue()));
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