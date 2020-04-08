package com.haru.pawprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SelectPetActivity extends BaseActivity{

    private Button buttonAddPet;
    private LayoutInflater inflater;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet);

        // 펫 리스트 아이템 동적 추가를 위한 인플래터 가져오기
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        // 펫 리스트 역할의 리니어 레이아웃 가져오기
        linearLayout = (LinearLayout) findViewById(R.id.layout_pet_list);

        // 펫 추가 버튼 가져오기
        buttonAddPet = (Button) findViewById(R.id.button_add_pet);
        // 펫 추가 버튼 클릭 리스너 등록
        buttonAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                inflater.inflate(R.layout.layout_pet_item, linearLayout, true);
//                checkPetItemNumber();

                // 반려동물 등록 화면으로 이동
                startActivity(new Intent(getApplication(), RegisterPetActivity.class));

                // Acivity 전환 효과
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                // 반려동물 선택 액티비티를 스텍에서 제거
                SelectPetActivity.this.finish();
            }
        });

        checkPetItemNumber();
    }

    private void checkPetItemNumber(){
        // 반려동물 최대 4마리 관리 가능
        if(linearLayout.getChildCount() >= 4)
            // 4마리가 되면 펫 추가 버튼 숨기기
            buttonAddPet.setVisibility(View.GONE);
        else
            // 4마리가 아니라면 펫 추가 버튼 보이기
            buttonAddPet.setVisibility(View.VISIBLE);
    }
}
