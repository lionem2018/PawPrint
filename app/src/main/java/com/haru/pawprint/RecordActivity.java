package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.haru.pawprint.fragment.RecordHealthFragment;
import com.haru.pawprint.fragment.RecordPictureFragment;

import static android.view.View.GONE;

public class RecordActivity extends AppCompatActivity {

    // 프래그먼트 관리 변수
    private FragmentManager fragmentManager;

    private RecordPictureFragment recordPictureFragment;
    private RecordHealthFragment recordHealthFragment;

    private LinearLayout btnGoRecordPicture;
    private LinearLayout btnGoRecordHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        btnGoRecordPicture = findViewById(R.id.layout_go_record_picture);
        btnGoRecordHealth = findViewById(R.id.layout_go_record_health);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        recordPictureFragment = new RecordPictureFragment();
        recordHealthFragment = new RecordHealthFragment();

        // 프래그먼트 등록
        fragmentTransaction.add(R.id.framelayout_record_picture, recordPictureFragment);
        fragmentTransaction.add(R.id.framelayout_record_health, recordHealthFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.show(recordPictureFragment);
        fragmentTransaction.hide(recordHealthFragment);

        btnGoRecordPicture.setVisibility(GONE);
        btnGoRecordHealth.setVisibility(View.VISIBLE);

        // 프래그먼트 상태 커밋
        fragmentTransaction.commit();

        btnGoRecordPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 프래그먼트 트랜잭션 가져오기
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // 프래그먼트 전환 애니메이션 등록
                fragmentTransaction.setCustomAnimations(R.anim.in_up, R.anim.out_down, R.anim.in_up, R.anim.out_down);
                // 첫번째 프래그먼트 숨기고 두번째 프래그먼트 보이기
                fragmentTransaction.hide(recordHealthFragment)
                        .show(recordPictureFragment)
                        .commit();

                btnGoRecordHealth.setVisibility(View.VISIBLE);
                view.setVisibility(GONE);
            }
        });

        btnGoRecordHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 프래그먼트 트랜잭션 가져오기
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // 프래그먼트 전환 애니메이션 등록
                fragmentTransaction.setCustomAnimations(R.anim.in_down, R.anim.out_up, R.anim.in_down, R.anim.out_up);
                // 첫번째 프래그먼트 숨기고 두번째 프래그먼트 보이기
                fragmentTransaction.hide(recordPictureFragment)
                        .show(recordHealthFragment)
                        .commit();

                btnGoRecordPicture.setVisibility(View.VISIBLE);
                view.setVisibility(GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}