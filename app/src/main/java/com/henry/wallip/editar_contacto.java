package com.henry.wallip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class editar_contacto extends AppCompatActivity {

    TextView destinario;
    TextView cnt;
    TextView mensaje;
    ImageButton vaciar;
    TextView number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        destinario = findViewById(R.id.para);
        cnt = findViewById(R.id.edNombre);
        mensaje = findViewById(R.id.mensaje);
        number = findViewById(R.id.edNumero);

        destinario.setText("Para: "+cnt.getText());

        recibirDator();
    }

    private void recibirDator() {
        Bundle extras = getIntent().getExtras();
        String nm = extras.getString("d1");
        String nmr = extras.getString("d2");

        cnt.setText(nm);
        number.setText(nmr);
    }

    public void borrar(View view){
        mensaje.setText("");
    }
    public void borrar1(){
        mensaje.setText("");
    }

    public void enviar(View view){
        String persona = number.getText().toString();
        String ms = mensaje.getText().toString();

        SMS(persona,ms);
    }

    public void SMS(String numero, String mensaje){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero,null,mensaje,null,null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado correctamente", Toast.LENGTH_SHORT).show();
            borrar1();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void llamar(View view){
        String persona = number.getText().toString();
        try {
            Toast.makeText(getApplicationContext(), ""+persona, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+persona)));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "No se puede llamar : "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}