package com.jenisk.roomcrudoprations.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jenisk.roomcrudoprations.Dao.DirectoryDao;
import com.jenisk.roomcrudoprations.model.DirectoryData;
/**
 * Created by Jenis Kasundra on 17/05/2018.
 */
@Database(entities = {DirectoryData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	public static final String DB_NAME = "app_db";
	public abstract DirectoryDao directoryDao();
}