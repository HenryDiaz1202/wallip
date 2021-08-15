package com.henry.wallip;

public class listaElements {


public  String Nombre;
public  String Numero;

public listaElements(String nombre, String numero){
    Nombre = nombre;
    Numero = numero;
}

public void changeText(String text){ Nombre = text; }

public String getNombre(){ return Nombre; }
public String getNumero(){ return Numero; }

}
