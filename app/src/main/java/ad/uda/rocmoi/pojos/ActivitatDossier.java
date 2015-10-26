package ad.uda.rocmoi.pojos;

public class ActivitatDossier {
    //Per a la definicio de la BD en SQLite
    public static final String TABLE = "activitatDossier";
    public static final String KEY_ID = "id";
    public static String KEY_idDossier = "idDossier";
    public static String KEY_idServei = "idServei";

    private int id;
    private int idDossier;
    private int idServei;

    public ActivitatDossier(){

    }

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
}
