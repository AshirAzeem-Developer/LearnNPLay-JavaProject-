package com.example.learnnplay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;


    TextView textView;
    Button btnLogin;
    UserProfileDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textView = findViewById(R.id.signupLink);

        dbHelper = new UserProfileDBHelper(this);

        btnLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Validate input fields
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate user credentials against database
            boolean loginSuccessful = dbHelper.checkUserCredentials(email, password);

            if (loginSuccessful) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // Proceed to the next activity or perform necessary actions for a successful login
                // For example, start the main activity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials or UserNot Found", Toast.LENGTH_SHORT).show();
            }
        });

        textView.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            finish();
        });
    }
}