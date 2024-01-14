package space.jacksonmonteiro.users.presenters;

/*
Created By Jackson Monteiro on 13/01/2024
*/

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import space.jacksonmonteiro.users.MyApplication;
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
        new InsertUserAsyncTask(view, user, context).execute();
    }

    @Override
    public void updateUser(User user) {
        new UpdateUserAsyncTask(view, user).execute();
    }

    @Override
    public void getUser(int id) {
        new GetUserAsyncTask(view, id).execute();
    }

    private static class InsertUserAsyncTask extends AsyncTask<Void, Void, Long> {
        private final CreateOrEditUserContract.View view;
        private final User user;
        private final Context context;

        public InsertUserAsyncTask(CreateOrEditUserContract.View view, User user, Context context) {
            this.view = view;
            this.user = user;
            this.context = context;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            API api = BaseRetrofitClient.createService(API.class, context);
            Call<String> call = api.sendUser(user);

            try {
                Response<String> response = call.execute();

                if (response.isSuccessful()) {
                    User existingUser = MyApplication.getRoomDatabase().userDaoRoom().getUserByUsername(user.getUsername());

                    if (existingUser != null) {
                        return -1L;
                    } else {
                        return MyApplication.getRoomDatabase().userDaoRoom().insertUser(user);
                    }
                } else {
                    return -2L;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return -2L;
            }
        }

        @Override
        protected void onPostExecute(Long result) {
            if (result != -1) {
                view.handleUserInserted();
            } else if (result == -1) {
                view.showInsertError("O nome de usuário já existe. troque o nome digitado e tente novamente");
            } else if (result == -2) {
                view.showInsertError("Não foi possível cadastrar o usuário. Por favor, tente novamente");
            }
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<Void, Void, Integer> {
        private final CreateOrEditUserContract.View view;
        private final User user;

        public UpdateUserAsyncTask(CreateOrEditUserContract.View view, User user) {
            this.view = view;
            this.user = user;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            User existingUser = MyApplication.getRoomDatabase().userDaoRoom().getUserByUsername(user.getUsername());
            if (existingUser != null) {
                return -1;
            } else {
                return MyApplication.getRoomDatabase().userDaoRoom().updateUser(user);
            }
        }

        @Override
        protected void onPostExecute(Integer updatedRows) {
            if (updatedRows > 0) {
                view.handleUserInserted();
            } else if (updatedRows == -1) {
                view.showInsertError("O nome de usuário já existe. troque o nome digitado e tente novamente");
            } else {
                view.showInsertError("Ocorreu um erro ao atualizar o usuário. Tente novamente");
            }
        }
    }

    private static class GetUserAsyncTask extends AsyncTask<Void, Void, User> {
        private final CreateOrEditUserContract.View view;
        private final int userId;

        public GetUserAsyncTask(CreateOrEditUserContract.View view, int userId) {
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
                view.populateFields(user);
            } else {
                view.showInsertError("Não foi possível recuperar os dados do usuário para edição. Você será removido da tela em 2 segundos");
                view.finishActivity();
            }
        }
    }
}
