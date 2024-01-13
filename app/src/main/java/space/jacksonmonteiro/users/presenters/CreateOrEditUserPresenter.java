package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import space.jacksonmonteiro.MyApplication;
import space.jacksonmonteiro.users.contracts.CreateOrEditUserContract;
import space.jacksonmonteiro.users.models.User;
import space.jacksonmonteiro.users.services.API;
import space.jacksonmonteiro.users.services.BaseRetrofitClient;

public class CreateOrEditUserPresenter implements CreateOrEditUserContract.Presenter {
    private Context context;
    private CreateOrEditUserContract.View view;

    public CreateOrEditUserPresenter(Context context, CreateOrEditUserContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void insertUser(User user) {
        API api = BaseRetrofitClient.createService(API.class, context);
        Call<String> call = api.sendUser(user);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    try {
                        User users = MyApplication.getRoomDatabase().userDaoRoom().getUserByUsername(user.getUsername());
                        if (users != null) {
                            view.showInsertError("Esse usuário já existe no banco de dados, por favor, troque o nome de usuário e tente novamente");
                        } else {
                            long result = MyApplication.getRoomDatabase().userDaoRoom().insertUser(user);
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
                } else {
                    view.showInsertError("Ocorreu um erro no envio do usuário para o serviço, portanto, ele não foi cadastrado no banco. Tente novamente");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.showInsertError("Ocorreu um erro no envio do usuário para o serviço, portanto, ele não foi cadastrado no banco. Tente novamente");
            }
        });
    }

    @Override
    public void updateUser(User user) {
        try {
            int updatedRows = MyApplication.getRoomDatabase().userDaoRoom().updateUser(user);
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
            User user = MyApplication.getRoomDatabase().userDaoRoom().getUserById(id);
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
