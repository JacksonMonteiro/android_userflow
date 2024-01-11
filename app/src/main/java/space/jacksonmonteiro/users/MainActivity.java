package space.jacksonmonteiro.users;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import space.jacksonmonteiro.users.viewmodels.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(
                this,
                users -> {
                    Toast.makeText(this, "All users loaded successful", Toast.LENGTH_LONG).show();
                }
        );
    }
}