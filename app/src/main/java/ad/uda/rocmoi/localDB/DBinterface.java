package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Parametre;
import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.pojos.Valoracio;

public class DBinterface {
    private DBhelper dBhelper;
    private static SQLiteDatabase database;
    private static final String STRING_TYPE = "text";
    private static final String INT_TYPE = "integer";

    //Constructor
    // Creant una instància cap a la BD
    public DBinterface(Context context) {
        dBhelper = new DBhelper(context);
        openDB();
    }

    public void openDB(){
        database = dBhelper.getWritableDatabase();
    }

    //Tancar la BD
    public void closeDB() {
        dBhelper.close();
    }

    /**
     *
     * Tots els inserts per a cada taula de la nostra BD en local
     */
    public static void insert(Dossier dossier) {
        //Contenidor per preparar dades per inserció
        ContentValues values = new ContentValues();
        //Preparant body y author (Mapping)
        values.put(Dossier.KEY_preu, dossier.getPreu());
        values.put(Dossier.KEY_descripcio, dossier.getDescripcio());
        //Inserir a la BD
        database.insert(Dossier.TABLE, null, values);
    }

    public void insert(Servei servei) {
        //Contenidor per preparar dades per inserció
        ContentValues values = new ContentValues();
        //Preparant body y author (Mapping)
        values.put(Servei.KEY_idTipus, servei.getIdTipus());
        values.put(Servei.KEY_descripcio, servei.getDescripcio());
        //Inserir a la BD
        database.insert(Servei.TABLE, null, values);
    }

    public void insert(Parametre parametre) {
        //Contenidor per preparar dades per inserció
        ContentValues values = new ContentValues();
        //Preparant body y author (Mapping)
        values.put(Parametre.KEY_idTipus, parametre.getIdTipus());
        values.put(Parametre.KEY_descripcio, parametre.getDescripcio());
        //Inserir a la BD
        database.insert(Parametre.TABLE, null, values);
    }

    public void insert(ActivitatDossier activitatDossier) {
        //Contenidor per preparar dades per inserció
        ContentValues values = new ContentValues();
        //Preparant body y author (Mapping)
        values.put(ActivitatDossier.KEY_idDossier, activitatDossier.getIdDossier());
        values.put(ActivitatDossier.KEY_idServei, activitatDossier.getIdServei());
        //Inserir a la BD
        database.insert(ActivitatDossier.TABLE, null, values);
    }

    public void insert(Valoracio valoracio) {
        //Contenidor per preparar dades per inserció
        ContentValues values = new ContentValues();
        //Preparant body y author (Mapping)
        values.put(Valoracio.KEY_idDossier, valoracio.getIdDossier());
        values.put(Valoracio.KEY_idServei, valoracio.getIdServei());
        values.put(Valoracio.KEY_idParam, valoracio.getIdParam());
        values.put(Valoracio.KEY_valor, valoracio.getValor());
        //Inserir a la BD
        database.insert(Valoracio.TABLE, null, values);
    }


}
