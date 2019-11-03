package com.example.aps_irh.Model;

import androidx.annotation.NonNull;

public class CatLivro extends Abstract_Cadastro{

    private int idCatLivro;
    private String cod;
    private String descricao;
    private int maxDays;

    public CatLivro(){

    }

    public CatLivro(String cod, String descricao, int maxDays) {
        this.cod = cod;
        this.descricao = descricao;
        this.maxDays = maxDays;
    }

    public CatLivro(int idCatLivro, String cod, String descricao, int maxDays) {
        this.idCatLivro = idCatLivro;
        this.cod = cod;
        this.descricao = descricao;
        this.maxDays = maxDays;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }


    public int getIdCatLivro() {
        return idCatLivro;
    }

    @NonNull
    @Override
    public String toString() {
        return idCatLivro == 0 ? this.descricao: this.cod + " - " + this.descricao;
    }
}
