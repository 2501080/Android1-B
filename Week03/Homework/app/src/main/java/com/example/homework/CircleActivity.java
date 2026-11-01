package com.example.homework;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CircleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_circle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editRadius = findViewById(R.id.editRadius);
        Button buttonArea = findViewById(R.id.buttonArea);
        TextView textResult = findViewById(R.id.textResult);

        buttonArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editRadius.getText().toString();
                if (str.isEmpty()) {
                    Snackbar.make(v,"입력 바람", BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
                try {
                    double radius = Double.parseDouble(str);
                    double area = 3.141592 * radius * radius;


                    String result = String.format(
                            "원의 반지름 : <font color='#FF0000'>%,.2f</font> ㎝<br>" +
                                    "원의 면적 : <font color='#FF0000'>%,.2f</font> ㎠",
                            radius, area);
                    textResult.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY));
                } catch (NumberFormatException e) {
                    Toast.makeText(getBaseContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}