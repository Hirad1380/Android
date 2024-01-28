package com.example.afinal;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void insert(Entity item);
    @Delete
    void delete(Entity item);
    @Query("SELECT * FROM Product")
    List<Entity> getAllItems();

    @Query("DELETE FROM Product WHERE title=:titledelete")
    void Deletbytitle(String titledelete);








}
