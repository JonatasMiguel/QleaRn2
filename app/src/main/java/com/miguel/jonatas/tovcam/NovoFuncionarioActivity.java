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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NovoFuncionarioActivity extends AppCompatActivity {
    private Button btnCadastrarFuncionario;
    private Button btnCancelarFuncionario;
    private Funcionario novoFunc;
    private EditText editTextNomeFunc;
    private EditText editTextCPF;
    private EditText editTextCargo;
    private EditText editTextSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_funcionario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Funcionario");
        setSupportActionBar(toolbar);
        btnCadastrarFuncionario = (Button)findViewById(R.id.btnCadastrarFuncionario);
        btnCancelarFuncionario = (Button)findViewById(R.id.btnCancelarFuncionario);
        editTextCargo = (EditText)findViewById(R.id.editTextCargo);
        editTextCPF = (EditText)findViewById(R.id.editTextCPF);
        editTextNomeFunc = (EditText)findViewById(R.id.editTextNomeFunc);
        editTextSenha = (EditText)findViewById(R.id.editTextSenha);

        btnCadastrarFuncionario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //dispara login
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                novoFunc = new Funcionario();
                novoFunc.setCargo(editTextCargo.getText().toString());
                novoFunc.setCpf(editTextCPF.getText().toString());
                novoFunc.setNome(editTextNomeFunc.getText().toString());
                novoFunc.setSenha(editTextSenha.getText().toString());
                if(TextUtils.isEmpty(editTextCPF.getText().toString())||TextUtils.isEmpty(editTextNomeFunc.getText().toString())){
                    AlertDialog alertDialog2 = new AlertDialog.Builder(NovoFuncionarioActivity.this).create();
                    alertDialog2.setTitle("Falhou");
                    alertDialog2.setMessage("CPF e Nome são obrigatorios");
                    alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog2.show();

                }else{
                    try{
                        myRef.child("FUNCIONARIO").child(novoFunc.getCpf()).setValue(novoFunc);//insere novo funcionario no banco

                    }catch (DatabaseException e){
                        AlertDialog alertDialog2 = new AlertDialog.Builder(NovoFuncionarioActivity.this).create();
                        alertDialog2.setTitle("Falhou");
                        alertDialog2.setMessage("Erro ao conectar com o Banco de dados");
                        alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog2.show();
                        startActivity(new Intent(v.getContext(),MenuAdminActivity.class));
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(NovoFuncionarioActivity.this).create();
                    alertDialog.setTitle("Concluído");
                    alertDialog.setMessage("Funcionario adicionado com sucesso");
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
        btnCancelarFuncionario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MenuAdminActivity.class));

            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

}
