package com.example.comp2000restaurantapp;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    public static void writeJson(Context context, String fileName, String jsonString) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOutputStream.write(jsonString.getBytes());
        fileOutputStream.close();
    }

    public static JsonNode readJson(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = Files.newInputStream(Paths.get(fileName));
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        inputStream.close();
        return jsonNode;
    }
}