package com.example.homework;   // ← 본인 패키지명 그대로 두기

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Report1Activity extends AppCompatActivity {
    int num1 = 1;
    int num2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView resultView = findViewById(R.id.resultView);

        // SeekBar는 0~99 → +1 해서 1~100으로 사용
        SeekBar seekBar1 = findViewById(R.id.seekbar1);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num1 = progress + 1;
                textView1.setText("자연수 입력(" + num1 + ")");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar seekBar2 = findViewById(R.id.seekbar2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num2 = progress + 1;
                textView2.setText("자연수 입력(" + num2 + ")");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아무것도 움직이지 않았으면 (둘 다 1) 토스트 표시
                if (num1 == 1 && num2 == 1) {
                    Toast.makeText(getApplicationContext(), "입력 바람", Toast.LENGTH_SHORT).show();
                    return;
                }
                int gcd = getGCD(num1, num2);
                int lcm = num1 * num2 / gcd;   // 최소공배수 = 두 수의 곱 / 최대공약수

                resultView.setText(num1 + ", " + num2 + "의 GCD(최대공약수)는 " + gcd + "입니다\n"
                        + num1 + ", " + num2 + "의 LCM(최소공배수)는 " + lcm + " 입니다.");
            }
        });
    }

    // 유클리드 호제법으로 최대공약수 구하기
    public int getGCD(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}