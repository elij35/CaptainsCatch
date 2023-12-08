package com.example.comp2000restaurantapp;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.nio.charset.StandardCharsets;

public class API {

    public static void apiSendData(Context context, String jsonString) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations/";

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
                return jsonString.getBytes(StandardCharsets.UTF_8);
            }
        };

        requestQueue.add(stringRequest);
    }
}
