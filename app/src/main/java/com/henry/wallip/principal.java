package com.henry.wallip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class principal extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ImageView imagen;
    int contador = 0;
    String numero_llamar;
    TextView cantidad;
    String name,idd;
    DBHelper DB;
    SearchView buscar;

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


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        imagen = (ImageView) findViewById(R.id.usuarioImg);
        DB = new DBHelper(this);
        buscar = findViewById(R.id.srBuscar);



        runnable.run();

        Cursor result = DB.obtenerDatos();
        if(result.getCount()==0){
            Toast.makeText(getApplicationContext(),"No hay contactos registrados", Toast.LENGTH_SHORT).show();
            return;
        }
        while (result.moveToNext()){
            elements.add(new listaElements(""+result.getString(0),""+result.getString(1), ""+result.getString(2), ""+result.getString(3)));
        }

        mRecyclerView = findViewById(R.id.listaRecycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new listAdaptador(elements);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new listAdaptador.OnItemClickListener() {
            @Override
            public void onPlay(int position) {
                idd = elements.get(position).getID();
                numero_llamar = elements.get(position).getNumero();
                name = elements.get(position).getNombre();
                pasar(idd,name,numero_llamar);
            }
        });

        cantidad = findViewById(R.id.idCantidad);
        int can = elements.size();
        cantidad.setText("Cant.\n"+can);

        buscar.setOnQueryTextListener(this);
    }

    private void pasar(String id,String nom,String num) {

        Intent i = new Intent(this, editar_contacto.class);
        i.putExtra("d0",id);
        i.putExtra("d1",nom);
        i.putExtra("d2",num);

        startActivity(i);

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

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mAdapter.filtrado(s);
        return false;
    }
}