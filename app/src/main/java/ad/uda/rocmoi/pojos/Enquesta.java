package ad.uda.rocmoi.pojos;

import java.util.ArrayList;

/**
 * Created by Moï on 12/10/2015.
 */
public class Enquesta {
    private int id;
    private int preu;
    private String descripcio;
    private String hotel;
    private String guia;
    private String activitat;

    /*TODO lo dels arrays podrie ser una bona idea per guardaru tot en el mateix POJO
     pro fot un palazoo implementaru ara que flipa XDD
     dema mi fotre si et sembla be, es llarg pro no gaire complicat,
     ara que ja tinc una mica mes de la ma el puto sql xDD
     cada una de les classes tindrie 5 instancies de la classe
     Parametre (amb nom y valor, valor pot ser -1 si encara no s'ha valorat)
     crec que facilitarie bastant lo de sqlLite per guardaru en local xD
     */

    private Valoracio valoracioHotel;
    private Valoracio valoracioGuia;
    private Valoracio valoracioActivitat;

    /*
    Per guardaru en local, podriam tenir una sola instancia a memoria
    de l'enquesta que s'esta realitzan i cuan fem seguent usuari
    recuperar els valors de les valoracions en els respectius arrays
    volcar les dades a la BD en local i reinicialitzar els arrays
     */

    public Enquesta(int id, int preu, String descripcio, String hotel, String guia, String activitat) {
        this.id = id;
        this.preu = preu;
        this.descripcio = descripcio;
        this.hotel = hotel;
        this.guia = guia;
        this.activitat = activitat;
    }

    public Enquesta(int id, int preu, String descripcio, String hotel, String guia, String activitat, Valoracio valoracioHotel, Valoracio valoracioGuia, Valoracio valoracioActivitat) {
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
