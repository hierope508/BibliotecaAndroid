package com.example.aps_irh.Model;

import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.aps_irh.Control.CriaBanco;

import java.io.Serializable;
import java.util.Date;

public class Livro extends Abstract_Cadastro implements Serializable {
    private int id;
    private String cod;
    private String ISBN;
    private String titulo;
    private CatLivro catLivro;
    private String autores;
    private String palavrasChave;
    private Date dataPublicacao;
    private int numEdicao;
    private String editora;
    private int numPaginas;
    private int codLeitor;
    private Date previsao;

    public Livro(){

    }

    public Livro(int id, String cod, String ISBN, String titulo, CatLivro catLivro, String autores,
        String palavrasChave, Date dataPublicacao, int numEdicao, String editora, int numPaginas,
         int codLeitor, Date previsao) {
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
        this.codLeitor = codLeitor;
        this.previsao = previsao;
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

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
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

    @NonNull
    @Override
    public String toString() {
        return id == 0 ? this.titulo: this.cod + " - " + this.titulo;
    }

    public int getCodLeitor() {
        return codLeitor;
    }

    public void setCodLeitor(int codLeitor) {
        this.codLeitor = codLeitor;
    }

    public Date getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Date previsao) {
        this.previsao = previsao;
    }
}
