/*
생성 날짜: 20200311
작성자: lionem2018@gmail.com
역할: 상태바 색상을 변경하기 위한 기본 Activity
사용방법: 상태바 색상 변경이 필요한 Activity가 있다면 AppCompatActivity 대신 해당 Class 상속하여 구현
 */

package com.haru.pawprint;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getWindow().getDecorView();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 #F2F2F2에 검은색 아이콘으로 변경
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#F2F2F2"));
            }

        }else if(Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }
}
