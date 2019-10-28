package com.example.aps_irh.Model;

import android.database.sqlite.SQLiteDatabase;

import com.example.aps_irh.Control.CriaBanco;

import java.util.Date;

public class Livro extends Abstract_Cadastro{
    private int id;
    private String cod;
    private String ISBN;
    private String titulo;
    private CatLivro catLivro;
    private String[] autores;
    private String[] palavrasChave;
    private Date dataPublicacao;
    private int numEdicao;
    private String editora;
    private int numPaginas;
    private SQLiteDatabase db;
    private CriaBanco banco;

    public Livro(){

    }

    public Livro(int id, String cod, String ISBN, String titulo, CatLivro catLivro, String[] autores,
        String[] palavrasChave, Date dataPublicacao, int numEdicao, String editora, int numPaginas) {
        this.id = id;
        this.cod = cod;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.catLivro = catLivro;
        this.autores = autores;
        this.palavrasChave = palavrasChave;
        this.dataPublicacao = dataPublicacao;
        this.numEdicao = numEdicao;
        this.editora = editora;
        this.numPaginas = numPaginas;
    }

    public int getId() {
        return id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CatLivro getCatLivro() {
        return catLivro;
    }

    public void setCatLivro(CatLivro catLivro) {
        this.catLivro = catLivro;
    }

    public String[] getAutores() {
        return autores;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public String[] getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getNumEdicao() {
        return numEdicao;
    }

    public void setNumEdicao(int numEdicao) {
        this.numEdicao = numEdicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    @Override
    public void Cadastrar() {

    }

    @Override
    public void Atualizar() {

    }
}
