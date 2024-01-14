package space.jacksonmonteiro.users.models;

/*
Created By Jackson Monteiro on 11/01/2024
*/


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("nome")
    @ColumnInfo(name = "nome")
    private String nome;

    @SerializedName("username")
    @ColumnInfo(name = "username")
    private String username;
    @SerializedName("password")
    @ColumnInfo(name = "password")
    private String password;
    @SerializedName("foto")
    @ColumnInfo(name = "foto")
    private String foto;
    @SerializedName("endereco")
    @ColumnInfo(name = "endereco")
    private String endereco;
    @SerializedName("email")
    @ColumnInfo(name = "email")
    private String email;
    @SerializedName("dataNascimento")
    @ColumnInfo(name = "dataNascimento")
    private long dataNascimento;
    @SerializedName("sexo")
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "tipo")
    private String tipo;
    @SerializedName("cpfCnpj")
    @ColumnInfo(name = "cpfCnpj")
    private String cpfCnpj;

    public User() {
    }

    public User(String nome, String username, String password, String foto, String endereco, String email, long dataNascimento, String sexo, String tipo, String cpfCnpj) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.foto = foto;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.tipo = tipo;
        this.cpfCnpj = cpfCnpj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(long dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
