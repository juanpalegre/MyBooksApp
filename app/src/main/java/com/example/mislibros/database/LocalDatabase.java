package com.example.mislibros.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mislibros.modelos.Libro;

@Database(entities = {Libro.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase sInstance;

    public static LocalDatabase getInstance(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context, LocalDatabase.class, "Local Database" )
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }

    public abstract LibrosDao getLibrosDao();
}
