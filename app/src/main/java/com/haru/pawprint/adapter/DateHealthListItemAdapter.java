package com.haru.pawprint.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haru.pawprint.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DateHealthListItemAdapter extends RecyclerView.Adapter<DateHealthListItemAdapter.DateHealthListItemViewHolder> {

    private Context context;
    private ArrayList<DialogHealthListItemAdapter.HealthListItem> healthListItems;

    public DateHealthListItemAdapter(@NonNull Context context, ArrayList<DialogHealthListItemAdapter.HealthListItem> itemList){
        this.context = context;
        this.healthListItems = itemList;
    }

    @NonNull
    @Override
    public DateHealthListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_date_health_list, parent, false);

        return new DateHealthListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateHealthListItemViewHolder holder, int position) {

        // 4번째 아이템부터는 달력에 표시하지 않음
        if (position > 2) {
            holder.textViewType.setVisibility(View.GONE);
            return;
        }

        holder.textViewType.setText(healthListItems.get(position).type);

        GradientDrawable drawable = (GradientDrawable) holder.textViewType.getBackground();

        switch(healthListItems.get(position).mode){
            case 1: // 산책
                holder.textViewType.setText("산책");
                drawable.setColor(Color.parseColor("#FBA8A8"));
                drawable.setStroke(2, Color.parseColor("#FBA8A8"));
                break;
            case 2: // 주식
                drawable.setColor(Color.parseColor("#8CC1FF"));
                drawable.setStroke(2, Color.parseColor("#8CC1FF"));
                break;
            case 3: // 간식
                drawable.setColor(Color.parseColor("#6BDCC1"));
                drawable.setStroke(2, Color.parseColor("#6BDCC1"));
                break;
            case 4: // 영양제
                drawable.setColor(Color.parseColor(("#C1ABff")));
                drawable.setStroke(2, Color.parseColor("#C1ABff"));
                break;
            case 5: // 약
                drawable.setColor(Color.parseColor(("#FFCC69")));
                drawable.setStroke(2, Color.parseColor("#FFCC69"));
                break;
            case 6: // 병원
                drawable.setColor(Color.parseColor(("#FFA451")));
                drawable.setStroke(2, Color.parseColor("#FFA451"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return healthListItems.size();
    }

    static class DateHealthListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewType;

        public DateHealthListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewType = itemView.findViewById(R.id.textview_date_health_list_type);
        }
    }
}
