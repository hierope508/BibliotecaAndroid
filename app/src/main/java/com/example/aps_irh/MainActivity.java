package com.example.aps_irh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aps_irh.View.Cadastro;

public class MainActivity extends AppCompatActivity {


    Button btnCadast;
    Button btnConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadast = findViewById(R.id.btnCadastrar);
        btnConsult = findViewById(R.id.btnConsulta);

        btnCadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Cadastro.class);
                startActivity(i);
            }
        });

    }
}
