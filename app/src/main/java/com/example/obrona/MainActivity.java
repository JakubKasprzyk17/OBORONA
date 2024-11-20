package com.example.obrona;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private String selectedDate = "";
    private final String LOG_TAG = "OBRONA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        EditText confirmPasswordField = findViewById(R.id.confirmPasswordField);
        CalendarView calendarView = findViewById(R.id.calendarView);
        Button registerButton = findViewById(R.id.registerButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
            }
        });

        registerButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            String confirmPassword = confirmPasswordField.getText().toString();
            Log.d(LOG_TAG, email);
            Log.d(LOG_TAG, password);
            Log.d(LOG_TAG, confirmPassword);
            Log.d(LOG_TAG, selectedDate);
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(MainActivity.this, "Brak wszystkich danych", Toast.LENGTH_LONG).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(MainActivity.this, "Inne hasła", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("dateOfBirth", selectedDate);
                startActivity(intent);
            }
        });
    }
}