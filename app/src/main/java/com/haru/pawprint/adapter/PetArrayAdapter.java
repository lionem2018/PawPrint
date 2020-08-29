package com.haru.pawprint.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haru.pawprint.MainActivity;
import com.haru.pawprint.PawPrintApplication;
import com.haru.pawprint.R;
import com.haru.pawprint.SelectPetActivity;
import com.haru.pawprint.database.entities.Pet;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

// 반려동물 선택 화면에서 반려동물 버튼 리스트 형성에 쓰이는 Adapter
public class PetArrayAdapter extends BaseAdapter {

    // 버튼 리스트로 보여줄 반려동물 리스트
    private ArrayList<Pet> items = new ArrayList<Pet>();

    private SelectPetActivity activity;

    public PetArrayAdapter(SelectPetActivity activity){
        this.activity = activity;
    }

    // 리스트 아이템 개수
    @Override
    public int getCount() {
        return items.size();
    }

    // 각 리스트 아이템에 반려동물 정보 적용
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_pet, viewGroup, false);
        }

        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.circle_image_view_item_pet);
        TextView textViewPetName = (TextView) view.findViewById(R.id.textview_item_pet_name);
        TextView textViewPetType = (TextView) view.findViewById(R.id.textview_item_pet_type);

        Pet petItem = items.get(i);

        circleImageView.setImageURI(Uri.parse(petItem.getPetProfileUri()));
        textViewPetName.setText(petItem.getPetName());
        textViewPetType.setText(petItem.getPetType());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PawPrintApplication)activity.getApplicationContext()).setCurrentPet(petItem);
                // 메인 화면으로 이동
                activity.startActivity(new Intent(activity.getApplication(), MainActivity.class));

                // Acivity 전환 효과
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                // 반려동물 선택 액티비티를 스텍에서 제거
                activity.finish();
            }
        });

        return view;
    }

    // 리스트 아이템 인덱스 반환
    @Override
    public long getItemId(int i) {
        return i;
    }

    // i번째 반려동물 정보 반환
    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    // 반려동물 정보 추가
    public void addItem(Pet pet)
    {
        items.add(pet);
    }

    // 반려동물 정보 삭제
    public void removeItem(Pet pet)
    {
        items.remove(pet);
    }
}
