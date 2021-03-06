package com.henry.wallip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class editar_contacto extends AppCompatActivity {

    TextView destinario;
    TextView cnt;
    TextView mensaje;
    ImageButton vaciar;
    ImageView ver;
    TextView number;
    String ids,foto,ftt;
    ImageButton borrar,actualizar;
    DBHelper DB;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        destinario = findViewById(R.id.para);
        cnt = findViewById(R.id.edNombre);
        mensaje = findViewById(R.id.mensaje);
        number = findViewById(R.id.edNumeroA);
        borrar = findViewById(R.id.limpiar);
        actualizar = findViewById(R.id.guarda);
        DB = new DBHelper(this);

        RecibirSMS rcbitSMS = new RecibirSMS();

        foto = "local gallery";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Se eliminará de forma permanente\n¿ Eliminar ?").setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Boolean borrado = DB.deleteContacto(ids);
                                if(borrado==true){
                                    Toast.makeText(getApplicationContext(), "Eliminado correctamente.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Error al eliminar.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Eliminando contacto");
                alert.show();

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean editado = DB.updateContacto(ids,cnt.getText().toString(),number.getText().toString(),foto);
                if(editado==true){
                    Toast.makeText(getApplicationContext(), "Actualizado correctamente.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error al actualizar.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recibirDator();
    }
    private void recibirDator() {
        Bundle extras = getIntent().getExtras();
        ids = extras.getString("d0");
        String nm = extras.getString("d1");
        String nmr = extras.getString("d2");
        ftt = extras.getString("d3");



        cnt.setText(nm);
        number.setText(nmr);

        destinario.setText("Para: "+cnt.getText());
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

    public void Recibir(Context context, Intent intent){

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