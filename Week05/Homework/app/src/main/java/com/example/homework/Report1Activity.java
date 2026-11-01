package com.example.homework;   // ← 본인 package 줄 유지

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Report1Activity extends AppCompatActivity {
    EditText editGrade;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1);

        editGrade = findViewById(R.id.editGrade);

        // Report1GradeActivity가 돌려준 값을 받는 부분
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            String grade = result.getData().getStringExtra("grade");
                            editGrade.setText(grade);
                        }
                    }
                });

        // EditText를 누르면 학년 입력 화면 실행
        editGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Report1Activity.this, Report1GradeActivity.class);
                launcher.launch(intent);
            }
        });
    }
}