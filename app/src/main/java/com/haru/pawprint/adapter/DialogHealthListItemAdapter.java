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

public class DialogHealthListItemAdapter extends RecyclerView.Adapter<DialogHealthListItemAdapter.DialogHealthListItemViewHolder> {

    private Context context;
    private ArrayList<HealthListItem> healthListItems;

    public DialogHealthListItemAdapter(@NonNull Context context, ArrayList<HealthListItem> itemList){
        this.context = context;
        this.healthListItems = itemList;
    }

    @NonNull
    @Override
    public DialogHealthListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_health_list, parent, false);

        return new DialogHealthListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogHealthListItemViewHolder holder, int position) {
        holder.textViewType.setText(healthListItems.get(position).type);
        holder.textViewText.setText(healthListItems.get(position).text);

        GradientDrawable drawable = (GradientDrawable) holder.textViewType.getBackground();

        switch(healthListItems.get(position).mode){
            case 1: // 산책
                drawable.setColor(Color.parseColor("#E5E5E5"));
                drawable.setStroke(2, Color.parseColor("#E5E5E5"));
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

    static class DialogHealthListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewType;
        private TextView textViewText;

        public DialogHealthListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewType = itemView.findViewById(R.id.textview_dialog_health_list_type);
            textViewText = itemView.findViewById(R.id.textview_dialog_health_list_text);
        }
    }

    public static class HealthListItem{
        int mode;
        String type;
        String text;

        public HealthListItem(int mode, String type, String text)
        {
            this.mode = mode;
            this.type = type;
            this.text = text;
        }
    }
}
