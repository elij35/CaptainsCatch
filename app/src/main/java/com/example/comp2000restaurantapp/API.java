package com.example.comp2000restaurantapp;

import static com.example.comp2000restaurantapp.Storage.readJson;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class API {

    public static void apiSendData(Context context, String bookingFilePath, String loginFilePath) throws IOException, JSONException {

        //Reading the data in the json files and inputting them into a JSON object to be sent by the API
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("customerName", readJson(loginFilePath).get("customerName").textValue());
        jsonBody.put("customerPhoneNumber", readJson(loginFilePath).get("customerPhoneNumber").textValue());
        jsonBody.put("meal", readJson(bookingFilePath).get("meal").textValue());
        jsonBody.put("seatingArea", readJson(bookingFilePath).get("seatingArea").textValue());
        jsonBody.put("tableSize", readJson(bookingFilePath).get("tableSize").textValue());
        jsonBody.put("date", readJson(bookingFilePath).get("date").textValue());
        final String requestBody = jsonBody.toString();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> Log.i("VOLLEY", response),
                error -> Log.e("VOLLEY", error.toString())) {
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
    }

    public static void apiGetData(Context context, String filename) throws IOException, JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String site = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, site, null, response -> {
            JSONObject jsonBody = new JSONObject();

            try {
                jsonBody.put("Data", response);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            final String bookingBody = jsonBody.toString();
            try {
                Storage.writeJson(context, filename, bookingBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, null);

        requestQueue.add(jsonArrayRequest);
    }
}