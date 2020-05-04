/*
2020.03.28
Author: lionem2018@gmail.com
반려동물 등록 Activity Class
 */

package com.haru.pawprint;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;

import com.haru.pawprint.database.AppDatabase;
import com.haru.pawprint.database.dao.PetDao;
import com.haru.pawprint.database.entities.Pet;
import com.haru.pawprint.fragment.QuestionPageOneFragment;
import com.haru.pawprint.fragment.QuestionPageTwoFragment;

import java.sql.Timestamp;

public class RegisterPetActivity extends BaseActivity {

    // 프래그먼트 관리 변수
    private FragmentManager fragmentManager;
    // 현재 프래그먼트 번호 변수
    private int fragmentNum;

    // 프래그먼트 변수
    private QuestionPageOneFragment questionPageOneFragment;
    private QuestionPageTwoFragment questionPageTwoFragment;

    // 현재 프래그먼트 표시 변수
    private View viewPageOne;
    private View viewPageTwo;

    // 이전, 다음 Button 변수
    private Button button_next_page;
    private Button button_prev_page;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        db = AppDatabase.getAppDataBase(this);

        // 프래그먼트 생성
        questionPageOneFragment = new QuestionPageOneFragment();
        questionPageTwoFragment = new QuestionPageTwoFragment();

        // 프래그먼트 관리 객체, 프래그먼트 트랜잭션 생성
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 프래그먼트 등록
        fragmentTransaction.add(R.id.framelayout_page, questionPageOneFragment);
        fragmentTransaction.add(R.id.framelayout_page, questionPageTwoFragment);

        // 처음으로 첫번째 프래그먼트 보이기
        fragmentTransaction.show(questionPageOneFragment);
        fragmentTransaction.hide(questionPageTwoFragment);

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
                            .commit();

                    // 현재 프래그먼트 표시 변경
                    viewPageOne.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonInvisibleStateColor));
                    viewPageTwo.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.buttonVisibleStateColor));

                    // 현재 프래그먼트 번호 변경
                    fragmentNum = 2;

                    button_next_page.setText("완료");
                    button_prev_page.setEnabled(true);
                }
                else {
                    if(button_next_page.getText() == "완료") {

                        // 사용자 입력 정보 가져오기
                        String petName = ((EditText)questionPageOneFragment.getView().findViewById(R.id.edit_text_name)).getText().toString();
                        String petType = ((EditText)questionPageOneFragment.getView().findViewById(R.id.edit_text_type)).getText().toString();
                        Uri petImageUri = questionPageOneFragment.getPetImageUri();
                        int petGender = questionPageTwoFragment.getGender();
                        String petBirthday = ((EditText)questionPageTwoFragment.getView().findViewById(R.id.edit_text_birthday)).getText().toString();
                        String petAdoptday = ((EditText)questionPageTwoFragment.getView().findViewById(R.id.edit_text_adopt)).getText().toString();
                        String timestamp = new Timestamp(System.currentTimeMillis()).toString();

                        // 입력 정보를 가지고 반려동물 객체 생성
                        Pet newPet = new Pet(petName, petType, petImageUri.toString(), petGender, petBirthday, petAdoptday, timestamp);

                        // 새 반려동물 정보를 DB에 저장
                        new InsertAsyncTask(db.petDao()).execute(newPet);

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

    // 반려동물 정보 DB에 추가하는 비동기 태스크 클래스
    public static class InsertAsyncTask extends AsyncTask<Pet, Void, Void>
    {
        // 반려동물 DAO
        private PetDao petDao;

        public InsertAsyncTask(PetDao petDao){
            this.petDao = petDao;
        }

        // 한 개의 반려동물 정보를 DB에 추가
        @Override
        protected Void doInBackground(Pet... pets) {
            petDao.insert(pets[0]);
            return null;
        }
    }
}
