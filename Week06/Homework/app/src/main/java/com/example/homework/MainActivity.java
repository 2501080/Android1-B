package com.example.homework;   // ← 내 프로젝트의 package 줄 그대로 두기

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;          // 회원 이름 목록
    ArrayAdapter<String> adapter;     // ListView와 목록을 연결해 주는 어댑터
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + 30, systemBars.top + 30, systemBars.right + 30, systemBars.bottom + 30);
            return insets;
        });

        // 1. 처음에 들어갈 회원 3명
        items = new ArrayList<>();
        items.add("홍길동");
        items.add("이대한");
        items.add("한민국");

        // 2. ListView에 어댑터 연결 (체크박스가 있는 기본 모양 사용)
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // 3. 항목을 체크하면 "선택 : 이름" 토스트 띄우기
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (listView.isItemChecked(position)) {
                Toast.makeText(getBaseContext(), "선택 : " + items.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        EditText editText = findViewById(R.id.editText);
        Button btnInput = findViewById(R.id.btnInput);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnResult = findViewById(R.id.btnResult);

        // 4. [입력] 버튼 : 이름을 목록에 추가
        btnInput.setOnClickListener(v -> {
            String name = editText.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(getBaseContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                items.add(name);
                adapter.notifyDataSetChanged();   // 목록 새로고침
                editText.setText("");
            }
        });

        // 5. [삭제] 버튼 : 체크한 회원 삭제
        btnDelete.setOnClickListener(v -> deleteChecked());

        // 6. [결과보기] 버튼 : 체크한 회원을 Dialog로 보여주기
        btnResult.setOnClickListener(v -> showResultDialog());
    }

    // 체크된 항목들을 지우는 메서드
    private void deleteChecked() {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        int count = 0;
        // 뒤에서부터 지워야 번호가 꼬이지 않음
        for (int i = items.size() - 1; i >= 0; i--) {
            if (checked.get(i)) {
                items.remove(i);
                count++;
            }
        }
        listView.clearChoices();          // 체크 표시 초기화
        adapter.notifyDataSetChanged();

        if (count == 0) {
            Toast.makeText(getBaseContext(), "삭제할 회원을 선택하세요", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), count + "개 삭제하였습니다", Toast.LENGTH_SHORT).show();
        }
    }

    // 체크된 항목들을 Dialog에 보여주는 메서드
    private void showResultDialog() {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (checked.get(i)) {
                sb.append(items.get(i)).append("\n");
            }
        }

        if (sb.length() == 0) {
            Toast.makeText(getBaseContext(), "선택한 회원이 없습니다", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("선택한 회원 목록")
                .setMessage(sb.toString().trim())
                .setNegativeButton("삭제", (dialog, which) -> deleteChecked())
                .setPositiveButton("확인", null)
                .show();
    }
}