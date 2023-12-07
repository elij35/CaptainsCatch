package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class NewBooking extends AppCompatActivity {

    private String url = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";

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
        String[] spinnerItems = getResources().getStringArray(R.array.mealtime);
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
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();

                Intent intent = new Intent(NewBooking.this, Success.class);
                startActivity(intent);
            }
        });

    }

    private void getData() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(NewBooking.this);
            String URL = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations/";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("customerName", "Jordan");
            jsonBody.put("customerPhoneNumber", "07777775776");
            jsonBody.put("meal", "Lunch");
            jsonBody.put("seatingArea", "Outside");
            jsonBody.put("tableSize", 6);
            jsonBody.put("date", "2024-04-22");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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