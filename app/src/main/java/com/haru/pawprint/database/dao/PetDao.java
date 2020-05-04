package com.haru.pawprint.database.dao;

import com.haru.pawprint.database.entities.Pet;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

// 반려동물 DAO
@Dao
public interface PetDao extends BaseDao<Pet>{
    // 모든 ROW 조회
    @Query("SELECT * FROM pet_table")
    public List<Pet> getAll();

    // 이름으로 ROW 조회
    @Query("SELECT * FROM pet_table WHERE pet_name LIKE :name")
    public Pet findByName(String name);
}
