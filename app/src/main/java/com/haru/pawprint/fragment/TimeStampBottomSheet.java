package com.haru.pawprint.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.haru.pawprint.R;
import com.haru.pawprint.adapter.TimeStampRecyclerViewAdapter;

import java.net.URI;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TimeStampBottomSheet extends BottomSheetDialogFragment {

    public static final int PICTURE_ROW = 3;

    private ArrayList<URI> uriArrayList;

    private Context context;

    public TimeStampBottomSheet(@NonNull Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bottomsheet_time_stamp_picture_list, container, false);

        uriArrayList = new ArrayList<>();
        uriArrayList.add(URI.create(""));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_time_stamp_picture_list);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), PICTURE_ROW));
        recyclerView.setAdapter(new TimeStampRecyclerViewAdapter(context, uriArrayList));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        return view;
    }
}
