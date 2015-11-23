package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import ad.uda.rocmoi.pojos.Parametre;

/**
 * Created by sesiom on 26/10/15.
 */
public class ParametreRepo {
    private DBhelper dbHelper;

    public ParametreRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void insert(Parametre parametre) {

        if(getParametreById(parametre.getId()) == null){
            //Open connection to write data
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Parametre.KEY_ID, (parametre.getId()));
            values.put(Parametre.KEY_idTipus, (parametre.getIdTipus()));
            values.put(Parametre.KEY_descripcio, (parametre.getDescripcio()));

            // Inserting Row
            long parametre_Id = db.insert(Parametre.TABLE, null, values);
            db.close(); // Closing database connection
        }
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Parametre.TABLE, Parametre.KEY_ID + "= ?", new String[]{String.valueOf(id)});
        db.close(); // Closing database connection
    }

    public void update(Parametre parametre) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Parametre.KEY_ID, parametre.getId());
        values.put(Parametre.KEY_idTipus, parametre.getIdTipus());
        values.put(Parametre.KEY_descripcio, parametre.getDescripcio());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Parametre.TABLE, values, Parametre.KEY_ID + "= ?", new String[]{String.valueOf(parametre.getId())});
        db.close(); // Closing database connection
    }

    public ArrayList<Parametre> getParametreList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Parametre.KEY_ID + "," +
                Parametre.KEY_idTipus + "," +
                Parametre.KEY_descripcio +
                " FROM " + Parametre.TABLE;

        ArrayList<Parametre> parametreList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) do {
            Parametre parametre = new Parametre();
            parametre.setId(cursor.getInt(cursor.getColumnIndex(Parametre.KEY_ID)));
            parametre.setIdTipus(cursor.getInt(cursor.getColumnIndex(Parametre.KEY_idTipus)));
            parametre.setDescripcio(cursor.getString(cursor.getColumnIndex(Parametre.KEY_descripcio)));
            parametreList.add(parametre);
        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        return parametreList;

    }

    public Parametre getParametreById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Parametre.KEY_ID + "," +
                Parametre.KEY_idTipus + "," +
                Parametre.KEY_descripcio +
                " FROM " + Parametre.TABLE
                + " WHERE " +
                Parametre.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Parametre parametre = new Parametre();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            do {
                parametre.setId(cursor.getInt(cursor.getColumnIndex(Parametre.KEY_ID)));
                parametre.setIdTipus(cursor.getInt(cursor.getColumnIndex(Parametre.KEY_idTipus)));
                parametre.setDescripcio(cursor.getString(cursor.getColumnIndex(Parametre.KEY_descripcio)));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return parametre;
        } else{
            cursor.close();
            db.close();
            return null;
        }
    }

    public ArrayList<Parametre> getParametreByIdTipus(int idTipus) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Parametre.KEY_ID + "," +
                Parametre.KEY_idTipus + "," +
                Parametre.KEY_descripcio +
                " FROM " + Parametre.TABLE
                + " WHERE " +
                Parametre.KEY_idTipus + "=?";


        ArrayList<Parametre> parametres = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idTipus)});

        if (cursor.moveToFirst()) {
            do {
                Parametre parametre = new Parametre();

                parametre.setId(cursor.getInt(cursor.getColumnIndex(Parametre.KEY_ID)));
                parametre.setIdTipus(cursor.getInt(cursor.getColumnIndex(Parametre.KEY_idTipus)));
                parametre.setDescripcio(cursor.getString(cursor.getColumnIndex(Parametre.KEY_descripcio)));

                parametres.add(parametre);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return parametres;
    }
}
