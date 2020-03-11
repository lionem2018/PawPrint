/*
생성 날짜: 20200311
작성자: lionem2018@gmail.com
역할: 로딩 화면
사용방법: SPLASH_DISPLAY_TIME 상수만큼 로딩 화면을 보여준 후 다음 Activity로 전환
 */

package com.haru.pawprint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.haru.pawprint.BaseActivity;
import com.haru.pawprint.MainActivity;
import com.haru.pawprint.R;

import androidx.annotation.Nullable;

public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // SPLASH_DISPLAY_TIME만큼 Splash Acivity 띄운 후 Activity 전환
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 다음 Acivity로 전환
                startActivity(new Intent(getApplication(), MainActivity.class));
                // Acivity 전환 효과 제거
                overridePendingTransition(0, 0);

                // 스플래시 액티비티를 스텍에서 제거
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    public void onBackPressed() {
        // 스플래시 화면 뒤로가기 기능 제거
    }
}
