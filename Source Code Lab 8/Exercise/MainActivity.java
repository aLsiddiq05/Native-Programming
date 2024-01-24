package com.example.exercisesp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText usernameEditText, passwordEditText;
    private AppCompatButton loginButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        // Check if the username and password are stored in Shared Preferences
        if (checkCredentials()) {
            openSuccessPage();
        }

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Check if the entered credentials are valid
            if (isValidCredentials(username, password)) {
                // Store the username and password in Shared Preferences
                saveCredentials(username, password);
                openSuccessPage();
            } else {
                Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // In a real-world scenario, you would implement a secure authentication mechanism.
        // Here, we are using a simple check for demonstration purposes.
        return "demoUser".equals(username) && "demoPassword".equals(password);
    }

    private void saveCredentials(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private boolean checkCredentials() {
        // Check if username and password are stored in Shared Preferences
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        return username != null && password != null;
    }

    private void openSuccessPage() {
        Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
        startActivity(intent);
        finish();
    }
}
