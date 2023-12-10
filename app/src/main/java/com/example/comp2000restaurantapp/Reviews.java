package com.example.comp2000restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.MessageFormat;

public class Reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);
        loadBottomBar();
        showSubmittedReview();
    }

    private void showSubmittedReview() {
        Button postReview = findViewById(R.id.submit_review_btn);
        TextView review_input = findViewById(R.id.review_input_box);
        RatingBar stars = findViewById(R.id.ratingBar);
        TextView person = findViewById(R.id.showReviewer);
        TextView message = findViewById(R.id.showReviewMessage);

        postReview.setOnClickListener(view -> {
            Float ratingNumber = stars.getRating(); // get rating number from a rating bar

            if (ratingNumber != 0 & review_input.getText().toString().length() != 0) {
                try {
                    String loginFile = getFilesDir() + "/" + "login.json";
                    String reviewerName = Storage.readJson(loginFile).get("customerName").textValue();

                    person.setText(MessageFormat.format("{0} {1} {2} {3}", reviewerName, ",", ratingNumber, "stars:"));
                    message.setText(MessageFormat.format("{0}", review_input.getText()));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(getApplicationContext(), "You must select a rating and type a review!", Toast.LENGTH_SHORT).show();
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