package com.aa.a.reviewtalent.Adapter;

import com.aa.a.reviewtalent.view.AcceptData;
import com.aa.a.reviewtalent.view.FragmentThree;
import com.aa.a.reviewtalent.view.LottieFragment;
import com.aa.a.reviewtalent.view.ViewModelExample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return ViewModelExample.newInstance();
            case 1:
                return LottieFragment.newInstance();
            case 2:
                return AcceptData.newInstance();
            case 3:
                return FragmentThree.newInstance();

            default :
                return ViewModelExample.newInstance();

        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "MVVM";
            case 1:
                return "LOTT";

            case 2:
                return "DATA";

            case 3:
                return "REC";

            default :
                return "MVVM";
        }

    }
}
