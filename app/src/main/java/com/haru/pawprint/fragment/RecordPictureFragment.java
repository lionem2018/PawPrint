package com.haru.pawprint.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.haru.pawprint.EditPictureActivity;
import com.haru.pawprint.PawPrintApplication;
import com.haru.pawprint.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RecordPictureFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_record_picture, container, false);
        ImageView imageView = view.findViewById(R.id.imageview_record_picture);

        imageView.setImageURI(Uri.parse(((PawPrintApplication)getContext().getApplicationContext()).getCurrentPet().getPetProfileUri()));  // just test
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), EditPictureActivity.class));
            }
        });

        RelativeLayout bottomSheet = view.findViewById(R.id.bottomsheet_record_picture);
        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setGestureInsetBottomIgnored(true);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return view;
    }
}
