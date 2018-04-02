package com.miguel.jonatas.tovcam;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnSuper;
    private EditText editTextCPFFuncionario;
    private EditText editTextTreinamentoID;
    private Funcionario f;
    private Treinamento t;
    private Boolean queryCPF;
    private Boolean queryTreinamentoNoome;
    private Intent profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queryCPF = false;
        queryTreinamentoNoome = false;
        btnLogin = (Button)findViewById(R.id.btnlogin);
        btnSuper = (Button)findViewById(R.id.btnAdminMode);
        //    btnSuper.setVisibility(View.INVISIBLE);
        ///  btnSuper.setEnabled(false);
        editTextCPFFuncionario = (EditText)findViewById(R.id.editTextCPFFuncionario);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef2 = database.getReference();
        {//SEÇÃO DE APP DOS FUNCIONARIOS
            btnSuper.setEnabled(false);
            btnSuper.setVisibility(View.INVISIBLE);
            //  editTextTreinamentoID.setVisibility(View.INVISIBLE);
        }
        editTextCPFFuncionario.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    editTextCPFFuncionario.setText("");
                }
                return false;
            }
        });







        btnSuper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //dispara login


                startActivity(new Intent(v.getContext(),MenuAdminActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //procura por aquele treinamento
                final ProgressDialog carregando = new ProgressDialog(LoginActivity.this);
                carregando.setMessage("Logando...");
                carregando.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                carregando.setCancelable(false);
                carregando.show();

                profile = new Intent(LoginActivity.this,MainActivity.class);
                myRef2.child("FUNCIONARIO").orderByChild("cpf").equalTo(editTextCPFFuncionario.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try{
                            f=new Funcionario();

                            Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                            for(DataSnapshot child:it)
                                f = child.getValue(Funcionario.class);
                            //Toast.makeText(LoginActivity.this,f.getNome()+" Logando...",Toast.LENGTH_SHORT).show();
                            if(f.getCpf()==null){
                                carregando.dismiss();
                                AlertDialog alertDialog2 = new AlertDialog.Builder(LoginActivity.this).create();
                                alertDialog2.setTitle("Falhou");
                                alertDialog2.setMessage("CPF não encontrado.");
                                alertDialog2.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog2.show();
                            }else{
                                // profile = new Intent(LoginActivity.this,MainActivity.class);
                                profile.putExtra("FUNCIONARIO",f);
                                //String treinamentoNome = editTextTreinamentoID.getText().toString();
                                //profile.putExtra("treinamentoNome",treinamentoNome);
                                carregando.dismiss();
                                startActivity(profile);
                            }

                        }catch (Exception e){
                            carregando.dismiss();
                            AlertDialog alertDialog2 = new AlertDialog.Builder(LoginActivity.this).create();
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
                        Toast.makeText(LoginActivity.this," Conexão cancelada. ",Toast.LENGTH_SHORT).show();
                    }
                });
                //startActivity(profile);

            }
        });
        //procura por aquele cpf
        // carregando.dismiss();








    }
}

 /*   public boolean existeFuncionario(String Nome){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("FUNCIONARIOS");






        return false;
    }*/

