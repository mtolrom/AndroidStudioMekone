package com.mekonetolrom.homework_week1;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SettingsDao {
    @Query("SELECT * FROM setting")
    List<Setting> getAll();

    @Query("SELECT * FROM setting WHERE email IN (:emails)")
    List<Setting> loadAllByIds(String[] emails);

    //@Query("SELECT * FROM setting WHERE first_name LIKE :firstName and last_name LIKE :lastName LIMIT 1")
    //Setting findByName(String firstName, String lastName);

    @Update
    void updateSettings(Setting... settings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Setting... settings);

    @Delete
    void delete(Setting setting);
}
