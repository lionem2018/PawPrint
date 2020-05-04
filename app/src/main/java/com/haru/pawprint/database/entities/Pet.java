package com.haru.pawprint.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

// 반려동물 프로필 정보 저장하는 엔티티
@Entity(tableName = "pet_table", primaryKeys = "pet_name")
public class Pet {
    // 반려동물 이름
    @ColumnInfo(name="pet_name")
    @NonNull
    private String petName;

    // 반려동물 종
    @ColumnInfo(name="pet_type")
    @NonNull
    private String petType;

    // 반려동물 프로필 사진 Uri
    @ColumnInfo(name="pet_profile_uri")
    @NonNull
    private String petProfileUri;

    // 반려동물 성별
    @ColumnInfo(name="pet_gender")
    private int petGender;

    // 반려동물 생일
    @ColumnInfo(name="pet_birthday")
    @NonNull
    private String petBirthday;

    // 반려동물 입양일
    @ColumnInfo(name="pet_adoptday")
    @NonNull
    private String petAdoptday;

    // 반려동물 정보 생성
    @ColumnInfo(name="pet_time_stamp")
    @NonNull
    private String petTimeStamp;

    @NonNull
    public String getPetName() {
        return petName;
    }

    public void setPetName(@NonNull String petName) {
        this.petName = petName;
    }

    @NonNull
    public String getPetType() {
        return petType;
    }

    public void setPetType(@NonNull String petType) {
        this.petType = petType;
    }

    @NonNull
    public String getPetProfileUri() {
        return petProfileUri;
    }

    public void setPetProfileUri(@NonNull String petProfileUri) {
        this.petProfileUri = petProfileUri;
    }

    public int getPetGender() {
        return petGender;
    }

    public void setPetGender(int petGender) {
        this.petGender = petGender;
    }

    @NonNull
    public String getPetBirthday() {
        return petBirthday;
    }

    public void setPetBirthday(@NonNull String petBirthday) {
        this.petBirthday = petBirthday;
    }

    @NonNull
    public String getPetAdoptday() {
        return petAdoptday;
    }

    public void setPetAdoptday(@NonNull String petAdoptday) {
        this.petAdoptday = petAdoptday;
    }

    @NonNull
    public String getPetTimeStamp() {
        return petTimeStamp;
    }

    public void setPetTimeStamp(@NonNull String petTimeStamp) {
        this.petTimeStamp = petTimeStamp;
    }

    public Pet(){}

    public Pet(@NonNull String name, @NonNull String type, @NonNull String profileUri,
               int gender, @NonNull String birthday, @NonNull String adoptday, @NonNull String creationDate)
    {
        this.petName = name;
        this.petType = type;
        this.petProfileUri = profileUri;
        this.petGender = gender;
        this.petBirthday = birthday;
        this.petAdoptday = adoptday;
        this.petTimeStamp = creationDate;
    }
}
