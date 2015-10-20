package ad.uda.rocmoi.pojos;

public class Parametre {
    private int id;
    private int idTipus;
    private String descripcio;

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
