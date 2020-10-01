package com.haru.pawprint.adapter;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.haru.pawprint.R;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class RecordHealthSpinnerListAdapter extends RecyclerView.Adapter<RecordHealthSpinnerListAdapter.RecordHealthListItemViewHolder> {
    private ArrayList<String> itemList;
    private Context context;
    private MODE mode;

    public enum MODE {
        MEAL,
        HOSPITAL
    }

    public RecordHealthSpinnerListAdapter(MODE mode, Context context, @Nullable ArrayList<String> spinnerItemList) {
        this.itemList = spinnerItemList;
        this.context = context;
        this.mode = mode;
    }


    @NonNull
    @Override
    public RecordHealthListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record_health_list_with_spinner, parent, false);

        return new RecordHealthListItemViewHolder(view, mode, this.context);
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
        private Spinner spinner;
        private EditText editText;
        private Context context;
        private RecordHealthSpinnerItemAdapter spinnerItemAdapter;
        private ImageButton buttonRemove;

        public RecordHealthListItemViewHolder(@NonNull View itemView, MODE mode, Context context) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinner_record_health_list_item);
            editText = itemView.findViewById(R.id.edittext_record_health_list_item);
            buttonRemove = itemView.findViewById(R.id.imagebutton_remove);
            this.context = context;

            buttonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            if (mode == MODE.MEAL) {
                spinnerItemAdapter = new RecordHealthSpinnerItemAdapter(context, new ArrayList<String>(Arrays.asList("종류▼", "주식", "간식", "영양제")));
                spinner.setAdapter(spinnerItemAdapter);
                spinner.setSelection(0);
            } else if (mode == MODE.HOSPITAL) {
                spinnerItemAdapter = new RecordHealthSpinnerItemAdapter(context, new ArrayList<String>(Arrays.asList("종류▼", "약", "병원")));
                spinner.setAdapter(spinnerItemAdapter);
                spinner.setSelection(0);
            }

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
