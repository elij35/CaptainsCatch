package com.example.comp2000restaurantapp;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class storage {
    public static void writeJson(Context context, String fileName, String jsonString) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJson(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = Files.newInputStream(Paths.get(fileName));
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            /*
            //For debugging
            for (JsonNode value : jsonNode) {
                Log.i("My tag here", "Value of 'key': " + value);
            }

             */
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}