package com.miguel.jonatas.tovcam;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button btnqreader;
    private Button btnRanking;
    private Button btnTreinamentosRealizados;
    private Funcionario f;
   // private Treinamento t;
    private Integer total;
    private TextView textViewNomeFunc;
    private TextView textViewPontos;
    private TextView textViewNomeTreinamento;
    private TextView textViewCargo;
 //   private String treinamentoNome;
    private RatingBar ratingBarStarsFuncionario;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent profile = getIntent();
        ratingBarStarsFuncionario = (RatingBar)findViewById(R.id.ratingBarStarsFuncionario);
        f = new Funcionario();
        f = (Funcionario)profile.getSerializableExtra("FUNCIONARIO");
      //  treinamentoNome = new String();
    //    treinamentoNome = (String)profile.getSerializableExtra("treinamentoNome");
        //t = (Treinamento)profile.getSerializableExtra("TREINAMENTO");
        //total = t.getPontuacaoTotal();
        //textViewNomeTreinamento = (TextView)findViewById(R.id.textViewNomeTreinamento);
       // textViewNomeTreinamento.setText(treinamentoNome);
        textViewNomeFunc = (TextView) findViewById(R.id.textViewNomeFunc);
        textViewNomeFunc.setText(f.getNome());
        textViewCargo = (TextView) findViewById(R.id.textViewCargo);
        textViewPontos = (TextView)findViewById(R.id.textViewPontos);
        ratingBarStarsFuncionario=(RatingBar)findViewById(R.id.ratingBarStarsFuncionario);
        ratingBarStarsFuncionario.setRating(0);
        ratingBarStarsFuncionario.setIsIndicator(true);
        LayerDrawable stars = (LayerDrawable) ratingBarStarsFuncionario.getProgressDrawable();
        stars.setColorFilter(Color.parseColor("#F4BE0E"), PorterDuff.Mode.SRC_ATOP);
        try{
            textViewPontos.setText(pontuacaoTotal()+" PONTOS");
            textViewCargo.setText(f.getCargo());
           // ratingBarStarsFuncionario.setRating(5*f.getTreinamentosRealizados().get(treinamentoNome).getPontuacaoTotal()/total);

        }catch (Exception e){
            textViewPontos.setText("0 PONTOS");
        }

        btnqreader = (Button)findViewById(R.id.btnQReader);
        btnqreader.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //dispara login
                Intent profile = new Intent(MainActivity.this,QRActivity.class);
                profile.putExtra("FUNCIONARIO",f);
              //profile.putExtra("treinamentoNome",treinamentoNome);
                startActivity(profile);
               //startActivity(new Intent(v.getContext(),QRActivity.class));
            }
        });
        btnRanking = (Button)findViewById(R.id.btnRanking);
        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,RankingActivity.class);
                i.putExtra("FUNCIONARIO",f);
                startActivity(i);

            }
        });
        btnTreinamentosRealizados = (Button)findViewById(R.id.btnTreinamentosRealizados);
        btnTreinamentosRealizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,TreinamentosCompletosActivity.class);
                i.putExtra("FUNCIONARIO",f);
                startActivity(i);
            }
        });

    }

    private int pontuacaoTotal(){
        int totaldepontos=0;
        Set<String> chaves = f.getTreinamentosRealizados().keySet();
        for (String chave : chaves)
        {
            if(chave != null)
                totaldepontos+=f.getTreinamentosRealizados().get(chave).getPontuacaoTotal();
        }
        return totaldepontos;
    }

}
