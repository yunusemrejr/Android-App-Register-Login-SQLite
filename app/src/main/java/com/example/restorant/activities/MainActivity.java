package com.example.restorant.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.restorant.R;
import com.example.restorant.database.VeriTabani;
import com.example.restorant.databinding.MainActivityBinding;
import com.example.restorant.utils.ValidationUtils;

public class MainActivity extends AppCompatActivity {
    
    private MainActivityBinding binding;
    private VeriTabani database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        database = new VeriTabani(this);
        
        setupClickListeners();
    }
    
    private void setupClickListeners() {
        // Register button
        binding.btnuyeol.setOnClickListener(v -> handleRegistration());
        
        // Login button
        binding.btngiris.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GirisYapActivity.class);
            startActivity(intent);
        });
        
        // Skip button
        binding.atla.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        });
    }
    
    private void handleRegistration() {
        String username = binding.kad.getText().toString().trim();
        String password = binding.ksifre.getText().toString();
        String confirmPassword = binding.ksifreyeniden.getText().toString();
        
        // Clear previous errors
        binding.usernameLayout.setError(null);
        binding.passwordLayout.setError(null);
        binding.confirmPasswordLayout.setError(null);
        
        // Validate inputs
        if (!ValidationUtils.isNotEmpty(username) || 
            !ValidationUtils.isNotEmpty(password) || 
            !ValidationUtils.isNotEmpty(confirmPassword)) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!ValidationUtils.isValidUsername(username)) {
            binding.usernameLayout.setError(getString(R.string.error_username_short));
            return;
        }
        
        if (!ValidationUtils.isValidPassword(password)) {
            binding.passwordLayout.setError(getString(R.string.error_password_weak));
            return;
        }
        
        if (!ValidationUtils.passwordsMatch(password, confirmPassword)) {
            binding.confirmPasswordLayout.setError(getString(R.string.error_password_mismatch));
            Toast.makeText(this, R.string.error_password_mismatch, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Check if username already exists
        if (database.kadkontrol(username)) {
            binding.usernameLayout.setError(getString(R.string.error_username_exists));
            Toast.makeText(this, R.string.error_username_exists, Toast.LENGTH_LONG).show();
            return;
        }
        
        // Register user
        boolean success = database.veriyapistir(username, password);
        
        if (success) {
            Toast.makeText(this, R.string.success_registration, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, R.string.error_registration_failed, Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
