package com.miguel.jonatas.tovcam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class PerguntaActivity extends AppCompatActivity {

    private Questao questao;
    private Funcionario funcionario;
    private String nomedotreinamento;
    private TextView textViewQuestaoTexto;
    private TextView textViewQuestaoTitulo;
    private Button buttonQuestaoCancela;
    private Button buttonQuestaoFalse;
    private Button buttonQuestaoTrue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        questao = (Questao) getIntent().getExtras().get("questao");
        funcionario =(Funcionario)getIntent().getExtras().get("FUNCIONARIO");
        nomedotreinamento = new String();
        nomedotreinamento = (String) getIntent().getExtras().get("NOMETREINAMENTO");
        textViewQuestaoTitulo = (TextView)findViewById(R.id.textViewTituloDaPerguntaME);
        textViewQuestaoTexto = (TextView) findViewById(R.id.textViewTextoDaPerguntaME);
        buttonQuestaoCancela = (Button) findViewById(R.id.buttonCancelarME);
        buttonQuestaoFalse = (Button) findViewById(R.id.buttonResponder);
        buttonQuestaoTrue = (Button) findViewById(R.id.buttonVerdadeiro);
        textViewQuestaoTitulo.setText(questao.getTitulo());
        textViewQuestaoTexto.setText(questao.getTexto());
        cadastraNoTreinamento();
        buttonQuestaoTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (acertou("true")){//sobe
                    //adiciona pontos ao jogador
                    //se jogador n pertence ao treinamento abre msg de confirmação

                    AlertDialog alertDialog4 = new AlertDialog.Builder(PerguntaActivity.this).create();
                    alertDialog4.setTitle("Certa Resposta");
                    alertDialog4.setMessage("Parabéns Resposta correta.");
                    alertDialog4.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    updateTreinamentoNoPlayer();
                                    updateFuncionario();
                                    Intent p = new Intent(PerguntaActivity.this,MainActivity.class);
                                    p.putExtra("FUNCIONARIO",funcionario);
                                    startActivity(p);
                                }
                            });
                    alertDialog4.show();


                }else{
                    AlertDialog alertDialog2 = new AlertDialog.Builder(PerguntaActivity.this).create();
                    alertDialog2.setTitle("Resposta Errada");
                    alertDialog2.setMessage("Infelizmente você errou a resposta. Tente novamente");
                    alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog2.show();
                    //menssagem de erro
                }
            }
        });
        buttonQuestaoFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (acertou("false")){
                    AlertDialog alertDialog4 = new AlertDialog.Builder(PerguntaActivity.this).create();
                    alertDialog4.setTitle("Certa Resposta");
                    alertDialog4.setMessage("Parabéns Resposta correta.");
                    alertDialog4.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    updateTreinamentoNoPlayer();
                                    updateFuncionario();
                                    Intent p = new Intent(PerguntaActivity.this,MainActivity.class);
                                    p.putExtra("FUNCIONARIO",funcionario);
                                    startActivity(p);
                                }
                            });
                    alertDialog4.show();
                }else{
                    AlertDialog alertDialog2 = new AlertDialog.Builder(PerguntaActivity.this).create();
                    alertDialog2.setTitle("Resposta Errada");
                    alertDialog2.setMessage("Infelizmente você errou a resposta. Tente novamente");
                    alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog2.show();
                    //menssagem de erro
                }
            }
        });





    }
    private void updateFuncionario(){
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference mr = db.getReference();
        try{
            mr.child("FUNCIONARIO").child(funcionario.getCpf()).setValue(funcionario);//insere funcionario modificado no banco

        }catch (DatabaseException e){
            AlertDialog alertDialog3 = new AlertDialog.Builder(PerguntaActivity.this).create();
            alertDialog3.setTitle("Falhou");
            alertDialog3.setMessage("Erro ao conectar com o Banco de dados");
            alertDialog3.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog3.show();
        }
    }

    private void updateTreinamentoNoPlayer(){
        funcionario.getTreinamentosRealizados().get(nomedotreinamento).setPontuacaoTotal(Integer.parseInt(questao.getPontos())+funcionario.getTreinamentosRealizados().get(nomedotreinamento).getPontuacaoTotal());
        System.out.println(">>>>>>>>>>>>>>>>>"+Integer.parseInt(questao.getPontos())+funcionario.getTreinamentosRealizados().get(nomedotreinamento).getPontuacaoTotal());

    }
    private void cadastraNoTreinamento(){

        if(funcionario.getTreinamentosRealizados()==null){
            //se não esta cadastrado em nenhum treinamento pergunta se quer cadastrar
            funcionario.setTreinamentosRealizados(new HashMap<String, TreinamentosRealizados>());
        }if(funcionario.getTreinamentosRealizados().get(nomedotreinamento)==null){
            //ja participa de outro treinamento
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            TreinamentosRealizados tr = new TreinamentosRealizados();
                            tr.setPontuacaoTotal(0);
                            tr.setCodTreinamento(nomedotreinamento);
                            funcionario.getTreinamentosRealizados().put(nomedotreinamento,tr);
                            dialog.dismiss();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            dialog.dismiss();
                            //volta a tela principal
                            Intent p = new Intent(PerguntaActivity.this,MainActivity.class);
                            p.putExtra("FUNCIONARIO",funcionario);
                            startActivity(p);
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ola, voce é novo por aqui");
            builder.setMessage("Quer fazer parte deste treinamento?").setPositiveButton("Sim", dialogClickListener).setNegativeButton("Não", dialogClickListener).show();


        }else{
            System.out.println("user ja possui registro nesse treinamento");
        }
    }
    private boolean acertou(String resposta){//true false
        if(resposta.equalsIgnoreCase(this.questao.getRespostaBinaria())){
            return true;
        }else return false;
    }

}
