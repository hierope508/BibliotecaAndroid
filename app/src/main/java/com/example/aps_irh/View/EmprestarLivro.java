package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aps_irh.Control.ControlCliente;
import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.Cliente;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EmprestarLivro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestar_livro);

        Toast.makeText(getApplicationContext(), "EmprestarLivro", Toast.LENGTH_SHORT).show();

        final Button emrpestar = findViewById(R.id.btn_Emprestar);
        final Spinner spinner = findViewById(R.id.spinnerPara);
        TextView codLivro = findViewById(R.id.txt_codLivro);
        TextView nomeLivro = findViewById(R.id.txt_titulo);
        int idLivro = this.getIntent().getIntExtra("idLivro",-1);
        ControlCliente controlCliente = new ControlCliente(getApplicationContext());
        ControlLivro controlLivro = new ControlLivro(getApplicationContext());

        try {
            final Livro livro ;
            livro = (Livro)controlLivro.Select("",false,idLivro).get(0);
            ArrayList<Abstract_Cadastro> listCliente = null;

            try {
                listCliente =  controlCliente.Select("");

            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                return;
            }

            final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listCliente){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    // Get the Item from ListView
                    View view = super.getView(position, convertView, parent);

                    // Initialize a TextView for ListView each Item
                    TextView tv = (TextView) view.findViewById(android.R.id.text1);

                    // Set the text color of TextView (ListView Item)
                    tv.setTextColor(Color.BLACK);

                    // Generate ListView Item using TextView
                    return view;
                }
            };

            spinner.setAdapter(adapter);
            codLivro.setText(livro.getCod());
            nomeLivro.setText(livro.getTitulo());

            emrpestar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ControlLivro controlLivro = new ControlLivro(getApplicationContext());
                    int maxDays;

                    if(livro.getCatLivro().getMaxDays()<((Cliente)spinner.getSelectedItem()).getCatLeitor().getMaxDays()){
                        maxDays = livro.getCatLivro().getMaxDays();
                    }
                    else{
                        maxDays =  ((Cliente)spinner.getSelectedItem()).getCatLeitor().getMaxDays();
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
