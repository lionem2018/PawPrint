package com.haru.pawprint.adapter;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.haru.pawprint.R;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class RecordHealthSpinnerListAdapter extends RecyclerView.Adapter<RecordHealthSpinnerListAdapter.RecordHealthListItemViewHolder> {
    private ArrayList<String> spinnerItemList;
    private Context context;
    private MODE mode;

    public enum MODE{
        PLAY,
        MEAL,
        HOSPITAL
    }

    public RecordHealthSpinnerListAdapter(MODE mode, Context context, @Nullable ArrayList<String> spinnerItemList)
    {
        this.spinnerItemList = spinnerItemList;
        this.context = context;
        this.mode = mode;
    }


    @NonNull
    @Override
    public RecordHealthListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record_health_list, parent,false);
        RecordHealthListItemViewHolder viewHolder = new RecordHealthListItemViewHolder(view, mode, this.context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHealthListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return spinnerItemList.size();
    }

    static class RecordHealthListItemViewHolder extends ViewHolder {
        private Spinner spinner;
        private EditText editText;
        private Context context;
        private RecordHealthSpinnerItemAdapter spinnerItemAdapter;

        public RecordHealthListItemViewHolder(@NonNull View itemView, MODE mode, Context context) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinner_record_health);
            editText = itemView.findViewById(R.id.edittext_record_health);
            this.context = context;

            if(mode == MODE.MEAL)
            {
                spinnerItemAdapter = new RecordHealthSpinnerItemAdapter(context, new ArrayList<String>(Arrays.asList("종류▼", "주식", "간식", "영양제")));
                spinner.setAdapter(spinnerItemAdapter);
                spinner.setSelection(0);
            }
        }
    }

}
