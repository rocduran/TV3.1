package ad.uda.rocmoi.pojos;

/**
 * Created by Moï on 13/10/2015.
 */
public class Parametre {
    private String descripcio;
    private int valor;

    public Parametre(String descripcio, int valor) {
        this.descripcio = descripcio;
        this.valor = valor;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public int getValor() {
        return valor;
    }
}
