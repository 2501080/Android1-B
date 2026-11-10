package com.example.homework;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) { // 0 ~ 3
            case 0 :
                fragment = new HomeFragment();
                break;
            case 1 :
                fragment = new ChattingFragment();
                break;
            case 2 :
                fragment = new NewsFragment();
                break;
            case 3 :
                fragment = new SettingFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
