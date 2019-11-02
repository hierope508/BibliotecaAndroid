package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.aps_irh.Control.ControlCatLeitor;
import com.example.aps_irh.Control.ControlCliente;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;
import com.example.aps_irh.Model.CatLivro;
import com.example.aps_irh.Model.Cliente;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Cadastro extends AppCompatActivity{
private TabLayout tabs;
private Context context;
private Button b;
private FrameLayout f;
private int currentTabID  = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        context = this.getBaseContext();
        tabs = findViewById(R.id.tabLayout);
        f = findViewById(R.id.frame1);

        HandlerTabClick(tabs.getTabAt(0));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                HandlerTabClick(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                HandlerTabClick(tab);
            }
        });
    }

    private void HandlerTabClick(TabLayout.Tab tab) {
        HandlerTabClick(tab, null);
    }

    private void HandlerTabClick(TabLayout.Tab tab, String query){
        currentTabID = -1;
        switch(tab.getPosition()){
            case 0:
                ArrayList<Abstract_Cadastro> catLeitores = new ArrayList<>();
                ControlCatLeitor controler = new ControlCatLeitor(this.getBaseContext());
                catLeitores = controler.Select(query);

                LoadListTab(R.layout.cat_leitores__list_fragment, R.id.listViewCatLeit, R.id.btn_CadastrarCatLei,
                        R.layout.cat_leitores_fragment, R.id.searchView_cat_leitores ,catLeitores);
                break;

            case 1:
                LoadCatTab(R.layout.cat_livros_fragment, R.id.btn_CatLivros, new CatLivro(),false);
                break;

            case 2:
                ArrayList<Abstract_Cadastro> clientes = new ArrayList<>();
                ControlCliente controlerCliente = new ControlCliente(this.getBaseContext());
                clientes = controlerCliente.Select(query);

                LoadListTab(R.layout.clientes__list_fragment, R.id.listviewClientes, R.id.btn_CadastrarClientes,
                        R.layout.clients_fragment, R.id.searchView_clientes, clientes);

                break;

            case 3:
                LoadCatTab(R.layout.livros_fragment, R.id.btn_Livros, new Livro(),false);
                break;
        }


    }

    private void LoadCatTab(int layoutID, int buttonID, final Abstract_Cadastro cadastro, final boolean isNew ){
        currentTabID = tabs.getSelectedTabPosition();
        f.removeAllViews();
        LayoutInflater.from(context).inflate(layoutID, f, true);

        b = findViewById(buttonID);

        if(!isNew){

            if(cadastro instanceof CatLeitor){
                EditText desCat = findViewById(R.id.txt_descCatLeit);
                EditText codCat = findViewById(R.id.txt_CodCatLeit);
                EditText maxDays = findViewById(R.id.txt_NumMaxCatLeit);

                desCat.setText(((CatLeitor)cadastro).getDescricao());
                codCat.setText(((CatLeitor)cadastro).getCod());
                maxDays.setText(String.valueOf(((CatLeitor)cadastro).getMaxDays()));

            }else if (cadastro instanceof Cliente){
                EditText nomeCli = findViewById(R.id.txt_NomeCliente);
                EditText enderecoCli = findViewById(R.id.txt_ClienteEndereco);
                EditText telefone = findViewById(R.id.txt_telefone);
                EditText email = findViewById(R.id.txt_ClienteEmail);

                nomeCli.setText(((Cliente)cadastro).getNome());
                enderecoCli.setText(((Cliente)cadastro).getEndereco());
                telefone.setText(((Cliente)cadastro).getTelefone());
                email.setText(((Cliente)cadastro).getEmail());
            }

        }

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                long result = -1;

                if(cadastro instanceof CatLeitor) {
                    EditText desCat = findViewById(R.id.txt_descCatLeit);
                    EditText codCat = findViewById(R.id.txt_CodCatLeit);
                    EditText maxDays = findViewById(R.id.txt_NumMaxCatLeit);

                    ((CatLeitor) cadastro).setDescricao(desCat.getText().toString());
                    ((CatLeitor) cadastro).setCod(codCat.getText().toString());
                    ((CatLeitor) cadastro).setMaxDays(Integer.valueOf(maxDays.getText().toString()));

                    if (!isNew)
                        result = new ControlCatLeitor(getApplicationContext()).Update((CatLeitor) cadastro);
                    else
                        result = new ControlCatLeitor(getApplicationContext()).Insert((CatLeitor) cadastro);

                }else if(cadastro instanceof Cliente){
                    EditText nomeCli = findViewById(R.id.txt_NomeCliente);
                    EditText enderecoCli = findViewById(R.id.txt_ClienteEndereco);
                    EditText telefone = findViewById(R.id.txt_telefone);
                    EditText email = findViewById(R.id.txt_ClienteEmail);

                    ((Cliente) cadastro).setNome(nomeCli.getText().toString());
                    ((Cliente) cadastro).setEndereco(enderecoCli.getText().toString());
                    ((Cliente) cadastro).setTelefone(telefone.getText().toString());
                    ((Cliente) cadastro).setEmail(email.getText().toString());

                    if (!isNew)
                        result = new ControlCliente(getApplicationContext()).Update((Cliente) cadastro);
                    else
                        result = new ControlCliente(getApplicationContext()).Insert((Cliente) cadastro);
                }

                if(result==-1)
                    Toast.makeText(context, "Ocorreu um erro ao cadastrar/atualizar o registro", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(context, "Registro cadastrado/atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }

                HandlerTabClick(tabs.getTabAt(tabs.getSelectedTabPosition()));
            }
        });

    }

    private void LoadListTab(final int layoutID, int viewID, final int buttonID, final int layoutInfoID,
                             int searchID, final List<Abstract_Cadastro> cadastro){

        f.removeAllViews();

        LayoutInflater.from(context).inflate(layoutID,f);

        ListView view = findViewById(viewID);

        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(context,  android.R.layout.simple_list_item_1, cadastro);

        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                LoadCatTab(layoutInfoID, buttonID, cadastro.get(position), position==0);
            }
        });

        SearchView simpleSearchView = (SearchView) findViewById(searchID); // inititate a search view

        // perform set on query text listener event
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HandlerTabClick(tabs.getTabAt(tabs.getSelectedTabPosition()),query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //onQueryTextSubmit(newText);
                return false;
            }
        });
    }
    public void onBackPressed(){
        if (currentTabID >= 0) {
            HandlerTabClick(tabs.getTabAt(currentTabID));
            currentTabID = -1;
        } else {
            super.onBackPressed();
        }
    }
}
