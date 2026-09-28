package com.example.project;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setLogo(R.mipmap.pinwheel);
            actionBar.setDisplayUseLogoEnabled(true); // 로고 표시 활성화
            actionBar.setDisplayShowHomeEnabled(true); // 홈 아이콘 표시 활성화
        }

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    textView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                    flag = false;
                } else {
                    textView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                    flag = true;
                }
            }
        });
    }
}