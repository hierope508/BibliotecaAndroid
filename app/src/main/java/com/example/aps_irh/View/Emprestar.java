package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;
import com.google.gson.Gson;

import java.util.List;

public class Emprestar extends AppCompatActivity {

    @Override protected  void onResume(){
        super.onResume();
        LoadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestar);
        LoadData();

    }


    private void LoadData(){
        ListView view = findViewById(R.id.listViewEmprestar);

        List<Abstract_Cadastro> livros = null;
        try {
            livros = new ControlLivro(getApplicationContext()).Select(null,true,0);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }

        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(getApplicationContext(),  android.R.layout.simple_list_item_1, livros){
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

        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(Emprestar.this, EmprestarLivro.class);
                i.putExtra("idLivro", ((Livro)parent.getItemAtPosition(position)).getId());
                startActivity(i);
            }
        });
    }
}
