package dfsolutions.com.tonnerdelivery.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;

/**
 * Created by Daniel on 07/08/2017.
 */

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public Usuario() {

    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuarios").child( getId()).setValue(this);
    }

    @Exclude
    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
