package ad.uda.rocmoi.pojos;

import java.util.ArrayList;

public class Valoracio {
    //Per a la creacio de la BD en SQLite
    public static final String TABLE = "valoracio";
    public static final String KEY_ID = "id";
    public static final String KEY_idDossier = "idDossier";
    public static final String KEY_idServei = "idServei";
    public static final String KEY_idParam = "idParam";
    public static final String KEY_valor = "valor";

    private int id;
    private int idDossier;
    private int idServei;
    private int idParam;
    private int valor;

    public Valoracio(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(int idDossier) {
        this.idDossier = idDossier;
    }

    public int getIdServei() {
        return idServei;
    }

    public void setIdServei(int idServei) {
        this.idServei = idServei;
    }

    public int getIdParam() {
        return idParam;
    }

    public void setIdParam(int idParam) {
        this.idParam = idParam;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Per si ens interessa carregar les valoracions que hi ha
     * guardades al servidor (mes endavant pot ser)
     */
    /*public Valoracio(ArrayList<Parametre> valors) {
        this.valors = valors;
    }

    private ArrayList<Parametre> valors;

   public ArrayList<Parametre> getValors() {
        return valors;
    }

    public void add(Parametre parametre){
        this.valors.add(parametre);
    }*/

}
