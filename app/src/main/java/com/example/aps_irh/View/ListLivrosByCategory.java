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
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;

import java.util.ArrayList;

public class ListLivrosByCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_livros_by_category);

        ListView livrosList = findViewById(R.id.listViewByCategory);

        ControlLivro controlLivro = new ControlLivro(getApplicationContext());
        ArrayList<Abstract_Cadastro> livros = null;

        int idCatLivro= this.getIntent().getIntExtra("idCategoria",-1);

        try {
            livros = controlLivro.Select("",false,0);
        }catch (Exception ex){
            return;
        }

        for(int i =0;i<livros.size();i++){
            if(idCatLivro != ((Livro)livros.get(i)).getCatLivro().getIdCatLivro())
                livros.remove(i);

        }


        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(getApplicationContext(),  android.R.layout.simple_list_item_1, livros);
        livrosList.setAdapter(adapter);

        livrosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListLivrosByCategory.this, LivroEmprestado.class);
                i.putExtra("idLivro", ((Livro)parent.getItemAtPosition(position)).getId());
                startActivity(i);
            }
        });
    }
}
