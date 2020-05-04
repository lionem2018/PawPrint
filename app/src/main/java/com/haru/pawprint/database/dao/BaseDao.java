package com.haru.pawprint.database.dao;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

// 기본 DAO(모든 DAO에서 쓰이는 Insert, Update, Delete 구현)
public interface BaseDao<ET> {
    // 하나의 Entity를 추가하는 Insert 함수
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(ET entity);

    // 여러 개의 Entity를 추가하는 Insert 함수
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<ET> entity);

    // 하나의 Entity 정보를 수정하는 Update 함수
    @Update
    public void update(ET entity);

    // 하나의 Entity 정보를 삭제하는 Delete 함수
    @Delete
    public void delete(ET entity);
}
