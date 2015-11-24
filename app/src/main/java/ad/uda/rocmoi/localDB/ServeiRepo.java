package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.pojos.Servei;

/**
 * Created by sesiom on 26/10/15.
 */
public class ServeiRepo {
    private DBhelper dbHelper;

    public ServeiRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void insert(Servei servei) {


        if(getServeiById(servei.getId()) == null){
            //Open connection to write data
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Servei.KEY_ID, (servei.getId()));
            values.put(Servei.KEY_idTipus, (servei.getIdTipus()));
            values.put(Servei.KEY_descripcio, (servei.getDescripcio()));

            // Inserting Row
            long Servei_Id = db.insert(Servei.TABLE, null, values);
            db.close(); // Closing database connection
        }
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Servei.TABLE, Servei.KEY_ID + "= ?", new String[]{String.valueOf(id)});
        db.close(); // Closing database connection
    }

    public void update(Servei servei) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Servei.KEY_ID, servei.getId());
        values.put(Servei.KEY_idTipus, servei.getIdTipus());
        values.put(Servei.KEY_descripcio, servei.getDescripcio());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Servei.TABLE, values, Servei.KEY_ID + "= ?", new String[]{String.valueOf(servei.getId())});
        db.close(); // Closing database connection
    }

    public ArrayList<Servei> getServeiList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Servei.KEY_ID + "," +
                Servei.KEY_idTipus + "," +
                Servei.KEY_descripcio +
                " FROM " + Servei.TABLE;

        //Student student = new Student();
        ArrayList<Servei> serveiList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) do {
            Servei servei = new Servei();
            servei.setId(cursor.getInt(cursor.getColumnIndex(Servei.KEY_ID)));
            servei.setIdTipus(cursor.getInt(cursor.getColumnIndex(Servei.KEY_idTipus)));
            servei.setDescripcio(cursor.getString(cursor.getColumnIndex(Servei.KEY_descripcio)));
            serveiList.add(servei);

        } while (cursor.moveToNext());

        cursor.close();
        db.close();
        return serveiList;

    }

    public Servei getServeiById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Servei.KEY_ID + "," +
                Servei.KEY_idTipus + "," +
                Servei.KEY_descripcio +
                " FROM " + Servei.TABLE
                + " WHERE " +
                Servei.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        Servei servei = new Servei();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                servei.setId(cursor.getInt(cursor.getColumnIndex(Servei.KEY_ID)));
                servei.setIdTipus(cursor.getInt(cursor.getColumnIndex(Servei.KEY_idTipus)));
                servei.setDescripcio(cursor.getString(cursor.getColumnIndex(Servei.KEY_descripcio)));

            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return servei;
        } else {
            cursor.close();
            db.close();
            return null;
        }
    }
}
