package com.miguel.jonatas.tovcam;

        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.method.ScrollingMovementMethod;
        import android.view.View;
        import android.content.Intent;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseException;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class QuestaoActivity extends AppCompatActivity {
    private TextView textViewQuestaoTexto;
    private TextView textViewQuestaoTitulo;
    private Questao q;
    private String codQuestaoNomeTreinamento;//codigoquestão:nometreinamento
    private Button buttonQuestaoCancela;
    private Button buttonQuestaoNO;
    private Button buttonQuestaoYES;
    private Funcionario f;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String[] parts;
    private String treinamentoNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent profile1 = getIntent();
        final ProgressDialog carregando = new ProgressDialog(QuestaoActivity.this);
        carregando.setMessage("Carregando Questão");
        carregando.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        carregando.setCancelable(false);
        carregando.show();
        f = new Funcionario();
        f = (Funcionario)profile1.getSerializableExtra("FUNCIONARIO");
        treinamentoNome = (String)profile1.getSerializableExtra("treinamentoNome");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("QUESTOES");
        buttonQuestaoNO = (Button)findViewById(R.id.buttonQuestaoNO);
       // buttonQuestaoNO.setClickable(false);
        buttonQuestaoNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviaResposta("False");
            }
        });
        buttonQuestaoYES = (Button)findViewById(R.id.buttonQuestaoYES);
       // buttonQuestaoYES.setClickable(false);
        buttonQuestaoYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviaResposta("True");
            }
        });
        buttonQuestaoCancela = (Button)findViewById(R.id.buttonQuestaoCancela);
        buttonQuestaoCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(QuestaoActivity.this,MainActivity.class);
                p.putExtra("FUNCIONARIO",f);

                p.putExtra("treinamentoNome",treinamentoNome);

                startActivity(p);
            }
        });
        buttonQuestaoCancela.setEnabled(false);
        buttonQuestaoCancela.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                codQuestaoNomeTreinamento= null;
            } else {//pega o codigo e nome do treinamento qr code deve conter(codigoquestão:nometreinamento)
                codQuestaoNomeTreinamento= extras.getString("qrcode");
            }
        } else {
            codQuestaoNomeTreinamento= (String) savedInstanceState.getSerializable("qrcode");
        }
        textViewQuestaoTexto = (TextView)findViewById(R.id.textViewQuestaoTexto);
        textViewQuestaoTexto.setMovementMethod(new ScrollingMovementMethod());
        textViewQuestaoTitulo =(TextView)findViewById(R.id.textViewQuestaoTitulo);
        textViewQuestaoTitulo.setText("");
        textViewQuestaoTexto.setText("");
        //questao.setText(codQuestaoNomeTreinamento);
        parts = codQuestaoNomeTreinamento.split(":");//pega so o codigo
        //verificar se é um codigo no formato valido(codQuestao:nomedotreinamento)
        //verificar codigo do treinamento aqui>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>importante
        myRef.orderByChild("codigo").equalTo(parts[0]).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    q=new Questao();
                    Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                    for(DataSnapshot child:it)
                        q = child.getValue(Questao.class);
                    if(q.getCodigo()==null){
                        carregando.dismiss();
                        AlertDialog alertDialog2 = new AlertDialog.Builder(QuestaoActivity.this).create();
                        alertDialog2.setTitle("Falhou");
                        alertDialog2.setMessage("Esta questão não esta em nosso banco de dados.");
                        alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog2.show();

                    }else{
                        textViewQuestaoTitulo.setText(q.getTitulo());
                        textViewQuestaoTexto.setText(q.getTexto());
                        carregando.dismiss();
                        /*

                        Intent profile = new Intent(LoginActivity.this,MainActivity.class);
                        profile.putExtra("FUNCIONARIO",f);
                        startActivity(profile);*/
                    }

                }catch (Exception e){
                    carregando.dismiss();
                    AlertDialog alertDialog2 = new AlertDialog.Builder(QuestaoActivity.this).create();
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                carregando.dismiss();
                AlertDialog alertDialog2 = new AlertDialog.Builder(QuestaoActivity.this).create();
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
        });
        /*buttonQuestaoCancela.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent profile = new Intent(QuestaoActivity.this,MainActivity.class);
                 //profile.putExtra("FUNCIONARIO",f);
                 startActivity(profile);
             }
        });*/
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void EnviaResposta(String respostaDoUsuario){
       /*
        if(respostaDoUsuario.equalsIgnoreCase(q.getResposta())){ //se resposta dada for certa
            //resposta certa

            //apartir daqui entra a justificativa

            if(f.getTreinamentosRealizados()==null){
                f.setTreinamentosRealizados(new HashMap<String, TreinamentosRealizados>());
            }
            if(f.getTreinamentosRealizados().get(parts[1])==null){//se aquele treinamento ainda não foi cadastrado
                TreinamentosRealizados tr = new TreinamentosRealizados();
                tr.setCodTreinamento(parts[1]);//seta o codigo do treinamento >>>>>verificar codigo do treinamento no oncreate
                tr.setPontuacaoTotal(Integer.parseInt(q.getPontos()));
                Map<String,String> questaoresposta = new HashMap<String, String>();
                questaoresposta.put(q.getCodigo(),respostaDoUsuario);
                tr.setQuestaoresposta(questaoresposta);
                Map<String,TreinamentosRealizados> listatr;
                if(f.getTreinamentosRealizados()==null){
                    listatr = new HashMap<String, TreinamentosRealizados>();
                }else{
                    listatr = f.getTreinamentosRealizados();
                }
                listatr.put(parts[1],tr);
                f.setTreinamentosRealizados(listatr);
            }else{//se tiver treinamentos cadastrados ler e adicionar
                Map<String,TreinamentosRealizados> listatr= new HashMap<String, TreinamentosRealizados>();
                listatr =  f.getTreinamentosRealizados();
                TreinamentosRealizados tr = new TreinamentosRealizados();
                tr = listatr.get(parts[1]);//erro aqui
                Map<String,String> questaoresposta = new HashMap<String, String>();
                questaoresposta = tr.getQuestaoresposta();
                if(questaoresposta.get(q.getCodigo())==null) {//se questao ainda nao foi respondida
                    try{
                        tr.setPontuacaoTotal(tr.getPontuacaoTotal()+Integer.parseInt(q.getPontos()));//soma aos pontos do usuario relacionado ao  treinamento em questao os pontos da questao respondida
                    }catch (Exception e){
                        Toast.makeText(QuestaoActivity.this," 1- Falha em setar pontuação. ",Toast.LENGTH_SHORT).show();
                    }
                    try{
                        questaoresposta.put(q.getCodigo(),respostaDoUsuario);
                    }catch (Exception e){
                        Toast.makeText(QuestaoActivity.this," 2- falha no put questao resposta. ",Toast.LENGTH_SHORT).show();
                    }
                    try{
                        tr.setQuestaoresposta(questaoresposta);
                    }catch (Exception e){
                        Toast.makeText(QuestaoActivity.this," 3- falha no set do tr. ",Toast.LENGTH_SHORT).show();
                    }
                    try{
                        listatr.put(parts[1],tr);
                    }catch (Exception e){
                        Toast.makeText(QuestaoActivity.this," 4- falha no put na listatr. ",Toast.LENGTH_SHORT).show();
                    }
                    try{
                        f.setTreinamentosRealizados(listatr);
                    }catch (Exception e){
                        Toast.makeText(QuestaoActivity.this," 5- falha no set da lista tr. ",Toast.LENGTH_SHORT).show();
                    }
                    AlertDialog alertDialog4 = new AlertDialog.Builder(QuestaoActivity.this).create();
                    alertDialog4.setTitle("Certa Resposta");
                    alertDialog4.setMessage("Parabéns por acertar a pergunta, agora justifique apontando para o codigo que mais justifica sua resposta.");
                    alertDialog4.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent p = new Intent(QuestaoActivity.this,MainActivity.class);
                                    p.putExtra("FUNCIONARIO",f);
                                    p.putExtra("treinamentoNome",treinamentoNome);
                                    startActivity(p);
                                }
                            });
                    alertDialog4.show();



                }else{//questao ja respondida
                    AlertDialog alertDialog3 = new AlertDialog.Builder(QuestaoActivity.this).create();
                    alertDialog3.setTitle("Questao já respondida");
                    alertDialog3.setMessage("Voce ja respondeu esta questão, avance para a proxima...");
                    alertDialog3.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog3.show();
                }
            }
            //inserir no banco de dados firebase
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference mr = database.getReference();
            try{
                mr.child("FUNCIONARIO").child(f.getCpf()).setValue(f);//insere funcionario modificado no banco

            }catch (DatabaseException e){
                AlertDialog alertDialog3 = new AlertDialog.Builder(QuestaoActivity.this).create();
                alertDialog3.setTitle("Falhou");
                alertDialog3.setMessage("Erro ao conectar com o Banco de dados");
                alertDialog3.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog3.show();

                //startActivity(new Intent(v.getContext(),MenuAdminActivity.class));
            }

        }else{//se resposta do usuario for errada
            //resposta errada
            AlertDialog alertDialog2 = new AlertDialog.Builder(QuestaoActivity.this).create();
            alertDialog2.setTitle("Resposta Errada");
            alertDialog2.setMessage("Infelizmente você errou a resposta. Tente novamente");
            alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog2.show();
            //talvez um texto explicando

        }*/


    }
}
