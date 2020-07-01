package com.haru.pawprint.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haru.pawprint.R;
import com.haru.pawprint.adapter.RecordHealthSpinnerListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecordHealthFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_health, container, false);

        Button buttonAddPlayItem = view.findViewById(R.id.button_add_play_item);
        Button buttonAddMealItem = view.findViewById(R.id.button_add_meal_item);
        Button buttonAddHospitalItem = view.findViewById(R.id.button_add_hospital_item);
        Button buttonAddETCItem = view.findViewById(R.id.button_add_etc_item);

        RecyclerView recyclerViewListPlay = view.findViewById(R.id.recyclerview_list_play);
        RecyclerView recyclerViewListMeal = view.findViewById(R.id.recyclerview_list_meal);
        RecyclerView recyclerViewListHospital = view.findViewById(R.id.recyclerview_list_hospital);
        RecyclerView recyclerViewListETC = view.findViewById(R.id.recyclerview_list_etc);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
//        recyclerViewListPlay.setHasFixedSize(true);
//        recyclerViewListPlay.setLayoutManager(layoutManager);
//        recyclerViewListMeal.setHasFixedSize(true);
        recyclerViewListMeal.setLayoutManager(layoutManager);
//        recyclerViewListHospital.setLayoutManager(layoutManager);
//        recyclerViewListETC.setLayoutManager(layoutManager);

//        recyclerViewListPlay.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.PLAY, getContext(), new ArrayList<String>(Arrays.asList("주식", "간식", "영양제"))))));
        recyclerViewListMeal.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.MEAL, view.getContext(), new ArrayList<String>(Arrays.asList("주식", "간식", "영양제"))));
//        recyclerViewListHospital.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.HOSPITAL, getContext(), ));
//        recyclerViewListETC.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.ETC, getContext(), ));


        buttonAddMealItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
