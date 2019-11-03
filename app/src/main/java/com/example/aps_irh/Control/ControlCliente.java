package com.example.aps_irh.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;
import com.example.aps_irh.Model.Cliente;

import java.util.ArrayList;

public class ControlCliente {
    private final String[] campos = {"codCliente", "nome", "endereco", "telefone", "email", "codCatLeitor"};


    private CriaBanco banco;
    private SQLiteDatabase db;
    private Context _context;
    public ControlCliente(Context context) {
        banco = new CriaBanco(context);
        this._context = context;
    }

    public ArrayList<Abstract_Cadastro> Select(String where){
        Cursor cursor;

        ArrayList<Abstract_Cadastro> list = new ArrayList<>();
        Cliente c0 = new Cliente(0, "Adicionar (+)", "","","", null);

        list.add(c0);

        db = banco.getReadableDatabase();

        if(where!=null)
            where = "nome LIKE '%{1}%' OR email LIKE '%{1}%'".replace("{1}",where);

        cursor = db.query("Cliente",campos, where, null, null, null, null, null);

        while(cursor.moveToNext()){
            int index;
            int codCliente;
            String nome;
            String endereco;
            String telefone;
            String email;
            String codCatLeitor;

            index = cursor.getColumnIndexOrThrow("codCliente");
            codCliente = cursor.getInt(index);

            index = cursor.getColumnIndexOrThrow("nome");
            nome = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("endereco");
            endereco = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("telefone");
            telefone = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("email");
            email = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow("codCatLeitor");
            codCatLeitor = cursor.getString(index);

            CatLeitor catLeitor = (CatLeitor)((new ControlCatLeitor(_context).Select(codCatLeitor)).get(0));

            Cliente cCliente= new Cliente(codCliente, nome, endereco, telefone, email, catLeitor);
            list.add(cCliente);
        }
        cursor.close();
        db.close();

        return list;

    }


    public long Insert(Cliente cliente){

        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("nome",cliente.getNome());
        valores.put("endereco",cliente.getEndereco());
        valores.put("telefone",cliente.getTelefone());
        valores.put("email",cliente.getEmail());
        valores.put("CodCatLeitor",cliente.getCatLeitor().getCod());
        db = banco.getWritableDatabase();
        resultado = db.insert("Cliente", null, valores);
        db.close();

        return resultado;
    }

    public long Update(Cliente cliente){
        ContentValues valores = new ContentValues();
        long resultado;

        valores.put("nome",cliente.getNome());
        valores.put("endereco",cliente.getEndereco());
        valores.put("telefone",cliente.getTelefone());
        valores.put("email",cliente.getEmail());
        valores.put("CodCatLeitor",cliente.getCatLeitor().getCod());
        String where = "codCliente = {codCliente}".replace("{codCliente}",
                String.valueOf(cliente.getCodCliente()));

        db = banco.getWritableDatabase();
        resultado = db.update("Cliente",valores, where,null);
        db.close();

        return resultado;

    }
}
