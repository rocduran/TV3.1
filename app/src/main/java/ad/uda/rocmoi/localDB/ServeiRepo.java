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

    public int insert(Servei servei) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Servei.KEY_ID, (servei.getId()));
        values.put(Servei.KEY_idTipus, (servei.getIdTipus()));
        values.put(Servei.KEY_descripcio, (servei.getDescripcio()));

        // Inserting Row
        long Servei_Id = db.insert(Servei.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) Servei_Id;
    }

    public void delete(int Servei_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Servei.TABLE, Servei.KEY_ID + "= ?", new String[]{String.valueOf(Servei_Id)});
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

    public ArrayList<HashMap<String, String>> getServeiList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Servei.KEY_ID + "," +
                Servei.KEY_idTipus + "," +
                Servei.KEY_descripcio + "," +
                " FROM " + Servei.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> ServeiList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> dossier = new HashMap<String, String>();
                dossier.put("id", cursor.getString(cursor.getColumnIndex(Servei.KEY_ID)));
                dossier.put("preu", cursor.getString(cursor.getColumnIndex(Servei.KEY_idTipus)));
                dossier.put("descripcio", cursor.getString(cursor.getColumnIndex(Servei.KEY_descripcio)));
                ServeiList.add(dossier);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ServeiList;

    }

    public Servei getServeiById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Servei.KEY_ID + "," +
                Servei.KEY_idTipus + "," +
                Servei.KEY_descripcio +
                " FROM " + Servei.TABLE
                + " WHERE " +
                Servei.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Servei Servei = new Servei();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                Servei.setId(cursor.getInt(cursor.getColumnIndex(Servei.KEY_ID)));
                Servei.setIdTipus(cursor.getInt(cursor.getColumnIndex(Servei.KEY_idTipus)));
                Servei.setDescripcio(cursor.getString(cursor.getColumnIndex(Servei.KEY_descripcio)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Servei;
    }
}
