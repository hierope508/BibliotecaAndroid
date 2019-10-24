package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;
import com.example.aps_irh.Model.CatLivro;
import com.example.aps_irh.Model.Cliente;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;
import com.google.android.material.tabs.TabLayout;

public class Cadastro extends AppCompatActivity{
private TabLayout tabs;
private Context context;
private Button b;
private FrameLayout f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        context = this.getBaseContext();
        tabs = findViewById(R.id.tabLayout);
        f = findViewById(R.id.frame1);
        LoadTab(R.layout.cat_leitores_fragment, R.id.btn_CadastrarCatLei, new CatLeitor());



        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch(tab.getPosition()){
                    case 0:
                        LoadTab(R.layout.cat_leitores_fragment, R.id.btn_CadastrarCatLei, new CatLeitor());
                        break;

                    case 1:
                        LoadTab(R.layout.cat_livros_fragment, R.id.btn_CatLivros, new CatLivro());
                        break;

                    case 2:
                        LoadTab(R.layout.clients_fragment, R.id.btn_clientes, new Cliente());
                        break;

                    case 3:
                        LoadTab(R.layout.livros_fragment, R.id.btn_Livros, new Livro());
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void LoadTab(int rId, int bID, final Abstract_Cadastro cadastro){

        f.removeAllViews();
        LayoutInflater.from(context).inflate(rId, f, true);

        b = findViewById(bID);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cadastro.getClass().getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
