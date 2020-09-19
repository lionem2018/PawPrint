package com.haru.pawprint;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.haru.pawprint.database.entities.Pet;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.annotation.NonNull;

public class PawPrintApplication extends Application {
    private Pet currentPet;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setCurrentPet(Pet currentPet)
    {
        this.currentPet = currentPet;
    }

    public Pet getCurrentPet()
    {
        return currentPet;
    }

    public static void setContext(Context context){
        PawPrintApplication.context = context;
    }

    public static Bitmap getBitmapFromUri(@NonNull Context context, @NonNull Uri uri, int maxSize) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        assert parcelFileDescriptor != null;
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();

        int outWidth;
        int outHeight;
        int inWidth = image.getWidth();
        int inHeight = image.getHeight();
        if(inWidth > inHeight){
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        return Bitmap.createScaledBitmap(image, outWidth, outHeight, false);
    }

}
