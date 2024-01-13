package space.jacksonmonteiro.users.contracts;

/*
Created By Jackson Monteiro on 13/01/2024
*/


import android.widget.Spinner;

import space.jacksonmonteiro.users.models.User;

public interface CreateOrEditUserContract {
    interface View {
        void handleUserInserted();

        void showInsertError(String message);

        void finishActivity();

        void showDatePicker();

        boolean validateForm(User user);

        void setupSpinner(int arrayResource, Spinner spinner);

        void populateFields(User user);
    }

    interface Presenter {
        void insertUser(User user);

        void updateUser(User user);

        void getUser(int id);
    }
}
