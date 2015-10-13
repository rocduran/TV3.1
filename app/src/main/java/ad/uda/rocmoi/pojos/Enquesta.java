package ad.uda.rocmoi.pojos;

/**
 * Created by Moï on 12/10/2015.
 */
public class Enquesta {
    private int id;
    private int preu;
    private String descripcio;

    public Enquesta (int id, int preu, String descripcio){
        this.id = id;
        this.preu = preu;
        this.descripcio = descripcio;
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
}
