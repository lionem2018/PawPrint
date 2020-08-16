package com.haru.pawprint.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.haru.pawprint.R;
import com.haru.pawprint.adapter.RecordHealthButtonListAdapter;
import com.haru.pawprint.adapter.RecordHealthSpinnerListAdapter;
import com.haru.pawprint.adapter.RecordHealthTextListAdapter;

import java.lang.reflect.Array;
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

        ArrayList<String> playList = new ArrayList<>();
        ArrayList<String> mealList = new ArrayList<>();
        ArrayList<String> hospitalList = new ArrayList<>();
        ArrayList<String> etcList = new ArrayList<>();

        Button buttonAddPlayItem = view.findViewById(R.id.button_add_play_item);
        Button buttonAddMealItem = view.findViewById(R.id.button_add_meal_item);
        Button buttonAddHospitalItem = view.findViewById(R.id.button_add_hospital_item);
        Button buttonAddETCItem = view.findViewById(R.id.button_add_etc_item);

        RecyclerView recyclerViewListPlay = view.findViewById(R.id.recyclerview_list_play);
        RecyclerView recyclerViewListMeal = view.findViewById(R.id.recyclerview_list_meal);
        RecyclerView recyclerViewListHospital = view.findViewById(R.id.recyclerview_list_hospital);
        RecyclerView recyclerViewListETC = view.findViewById(R.id.recyclerview_list_etc);

        recyclerViewListPlay.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewListMeal.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewListHospital.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewListETC.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerViewListPlay.setAdapter(new RecordHealthButtonListAdapter(view.getContext(), playList));
        recyclerViewListMeal.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.MEAL, view.getContext(), mealList));
        recyclerViewListHospital.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.HOSPITAL, view.getContext(), hospitalList));
        recyclerViewListETC.setAdapter(new RecordHealthTextListAdapter(view.getContext(), etcList));


        buttonAddPlayItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playList.add("");
                recyclerViewListPlay.getAdapter().notifyItemInserted(playList.size()-1);
            }
        });

        buttonAddMealItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mealList.add("");
                recyclerViewListMeal.getAdapter().notifyItemInserted(mealList.size() - 1);
            }
        });

        buttonAddHospitalItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospitalList.add("");
                recyclerViewListHospital.getAdapter().notifyItemInserted(hospitalList.size()-1);
            }
        });

        buttonAddETCItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etcList.add("");
                recyclerViewListETC.getAdapter().notifyItemInserted(etcList.size()-1);
            }
        });

        return view;
    }
}
