package dfsolutions.com.tonnerdelivery.model;

import com.google.firebase.database.DatabaseReference;

import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;

/**
 * Created by Daniel on 18/08/2017.
 */
public class Produtos {

    private String tipo, marca, titulo, descricao, valorStg;
    private double valorDb;

    public Produtos() {
    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        valorDb = Double.parseDouble(valorStg);
        referenciaFirebase.child("produtos").setValue(this);
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorDb() {
        return valorDb;
    }

    public void setValorDb(double valorDb) {
        this.valorDb = valorDb;
    }

    public String getValorStg() {
        return valorStg;
    }

    public void setValorStg(String valorStg) {
        this.valorStg = valorStg;
    }

}
