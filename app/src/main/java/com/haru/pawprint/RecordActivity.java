package com.haru.pawprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.haru.pawprint.fragment.RecordPictureFragment;

public class RecordActivity extends AppCompatActivity {

    // 프래그먼트 관리 변수
    private FragmentManager fragmentManager;

    private RecordPictureFragment recordPictureFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        recordPictureFragment = new RecordPictureFragment();
//
//        // 프래그먼트 등록
        fragmentTransaction.add(R.id.framelayout_record_picture, recordPictureFragment);
////        fragmentTransaction.add(R.id.framelayout_page, questionPageTwoFragment);
//
        fragmentTransaction.show(recordPictureFragment);

        // 프래그먼트 상태 커밋
        fragmentTransaction.commit();
    }
}