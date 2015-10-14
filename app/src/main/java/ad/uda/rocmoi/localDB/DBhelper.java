package ad.uda.rocmoi.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ad.uda.rocmoi.pojos.Enquesta;
import ad.uda.rocmoi.pojos.Valoracio;

public class DBhelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "moro.db";

    public SQLiteDatabase db;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String CREATE_TABLE_ENQUESTES = "CREATE TABLE " + Enquesta.TABLE  + "("
                + Enquesta.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Enquesta.KEY_preu + " INTEGER, "
                + Enquesta.KEY_descripcio + " TEXT)";*/

        String CREATE_TABLE_ACTIVITATDOSSIER = "CREATE TABLE activitatDossier ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "idDossier INTEGER, "
                + "idServei INTEGER)";

        String CREATE_TABLE_SERVEI = "CREATE TABLE servei ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "idTipus INTEGER, "
                + "Descripcio TEXT)";

        String CREATE_TABLE_PARAMETRES = "CREATE TABLE parametres ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "idTipus INTEGER, "
                + "descripcio TEXT)";

        String CREATE_TABLE_VALORACIO = "CREATE TABLE valoracio ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "idDossier INTEGER, "
                + "idServei INTEGER, "
                + "idPara INTEGER, "
                + "valor INTEGER)";

        String CREATE_TABLE_ENQUESTES = "CREATE TABLE valoracio ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "idDossier INTEGER, "
                + "idServei INTEGER, "
                + "idPara INTEGER, "
                + "valor INTEGER)";


        // TODO pensar la millor manera per enmagatzemar les valoracions
        /* String CREATE_TABLE_VALORACIONS = "CREATE TABLE " + Valoracio.TABLE +"("
                + Valoracio.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Valoracio.KEY_idEnquesta + "INTEGER ,"

                ;
*/
        db.execSQL(CREATE_TABLE_ENQUESTES);
        db.execSQL(CREATE_TABLE_ACTIVITATDOSSIER);
        db.execSQL(CREATE_TABLE_SERVEI);
        db.execSQL(CREATE_TABLE_PARAMETRES);
        db.execSQL(CREATE_TABLE_VALORACIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Enquesta.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Valoracio.TABLE);

        // Create tables again
        onCreate(db);

    }


}

