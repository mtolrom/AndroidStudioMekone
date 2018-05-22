package com.mekonetolrom.homework_week1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Room;


@Database(entities = {Setting.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract SettingsDao settingsDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "setting-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}