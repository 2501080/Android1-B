package com.example.homework;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PageAdapter extends RecyclerView.Adapter<PageViewHolder> {
    private Context context;
    private int[] colors = {
            Color.parseColor("#AA66CC"),  // 1 Page
            Color.parseColor("#FF4444"),  // 2 Page
            Color.parseColor("#669900"),  // 3 Page
            Color.parseColor("#FF8800"),  // 4 Page
            Color.parseColor("#33B5E5"),  // 5 Page
            Color.parseColor("#00DDFF")   // 6 Page
    };

    public PageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_item, parent, false);

        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {
        holder.layout.setBackgroundColor(colors[position]);
        holder.textView.setText((position + 1) + " Page");
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }
}
