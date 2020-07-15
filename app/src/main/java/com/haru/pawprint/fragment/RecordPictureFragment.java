package com.haru.pawprint.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haru.pawprint.EditPictureActivity;
import com.haru.pawprint.R;

import androidx.fragment.app.Fragment;

public class RecordPictureFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_record_picture, container, false);
        ImageView imageView = view.findViewById(R.id.imageview_record_picture);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), EditPictureActivity.class));
            }
        });

        return view;
    }
}
