package com.haru.pawprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        // 설정 화면 버튼
        Button btnSetting = findViewById(R.id.button_setting);
        Button btnProfile = findViewById(R.id.button_profile);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), EditProfileActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager2) findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
//        mPager.setAdapter(pagerAdapter);
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
//
//                page.setTranslationX(position * -offsetPx);
        });
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
//    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//        public ScreenSlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return new CardViewFragment();
//        }
//
//        @Override
//        public int getCount() {
//            return NUM_PAGES;
//        }
//    }

//    // An equivalent ViewPager2 adapter class
//    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
//        public ScreenSlidePagerAdapter(FragmentActivity fa) {
//            super(fa);
//        }
//
//        @Override
//        public Fragment createFragment(int position) {
//            return new CardViewFragment();
//        }
//
//        @Override
//        public int getItemCount() {
//            return NUM_PAGES;
//        }
//    }
}
