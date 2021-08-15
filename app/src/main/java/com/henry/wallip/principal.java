package com.henry.wallip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class principal extends AppCompatActivity {

    ImageView imagen;
    int contador = 0;

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