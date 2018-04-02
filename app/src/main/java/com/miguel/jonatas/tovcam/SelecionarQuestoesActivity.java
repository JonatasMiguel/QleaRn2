package com.miguel.jonatas.tovcam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelecionarQuestoesActivity extends AppCompatActivity {
    private ListView lvSelecionarQuestões;
    private ArrayList<Questao> listaQuestoes;
    private Questao q;
    private String msg;
    private Button btnSelecionarQuestoesOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_questoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        q = new Questao();
        msg = new String("Carregando lista de Questões...");
        final ProgressDialog carregando = new ProgressDialog(SelecionarQuestoesActivity.this);
        carregando.setMessage("Carregando Questão");
        carregando.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        carregando.setCancelable(false);
        carregando.show();
        lvSelecionarQuestões = (ListView)findViewById(R.id.lvSelecionarQuestoes);
        btnSelecionarQuestoesOK = (Button)findViewById(R.id.btnSelecionarQuestoesOK);
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        listaQuestoes = new ArrayList<Questao>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("QUESTOES");
        final ArrayAdapter<Questao> adapterQuestoes = new ArrayAdapter<Questao>(SelecionarQuestoesActivity.this,android.R.layout.simple_list_item_multiple_choice,listaQuestoes);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                for(DataSnapshot child:it){
                    q = child.getValue(Questao.class);//todo o objeto empresa
                    listaQuestoes.add(q);

                }
               lvSelecionarQuestões.setAdapter(adapterQuestoes);
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
        //lvSelecionarQuestões.setAdapter(adapterQuestoes);
        btnSelecionarQuestoesOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ArrayList<String> questoesSelecionadas = new ArrayList<String>();
                String aux = new String();
                Integer PontuacaoTotal = new Integer(0);

                for(int count=0;count<lvSelecionarQuestões.getCount();count++){
                    if(lvSelecionarQuestões.isItemChecked(count)){
                        questoesSelecionadas.add(listaQuestoes.get(count).getCodigo());
                        PontuacaoTotal+=Integer.parseInt(listaQuestoes.get(count).getPontos());
                    }
                }
                i.putExtra("PONTUACAOTOTAL",PontuacaoTotal);
                i.putExtra("COD",questoesSelecionadas);
                setResult(1,i);
                finish();




            }
        });


    }

}
