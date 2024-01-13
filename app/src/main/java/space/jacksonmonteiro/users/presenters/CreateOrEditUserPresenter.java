package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import android.content.Context;

import java.util.List;

import space.jacksonmonteiro.users.contracts.CreateOrEditUserContract;
import space.jacksonmonteiro.users.data.local.UserDao;
import space.jacksonmonteiro.users.models.User;

public class CreateOrEditUserPresenter implements CreateOrEditUserContract.Presenter {
    private UserDao dao;
    private Context context;
    private CreateOrEditUserContract.View view;

    public CreateOrEditUserPresenter(Context context, CreateOrEditUserContract.View view) {
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

    @Override
    public void updateUser(User user) {
        try {
            int updatedRows = dao.updateUser(user);
            if (updatedRows > 0) {
                view.handleUserInserted();
            } else {
                view.showInsertError("Ocorreu um erro ao atualizar o usuário. Tente novamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showInsertError("Ocorreu um erro ao atualizar o usuário. Tente novamente");
        }
    }

    @Override
    public void getUser(int id) {
        try {
            User user = dao.getUserById(id);
            if (user != null) {
                view.populateFields(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showInsertError("Não foi possível recuperar os dados do usuário para edição. Você será removido da tela em 2 segundos");
            view.finishActivity();
        }
    }
}
