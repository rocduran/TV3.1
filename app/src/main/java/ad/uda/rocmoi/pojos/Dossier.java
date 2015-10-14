package ad.uda.rocmoi.pojos;

public class Dossier {
    //Per a la definicio de la BD en SQLite
    public static final String TABLE = "Dossier";
    public static final String KEY_ID = "id";
    public static String KEY_preu = "preu";
    public static String KEY_descripcio = "descripcio";

    //Attributs del pojo
    private int id;
    private int preu;
    private String descripcio;

    private Servei hotel;
    private Servei guia;
    private Servei activitat;

    public Dossier(int id, int preu, String descripcio, Servei hotel, Servei guia, Servei activitat) {
        this.id = id;
        this.preu = preu;
        this.descripcio = descripcio;
        this.hotel = hotel;
        this.guia = guia;
        this.activitat = activitat;
    }
    public Dossier(int id, int preu, String descripcio){
        this.id = id;
        this.preu = preu;
        this.descripcio = descripcio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreu() {
        return preu;
    }

    public void setPreu(int preu) {
        this.preu = preu;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Servei getHotel() {
        return hotel;
    }

    public void setHotel(Servei hotel) {
        this.hotel = hotel;
    }

    public Servei getGuia() {
        return guia;
    }

    public void setGuia(Servei guia) {
        this.guia = guia;
    }

    public Servei getActivitat() {
        return activitat;
    }

    public void setActivitat(Servei activitat) {
        this.activitat = activitat;
    }

    public String getDetails(){
        return "Nom: " + getDescripcio() + " \n Preu: " + getPreu() + "€.";
    }
}
