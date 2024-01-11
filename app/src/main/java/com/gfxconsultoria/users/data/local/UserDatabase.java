package com.gfxconsultoria.users.data.local;
/*
Created By Jackson Monteiro on 11/01/2024
Copyright (c) 2024 GFX Consultoria
*/


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gfxconsultoria.users.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}