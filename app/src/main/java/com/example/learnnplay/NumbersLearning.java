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

public class NumbersLearning extends AppCompatActivity {
    private static final String TAG = "NumbersLearning";

    private ImageView numberImage;
    private TextView colorNameTextView;
    private int currentColorIndex = 0;
    private final int[] colorImages = {R.drawable.number_1, R.drawable.number_2, R.drawable.number_3, R.drawable.number_4};
    private final String[] colorNames = {"One", "Two", "Three", "Four"};
    private MediaPlayer mediaPlayer;

    private static final int RESTART_DELAY = 30 * 1000; // 30 seconds

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_learning);

        numberImage = findViewById(R.id.number_image);
        ImageButton btnPrevious = findViewById(R.id.btnPrevious);
        ImageButton btnNext = findViewById(R.id.btnNext);
        colorNameTextView = findViewById(R.id.alphabetButton);
        ImageView backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(view -> startActivity(new Intent(NumbersLearning.this, MainActivity.class)));

        btnPrevious.setOnClickListener(v -> showPreviouscolor());

        btnNext.setOnClickListener(v -> showNextcolor());

        numberImage.setOnClickListener(v -> playcolorNameAudio());

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
        numberImage.setImageResource(colorImages[currentColorIndex]);
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





