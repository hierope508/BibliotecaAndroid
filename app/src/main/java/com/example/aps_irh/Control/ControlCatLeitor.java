package com.example.aps_irh.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;

import java.util.ArrayList;

public class ControlCatLeitor {

    private final String[] campos = {"idCatLeitor", "CodCatLeitor", "Descricao", "maxDays"};


    private CriaBanco banco;
    private SQLiteDatabase db;

    public ControlCatLeitor(Context context){
        banco = new CriaBanco(context);
    }

    public ArrayList<Abstract_Cadastro> Select(String where){
        Cursor cursor;

        ArrayList<Abstract_Cadastro> list = new ArrayList<>();
        CatLeitor c0 = new CatLeitor(0,"0", "Adicionar (+)", 0);

        list.add(c0);

        db = banco.getReadableDatabase();

        if(where!=null)
            where = "Descricao LIKE '%{1}%' OR CodCatLeitor LIKE '%{1}%'".replace("{1}",where);

        cursor = db.query("CatLeitor",campos, where, null, null, null, null, null);

        while(cursor.moveToNext()){
            int index;
            int idCatLeitor;
            String CodCatLeitor;
            String Descricao;
            int maxDays;

            index = cursor.getColumnIndexOrThrow("idCatLeitor");
            idCatLeitor = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("CodCatLeitor");
            CodCatLeitor = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("Descricao");
            Descricao = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("maxDays");
            maxDays = cursor.getInt(index);

            CatLeitor cLeitor = new CatLeitor(idCatLeitor, CodCatLeitor, Descricao, maxDays);
            list.add(cLeitor);
        }
        cursor.close();
        db.close();

        return list;

    }


    public long Insert(CatLeitor catLeitor){

        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("CodCatLeitor", catLeitor.getCod());
        valores.put("Descricao",catLeitor.getDescricao());
        valores.put("maxDays",catLeitor.getMaxDays());
        db = banco.getWritableDatabase();
        resultado = db.insert("CatLeitor", null, valores);
        db.close();

        return resultado;
    }

    public long Update(CatLeitor catLeitor){
        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("CodCatLeitor", catLeitor.getCod());
        valores.put("Descricao",catLeitor.getDescricao());
        valores.put("maxDays",catLeitor.getMaxDays());
        String where = "idCatLeitor = {idCatLeitor}".replace("{idCatLeitor}",
                String.valueOf(catLeitor.getIdCatLeitor()));

        db = banco.getWritableDatabase();
        resultado = db.update("CatLeitor",valores, where,null);
        db.close();

        return resultado;

    }
}
