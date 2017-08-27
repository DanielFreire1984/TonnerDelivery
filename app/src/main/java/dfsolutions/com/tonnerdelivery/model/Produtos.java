package dfsolutions.com.tonnerdelivery.model;

import com.google.firebase.database.DatabaseReference;

import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;

/**
 * Created by Daniel on 18/08/2017.
 */
public class Produtos {

    private String tipo, marca, titulo, descricao, valor, qtd, id;

    public Produtos() {
    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("produtos").child(this.getId()).setValue(this);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getValor() {return valor; }

    public void setValor(String valor) { this.valor = valor; }

    public String getQtd() { return qtd; }

    public void setQtd( String qtd) { this.qtd = qtd; }

    public String getId() { return id; }

    public void setId( String id) { this.id = id; }

}
