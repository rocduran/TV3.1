package ad.uda.rocmoi.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


import ad.uda.rocmoi.adaptadors.EnquestaAdapter;
import ad.uda.rocmoi.localDB.DBinterface;
import ad.uda.rocmoi.pojos.Dossier;
import ad.uda.rocmoi.tools.DataLoader;

public class EnquestaListFragment extends ListFragment {

    EnquestaAdapter adaptador;
    ArrayList<Dossier> enquestes;

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private Callbacks mCallbacks = sDummyCallbacks;

    private int mActivatedPosition = ListView.INVALID_POSITION;

    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         * @param id
         */
        public void onItemSelected(String id);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    public EnquestaListFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enquestes = new ArrayList<Dossier>();


        adaptador = new EnquestaAdapter(getActivity(), enquestes);
        setListAdapter(adaptador);

        DataLoader dl = new DataLoader(getActivity(), adaptador);
        dl.execute();

        Log.d("AAAH", "aH");

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    public void onDetach() {
        super.onDetach();

        mCallbacks = sDummyCallbacks;
    }

    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        DBinterface database = new DBinterface(getActivity());
        ArrayList<Dossier> dossiers = database.getDossierList();

        Log.d("MMM","POSITION CLICKED: "+position);

        mCallbacks.onItemSelected(String.valueOf(dossiers.get(position).getId()));
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {

            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        DBinterface database = new DBinterface(getActivity());
        ArrayList<Dossier> dossiers = database.getDossierList();

        mActivatedPosition = dossiers.get(position).getId();
    }
}
