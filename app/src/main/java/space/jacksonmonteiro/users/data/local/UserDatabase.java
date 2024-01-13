package space.jacksonmonteiro.users.data.local;

/*
Created By Jackson Monteiro on 11/01/2024
*/


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_NAME = "users";
    public static final String ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FOTO = "foto";
    public static final String COLUMN_ENDERECO = "endereco";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DATA_NASCIMENTO = "dataNascimento";
    public static final String COLUMN_SEXO = "sexo";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_CPF_CNPJ = "cpfCnpj";

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME + " TEXT NOT NULL, "
                + COLUMN_USERNAME + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_FOTO + " BLOB, "
                + COLUMN_ENDERECO + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_DATA_NASCIMENTO + " INTEGER, "
                + COLUMN_SEXO + " TEXT, "
                + COLUMN_TIPO + " TEXT, "
                + COLUMN_CPF_CNPJ + " TEXT);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
