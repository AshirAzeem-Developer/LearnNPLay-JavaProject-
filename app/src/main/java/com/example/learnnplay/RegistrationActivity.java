package com.example.learnnplay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextName, editTextAge, editTextEmail,editTextPass;
    UserProfileDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize EditText fields and Database Helper
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPass);
        dbHelper = new UserProfileDBHelper(this);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            // Retrieve input data from EditText fields
            String name = editTextName.getText().toString().trim();
            String age = editTextAge.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPass.getText().toString().trim();

            // Validate input fields
            if (name.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate age as a number
            if (!age.matches("\\d+")) {
                Toast.makeText(RegistrationActivity.this, "Enter a valid age", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert user profile using Database Helper
            boolean success = dbHelper.insertUserProfile(name, age, email,password);

            if (success) {
                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(RegistrationActivity.this, "Error saving profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}