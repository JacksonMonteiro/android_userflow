package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/


import android.content.Context;

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
            List<User> userList = MyApplication.getRoomDatabase().userDaoRoom().getAllUsers();

            if (userList != null && userList.size() > 0) {
                view.setUserList(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showListError();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try {
            User user = MyApplication.getRoomDatabase().userDaoRoom().getUserById(userId);

            if (user != null) {
                MyApplication.getRoomDatabase().userDaoRoom().deleteUser(user);
                view.handleUserDeleted();
            } else {
                view.showDeleteError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showListError();
        }
    }
}
