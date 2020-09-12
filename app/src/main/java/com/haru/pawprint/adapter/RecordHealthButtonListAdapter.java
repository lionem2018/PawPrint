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

public class RecordHealthButtonListAdapter extends RecyclerView.Adapter<RecordHealthButtonListAdapter.RecordHealthListItemViewHolder> {
    private ArrayList<String> itemList;
    private Context context;

    public RecordHealthButtonListAdapter(Context context, @Nullable ArrayList<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecordHealthListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record_health_list_with_button, parent, false);
        RecordHealthListItemViewHolder viewHolder = new RecordHealthListItemViewHolder(context, view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHealthListItemViewHolder holder, int position) {
        holder.editText.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class RecordHealthListItemViewHolder extends ViewHolder {
        private Button button;
        private EditText editText;
        private int hour = 0, minute = 0;

        public RecordHealthListItemViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button_record_health_list_item);
            editText = itemView.findViewById(R.id.edittext_record_health_list_item);

            GradientDrawable drawable = (GradientDrawable) button.getBackground();
            drawable.setColor(Color.parseColor("#E5E5E5"));
            drawable.setStroke(2, Color.parseColor("#E5E5E5"));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            button.setText(String.format("%02d:%02d", i, i1));
                            GradientDrawable drawable = (GradientDrawable) button.getBackground();
                            drawable.setColor(Color.parseColor("#FBA8A8"));
                            drawable.setStroke(2, Color.parseColor("#FBA8A8"));
                        }
                    }, hour, minute, false);
                    dialog.show();

                }
            });
        }
    }
}
