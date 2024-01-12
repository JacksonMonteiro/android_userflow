package space.jacksonmonteiro.users;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import space.jacksonmonteiro.users.ui.user.CreateUserActivity;
import space.jacksonmonteiro.users.ui.user.adapter.UserAdapter;
import space.jacksonmonteiro.users.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private FloatingActionButton btnGoToAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToAddUser = findViewById(R.id.btnGoToAddUser);

        final UserAdapter adapter = new UserAdapter();

        RecyclerView recyclerView = findViewById(R.id.usersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(
                this,
                users -> {
                    adapter.setUsers(users);
                }
        );

        btnGoToAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivity(intent);
        });
    }
}