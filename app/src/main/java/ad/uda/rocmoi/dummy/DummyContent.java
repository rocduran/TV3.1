package ad.uda.rocmoi.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ad.uda.rocmoi.pojos.ActivitatDossier;
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
    private static ArrayList<ActivitatDossier> activitatDossier = dummyActivitatDossiers();

    public DummyContent(){

        setServeis();
        setParametres();

    }

    private static ArrayList<Dossier> dummyDossiers() {
        ArrayList<Dossier> dossiers = new ArrayList<>();

        dossiers.add(new Dossier(1, 150, "DummyDossier 1 "));
        dossiers.add(new Dossier(2, 355, "DummyDossier 2 "));
        dossiers.add(new Dossier(3, 344, "DummyDossier 2 "));

        return dossiers;
    }

    private static ArrayList<Servei> dummyServeis() {
        ArrayList<Servei> serveis = new ArrayList<>();

        serveis.add(new Servei(1, 1, "DummyGuia 1"));
        serveis.add(new Servei(2, 1, "DummyGuia 2"));
        serveis.add(new Servei(3, 1, "DummyGuia 3"));

        serveis.add(new Servei(4, 2, "DummyHotel 1"));
        serveis.add(new Servei(5, 2, "DummyHotel 2"));
        serveis.add(new Servei(6, 2, "DummyHotel 3"));

        serveis.add(new Servei(7, 3, "DummyActivitat1"));
        serveis.add(new Servei(8, 3, "DummyActivitat2"));
        serveis.add(new Servei(9, 3, "DummyActivitat3"));

        return serveis;
    }

    private static ArrayList<Parametre> dummyParametres() {
        ArrayList<Parametre> parametres = new ArrayList<>();

        for(int i = 1; i < 5; i++){
            parametres.add(new Parametre(i, i, "DummyParametre " + i));
        }

        return parametres;
    }

    private static ArrayList<ActivitatDossier> dummyActivitatDossiers() {
        ArrayList<ActivitatDossier> activitatDossiers = new ArrayList<>();

        activitatDossiers.add(new ActivitatDossier(1,1,1));
        activitatDossiers.add(new ActivitatDossier(2,1,4));
        activitatDossiers.add(new ActivitatDossier(3,1,7));

        activitatDossiers.add(new ActivitatDossier(4,2,2));
        activitatDossiers.add(new ActivitatDossier(5,2,5));
        activitatDossiers.add(new ActivitatDossier(6,2,8));

        activitatDossiers.add(new ActivitatDossier(7,3,3));
        activitatDossiers.add(new ActivitatDossier(8,3,6));
        activitatDossiers.add(new ActivitatDossier(9,3,9));

        return activitatDossiers;
    }

    private void setServeis() {
        dossiers.get(0).setGuia(serveis.get(0));
        dossiers.get(0).setHotel(serveis.get(3));
        dossiers.get(0).setActivitat(serveis.get(6));

        dossiers.get(1).setGuia(serveis.get(1));
        dossiers.get(1).setHotel(serveis.get(4));
        dossiers.get(1).setActivitat(serveis.get(7));

        dossiers.get(2).setGuia(serveis.get(2));
        dossiers.get(2).setHotel(serveis.get(5));
        dossiers.get(2).setActivitat(serveis.get(8));
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

    public static ArrayList<Servei> getServeis(){
        return serveis;
    }

    public static ArrayList<Parametre> getParametres(){
        return parametres;
    }

    public static ArrayList<ActivitatDossier> getActivitatDossier() {
        return activitatDossier;
    }


}