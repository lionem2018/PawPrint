package com.haru.pawprint.database;

import android.content.Context;

import com.haru.pawprint.database.dao.PetDao;
import com.haru.pawprint.database.entities.Pet;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Pet.class}, version= 1)
public abstract class AppDatabase extends RoomDatabase {
    // 데이터베이스 인스턴스를 싱글톤으로 관리
    private static AppDatabase INSTANCE;

    // 반려동물 DAO
    public abstract PetDao petDao();

    // 데이터베이스 인스턴스 생성 및 반환
    public static AppDatabase getAppDataBase(Context context)
    {
        // 이전에 생성한 인스턴트가 존재하지 않는다면 새로 생성
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "app-db").build();
        }

        return INSTANCE;
    }

    // 데이터베이스 인스턴스 제거
    public static void destroyInstance()
    {
        INSTANCE = null;
    }
}
