/*
2020.03.28
Author: lionem2018@gmail.com
반려동물 등록 Activity Class
 */

package com.haru.pawprint;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.haru.pawprint.BaseActivity;
import com.haru.pawprint.R;
import com.haru.pawprint.fragment.QuestionPageOneFragment;
import com.haru.pawprint.fragment.QuestionPageThreeFragment;
import com.haru.pawprint.fragment.QuestionPageTwoFragment;

public class RegisterPetActivity extends BaseActivity {

    // 프래그먼트 관리 변수
    private FragmentManager fragmentManager;
    // 현재 프래그먼트 번호 변수
    private int fragmentNum;

    // 프래그먼트 변수
    private QuestionPageOneFragment questionPageOneFragment;
    private QuestionPageTwoFragment questionPageTwoFragment;
//    private QuestionPageThreeFragment questionPageThreeFragment;

    // 현재 프래그먼트 표시 변수
    private View viewPageOne;
    private View viewPageTwo;
//    private View viewPageThree;

    // 이전, 다음 Button 변수
    private Button button_next_page;
    private Button button_prev_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        // 프래그먼트 생성
        questionPageOneFragment = new QuestionPageOneFragment();
        questionPageTwoFragment = new QuestionPageTwoFragment();
//        questionPageThreeFragment = new QuestionPageThreeFragment();

        // 프래그먼트 관리 객체, 프래그먼트 트랜잭션 생성
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 프래그먼트 등록
        fragmentTransaction.add(R.id.framelayout_page, questionPageOneFragment);
        fragmentTransaction.add(R.id.framelayout_page, questionPageTwoFragment);
//        fragmentTransaction.add(R.id.framelayout_page, questionPageThreeFragment);

        // 처음으로 첫번째 프래그먼트 보이기
        fragmentTransaction.show(questionPageOneFragment);
        fragmentTransaction.hide(questionPageTwoFragment);
//        fragmentTransaction.hide(questionPageThreeFragment);

        // 프래그먼트 상태 커밋
        fragmentTransaction.commit();

        // 헌재 프래그먼트 번호를 1로 설정
        fragmentNum = 1;

        // 버튼 객체 불러오기
        button_next_page = (Button)findViewById(R.id.button_next_page);
        button_prev_page = (Button)findViewById(R.id.button_prev_page);

        // 뷰 객체 불러오기
        viewPageOne = (View)findViewById(R.id.view_page_one);
        viewPageTwo = (View)findViewById(R.id.view_page_two);
//        viewPageThree = (View)findViewById(R.id.view_page_three);

        // 다음 버튼 클릭리스너 등록
        button_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 첫번째 프래그먼트인 경우
                if(fragmentNum == 1) {
                    // 프래그먼트 트랜잭션 가져오기
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // 프래그먼트 전환 애니메이션 등록
                    fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_left, R.anim.in_right, R.anim.out_left);
                    // 첫번째 프래그먼트 숨기고 두번째 프래그먼트 보이기
                    fragmentTransaction.hide(questionPageOneFragment)
                            .show(questionPageTwoFragment)
//                            .hide(questionPageThreeFragment)
                            .commit();

                    // 현재 프래그먼트 표시 변경
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    // 현재 프래그먼트 번호 변경
                    fragmentNum = 2;

                    button_next_page.setText("완료");
                    button_prev_page.setEnabled(true);
//                    button_next_page.setEnabled(false);

//                    // 프래그먼트의 값이 다 채워졌다면 이전, 다음 버튼 상태 변경(보류)
//                    if (questionPageOneFragment.isFilledAllView() && questionPageTwoFragment.isFilledAllView())
//                        button_next_page.setEnabled(true);
//                    else
//                        button_next_page.setEnabled(false);

                }
                // 현재 두 번째 프래그먼트 인 경우 (보류)
//                else if(fragmentNum == 2) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.in_right, R.anim.out_left, R.anim.in_right, R.anim.out_left);
//                    fragmentTransaction.hide(questionPageOneFragment).hide(questionPageTwoFragment).show(questionPageThreeFragment).commit();
//
//                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
//                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
//
//                    fragmentNum = 3;
//
//                    button_next_page.setEnabled(false);
//                }
                else{
                    // 이전, 다음 버튼 상태 변경
//                    button_next_page.setEnabled(false);
                    if(button_next_page.getText() == "완료") {
                        // 반려동물 선택 화면으로 이동
                        startActivity(new Intent(getApplication(), SelectPetActivity.class));

                        // Acivity 전환 효과
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                        // 반려동물 등록 액티비티를 스텍에서 제거
                        RegisterPetActivity.this.finish();
                    }
                    else {
                        button_next_page.setText("완료");
                        button_prev_page.setEnabled(true);
//                    button_next_page.setEnabled(false);
                    }
                }

            }
        });

        // 이전 버튼 클릭리스너 등록
        button_prev_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 현재 두번째 프래그먼트인 경우
                if(fragmentNum == 2) {
                    // 프래그먼트 트랜잭션 가져오기
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // 프래그먼트 전환 애니메이션 등록
                    fragmentTransaction.setCustomAnimations(R.anim.in_left, R.anim.out_right, R.anim.in_left, R.anim.out_right);
                    // 첫번째 프래그먼트 보이고, 두번째 프래그먼트 숨기기
                    fragmentTransaction.show(questionPageOneFragment)
                            .hide(questionPageTwoFragment)
//                            .hide(questionPageThreeFragment)
                            .commit();

                    // 현재 프래그먼트 표시 변경
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    // 현재 프래그먼트 번호 변경
                    fragmentNum = 1;

                    // 이전, 다음 버튼 상태 변경
                    button_prev_page.setEnabled(false);
                    button_next_page.setText("다음");
                    button_next_page.setEnabled(true);
                }
                // 현재 세 번째 프래그먼트 인 경우 (보류)
//                else if(fragmentNum == 3) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.in_left, R.anim.out_right, R.anim.in_left, R.anim.out_right);
//                    fragmentTransaction.hide(questionPageOneFragment).show(questionPageTwoFragment).hide(questionPageThreeFragment).commit();
//
//                    viewPageThree.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
//                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));
//
//                    fragmentNum = 2;
//
//                    button_next_page.setEnabled(true);
//                }
                else{
                    // 이전, 다음 버튼 상태 변경
                    button_prev_page.setEnabled(false);
                    button_next_page.setText("다음");
                    button_next_page.setEnabled(true);
                }

            }
        });
    }

    // ActivityResult를 Fragment로 넘김
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int request = requestCode & 0xffff;

        questionPageOneFragment.onActivityResult(request, resultCode, data);
    }
}
