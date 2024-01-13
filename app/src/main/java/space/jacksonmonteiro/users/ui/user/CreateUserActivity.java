package space.jacksonmonteiro.users.ui.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Locale;

import space.jacksonmonteiro.users.R;
import space.jacksonmonteiro.users.contracts.CreateUserContract;
import space.jacksonmonteiro.users.models.User;
import space.jacksonmonteiro.users.presenters.CreateUserPresenter;
import space.jacksonmonteiro.users.utils.DateUtil;
import space.jacksonmonteiro.users.utils.ImageUtil;
import space.jacksonmonteiro.users.utils.MaskUtil;

public class CreateUserActivity extends AppCompatActivity implements CreateUserContract.View {

    private ImageView profileImage;
    private View view;
    private DatePickerDialog datePickerDialog;
    private Spinner spinnerSexo, spinnerTipo;
    private Button btnChooseImage, btnCreateUser, btnGoBack;
    private EditText etNome, etUsername, etPassword, etAddress, etEmail, etCpf, etCnpj, etDataNascimento;
    private final CreateUserPresenter presenter = new CreateUserPresenter(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        // View
        view = findViewById(android.R.id.content);

        // ImageView
        profileImage = findViewById(R.id.profileImage);

        // EditTexts
        etNome = findViewById(R.id.etNome);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etSenha);
        etAddress = findViewById(R.id.etEndereco);
        etEmail = findViewById(R.id.etEmail);
        etDataNascimento = findViewById(R.id.etDataNascimento);

        etCpf = findViewById(R.id.etCpf);
        etCpf.addTextChangedListener(MaskUtil.mask(etCpf, MaskUtil.FORMAT_CPF));

        etCnpj = findViewById(R.id.etCnpj);
        etCnpj.addTextChangedListener(MaskUtil.mask(etCnpj, MaskUtil.FORMAT_CNPJ));

        // Buttons
        btnGoBack = findViewById(R.id.btnGoBack);
        btnCreateUser = findViewById(R.id.buttonAddUser);
        btnChooseImage = findViewById(R.id.btnChooseImg);

        // Spinners
        spinnerSexo = findViewById(R.id.spinnerSexo);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = adapterView.getSelectedItemPosition();
                switch (position) {
                    case 2:
                        etCpf.setVisibility(View.GONE);
                        etCnpj.setVisibility(View.VISIBLE);
                        break;
                    default:
                        etCpf.setVisibility(View.VISIBLE);
                        etCnpj.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            // Fields
            String nome = etNome.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String endereco = etAddress.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String cpf = MaskUtil.unmask(etCpf.getText().toString().trim());
            String cnpj = MaskUtil.unmask(etCnpj.getText().toString().trim());

            // Spinners
            int sexSpinnerSelectedPosition = spinnerSexo.getSelectedItemPosition();
            String sexo = spinnerSexo.getItemAtPosition(sexSpinnerSelectedPosition).toString();

            int tipoSpinnerSelectedPosition = spinnerTipo.getSelectedItemPosition();
            String tipo = spinnerTipo.getItemAtPosition(tipoSpinnerSelectedPosition).toString();

            // Date
            String dataNascimento = etDataNascimento.getText().toString().trim();
            long dataNascimentoTimestamp = DateUtil.convertDateToTimestamp(dataNascimento);

            String cpfCnpj = "";
            if (tipoSpinnerSelectedPosition == 1) {
                cpfCnpj = cpf;
            } else {
                cpfCnpj = cnpj;
            }

            User user = new User(nome, username, password, ImageUtil.convertImageViewToBase64(profileImage), endereco, email, dataNascimentoTimestamp, sexo, tipo, cpfCnpj);

            if (validateForm(user)) {
                presenter.insertUser(user);
            }
        });

        btnGoBack.setOnClickListener(v -> {
            finish();
        });

        btnChooseImage.setOnClickListener(v -> {
            ImageUtil.pickImageFromGallery(CreateUserActivity.this);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageUtil.handleImagePickResult(CreateUserActivity.this, requestCode, resultCode, data, profileImage);
    }

    public void setupSpinner(int arrayResource, Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public boolean validateForm(User user) {
        // RegEx
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        String emailRegex = "^([\\w-\\.]+)@([\\w-]+\\.)+([a-zA-Z]{2,4})$";

        // Spinners
        int sexSpinnerSelectedPosition = spinnerSexo.getSelectedItemPosition();
        int tipoSpinnerSelectedPosition = spinnerTipo.getSelectedItemPosition();

        // Date Field
        String dataNascimento = etDataNascimento.getText().toString().trim();


        if (user.getNome().length() < 30) {
            etNome.setError("Seu nome deve ter no mínimo 30 caracteres");
            return false;
        } else if (user.getUsername().isEmpty()) {
            etUsername.setError("Seu nome de usuário não pode estar vazio e deve ter no mínimo 4 caracteres");
            return false;
        } else if (!user.getPassword().matches(passwordRegex)) {
            etPassword.setError("Sua senha deve possui pelo menos 8 letras, e ter pelo menos uma letra maiúscula e um número");
            return false;
        } else if (user.getEndereco().isEmpty()) {
            etAddress.setError("Seu endereço não pode estar vazio");
            return false;
        } else if (user.getEmail().isEmpty()) {
            etEmail.setError("Seu email não pode estar vazio");
            return false;
        } else if (!user.getEmail().matches(emailRegex)) {
            etEmail.setError("O formato do seu e-mail está inválido. Por favor, corrija e tente novamente");
            return false;
        } else if (dataNascimento.isEmpty()) {
            etDataNascimento.setError("A sua data de nascimento precisa estar preenchida");
            return false;
        } else if (!DateUtil.isOver18(user.getDataNascimento())) {
            etDataNascimento.setError("Você precisa ter mais de 18 anos para se cadastrar");
            return false;
        } else if (sexSpinnerSelectedPosition == 0) {
            Snackbar.make(view, "Você precisa informar seu sexo", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (tipoSpinnerSelectedPosition == 0) {
            Snackbar.make(view, "Você precisa informar se é pessoa física ou jurídica", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (tipoSpinnerSelectedPosition == 1 && user.getCpfCnpj().length() != 11) {
            etCpf.setError("Você precisa informar um CPF (11 dígitos)");
            return false;
        } else if (tipoSpinnerSelectedPosition == 2 && user.getCpfCnpj().length() != 14) {
            etCnpj.setError("Você precisa informar um CNPJ (14 dígitos)");
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
                etDataNascimento.setError(null);
            }
        }, year, month, day);

        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.show();
    }

    @Override
    public void handleUserInserted() {
        finish();
    }

    @Override
    public void showInsertError(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}