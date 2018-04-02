package com.miguel.jonatas.tovcam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MenuAdminActivity extends AppCompatActivity {
    private Button btnAddfuncionario;
    private Button btnAddEmpresa;
    private Button btnAddQuestao;
    private Button btnAddTreinamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar recursos");
        setSupportActionBar(toolbar);
        btnAddfuncionario = (Button)findViewById(R.id.btnAddfuncionario);
        btnAddfuncionario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(),NovoFuncionarioActivity.class));
            }
        });
        btnAddEmpresa = (Button)findViewById(R.id.btnAddEmpresa);
        btnAddEmpresa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(),NovaEmpresa.class));
            }
        });
        btnAddQuestao = (Button)findViewById(R.id.btnAddQuestao);
        btnAddQuestao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),CriarQuestaoActivity.class));


            }
        });

        btnAddTreinamento = (Button)findViewById(R.id.btnAddTreinamento);
        btnAddTreinamento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //dispara login
                startActivity(new Intent(v.getContext(),NovoTreinamentoActivity.class));

            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
