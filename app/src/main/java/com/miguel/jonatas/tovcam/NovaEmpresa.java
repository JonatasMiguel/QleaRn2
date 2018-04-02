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

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NovaEmpresa extends AppCompatActivity {
/*    private EditText editTextNomeNovaEmpresa;
    private EditText editTextCnpjNovaEmpresa;
    private EditText editTextEnderecoNovaEmpresa;
    private Button btnCadastrarNovaEmpresa;
    private Button btnCancelarNovaEmpresa;
    private Empresa novaEmpresa;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_empresa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nova Empresa");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextCnpjNovaEmpresa = (EditText)findViewById(R.id.editTextCnpjNovaEmpresa);
        editTextEnderecoNovaEmpresa = (EditText)findViewById(R.id.editTextEnderecoNovaEmpresa);
        editTextNomeNovaEmpresa = (EditText)findViewById(R.id.editTextNomeNovaEmpresa);
        btnCadastrarNovaEmpresa = (Button)findViewById(R.id.btnCadastrarNovaEmpresa);
        btnCancelarNovaEmpresa = (Button)findViewById(R.id.btnCancelarNovaEmpresa);
        btnCadastrarNovaEmpresa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextCnpjNovaEmpresa.getText().toString())||TextUtils.isEmpty(editTextNomeNovaEmpresa.getText().toString())){
                    AlertDialog alertDialog4 = new AlertDialog.Builder(NovaEmpresa.this).create();
                    alertDialog4.setTitle("Falhou");
                    alertDialog4.setMessage("Nome da empresa e CNPJ são obrigatorios");
                    alertDialog4.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog4.show();
                }else{
                    novaEmpresa=new Empresa();
                    novaEmpresa.setNome(editTextNomeNovaEmpresa.getText().toString());
                    novaEmpresa.setCnpj(editTextCnpjNovaEmpresa.getText().toString());
                    novaEmpresa.setLocalização(editTextEnderecoNovaEmpresa.getText().toString());
                    try{
                        myRef.child("EMPRESA").child(novaEmpresa.getCnpj()).setValue(novaEmpresa);//insere novo funcionario no banco

                    }catch (DatabaseException e){
                        AlertDialog alertDialog2 = new AlertDialog.Builder(NovaEmpresa.this).create();
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

                    AlertDialog alertDialog = new AlertDialog.Builder(NovaEmpresa.this).create();
                    alertDialog.setTitle("Concluído");
                    alertDialog.setMessage("Empresa adicionada com sucesso");
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
        btnCancelarNovaEmpresa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MenuAdminActivity.class));

            }
        });



    }
*/
}
