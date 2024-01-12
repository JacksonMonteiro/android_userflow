package space.jacksonmonteiro.users.ui.user;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import space.jacksonmonteiro.users.R;

public class CreateUserActivity extends AppCompatActivity {
    private Spinner spinnerSexo, spinnerTipo;
    private Button btnChooseImage, btnCreateUser, btnGoBack;
    private EditText etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // EditTexts
        etNome = findViewById(R.id.etNome);

        // Buttons
        btnGoBack = findViewById(R.id.btnGoBack);
        btnCreateUser = findViewById(R.id.buttonAddUser);

        // Spinners
        spinnerSexo = findViewById(R.id.spinnerSexo);
        spinnerTipo = findViewById(R.id.spinnerTipo);

        setupSpinner(R.array.sexo, spinnerSexo);
        setupSpinner(R.array.tipo, spinnerTipo);

        btnCreateUser.setOnClickListener(v -> {
            if (validateForm()) {
                Toast.makeText(this, "Todos os campos validados com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

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

    public boolean validateForm() {
        String nome = etNome.getText().toString().trim();

        if (nome.length() < 30) {
            etNome.setError("Seu nome deve ter no mínimo 30 caracteres");
            return false;
        }

        return true;
    }
}