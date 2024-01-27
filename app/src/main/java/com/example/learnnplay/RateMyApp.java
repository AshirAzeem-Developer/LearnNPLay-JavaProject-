package com.example.learnnplay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RateMyApp extends AppCompatActivity {

    private EditText editTextFeedback;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_my_app);

        editTextFeedback = findViewById(R.id.editTextFeedback);
        ratingBar = findViewById(R.id.ratingBar);
        TextView btnSubmitFeedback = findViewById(R.id.textView2);

        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        String feedbackText = editTextFeedback.getText().toString();
        float userRating = ratingBar.getRating();

        // You can handle the feedback and rating here
        // For example, send it to a server or save it locally

        // For demonstration purposes, displaying a toast with the feedback and rating
        String toastMsg = "Feedback: " + feedbackText + "\nRating: " + userRating;
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
