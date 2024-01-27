package com.example.learnnplay;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShapeLearning extends AppCompatActivity {
    private static final String TAG = "ShapeRecognition";

    private ImageView shape_Image;
    private TextView shapeNameTextView;
    private int currentShapeIndex = 0;
    private final int[] shapeImages = {R.drawable.circle_image, R.drawable.square_image, R.drawable.rectangle_image, R.drawable.hexagon_image, R.drawable.triangle_image};
    private final String[] shapeNames = {"Circle", "Square", "Rectangle", "Hexagon", "Triangle"};
    private MediaPlayer mediaPlayer;

    private static final int RESTART_DELAY = 30 * 1000; // 30 seconds

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_learning);

        shape_Image = findViewById(R.id.shape_Image);
        ImageButton btnPrevious = findViewById(R.id.btnPrevious);
        ImageButton btnNext = findViewById(R.id.btnNext);
        shapeNameTextView = findViewById(R.id.alphabetButton);
        ImageView backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(view -> startActivity(new Intent(ShapeLearning.this, MainActivity.class)));

        btnPrevious.setOnClickListener(v -> showPreviousShape());

        btnNext.setOnClickListener(v -> showNextShape());

        shape_Image.setOnClickListener(v -> playShapeNameAudio());

        updateShapeImage();

        // Schedule the delayed restart
        scheduleRestart();
    }
    private void showPreviousShape() {
        currentShapeIndex = (currentShapeIndex - 1 + shapeImages.length) % shapeImages.length;
        updateShapeImage();
    }

    private void showNextShape() {
        currentShapeIndex = (currentShapeIndex + 1) % shapeImages.length;
        updateShapeImage();
    }

    private void updateShapeImage() {
        shape_Image.setImageResource(shapeImages[currentShapeIndex]);
        shapeNameTextView.setText(shapeNames[currentShapeIndex]);
    }

    private void playShapeNameAudio() {
        @SuppressLint("DiscouragedApi") int audioResourceId = getResources().getIdentifier(
                shapeNames[currentShapeIndex].toLowerCase(), "raw", getPackageName());

        Log.d(TAG, "Audio Resource ID: " + audioResourceId);

        if (audioResourceId != 0) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, audioResourceId);

            if (mediaPlayer != null) {
                mediaPlayer.start();
            } else {
                Log.e(TAG, "Error creating MediaPlayer");
            }
        } else {
            Log.e(TAG, "Audio resource not found");
        }
    }

    private void scheduleRestart() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Restart the activity
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }, RESTART_DELAY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}





