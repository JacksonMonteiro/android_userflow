package space.jacksonmonteiro.users.contracts;
/*
Created By Jackson Monteiro on 13/01/2024
*/


import java.util.List;

import space.jacksonmonteiro.users.models.User;

public interface MainContract {
    interface View {
        void setUserList(List<User> users);

        void showListError();
        void showDeleteError();
        void handleUserDeleted();
    }

    interface Presenter {
        void getUsers();

        void deleteUser(int userId);
    }
}
