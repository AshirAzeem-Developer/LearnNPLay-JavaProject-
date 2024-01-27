package com.example.learnnplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView alphabetTextView;
    TextView numbersTextView;
    TextView colorsTextView;
    TextView shapesTextView;

    TextView profileSettingTextView,quizTextView;
    ImageView settingImageView;

    TextView rateTextView;

    private MyBroadCastReciever receiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapesTextView = findViewById(R.id.shapesTextView);
        colorsTextView = findViewById(R.id.colorsTextView);
        alphabetTextView = findViewById(R.id.alphabetsTextView);
        numbersTextView = findViewById(R.id.numbersTextView);
        settingImageView = findViewById(R.id.settingImageView);
        rateTextView = findViewById(R.id.rateTextView);
        quizTextView = findViewById(R.id.quizTextView);

        shapesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShapeLearning.class));
                finish();
            }
        });
        colorsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ColorLearning.class));
                finish();

            }
        });
        alphabetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AlphabetLearning.class));
                finish();
            }
        });
        numbersTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NumbersLearning.class));
                finish();
            }
        });
        settingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileSettings.class));
                finish();
            }
        });
        rateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RateMyApp.class));
                finish();
            }
        });
        quizTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Quiz.class));
                finish();
            }
        });
        receiver = new MyBroadCastReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver,filter);





    }
    private void broadcastIntent() {
        sendBroadcast(new
                Intent("com.example.broadcastreceivers.MY_CUSTOM_ACTION"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}