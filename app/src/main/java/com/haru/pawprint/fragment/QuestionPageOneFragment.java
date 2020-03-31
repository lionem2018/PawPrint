package com.haru.pawprint.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.haru.pawprint.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionPageOneFragment extends Fragment {

    // CircleImageView 객체를 가져오기 위한 View 변수
    private View view;
    // 반려동물 종 EditText 변수
    private EditText editTextType;
    // 반려동물 이름 EditText 변수
    private EditText editTextName;
    // CircleImageView 변수(원형 이미지뷰)
    private CircleImageView circleImageView;

    private final int GET_GALLERY_IMAGE = 200;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // View 객체 가져오기
        view = inflater.inflate(R.layout.fragment_question_page_one, container, false);

        // 반려동물 종 EditText 객체 가져오기
        editTextType = (EditText) view.findViewById(R.id.edit_text_type);
        // 반려동물 이름 EditText  객체 가져오기
        editTextName = (EditText) view.findViewById(R.id.edit_text_name);

        // CircleImageView 객체 가져오기
        circleImageView = (CircleImageView) view.findViewById(R.id.circle_image_view_pet);
        // CircleImageView 클릭 리스너 등록
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CircleImageView가 눌리면 갤러리 보이기
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data)
    {
        // 갤러리에서 이미지를 선택한 결과라면
        if (requestCode == GET_GALLERY_IMAGE && resultCode == AppCompatActivity.RESULT_OK && data != null && data.getData() != null) {
            // 이미지 uri 가져오기
            Uri selectedImageUri = data.getData();

            // Image Cropper 실행
            CropImage.activity(selectedImageUri)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setFixAspectRatio(true)
                    .start(getActivity());// for fragment (DO NOT use `getActivity()`)

        }
        // Image Cropper에서 온 결과라면
        else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
            // 이미지가 성공적으로 크롭되었다면
            if(resultCode == AppCompatActivity.RESULT_OK) {
                // 크롭된 이미지를 CircleImageView에 적용
                Uri resultUri = activityResult.getUri();
                circleImageView.setImageURI(resultUri);
            }
        }
    }

    /**
     * 프래그먼트의 View 요소들의 값이 다 채워졌는지 확인하는 함수
     * @return boolean, 값이 다 채워졌는지 여부
     */
    public boolean isFilledAllView()
    {
        if(!editTextType.getText().toString().equals("") && !editTextName.getText().toString().equals("") && circleImageView.getDrawable() != null)
            return true;
        else
            return false;
    }
}
