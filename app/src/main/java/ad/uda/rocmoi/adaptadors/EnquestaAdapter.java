package ad.uda.rocmoi.adaptadors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ad.uda.rocmoi.R;
import ad.uda.rocmoi.pojos.Enquesta;

public class EnquestaAdapter extends ArrayAdapter<Enquesta> {

    public EnquestaAdapter(Context context, List<Enquesta> enquestes) {
        super(context, 0, enquestes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obtenint una instància del XML per presentar les dades
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_enquesta, parent, false);

        }

        //Referencia al Camps
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvSubTitle = (TextView)convertView.findViewById(R.id.tvSubTitle);



        //Mapejar informació a mostrar a partir d'un Item
        Enquesta e = getItem(position);
        tvTitle.setText("Enquesta "+ String.valueOf(e.getId()));
        tvSubTitle.setText(e.getDescripcio());

        return convertView;
    }
}