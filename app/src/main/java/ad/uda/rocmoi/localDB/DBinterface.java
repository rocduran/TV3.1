package ad.uda.rocmoi.localDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Parametre;
import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.pojos.Valoracio;

public class DBinterface {
    private DBhelper dBhelper;
    private SQLiteDatabase database;
    private DossierRepo dossierRepo;
    private ActivitatDossierRepo activitatDossierRepo;
    private ParametreRepo parametreRepo;
    private ServeiRepo serveiRepo;
    private ValoracioRepo valoracioRepo;


    //Constructor
    // Creant una instància cap a la BD
    public DBinterface(Context context) {
        this.dBhelper = new DBhelper(context);
        this.dossierRepo = new DossierRepo(context);
        this.activitatDossierRepo = new ActivitatDossierRepo(context);
        this.parametreRepo = new ParametreRepo(context);
        this.serveiRepo = new ServeiRepo(context);
        this.valoracioRepo = new ValoracioRepo(context);

        openDB();
    }

    public void openDB() {
        database = dBhelper.getWritableDatabase();
    }

    //Tancar la BD
    public void closeDB() {
        database.close();
    }

    public void insert(Dossier dossier) {
        dossierRepo.insert(dossier);
    }

    public void insert(Servei servei) {
        serveiRepo.insert(servei);
    }

    public void insert(Parametre parametre) {
        parametreRepo.insert(parametre);
    }

    public void insert(ActivitatDossier activitatDossier) {
        activitatDossierRepo.insert(activitatDossier);
    }

    public void insert(Valoracio valoracio) {
        valoracioRepo.insert(valoracio);
    }

    public void deleteDossier(int id){
        dossierRepo.delete(id);
    }

    public void deleteActivitatDossier(int id){
        activitatDossierRepo.delete(id);
    }

    public void deleteParametre(int id){
        parametreRepo.delete(id);
    }

    public void deleteServei(int id){
        serveiRepo.delete(id);
    }

    public void deleteValoracio(int id){
        valoracioRepo.delete(id);
    }

    public ArrayList<Dossier> getDossierList() {
        return dossierRepo.getDossierList();
    }

    public ArrayList<ActivitatDossier> getActivitatDossierList() {
        return activitatDossierRepo.getActivitatDossierList();
    }

    public ArrayList<Parametre> getParametreList() {
        return parametreRepo.getParametreList();
    }

    public ArrayList<Servei> getServeiList() {
        return serveiRepo.getServeiList();
    }

    public Dossier getDossierById(int id, Context context){
        Log.d("MMM","DOSSIER ID INTERFACE INT: "+id);
        Dossier dossier = dossierRepo.getDossierById(id);
        Log.d("MMM","DOSSIER ID INTERFACE: "+dossier.getId());
        return dossierRepo.loadServices(dossier, context);
    }
}
