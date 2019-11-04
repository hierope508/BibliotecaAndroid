package com.example.aps_irh.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;
import com.example.aps_irh.Model.CatLivro;

import java.util.ArrayList;

public class ControlCatLivro {

    private final String[] campos = {"idCatLivro", "CodCatLivro", "Descricao", "maxDays", "tax"};

    private CriaBanco banco;
    private SQLiteDatabase db;

    public ControlCatLivro(Context context){
        banco = new CriaBanco(context);
    }

    public ArrayList<Abstract_Cadastro> Select(String where){
        Cursor cursor;
        ArrayList<Abstract_Cadastro> list = new ArrayList<>();

        if(where == null) {
            CatLivro c0 = new CatLivro(0, "0", "Adicionar (+)", 0);
            list.add(c0);
        }

        db = banco.getReadableDatabase();

        if(where!=null)
            where = "Descricao LIKE '%{1}%' OR CodCatLivro LIKE '%{1}%'".replace("{1}",where);

        cursor = db.query("CatLivro",campos, where, null, null, null, null, null);

        while(cursor.moveToNext()){
            int index;
            int idCatLivro;
            String CodCatLivro;
            String Descricao;
            int maxDays;

            index = cursor.getColumnIndexOrThrow("idCatLivro");
            idCatLivro = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("CodCatLivro");
            CodCatLivro = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("Descricao");
            Descricao = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("maxDays");
            maxDays = cursor.getInt(index);

            CatLivro cLeitor = new CatLivro(idCatLivro, CodCatLivro, Descricao, maxDays);
            list.add(cLeitor);
        }
        cursor.close();
        db.close();

        return list;

    }


    public long Insert(CatLivro catLivro){

        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("CodCatLivro", catLivro.getCod());
        valores.put("Descricao",catLivro.getDescricao());
        valores.put("maxDays",catLivro.getMaxDays());

        db = banco.getWritableDatabase();
        resultado = db.insert("CatLivro", null, valores);
        db.close();

        return resultado;
    }

    public long Update(CatLivro catLivro){
        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("CodCatLivro", catLivro.getCod());
        valores.put("Descricao",catLivro.getDescricao());
        valores.put("maxDays",catLivro.getMaxDays());
        String where = "idCatLivro = {idCatLivro}".replace("{idCatLivro}",
                String.valueOf(catLivro.getIdCatLivro()));

        db = banco.getWritableDatabase();
        resultado = db.update("CatLivro",valores, where,null);
        db.close();

        return resultado;

    }
}
