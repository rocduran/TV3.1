package ad.uda.rocmoi.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Parametre;
import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.pojos.Valoracio;

public class DBhelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "moro.db";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_DOSSIER = "CREATE TABLE " + Dossier.TABLE  + "("
                + Dossier.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Dossier.KEY_preu + " INTEGER, "
                + Dossier.KEY_descripcio + " TEXT)";


        String CREATE_TABLE_SERVEI = "CREATE TABLE " + Servei.TABLE + "("
                + Servei.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Servei.KEY_idTipus + " INTEGER, "
                + Servei.KEY_descripcio + " TEXT)";

        String CREATE_TABLE_PARAMETRES = "CREATE TABLE " + Parametre.TABLE + "("
                + Parametre.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Parametre.KEY_idTipus + " INTEGER, "
                + Parametre.KEY_descripcio + " TEXT)";

        String CREATE_TABLE_ACTIVITATDOSSIER = "CREATE TABLE " + ActivitatDossier.TABLE + " ( "
                + ActivitatDossier.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ActivitatDossier.KEY_idDossier + " INTEGER, "
                + ActivitatDossier.KEY_idServei + " INTEGER, "
                + "FOREIGN KEY (" + ActivitatDossier.KEY_idDossier + ") " + " REFERENCES " + Dossier.TABLE +" (" + Dossier.KEY_ID +  "), "
                + "FOREIGN KEY (" + ActivitatDossier.KEY_idServei + ") " + "REFERENCES " + Servei.TABLE +" (" + Servei.KEY_ID +  "))";

        String CREATE_TABLE_VALORACIO = "CREATE TABLE " + Valoracio.TABLE + "("
                + Valoracio.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Valoracio.KEY_idDossier + " INTEGER, "
                + Valoracio.KEY_idServei + " INTEGER, "
                + Valoracio.KEY_idParam + " INTEGER, "
                + Valoracio.KEY_valor + " INTEGER, "
                + "FOREIGN KEY (" + Valoracio.KEY_idDossier + ") REFERENCES "+ Dossier.TABLE + " ("+Dossier.KEY_ID+"), "
                + "FOREIGN KEY (" + Valoracio.KEY_idServei + ") REFERENCES " + Servei.TABLE + " (" +Servei.KEY_ID +"), "
                + "FOREIGN KEY (" + Valoracio.KEY_idParam + ") REFERENCES " + Parametre.TABLE +" (" + Parametre.KEY_ID + "))";


        db.execSQL(CREATE_TABLE_DOSSIER);
        db.execSQL(CREATE_TABLE_ACTIVITATDOSSIER);
        db.execSQL(CREATE_TABLE_SERVEI);
        db.execSQL(CREATE_TABLE_PARAMETRES);
        db.execSQL(CREATE_TABLE_VALORACIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(db.getVersion() + 1);

        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Dossier.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ActivitatDossier.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Valoracio.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Servei.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Parametre.TABLE);

        // Create tables again
        onCreate(db);
    }
}

