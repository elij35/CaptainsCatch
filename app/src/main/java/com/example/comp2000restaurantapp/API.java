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
import java.util.Arrays;

public class API {

    public static void apiWriteData(Context context, String bookingFilePath, String loginFilePath) throws IOException, JSONException {

        //Reading the data in the json files and inputting them into a JSON object to be sent by the API
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("customerName", readJson(loginFilePath).get("customerName").textValue());
        jsonBody.put("customerPhoneNumber", readJson(loginFilePath).get("customerPhoneNumber").textValue());
        jsonBody.put("meal", readJson(bookingFilePath).get("meal").textValue());
        jsonBody.put("seatingArea", readJson(bookingFilePath).get("seatingArea").textValue());
        jsonBody.put("tableSize", readJson(bookingFilePath).get("tableSize").textValue());
        jsonBody.put("date", readJson(bookingFilePath).get("date").textValue());
        final String requestBody = jsonBody.toString();

        SendToApi(context, requestBody);
    }

    public static void SendToApi(Context context, String body) {

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
                return body.getBytes(StandardCharsets.UTF_8);
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void apiGetData(Context context, String key, String value, String fileName) throws IOException, JSONException {

        JSONObject reset = new JSONObject();
        reset.put("key", 0);
        final String resetBody = reset.toString();
        Storage.writeJson(context, fileName, resetBody);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String site = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, site, null, response -> {
            try {
                int j = 1;
                for (int i = 0; i < response.length(); i++) {
                    JSONObject reservationJSON;
                    reservationJSON = response.getJSONObject(i);
                    String[] date = new String[0];
                    date = new String[]{reservationJSON.getString(key)};

                    if (date[0].equalsIgnoreCase(value)) {
                        Log.i("number: " + j + " id: " + i, Arrays.toString(date));
                        JSONObject jsonBody = new JSONObject();
                        jsonBody.put("key", JSONObject.numberToString(j));
                        final String requestBody = jsonBody.toString();
                        Storage.writeJson(context, fileName, requestBody);
                        j++;
                    }
                }
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }

        }, null);
        requestQueue.add(jsonArrayRequest);
    }
}