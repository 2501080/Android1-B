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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Report1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editId = findViewById(R.id.editId);
        EditText editPw = findViewById(R.id.editPw);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();

                if (id.isEmpty() || pw.isEmpty()) {
                    // 하나라도 비어 있으면 Toast
                    Toast.makeText(getBaseContext(), "데이터 입력 해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    // 둘 다 입력하면 아래쪽에 Snackbar
                    Snackbar.make(v, "아이디 : " + id + " 비밀번호 : " + pw,
                            BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });
    }
}