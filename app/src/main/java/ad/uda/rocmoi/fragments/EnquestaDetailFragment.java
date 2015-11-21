package ad.uda.rocmoi.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.activities.EnquestaActivity;
import ad.uda.rocmoi.activities.EnquestaDetailActivity;
import ad.uda.rocmoi.activities.EnquestaListActivity;
import ad.uda.rocmoi.localDB.ActivitatDossierRepo;
import ad.uda.rocmoi.localDB.DossierRepo;
import ad.uda.rocmoi.localDB.ServeiRepo;
import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.tools.DataLoader;

public class EnquestaDetailFragment extends Fragment implements View.OnClickListener{
    public static final String ARG_ITEM_ID = "id";

    private static Dossier dossier;
    private static int posicio;

    public static int getPosicio() {
        return posicio+1;
    }

    public EnquestaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitatDossierRepo activitatDossierRepo = new ActivitatDossierRepo(getActivity());
        DossierRepo dossierRepo = new DossierRepo(getActivity());
        ServeiRepo serveiRepo = new ServeiRepo(getActivity());


        // TODO carregar detalls des de la BD i presentarlos
        if (getArguments().containsKey(ARG_ITEM_ID)) {

            // Carreguem el dossier seleccionat
            posicio = Integer.parseInt(getArguments().getString(ARG_ITEM_ID));
            Log.d("M", "posicio  " + posicio);


            dossier = dossierRepo.getDossierById(posicio);
            /**
             * fins aqui be, el id, preu i descripcio del dossier estan be.
             * el problema es amb guia, hotel i activitat
             * si en aquest punt fas  dossier.getGuia() et retorna null,
             * aixi que basicament hem de "montar" el objecte dossier
             * recorrent les taules de activitatDossier i servei
             * (PD: si tens un moment podries mirar lo de com accedir a la
             * bd des de la tablet por fa ? XD xq e estat mirant aquesta tarda
             * pro no he trobat gran cosa :/
             * en principi es guarde a data/data/RocMoi/dbs/moro.db
             * pro a mi no hem surt la carpeta data a la root XDXD
             */

            /**
             * aqui agafem totes el serveis associats al dossier en questio
             * (si ho deixem aixi, nomes tire amb lo del dummy
             * per que al php no recuperem la taula activitatDossier XD)
             * TODO si ho deixem aixi, modificar el php + DataLoader amb la taula activitatDossier!
             */
            ArrayList<ActivitatDossier> activitats = activitatDossierRepo.getActivitatDossierByIdDossier(dossier.getId());
            Log.d("M", "activitats size " + activitats.size());

            /**
             * Aqui recuperem els serveis del dossier
             */
            ArrayList<Servei> serveis = new ArrayList<>();
            for(int i = 0; i < activitats.size(); i++) serveis.add(serveiRepo.getServeiById(activitats.get(i).getIdServei()));

            Log.d("M", "Servei size " + serveis.get(1));

            /**
             * I finalment montem l'objecte amb els attributs que falten
             * (es a dir guia, hotel i activitat)
             */
            for (int i = 0; i < serveis.size(); i++){
                if(serveis.get(i).getIdTipus() == 1) dossier.setGuia(serveis.get(i));
                if(serveis.get(i).getIdTipus() == 2) dossier.setHotel(serveis.get(i));
                if(serveis.get(i).getIdTipus() == 3) dossier.setActivitat(serveis.get(i));
            }

            /**
             * Aqui no es gaire follon, pro imaginat a lo de valoracio...
             * hem fot por nomes de pensaru xDD
             *
             */

            Log.d("M", "Dossier Guia " + dossier.getGuia().getDescripcio());
            Log.d("M", "Dossier Guia " + dossier.getActivitat().getDescripcio());

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(dossier.getDescripcio());
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int animShort = getResources().getInteger(android.R.integer.config_shortAnimTime);

        View rootView = inflater.inflate(R.layout.fragment_enquesta_detail, container, false);
        //TransitionManager.beginDelayedTransition(container, new Slide(5).setDuration(animShort));
        if (dossier != null) {
            ((TextView) rootView.findViewById(R.id.enquesta_detail_titol)).setText(dossier.getDescripcio());
            ((TextView) rootView.findViewById(R.id.enquesta_detail_preu)).setText(String.valueOf(dossier.getPreu()) + "€");

            ((ImageView) rootView.findViewById(R.id.imatge_guia)).setImageResource(R.drawable.guia);
            ((ImageView) rootView.findViewById(R.id.imatge_hotel)).setImageResource(R.drawable.hotel);
            ((ImageView) rootView.findViewById(R.id.imatge_activitat)).setImageResource(R.drawable.activitat);

            ((TextView) rootView.findViewById(R.id.enquesta_detail_guia)).setText(dossier.getGuia().getDescripcio());
            ((TextView) rootView.findViewById(R.id.enquesta_detail_hotel)).setText(dossier.getHotel().getDescripcio());
            ((TextView) rootView.findViewById(R.id.enquesta_detail_activitat)).setText(dossier.getActivitat().getDescripcio());
        }
        FloatingActionButton fab_realitzar = (FloatingActionButton) rootView.findViewById(R.id.fab_realitzar);
        fab_realitzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View rootView) {
                getActivity().startActivity(new Intent("ad.uda.rocmoi.activities.EnquestaActivity"));
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent enquestaIntent;
        enquestaIntent = new Intent(getActivity(), EnquestaActivity.class);
        enquestaIntent.putExtra("id", posicio);
        startActivity(enquestaIntent);
    }
}
