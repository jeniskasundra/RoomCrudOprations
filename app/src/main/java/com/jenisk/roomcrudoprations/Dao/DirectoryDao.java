package com.jenisk.roomcrudoprations.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jenisk.roomcrudoprations.model.DirectoryData;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jenis Kasundra on 17/05/2018.
 */
@Dao
public interface DirectoryDao {
    @Query("SELECT * FROM directory")
    List<DirectoryData> getAllDirectory();

    @Query("SELECT * FROM directory WHERE id=:id")
    DirectoryData getDirectoryData(int id);

    @Insert
    void insertAll(DirectoryData directoryData);

    @Update
    void update(DirectoryData directoryData);

    @Delete
    void delete(DirectoryData directoryData);

}
