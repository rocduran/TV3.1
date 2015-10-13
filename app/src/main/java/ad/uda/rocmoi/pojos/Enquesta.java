package ad.uda.rocmoi.pojos;

public class Enquesta {
    //Per a la definicio de la BD en SQLite
    public static final String TABLE = "Enquestes";
    public static final String KEY_ID = "id";
    public static String KEY_preu = "preu";
    public static String KEY_descripcio = "descripcio";
    public static String KEY_hotel = "hotel";
    public static String KEY_guia = "guia";
    public static String KEY_activitat = "activitat";
    public static String KEY_valoracio = "valoracio";


    //Attributs del pojo
    private int id;
    private int preu;
    private String descripcio;
    private String hotel;
    private String guia;
    private String activitat;

    private Valoracio valoracioHotel;
    private Valoracio valoracioGuia;
    private Valoracio valoracioActivitat;

    public Enquesta(int id, int preu, String descripcio, String hotel, String guia, String activitat) {
        this.id = id;
        this.preu = preu;
        this.descripcio = descripcio;
        this.hotel = hotel;
        this.guia = guia;
        this.activitat = activitat;
    }

    public Enquesta(int id, int preu, String descripcio, String hotel, String guia, String activitat, Valoracio valoracioHotel,                     Valoracio valoracioGuia, Valoracio valoracioActivitat) {
        this.id = id;
        this.preu = preu;
        this.descripcio = descripcio;
        this.hotel = hotel;
        this.guia = guia;
        this.activitat = activitat;
        this.valoracioHotel = valoracioHotel;
        this.valoracioGuia = valoracioGuia;
        this.valoracioActivitat = valoracioActivitat;
    }

    public int getId() {
        return id;
    }

    public int getPreu() {
        return preu;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getHotel() {
        return hotel;
    }

    public String getGuia() {
        return guia;
    }

    public String getActivitat() {
        return activitat;
    }

    public void setValoracioHotel(Valoracio valoracioHotel) {
        this.valoracioHotel = valoracioHotel;
    }

    public void setValoracioGuia(Valoracio valoracioGuia) {
        this.valoracioGuia = valoracioGuia;
    }

    public void setValoracioActivitat(Valoracio valoracioActivitat) {
        this.valoracioActivitat = valoracioActivitat;
    }
}
