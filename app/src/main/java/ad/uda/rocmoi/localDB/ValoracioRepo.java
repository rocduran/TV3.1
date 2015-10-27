package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Valoracio;

public class ValoracioRepo {
    private DBhelper dbHelper;

    public ValoracioRepo(Context context) {
        dbHelper = new DBhelper(context);
    }

    public int insert(Valoracio valoracio) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Valoracio.KEY_ID, valoracio.getId());
        values.put(Valoracio.KEY_idDossier, valoracio.getIdDossier());
        values.put(Valoracio.KEY_idServei, valoracio.getIdServei());
        values.put(Valoracio.KEY_idParam, valoracio.getIdParam());
        values.put(Valoracio.KEY_valor, valoracio.getValor());

        // Inserting Row
        long dossier_Id = db.insert(Dossier.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) dossier_Id;
    }

    public void delete(int valoracio_id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Valoracio.TABLE, Valoracio.KEY_ID + "= ?", new String[]{String.valueOf(valoracio_id)});
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

}
