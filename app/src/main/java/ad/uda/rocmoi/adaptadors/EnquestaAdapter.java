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

public abstract class EnquestaAdapter extends BaseAdapter {
    private Context contexte;
    private int layout_list_view;
    private ArrayList<Enquesta> enquestes;

    public EnquestaAdapter (Context contexte, int layout_list_view, ArrayList<Enquesta> enquestes) {
        super();
        this.contexte = contexte;
        this.enquestes = enquestes;
        this.layout_list_view = layout_list_view;
    }

    public int getCount() {
        return enquestes.size();
    }

    public Object getItem(int position) {
        return enquestes.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int posicion, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(layout_list_view, null);
        }
        onEntrada(enquestes.get(posicion), view);
        return view;
    }

    public abstract void onEntrada(Enquesta enquesta, View view);

}