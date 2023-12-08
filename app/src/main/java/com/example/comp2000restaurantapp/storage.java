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

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendToApi(Context context, String accountsFilePath, String loginFilePath) throws RuntimeException {
        try {
            ObjectMapper accountsMap = new ObjectMapper();
            ObjectMapper loginMap = new ObjectMapper();

            InputStream accountsRead = Files.newInputStream(Paths.get(accountsFilePath));
            InputStream loginRead = Files.newInputStream(Paths.get(loginFilePath));

            JsonNode accountsContent = accountsMap.readTree(accountsRead);
            JsonNode loginContent = loginMap.readTree(loginRead);

            JSONObject jsonBody = new JSONObject();

            jsonBody.put("customerName", loginContent.get("customerName"));
            jsonBody.put("customerPhoneNumber", loginContent.get("customerPhoneNumber"));
            jsonBody.put( "meal", accountsContent.get("meal"));
            jsonBody.put( "seatingArea", accountsContent.get("seatingArea"));
            jsonBody.put("tableSize", accountsContent.get("tableSize"));
            jsonBody.put( "date", accountsContent.get("date"));
            final String requestBody = jsonBody.toString();

            API.apiSendData(context, requestBody);

            accountsRead.close();
            loginRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}