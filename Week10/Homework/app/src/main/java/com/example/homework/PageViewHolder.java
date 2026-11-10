package com.example.homework;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PageViewHolder extends RecyclerView.ViewHolder {
    LinearLayout layout;
    TextView textView;

    public PageViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.pageLayout);
        textView = itemView.findViewById(R.id.pageText);
    }
}
