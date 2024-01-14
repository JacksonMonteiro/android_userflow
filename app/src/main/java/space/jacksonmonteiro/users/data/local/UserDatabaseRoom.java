package space.jacksonmonteiro.users.data.local;

/*
Created By Jackson Monteiro on 13/01/2024
*/


import androidx.room.Database;
import androidx.room.RoomDatabase;

import space.jacksonmonteiro.users.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabaseRoom extends RoomDatabase {
    public abstract UserDaoRoom userDaoRoom();
}
