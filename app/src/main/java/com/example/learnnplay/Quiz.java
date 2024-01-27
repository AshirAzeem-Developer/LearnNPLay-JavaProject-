package com.example.learnnplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Quiz extends AppCompatActivity {

    private ImageView questionImageView;
    private TextView timerTextView;
    private RadioGroup radioGroup;
    private Button restartButton;
    private TextView scoreTextView;

    /** @noinspection MismatchedReadAndWriteOfArray*/
    private final String[] questions = {"question1", "question2", "question3", "question4", "question5"};
    private final int[] questionImages = {R.drawable.hexagon_image, R.drawable.pink_color, R.drawable.number_1, R.drawable.letter_b, R.drawable.triangle_image};
    private final String[] correctAnswers = {"Hexagon", "Pink Color", "1", "Bear", "Triangle"};
    private final String[] options={"cat","none"};
    private int currentQuestionIndex = 0;
    private int score = 0;

    private CountDownTimer timer;
    private long timeLeftInMillis = 20000; // 20 seconds

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionImageView = findViewById(R.id.questionImageView);
        timerTextView = findViewById(R.id.timerTextView);
        radioGroup = findViewById(R.id.radioGroup);
        Button submitButton = findViewById(R.id.submitBtn);
        restartButton = findViewById(R.id.restartBtn);
        scoreTextView = findViewById(R.id.scoreTextView);
        Button backButton = findViewById(R.id.backbutton);


        backButton.setOnClickListener(view -> startActivity(new Intent(Quiz.this, MainActivity.class)));
        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.quiz);

        // Start playing audio
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

        displayQuestion();

        submitButton.setOnClickListener(view -> checkAnswer());

        restartButton.setOnClickListener(view -> restartQuiz());

        startTimer();
    }

    @SuppressLint("SetTextI18n")
    private void displayQuestion() {
        questionImageView.setImageResource(questionImages[currentQuestionIndex]);
        RadioButton option1 = findViewById(R.id.option1);
        RadioButton option2 = findViewById(R.id.option2);
        RadioButton option3 = findViewById(R.id.option3);

        option1.setText("A. " + options[0]);
        option2.setText("B. " + correctAnswers[currentQuestionIndex]);
        option3.setText("C. " + options[1]);
    }

    private void checkAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString().substring(3); // Remove the prefix "A. " or "B. " or "C. "
            String correctAnswer = correctAnswers[currentQuestionIndex];

            if (selectedAnswer.equals(correctAnswer)) {
                score++;
                updateScore();
            }

            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                displayQuestion();
                radioGroup.clearCheck();
            } else {
                showResults();
            }
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateScore() {
        scoreTextView.setText("Score: " + score);
    }

    private void showResults() {
        stopTimer();
        mediaPlayer.release(); // Release MediaPlayer resources
        mediaPlayer = null;

        String resultMessage = "Your score is: " + score + " out of " + questions.length;
        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();

        // Show restart button
        restartButton.setVisibility(View.VISIBLE);
    }

    private void restartQuiz() {
        // Reset quiz state
        currentQuestionIndex = 0;
        score = 0;
        timeLeftInMillis = 15000;

        // Hide restart button
        restartButton.setVisibility(View.GONE);

        // Start playing audio
        mediaPlayer = MediaPlayer.create(this, R.raw.quiz);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

        // Restart timer, update score, and display the first question
        startTimer();
        updateScore();
        displayQuestion();
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                showResults();
            }
        }.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerTextView.setText("Timer: " + seconds + "s");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
}
}