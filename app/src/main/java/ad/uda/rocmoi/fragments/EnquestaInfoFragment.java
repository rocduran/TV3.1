package ad.uda.rocmoi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ad.uda.rocmoi.pojos.Valoracio;
import ad.uda.rocmoi.widgets.ValBar;

public class EnquestaInfoFragment extends Fragment{

    private ArrayList<Valoracio> valoracions;
    private ArrayList<ValBar> valBars;

    public EnquestaInfoFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        int idDossier = this.getArguments().getInt("idDossier");
        int idServei = this.getArguments().getInt("idServei");

        ArrayList<String> params = this.getArguments().getStringArrayList("parametres");
        ArrayList<Integer> paramsId = this.getArguments().getIntegerArrayList("parametresId");

        valoracions = new ArrayList<>();

        /**
         * ScrollView per si tenim mes de 5 parametres
         */
        ScrollView scrollView = new ScrollView(getContext());
        /**
         * El GridLayout serveix per possar nom del parametre a valora i slider
         * un al costat del altre ( 2 columnes i params.size()files)
         **/
        GridLayout gridLayout = new GridLayout(getActivity());
        gridLayout.setColumnCount(2);
        gridLayout.setRowCount(params.size());

        gridLayout.setPadding(100, 0,100,0);

        scrollView.addView(gridLayout);

        TextView txt;
        for (int i = 0; i < params.size(); i++){

            txt = new TextView(getContext());
            txt.setText(params.get(i));
            txt.setTextSize(22);
            txt.setPadding(0, 10, 0, 0);

            Valoracio valoracio = new Valoracio(idDossier, idServei, paramsId.get(i));
            ValBar valBar = new ValBar(getActivity(), valoracio);
            valoracions.add(valoracio);

            gridLayout.addView(txt,500,100);
            gridLayout.addView(valBar.getRatingBar());
        }

            return scrollView;
    }

    public ArrayList<Valoracio> getValoracions(){
        return this.valoracions;
    }
}
