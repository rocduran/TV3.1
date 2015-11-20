package ad.uda.rocmoi.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Parametre;
import ad.uda.rocmoi.pojos.Servei;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    private static ArrayList<Dossier> dossiers = dummyDossiers();
    private static ArrayList<Servei> serveis = dummyServeis();
    private static ArrayList<Parametre> parametres = dummyParametres();

    public DummyContent(){
        setServeis();
        setParametres();
    }

    private static ArrayList<Dossier> dummyDossiers() {
        ArrayList<Dossier> dossiers = new ArrayList<Dossier>();
        for(int i = 1; i < 15; i++){
            dossiers.add(new Dossier(i, i*100, "DummyDossier " + i));
        }
        return dossiers;
    }

    private static ArrayList<Servei> dummyServeis() {
        ArrayList<Servei> serveis = new ArrayList<Servei>();
        for(int i = 1; i < 4; i++){
            serveis.add(new Servei(i, i, "DummyServei " + i));
        }
        return serveis;
    }

    private static ArrayList<Parametre> dummyParametres() {
        ArrayList<Parametre> parametres = new ArrayList<Parametre>();
        for(int i = 1; i < 20; i++){
            parametres.add(new Parametre(i, i, "DummyParametre " + i));
        }
        return parametres;
    }

    private void setServeis() {
        for(int i = 0; i < dossiers.size(); i++){
            dossiers.get(i).setGuia(serveis.get(0));
            dossiers.get(i).setHotel(serveis.get(1));
            dossiers.get(i).setActivitat(serveis.get(2));
        }
    }

    private void setParametres() {
        for(int i = 0; i < dossiers.size(); i++){
            dossiers.get(i).getGuia().setParametres(parametres);
            dossiers.get(i).getHotel().setParametres(parametres);
            dossiers.get(i).getActivitat().setParametres(parametres);
        }
    }

    public static ArrayList<Dossier> getDossiers() {
        return dossiers;
    }
}
