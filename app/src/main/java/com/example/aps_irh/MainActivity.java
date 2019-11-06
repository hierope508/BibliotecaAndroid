package com.example.aps_irh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aps_irh.View.Cadastro;
import com.example.aps_irh.View.Emprestar;

public class MainActivity extends AppCompatActivity {


    Button btnCadast;
    Button btnConsult;
    Button btnSair;
    Button btnEmprestar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadast = findViewById(R.id.btnCadastrar);
        btnConsult = findViewById(R.id.btnConsulta);
        btnSair = findViewById(R.id.btn_sair);
        btnEmprestar = findViewById(R.id.btn_emprestar);


        btnCadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Cadastro.class);
                startActivity(i);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEmprestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Emprestar.class);
                startActivity(i);
            }
        });

    }
}
