package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;

public class ActivitatDossierRepo {
    private DBhelper dbHelper;

    public ActivitatDossierRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public int insert(ActivitatDossier activitatDossier) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ActivitatDossier.KEY_ID, activitatDossier.getId());
        values.put(ActivitatDossier.KEY_idDossier, activitatDossier.getIdDossier());
        values.put(ActivitatDossier.KEY_idServei, activitatDossier.getIdServei());

        // Inserting Row
        long activitatDossier_Id = db.insert(ActivitatDossier.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) activitatDossier_Id;
    }

    public void delete(int activitatDossier_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(ActivitatDossier.TABLE, ActivitatDossier.KEY_ID + "= ?", new String[]{String.valueOf(activitatDossier_Id)});
        db.close(); // Closing database connection
    }

    public void update(ActivitatDossier activitatDossier) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ActivitatDossier.KEY_ID, activitatDossier.getId());
        values.put(ActivitatDossier.KEY_idDossier, activitatDossier.getIdDossier());
        values.put(ActivitatDossier.KEY_idServei, activitatDossier.getIdServei());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(ActivitatDossier.TABLE, values, ActivitatDossier.KEY_ID + "= ?", new String[]{String.valueOf(activitatDossier.getId())});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getActivitatDossierList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                ActivitatDossier.KEY_ID + "," +
                ActivitatDossier.KEY_idDossier + "," +
                ActivitatDossier.KEY_idServei + "," +
                " FROM " + ActivitatDossier.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> activitatDossierList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> activitatDossier = new HashMap<String, String>();
                activitatDossier.put("id", cursor.getString(cursor.getColumnIndex(ActivitatDossier.KEY_ID)));
                activitatDossier.put("idDossier", cursor.getString(cursor.getColumnIndex(ActivitatDossier.KEY_idDossier)));
                activitatDossier.put("idServei", cursor.getString(cursor.getColumnIndex(ActivitatDossier.KEY_idServei)));
                activitatDossierList.add(activitatDossier);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return activitatDossierList;

    }

    public ActivitatDossier getActivitatDossierById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                ActivitatDossier.KEY_ID + "," +
                ActivitatDossier.KEY_idDossier + "," +
                ActivitatDossier.KEY_idServei + "," +
                " FROM " + ActivitatDossier.TABLE
                + " WHERE " +
                ActivitatDossier.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount = 0;
        ActivitatDossier activitatDossier = new ActivitatDossier();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});

        if (cursor.moveToFirst()) {
            do {
                activitatDossier.setId(cursor.getInt(cursor.getColumnIndex(ActivitatDossier.KEY_ID)));
                activitatDossier.setIdDossier(cursor.getInt(cursor.getColumnIndex(ActivitatDossier.KEY_idDossier)));
                activitatDossier.setIdServei(cursor.getInt(cursor.getColumnIndex(ActivitatDossier.KEY_idServei)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return activitatDossier;
    }

    public ArrayList<ActivitatDossier> getActivitatDossierByIdDossier(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                ActivitatDossier.KEY_ID + "," +
                ActivitatDossier.KEY_idDossier + "," +
                ActivitatDossier.KEY_idServei +
                " FROM " + ActivitatDossier.TABLE
                + " WHERE " +
                ActivitatDossier.KEY_idDossier + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        ArrayList<ActivitatDossier> activitatsDossier = new ArrayList<ActivitatDossier>();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                ActivitatDossier activitatDossier = new ActivitatDossier();

                activitatDossier.setId(cursor.getInt(cursor.getColumnIndex(ActivitatDossier.KEY_ID)));
                activitatDossier.setIdDossier(cursor.getInt(cursor.getColumnIndex(ActivitatDossier.KEY_idDossier)));
                activitatDossier.setIdServei(cursor.getInt(cursor.getColumnIndex(ActivitatDossier.KEY_idServei)));

                activitatsDossier.add(activitatDossier);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return activitatsDossier;
    }
}
