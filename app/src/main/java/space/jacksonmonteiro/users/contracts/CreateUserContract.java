package space.jacksonmonteiro.users.contracts;

/*
Created By Jackson Monteiro on 13/01/2024
*/


import space.jacksonmonteiro.users.models.User;

public interface CreateUserContract {
    interface View {
        void handleUserInserted();
        void showInsertError();
    }

    interface Presenter {
        void insertUser(User user);
    }
}
