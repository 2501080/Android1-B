package com.example.homework;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + 40, systemBars.top + 40, systemBars.right + 40, systemBars.bottom + 40);
            return insets;
        });

        // strings.xml에 만든 과일 목록 가져오기
        String[] fruits = getResources().getStringArray(R.array.fruits);

        Spinner spinner = findViewById(R.id.spinner);

        // 어댑터 : 펼쳤을 때 첫 줄("과일을 고르세요")만 빨간색으로
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity3.this,
                android.R.layout.simple_spinner_item, fruits) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    tv.setTextColor(Color.RED);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return tv;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 과일을 고르면 "딸기는 맛있다" 토스트
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) return;   // "과일을 고르세요"는 무시
                String fruit = fruits[position];
                Toast.makeText(getBaseContext(), fruit + josa(fruit) + " 맛있다",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // 받침이 있으면 "은", 없으면 "는" (예: 수박은, 딸기는)
    private String josa(String word) {
        char last = word.charAt(word.length() - 1);
        if (last >= 0xAC00 && last <= 0xD7A3 && (last - 0xAC00) % 28 != 0) {
            return "은";
        }
        return "는";
    }
}