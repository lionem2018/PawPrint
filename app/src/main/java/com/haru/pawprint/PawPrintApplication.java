package com.haru.pawprint;

import android.app.Application;
import android.net.Uri;

import com.haru.pawprint.database.entities.Pet;

public class PawPrintApplication extends Application {
    private Pet currentPet;

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
}
