package com.example.homework;   // ← 본인 package 줄 유지

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    private Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   // 기본 제목줄 없애기
        setContentView(R.layout.custom_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText editId = findViewById(R.id.editId);
        EditText editPwd = findViewById(R.id.editPwd);

        // LOGIN 버튼
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString().trim();
                String pwd = editPwd.getText().toString().trim();
                if (id.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(context, "정확하게 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, id + " / " + pwd, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 취소 버튼
        Button btnDialogCancel = findViewById(R.id.btnDialogCancel);
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "취소하였습니다", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        // 확인 버튼 : 입력이 다 되어 있으면 결과 보여주고 닫기
        Button btnDialogOk = findViewById(R.id.btnDialogOk);
        btnDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString().trim();
                String pwd = editPwd.getText().toString().trim();
                if (id.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(context, "정확하게 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, id + " / " + pwd, Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
    }
}