package space.jacksonmonteiro.users.data.local;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import space.jacksonmonteiro.users.models.User;

@Dao
public interface UserDaoRoom {
    @Insert
    long insertUser(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(int id);

    @Update
    int updateUser(User user);

    @Delete
    int deleteUser(User user);
}
