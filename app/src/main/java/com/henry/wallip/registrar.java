package com.henry.wallip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class registrar extends AppCompatActivity {

    TextView ednombre;
    TextView ednumero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        ednumero = findViewById(R.id.edNombre);
        ednumero = findViewById(R.id.edNumero);
    }

}