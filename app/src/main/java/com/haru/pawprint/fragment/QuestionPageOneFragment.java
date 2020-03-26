package com.haru.pawprint.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.haru.pawprint.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionPageOneFragment extends Fragment {

    View view;
    CircleImageView img;

    private final int GET_GALLERY_IMAGE = 200;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_question_page_one, container, false);

        img = (CircleImageView) view.findViewById(R.id.circle_image_view_pet);
        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        if (requestCode == GET_GALLERY_IMAGE && resultCode == AppCompatActivity.RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
//            img.setImageURI(selectedImageUri);

            CropImage.activity(selectedImageUri)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setFixAspectRatio(true)
                    .start(getActivity());// for fragment (DO NOT use `getActivity()`)

        }
        else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(data);
            if(resultCode == AppCompatActivity.RESULT_OK) {
                Uri resultUri = activityResult.getUri();
                img.setImageURI(resultUri);
                Log.d("hello", activityResult.getCropRect().toString());
            }
        }
    }
}
