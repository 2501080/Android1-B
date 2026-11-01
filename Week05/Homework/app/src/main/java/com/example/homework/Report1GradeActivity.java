package com.example.homework;   // ← 본인 package 줄 유지

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Report1GradeActivity extends AppCompatActivity {
    String[] grades = {"1학년", "2학년", "3학년", "4학년"};
    int selected = -1;   // -1 = 아직 선택 안 함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1_grade);

        showGradeDialog();   // 화면이 열리자마자 Dialog 띄우기
    }

    void showGradeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Report1GradeActivity.this);
        builder.setTitle("몇학년 입니까?");
        builder.setIcon(R.mipmap.ic_launcher);

        // 라디오버튼(하나만 선택)
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
                    Toast.makeText(Report1GradeActivity.this, "학년을 선택해주세요", Toast.LENGTH_SHORT).show();
                    showGradeDialog();   // 선택 안 했으면 다시 띄움
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("grade", grades[selected]);
                    setResult(RESULT_OK, intent);   // Report1Activity로 값 돌려주기
                    finish();
                }
            }
        });

        builder.setCancelable(false);   // 바깥 터치로 안 닫히게
        builder.create();
        builder.show();
    }
}