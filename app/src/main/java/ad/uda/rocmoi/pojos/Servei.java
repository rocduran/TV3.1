package ad.uda.rocmoi.pojos;

import java.util.ArrayList;

public class Servei {
    //Per a la definicio de la BD en SQLite
    public static final String TABLE = "servei";
    public static final String KEY_ID = "id";
    public static String KEY_idTipus = "idTipus";
    public static String KEY_descripcio = "descripcio";

    private int id;
    private int idTipus;
    private String descripcio;
    private ArrayList<Parametre> parametres;

    public Servei(){

    }

    public Servei(int id, int idTipus, String descripcio, ArrayList<Parametre> parametres) {
        this.id = id;
        this.idTipus = idTipus;
        this.descripcio = descripcio;
        this.parametres = parametres;
    }
    public Servei(int id, int idTipus, String descripcio) {
        this.id = id;
        this.idTipus = idTipus;
        this.descripcio = descripcio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipus() {
        return idTipus;
    }

    public void setIdTipus(int idTipus) {
        this.idTipus = idTipus;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public ArrayList<Parametre> getParametres() {
        return parametres;
    }

    public void setParametres(ArrayList<Parametre> parametres) {
        this.parametres = parametres;
    }
}
