package com.example.homework;   // ← 본인 package 줄 유지

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Report2Activity extends AppCompatActivity {
    String[] grades = {"1학년", "2학년", "3학년", "4학년"};
    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);

        // ① 알림 다이얼로그
        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Report2Activity.this);
                builder.setTitle("알림");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setMessage("이번주 레포트는 Dialog 입니다");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Report2Activity.this, "확인했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
            }
        });

        // ② 입력 다이얼로그
        Button btnInput = findViewById(R.id.btnInput);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = new EditText(Report2Activity.this);   // Dialog 안에 넣을 입력칸

                AlertDialog.Builder builder = new AlertDialog.Builder(Report2Activity.this);
                builder.setTitle("이름 입력");
                builder.setView(editText);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText.getText().toString().trim();
                        if (name.isEmpty()) {
                            Toast.makeText(Report2Activity.this, "입력해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Report2Activity.this, name, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });

        // ③ 리스트 다이얼로그 (학번 짝수: 학년 단일 선택)
        Button btnList = findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = -1;   // 열 때마다 선택 초기화

                AlertDialog.Builder builder = new AlertDialog.Builder(Report2Activity.this);
                builder.setTitle("몇학년 입니까?");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setSingleChoiceItems(grades, selected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected = which;
                    }
                });
                builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selected == -1) {
                            Toast.makeText(Report2Activity.this, "학년을 선택해주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Report2Activity.this, "선택한 학년 : " + grades[selected], Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}