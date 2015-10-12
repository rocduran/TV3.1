package ad.uda.rocmoi.pojos;

/**
 * Created by Moï on 12/10/2015.
 */
public class Enquesta {
    private int id;
    private String nom;

    public Enquesta (int id, String nom){
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
