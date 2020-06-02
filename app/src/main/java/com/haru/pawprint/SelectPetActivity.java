package com.haru.pawprint;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.haru.pawprint.database.AppDatabase;
import com.haru.pawprint.database.dao.PetDao;
import com.haru.pawprint.database.entities.Pet;
import com.haru.pawprint.util.PetArrayAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public class SelectPetActivity extends BaseActivity{

    // 커스텀 반려동물 추가 버튼
    private static LinearLayout linearLayoutButtonAddPet;
    // 반려동물 리스트 뷰
    private ListView listViewPet;
    // Room DB
    private AppDatabase db;
    // 반려동물 리스트 뷰 어댑터
    private static PetArrayAdapter petArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet);

        // 반려동물 리스트뷰 가져오기
        listViewPet = (ListView) findViewById(R.id.listView_pet_list);
        // 반려동물 리스트뷰 어댑터 생성
        petArrayAdapter = new PetArrayAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 메인 화면으로 이동
                startActivity(new Intent(getApplication(), MainActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                // 반려동물 선택 액티비티를 스텍에서 제거
                SelectPetActivity.this.finish();
            }
        });
        // 반려동물 리스트뷰 어댑터 지정
        listViewPet.setAdapter(petArrayAdapter);

        // Room DB 인스턴스 가져오기
        db = AppDatabase.getAppDataBase(this);

        // 펫 추가 버튼 가져오기
        linearLayoutButtonAddPet = (LinearLayout) findViewById(R.id.layouyt_add_pet);
        // 펫 추가 버튼 클릭 리스너 등록
        linearLayoutButtonAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 반려동물 등록 화면으로 이동
                startActivity(new Intent(getApplication(), RegisterPetActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                // 반려동물 선택 액티비티를 스텍에서 제거
                SelectPetActivity.this.finish();
            }
        });

        // 반려동물 리스트뷰에 리스트 아이템 추가
        showPetItem();
    }

    // Room DB에서 반려동물 정보 가져와 리스트뷰에 추가하는 함수
    private void showPetItem(){
        // Room DB 특성상 서브 스레드에서 진행
        new GetAllAsyncTask(db.petDao()).execute();
    }

    // 반려동물 4마리 이상 되면 추가 버튼 숨기는 함수
    private static void checkPetItemNumber(){
        // 반려동물 최대 4마리 관리
        if(petArrayAdapter.getCount() >= 4)
            // 4마리가 되면 펫 추가 버튼 숨기기
            linearLayoutButtonAddPet.setVisibility(View.GONE);
        else
            // 4마리가 아니라면 펫 추가 버튼 보이기
            linearLayoutButtonAddPet.setVisibility(View.VISIBLE);
    }

    // Room DB에서 모든 반려동물 정보 가져오는 비동기 태스크 클래스
    private static class GetAllAsyncTask extends AsyncTask<Void, Void, Void> {
        // 반려동물 DAO
        private PetDao petDao;

        public GetAllAsyncTask(PetDao petDao)
        {
            this.petDao = petDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // DB에서 반려동물 정보 모두 가져오기
            List<Pet> pets =  petDao.getAll();

            // 모든 반려동물 정보 어댑터에 추가
            for(Pet pet:pets)
            {
                petArrayAdapter.addItem(pet);
            }

            // 반려동물 수가 4마리 이상인지 확인
            checkPetItemNumber();

            return null;
        }
    }

    class OnItemClick{
        public void changeToMainAcitivity(){
            startActivity(new Intent(getApplication(), RegisterPetActivity.class));

            // Acivity 전환 효과
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            // 반려동물 선택 액티비티를 스텍에서 제거
            SelectPetActivity.this.finish();
        }
    }
}
