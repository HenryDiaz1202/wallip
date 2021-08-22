package com.henry.wallip;

public class listaElements {


public  String ID;
public  String Numero;
public  String Nombre;
public  String Foto;

public listaElements(String id, String nombre,String numero, String foto){
    ID = id;
    Nombre = nombre;
    Numero = numero;
    Foto = foto;
}

public void changeText(String text){ Nombre = text; }

public String getID(){return ID;}
public String getNombre(){ return Nombre; }
public String getNumero(){ return Numero; }
public String getFoto(){return Foto;}

}
