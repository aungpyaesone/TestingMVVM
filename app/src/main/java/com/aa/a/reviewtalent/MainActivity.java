package com.aa.a.reviewtalent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aa.a.reviewtalent.Adapter.ViewPagerAdapter;
import com.aa.a.reviewtalent.view.AcceptData;
import com.aa.a.reviewtalent.view.LottieFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity{
    public @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    public @BindView(R.id.view_pager)
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }

   /* @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 2;
        AcceptData fTwo = (AcceptData) getSupportFragmentManager().findFragmentByTag(tag);
        fTwo.displayReceiveData(message);

    }*/

}
