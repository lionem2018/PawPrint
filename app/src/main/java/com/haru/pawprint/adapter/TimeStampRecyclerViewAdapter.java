package com.haru.pawprint.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haru.pawprint.CalendarActivity;
import com.haru.pawprint.R;
import com.haru.pawprint.TimeStampActivity;
import com.haru.pawprint.dialog.HealthItemDialog;
import com.haru.pawprint.util.RecordCalendar;

import java.net.URI;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TimeStampRecyclerViewAdapter extends RecyclerView.Adapter<TimeStampRecyclerViewAdapter.TimeStampViewHolder> {

    private Context context;
    private ArrayList<URI> uriArrayList;

    public TimeStampRecyclerViewAdapter(@NonNull Context context, ArrayList<URI> uriArrayList){
        this.context = context;
        this.uriArrayList = uriArrayList;
    }

    @NonNull
    @Override
    public TimeStampViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_stamp, parent, false);
        return new TimeStampViewHolder(view, context);
    }

    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TimeStampViewHolder holder, int position) {
//        holder.imageView.setImageURI(uriArrayList.get(position));
        holder.imageView.setImageResource(R.drawable.pic_example);
    }

    class TimeStampViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public TimeStampViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_item_time_stamp);
        }
    }
}
