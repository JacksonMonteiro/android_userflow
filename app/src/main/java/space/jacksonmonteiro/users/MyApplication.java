package space.jacksonmonteiro.users;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import android.app.Application;

import androidx.room.Room;

import space.jacksonmonteiro.users.data.local.UserDatabaseRoom;

public class MyApplication extends Application {
    private static UserDatabaseRoom roomDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRoomDatabase();
    }

    private void initializeRoomDatabase() {
        roomDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabaseRoom.class, "user_database").build();
    }

    public static UserDatabaseRoom getRoomDatabase() {
        return roomDatabase;
    }
}
