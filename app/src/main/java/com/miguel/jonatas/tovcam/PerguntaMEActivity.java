package com.miguel.jonatas.tovcam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class PerguntaMEActivity extends AppCompatActivity {
    private Questao questao;
    private Funcionario funcionario;
    private String nomedotreinamento;

    private TextView textViewQuestaoTexto;
    private TextView textViewQuestaoTitulo;
    private Button btResponde;
    private Button btCancela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_me);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewQuestaoTitulo = (TextView)findViewById(R.id.textViewTituloDaPerguntaME);
        textViewQuestaoTexto = (TextView) findViewById(R.id.textViewTextoDaPerguntaME);
        btCancela = (Button)findViewById(R.id.buttonCancelarME);
        btResponde =(Button)findViewById(R.id.buttonResponder);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
