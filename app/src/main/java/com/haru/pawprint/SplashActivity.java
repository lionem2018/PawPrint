/*
생성 날짜: 20200311
작성자: lionem2018@gmail.com
역할: 로딩 화면
사용방법: SPLASH_DISPLAY_TIME 상수만큼 로딩 화면을 보여준 후 다음 Activity로 전환
 */

package com.haru.pawprint;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_TIME = 1000;
    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // SPLASH_DISPLAY_TIME만큼 Splash Acivity 띄운 후 Activity 전환
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 저장소 쓰기 권한 확인(쓰기 권한이 있으면 읽기 권한 자동 부여)
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // 권한이 있으면 다음 Activity로 전환
                    startNextAcivity();
                } else {
                    // 권한이 없다면 권한 요청
                    requestExternalStoragePermission();
                }
            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    public void onBackPressed() {
        // 스플래시 화면 뒤로가기 기능 제거
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        // 저장소 쓰기에 대한 권한
        if (requestCode == PERMISSION_WRITE_EXTERNAL_STORAGE) {
            // Request for camera permission.
            // 권한이 승인되었다면
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted.
                // 다음 Activity로 전환
                startNextAcivity();
            } else {
                // Permission request was denied.
                //권한이 거부되었다면 Toast 메시지 출력 후 종료
                Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    public void startNextAcivity(){
        // 다음 Acivity로 전환
//        startActivity(new Intent(getApplication(), RegisterPetActivity.class));\
        startActivity(new Intent(getApplication(), SelectPetActivity.class));

        // Acivity 전환 효과
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        // 스플래시 액티비티를 스텍에서 제거
        SplashActivity.this.finish();
    }

    public void requestExternalStoragePermission(){
        // Request the permission. The result will be received in onRequestPermissionResult().
        // 저장소 쓰기 권한 요청
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE_EXTERNAL_STORAGE);
    }
}
