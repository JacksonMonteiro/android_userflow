package space.jacksonmonteiro.users.ui.user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

import space.jacksonmonteiro.users.R;
import space.jacksonmonteiro.users.utils.DateUtils;

public class CreateUserActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Spinner spinnerSexo, spinnerTipo;
    private Button btnChooseImage, btnCreateUser, btnGoBack;
    private EditText etNome, etUsername, etPassword, etAddress, etEmail, etCpfCnpj, etDataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // EditTexts
        etNome = findViewById(R.id.etNome);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etSenha);
        etAddress = findViewById(R.id.etEndereco);
        etEmail = findViewById(R.id.etEmail);
        etCpfCnpj = findViewById(R.id.etCpfCnpj);
        etDataNascimento = findViewById(R.id.etDataNascimento);

        // Buttons
        btnGoBack = findViewById(R.id.btnGoBack);
        btnCreateUser = findViewById(R.id.buttonAddUser);

        // Spinners
        spinnerSexo = findViewById(R.id.spinnerSexo);
        spinnerTipo = findViewById(R.id.spinnerTipo);

        setupSpinner(R.array.sexo, spinnerSexo);
        setupSpinner(R.array.tipo, spinnerTipo);

        // Focus Actions
        etDataNascimento.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showDatePicker();
            }
        });

        // Click Actions
        etDataNascimento.setOnClickListener(v -> {
            showDatePicker();
        });

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResource, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public boolean validateForm() {
        // RegEx
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        String emailRegex = "^([\\w-\\.]+)@([\\w-]+\\.)+([a-zA-Z]{2,4})$";

        // Fields
        String nome = etNome.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String endereco = etAddress.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String cpfCnpj = etCpfCnpj.getText().toString().trim();

        String dataNascimento = etDataNascimento.getText().toString().trim();
        long dataNascimentoTimestamp = DateUtils.convertDateToTimestamp(dataNascimento);

        Log.d("CREATEUSER", password.matches(passwordRegex) + "");

        if (nome.length() < 30) {
            etNome.setError("Seu nome deve ter no mínimo 30 caracteres");
            return false;
        } else if (username.isEmpty() || username.length() < 4) {
            etUsername.setError("Seu nome de usuário não pode estar vazio e deve ter no mínimo 4 caracteres");
            return false;
        } else if (!password.matches(passwordRegex)) {
            etPassword.setError("Sua senha deve possui pelo menos 8 letras, e ter pelo menos uma letra maiúscula e um número");
            return false;
        } else if (endereco.isEmpty()) {
            etAddress.setError("Seu endereço não pode estar vazio");
            return false;
        } else if (email.isEmpty()) {
            etEmail.setError("Seu email não pode estar vazio");
            return false;
        } else if (!email.matches(emailRegex)) {
            etEmail.setError("O formato do seu e-mail está inválido. Por favor, corrija e tente novamente");
            return false;
        } else if (cpfCnpj.length() != 8 || cpfCnpj.length() != 14) {
            etCpfCnpj.setError("deve digitar um CPF (8 números) ou CNPJ (14 números)");
            return false;
        } else if (dataNascimento.isEmpty()) {
            etDataNascimento.setError("A sua data de nascimento precisa estar preenchida");
            return false;
        } else if (!DateUtils.isOver18(dataNascimentoTimestamp)) {
            etDataNascimento.setError("Você precisa ter mais de 18 anos para se cadastrar");
            return false;
        }


        return true;
    }

    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month + 1, year);
                etDataNascimento.setText(formattedDate);
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }
}