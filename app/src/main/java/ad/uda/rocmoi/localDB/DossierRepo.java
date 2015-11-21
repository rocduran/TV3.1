package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import ad.uda.rocmoi.pojos.Dossier;

public class DossierRepo {
    private DBhelper dbHelper;

    public DossierRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public int insert(Dossier dossier) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Dossier.KEY_ID, dossier.getId());
        values.put(Dossier.KEY_preu, dossier.getPreu());
        values.put(Dossier.KEY_descripcio, dossier.getDescripcio());

        // Inserting Row
        long dossier_Id = db.insert(Dossier.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) dossier_Id;
    }

    public void delete(int dossier_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Dossier.TABLE, Dossier.KEY_ID + "= ?", new String[]{String.valueOf(dossier_Id)});
        db.close(); // Closing database connection
    }

    public void update(Dossier dossier) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Dossier.KEY_ID, dossier.getId());
        values.put(Dossier.KEY_preu, dossier.getPreu());
        values.put(Dossier.KEY_descripcio, dossier.getDescripcio());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Dossier.TABLE, values, Dossier.KEY_ID + "= ?", new String[]{String.valueOf(dossier.getId())});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getDossierList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Dossier.KEY_ID + "," +
                Dossier.KEY_preu + "," +
                Dossier.KEY_descripcio + "," +
                " FROM " + Dossier.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> dossierList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> dossier = new HashMap<String, String>();
                dossier.put("id", cursor.getString(cursor.getColumnIndex(Dossier.KEY_ID)));
                dossier.put("preu", cursor.getString(cursor.getColumnIndex(Dossier.KEY_preu)));
                dossier.put("descripcio", cursor.getString(cursor.getColumnIndex(Dossier.KEY_descripcio)));
                dossierList.add(dossier);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dossierList;

    }

    public Dossier getDossierById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Dossier.KEY_ID + "," +
                Dossier.KEY_preu + "," +
                Dossier.KEY_descripcio +
                " FROM " + Dossier.TABLE
                + " WHERE " +
                Dossier.KEY_ID + "=?" ;// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        Dossier dossier = new Dossier();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                dossier.setId(cursor.getInt(cursor.getColumnIndex(Dossier.KEY_ID)));
                dossier.setPreu(cursor.getInt(cursor.getColumnIndex(Dossier.KEY_preu)));
                dossier.setDescripcio(cursor.getString(cursor.getColumnIndex(Dossier.KEY_descripcio)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dossier;
    }
}
