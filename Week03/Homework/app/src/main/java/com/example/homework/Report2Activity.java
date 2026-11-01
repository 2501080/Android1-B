package com.example.homework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Report2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editYear = findViewById(R.id.editYear);
        EditText editAge = findViewById(R.id.editAge);
        Button buttonAge = findViewById(R.id.buttonAge);
        Button buttonYear = findViewById(R.id.buttonYear);

        // 올해 년도 (예: 2026)
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);

        // 출생년도 → 나이
        buttonAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editYear.getText().toString();
                if (str.isEmpty()) {
                    Toast.makeText(getBaseContext(), "출생 년도를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int year = Integer.parseInt(str);
                if (year < 0 || year > thisYear) {           // 조건: 0 ~ 올해
                    Toast.makeText(getBaseContext(), "0 ~ " + thisYear + " 사이로 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int age = thisYear - year;
                Toast.makeText(getBaseContext(), "당신의 나이는 " + age + "세입니다.", Toast.LENGTH_SHORT).show();
            }
        });


        buttonYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editAge.getText().toString();
                if (str.isEmpty()) {
                    Toast.makeText(getBaseContext(), "나이를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int age = Integer.parseInt(str);
                if (age < 1 || age > 130) {                  // 조건: 1 ~ 130살
                    Toast.makeText(getBaseContext(), "1 ~ 130 사이로 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int year = thisYear - age;
                Toast.makeText(getBaseContext(), "당신의 태어난 해는 " + year + "입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}