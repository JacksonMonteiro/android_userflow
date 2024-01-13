package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import android.content.Context;

import java.util.List;

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
            List<User> users = dao.getUserByUsername(user.getUsername());
            if (users.size() > 0) {
                view.showInsertError("Esse usuário já existe no banco de dados, por favor, troque o nome de usuário e tente novamente");
            } else {
                long result = dao.insertUser(user);

                if (result != -1) {
                    view.handleUserInserted();
                } else {
                    view.showInsertError("Não foi possível cadastrar o usuário. Por favor, tente novamente");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showInsertError("Não foi possível cadastrar o usuário. Por favor, tente novamente! - " + e.getMessage());
        }
    }
}
