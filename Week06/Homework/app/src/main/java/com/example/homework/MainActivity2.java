package com.example.homework;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + 30, systemBars.top + 30, systemBars.right + 30, systemBars.bottom + 30);
            return insets;
        });

        // 나라 목록
        List<String> items = new ArrayList<>();
        items.add("신라");
        items.add("고구려");
        items.add("백제");
        items.add("발해");
        items.add("옥저");
        items.add("동예");
        items.add("부여");
        items.add("대한민국");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(MainActivity2.this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // 세로 목록
        recyclerView.setAdapter(adapter);
    }
}