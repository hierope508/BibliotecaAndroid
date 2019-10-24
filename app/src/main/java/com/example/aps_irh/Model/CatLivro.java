package com.example.aps_irh.Model;

public class CatLivro extends Abstract_Cadastro{

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

    @Override
    public void Cadastrar() {

    }

    @Override
    public void Atualizar() {

    }
}
