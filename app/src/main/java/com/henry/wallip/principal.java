package com.henry.wallip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class principal extends AppCompatActivity {

    ImageView imagen;
    int contador = 0;
    String numero_llamar;
    TextView cantidad;

    final ArrayList<listaElements> elements = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private listAdaptador mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        public void run() {
            metodo_timmer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        imagen = (ImageView) findViewById(R.id.usuarioImg);

        runnable.run();

        //Toast.makeText(getApplicationContext(), ""+contador, Toast.LENGTH_SHORT).show();

        elements.add(new listaElements("Henry","58757294"));
        elements.add(new listaElements("James","58709462"));
        elements.add(new listaElements("Eliezer","81388181"));
        elements.add(new listaElements("Faruck","58726730"));

        mRecyclerView = findViewById(R.id.listaRecycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new listAdaptador(elements);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new listAdaptador.OnItemClickListener() {
            @Override
            public void onPlay(int position) {
                numero_llamar = elements.get(position).getNumero();
                Toast.makeText(getApplicationContext(), ""+numero_llamar, Toast.LENGTH_SHORT).show();
                llamar(numero_llamar);
            }

        });

        cantidad = findViewById(R.id.idCantidad);
        int can = elements.size();
        cantidad.setText("Cant.\n"+can);
    }

    public void metodo_timmer(){
        contador ++;
        if (contador == 3){
            imagen.setImageResource(R.drawable.usera);
        }
        if (contador == 6){
            imagen.setImageResource(R.drawable.user);
            contador = 0;
        }
        handler.postDelayed(runnable, 1000);
    }

    public void registrar(View view){
        Intent i = new Intent(this, registrar.class);
        startActivity(i);
    }

    /*public void llamar(View view) {
        llamar();
    }*/

    public void llamar(String num){
        try {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+num)));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "No se puede llamar : "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}