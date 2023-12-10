package com.example.comp2000restaurantapp;

import static com.example.comp2000restaurantapp.Storage.readJson;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

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
            checkData();

            if (checkData()) {
                loadBookingDetails();
            } else {
                hide();
            }

        } catch (IOException | ParseException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkData() throws IOException {
        String booking = getFilesDir() + "/" + "booking.json";
        return Storage.readJson(booking).has("date");
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
            cancelAlert();
        });
    }

    private void cancelAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Cancel booking");
        builder.setMessage("Are you sure you want to cancel your booking?");
        builder.setPositiveButton("Confirm", (dialog, which) -> {

            String booking = getFilesDir() + "/" + "booking.json";
            try {
                if (DateNow.dateDifference(Storage.readJson(booking).get("date").textValue()) <= 1) {
                    showDateAlert();
                } else {
                    try {
                        hide();
                    } catch (JSONException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            } catch (ParseException | IOException e) {
                throw new RuntimeException(e);
            }

        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDateAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Can't cancel booking");
        builder.setMessage("Unfortunately the booking cannot be cancelled as it is less than 24 hours away.");
        builder.setNegativeButton(android.R.string.ok, (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void hide() throws JSONException, IOException {
        Button cancelbtn = findViewById(R.id.cancel_booking);
        Button editbtn = findViewById(R.id.edit_booking);
        TextView mealTime = findViewById(R.id.textBox1_mealTime);
        TextView location = findViewById(R.id.textBox1_location);
        TextView tableSize = findViewById(R.id.textBox1_tableSize);
        TextView mainBox = findViewById(R.id.booking1);

        mealTime.setVisibility(View.INVISIBLE);
        location.setVisibility(View.INVISIBLE);
        tableSize.setVisibility(View.INVISIBLE);
        mainBox.setVisibility(View.INVISIBLE);
        cancelbtn.setVisibility(View.INVISIBLE);
        editbtn.setVisibility(View.INVISIBLE);

        String fileBookings = "booking.json";
        JSONObject jsonBody = new JSONObject();
        final String bookingBody = jsonBody.toString();
        Storage.writeJson(getApplicationContext(), fileBookings, bookingBody);
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