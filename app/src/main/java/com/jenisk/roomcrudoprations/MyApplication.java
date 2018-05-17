package com.jenisk.roomcrudoprations;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jenisk.roomcrudoprations.database.AppDatabase;

/**
 * Created by Jenis Kasundra on 17/05/2018.
 */

public class MyApplication extends Application {

    public static MyApplication INSTANCE;
    private AppDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        // create database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build();
        INSTANCE = this;

    }

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    public AppDatabase getDB() {
        return database;
    }


}
