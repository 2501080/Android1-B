package com.example.homework;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Report3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CheckBox checkRead = findViewById(R.id.checkRead);
        CheckBox checkTravel = findViewById(R.id.checkTravel);
        CheckBox checkGame = findViewById(R.id.checkGame);
        Button buttonSelect = findViewById(R.id.buttonSelect);
        TextView textResult = findViewById(R.id.textResult);

        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();   // 체크된 취미를 담을 목록
                if (checkRead.isChecked())   list.add("독서");
                if (checkTravel.isChecked()) list.add("여행");
                if (checkGame.isChecked())   list.add("게임");

                if (list.isEmpty()) {
                    textResult.setText("");
                    Toast.makeText(getBaseContext(), "취미를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // ["독서","게임"] → "독서, 게임"
                    textResult.setText("선택한 취미 : " + TextUtils.join(", ", list));
                }
            }
        });
    }
}