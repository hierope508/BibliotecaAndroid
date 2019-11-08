package com.example.aps_irh.Control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Banco.db";
    private static final int VERSAO = 1;

    public CriaBanco(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = " CREATE TABLE IF NOT EXISTS Cliente( " +
                "    codCliente INTEGER PRIMARY KEY AUTOINCREMENT " +
                "    ,nome TEXT NOT NULL " +
                "    ,endereco TEXT NOT NULL " +
                "    ,telefone TEXT NOT NULL " +
                "    ,email TEXT NOT NULL " +
                "    ,CPF TEXT" +
                "    ,dtNascimento DATE" +
                "    ,codCatLeitor TEXT NOT NULL" +
                "    ,FOREIGN KEY(CodCatLeitor ) REFERENCES CatLeitor(CodCatLeitor ) " +
                "); ";
        String sql2 =
                " CREATE TABLE IF NOT EXISTS CatLivro( " +
                "    idCatLivro INTEGER PRIMARY KEY AUTOINCREMENT " +
                "    ,CodCatLivro TEXT NOT NULL " +
                "    ,Descricao TEXT NOT NULL " +
                "    ,maxDays INTEGER " +
                "    ,tax INTEGER" +
                ");" ;
        String sql3 =
                "CREATE TABLE IF NOT EXISTS CatLeitor( " +
                "    idCatLeitor INTEGER PRIMARY KEY AUTOINCREMENT " +
                "    ,CodCatLeitor TEXT NOT NULL " +
                "    ,Descricao TEXT NOT NULL " +
                "    ,maxDays INTEGER " +
                "); ";
        String sql4 =
                " CREATE TABLE IF NOT EXISTS Livro( " +
                "    idLivro INTEGER PRIMARY KEY AUTOINCREMENT " +
                "    ,CodLivro TEXT NOT NULL " +
                "    ,ISBN TEXT NOT NULL " +
                "    ,Titulo TEXT NOT NULL " +
                "    ,CodCatLivro TEXT " +
                "    ,autores TEXT NOT NULL " +
                "    ,palavrasChave TEXT NOT NULL " +
                "    ,dataPublicacao DATE " +
                "    ,numEdicao INTEGER NOT NULL " +
                "    ,editora TEXT " +
                "    ,numPaginas INTEGER " +
                "    ,codCliente TEXT" +
                "    ,dtPrevista DATE" +
                "    ,FOREIGN KEY(CodCatLivro) REFERENCES CatLivro(CodCatLivro) " +
                "    ,FOREIGN KEY(codCliente) REFERENCES Cliente(codCliente) " +
                "); ";


        String sql5 = "INSERT INTO CatLeitor(CodCatLeitor, Descricao, maxDays) " +
                "VALUES ('001', 'Estudante', 60) " +
                ",('002', 'Professor', 90) " +
                ",('003', 'Usuário Comum', 30); ";

        String sql6 = "INSERT INTO CatLivro(CodCatLivro, Descricao, maxDays, tax) VALUES ('001', 'Livro', 60, 10) " +
                ",('002', 'Revista', 90, 10) " +
                ",('003', 'Relatório Técnico', 100, 20); ";

        String sql7 = "INSERT INTO Cliente(codCliente, nome, endereco, telefone,email,CPF,dtNascimento,codCatLeitor) " +
                "VALUES (1, 'Iran', 'Rua dos Irans', '958216546546', 'irannlopes@gmail.com','54654654', 'Wed Oct 05 00:00:00 EST 1995', '001') " +
                "      ,(2, 'Rodrigo', 'Rua dos Rodrigos', '958216546546', 'rodrigo@gmail.com', '54654654', 'Wed Oct 16 00:00:00 EST 1990', '002') " +
                "      ,(3, 'Humner', 'Rua dos Beto', '958216546546', 'beto@gmail.com','54654654', 'Wed Oct 16 00:00:00 EST 1980', '003') ";

        String sql8 = "INSERT INTO Livro(CodLivro, ISBN, Titulo, CodCatLivro,autores,palavrasChave,dataPublicacao,numEdicao, editora, numPaginas, codCliente, dtPrevista) " +
                "VALUES ('001', '11111', 'O segredo das coisas', '001', 'Irna, asajs, José', 'livro, bom dmeais', 'Wed Oct 16 00:00:00 EST 2013', 10, 'Abril', 100, null, null) " +
                "      ,('002', '645454', 'Fisica quantica', '003', 'Irna, asajs, José', 'livro, bom dmeais', 'Wed Oct 16 00:00:00 EST 2013', 10, 'Abril', 100, null, null) " +
                "      ,('003', '5555', 'Veja', '002', 'Irna, asajs, José', 'livro, bom dmeais', 'Wed Oct 16 00:00:00 EST 2013', 10, 'Abril', 100, null, null) ";


        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
        try{
            db.execSQL(sql7);
            db.execSQL(sql8);
        }catch (Exception ex){
            ex.toString();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}