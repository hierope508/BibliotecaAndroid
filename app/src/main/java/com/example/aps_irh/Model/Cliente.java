package com.example.aps_irh.Model;

import java.util.Date;

public class Cliente extends Abstract_Cadastro{

    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;
    private Date nascimento;

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

    public Cliente(int codCliente, String nome, String endereco, String telefone, String email, CatLeitor catLeitor
                    ,String cpf, Date nasicimento) {

        this.codCliente = codCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.catLeitor = catLeitor;
        this.cpf = cpf;
        this.nascimento = nasicimento;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
}
