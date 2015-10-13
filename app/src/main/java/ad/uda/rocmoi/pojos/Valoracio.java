package ad.uda.rocmoi.pojos;

import java.util.ArrayList;

/**
 * Created by Moï on 13/10/2015.
 */
public class Valoracio {
    private ArrayList<Parametre> valors;

    public Valoracio() {
        this.valors = new ArrayList<Parametre>();
    }

    public Valoracio(ArrayList<Parametre> valors) {
        this.valors = valors;
    }

    public ArrayList<Parametre> getValors() {
        return valors;
    }

    public void add(Parametre parametre){
        this.valors.add(parametre);
    }
}
