package com.example.aps_irh.Model;

public class Cliente extends Abstract_Cadastro{

    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private CatLeitor catLeitor;

    private int codCliente;

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public Cliente(){

    }

    public Cliente(int codCliente, String nome, String endereco, String telefone, String email, CatLeitor catLeitor) {
        this.codCliente = codCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.catLeitor = catLeitor;
    }

    public void setCatLeitor(CatLeitor catLeitor) {
        this.catLeitor = catLeitor;
    }

    public CatLeitor getCatLeitor() {
        return catLeitor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString(){
        return this.nome;
    }

    public String getCatLeitorString(){
        return  catLeitor.toString();
    }
}
