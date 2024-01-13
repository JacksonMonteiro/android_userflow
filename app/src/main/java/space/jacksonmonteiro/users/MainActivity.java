package space.jacksonmonteiro.users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import space.jacksonmonteiro.users.contracts.MainContract;
import space.jacksonmonteiro.users.listeners.OnUserClickListener;
import space.jacksonmonteiro.users.models.User;
import space.jacksonmonteiro.users.presenters.MainPresenter;
import space.jacksonmonteiro.users.ui.user.CreateOrEditUserActivity;
import space.jacksonmonteiro.users.ui.user.adapter.UserAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View, OnUserClickListener {
    public static final String EXTRA_USER_ID = "USER_ID";
    private final MainPresenter presenter = new MainPresenter(this, this);

    private UserAdapter adapter;
    private FloatingActionButton btnGoToAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToAddUser = findViewById(R.id.btnGoToAddUser);

        adapter = new UserAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        btnGoToAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateOrEditUserActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUsers();
    }

    @Override
    public void setUserList(List<User> users) {
        adapter.setUsers(users);
    }

    @Override
    public void showListError() {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view, "Ocorreu um erro no cadastro do usuário. Por favor, tente novamente!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteError() {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view, "Ocorreu um erro ao tentar excluir o usuário. Por favor, tente novamente!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void handleUserDeleted() {
        onResume();
    }

    @Override
    public void gotoEdit(int userId) {
        Intent intent = new Intent(this, CreateOrEditUserActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        startActivity(intent);
    }

    @Override
    public void gotoDelete(int userId) {
        showDeleteConfirmationDialog(userId);
    }

    private void showDeleteConfirmationDialog(int userId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar exclusão");
        builder.setMessage("Tem certeza que deseja excluir este usuário?");


        builder.setPositiveButton("Sim", (dialog, which) -> {
            presenter.deleteUser(userId);
        });

        builder.setNegativeButton("Não", (dialog, which) -> {

        });

        // Mostra o AlertDialog
        builder.show();
    }

}