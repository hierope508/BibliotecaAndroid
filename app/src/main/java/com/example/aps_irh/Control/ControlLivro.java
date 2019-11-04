package com.example.aps_irh.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;
import com.example.aps_irh.Model.CatLivro;
import com.example.aps_irh.Model.Livro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControlLivro {

    private final String[] campos = {"idLivro","CodLivro", "ISBN", "Titulo", "CodCatLivro", "autores", "palavrasChave",
            "dataPublicacao", "numEdicao", "editora", "numPaginas"};


    private CriaBanco banco;
    private SQLiteDatabase db;
    private Context _context;

    public ControlLivro(Context context){
        banco = new CriaBanco(context);
        this._context = context;
    }

    public ArrayList<Abstract_Cadastro> Select(String where) throws ParseException {
        Cursor cursor;

        ArrayList<Abstract_Cadastro> list = new ArrayList<>();

        if(where == null){
            Livro l0 = new Livro(0,"0", "0", "Adicionar (+)", null, null,null, null,0,null,0);
            list.add(l0);
        }

        db = banco.getReadableDatabase();

        if(where!=null)
            where = "Titulo LIKE '%{1}%' OR autores LIKE '%{1}%' OR editora LIKE '%{1}%'".replace("{1}",where);

        cursor = db.query("Livro",campos, where, null, null, null, null, null);

        while(cursor.moveToNext()){
            int index;
            int idLivro;
            String codLivro;
            String ISBN;
            String titulo;
            String codCatLivro;
            String autores;
            String palavrasChave;
            Date datapublicacao;
            int numEdicao;
            String editora;
            int numPaginas;

            index = cursor.getColumnIndexOrThrow("idLivro");
            idLivro = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("CodLivro" +
                    "");
            codLivro = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("ISBN");
            ISBN = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("Titulo");
            titulo = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("CodCatLivro");
            codCatLivro = cursor.getString(index);

            CatLivro catLivro= (CatLivro )((new ControlCatLivro(_context).Select(codCatLivro)).get(0));

            index = cursor.getColumnIndexOrThrow("autores");
            autores = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("palavrasChave");
            palavrasChave = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("dataPublicacao");
            DateFormat iso8601Format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            datapublicacao = iso8601Format.parse(cursor.getString(index));

            index = cursor.getColumnIndexOrThrow("numEdicao");
            numEdicao = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("editora");
            editora = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("numPaginas");
            numPaginas = cursor.getInt(index);

            Livro livro= new Livro(idLivro,codLivro,ISBN,titulo,catLivro,autores,palavrasChave,datapublicacao,numEdicao,editora,numPaginas);
            list.add(livro);
        }
        cursor.close();
        db.close();

        return list;

    }


    public long Insert(Livro livro){

        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("CodLivro", livro.getCod());
        valores.put("ISBN", livro.getISBN());
        valores.put("Titulo",livro.getTitulo());
        valores.put("CodCatLivro", livro.getCatLivro().getCod());
        valores.put("autores", livro.getAutores());
        valores.put("palavrasChave", livro.getPalavrasChave());
        valores.put("dataPublicacao", livro.getDataPublicacao().toString());
        valores.put("numEdicao", livro.getNumEdicao());
        valores.put("editora", livro.getEditora());
        valores.put("numPaginas", livro.getNumPaginas());
        db = banco.getWritableDatabase();
        resultado = db.insert("Livro", null, valores);
        db.close();

        return resultado;
    }

    public long Update(Livro livro){
        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("CodLivro", livro.getCod());
        valores.put("ISBN", livro.getISBN());
        valores.put("Titulo",livro.getTitulo());
        valores.put("CodCatLivro", livro.getCatLivro().getCod());
        valores.put("autores", livro.getAutores());
        valores.put("palavrasChave", livro.getPalavrasChave());
        valores.put("dataPublicacao", livro.getDataPublicacao().toString());
        valores.put("numEdicao", livro.getNumEdicao());
        valores.put("editora", livro.getEditora());
        valores.put("numPaginas", livro.getNumPaginas());
        String where = "idLivro = {idLivro}".replace("{idLivro}",
                String.valueOf(livro.getId()));

        db = banco.getWritableDatabase();
        resultado = db.update("Livro",valores, where,null);
        db.close();

        return resultado;

    }
}
