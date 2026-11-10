package com.example.homework;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private LinearLayout first;
    private LinearLayout second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 안드로이드 16 이상에서는 화면이 상단바(ActionBar) 밑으로 들어가므로
        // 버튼과 탭이 가려지지 않도록 상단바 아래로 내려준다
        View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        root.post(new Runnable() {
            @Override
            public void run() {
                View actionBar = findViewById(androidx.appcompat.R.id.action_bar_container);
                if (actionBar == null) return;
                int[] rootPos = new int[2];
                int[] barPos = new int[2];
                root.getLocationInWindow(rootPos);
                actionBar.getLocationInWindow(barPos);
                int top = barPos[1] + actionBar.getHeight() - rootPos[1];
                if (top > 0) {
                    root.setPadding(0, top, 0, 0);
                }
            }
        });

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);

        // 첫번째 화면 : ViewPager2 슬라이드 (가로 <-> 세로)
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        PageAdapter pageAdapter = new PageAdapter(MainActivity.this);
        viewPager2.setAdapter(pageAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        Button btnToggle = findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                    btnToggle.setText("세로로 슬라이드");
                } else {
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                    btnToggle.setText("가로로 슬라이드");
                }
            }
        });

        // 두번째 화면 : TabLayout + ViewPager2
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setBackgroundColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);           // 선택 안됨 : 회색, 선택 : 검정
        tabLayout.setTabIconTint(ColorStateList.valueOf(Color.BLACK));  // 아이콘 검정
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#008577"));
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabPagerAdapter tabAdapter = new TabPagerAdapter(MainActivity.this);
        viewPager.setAdapter(tabAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                switch (i) {
                    case 0 :
                        tab.setText("HOME");
                        tab.setIcon(R.drawable.ic_home);
                        break;
                    case 1 :
                        tab.setText("CHATTING");
                        tab.setIcon(R.drawable.ic_chat);
                        break;
                    case 2 :
                        tab.setText("NEWS");
                        tab.setIcon(R.drawable.ic_news);
                        break;
                    case 3 :
                        tab.setText("SETTING");
                        tab.setIcon(R.drawable.ic_setting);
                        break;
                }
            }
        });
        mediator.attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.newSlide) {
            if (item.isChecked()) {
                item.setChecked(false);
                first.setVisibility(View.VISIBLE);
                second.setVisibility(View.INVISIBLE);
            } else {
                item.setChecked(true);
                first.setVisibility(View.INVISIBLE);
                second.setVisibility(View.VISIBLE);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
