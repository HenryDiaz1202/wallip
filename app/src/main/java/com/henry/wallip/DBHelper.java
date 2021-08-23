package com.henry.wallip;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"Contactosdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE contactosdata(id TEXT PRIMARY KEY,nombre TEXT, numero TEXT, foto TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS contactosdata");
    }

    public Boolean insertarContacto(String id,String nombre,String numero, String foto){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id",id);
        contentValues.put("nombre",nombre);
        contentValues.put("numero",numero);
        contentValues.put("foto",foto);

        long resultados = DB.insert("contactosdata",null,contentValues);
        if(resultados == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateContacto(String id,String nombre,String numero, String foto){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",nombre);
        contentValues.put("numero",numero);
        contentValues.put("foto",foto);
        Cursor cursor = DB.rawQuery("SELECT * FROM contactosdata WHERE id=?",  new String[]{id});
        if(cursor.getCount()>0) {
            long resultados = DB.update("contactosdata", contentValues, "id=?", new String[]{id});
            if (resultados == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Boolean deleteContacto(String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM contactosdata WHERE id=?",  new String[]{id});
        if(cursor.getCount()>0) {
            long resultados = DB.delete("contactosdata", "id=?", new String[]{id});
            if (resultados == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor obtenerDatos(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM contactosdata ORDER BY nombre", null);
        return  cursor;
    }

}
