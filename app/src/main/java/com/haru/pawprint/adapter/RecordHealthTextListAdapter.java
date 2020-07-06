package com.haru.pawprint.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.haru.pawprint.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class RecordHealthTextListAdapter extends RecyclerView.Adapter<RecordHealthTextListAdapter.RecordHealthListItemViewHolder> {
    private ArrayList<String> itemList;
    private Context context;

    public RecordHealthTextListAdapter(Context context, @Nullable ArrayList<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecordHealthListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record_health_list_only_text, parent, false);
        RecordHealthListItemViewHolder viewHolder = new RecordHealthListItemViewHolder(context, view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHealthListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class RecordHealthListItemViewHolder extends ViewHolder {
        private EditText editText;

        public RecordHealthListItemViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.edittext_record_health_list_item);
        }
    }
}
