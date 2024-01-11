package com.gfxconsultoria.users.data.local;

/*
Created By Jackson Monteiro on 11/01/2024
*/

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gfxconsultoria.users.models.User;

import java.util.ArrayList;

public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("SELECT * FROM users")
    LiveData<ArrayList<User>> getAllUsers();


}
