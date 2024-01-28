package com.example.afinal;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


@androidx.room.Database(entities = {Entity.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract DAO dao();

//    private static Database INSTANCE;
//
//    public static synchronized Database getInstance(Context context){
//        if (INSTANCE == null){
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
//            ,Database.class, "title").build();
//        }
//        return INSTANCE;
//    }

}
