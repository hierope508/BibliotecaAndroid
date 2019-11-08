package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aps_irh.Control.ControlCatLivro;
import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLivro;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class Consulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        ListView catlivros = findViewById(R.id.listViewConsulta);
        ArrayList<Abstract_Cadastro> list =  new ControlCatLivro(getApplicationContext()).Select("");

        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(getApplicationContext(),  android.R.layout.simple_list_item_1, list);

        catlivros.setAdapter(adapter);

        catlivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                CatLivro selecionado = (CatLivro)adapter.getItem(position);

                Intent i = new Intent(Consulta.this, ListLivrosByCategory.class);
                i.putExtra("idCategoria", selecionado.getIdCatLivro());
                startActivity(i);
            }
        });
    }
}
