package com.example.homework;   // ← 본인 package 줄 유지

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Report2EvenHobbyActivity extends AppCompatActivity {
    String[] hobbies = {"독서", "등산", "수영", "수집", "영화"};
    boolean[] checked = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2_even_hobby);

        Button btnHobbyDialog = findViewById(R.id.btnHobbyDialog);
        btnHobbyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHobbyDialog();
            }
        });

        // Back 버튼 막기 : 누르면 Toast만 나오고 뒤로 가지 않음
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(Report2EvenHobbyActivity.this, "Back Button을 눌렀다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void showHobbyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Report2EvenHobbyActivity.this);
        builder.setTitle("당신의 취미는?");
        builder.setIcon(R.mipmap.ic_launcher);

        // 체크박스(여러 개 선택)
        builder.setMultiChoiceItems(hobbies, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checked[which] = isChecked;
            }
        });

        builder.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = "";
                for (int i = 0; i < hobbies.length; i++) {
                    if (checked[i]) {
                        if (!result.isEmpty()) result += ", ";
                        result += hobbies[i];
                    }
                }

                if (result.isEmpty()) {
                    Toast.makeText(Report2EvenHobbyActivity.this, "취미를 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("hobby", result);
                    setResult(RESULT_OK, intent);   // Report2EvenActivity로 값 돌려주기
                    finish();
                }
            }
        });
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;  // 애니메이션 적용
        dialog.show();
    }
}