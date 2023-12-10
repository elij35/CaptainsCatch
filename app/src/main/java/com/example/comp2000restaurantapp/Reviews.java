package com.example.comp2000restaurantapp;

import static com.example.comp2000restaurantapp.Storage.readJson;

import android.content.Intent;
import android.graphics.fonts.FontStyle;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;

public class Reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);
        loadBottomBar();
        showSubmittedReview();
    }

    private void showSubmittedReview() {
        String login = getFilesDir() + "/" + "login.json";
        Button postReview = findViewById(R.id.submit_review_btn);

        postReview.setOnClickListener(view -> {
            TextView review = findViewById(R.id.review_input_box);
            RatingBar stars = findViewById(R.id.ratingBar);
            TextView person = findViewById(R.id.showReviewer);
            TextView message = findViewById(R.id.showReviewMessage);

            Float rating = stars.getRating();

            try {
                String reviewerName = readJson(login).get("customerName").textValue();
                person.setText(MessageFormat.format("{0} {1} {2} {3}", reviewerName, ",", rating, "stars:"));
                message.setText(MessageFormat.format("{0} {1}", "Says:", review.getText()));

            } catch (IOException e) {
                throw new RuntimeException(e);
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
}