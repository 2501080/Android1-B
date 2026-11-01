package com.example.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context context;
    private List<String> items;

    public RecyclerAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    // 한 줄의 화면(recycleritem.xml)을 만들어 줌
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleritem, parent, false);
        return new RecyclerViewHolder(view);
    }

    // 각 줄에 나라 이름을 넣고, 클릭하면 토스트 띄우기
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String name = items.get(position);
        holder.textView.setText(name);

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, name + "에 대하여 알아보자", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}