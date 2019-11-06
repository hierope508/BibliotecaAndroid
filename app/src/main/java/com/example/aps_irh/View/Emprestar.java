package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;

import java.util.List;

public class Emprestar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestar);

        ListView view = findViewById(R.id.listViewEmprestar);

        List<Abstract_Cadastro> livros = null;
        try {
            livros = new ControlLivro(getApplicationContext()).Select(null);
        }catch (Exception ex){
            return;
        }

        for (int i = 0; i< livros.size();i++){
            if(((Livro)livros.get(i)).getCodLeitor() == 0)
                livros.remove(i);

        }

        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(getApplicationContext(),  android.R.layout.simple_list_item_1, livros);

        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(Emprestar.this, EmprestarLivro.class);
                i.putExtra("Livro", parent.getItemIdAtPosition(position));
                startActivity(i);
            }
        });
    }
}
