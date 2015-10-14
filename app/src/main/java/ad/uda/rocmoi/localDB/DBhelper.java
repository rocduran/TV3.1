package ad.uda.rocmoi.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ad.uda.rocmoi.pojos.Enquesta;
import ad.uda.rocmoi.pojos.Valoracio;

public class DBhelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "enquestes.db";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ENQUESTES = "CREATE TABLE " + Enquesta.TABLE  + "("
                + Enquesta.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Enquesta.KEY_preu + " INTEGER, "
                + Enquesta.KEY_descripcio + " TEXT, "
                + Enquesta.KEY_hotel + " TEXT, "
                + Enquesta.KEY_guia + " TEXT, "
                + Enquesta.KEY_activitat + " TEXT, "
                + Enquesta.KEY_valoracio + "INTEGER)";
        // TODO pensar la millor manera per enmagatzemar les valoracions
        /* String CREATE_TABLE_VALORACIONS = "CREATE TABLE " + Valoracio.TABLE +"("
                + Valoracio.KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Valoracio.KEY_idEnquesta + "INTEGER ,"

                ;
*/
        db.execSQL(CREATE_TABLE_ENQUESTES);

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

