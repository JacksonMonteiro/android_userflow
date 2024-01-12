package space.jacksonmonteiro.users.ui.user;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import space.jacksonmonteiro.users.R;

public class CreateUserActivity extends AppCompatActivity {
    private Spinner spinnerSexo, spinnerTipo;
    private Button btnChooseImage, btnCreateUser, btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        btnGoBack = findViewById(R.id.btnGoBack);

        spinnerSexo = findViewById(R.id.spinnerSexo);
        spinnerTipo = findViewById(R.id.spinnerTipo);

        setupSpinner(R.array.sexo, spinnerSexo);
        setupSpinner(R.array.tipo, spinnerTipo);

        btnGoBack.setOnClickListener(v -> {
            finish();
        });
    }

    public void setupSpinner(int arrayResource, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                arrayResource,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}