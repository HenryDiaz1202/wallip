package com.henry.wallip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class principal extends AppCompatActivity {

    ImageView imagen;
    int contador = 0;

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

        elements.add(new listaElements("Miguel","+505 58757294"));
        elements.add(new listaElements("Henry","+505 58757294"));
        elements.add(new listaElements("Anielka","+505 58757294"));

        mRecyclerView = findViewById(R.id.listaRecycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new listAdaptador(elements);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new listAdaptador.OnItemClickListener() {
            @Override
            public void onPlay(int position) {
                String palabra = elements.get(position).getNombre();
                Toast.makeText(getApplicationContext(), ""+palabra, Toast.LENGTH_SHORT).show();
            }

        });

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

}