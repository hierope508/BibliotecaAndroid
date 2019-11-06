package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aps_irh.Control.ControlCliente;
import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmprestarLivro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestar_livro);

        final Button emrpestar = findViewById(R.id.btn_emprestar);
        final Spinner spinner = findViewById(R.id.spinnerPara);
        TextView codLivro = findViewById(R.id.txt_codLivro);
        TextView nomeLivro = findViewById(R.id.txt_titulo);
        final Livro livro = (Livro)this.getIntent().getSerializableExtra("Livro");

        ControlCliente controlCliente = new ControlCliente(getApplicationContext());
        ArrayList<Abstract_Cadastro> list = null;

        try {
            list =  controlCliente.Select("");
        }catch (Exception ex){

        }

        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        codLivro.setText(livro.getCod());
        nomeLivro.setText(livro.getTitulo());

        emrpestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlLivro controlLivro = new ControlLivro(getApplicationContext());
                int maxDays;

                if(livro.getCatLivro().getMaxDays()<((Livro)spinner.getSelectedItem()).getCatLivro().getMaxDays()){
                    maxDays = livro.getCatLivro().getMaxDays();
                }
                else{
                    maxDays =  ((Livro)spinner.getSelectedItem()).getCatLivro().getMaxDays();
                }
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, maxDays);

                Date limit = c.getTime();

                livro.setPrevisao(limit);

                double result = controlLivro.Update(livro);

                if(result>-1)
                    Toast.makeText(getApplicationContext(), "Emprestado com sucesso", Toast.LENGTH_LONG).show();

                onBackPressed();
            }
        });

    }
}
