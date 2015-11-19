package ad.uda.rocmoi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class EnquestaInfoFragment extends Fragment{

    public EnquestaInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ArrayList<String> params = this.getArguments().getStringArrayList("parametres");

        /**
         * El GridLayout serveix per possar nom del parametre a valora i slider
         * un al costat del altre ( 2 columnes i params.size()files
         */
        GridLayout gridLayout = new GridLayout(getActivity());
        gridLayout.setColumnCount(2);
        gridLayout.setRowCount(params.size());
        gridLayout.setPadding(50, 0, 50, 0);

        /**
         * Basicament, en l'array params tenims tots els parametres d'un
         * servei a valorar, ja sigui Guia, Hotel o Activitat
         *
         */

        TextView txt, txtValor;
        for (int i = 0; i < params.size(); i++){

            txt = new TextView(getContext());
            txt.setText(params.get(i));
            txt.setTextSize(30);
            txt.setId(i);

            /**
             * el txtValor serviria per mostrar el valor del seekbar en questio
             * (per els abueletes que no saben com va lo de la "barreta taronja XD"
             */
            txtValor = new TextView(getContext());

            SeekBar seekBar = new SeekBar(getContext());
            seekBar.setMax(5);
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(400, 50);
            seekBar.setLayoutParams(lp);
            /**
             * Per al que fa al id, he trobat una pagina on t'ensenyen com
             * posarli un string en comtes de un int (dema t'ho ensenyo)
             */
            seekBar.setId(i);


            gridLayout.addView(txt);
            gridLayout.addView(seekBar);


        }

        return gridLayout;
    }

}
