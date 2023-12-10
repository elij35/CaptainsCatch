package com.example.comp2000restaurantapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class NewBooking extends AppCompatActivity {
    String dateSelected;
    String mealtimeSelected;
    String locationSelected;
    String tableSizeSelected;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_booking);

        loadBookNow();
        loadManageBookings();
        loadAvailableTables();
        loadBottomBar();

        //Drop downs for making a booking:
        initDatePicker();
        mealtime();
        location();
        table_size();
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

    private void table_size() {
        Spinner staticSpinner = findViewById(R.id.table_size_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.table_size, android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                tableSizeSelected = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "A date must be selected", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void location() {
        Spinner staticSpinner = findViewById(R.id.location_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,
                R.array.location, android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                locationSelected = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "A date must be selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBookNow() {
        Button bookings = findViewById(R.id.book_now_btn);
        bookings.setOnClickListener(view -> {
            if (!mealtimeSelected.equals("Select mealtime") & !locationSelected.equals("Select location") & !tableSizeSelected.equals("Select table size") & dateSelected != null) {
                try {
                    if (DateNow.dateDifference(dateSelected) < 7) {
                        showDateAlert();
                    } else {
                        writeJson();
                        sendAPI();
                        sendNotification();
                        Intent intent = new Intent(this, BookingSuccess.class);
                        startActivity(intent);
                    }
                } catch (JSONException | IOException | ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                showNotSelectedAlert();
            }
        });
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

    private void sendAPI() throws JSONException, IOException {
        String booking = getFilesDir() + "/" + "booking.json";
        String login = getFilesDir() + "/" + "login.json";
        API.apiSendData(getApplicationContext(), booking, login);
    }

    private void sendNotification() throws IOException {
        String title = "Successful booking";
        String body = "Your booking has been confirmed";
        Notifications.notification(getApplicationContext(), title, body);
    }

    private void writeJson() throws JSONException, IOException {
        String fileName = "booking.json";
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("meal", mealtimeSelected);
        jsonBody.put("seatingArea", locationSelected);
        jsonBody.put("tableSize", tableSizeSelected);
        jsonBody.put("date", dateSelected);
        final String requestBody = jsonBody.toString();
        Storage.writeJson(getApplicationContext(), fileName, requestBody);
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