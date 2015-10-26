package ad.uda.rocmoi.pojos;

public class Parametre {
    //Per a la definicio de la BD en SQLite
    public static final String TABLE = "parametres";
    public static final String KEY_ID = "id";
    public static String KEY_idTipus = "idTipus";
    public static String KEY_descripcio = "descripcio";

    private int id;
    private int idTipus;
    private String descripcio;

    public Parametre(){

    }

    public Parametre(int id, int idTipus, String descripcio) {
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

}
