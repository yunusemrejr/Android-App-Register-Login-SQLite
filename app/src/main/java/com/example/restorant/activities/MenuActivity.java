package com.example.restorant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import com.example.restorant.adapters.MenuAdapter;
import com.example.restorant.databinding.MenuActivityBinding;
import com.example.restorant.models.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    
    private MenuActivityBinding binding;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MenuActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setupMenuItems();
        setupRecyclerView();
        setupClickListeners();
    }
    
    private void setupMenuItems() {
        menuItems = new ArrayList<>();
        
        // Main Courses
        menuItems.add(new MenuItem("Tavuk Sote", "30 TL", "Main Course"));
        menuItems.add(new MenuItem("Etli Yemek", "50 TL", "Main Course"));
        
        // Soups
        menuItems.add(new MenuItem("Çorba", "20 TL", "Soup"));
        
        // Vegetables
        menuItems.add(new MenuItem("Ispanak", "20 TL", "Vegetable"));
        menuItems.add(new MenuItem("Kereviz", "40 TL", "Vegetable"));
        menuItems.add(new MenuItem("Brokoli", "10 TL", "Vegetable"));
    }
    
    private void setupRecyclerView() {
        menuAdapter = new MenuAdapter(menuItems);
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.menuRecyclerView.setAdapter(menuAdapter);
    }
    
    private void setupClickListeners() {
        binding.logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
