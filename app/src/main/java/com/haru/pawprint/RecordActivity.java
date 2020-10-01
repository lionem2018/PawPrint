package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Rect;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        // back button
        TextView btnBack = findViewById(R.id.textview_back);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                fragmentTransaction.show(recordPictureFragment).hide(recordHealthFragment).commit();

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
                fragmentTransaction.show(recordHealthFragment).hide(recordPictureFragment).commit();

                btnGoRecordPicture.setVisibility(View.VISIBLE);
                view.setVisibility(GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();

            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);

                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }

        return super.dispatchTouchEvent( event );

    }
}