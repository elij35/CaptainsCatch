package com.example.comp2000restaurantapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class AvailableTables extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    String dateSelected;
    String mealtimeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_tables);

        hide();
        loadBackToBookings();
        loadBottomBar();
        initDatePicker();
        mealtime();

        try {
            showAvailable();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            loadApiCheck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void hide() {
        Button loadAvailableTables = findViewById(R.id.showAvailable);
        loadAvailableTables.setVisibility(View.INVISIBLE);
    }

    private void unhide() {
        Button loadAvailableTables = findViewById(R.id.showAvailable);
        loadAvailableTables.setVisibility(View.VISIBLE);
    }

    private void loadApiCheck() throws IOException {
        Button bookings = findViewById(R.id.check_available);
        bookings.setOnClickListener(view -> {

            String dateFile = "date.json";

            if (!mealtimeSelected.equals("Select table size") & dateSelected != null) {
                try {
                    if (DateNow.dateDifference(dateSelected) < 20) {
                        showDateAlert();
                    } else {
                        API.apiGetData(getApplicationContext(), "date", dateSelected, dateFile);
                        unhide();
                        sendNotification();
                    }
                } catch (ParseException | IOException | JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                showNotSelectedAlert();
            }
        });
    }

    private void sendNotification() throws IOException {
        String title = "Availability";
        String body = "We are now checking the availability";
        Notifications.notification(getApplicationContext(), title, body);
    }

    private void showAvailable() throws IOException, InterruptedException {
        Button bookings = findViewById(R.id.showAvailable);
        bookings.setOnClickListener(view -> {
            String availableFile = getFilesDir() + "/" + "date.json";

            try {
                if (Storage.readJson(availableFile).get("key").asInt() < 7) {
                    Log.i("Yes string output: ", String.valueOf(Storage.readJson(availableFile).get("key")));
                    AvailableTrueAlert();
                } else {
                    Log.i("No string output: ", String.valueOf(Storage.readJson(availableFile).get("key")));
                    AvailableFalseAlert();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void AvailableTrueAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Tables are available!");
        builder.setMessage("Book your table now on the bookings page!");
        builder.setNegativeButton(android.R.string.ok, (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void AvailableFalseAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("No tables are available");
        builder.setMessage("Unfortunately the date you selected is fully booked.");
        builder.setNegativeButton(android.R.string.ok, (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDateAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Date is too soon");
        builder.setMessage("Booking date must be at least a week in advance!");
        builder.setNegativeButton(android.R.string.ok, (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showNotSelectedAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("All inputs must be selected");
        builder.setMessage("You must a date, mealtime, location and table size!");
        builder.setNegativeButton(android.R.string.ok, (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initDatePicker() {
        dateButton = findViewById(R.id.datePickerButton);
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = DateNow.makeDateString(day, month, year);
            dateButton.setText(date);
            dateSelected = DateNow.makeJsonDateString(year, month, day);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private void mealtime() {
        Spinner staticSpinner = findViewById(R.id.meal_time_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.mealtime, android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                mealtimeSelected = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "A date must be selected", Toast.LENGTH_SHORT).show();
            }
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