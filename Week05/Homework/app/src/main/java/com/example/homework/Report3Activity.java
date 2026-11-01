package com.example.homework;   // ← 본인 package 줄 유지

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Report3Activity extends AppCompatActivity {
    Handler handler = new Handler(Looper.getMainLooper());   // 일정 시간마다 진행률 올리는 용도
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report3);

        // ① Progress Dialog (Spinner)
        Button btnSpinner = findViewById(R.id.btnSpinner);
        btnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(Report3Activity.this);
                progressDialog.setIcon(android.R.drawable.ic_lock_idle_lock);
                progressDialog.setTitle("프로그래스 다이얼로그(Spinner)");
                progressDialog.setMessage("현재 진행중입니다.");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.dismiss();
                        Toast.makeText(Report3Activity.this, "취소하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.show();
            }
        });

        // ② Progress Dialog (Horizontal Bar)
        Button btnHorizontal = findViewById(R.id.btnHorizontal);
        btnHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = 0;

                ProgressDialog progressDialog = new ProgressDialog(Report3Activity.this);
                progressDialog.setIcon(android.R.drawable.ic_lock_idle_lock);
                progressDialog.setTitle("프로그래스 다이얼로그(Horizontal)");
                progressDialog.setMessage("잠시만 기다려 주세요.");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.setCancelable(false);

                // 0.05초마다 1씩 증가 → 약 5초 뒤 100%
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        progress++;
                        progressDialog.setProgress(progress);
                        if (progress < 100) {
                            handler.postDelayed(this, 50);
                        } else {
                            progressDialog.setMessage("로딩이 완료되었습니다");
                            Toast.makeText(Report3Activity.this, "로딩이 완료되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.removeCallbacks(runnable);   // 진행 멈추기
                        progressDialog.dismiss();
                        Toast.makeText(Report3Activity.this, "취소하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                progressDialog.show();
                handler.postDelayed(runnable, 50);
            }
        });

        // ③ Custom Dialog
        Button btnCustom = findViewById(R.id.btnCustom);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(Report3Activity.this);
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }
}