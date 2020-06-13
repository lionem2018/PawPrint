package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView btnBack = findViewById(R.id.textview_back_from_setting);

        RelativeLayout btnConfigLanguage = findViewById(R.id.layout_config_lang);
        RelativeLayout btnConfigAlarm = findViewById(R.id.layout_config_alarm);
        RelativeLayout btnConfigLock = findViewById(R.id.layout_config_lock);
        RelativeLayout btnConfigBackup = findViewById(R.id.layout_config_backup);
        RelativeLayout btnConfigRemoveAll = findViewById(R.id.layout_config_removeall);
        RelativeLayout btnConfigBugReport = findViewById(R.id.layout_config_bug_report);
        RelativeLayout btnConfigOpenSource = findViewById(R.id.layout_config_open_source);

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        btnConfigLanguage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingLanguageActiviity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });

        btnConfigAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingAlarmActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });

        btnConfigLock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingLockActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });

        btnConfigBackup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingBackupActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });

        btnConfigRemoveAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingRemoveAllActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });

        btnConfigBugReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingBugReportActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });

        btnConfigOpenSource.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SettingOpenSourceActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.in_right, R.anim.out_left);
            }
        });
    }
}
