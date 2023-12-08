package com.example.comp2000restaurantapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class NewBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_booking);

        loadBookNow();
        loadManageBookings();
        loadAvailableTables();
        loadBottomBar();
        mealtime();
        location();
        table_size();
        date();
    }

    private void date() {
        Spinner staticSpinner = findViewById(R.id.date_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.date, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
    }

    private void mealtime() {
        Spinner staticSpinner = findViewById(R.id.meal_time_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.mealtime, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
    }

    private void location() {
        Spinner staticSpinner = findViewById(R.id.location_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.location, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
    }

    private void table_size() {
        Spinner staticSpinner = findViewById(R.id.table_size_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.table_size, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
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

    private void loadBookNow() {
        Button bookings = findViewById(R.id.book_now_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(NewBooking.this,
                    Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(NewBooking.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        bookings.setOnClickListener(view -> {

            getNotification();

            try {
                writeJson();

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            sendAPI();

            Intent intent = new Intent(NewBooking.this, Success.class);
            startActivity(intent);
        });
    }

    private void sendAPI() {
        Context context = getApplicationContext();
        String accounts = getFilesDir() + "/" + "accounts.json";
        String login = getFilesDir() + "/" + "login.json";
        Storage.sendToApi(context, accounts, login);
    }

    private void getNotification() {
        Context context = getApplicationContext();

        String title = "Successful booking";
        String body = "Your booking has been confirmed";
        Notifications.notification(context, title, body);
    }

    private void writeJson() throws JSONException {
        Context context = getApplicationContext();

        String FILE_NAME = "accounts.json";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("meal", "Lunch");
        jsonBody.put("seatingArea", "Outside");
        jsonBody.put("tableSize", 6);
        jsonBody.put("date", "2024-04-22");

        final String requestBody = jsonBody.toString();

        Storage.writeJson(context, FILE_NAME, requestBody);
    }

    private void loadManageBookings() {
        Button bookings = findViewById(R.id.manage_bookings);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(NewBooking.this, ManageBookings.class);
            startActivity(intent);
        });
    }

    private void loadAvailableTables() {
        Button bookings = findViewById(R.id.available_tables);
        bookings.setOnClickListener(view -> {
            Intent intent = new Intent(NewBooking.this, AvailableTables.class);
            startActivity(intent);
        });
    }
}