package com.henry.wallip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class registrar extends AppCompatActivity {

    EditText ednombre;
    EditText ednumero;
    ImageButton insertar;
    ImageButton ver;
    DBHelper DB;
    String id,nombre,numero,foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        ednombre = findViewById(R.id.edNombre);
        ednumero = findViewById(R.id.edNumeroA);
        insertar = findViewById(R.id.guarda);
        ver = findViewById(R.id.limpiar);
        DB = new DBHelper(this);



        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = ednombre.getText().toString();
                numero = ednumero.getText().toString();
                foto = "local gallery";
                id = nombre.substring(0,3)+numero.substring(0,4);

                Boolean inserted = DB.insertarContacto(id,nombre,numero,foto);

                if(inserted==true){
                    Toast.makeText(getApplicationContext(), "Insertado correctamente.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error al insertar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ednombre.setText("");
                ednumero.setText("");
            }
        });
    }

}