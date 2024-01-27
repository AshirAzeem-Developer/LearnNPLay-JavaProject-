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

public class ColorLearning extends AppCompatActivity {
    private static final String TAG = "ColorsRecognition";

    private ImageView colorImage;
    private TextView colorNameTextView;
    private int currentColorIndex = 0;
    private final int[] colorImages = {R.drawable.blue_color, R.drawable.red_color, R.drawable.orange_color, R.drawable.pink_color, R.drawable.green_color};
    private final String[] colorNames = {"Blue", "red", "Orange", "Pink", "Green"};
    private MediaPlayer mediaPlayer;

    private static final int RESTART_DELAY = 30 * 1000; // 30 seconds

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_learning);

        colorImage = findViewById(R.id.color_image);
        ImageButton btnPrevious = findViewById(R.id.btnPrevious);
        ImageButton btnNext = findViewById(R.id.btnNext);
        colorNameTextView = findViewById(R.id.alphabetButton);
        ImageView backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(view -> startActivity(new Intent(ColorLearning.this, MainActivity.class)));

        btnPrevious.setOnClickListener(v -> showPreviouscolor());

        btnNext.setOnClickListener(v -> showNextcolor());

        colorImage.setOnClickListener(v -> playcolorNameAudio());

        updatecolorImage();

        // Schedule the delayed restart
        scheduleRestart();
    }
    private void showPreviouscolor() {
        currentColorIndex = (currentColorIndex - 1 + colorImages.length) % colorImages.length;
        updatecolorImage();
    }

    private void showNextcolor() {
        currentColorIndex = (currentColorIndex + 1) % colorImages.length;
        updatecolorImage();
    }

    private void updatecolorImage() {
        colorImage.setImageResource(colorImages[currentColorIndex]);
        colorNameTextView.setText(colorNames[currentColorIndex]);
    }

    private void playcolorNameAudio() {
        @SuppressLint("DiscouragedApi") int audioResourceId = getResources().getIdentifier(
                colorNames[currentColorIndex].toLowerCase(), "raw", getPackageName());

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





