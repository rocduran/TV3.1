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
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Parametre;
import ad.uda.rocmoi.pojos.Valoracio;

public class DataLoader extends AsyncTask<String, Void, ArrayList<Dossier>> {
    String dadesJSON;
    ArrayList enquestes;
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
        enquestes=parseJSON(dadesJSON);
        return enquestes;
    }
    //Mètode que s'executarà després de doInBackGround
    //Amb les dades rebudes, actualitzar el adaptador per que mostri les dades
    @Override
    protected void onPostExecute(ArrayList<Dossier> enquestes) {
        for (Dossier tmp : enquestes)
            adaptador.add(tmp);
        Log.d("MISSATGE 7", " Tinc " + adaptador.getCount());
        adaptador.notifyDataSetChanged();
        pd.dismiss();
        super.onPostExecute(enquestes);
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
        StringBuffer cadena = new StringBuffer("");
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
            String line = "";
            while ((line = rd.readLine()) != null) {
                cadena.append(line);
            }

        } catch (IOException e) {
            // Cas d'exception, mostrar el log
            e.printStackTrace();
        }
        //Enviar al Log la Cadena JSON rebuda
        Log.d("******Missatge********", "\nCadena JSON : " + cadena.toString());
        return cadena.toString();

    }
    //Conversió de JSON a taula ArrayList d'usuaris
    public ArrayList<Dossier> parseJSON(String result) {
        ArrayList<Dossier> enquestes = new ArrayList<Dossier>();
        try {
            JSONObject query = new JSONObject(result);
            JSONArray json_dossier = query.getJSONArray("dossier");
            JSONArray json_servei = query.getJSONArray("servei");
            JSONArray json_parametre = query.getJSONArray("parametre");

            Log.d("M servei", " " + json_servei.length());
            /*for (int i = 0; i < query.length(); i++) {


                //Recuperem els valors del item
                int id = json_data.getInt("id");
                int preu = json_data.getInt("preu");
                String desc = json_data.getString("descripcio");

                String hotel = json_data.getString("hotel");
                String guia = json_data.getString("guia");
                String atv = json_data.getString("activitat");
                //les valoracion venen totes en valh separades per una coma
                //utilitzem split per a separarles
                String aux = json_data.getString("valh");
                String [] valh = aux.split(",");
                //Ara creem els diferents arrays que comtindran
                //els parametres a valorar de cada dossier
                Valoracio valHotel = crearValoracion(valh);

                aux = json_data.getString("valg");
                String [] valg = aux.split(",");
                //Ara creem els diferents arrays que comtindran
                //els parametres a valorar de cada dossier
                Valoracio valGuia = crearValoracion(valg);

                aux = json_data.getString("vala");
                String [] vala = aux.split(",");
                //Ara creem els diferents arrays que comtindran
                //els parametres a valorar de cada dossier
                Valoracio valAtv = crearValoracion(vala);

                //Creem el handler i l'afegim a la llista
                Dossier dossier = new Dossier(id, preu, desc, hotel, guia, atv, valHotel, valGuia, valAtv);
                enquestes.add(dossier);
            }*/
        } catch (JSONException e) {
            Log.d("log_tag", "Error parsing dades " + e.toString());
        }
        //volquem la taula d'enquestes a la BD local
            for (Dossier enq: enquestes) {
                String INSERT_ENQUESTA =
                        "insert into "+ Dossier.TABLE +" values(" +
                                enq.getId() +", " + enq.getPreu() + ", " +enq.getDescripcio()+")";
                database.db.execSQL(INSERT_ENQUESTA);
                String INSERT_GUIA =
                        "insert into activitatDossier values(" +
                                enq.getId() +", " + enq.getPreu() + ", " +enq.getDescripcio()+")";
                database.db.execSQL(INSERT_ENQUESTA);
            }

        return enquestes;
    }
}
