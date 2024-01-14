package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/


import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import space.jacksonmonteiro.MyApplication;
import space.jacksonmonteiro.users.contracts.MainContract;
import space.jacksonmonteiro.users.models.User;

public class MainPresenter implements MainContract.Presenter {
    private Context context;
    private MainContract.View view;

    public MainPresenter(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void getUsers() {
        try {
            new GetUsersAsyncTask(view).execute();
        } catch (Exception e) {
            e.printStackTrace();
            view.showListError();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            new GetUserAsyncTask(view, userId).execute();
        } catch (Exception e) {
            e.printStackTrace();
            view.showListError();
        }
    }

    private static class GetUsersAsyncTask extends AsyncTask<Void, Void, List<User>> {
        private final MainContract.View view;

        public GetUsersAsyncTask(MainContract.View view) {
            this.view = view;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return MyApplication.getRoomDatabase().userDaoRoom().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> userList) {
            if (userList != null && !userList.isEmpty()) {
                view.setUserList(userList);
            }
        }
    }

    private static class GetUserAsyncTask extends AsyncTask<Void, Void, User> {
        private final MainContract.View view;
        private final int userId;

        public GetUserAsyncTask(MainContract.View view, int userId) {
            this.view = view;
            this.userId = userId;
        }

        @Override
        protected User doInBackground(Void... voids) {
            return MyApplication.getRoomDatabase().userDaoRoom().getUserById(userId);
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {
                new DeleteUserAsyncTask(view, user).execute();
            } else {
                view.showDeleteError();
            }
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private final MainContract.View view;
        private final User user;

        public DeleteUserAsyncTask(MainContract.View view, User user) {
            this.view = view;
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MyApplication.getRoomDatabase().userDaoRoom().deleteUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            view.handleUserDeleted();
        }
    }

}
