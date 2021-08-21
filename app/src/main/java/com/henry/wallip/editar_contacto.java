package com.henry.wallip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class editar_contacto extends AppCompatActivity {

    TextView destinario;
    TextView cnt;
    TextView mensaje;
    ImageButton vaciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        destinario = findViewById(R.id.para);
        cnt = findViewById(R.id.contactosm);
        mensaje = findViewById(R.id.mensaje);

        destinario.setText("Para: "+cnt.getText());
    }

    public void borrar(View view){
        mensaje.setText("");
    }
}