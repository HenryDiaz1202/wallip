package com.henry.wallip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Toast;

public class registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }
    public String nume= "84493351";

    public void llamar(){
        try {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+nume)));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "No se puede llamar : "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    public void llamar(View view) {
        llamar();
    }
}