package space.jacksonmonteiro.users;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
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
    private EditText etFilter;
    private ArrayList<User> usersList;
    private UserAdapter adapter;
    private FloatingActionButton btnGoToAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.green));

        btnGoToAddUser = findViewById(R.id.btnGoToAddUser);

        adapter = new UserAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // Name Filter
        etFilter = findViewById(R.id.etFilter);
        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<User> filteredList = new ArrayList<>();
                String filterStr = charSequence.toString();

                for (User user : usersList) {
                    if (user.getNome().toLowerCase().contains(filterStr.toLowerCase())) {
                        filteredList.add(user);
                    }
                }

                adapter.setUsers(filteredList);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

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
        usersList = new ArrayList<>(users);
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