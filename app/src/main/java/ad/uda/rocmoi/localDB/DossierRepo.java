package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import ad.uda.rocmoi.activities.EnquestaActivity;
import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Servei;

public class DossierRepo {
    private DBhelper dbHelper;

    public DossierRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void insert(Dossier dossier) {

        if(getDossierById(dossier.getId()) == null){
            //Open connection to write data
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(Dossier.KEY_ID, dossier.getId());
            values.put(Dossier.KEY_preu, dossier.getPreu());
            values.put(Dossier.KEY_descripcio, dossier.getDescripcio());

            // Inserting Row
            long dossier_Id = db.insert(Dossier.TABLE, null, values);
            db.close(); // Closing database connection
        }

    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Dossier.TABLE, Dossier.KEY_ID + "= ?", new String[]{String.valueOf(id)});
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

    public ArrayList<Dossier> getDossierList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Dossier.KEY_ID + "," +
                Dossier.KEY_preu + "," +
                Dossier.KEY_descripcio +
                " FROM " + Dossier.TABLE;

        ArrayList<Dossier> dossierList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Dossier dossier = new Dossier();
                dossier.setId(cursor.getInt(cursor.getColumnIndex(Dossier.KEY_ID)));
                dossier.setPreu(cursor.getInt(cursor.getColumnIndex(Dossier.KEY_preu)));
                dossier.setDescripcio(cursor.getString(cursor.getColumnIndex(Dossier.KEY_descripcio)));
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

        Dossier dossier = new Dossier();

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                dossier.setId(cursor.getInt(cursor.getColumnIndex(Dossier.KEY_ID)));
                dossier.setPreu(cursor.getInt(cursor.getColumnIndex(Dossier.KEY_preu)));
                dossier.setDescripcio(cursor.getString(cursor.getColumnIndex(Dossier.KEY_descripcio)));
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return dossier;
        } else{
            cursor.close();
            db.close();
            return null;
        }


    }

    public Dossier loadServices(Dossier dossier, Context context) {

        ActivitatDossierRepo activitatDossierRepo = new ActivitatDossierRepo(context);
        ServeiRepo serveiRepo = new ServeiRepo(context);

        Log.d("MMM", "DOSSIER REPO DOSSIER ID : " + dossier.getId());

        ArrayList<ActivitatDossier> activitats = activitatDossierRepo.getActivitatDossierByIdDossier(dossier.getId());

        Log.d("MMM","DOSSIER REPO ACTIVITATS SIZE: "+activitats.size());
        ArrayList<Servei> serveis = new ArrayList<>();
        for(int i = 0; i < activitats.size(); i++) serveis.add(serveiRepo.getServeiById(activitats.get(i).getIdServei()));

        for (int i = 0; i < serveis.size(); i++){
            if(serveis.get(i).getIdTipus() == 1) dossier.setGuia(serveis.get(i));
            if(serveis.get(i).getIdTipus() == 2) dossier.setHotel(serveis.get(i));
            if(serveis.get(i).getIdTipus() == 3) dossier.setActivitat(serveis.get(i));
        }

        ParametreRepo parametreRepo = new ParametreRepo(context);


        dossier.getGuia().setParametres(parametreRepo.getParametreByIdTipus(1));
        dossier.getHotel().setParametres(parametreRepo.getParametreByIdTipus(2));
        dossier.getActivitat().setParametres(parametreRepo.getParametreByIdTipus(3));

        return dossier;

    }
}
