package space.jacksonmonteiro.users.data.local;

/*
Created By Jackson Monteiro on 11/01/2024
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import space.jacksonmonteiro.users.models.User;


public class UserDao {
    private UserDatabase database;

    public UserDao(Context context) {
        database = new UserDatabase(context);
    }

    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserDatabase.COLUMN_NOME, user.getNome());
        values.put(UserDatabase.COLUMN_USERNAME, user.getUsername());
        values.put(UserDatabase.COLUMN_PASSWORD, user.getPassword());
        values.put(UserDatabase.COLUMN_FOTO, user.getFoto());
        values.put(UserDatabase.COLUMN_ENDERECO, user.getEndereco());
        values.put(UserDatabase.COLUMN_EMAIL, user.getEmail());
        values.put(UserDatabase.COLUMN_DATA_NASCIMENTO, user.getDataNascimento());
        values.put(UserDatabase.COLUMN_SEXO, user.getSexo());
        values.put(UserDatabase.COLUMN_TIPO, user.getTipo());
        values.put(UserDatabase.COLUMN_CPF_CNPJ, user.getCpfCnpj());

        return database.getWritableDatabase().insert(UserDatabase.TABLE_NAME, null, values);
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String[] projection = {UserDatabase.ID, UserDatabase.COLUMN_NOME, UserDatabase.COLUMN_USERNAME, UserDatabase.COLUMN_PASSWORD, UserDatabase.COLUMN_FOTO, UserDatabase.COLUMN_ENDERECO, UserDatabase.COLUMN_EMAIL, UserDatabase.COLUMN_DATA_NASCIMENTO, UserDatabase.COLUMN_SEXO, UserDatabase.COLUMN_TIPO, UserDatabase.COLUMN_CPF_CNPJ};

        Cursor cursor = database.getReadableDatabase().query(UserDatabase.TABLE_NAME, projection, null, null, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(UserDatabase.ID)));
                    user.setNome(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_NOME)));
                    user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_USERNAME)));
                    user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_PASSWORD)));
                    user.setFoto(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_FOTO)));
                    user.setEndereco(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_ENDERECO)));
                    user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_EMAIL)));
                    user.setDataNascimento(cursor.getLong(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_DATA_NASCIMENTO)));
                    user.setSexo(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_SEXO)));
                    user.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_TIPO)));
                    user.setCpfCnpj(cursor.getString(cursor.getColumnIndexOrThrow(UserDatabase.COLUMN_CPF_CNPJ)));

                    userList.add(user);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return userList;
    }
}
