package com.miguel.jonatas.tovcam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NovoTreinamentoActivity extends AppCompatActivity {
    private EditText editTextNovoTreinamentoNome;
    private EditText editTextNovoTreinamentoDescricao;
    private Button btnNovoTreinamentoSelecionarEmpresa;
    private Button btnNovoTreinamentoSelecionarQuestoes;
    private Button btnNovoTreinamentoCadastrar;
    private Button btnNovoTreinamentoCancelar;
    private Treinamento treinamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_treinamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        treinamento = new Treinamento();
        btnNovoTreinamentoCadastrar = (Button)findViewById(R.id.btnNovoTreinamentoCadastrar);
        btnNovoTreinamentoCancelar = (Button)findViewById(R.id.btnNovoTreinamentoCancelar);
        btnNovoTreinamentoSelecionarEmpresa = (Button)findViewById(R.id.btnNovoTreinamentoSelecionarEmpresa);
        btnNovoTreinamentoSelecionarQuestoes = (Button)findViewById(R.id.btnNovoTreinamentoSelecionarQuestoes);
        editTextNovoTreinamentoDescricao = (EditText)findViewById(R.id.editTextNovoTreinamentoDescricao);
        editTextNovoTreinamentoNome = (EditText)findViewById(R.id.editTextNovoTreinamentoNome);
        final Intent intentSelecionarEmpresa = new Intent(this,SelecionarEmpresasActivity.class);
        final Intent intentSelecionarQuestoes = new Intent(this,SelecionarQuestoesActivity.class);
        btnNovoTreinamentoSelecionarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentSelecionarEmpresa,1);}}

        );
        btnNovoTreinamentoSelecionarQuestoes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivityForResult(intentSelecionarQuestoes,2);
            }
        });
        btnNovoTreinamentoCancelar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnNovoTreinamentoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treinamento.setNome(editTextNovoTreinamentoNome.getText().toString());
                treinamento.setDescricao(editTextNovoTreinamentoDescricao.getText().toString());
                if(!TextUtils.isEmpty(editTextNovoTreinamentoNome.getText().toString()) || treinamento.getCnpjdaEmpresa()!=null){//nome é obrigatorio
                    try{
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference();
                        myRef.child("TREINAMENTOS").child(treinamento.getNome()+":"+treinamento.getCnpjdaEmpresa()).setValue(treinamento);

                    }catch (Exception e){
                        AlertDialog alertDialog2 = new AlertDialog.Builder(NovoTreinamentoActivity.this).create();
                        alertDialog2.setTitle("Falhou");
                        alertDialog2.setMessage("Erro ao conectar com o Banco de dados");
                        alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog2.show();
                    }
                    AlertDialog alertDialog = new AlertDialog.Builder(NovoTreinamentoActivity.this).create();
                    alertDialog.setTitle("Concluído");
                    alertDialog.setMessage("Treinamento adicionado com sucesso");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(NovoTreinamentoActivity.this).create();
                    alertDialog.setTitle("Erro na inserção");
                    alertDialog.setMessage("Treinamento não possui empresa ou nome");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }
    protected void onActivityResult(int codtela,int resultado,Intent intent){
        if(codtela == 1){
            try{
                Bundle params = intent.getExtras();
                if(params!=null){
                    treinamento.setCnpjdaEmpresa(params.getString("CNPJ"));
                    Toast.makeText(this,"CNPJ: "+ treinamento.getCnpjdaEmpresa(),Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                //não foi selecionado nenhuma empresa
                Toast.makeText(this,"Não foi selecionada nenhuma empresa.",Toast.LENGTH_SHORT).show();
            }
        }else if (codtela == 2){
            try {
                Bundle params = intent.getExtras();
                if (params != null) {
                    ArrayList<String> listadasquestoes = new ArrayList<String>();
                    listadasquestoes = params.getStringArrayList("COD");
                    for(String s : listadasquestoes){
                        Toast.makeText(this,"codigo selecionado: "+s,Toast.LENGTH_SHORT).show();
                    }
                    treinamento.setCodigos(listadasquestoes);
                    treinamento.setPontuacaoTotal(params.getInt("PONTUACAOTOTAL"));

                }
            }catch (Exception e){
                Toast.makeText(this,"Não foi selecionada nenhuma questão.",Toast.LENGTH_SHORT).show();
                //não foi selecionado nenhuma questao
            }
        }

    }

}
