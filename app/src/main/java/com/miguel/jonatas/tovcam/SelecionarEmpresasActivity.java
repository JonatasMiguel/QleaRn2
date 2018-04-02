package com.miguel.jonatas.tovcam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SelecionarEmpresasActivity extends AppCompatActivity {
    private ArrayList<Empresa> listaEmpresas;
    private ListView lvEmpresasCadastradas;
    private  Empresa e;
    private String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_empresas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        e = new Empresa();
        final ProgressDialog carregando = new ProgressDialog(SelecionarEmpresasActivity.this);
        carregando.setMessage("Carregando lista de  empresas.");
        carregando.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        carregando.setCancelable(false);
        carregando.show();
        msg = new String("Carregando lista de empresas...");
        setSupportActionBar(toolbar);

        lvEmpresasCadastradas = (ListView)findViewById(R.id.lvEmpresasCadastradas);
        listaEmpresas = new ArrayList<Empresa>();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("EMPRESA");
        final ArrayAdapter<Empresa> adapterEmpresa = new ArrayAdapter<Empresa>(SelecionarEmpresasActivity.this,android.R.layout.simple_list_item_checked,listaEmpresas);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot child:it){
                    e = child.getValue(Empresa.class);//todo o objeto empresa
                    listaEmpresas.add(e);

                }
                lvEmpresasCadastradas.setAdapter(adapterEmpresa);
                msg = "Pronto";
                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
                carregando.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String message = "Server error. Refresh page";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                carregando.dismiss();
            }
        });
        lvEmpresasCadastradas.setAdapter(adapterEmpresa);
        lvEmpresasCadastradas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                i.putExtra("CNPJ",listaEmpresas.get(lvEmpresasCadastradas.getCheckedItemPosition()).getCnpj());
                setResult(1,i);
                finish();
            }
        });


    }


}
