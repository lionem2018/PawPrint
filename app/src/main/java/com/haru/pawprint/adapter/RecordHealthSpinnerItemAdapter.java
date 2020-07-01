package com.haru.pawprint.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.haru.pawprint.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordHealthSpinnerItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> spinnerItemList;

    public RecordHealthSpinnerItemAdapter(@NonNull Context context, ArrayList<String> spinnerItemList){
        this.context = context;
        this.spinnerItemList = spinnerItemList;
    }

    @Override
    public int getCount() {
        return spinnerItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return spinnerItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contvertView, ViewGroup viewGroup) {
        View view;
        SpinnerViewHolder viewHolder;
        if(contvertView == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_record_health_spinner_hint, viewGroup, false);
            viewHolder = new SpinnerViewHolder(view);
            view.setTag(viewHolder);
        }
        else
        {
            view = contvertView;
            viewHolder = (SpinnerViewHolder)view.getTag();
        }

        GradientDrawable drawable = (GradientDrawable) viewHolder.textView.getBackground();

        if(spinnerItemList.get(i).equals("주식"))
        {
            drawable.setColor(Color.parseColor("#8CC1FF"));
            drawable.setStroke(2, Color.parseColor("#8CC1FF"));
            viewHolder.textView.setText(spinnerItemList.get(i));
        }
        else if(spinnerItemList.get(i).equals("간식"))
        {
            drawable.setColor(Color.parseColor("#6BDCC1"));
            drawable.setStroke(2, Color.parseColor("#6BDCC1"));
            viewHolder.textView.setText(spinnerItemList.get(i));
        }else if(spinnerItemList.get(i).equals("영양제"))
        {
            drawable.setColor(Color.parseColor(("#C1ABff")));
            drawable.setStroke(2, Color.parseColor("#C1ABff"));
            viewHolder.textView.setText(spinnerItemList.get(i));
        }

        return view;
    }

    private class SpinnerViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public SpinnerViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview_spinner_item);
        }
    }
}
