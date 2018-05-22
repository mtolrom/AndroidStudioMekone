package com.mekonetolrom.homework_week1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Setting {
    @PrimaryKey
    @NonNull
    private String email = "";

    @ColumnInfo(name = "daily_reminder")
    private String dailyReminder;

    @ColumnInfo(name = "min_distance")
    private String minDistance;

    @ColumnInfo(name = "max_distance")
    private String maxDistance;

    @ColumnInfo(name = "account_status")
    private String accountStatus;

    @ColumnInfo(name = "male_female")
    private String maleFemale;

    @ColumnInfo(name = "min_age")
    private String minAge;

    @ColumnInfo(name = "max_age")
    private String maxAge;

    @ColumnInfo(name = "photo_url")
    private String photoUrl;

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getDailyReminder() {
        return dailyReminder;
    }

    public void setDailyReminder(String dailyReminder) {
        this.dailyReminder = dailyReminder;
    }

    public String getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(String minDistance) {
        this.minDistance = minDistance;
    }

    public String getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(String maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getMaleFemale() {
        return maleFemale;
    }

    public void setMaleFemale(String maleFemale) {
        this.maleFemale = maleFemale;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
