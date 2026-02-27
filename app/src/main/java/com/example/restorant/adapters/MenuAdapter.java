package com.example.restorant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restorant.R;
import com.example.restorant.models.MenuItem;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    
    private List<MenuItem> menuItems;
    
    public MenuAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(item.getPrice());
        holder.categoryTextView.setText(item.getCategory());
    }
    
    @Override
    public int getItemCount() {
        return menuItems.size();
    }
    
    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView categoryTextView;
        
        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.menuItemName);
            priceTextView = itemView.findViewById(R.id.menuItemPrice);
            categoryTextView = itemView.findViewById(R.id.menuItemCategory);
        }
    }
}
