package com.example.homework;   // ← 본인 패키지명 그대로 두기

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Report2Activity extends AppCompatActivity {
    double weight = 35.0;   // 몸무게(kg)
    double height = 90.0;   // 키(cm)
    TextView weightView, heightView, resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);

        weightView = findViewById(R.id.weightView);
        heightView = findViewById(R.id.heightView);
        resultView = findViewById(R.id.resultView);

        SeekBar weightSeekBar = findViewById(R.id.weightSeekBar);
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weight = 35.0 + progress * 0.5;          // 0.5씩 변하게
                weightView.setText(String.format("%.1f Kg", weight));
                calcBMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar heightSeekBar = findViewById(R.id.heightSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height = 90.0 + progress * 0.5;          // 0.5씩 변하게
                heightView.setText(String.format("%.1f Cm", height));
                calcBMI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // BMI = 몸무게(kg) / (키(m) × 키(m))
    public void calcBMI() {
        double h = height / 100.0;
        double bmi = weight / (h * h);

        String state;
        if (bmi < 18.5) {
            state = "저체중";
        } else if (bmi < 23) {
            state = "정상 체중";
        } else if (bmi < 25) {
            state = "과체중";
        } else {
            state = "비만";
        }
        resultView.setText(String.format("BMI : %.2f\n당신은 %s 입니다!", bmi, state));
    }
}