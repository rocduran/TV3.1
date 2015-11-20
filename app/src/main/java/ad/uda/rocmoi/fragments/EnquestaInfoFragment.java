package ad.uda.rocmoi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnquestaInfoFragment extends Fragment{

    public EnquestaInfoFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        /**
         * Basicament, en l'array params tenims tots els parametres d'un
         * servei a valorar, ja sigui Guia, Hotel o Activitat
         */
        ArrayList<String> params = this.getArguments().getStringArrayList("parametres");

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
            txt.setPadding(0,10,0,0);

            ViewGroup.LayoutParams ratingBarLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                  ViewGroup.LayoutParams.WRAP_CONTENT);

            RatingBar ratingBar = new RatingBar(getContext());
            ratingBar.setLayoutParams(ratingBarLayoutParams);
            ratingBar.setStepSize(1);
            ratingBar.setMax(5);
            ratingBar.setNumStars(5);
            ratingBar.setMinimumHeight(100);
            ratingBar.setScaleX((float) 1.5);
            ratingBar.setScaleY((float) 1.5);
            ratingBar.setPadding(0, 10, 0, 0);

            /**
             * Per al que fa al id, he trobat una pagina on t'ensenyen com
             * posarli un string en comtes de un int (dema t'ho ensenyo)
             **/

            gridLayout.addView(txt, 500, 100);
            gridLayout.addView(ratingBar);

        }

        return scrollView;
    }

}
