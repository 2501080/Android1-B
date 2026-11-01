package com.example.homework;   // ← 본인 패키지명 그대로 두기

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Report3Activity extends AppCompatActivity {
    int celsius = 0;      // 섭씨 입력값
    int fahrenheit = 0;   // 화씨 입력값

    TextView celsiusView, fahrenheitView, resultView;
    SeekBar celsiusSeekBar, fahrenheitSeekBar;
    RadioGroup radioGroup;
    RadioButton radioCtoF, radioFtoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report3);

        celsiusView = findViewById(R.id.celsiusView);
        fahrenheitView = findViewById(R.id.fahrenheitView);
        resultView = findViewById(R.id.resultView);
        celsiusSeekBar = findViewById(R.id.celsiusSeekBar);
        fahrenheitSeekBar = findViewById(R.id.fahrenheitSeekBar);
        radioGroup = findViewById(R.id.radioGroup);
        radioCtoF = findViewById(R.id.radioCtoF);
        radioFtoC = findViewById(R.id.radioFtoC);

        // 처음에는 라디오 버튼을 고르기 전이므로 둘 다 입력 불가
        celsiusSeekBar.setEnabled(false);
        fahrenheitSeekBar.setEnabled(false);

        // SeekBar 0~200 → -100 ~ +100 으로 사용
        celsiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                celsius = progress - 100;
                celsiusView.setText(String.format("섭씨온도(%.1f ℃)", (double) celsius));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        fahrenheitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fahrenheit = progress - 100;
                fahrenheitView.setText(String.format("화씨온도(%.1f ℉)", (double) fahrenheit));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 라디오 버튼을 누르면 해당 SeekBar만 입력 가능하게
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioCtoF) {
                    celsiusSeekBar.setEnabled(true);
                    fahrenheitSeekBar.setEnabled(false);
                } else if (checkedId == R.id.radioFtoC) {
                    celsiusSeekBar.setEnabled(false);
                    fahrenheitSeekBar.setEnabled(true);
                }
            }
        });

        // 변환 버튼
        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioCtoF.isChecked()) {
                    double f = celsius * 1.8 + 32;            // ℉ = (℃ × 1.8) + 32
                    resultView.setText(String.format("섭씨온도 : %.2f ℃, 화씨온도 : %.2f ℉", (double) celsius, f));
                } else if (radioFtoC.isChecked()) {
                    double c = (fahrenheit - 32) / 1.8;       // ℃ = (℉ - 32) / 1.8
                    resultView.setText(String.format("화씨온도 : %.2f ℉, 섭씨온도 : %.2f ℃", (double) fahrenheit, c));
                } else {
                    Toast.makeText(getApplicationContext(), "선택해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 초기화 버튼
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                celsiusSeekBar.setProgress(100);      // 0도로 되돌리기
                fahrenheitSeekBar.setProgress(100);
                celsiusSeekBar.setEnabled(false);
                fahrenheitSeekBar.setEnabled(false);
                resultView.setText("");
            }
        });
    }
}