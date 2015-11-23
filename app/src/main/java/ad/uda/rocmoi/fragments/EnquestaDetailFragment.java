package ad.uda.rocmoi.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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
import ad.uda.rocmoi.localDB.DBinterface;
import ad.uda.rocmoi.localDB.DossierRepo;
import ad.uda.rocmoi.localDB.ServeiRepo;
import ad.uda.rocmoi.pojos.ActivitatDossier;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.pojos.Servei;
import ad.uda.rocmoi.tools.DataLoader;

public class EnquestaDetailFragment extends Fragment{
    public static final String ARG_ITEM_ID = "id";

    private Dossier dossier;
    private int posicio;
    private DBinterface database;

    public EnquestaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new DBinterface(getActivity());

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            posicio = Integer.parseInt(getArguments().getString(ARG_ITEM_ID));

            Log.d("MMM","ENQUESTA DETAIL FRAG POSICIO: "+ posicio);

            dossier = database.getDossierById(posicio, getActivity());

            Log.d("MMM","ENQUESTA DETAIL FRAG DOSSIER: "+dossier.getDetails());

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(container, new Slide(5).setDuration(animShort));
        }
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
                Intent enquestaIntent;
                enquestaIntent = new Intent(getActivity(), EnquestaActivity.class);
                enquestaIntent.putExtra("id", dossier.getId());
                startActivity(enquestaIntent);
            }
        });

        return rootView;
    }

}
