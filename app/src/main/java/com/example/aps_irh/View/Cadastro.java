package com.example.aps_irh.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aps_irh.Control.ControlCatLeitor;
import com.example.aps_irh.Control.ControlCatLivro;
import com.example.aps_irh.Control.ControlCliente;
import com.example.aps_irh.Control.ControlLivro;
import com.example.aps_irh.Model.Abstract_Cadastro;
import com.example.aps_irh.Model.CatLeitor;
import com.example.aps_irh.Model.CatLivro;
import com.example.aps_irh.Model.Cliente;
import com.example.aps_irh.Model.Livro;
import com.example.aps_irh.R;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

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
                        R.layout.cat_leitores_fragment, R.id.searchView_cat_leitores ,catLeitores, query);
                break;

            case 1:
                ArrayList<Abstract_Cadastro> catLivro= new ArrayList<>();
                ControlCatLivro controlerCatLivro= new ControlCatLivro(this.getBaseContext());
                catLivro = controlerCatLivro.Select(query);

                LoadListTab(R.layout.cat_livros_list_fragment_fragment, R.id.listViewCatLivros, R.id.btn_CadastrarCatLivro,
                        R.layout.cat_livros_fragment, R.id.searchView_cat_livros, catLivro, query);

                break;

            case 2:
                ArrayList<Abstract_Cadastro> clientes = new ArrayList<>();
                ControlCliente controlerCliente = new ControlCliente(this.getBaseContext());

                try {
                    clientes = controlerCliente.Select(query);
                }
                catch (Exception ex){
                    Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
                }

                LoadListTab(R.layout.clientes__list_fragment, R.id.listviewClientes, R.id.btn_CadastrarClientes,
                        R.layout.clients_fragment, R.id.searchView_clientes, clientes, query);

                break;

            case 3:
                ArrayList<Abstract_Cadastro> livros = new ArrayList<>();
                ControlLivro controlLivro = new ControlLivro(this.getBaseContext());
                try {
                    livros  = controlLivro.Select(query);
                }catch (Exception ex){
                    Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
                }

                LoadListTab(R.layout.livro__list_fragement, R.id.listviewLivros, R.id.btn_Livros,
                        R.layout.livros_fragment, R.id.searchView_Livros ,livros, query);
                break;
        }


    }

    private void LoadListItemTab(int layoutID, int buttonID, final Abstract_Cadastro cadastro, final boolean isNew ){
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
                EditText codCliente = findViewById(R.id.txt_codCliente);
                EditText cpf = findViewById(R.id.txt_CPF);
                EditText nascimento = findViewById(R.id.txt_nascimento);
                Spinner spinner = findViewById(R.id.spinner_CatLeitor);

                ControlCatLeitor controlCatLeitor = new ControlCatLeitor(context);

                ArrayList<Abstract_Cadastro> list =  controlCatLeitor.Select("");

                final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(context, R.layout.support_simple_spinner_dropdown_item, list);

                int index = -1;

                for(int i = 0; i<list.size();i++){
                    if(1 == ((Cliente) cadastro).getCatLeitor().toString().compareToIgnoreCase(((CatLeitor)list.get(i)).toString())){
                        index = i+1;
                    }
                }

                spinner.setAdapter(adapter);
                spinner.setSelection(index);

                nomeCli.setText(((Cliente)cadastro).getNome());
                enderecoCli.setText(((Cliente)cadastro).getEndereco());
                telefone.setText(((Cliente)cadastro).getTelefone());
                email.setText(((Cliente)cadastro).getEmail());
                codCliente.setText(String.valueOf(((Cliente)cadastro).getCodCliente()));
                cpf.setText(((Cliente)cadastro).getCpf());

                Date date = ((Cliente) cadastro).getNascimento();

                int day = date.getDate();
                int month = date.getMonth() + 1;
                int year = date.getYear();

                nascimento.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

                codCliente.setEnabled(false);

            }else if (cadastro instanceof CatLivro){
                EditText desCat = findViewById(R.id.txt_descCatLivro);
                EditText codCat = findViewById(R.id.txt_CodCatLivro);
                EditText maxDays = findViewById(R.id.txt_NumMaxCatLivro);

                desCat.setText(((CatLivro)cadastro).getDescricao());
                codCat.setText(((CatLivro)cadastro).getCod());
                maxDays.setText(String.valueOf(((CatLivro)cadastro).getMaxDays()));
            }
            else if(cadastro instanceof Livro){
                EditText codLivro = findViewById(R.id.txt_codLivro);
                EditText ISBN = findViewById(R.id.txt_ISBN);
                EditText titulo = findViewById(R.id.txt_Titulo);
                EditText numEd = findViewById(R.id.txt_NumEd);
                Spinner catLivro= findViewById(R.id.spinner_catLivro);
                EditText autores = findViewById(R.id.txt_Autores);
                EditText paginas = findViewById(R.id.txt_paginas);
                EditText palavras = findViewById(R.id.txt_key);
                EditText datapub = findViewById(R.id.txt_DataPub);
                EditText editora = findViewById(R.id.txt_editora);

                ControlCatLivro controlCatLivro= new ControlCatLivro(context);

                ArrayList<Abstract_Cadastro> list =  controlCatLivro.Select("");

                final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(context, R.layout.support_simple_spinner_dropdown_item, list);

                int index = -1;

                for(int i = 0; i<list.size();i++){
                    if(1 == ((Livro) cadastro).getCatLivro().toString().compareToIgnoreCase(((CatLivro)list.get(i)).toString())){
                        index = i+1;
                    }
                }

                catLivro.setAdapter(adapter);
                catLivro.setSelection(index);

                codLivro.setText(((Livro) cadastro).getCod());
                ISBN.setText(((Livro) cadastro).getISBN());
                titulo.setText(((Livro) cadastro).getTitulo());
                numEd.setText(String.valueOf(((Livro) cadastro).getNumEdicao()));
                autores.setText(((Livro) cadastro).getAutores());
                paginas.setText(String.valueOf(((Livro) cadastro).getNumPaginas()));
                palavras.setText(((Livro) cadastro).getPalavrasChave());
                editora.setText(((Livro) cadastro).getEditora());
                Date date = ((Livro) cadastro).getDataPublicacao();

                int day = date.getDate();
                int month = date.getMonth() + 1;
                int year = date.getYear();

                datapub.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
            }

        }
        else{
            if(cadastro instanceof Cliente){
                Spinner spinner = findViewById(R.id.spinner_CatLeitor);
                EditText codCliente = findViewById(R.id.txt_codCliente);

                ControlCatLeitor controlCatLeitor = new ControlCatLeitor(context);

                ArrayList<Abstract_Cadastro> list =  controlCatLeitor.Select("");

                final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(context, R.layout.support_simple_spinner_dropdown_item, list);

                spinner.setAdapter(adapter);
                codCliente.setEnabled(false);

            }else if(cadastro instanceof Livro){
                Spinner spinner = findViewById(R.id.spinner_catLivro);

                ControlCatLivro controlCatLivro= new ControlCatLivro (context);

                ArrayList<Abstract_Cadastro> list =  controlCatLivro.Select("");

                final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(context, R.layout.support_simple_spinner_dropdown_item, list);

                spinner.setAdapter(adapter);
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

                    if((desCat.getText().toString()).isEmpty() ||
                        codCat.getText().toString().isEmpty() ||
                        maxDays.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Todos os campos são obrigatórios",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ((CatLeitor) cadastro).setDescricao(desCat.getText().toString());
                    ((CatLeitor) cadastro).setCod(codCat.getText().toString());
                    ((CatLeitor) cadastro).setMaxDays(Integer.valueOf(maxDays.getText().toString()));

                    if (!isNew)
                        result = new ControlCatLeitor(getApplicationContext()).Update((CatLeitor) cadastro);
                    else
                        result = new ControlCatLeitor(getApplicationContext()).Insert((CatLeitor) cadastro);

                }
                else if(cadastro instanceof Cliente){
                    EditText nomeCli = findViewById(R.id.txt_NomeCliente);
                    EditText enderecoCli = findViewById(R.id.txt_ClienteEndereco);
                    EditText telefone = findViewById(R.id.txt_telefone);
                    EditText email = findViewById(R.id.txt_ClienteEmail);
                    EditText cpf = findViewById(R.id.txt_CPF);
                    EditText nascimento= findViewById(R.id.txt_nascimento);
                    Spinner spinner = findViewById(R.id.spinner_CatLeitor);

                    CatLeitor catLeitor = (CatLeitor) (spinner.getSelectedItem());

                    ((Cliente) cadastro).setNome(nomeCli.getText().toString());
                    ((Cliente) cadastro).setEndereco(enderecoCli.getText().toString());
                    ((Cliente) cadastro).setTelefone(telefone.getText().toString());
                    ((Cliente) cadastro).setEmail(email.getText().toString());
                    ((Cliente) cadastro).setCatLeitor(catLeitor);
                    ((Cliente) cadastro).setCpf(cpf.getText().toString());

                    String date = nascimento.getText().toString();
                    if(date.length() < 8)
                    {
                        Toast.makeText(context,"Formato de date inválida. Formato aceito: dd/mm/aa",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        int day = Integer.valueOf(date.substring(0, date.indexOf("/")));
                        int month = Integer.valueOf(date.substring(date.indexOf("/")+1,date.indexOf("/")+3));
                        int year = Integer.valueOf(date.substring(date.indexOf("/")+4,date.indexOf("/")+6));

                        if(day<1 || day> 31||month<1 ||month>12)
                        {
                            Toast.makeText(context,"Valor do dia ou mês fora do range esperado",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ((Cliente) cadastro).setNascimento(new Date(year,month-1,day));

                    }catch (Exception ex){
                        Toast.makeText(context,"Formato de date inválida. Formato aceito: dd/mm/aa",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if((nomeCli.getText().toString()).isEmpty() ||
                            enderecoCli.getText().toString().isEmpty() ||
                            telefone.getText().toString().isEmpty() ||
                            email.getText().toString().isEmpty()
                    )
                    {
                        Toast.makeText(context, "Todos os campos são obrigatórios",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!isNew)
                        result = new ControlCliente(getApplicationContext()).Update((Cliente) cadastro);
                    else
                        result = new ControlCliente(getApplicationContext()).Insert((Cliente) cadastro);
                }
                else if(cadastro instanceof CatLivro){

                    EditText desCat = findViewById(R.id.txt_descCatLivro);
                    EditText codCat = findViewById(R.id.txt_CodCatLivro);
                    EditText maxDays = findViewById(R.id.txt_NumMaxCatLivro);

                    if((desCat.getText().toString()).isEmpty() ||
                            codCat.getText().toString().isEmpty() ||
                            maxDays.getText().toString().isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Todos os campos são obrigatórios",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ((CatLivro) cadastro).setDescricao(desCat.getText().toString());
                    ((CatLivro) cadastro).setCod(codCat.getText().toString());
                    ((CatLivro) cadastro).setMaxDays(Integer.valueOf(maxDays.getText().toString()));

                    if (!isNew)
                        result = new ControlCatLivro(getApplicationContext()).Update((CatLivro) cadastro);
                    else
                        result = new ControlCatLivro(getApplicationContext()).Insert((CatLivro) cadastro);

                }else if(cadastro instanceof  Livro){
                    EditText codLivro = findViewById(R.id.txt_codLivro);
                    EditText ISBN = findViewById(R.id.txt_ISBN);
                    EditText titulo = findViewById(R.id.txt_Titulo);
                    EditText numEd = findViewById(R.id.txt_NumEd);
                    Spinner catLivro= findViewById(R.id.spinner_catLivro);
                    EditText autores = findViewById(R.id.txt_Autores);
                    EditText paginas = findViewById(R.id.txt_paginas);
                    EditText palavras = findViewById(R.id.txt_key);
                    EditText datapub = findViewById(R.id.txt_DataPub);
                    EditText editora = findViewById(R.id.txt_editora);

                    CatLivro categoria= (CatLivro)(catLivro.getSelectedItem());


                    ((Livro) cadastro).setCod(codLivro.getText().toString());
                    ((Livro) cadastro).setISBN(ISBN.getText().toString());
                    ((Livro) cadastro).setTitulo(titulo.getText().toString());
                    ((Livro) cadastro).setNumEdicao(Integer.valueOf(numEd.getText().toString()));
                    ((Livro) cadastro).setAutores(autores.getText().toString());
                    ((Livro) cadastro).setNumPaginas(Integer.valueOf(paginas.getText().toString()));
                    ((Livro) cadastro).setPalavrasChave(palavras.getText().toString());
                    ((Livro) cadastro).setCatLivro(categoria);
                    ((Livro) cadastro).setEditora(editora.getText().toString());

                    String date = datapub.getText().toString();
                    if(date.length() < 8)
                    {
                        Toast.makeText(context,"Formato de date inválida. Formato aceito: dd/mm/aa",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        int day = Integer.valueOf(date.substring(0, date.indexOf("/")));
                        int month = Integer.valueOf(date.substring(date.indexOf("/")+1,date.indexOf("/")+3));
                        int year = Integer.valueOf(date.substring(date.indexOf("/")+4,date.indexOf("/")+6));

                        if(day<1 || day> 31||month<1 ||month>12)
                        {
                            Toast.makeText(context,"Valor do dia ou mês fora do range esperado",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ((Livro) cadastro).setDataPublicacao(new Date(year,month-1,day));

                    }catch (Exception ex){
                        Toast.makeText(context,"Formato de date inválida. Formato aceito: dd/mm/aa",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(codLivro.getText().toString().isEmpty() ||
                            ISBN.getText().toString().isEmpty() ||
                            titulo.getText().toString().isEmpty() ||
                            numEd.getText().toString().isEmpty() ||
                            autores.getText().toString().isEmpty() ||
                            paginas.getText().toString().isEmpty() ||
                            palavras.getText().toString().isEmpty() ||
                            datapub.getText().toString().isEmpty() ||
                            editora.getText().toString().isEmpty()
                    )
                    {
                        Toast.makeText(context, "Todos os campos são obrigatórios",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!isNew)
                        result = new ControlLivro(getApplicationContext()).Update((Livro) cadastro);
                    else
                        result = new ControlLivro(getApplicationContext()).Insert((Livro) cadastro);

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
                             int searchID, final List<Abstract_Cadastro> cadastro, final String query){

        f.removeAllViews();

        LayoutInflater.from(context).inflate(layoutID,f);

        ListView view = findViewById(viewID);

        final ArrayAdapter<Abstract_Cadastro> adapter = new ArrayAdapter<Abstract_Cadastro>(context,  android.R.layout.simple_list_item_1, cadastro);

        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                LoadListItemTab(layoutInfoID, buttonID, cadastro.get(position), (position==0 && query == null));
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
