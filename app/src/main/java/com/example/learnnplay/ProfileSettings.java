package com.example.learnnplay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileSettings extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextPassword;

    TextView buttonSave,logoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSave = findViewById(R.id.buttonSave);
        logoutView = findViewById(R.id.logoutView);
        // Implement button click functionality
        buttonSave.setOnClickListener(view -> saveChanges());


        logoutView.setOnClickListener(v -> {
            startActivity(new Intent(ProfileSettings.this,LoginActivity.class));
            finish();
        });
    }



    private void saveChanges() {
        // Fetch the entered values
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Perform save operation or validation as needed
        // For example, you can save these details to a database or perform validation checks
        // Replace this with your implementation based on what you want to do with the user input
        // For demonstration purposes, let's log the input values
        logUserData(name, email, password);
    }

    private void logUserData(String name, String email, String password) {
        // Log the user data (Replace this with your logic)
        // For example, you might want to save this data to a database or perform some other action
        // For now, let's just log this information
        // You can replace this with your actual implementation
        // Log the user data
        // For demo, you can display this in Logcat or use Toast to show this information
        // Example: Log.d("UserData", userData);
    }
}
