package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.pojos.Valoracio;

public class ValoracioRepo {
    private DBhelper dbHelper;

    public ValoracioRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void insert(Valoracio valoracio) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Valoracio.KEY_idDossier, valoracio.getIdDossier());
        values.put(Valoracio.KEY_idServei, valoracio.getIdServei());
        values.put(Valoracio.KEY_idParam, valoracio.getIdParam());
        values.put(Valoracio.KEY_valor, valoracio.getValor());

        // Inserting Row
        db.insert(Valoracio.TABLE, null, values);
        db.close(); // Closing database connection;
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Valoracio.TABLE, Valoracio.KEY_ID + "= ?", new String[]{String.valueOf(id)});
        db.close(); // Closing database connection
    }

    public void update(Valoracio valoracio) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Valoracio.KEY_ID, valoracio.getId());
        values.put(Valoracio.KEY_idDossier, valoracio.getIdDossier());
        values.put(Valoracio.KEY_idServei, valoracio.getIdServei());
        values.put(Valoracio.KEY_idParam, valoracio.getIdParam());
        values.put(Valoracio.KEY_valor, valoracio.getValor());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Valoracio.TABLE, values, Valoracio.KEY_ID + "= ?", new String[]{String.valueOf(valoracio.getId())});
        db.close(); // Closing database connection
    }

    public ArrayList<Valoracio> getValoracioList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Valoracio.KEY_ID + "," +
                Valoracio.KEY_idDossier + "," +
                Valoracio.KEY_idServei +","+
                Valoracio.KEY_idParam + ","+
                Valoracio.KEY_valor +
                " FROM " + Valoracio.TABLE;

        //Student student = new Student();
        ArrayList<Valoracio> valoracioList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) do {
            Valoracio valoracio = new Valoracio();
            valoracio.setId(cursor.getInt(cursor.getColumnIndex(Valoracio.KEY_ID)));
            valoracio.setIdDossier(cursor.getInt(cursor.getColumnIndex(Valoracio.KEY_idDossier)));
            valoracio.setIdServei(cursor.getInt(cursor.getColumnIndex(Valoracio.KEY_idServei)));
            valoracio.setIdParam(cursor.getInt(cursor.getColumnIndex(Valoracio.KEY_idParam)));
            valoracio.setValor(cursor.getInt(cursor.getColumnIndex(Valoracio.KEY_valor)));
            valoracioList.add(valoracio);

        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        return valoracioList;
    }

}
