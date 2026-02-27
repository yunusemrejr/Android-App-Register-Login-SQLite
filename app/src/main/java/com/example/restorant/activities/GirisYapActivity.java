package com.example.restorant.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.restorant.R;
import com.example.restorant.database.VeriTabani;
import com.example.restorant.databinding.GirisActivityBinding;
import com.example.restorant.utils.ValidationUtils;

public class GirisYapActivity extends AppCompatActivity {
    
    private GirisActivityBinding binding;
    private VeriTabani database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GirisActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        database = new VeriTabani(this);
        
        setupClickListeners();
    }
    
    private void setupClickListeners() {
        binding.girisbtn.setOnClickListener(v -> handleLogin());
    }
    
    private void handleLogin() {
        String username = binding.kullaniciadi.getText().toString().trim();
        String password = binding.ksifresi.getText().toString();
        
        // Clear previous errors
        binding.usernameLayout.setError(null);
        binding.passwordLayout.setError(null);
        
        // Validate inputs
        if (!ValidationUtils.isNotEmpty(username) || !ValidationUtils.isNotEmpty(password)) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Verify credentials
        boolean isValid = database.kadveksifrekontrol(username, password);
        
        if (isValid) {
            Toast.makeText(this, R.string.success_login, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(GirisYapActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        } else {
            binding.passwordLayout.setError(getString(R.string.error_invalid_credentials));
            Toast.makeText(this, R.string.error_invalid_credentials, Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
