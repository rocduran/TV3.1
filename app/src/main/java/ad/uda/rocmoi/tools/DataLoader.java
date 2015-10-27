package ad.uda.rocmoi.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.adaptadors.EnquestaAdapter;
import ad.uda.rocmoi.localDB.DBhelper;
import ad.uda.rocmoi.localDB.DBinterface;
import ad.uda.rocmoi.localDB.DossierRepo;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Parametre;
import ad.uda.rocmoi.pojos.Servei;

public class DataLoader extends AsyncTask<String, Void, ArrayList<Dossier>> {
    String dadesJSON;
    public static ArrayList<Dossier> dossiers;
    public ArrayList<Servei> serveis;
    public ArrayList<Parametre> parametres;
    Context context;
    EnquestaAdapter adaptador;
    ProgressDialog pd;
    DBhelper database;

    //Constructor, rebem el Context i l'adaptador de Main
    public DataLoader(Context context, EnquestaAdapter adaptador) {
        this.context = context;
        this.adaptador=adaptador;
        pd = new ProgressDialog(context);
        database = new DBhelper(context);
    }

    //Mètode per recuperar les dades al servidor
    //I efectuar el Parsing per obtenir un ArraList de Usuaris
    @Override
    protected ArrayList<Dossier> doInBackground(String... params) {
        dadesJSON= getDataBD();
        parseJSON(dadesJSON);
        return dossiers;
    }
    //Mètode que s'executarà després de doInBackGround
    //Amb les dades rebudes, actualitzar el adaptador per que mostri les dades
    @Override
    protected void onPostExecute(ArrayList<Dossier> dossiers) {
        Log.d("M", " onPostExecute");
        for (Dossier tmp : dossiers)
            adaptador.add(tmp);
        Log.d("MISSATGE 7", " Tinc " + adaptador.getCount());
        adaptador.notifyDataSetChanged();
        pd.dismiss();
        super.onPostExecute(dossiers);
    }

    @Override
    protected void onPreExecute() {
        // Mètode que s'executara just abans i durant doInBackGround
        // un cop finalitzat doInBackGround, s'atura.
        pd.setIndeterminate(true);
        pd.setMessage(context.getResources().getString(
                R.string.MessageProgressDialog));
        pd.setTitle(R.string.TitleProgressDialog);
        // Mostramos el ProgressDialog.
        pd.show();
        // Eliminamos el ProgressDialog.

        super.onPreExecute();
    }


    //Connexió HTTP(php) per recuperar les dades en format JSON
    public String getDataBD()  {
        StringBuilder cadena = new StringBuilder("");
        try{
            //PHP que efectuarà el Query
            URL url = new URL("http://exemples.ua.ad/RocMoi/SelectDossiers.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            String responseMessage = connection.getResponseMessage();
            Log.d("M", "Response code " + responseMessage);

            //Recuperar Informació rebuda com una String amb salts de línia
            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = rd.readLine()) != null) {
                cadena.append(line);
            }

        } catch (IOException e) {
            // Cas d'exception, mostrar el log
            Log.d("M", "IOException " + e);
        }
        //Enviar al Log la Cadena JSON rebuda
        Log.d("******Missatge********", "\nCadena JSON : " + cadena.toString());
        return cadena.toString();

    }
    //Conversió de JSON a taula ArrayList d'usuaris
    public void parseJSON(String result) {
        Log.d("M", "parsing Cadena");
        try {

            JSONObject query = new JSONObject(result);
            JSONArray json_dossier = query.getJSONArray("dossier");
            dossiers = new ArrayList<>();
            //Creem Array dossiers amb objectes dossier
            for (int i= 0; i < json_dossier.length();i++){
                JSONObject tmp = json_dossier.getJSONObject(i);
                Dossier dossier = new Dossier(tmp.getInt("id"),tmp.getInt("preu"),tmp.getString("descripcio"));
                dossiers.add(dossier);
            }

            JSONArray json_parametre = query.getJSONArray("parametres");
            parametres = new ArrayList<>();
            for (int i=0; i < json_parametre.length();i++){
                JSONObject tmp = json_parametre.getJSONObject(i);
                Parametre parametre = new Parametre(tmp.getInt("id"),tmp.getInt("idTipus"),tmp.getString("descripcio"));
                parametres.add(parametre);
            }

            JSONArray json_servei = query.getJSONArray("servei");
            serveis = new ArrayList<>();
            for (int i=0; i < json_servei.length();i++){
                JSONObject tmp = json_servei.getJSONObject(i);
                Servei servei = new Servei(tmp.getInt("id"),tmp.getInt("idTipus"),tmp.getString("descripcio"), getLlistaParametres(tmp.getInt("idTipus")));
                serveis.add(servei);
            }

            JSONArray json_activitatDossier = query.getJSONArray("activitatdossier");
            Log.d("M activitatDossier", " " + json_activitatDossier);
            for (int i=0; i < json_activitatDossier.length();i++){
                JSONObject tmp = json_activitatDossier.getJSONObject(i);

                int idDossier = tmp.getInt("idDossier") -1;
                int idServei = tmp.getInt("idServei") - 1;

                if(serveis.get(idServei).getIdTipus() == 1){
                    dossiers.get(idDossier).setGuia(serveis.get(idServei));
                }

                if(serveis.get(idServei).getIdTipus() == 2){
                    dossiers.get(idDossier).setHotel(serveis.get(idServei));
                }

                if(serveis.get(idServei).getIdTipus() == 3){
                    dossiers.get(idDossier).setActivitat(serveis.get(idServei));
                }
            }

            Log.d("M dossier", " " + json_dossier);
            Log.d("M servei", " " + json_servei);
            Log.d("M parametre", " " + json_dossier);

        } catch (JSONException e) {
            Log.d("log_tag", "Error parsing dades " + e.toString());
        }
        /**
         * Test insercio dades a la bd local
         */
        for(int i =0; i < dossiers.size(); i++){
            DBinterface.insert(dossiers.get(i));
        }



    }

    public ArrayList<Parametre> getLlistaParametres(int tipus) {
        ArrayList<Parametre> llistaParametres = new ArrayList<>();
        for (int i =0; i < parametres.size(); i++){
            if(parametres.get(i).getIdTipus() == tipus){
                llistaParametres.add(parametres.get(i));
            }
        }
        return llistaParametres;
    }
}
