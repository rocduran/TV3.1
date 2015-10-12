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
import ad.uda.rocmoi.pojos.Enquesta;

public class DataLoader extends AsyncTask<String, Void, ArrayList<Enquesta>> {
    String dadesJSON;
    ArrayList enquestes;
    Context context;
    EnquestaAdapter adaptador;
    ProgressDialog pd;

    //Constructor, rebem el Context i l'adaptador de Main
    public DataLoader(Context context, EnquestaAdapter adaptador) {
        this.context = context;
        this.adaptador=adaptador;
        pd = new ProgressDialog(context);
    }
    //Mètode per recuperar les dades al servidor
    //I efectuar el Parsing per obtenir un ArraList de Usuaris
    @Override
    protected ArrayList<Enquesta> doInBackground(String... params) {
        dadesJSON=getDataDeBD();
        enquestes=parseJSON(dadesJSON);
        return enquestes;
    }
    //Mètode que s'executarà després de doInBackGround
    //Amb les dades rebudes, actualitzar el adaptador per que mostri les dades
    @Override
    protected void onPostExecute(ArrayList<Enquesta> enquestes) {
        for (Enquesta tmp : enquestes)
            enquestes.add(tmp);
        Log.d("MISSATGE 7", " Tinc " + adaptador.getCount());
        adaptador.notifyDataSetChanged();
        pd.dismiss();
        super.onPostExecute(enquestes);
    }

    /*@Override
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
    }*/


    //Connexió HTTP(php) per recuperar les dades en format JSON
    public String getDataDeBD()  {
        StringBuffer cadena = new StringBuffer("");
        try{
            //PHP que efectuarà el Query
            URL url = new URL("http://exemples.ua.ad/RocMoi/SelectDossiers.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            int responseCode=connection.getResponseCode();

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
    public ArrayList<Enquesta> parseJSON(String result) {
        ArrayList<Enquesta> enquestes = new ArrayList<Enquesta>();
        try {
            JSONArray jArray = new JSONArray(result);
            Log.d("MISSATGE4 ",""+jArray.length());
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                Enquesta enquesta = new Enquesta(json_data.getInt("id"), json_data.getString("ref"));

                enquestes.add(enquesta);
                Log.d("MISSATGE3 ",enquesta.getNom());
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing dades " + e.toString());
        }
        return enquestes;
    }

}
