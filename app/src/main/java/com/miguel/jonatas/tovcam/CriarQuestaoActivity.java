package com.miguel.jonatas.tovcam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriarQuestaoActivity extends AppCompatActivity {
/*    private EditText editTextNovaQuestaoCod;
    private EditText editTextNovaQuestaoTitulo;
    private EditText editTextNovaQuestaoPontos;
    private EditText editTextNovaQuestaoTexto;
    private RadioButton radioButtonNovaQuestaoTrue;
    private RadioButton radioButtonNovaQuestaoFalse;
    private Button buttonNovaQuestaoCadastrar;
    private Button buttonNovaQuestaoCancela;
    private Questao questao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_questao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nova Questão");
        editTextNovaQuestaoCod = (EditText)findViewById(R.id.editTextNovaQuestaoCod);
        editTextNovaQuestaoPontos = (EditText)findViewById(R.id.editTextNovaQuestaoPontos);
        editTextNovaQuestaoTexto = (EditText)findViewById(R.id.editTextNovaQuestaoTexto);
        editTextNovaQuestaoTitulo = (EditText)findViewById(R.id.editTextNovaQuestaoTitulo);
        radioButtonNovaQuestaoFalse = (RadioButton)findViewById(R.id.radioButtonNovaQuestaoFalse);
        radioButtonNovaQuestaoTrue = (RadioButton)findViewById(R.id.radioButtonNovaQuestaoTrue);
        buttonNovaQuestaoCadastrar = (Button)findViewById(R.id.buttonNovaQuestaoCadastrar);
        buttonNovaQuestaoCancela = (Button)findViewById(R.id.buttonNovaQuestaoCancela);

        buttonNovaQuestaoCadastrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(TextUtils.isEmpty(editTextNovaQuestaoCod.getText().toString())||TextUtils.isEmpty(editTextNovaQuestaoTitulo.getText().toString())||TextUtils.isEmpty(editTextNovaQuestaoPontos.getText().toString())||TextUtils.isEmpty(editTextNovaQuestaoTexto.getText().toString())){
                    AlertDialog alertDialog2 = new AlertDialog.Builder(CriarQuestaoActivity.this).create();
                    alertDialog2.setTitle("Falhou");
                    alertDialog2.setMessage("Todos os campos são obrigatorios'");
                    alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog2.show();
                }else{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    questao = new Questao();
                    questao.setCodigo(editTextNovaQuestaoCod.getText().toString());
                    questao.setTitulo(editTextNovaQuestaoTitulo.getText().toString());
                    questao.setPontos(editTextNovaQuestaoPontos.getText().toString());
                    questao.setTexto(editTextNovaQuestaoTexto.getText().toString());
                    if(radioButtonNovaQuestaoTrue.isChecked()){
                        questao.setResposta("True");
                    }else{
                        questao.setResposta("False");
                    }
                    try{
                        myRef.child("QUESTOES").child(questao.getCodigo()).setValue(questao);//insere novo funcionario no banco

                    }catch (DatabaseException e){
                        AlertDialog alertDialog2 = new AlertDialog.Builder(CriarQuestaoActivity.this).create();
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

                    AlertDialog alertDialog = new AlertDialog.Builder(CriarQuestaoActivity.this).create();
                    alertDialog.setTitle("Concluído");
                    alertDialog.setMessage("Questao adicionado com sucesso");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    startActivity(new Intent(v.getContext(),MenuAdminActivity.class));
                }

            }
        });
        buttonNovaQuestaoCancela.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MenuAdminActivity.class));

            }
        });
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
*/
}
