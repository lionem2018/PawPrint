package com.haru.pawprint.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haru.pawprint.R;

import androidx.fragment.app.Fragment;

public class RecordPictureFragment extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_record_picture, container, false);
        return rootView;
    }
}
