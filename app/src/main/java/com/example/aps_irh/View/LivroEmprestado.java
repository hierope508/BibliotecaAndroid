package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.aps_irh.Control.ControlCliente;
import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.Cliente;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LivroEmprestado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_emprestado);

        TextView codLivro = findViewById(R.id.txt_codLivro);
        TextView Titulo = findViewById(R.id.txt_TituloLivro);
        TextView dataPrevista = findViewById(R.id.txt_DataPrevista);
        TextView cliente = findViewById(R.id.txt_nomeClienteEmpret);

        ControlLivro controlLivro = new ControlLivro(getApplicationContext());
        ControlCliente controlCliente = new ControlCliente(getApplicationContext());
        try {
            cliente.setText(((Cliente)controlCliente.Select(cliente.getText().toString()).get(0)).getNome());
        }catch (Exception ex){

        }

        Livro livro = null;
        int idLivro = this.getIntent().getIntExtra("idLivro",-1);

        try {
            livro = (Livro)controlLivro.Select("",false,idLivro).get(0);
        }catch(Exception ex){

        }

        codLivro.setText(livro.getCod());
        Titulo.setText(livro.getTitulo());
        Date date = livro.getPrevisao();

        if(date != null){
            int day = date.getDate();
            int month = date.getMonth() + 1;
            int year = date.getYear();

            dataPrevista.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

        }

        codLivro.setEnabled(false);
        Titulo.setEnabled(false);
        dataPrevista.setEnabled(false);
    }
}
