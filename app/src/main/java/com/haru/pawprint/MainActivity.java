package com.haru.pawprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haru.pawprint.adapter.ScreenSlidePagerAdapter;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 mPager;  //ViewPager2로 변경 필요

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 돌아가기 버튼
        TextView btnBack = findViewById(R.id.textview_back);

        // 설정 화면 버튼
        Button btnSetting = findViewById(R.id.button_setting);
        // 펫 프로필 버튼
        Button btnProfile = findViewById(R.id.button_profile);
        // 캘린더 버튼
        Button btnCalendar = findViewById(R.id.button_calendar);
        // 타임 스탬프 버튼
        Button btnTimeStamp = findViewById(R.id.button_timestamp);

        // 펫 선택 화면으로 이동
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SelectPetActivity.class));
                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 설정 화면으로 이동
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // 펫 프로필 화면으로 이동
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), ProfileActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // 캘린더 화면으로 이동
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), CalendarActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // 타임 스탬프 화면으로 이동
        btnTimeStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), TimeStampActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager2) findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        mPager.setAdapter(pagerAdapter);
        mPager.setOffscreenPageLimit(3);

//        mPager.setPadding((int)((getResources().getDisplayMetrics().xdpi-244)), 0, (int)((getResources().getDisplayMetrics().xdpi-244)), 0);
//        mPager.setPadding(24, 0, 24, 0);

//        mPager.setPageMargin(24);
//        mPager.setPageTransformer(new MarginPageTransformer(((int)((getResources().getDisplayMetrics().xdpi-244)))));

        float pageMarginPx = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float pagerWidth = getResources().getDimensionPixelOffset(R.dimen.pagerWidth);
        float screenWidth = getResources().getDisplayMetrics().widthPixels;
        float offsetPx = screenWidth - pageMarginPx - pagerWidth;

        float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mPager.setPageTransformer((page, position) -> {
            float myOffset = position * -(2 * pageOffset + pageMargin);
            if (mPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(mPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.setTranslationX(-myOffset);
                } else {
                    page.setTranslationX(myOffset);
                }
            } else {
                page.setTranslationY(myOffset);
            }
        });
    }
}
