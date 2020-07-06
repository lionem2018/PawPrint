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

        ArrayList<String> playList = new ArrayList<String>(Arrays.asList("1234", "1234"));
        ArrayList<String> mealList = new ArrayList<String>(Arrays.asList("주식", "간식", "영양제"));
        ArrayList<String> hospitalList = new ArrayList<String>(Arrays.asList("약", "병원"));
        ArrayList<String> etcList = new ArrayList<String>(Arrays.asList("test","test"));

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

        recyclerViewListPlay.setAdapter(new RecordHealthButtonListAdapter(getContext(), playList));
        recyclerViewListMeal.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.MEAL, view.getContext(), mealList));
        recyclerViewListHospital.setAdapter(new RecordHealthSpinnerListAdapter(RecordHealthSpinnerListAdapter.MODE.HOSPITAL, getContext(), hospitalList));
        recyclerViewListETC.setAdapter(new RecordHealthTextListAdapter(getContext(), etcList));


        buttonAddMealItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mealList.add("");
            }
        });

        return view;
    }
}
