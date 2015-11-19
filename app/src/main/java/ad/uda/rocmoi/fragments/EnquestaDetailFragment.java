package ad.uda.rocmoi.fragments;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.activities.EnquestaActivity;
import ad.uda.rocmoi.activities.EnquestaDetailActivity;
import ad.uda.rocmoi.activities.EnquestaListActivity;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.tools.DataLoader;

/**
 * A fragment representing a single EnquestaActivity detail screen.
 * This fragment is either contained in a {@link EnquestaListActivity}
 * in two-pane mode (on tablets) or a {@link EnquestaDetailActivity}
 * on handsets.
 */
public class EnquestaDetailFragment extends Fragment implements View.OnClickListener{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Dossier dossier;
    private static int posicio;

    public static int getPosicio() {
        return posicio+1;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EnquestaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // TODO carregar detalls des de la BD i presentarlos
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Carreguem el dossier seleccionat
            posicio = Integer.parseInt(getArguments().getString(ARG_ITEM_ID)) - 1;
            dossier = DataLoader.dossiers.get(posicio);

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
        TransitionManager.beginDelayedTransition(container, new Slide(5).setDuration(animShort));
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
