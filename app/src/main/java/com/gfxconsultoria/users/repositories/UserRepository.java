package com.gfxconsultoria.users.repositories;
/*
Created By Jackson Monteiro on 11/01/2024
Copyright (c) 2024 GFX Consultoria
*/


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.gfxconsultoria.users.data.local.UserDao;
import com.gfxconsultoria.users.data.local.UserDatabase;
import com.gfxconsultoria.users.models.User;

import java.util.ArrayList;

public class UserRepository {
    private UserDao userDao;
    private LiveData<ArrayList<User>> users;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        users = userDao.getAllUsers();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public LiveData<ArrayList<User>> getAllUsers() {
        return users;
    }

    // Database Operations Asynctask
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
