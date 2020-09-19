package com.haru.pawprint.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.haru.pawprint.PawPrintApplication;
import com.haru.pawprint.R;
import com.haru.pawprint.RecordActivity;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardSlidePagerAdapter extends RecyclerView.Adapter<CardSlidePagerAdapter.CardViewHolder> {

    private ArrayList<Uri> uriArrayList;
    private Context context;

    public CardSlidePagerAdapter(Context context, ArrayList<Uri> uriArrayList) {
        this.context = context;
        this.uriArrayList = uriArrayList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_main_date, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 기록 화면으로 이동
                context.startActivity(new Intent(context, RecordActivity.class));
            }
        });

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Uri imageUri = uriArrayList.get(position);

        holder.imageView.post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {
                    Log.d("size", (int) holder.imageView.getHeight()+"");
                    bitmap = PawPrintApplication.getBitmapFromUri(context, imageUri, (int) holder.imageView.getHeight());
//            bitmap = PawPrintApplication.decodeSampledBitmapFromResource(imageUri, Resources.getSystem().getDisplayMetrics().widthPixels, Resources.getSystem().getDisplayMetrics().widthPixels);
//            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),imageUri);
                    holder.imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return uriArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_cardview);
        }
    }
}