package com.haru.pawprint.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
        holder.editText.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class RecordHealthListItemViewHolder extends ViewHolder {
        private EditText editText;
        private ImageButton buttonRemove;

        public RecordHealthListItemViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.edittext_record_health_list_item);
            buttonRemove = itemView.findViewById(R.id.imagebutton_remove);

            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        itemList.set(getAdapterPosition(), ((EditText)view).getText().toString());
                    }
                }
            });

            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId== EditorInfo.IME_ACTION_DONE){
                        //Clear focus here from edittext
                        v.clearFocus();
                    }
                    return false;
                }
            });
        }
    }
}
