package com.henry.wallip;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class registrar extends AppCompatActivity {

    EditText ednombre;
    EditText ednumero;
    ImageButton insertar;
    ImageButton ver;
    DBHelper DB;
    String id,nombre,numero,foto;
    ImageView fotoGallery;
    Uri path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        ednombre = findViewById(R.id.edNombre);
        ednumero = findViewById(R.id.edNumeroA);
        insertar = findViewById(R.id.guarda);
        ver = findViewById(R.id.limpiar);
        DB = new DBHelper(this);
        fotoGallery = (ImageView) findViewById(R.id.idImagen);


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
                    limpiar();
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

    public void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicación"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            path = data.getData();
            Toast.makeText(getApplicationContext(), ""+path, Toast.LENGTH_SHORT).show();
            fotoGallery.setImageURI(path);
        }
    }

    public void upandup(View view) {
        cargarImagen();
    }

    public void limpiar(){
        ednombre.setText("");
        ednumero.setText("");
    }
}