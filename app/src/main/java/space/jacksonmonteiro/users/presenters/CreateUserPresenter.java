package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import android.content.Context;

import space.jacksonmonteiro.users.contracts.CreateUserContract;
import space.jacksonmonteiro.users.data.local.UserDao;
import space.jacksonmonteiro.users.models.User;

public class CreateUserPresenter implements CreateUserContract.Presenter {
    private UserDao dao;
    private Context context;
    private CreateUserContract.View view;

    public CreateUserPresenter(Context context, CreateUserContract.View view) {
        this.context = context;
        this.view = view;
        this.dao = new UserDao(context);
    }

    @Override
    public void insertUser(User user) {
        try {
            long result = dao.insertUser(user);

            if (result != -1) {
                view.handleUserInserted();
            } else {
                view.showInsertError();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
