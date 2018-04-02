package com.miguel.jonatas.tovcam;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import android.util.Log;
import android.app.AlertDialog;
import android.view.View;
import android.content.Intent;

import java.util.Random;

public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private Funcionario f;
    private String treinamentoNome;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private String[] parts;

    private QRTag qrtag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Intent profile = getIntent();
        f = new Funcionario();
        f = (Funcionario)profile.getSerializableExtra("FUNCIONARIO");
        treinamentoNome = new String();
        treinamentoNome = (String)profile.getSerializableExtra("treinamentoNome");
        mScannerView = new ZXingScannerView(this);

    }
    public void onClick(View v){

        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Do anything with result here :D
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        //alertDialog.show();
        String strName = result.getText().toString();
        //divide o codigo em dois pelo ":"
        //a segunda parte é nome do treinamento
        parts = strName.split(":");
        System.out.println(parts[1]);
        System.out.println(parts[0]);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("QRTAG");
        myRef.orderByChild("cod").equalTo(strName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                qrtag = new QRTag();
                Iterable<DataSnapshot> it = dataSnapshot.getChildren();
                System.out.println(it.toString());
                for(DataSnapshot child:it)
                    qrtag = child.getValue(QRTag.class);
                Random r = new Random();
                System.out.println(qrtag.getCod());
                System.out.println(it.toString());

                int n = r.nextInt(qrtag.getListaDeQuestoes().size());
                System.out.println("indice aleatorio:"+n);
                Questao q = new Questao();
                q = qrtag.getListaDeQuestoes().get(n);
                System.out.println(q.getCodigo());
                System.out.println(q.getPontos());
                System.out.println(q.getTipo());
                if(q.getTipo().equals("0")){
                    //pergunta do tipo binaria
                    Intent i = new Intent(QRActivity.this,PerguntaActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("questao",q);
                    Funcionario f =  new Funcionario();
                    f=(Funcionario)getIntent().getExtras().get("FUNCIONARIO");
                    b.putSerializable("FUNCIONARIO",f);
                    b.putSerializable("NOMETREINAMENTO",parts[1]);
                    i.putExtras(b);
                    startActivity(i);

                }else if(q.getTipo().equals("1")){
                    //multiplas escolhas
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                android.support.v7.app.AlertDialog alertDialog2 = new android.support.v7.app.AlertDialog.Builder(QRActivity.this).create();
                alertDialog2.setTitle("Falhou");
                alertDialog2.setMessage("Erro ao conectar com o Banco de dados");
                alertDialog2.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog2.show();
            }
        });




        //Resume scanning
        //mScannerView.resumeCameraPreview(this);
    }
}
