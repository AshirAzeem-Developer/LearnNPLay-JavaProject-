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

public class AlphabetLearning extends AppCompatActivity {
    private static final String TAG = "ColorsRecognition";

    private ImageView alphabetImage;
    private TextView alphabetNameTextView;
    private int currentAlphabetIndex = 0;
    private final int[] alphabetImages = {R.drawable.letter_a, R.drawable.letter_b, R.drawable.letter_c, R.drawable.letter_d, R.drawable.letter_e,R.drawable.letter_f};
    private final String[] alphabetNames = {"A", "B", "C", "D", "E","F"};
    private MediaPlayer mediaPlayer;

    private static final int RESTART_DELAY = 30 * 1000; // 30 seconds

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_learning);

        alphabetImage = findViewById(R.id.number_image);
        ImageButton btnPrevious = findViewById(R.id.btnPrevious);
        ImageButton btnNext = findViewById(R.id.btnNext);
        alphabetNameTextView = findViewById(R.id.alphabetButton);
        ImageView backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(view -> startActivity(new Intent(AlphabetLearning.this, MainActivity.class)));

        btnPrevious.setOnClickListener(v -> showPreviouscolor());

        btnNext.setOnClickListener(v -> showNextcolor());

        alphabetImage.setOnClickListener(v -> playcolorNameAudio());

        updatecolorImage();

        // Schedule the delayed restart
        scheduleRestart();
    }
    private void showPreviouscolor() {
        currentAlphabetIndex = (currentAlphabetIndex - 1 + alphabetImages.length) % alphabetImages.length;
        updatecolorImage();
    }

    private void showNextcolor() {
        currentAlphabetIndex = (currentAlphabetIndex + 1) % alphabetImages.length;
        updatecolorImage();
    }

    private void updatecolorImage() {
        alphabetImage.setImageResource(alphabetImages[currentAlphabetIndex]);
        alphabetNameTextView.setText(alphabetNames[currentAlphabetIndex]);
    }

    private void playcolorNameAudio() {
        @SuppressLint("DiscouragedApi") int audioResourceId = getResources().getIdentifier(
                alphabetNames[currentAlphabetIndex].toLowerCase(), "raw", getPackageName());

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
            @SuppressLint("UnsafeIntentLaunch") Intent intent = getIntent();
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





